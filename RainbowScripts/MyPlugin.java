package RainbowScripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import PluginReference.*;

public class MyPlugin extends PluginBase{

	private String pluginDir = "plugins_mod" + File.separator + "RainbowScripts";
	List<String> lines;
	MC_Server server;

	public void onStartup(MC_Server svr){
		server = svr;
		new File(pluginDir).mkdirs();
		System.out.println("Plugin starting! Let's hope this works! :)");
		File file = new File(pluginDir + File.separator + "instruction.txt");
		if(file.exists()) System.out.println("File exists!");
		else
			try {
				System.out.println("Creating file!");
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		try {
			List<String> contents = Files.readAllLines(Paths.get(file.toURI()));
			lines = contents;
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < lines.size(); i++){
			String line = lines.get(i);
			if(line.startsWith("When the server starts")){
				Instruction in = getInstructionFor(line, 22, "server");
				if(in instanceof MessageInstruction) System.out.println(in.getReturnMessage());
				else if(in instanceof CommandInstruction) server.executeCommand(in.getReturnMessage());
				else if(in.equals(null)) System.out.println("No instruction can be found for your message!");
				else{
					System.out.println("Plugin is potentially broken! Please report!");
				}
			}else if(line.startsWith("When server starts")){
				Instruction in = getInstructionFor(line, 18, "server");
				if(in instanceof MessageInstruction) System.out.println(in.getReturnMessage());
				else if(in instanceof CommandInstruction) server.executeCommand(in.getReturnMessage());
				else if(in.equals(null)) System.out.println("No instruction can be found for your message!");
				else{
					System.out.println("Plugin is potentially broken! Please report!");
				}
			}
		}
			
	}
	private Instruction getInstructionFor(String line, int i, String type) {
		String s = line.substring(i + 4);
		if(s.startsWith("say")){
			String mes = s.substring(4);
			return new MessageInstruction(type, mes);
		}else if(s.startsWith("command")){
			String mes = s.substring(8);
			return new CommandInstruction(type, mes, true);
		}
		return null;
	}
	public void onShutdown(){
		System.out.println("Shutting down RainbowScripts!");
	}
	
	public void onPlayerInput(MC_Player plr, String mes, MC_EventInfo ei){
		if(mes.equalsIgnoreCase("/rescript")){
			ei.isCancelled = true;
			try {
				lines = Files.readAllLines(Paths.get(new File(pluginDir + File.separator + "instruction.txt").toURI()));
				plr.sendMessage("Rescripting complete!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void onPlayerJoin(MC_Player plr){
		for(int i = 0; i < lines.size(); i++){
			String line = lines.get(i);
			if(line.startsWith("When a player joins")){
				Instruction in = getInstructionFor(line, 19, "player");
				if(in instanceof MessageInstruction) plr.sendMessage(in.getReturnMessage());
				else if(in instanceof CommandInstruction) plr.executeCommand(in.getReturnMessage());
				else if(in.equals(null)) System.out.println("No instruction can be found for your message!");
				else{
					System.out.println("Plugin is potentially broken! Please report!");
				}
			}
		}
	}
	
	public PluginInfo getPluginInfo(){
		PluginInfo info = new PluginInfo();
		info.description = "A plugin which allows basic sentence reading to do stuff around your server: V0.1 started";
		info.name = "RainbowScripts";
		info.version = "V0.1";
		return info;
	}
	
}
