package CropProtect;

import PluginReference.*;

public class MyPlugin extends PluginBase{
	public static MC_Server server = null;
	public static boolean ToggleProtect = true;
	
	
	public void onStartup(MC_Server argServer)
	{
		System.out.println("Plugin starting! Lets hope it works! :)");
		server = argServer;
	}
	public void onShutdown()
	{
		System.out.println("CropProtect shutting down!");
	}

	public PluginInfo getPluginInfo() 
	{ 
		PluginInfo info = new PluginInfo();
		info.description = "Stops you from injuring crops: version 0.2 started";
		return info;
	}
	public void onPlayerJoin(MC_Player plr) 
	{
		plr.sendMessage(ChatColor.RED + "Your crops are now protected");
				}
	public void onPlayerInput(MC_Player plr, String msg, MC_EventInfo ei, MC_Server argserver){

		if(msg.equalsIgnoreCase("/disscrop")){
			ToggleProtect = true;
			}
		if(msg.equalsIgnoreCase("/protectcrop")){
			ToggleProtect = false;
			}	
	}
	public void onAttemptCropTrample(MC_Entity ent, MC_Location loc, MC_EventInfo ei){
		if(ei.isCancelled) return;
		if(ToggleProtect){
			ei.isCancelled = true;
			server.broadcastMessage("No squishing crops!");
		}
	}
}



