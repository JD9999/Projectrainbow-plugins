package Clock;

import java.util.Timer;
import java.util.TimerTask;

import PluginReference.MC_Player;

public class Stopwatch{
	public Stopwatch() {
	}
	public static boolean millisecond = false;
	public static boolean stoptimer = false;
	public int elapsedseconds = 0;
	public int elapsedmilliseconds = 0;	
	public TimerTask startstopwatch(){
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				if(stoptimer){
					System.out.println("The stopwatch finished at: " + elapsedmilliseconds + "milliseconds");
					timer.cancel();
				}
			}	
		};
		TimerTask task2 = new TimerTask(){
			@Override
			public void run() {
				if(stoptimer){
					System.out.println("The stopwatch finished at: " + elapsedseconds + "seconds");
					timer.cancel();
				}
			}
		};
		if(millisecond){
		timer.scheduleAtFixedRate(task, 1, 1);
		MyPlugin.task = task;
		return task;
		}else{
		timer.scheduleAtFixedRate(task2, 1000, 1000);
		MyPlugin.task = task2;
		return task2;
		}	
	}
	public TimerTask startstopwatchplr(MC_Player plr){
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				if(stoptimer){
					System.out.println("The stopwatch finished at: " + elapsedmilliseconds + "milliseconds");
					timer.cancel();
				}
			}	
		};
		TimerTask task2 = new TimerTask(){
			@Override
			public void run() {
				if(stoptimer){
					plr.sendMessage("The stopwatch finished at: " + elapsedseconds + "seconds");
					timer.cancel();
				}
			}
		};
		if(millisecond){
		timer.scheduleAtFixedRate(task, 1, 1);
		MyPlugin.task = task;
		return task;
		}else{
		timer.scheduleAtFixedRate(task2, 1000, 1000);
		MyPlugin.task = task2;
		return task2;
		}	
	}
	public void stopstopwatch(){
		System.out.println("Stopping timer!");
		stoptimer = true;
	}
	public void stopstopwatchplr(MC_Player plr){
		plr.sendMessage("Stopping timer!");
		stoptimer = true;
	}
}
