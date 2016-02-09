package Clock;

import java.util.Timer;
import java.util.TimerTask;

import PluginReference.MC_Player;
import PluginReference.ChatColor;

public class Stopwatch{
	public Stopwatch() {
	}
	public static boolean millisecond = false;
	public int elapsedseconds = 0;
	public int elapsedmilliseconds = 0;
	public TimerTask task;
	
	public TimerTask startstopwatch(){
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				elapsedmilliseconds++;
			}	
		};
		TimerTask task2 = new TimerTask(){
			@Override
			public void run() {
				elapsedseconds++;
			}
		};
		if(millisecond){
		timer.scheduleAtFixedRate(task, 1, 1);
		this.task = task;
		return task;
		}else{
		timer.scheduleAtFixedRate(task2, 1000, 1000);
		this.task = task2;
		return task2;
		}	
	}
	public TimerTask startstopwatchplr(MC_Player plr){
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				elapsedmilliseconds++;
			}	
		};
		TimerTask task2 = new TimerTask(){
			@Override
			public void run() {
				elapsedseconds++;
			}
		};
		if(millisecond){
		timer.scheduleAtFixedRate(task, 1, 1);
		this.task = task;
		return task;
		}else{
		timer.scheduleAtFixedRate(task2, 1000, 1000);
		this.task = task2;
		return task2;
		}	
	}
	public void stopstopwatch(){
		System.out.println("Stopping timer!");
		if(millisecond){
			System.out.println("Stopwatch finished at: " + elapsedmilliseconds + " milliseconds");
		}else{
			System.out.println("Stopwatch finished at: " + elapsedseconds + " seconds");
		}
		task.cancel();
	}
	public void stopstopwatchplr(MC_Player plr){
		plr.sendMessage("Stopping timer!");
		if(millisecond){
			plr.sendMessage(ChatColor.GOLD + "Stopwatch finished at: " + elapsedmilliseconds + " milliseconds");
		}else{
			plr.sendMessage(ChatColor.GOLD + "Stopwatch finished at: " + elapsedseconds + " seconds");
		}
		task.cancel();
	}
}
