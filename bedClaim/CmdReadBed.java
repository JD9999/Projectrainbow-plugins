package bedClaim;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import PluginReference.MC_Command;
import PluginReference.MC_Player;

public class CmdReadBed implements MC_Command{

	@Override
	public List<String> getAliases() {
		List<String> aliases = new ArrayList<String>();
		aliases.add("showbed");
		return aliases;
	}

	@Override
	public String getCommandName() {
		return "readbed";
	}

	@Override
	public String getHelpLine(MC_Player arg0) {
		return "Reads the bed claim file for specific player or general bed claims";
	}

	@Override
	public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
		return null;
	}

	@Override
	public void handleCommand(MC_Player plr, String[] array) {
		MyPlugin.doStartup();
		if(array.length < 1){
			try {
				List<String> filecontents = Files.readAllLines(MyPlugin.bedfile.toPath());
				plr.sendMessage(removeDuplicatesAndNumbers(filecontents));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(array.length < 2){
			String plrname = array[0];
			if(readBed().contains(plrname)){
				List<String> filecontents;
				try {
					filecontents = Files.readAllLines(MyPlugin.bedfile.toPath());
				
				for(int i = 0; i < filecontents.size(); i++){
					String line = filecontents.get(i);
					if(line.contains(plrname)) plr.sendMessage(line);
				}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	@Override
	public boolean hasPermissionToUse(MC_Player plr) {
		if(plr !=null) return plr.isOp();
		else return true;
	}
	
	private String removeDuplicatesAndNumbers(List<String> filecontents) {
		String returnline = "";
		List<String> additions = new ArrayList<String>();
		for(int i = 0; i < filecontents.size(); i++){
			String line = filecontents.get(i);
			if((line.length() <= String.valueOf(MyPlugin.increment).length()) && !additions.contains(line)) additions.add(line);
		}
		for(int i = 0; i < additions.size(); i++){
			String line = additions.get(i);
			returnline = returnline + line + System.getProperty("line.separator");
		}
		return returnline;
	}
	public static String readBed(){
		try {
			return new String(Files.readAllBytes(MyPlugin.bedfile.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}