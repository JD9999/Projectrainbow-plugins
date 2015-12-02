package Clock;

import java.util.Timer;
import java.util.TimerTask;

import PluginReference.*;

public class Timerclass {
	
	public static MC_Server server = null;
	/**
	 * Called when the timerminute command is entered from the server
	 */
	public static void minutetimer() {
		Timer timer = new Timer();
		int timeinterval = 1*60 * 1000;
		timer.schedule(new TimerTask() {
		  @Override
		  public void run() {
System.out.println("your timer is up!"); }
		}, timeinterval);
	}
	/**
	 * Called when the timerminuterepeating command is entered from the server
	 */
	public static void repeatingminutetimer() {
		Timer timer = new Timer();
		int timeinterval = 1*60 * 1000;
		timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
System.out.println("your timer is up!"); }
		}, timeinterval, timeinterval);
	}
	/**
	 * Called when the timerhalfhour command is entered from the server 
	 */
	public static void halfhourtimer(){
		Timer timer = new Timer();
		int timeinterval = 30*60 * 1000;
		timer.schedule(new TimerTask() {
		  @Override
		  public void run() {
System.out.println("your timer is up!"); }
		}, timeinterval);
	}
	/**
	 * Called when the timerhalfhourrepeating command is entered from the server
	 */
	public static void repeatinghalfhourtimer(){
		Timer timer = new Timer();
		int timeinterval = 30*60 * 1000;
		timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
System.out.println("your timer is up!"); }
		}, timeinterval, timeinterval);
	}
	/**
	 * Called when the timerhour command is entered from the server
	 */
	public static void hourtimer(){
		Timer timer = new Timer();
		int timeinterval = 1*60*60 * 1000;
		timer.schedule(new TimerTask() {
		  @Override
		  public void run() {
System.out.println("your timer is up!"); }
		}, timeinterval);
	}
	/**
	 * Called when the timerhourrepeating command is entered from the server
	 */
	public static void repeatinghourtimer(){
		Timer timer = new Timer();
		int timeinterval = 1*60*60 * 1000;
		timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
System.out.println("your timer is up!"); }
		}, timeinterval, timeinterval);
	}
	/**
	 * Called when the /timerhalfhour command is entered by a player
	 * 
	 * @param plr MC_Player
	 */
	public static void halfhourtimer(MC_Player plr) {
		Timer timer = new Timer();
		int timeinterval = 30*60 * 1000;
		timer.schedule(new TimerTask() {
		  @Override
		  public void run() {
			  plr.sendMessage("Your timer is up!");
		  }
		}, timeinterval);
}
	/**
	 * Called when the /timerhalfhourrepeating command is entered by a player
	 * 
	 * @param plr MC_Player
	 */
	public static void repeatinghalfhourtimer(MC_Player plr) {
		Timer timer = new Timer();
		int timeinterval = 30*60 * 1000;
		timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
			  plr.sendMessage("Your timer is up!");
		  }
		}, timeinterval, timeinterval);
}
	/**
	 * Called when the /timerhour command is entered by a player
	 * 
	 * @param plr
	 */
	public static void hourtimer(MC_Player plr) {
		Timer timer = new Timer();
		int timeinterval = 1*60*60 * 1000;
		timer.schedule(new TimerTask() {
		  @Override
		  public void run() {
	plr.sendMessage("Your timer is up!");
		  }
}, timeinterval);
		}
	/**
	 * Called when the /timerhourrepeating command is entered by a player
	 * 
	 * @param plr
	 */
	public static void repeatinghourtimer(MC_Player plr) {
		Timer timer = new Timer();
		int timeinterval = 1*60*60 * 1000;
		timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
	plr.sendMessage("Your timer is up!");
		  }
}, timeinterval, timeinterval);
		}
	/**
	 * Called when the /timerminute command is entered by a player
	 * 
	 * @param plr
	 */
	public static void minutetimer(MC_Player plr) {
		Timer timer = new Timer();
		int timeinterval = 1*60 * 1000;
		timer.schedule(new TimerTask() {
		  @Override
		  public void run() {
plr.sendMessage("Your timer is up!"); }
		}, timeinterval);
		}
	/**
	 * Called when the /timerminuterepeating command is entered by a player
	 * 
	 * @param plr
	 */
	public static void repeatingminutetimer(MC_Player plr) {
		Timer timer = new Timer();
		int timeinterval = 1*60 * 1000;
		timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
plr.sendMessage("Your timer is up!"); }
		}, timeinterval, timeinterval);
		}
}
