package Clock;

import PluginReference.*;

import java.util.Date;
import java.util.TimerTask;

public class MyPlugin extends PluginBase{

	public static MC_Server server = null;
	public static TimerTask task = null;

	public void onStartup(MC_Server argServer)
	{
		System.out.println("Plugin starting! Lets hope it works! :)");
		server = argServer;
	}
	public void onShutdown()
	{
		System.out.println("Clock shutting down!");
	}

	public PluginInfo getPluginInfo() 
	{ 
		PluginInfo info = new PluginInfo();
		info.description = "A clock: version 1.2 started";
		return info;
	}
	public void onPlayerInput(MC_Player plr, String msg, MC_EventInfo ei)
	{
		if(msg.equalsIgnoreCase("/clock"))
		{
			ei.isCancelled = true;
			plr.sendMessage(ChatColor.GOLD + "Welcome to Clock. The current time is:");
			Date date = new Date();
			plr.sendMessage(date.toString());			
			return;
			}
	    if(msg.equalsIgnoreCase("/timer")) { 
	        ei.isCancelled = true;  
	            plr.sendMessage(ChatColor.GOLD + "'/timerminute' puts the timer on for a minute");
	            plr.sendMessage(ChatColor.GOLD + "'/timerhour' puts the timer on for an hour");
	            plr.sendMessage(ChatColor.GOLD + "'/timerhalfhour' puts the timer on for half an hour");
	            plr.sendMessage(ChatColor.GOLD + "You can put repeating on the end of the command to make it a repeating timer e.g. /timerminuterepeating");
	            }
	if (msg.equalsIgnoreCase("/timerminute")) {  
		ei.isCancelled = true;
		plr.sendMessage(ChatColor.GOLD + "Your minute timer has started.");
        	Timerclass.minutetimer(plr);
        }
	if (msg.equalsIgnoreCase("/timerminuterepeating")) {  
		ei.isCancelled = true;
		plr.sendMessage(ChatColor.GOLD + "Your repeating minute timer has started.");
        	Timerclass.repeatingminutetimer(plr);
        }
	if (msg.equalsIgnoreCase("/timerhalfhour")){
		ei.isCancelled = true;
		plr.sendMessage(ChatColor.GOLD + "Your half hour timer has started.");
        	Timerclass.halfhourtimer(plr);
	}
	if (msg.equalsIgnoreCase("/timerhalfhourrepeating")){
		ei.isCancelled = true;
		plr.sendMessage(ChatColor.GOLD + "Your repeating half hour timer has started.");
        	Timerclass.repeatinghalfhourtimer(plr);
	}
    if (msg.equalsIgnoreCase("/timerhour")) {
    	plr.sendMessage(ChatColor.GOLD + "Your hour timer has started.");
    	ei.isCancelled = true;
        	Timerclass.hourtimer(plr);
        }
    if (msg.equalsIgnoreCase("/timerhourrepeating")) {
    	plr.sendMessage(ChatColor.GOLD + "Your repeating hour timer has started.");
    	ei.isCancelled = true;
        	Timerclass.repeatinghourtimer(plr);
        }
    if(msg.equalsIgnoreCase("/stopwatchstart")){
    	ei.isCancelled = true;
    	plr.sendMessage(ChatColor.GOLD + "Starting stop watch");
    	Stopwatch sw = new Stopwatch();
    	Stopwatch.millisecond = false;
    	task = sw.startstopwatchplr(plr);
    }
    if(msg.equalsIgnoreCase("/stopwatchstartms")){
    	ei.isCancelled = true;
    	plr.sendMessage(ChatColor.GOLD + "Starting millisecond stop watch");
    	Stopwatch sw = new Stopwatch();
    	Stopwatch.millisecond = true;
    	task = sw.startstopwatchplr(plr);
    }
    if(msg.equalsIgnoreCase("/stopwatchstop")){
    	ei.isCancelled = true;
    	plr.sendMessage("Stopping stop watch!");
       	Stopwatch sw = new Stopwatch();
        sw.stopstopwatchplr(plr);
    }
	}
			
			public void onPlayerJoin(MC_Player plr, String msg) 
			{
				plr.sendMessage(ChatColor.GOLD + "The current time is:");
				Date date = new Date();
				System.out.println(date.toString());
				plr.sendMessage(date.toString());
				   }
			public void onConsoleInput(String cmd, MC_EventInfo ei) 
			{
				if(ei.isCancelled) return;
				if(cmd.equalsIgnoreCase("clock")){
					ei.isCancelled = true;
					Date date = new Date();
					System.out.println(date.toString());
					return;
				}
				if(cmd.equalsIgnoreCase("timerminute")){
					ei.isCancelled = true;
					System.out.println("Your minute timer has started.");
					Timerclass.minutetimer();
				}
				if(cmd.equalsIgnoreCase("timerhalfhour")){
					ei.isCancelled = true;
					System.out.println("Your half hour timer has started.");
					Timerclass.halfhourtimer();
				}
				if(cmd.equalsIgnoreCase("timerhour")){
					ei.isCancelled = true;
					System.out.println("Your hour timer has started.");
					Timerclass.hourtimer();
				}
				if(cmd.equalsIgnoreCase("timerminuterepeating")){
					ei.isCancelled = true;
					System.out.println("Your repeating minute timer has started.");
					Timerclass.repeatingminutetimer();
				}
				if(cmd.equalsIgnoreCase("timerhalfhourrepeating")){
					ei.isCancelled = true;
					System.out.println("Your repeating half hour timer has started.");
					Timerclass.repeatinghalfhourtimer();
				}
				if(cmd.equalsIgnoreCase("timerhourrepeating")){
					ei.isCancelled = true;
					System.out.println("Your repeating hour timer has started.");
					Timerclass.repeatinghourtimer();
				}
				if(cmd.equalsIgnoreCase("stopwatchstart")){
					ei.isCancelled = true;
			    	System.out.println("Starting stop watch");
			    	Stopwatch sw = new Stopwatch();
			    	Stopwatch.millisecond = false;
			    	task = sw.startstopwatch();
			    }
				if(cmd.equalsIgnoreCase("stopwatchstopms")){
					ei.isCancelled = true;
					System.out.println("Starting millisecond stop watch");
					Stopwatch sw = new Stopwatch();
					Stopwatch.millisecond = true;
					task = sw.startstopwatch();
				}
			    if(cmd.equalsIgnoreCase("stopwatchstop")){
			    	ei.isCancelled = true;
			    	System.out.println("Stopping stopwatch!");
			       	Stopwatch sw = new Stopwatch();
			        sw.stopstopwatch();
			    }
			        }
			
			        }
