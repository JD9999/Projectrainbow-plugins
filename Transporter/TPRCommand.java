package Transporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import PluginReference.MC_Command;
import PluginReference.MC_DirectionNESWUD;
import PluginReference.MC_Location;
import PluginReference.MC_Player;
import PluginReference.MC_Sign;
import PluginReference.MC_World;
import PluginReference.RainbowUtils;

public class TPRCommand implements MC_Command {

	protected static String doublequote = String.valueOf('"');

	@Override
	public List<String> getAliases() {
		List<String> list = new ArrayList<String>();
		list.add("teleporter");
		return list;
	}

	@Override
	public String getCommandName() {
		return "tpr";
	}

	@Override
	public String getHelpLine(MC_Player plr) {
		return "Incorrect command.";
	}

	@Override
	public List<String> getTabCompletionList(MC_Player plr, String[] args) {
		return null;
	}

	@Override
	public void handleCommand(MC_Player plr, String[] args) {
		if(args.length == 0){
			print(plr, "Commands:");
			print(plr, "/tpr build [name] - build a gate with the specified name");
			print(plr, "/tpr destroy [name] - destroys the gate with the specified name");
			print(plr, "/tpr link [gate1] [gate2] - links the two gates");
			print(plr, "/tpr unlink [gate1] [gate2] - unlinks the two gates");
			print(plr, "/tpr gate [gate] - teleports you to a gate");
			print(plr, "/tpr list - lists all the gate names");
			print(plr, "/tpr clear - clears all the gates");
			print(plr, "/tpr stop - stops all teleportations");
			print(plr, "/tpr restart - restarts all teleportations");
			print(plr, "/tpr reload - reloads the plugin");
			print(plr, "/tpr debug - debug the plugin's stored information");		
			return;
		}
		String mainArg = obtainArg(args, 0);
		String nameArg = obtainArg(args, 1);
		String otherArg = obtainArg(args, 2);
		switch (mainArg){
		case "build":
			if(plr == null) System.out.println("Only players can create gates!");
			else if(nameArg == null) plr.sendMessage("Need to give the gate a name!");
			else if(!plr.hasPermission("tpr.build")) plr.sendMessage("You do not have the required permission " + doublequote + "tpr.build" + doublequote);
			else{
				for(Gate g : MyPlugin.gates){
					if(g.getName().equals(nameArg)){
						plr.sendMessage("A gate already exists with that name!");
						return;
					}
				}
				MC_Location plrLoc = plr.getLocation();
				List<MC_Location> portalLocs = PortalBuilder.build(plrLoc);
				Gate g = new Gate(nameArg, portalLocs);
				MC_DirectionNESWUD dir = PortalBuilder.direction;
				g.setDirection(dir);
				g.buildSign();
				MC_Sign sign = g.getSign();
				List<String> lines = new ArrayList<String>();
				lines.add(g.getName());
				for(int i = 0; i < 3; i++){
					lines.add("");
				}
				sign.setLines(lines);
				MyPlugin.gates.add(g);
				plr.sendMessage("Built portal at: " + g.getTransportLocations().get(1).toString());
				save();
			}
			break;
		case "destroy":
			if(plr != null && !plr.hasPermission("tpr.destroy")) plr.sendMessage("You do not have the required permission " + doublequote + "tpr.destroy" + doublequote);
			Gate gate = null;
			boolean c = false;
			for(Gate g : MyPlugin.gates){
				if(c) continue;
				if(g.getName().equals(nameArg)){
					gate = g;
					c = true;
				}
			}
			if(gate != null){
				MC_Location worldLoc = gate.getLocations().get(0);
				MC_World world = RainbowUtils.getServer().getWorld(worldLoc.dimension);
				for(MC_Location loc : gate.getLocations()){
					world.setBlockAt(loc, RainbowUtils.getServer().getBlock(0), 0);
				}
				MC_Location signLoc = gate.getSignLocation();
				world.setBlockAt(signLoc, RainbowUtils.getServer().getBlock(0), 0);
				if(otherArg != "false")MyPlugin.gates.remove(MyPlugin.gates.indexOf(gate));
				for(Gate g : MyPlugin.gates){
					g.removeLink(gate);
				}
				print(plr, "Destroyed gate: " + gate.getName());
				save();
			}else print(plr, "Unable to find gate: " + nameArg);
			break;
		case "link":
			if(nameArg == null || otherArg == null) print(plr, "You need to supply two gates to link!");
			else if(plr != null && !plr.hasPermission("tpr.link")) plr.sendMessage("You do not have the required permission " + doublequote + "tpr.link" + doublequote);
			else{
				Gate gateName = null;
				Gate gateOther = null;
				for(Gate g : MyPlugin.gates){
					if(g.getName().equals(nameArg)) gateName = g;
					if(g.getName().equals(otherArg)) gateOther = g;
				}
				boolean toContinue = true;
				if(gateName == null){
					print(plr, nameArg + " is not a registered gate!");
					toContinue = false;
				}
				if(gateOther == null){
					print(plr, otherArg + " is not a registered gate!");
					toContinue = false;
				}
				if(toContinue){
					gateName.addLink(gateOther); //The other gate will be linked automatically
					print(plr, "linked: " + gateName.getName() + " with " + gateOther.getName());
					save();
				}else print(plr, "Please try again.");
			}
			break;
		case "unlink":
			if(nameArg == null || otherArg == null) print(plr, "You need to supply two gates to link!");
			else if(plr != null && !plr.hasPermission("tpr.unlink")) plr.sendMessage("You do not have the required permission " + doublequote + "tpr.unlink" + doublequote);
			else{
				Gate gateName = null;
				Gate gateOther = null;
				for(Gate g : MyPlugin.gates){
					if(g.getName().equals(nameArg)) gateName = g;
					if(g.getName().equals(otherArg)) gateOther = g;
				}
				boolean toContinue = true;
				if(gateName == null){
					print(plr, nameArg + " is not a registered gate!");
					toContinue = false;
				}
				if(gateOther == null){
					print(plr, otherArg + " is not a registered gate!");
					toContinue = false;
				}
				if(toContinue){
					gateName.removeLink(gateOther); //The other gate will be unlinked automatically
					print(plr, "unlinked: " + gateName.getName() + " with " + gateOther.getName());
					save();
				}else print(plr, "Please try again.");
			}
			break;
		case "list":
			if(MyPlugin.gates.size() != 0){
				for(Gate g : MyPlugin.gates){
					print(plr, g.getName());
				}
			}else print(plr, "No gates exist!");
			break;
		case "gate":
			if(plr == null) System.out.println("Only players can transport between gates!");
			else if(nameArg == null) plr.sendMessage("You need to supply the name of the gate!");
			else if(!plr.hasPermission("tpr.gate")) plr.sendMessage("You do not have the required permission " + doublequote + "tpr.gate" + doublequote);
			else{
				boolean found = false;
				for(Gate g : MyPlugin.gates){
					if(g.getName().equals(nameArg)){
						found = true;
						GateRequest request = new GateRequest(g);
						g.sendPlayer(plr, request);
						plr.sendMessage("You went to portal " + g.getName());
					}
				}
				if(!found) plr.sendMessage("Gate: " + nameArg + " was not found!");
			}
			break;
		case "debug":
			if(plr != null){
				if(!plr.hasPermission("tpr.debug")) plr.sendMessage("You do not have the required permission " + doublequote + "tpr.debug" + doublequote);
				else{
					plr.sendMessage("The debugging will appear on the console");
					debug();
				}
			}else debug(); 
			break;
		case "clear":
			if(plr != null){
				if(!plr.hasPermission("tpr.destroy")) plr.sendMessage("You do not have the required permission " + doublequote + "tpr.destroy" + doublequote);
				else clear(plr);
			}else clear(plr);
			break;
		case "stop":
			if(plr != null){
				if(!plr.hasPermission("tpr.control")) plr.sendMessage("You do not have the required permission " + doublequote + "tpr.control" + doublequote);
				else stop(plr);
			}else stop(plr);
			break;
		case "restart":
			if(plr != null){
				if(!plr.hasPermission("tpr.control")) plr.sendMessage("You do not have the required permission " + doublequote + "tpr.control" + doublequote);
				else restart(plr);
			}else restart(plr);
			break;
		case "reload":
			if(plr != null){
				if(!plr.hasPermission("tpr.control") || !plr.hasPermission("tpr.io")) plr.sendMessage("You do not have both of the required permissions " + doublequote + "tpr.control" + doublequote + " and " + doublequote + "tpr.io" + doublequote);
				else reload(plr);
			}else reload(plr);
			break;
		default:
			print(plr, "Commands:");
			print(plr, "/tpr build [name] - build a gate with the specified name");
			print(plr, "/tpr destroy [name] - destroys the gate with the specified name");
			print(plr, "/tpr link [gate1] [gate2] - links the two gates");
			print(plr, "/tpr unlink [gate1] [gate2] - unlinks the two gates");
			print(plr, "/tpr gate [gate] - teleports you to a gate");
			print(plr, "/tpr list - lists all the gate names");
			print(plr, "/tpr clear - clears all the gates");
			print(plr, "/tpr stop - stops all teleportations");
			print(plr, "/tpr restart - restarts all teleportations");
			print(plr, "/tpr reload - reloads the plugin");
			print(plr, "/tpr debug - debug the plugin's stored information");
			break;
		}
	}

