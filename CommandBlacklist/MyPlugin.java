package CommandBlacklist;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import PluginReference.ChatColor;
import PluginReference.MC_EventInfo;
import PluginReference.MC_Player;
import PluginReference.MC_Server;
import PluginReference.PluginBase;
import PluginReference.PluginInfo;

public class MyPlugin extends PluginBase {

	private String pluginDir = "plugins_mod" + File.separator + "CommandBlacklist" + File.separator;
	private File cmd;
	private File mes;
	
	public void onStartup(MC_Server argServer)
	{
		System.out.println("Plugin starting! Lets hope it works! :)");
		new File(pluginDir).mkdir();
		try{
			File cmd = new File(pluginDir + "commands.txt");
			if (cmd.createNewFile()){
				System.out.println("The file commands.txt is created!");
			}else{
				System.out.println("coommands.txt already created.");
			}
			this.cmd = cmd;
			
		}catch (IOException prob){
			prob.printStackTrace();
		}
		try{
			File mes = new File(pluginDir + "message.txt");
			if (mes.createNewFile()){
				System.out.println("The file message.txt is created!");
			}else{
				System.out.println("message.txt already created.");
			}
			this.mes = mes;
		}catch (IOException prob){
			prob.printStackTrace();
		}
	}
	public void onShutdown()
	{
		System.out.println("CommandCosts shutting down!");
	}

	public PluginInfo getPluginInfo() 
	{ 
		PluginInfo info = new PluginInfo();
		info.description = "CommandBlacklist: V0.1 started.";
		return info;
	}
	
	public void onPlayerInput(MC_Player plr, String message, MC_EventInfo ei){
		handle(plr, message, ei);
	}
	
	public void onConsoleInput(String message, MC_EventInfo ei){
		handle(null, message, ei);
	}
	
	public void handle(MC_Player plr, String message, MC_EventInfo ei){
		try {
			List<String> commands = Files.readAllLines(cmd.toPath());
			for(int i = 0; i < commands.size(); i++){
				String cmd = commands.get(i);
				if(!cmd.startsWith("/") && plr !=null) cmd = "/" + cmd;
				String[] array = cmd.split(" ");
				if(array[0].equalsIgnoreCase(message)){
					ei.isCancelled = true;
					String mes = new String(Files.readAllBytes(this.mes.toPath()));
					String cl = mes.replaceAll("&b,", ChatColor.AQUA).replaceAll("&0,", ChatColor.BLACK).replaceAll("&9,", ChatColor.BLUE).replaceAll("&l,", ChatColor.BOLD).replaceAll("&3,", ChatColor.DARK_AQUA).replaceAll("&1,", ChatColor.DARK_BLUE).replaceAll("&8,", ChatColor.DARK_GRAY).replaceAll("&2,", ChatColor.DARK_GREEN).replaceAll("&5,", ChatColor.DARK_PURPLE).replaceAll("&4,", ChatColor.DARK_RED).replaceAll("&6,", ChatColor.GOLD).replaceAll("&7,", ChatColor.GRAY).replaceAll("&a,", ChatColor.GREEN).replaceAll("&o,", ChatColor.ITALIC).replaceAll("&d,", ChatColor.LIGHT_PURPLE).replaceAll("&k,", ChatColor.MAGIC).replaceAll("&c,", ChatColor.RED).replaceAll("&r,", ChatColor.RESET).replaceAll("&m", ChatColor.STRIKETHROUGH).replaceAll("&n,", ChatColor.UNDERLINE).replaceAll("&f,", ChatColor.WHITE).replaceAll("&e,", ChatColor.YELLOW);
					if(plr!=null) plr.sendMessage(cl);
					else System.out.println(cl);
					return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
