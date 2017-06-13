package Transporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import PluginReference.MC_Block;
import PluginReference.MC_DirectionNESWUD;
import PluginReference.MC_EventInfo;
import PluginReference.MC_Location;
import PluginReference.MC_Player;
import PluginReference.MC_Server;
import PluginReference.PluginBase;
import PluginReference.PluginInfo;
import PluginReference.RainbowUtils;

public class MyPlugin extends PluginBase{

	public static MC_Block PORTAL_BLOCK; //Cannot be final because it is impossible to define it at runtime
	protected static List<Gate> gates = new ArrayList<>();
	protected static List<GateLinkStore> gateLinkStores = new ArrayList<>();
	public static boolean run = true;
	public static String location;
	public static final File DIRS = new File("plugins_mod" + File.separator + "Transporter" + File.separator);
	public static final File FILE = new File(DIRS.getAbsolutePath() + File.separator + "gates.tpr");
	public static final String TAB = "    ";
	public static final List<Exception> EXCEPTIONS_LIST = new ArrayList<>();

	public void onStartup(MC_Server server){
		System.out.println("Plugin starting! Lets hope it works! :)");
		server.registerCommand(new TPRCommand());
		PORTAL_BLOCK = server.getBlock(49); //Obsidian
		if(!FILE.exists()){
			try {
				DIRS.mkdirs();
				FILE.createNewFile();
			} catch (IOException e) {
				EXCEPTIONS_LIST.add(e);
			}
		}else load();
	}

	public PluginInfo getPluginInfo(){
		PluginInfo info = new PluginInfo();
		info.description = "Transport players between worlds using teleportation gates: V0.1 started";
		info.version = "V0.1";
		return info;
	}

	public void onAttemptPlayerMove(MC_Player plr, MC_Location locFrom, MC_Location locTo, MC_EventInfo ei){
		if(!sameLoc(locFrom, locTo)){
			if(run){
				for(Gate g : gates){
					List<Gate> links = g.getAllLinks();
					if(!links.isEmpty()){
						List<MC_Location> locs = g.getTransportLocations();
						for(MC_Location loc : locs){
							MC_Location regLoc = PortalBuilder.roundOffLocation(locTo);
							location = regLoc.toString();
							if(loc.equals(regLoc)){
								GateRequest request = new GateRequest(links);
								g.sendPlayer(plr, request);
								return;
							}
						}
					}	
				}
			}
		}
	}

	public static void load() {
		String name = null;
		List<MC_Location> locations = new ArrayList<MC_Location>();
		List<String> lines;
		MC_DirectionNESWUD direction = null;
		int key = 2;
		int boolCount = 0;
		lines = readLines();
		if(lines.size() == 0){
			System.out.println("No gates to load!");
			System.out.println("Plugin loaded successfully!");
		}else{
			List<String> linkNames = new ArrayList<String>();
			for(String line : lines){
				if(line.startsWith(TAB + TAB)){
					switch(key){
					case 1:
						String location = line.trim();
						MC_Location loc = toMC_Location(location);
						locations.add(loc);
						break;
					case 2:
						linkNames.add(line.trim());
						break;
					case 3:
						direction = MC_DirectionNESWUD.valueOf(line.trim());
					}
				}else if(line.startsWith(TAB)){
					try{
						String indicator = line.substring(0, line.indexOf(':')).trim();
						switch(indicator){
						case "locations":
							key = 1;
							break;
						case "links":
							key = 2;
							break;
						case "direction":
							key = 3;
							break;
						}
					}catch(StringIndexOutOfBoundsException e){
						System.err.println("Error with line:" + line);
						EXCEPTIONS_LIST.add(e);
					}
				}else{
					if(name == null){
						name = line;
						continue;
					}else if(key == 1){
						Gate g = new Gate(name, locations);
						GateLinkStore store = new GateLinkStore(linkNames);
						gates.add(g);
						g.setDirection(direction);
						gateLinkStores.add(store);
						key = 0;
						name = line;
						locations.clear();
						linkNames.clear();
					}else{
						boolCount++;
						GateBuildFailureException e = new GateBuildFailureException(name, locations);
						EXCEPTIONS_LIST.add(e);
					}
				}
			}
			Gate gate = new Gate(name, locations);
			gate.setDirection(direction);
			GateLinkStore gls = new GateLinkStore(linkNames);
			gates.add(gate);
			gateLinkStores.add(gls);
			key = 0;
			if(boolCount == 0){
				System.out.println("All gates info loaded successfully! Building Gate objects");
			}else{
				System.err.println(boolCount + " gates failed to load. Some gates may have name or linkage issues. If this becomes a consistent issue, please report.");
			}
			if(gateLinkStores.size() == gates.size()){
				for(int i = 0; i < gates.size(); i++){
					Gate g = gates.get(i);
					GateLinkStore store = gateLinkStores.get(i);
					for(String gateName: store.getLinkNames()){
						boolean isFound = false;
						for(Gate possGate : gates){
							if(isFound) continue;
							if(possGate.getName().equals(gateName)){
								isFound = true;
								g.addLink(possGate);
							}
						}
						if(!isFound){
							System.err.println("Unable to find gate: " + gateName);
						}
					}
				}
			}else{
				IllegalStateException e = new IllegalStateException("Each gate must have matching info!");
				EXCEPTIONS_LIST.add(e);
			}
			int count = 0;	
			for(Exception e : EXCEPTIONS_LIST){
				count++;
				e.printStackTrace();
			}
			if(count > 0){
				System.err.println("FATAL ISSUE! Please report the plugin's startup log and config file. Stopping server at first convenient time.");
				RainbowUtils.getServer().executeCommand("stop");
			}else System.out.println("Plugin able to load successfully!");
		}
	}

	/**
	 * Works out if the player is standing in the same location, and have just rotated their head or something like that.
	 * @param locFrom the player's last location
	 * @param locTo the player's new location
	 * @return whether the location is the same
	 */
	private boolean sameLoc(MC_Location locFrom, MC_Location locTo) {
		return((locFrom.x == locTo.x) && (locFrom.y == locTo.y) && (locFrom.z == locTo.z));
	}

	private static List<String> readLines() {
		if(FILE.length() == 0) return new ArrayList<String>();
		try {
			return Files.readAllLines(FILE.toPath());
		} catch (IOException e) {
			EXCEPTIONS_LIST.add(e);
		}
		return null;
	}

	private static MC_Location toMC_Location(String location) {
		String[] values = location.split(", ");
		double x = Double.parseDouble(values[0]);
		double y = Double.parseDouble(values[1]);
		double z = Double.parseDouble(values[2]);
		int dim = Integer.parseInt(values[3]);
		MC_Location loc = new MC_Location(x, y, z, dim);
		return loc;
	}

}