	@Override
	public boolean hasPermissionToUse(MC_Player plr) {
		return plr == null ? true : plr.hasPermission("tpr.tpr");
	}

	private void save() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(MyPlugin.FILE))){
			writer.write("");
			for(Gate g : MyPlugin.gates){
				writeGate(g, writer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> getStringList(List<Gate> allLinks) {
		List<String> names = new ArrayList<>();
		for(Gate g : allLinks){
			names.add(g.getName());
		}
		if(allLinks.size() == names.size()) return names;
		else throw new IllegalStateException("Problem obtaining a name for all the gates!");
	}

	private void writeGate(Gate gate, BufferedWriter writer) throws IOException{
		writer.write(gate.getName());
		writer.newLine();
		writer.write(MyPlugin.TAB + "links:");
		writer.newLine();
		for(Gate g : gate.getAllLinks()){
			writer.write(MyPlugin.TAB + MyPlugin.TAB + g.getName());
			writer.newLine();
		}
		writer.write(MyPlugin.TAB + "direction:");
		writer.newLine();
		writer.write(MyPlugin.TAB + MyPlugin.TAB + gate.getDirection().name());
		writer.newLine();
		writer.write(MyPlugin.TAB + "locations:");
		writer.newLine();
		for(MC_Location loc : gate.getLocations()){
			writer.write(MyPlugin.TAB + MyPlugin.TAB + toString(loc));
			writer.newLine();
		}
	}

	private String toString(MC_Location loc) {
		return String.valueOf(loc.x) + ", " + String.valueOf(loc.y) + ", " + String.valueOf(loc.z) + ", " + String.valueOf(loc.dimension);
	}

	private String obtainArg(String[] args, int i) {
		return args.length >= i + 1 ? args[i] : null;
	}

	private void print(MC_Player plr, String string) {
		if(plr !=null) plr.sendMessage(string);
		else System.out.println(string);
	}

	private void debug(){
		for(Gate g : MyPlugin.gates){
			System.out.println(g.getName() + ":");
			System.out.println(MyPlugin.TAB + "Built at locations:");
			for(MC_Location loc : g.getLocations()){
				System.out.println(MyPlugin.TAB + MyPlugin.TAB + toString(loc));
			}
			System.out.println(MyPlugin.TAB + "Accessible from:");
			for(MC_Location loc : g.getTransportLocations()){
				System.out.println(MyPlugin.TAB + MyPlugin.TAB +  toString(loc));
			}
			if(g.isEastOrWest())System.out.println("Gate is facing east or west!");
			else System.out.println("Gate is not facing east or west!");
			System.out.println("At direction: " + g.getDirection().name());
			System.out.println("Linked with gate(s) " + RainbowUtils.GetCommaList(getStringList(g.getAllLinks())));
		}
	}

	private void clear(MC_Player plr){
		String[] arguments = new String[3];
		arguments[0] = "destroy";
		for(Gate g : MyPlugin.gates){
			arguments[1] = g.getName();
			arguments[2] = "false";
			handleCommand(plr, arguments);
		}
		MyPlugin.gates.clear();
		print(plr, "Done!");
		save();
	}

	private void stop(MC_Player plr){
		MyPlugin.run = false;
		print(plr, "Teleportations turned off!");
	}

	private void restart(MC_Player plr){
		MyPlugin.run = true;
		print(plr, "Teleportations turned on!");
	}

	private void reload(MC_Player plr){
		print(plr, "Reloading plugin!");
		stop(plr);
		MyPlugin.gates.clear();
		MyPlugin.gateLinkStores.clear();
		MyPlugin.EXCEPTIONS_LIST.clear();
		MyPlugin.load();
		print(plr, "Reloading complete!");
		restart(plr);
	}

}
