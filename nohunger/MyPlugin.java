package nohunger;

import PluginReference.*;

	public class MyPlugin extends PluginBase{

		public static boolean Togglenohunger = true;

		public void onStartup(MC_Server argServer)
		{
			System.out.println("Plugin starting! Lets hope it works! :)");
		}
		public void onShutdown()
		{
			System.out.println("nohunger shutting down!");
		}

		public PluginInfo getPluginInfo() 
		{ 
			PluginInfo info = new PluginInfo();
			info.description = "No hunger: version 0.1 started";
			return info;
		}
		public void onAttemptPlayerMove(MC_Player plr, MC_Location locFrom, MC_Location locTo, MC_EventInfo ei){
			if(Togglenohunger){
			plr.setFoodLevel(20);	
			}
}
		public void onPlayerInput(MC_Player plr, String mes, MC_EventInfo ei){
			if(ei.isCancelled) return;
			
			if(mes.equalsIgnoreCase("/nohungeron")){
				ei.isCancelled = true;
				plr.sendMessage("Toggling nohunger on!");
				Togglenohunger = true;
			}
			if(mes.equalsIgnoreCase("/nohungeroff")){
				ei.isCancelled = true;
				plr.sendMessage("Toggling nohunger off!");
				Togglenohunger = false;
			}
		}
		public void onConsoleInput(String mes, MC_EventInfo ei){
			if(mes.equalsIgnoreCase("nohungeron")){
				ei.isCancelled = true;
				System.out.println("Toggling nohunger on!");
				Togglenohunger = true;
			}
			if(mes.equalsIgnoreCase("nohungeroff")){
				ei.isCancelled = true;
				System.out.println("Toggling nohunger off!");
				Togglenohunger = false;
			}
		}
}
