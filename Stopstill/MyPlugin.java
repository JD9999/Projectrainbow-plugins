package Stopstill;

import PluginReference.*;

public class MyPlugin extends PluginBase{
	public static MC_Server server = null;

	public void onStartup(MC_Server argServer)
	{
		System.out.println("Plugin starting! Lets hope it works! :)");
		server = argServer;
	}
	public void onShutdown()
	{
		System.out.println("Stopstill shutting down!");
	}

	public PluginInfo getPluginInfo() 
	{ 
		PluginInfo info = new PluginInfo();
		info.description = "Stops players from moving: version 0.4 started";
		return info;
	}
	public static boolean ToggleMovement = true;
	public static boolean Togglemessage = true;
	public void onAttemptPlayerMove(MC_Player plr, MC_Location locFrom, MC_Location locTo, MC_EventInfo ei)  
	{
		if(ToggleMovement)
		{
			ei.isCancelled = true;
			plr.setFlying(false);
			if(Togglemessage) {
				plr.sendMessage("Stopstill preventing movement");
			}
		}else{
			ei.isCancelled = false;
		}
	}
	public void onPlayerInput(MC_Player plr, String msg, MC_EventInfo ei)
	{
		if(ei.isCancelled) return;

		if(msg.equalsIgnoreCase("/freeze"))
		{
			ei.isCancelled = true;
			ToggleMovement = true;
			plr.sendMessage(ChatColor.AQUA + "Stopstill toggled");			
			return;
		}
		if(msg.equalsIgnoreCase("/unfreeze"))
		{
			ei.isCancelled = true;
			ToggleMovement = false;
			plr.sendMessage(ChatColor.AQUA + "GO! BE FREEEEEE!");
}
		if(msg.equalsIgnoreCase("/freezemessageon")){
			ei.isCancelled = true;
		plr.sendMessage("Toggled 'Stopstill toggled' message on!");
		Togglemessage = true;
		}
		
		if(msg.equalsIgnoreCase("/freezemessageoff")){
			ei.isCancelled = true;
			plr.sendMessage("Toggle 'Stopstill toggled' message off!");
			Togglemessage = false;
		}
}
	public void onConsoleInput(String cmd, MC_EventInfo ei){
		if(ei.isCancelled) return;
		
		if(cmd.equalsIgnoreCase("freeze")){
			ei.isCancelled = true;
			ToggleMovement = true;
			server.broadcastMessage(ChatColor.AQUA + "Stopstill toggled");
		}
		if(cmd.equalsIgnoreCase("unfreeze")){
			ei.isCancelled = true;
			ToggleMovement = false;
			server.broadcastMessage(ChatColor.AQUA + "GO! BE FREEEEEE!");
		}
		if(cmd.equalsIgnoreCase("freezemessageon")){
			System.out.println("Toggled 'Stopstill toggled' message on!");
			Togglemessage = true;
		}
			
		if(cmd.equalsIgnoreCase("freezemessageoff")){
			System.out.println("Toggle 'Stopstill toggled' message off!");
			Togglemessage = false;
		}
	}
}
