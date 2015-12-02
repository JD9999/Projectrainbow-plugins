package Radius;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import PluginReference.*;

public class MyPlugin extends PluginBase{

	public static MC_Server server = null;
	public boolean Togglecheck = false;

	public void onStartup(MC_Server argServer)
	{
		System.out.println("Plugin starting! Lets hope it works! :)");
		server = argServer;
	}
	public void onShutdown()
	{
		System.out.println("Radius shutting down!");
	}

	public PluginInfo getPluginInfo() 
	{ 
		PluginInfo info = new PluginInfo();
		info.description = "An attack prevention tool: version 1.1 started";
		return info;
	}
	public void onPlayerInput(MC_Player plr, String msg, MC_EventInfo ei)
	{
		if(msg.equalsIgnoreCase("/checkon") && plr.hasPermission("radius.check")){
			ei.isCancelled = true;
			Togglecheck = true;
			plr.sendMessage("Toggled mob checking to true");
			halfsecondtimer(plr);
		}
		if(msg.equalsIgnoreCase("/checkoff") && plr.hasPermission("radius.check")){
			ei.isCancelled = true;
			Togglecheck = false;
			plr.sendMessage("Toggled mob checking to false");
		}
		String inputLine = null;
	    String inputField = null;
	    String inputArray[] = new String [20];
	    int inputIndex = 0;
	    int charIndex = 0;	
	    inputIndex = 0;
	    inputLine = msg.trim();		
	    // Parse the input string and put each "word" in each element in inputArray[] starting at subscript 1

	    while ((inputLine.length() > 0) && (inputIndex < 20)) {
	        charIndex = inputLine.indexOf(' ');
	        if (charIndex < 0) { 
	            inputField = inputLine.substring(0, inputLine.length());
	            inputLine = "";
	            }
	        else {
	            inputField = inputLine.substring(0, charIndex);
	            inputLine = inputLine.substring(charIndex, inputLine.length());
	            inputLine = inputLine.trim();
	            }					
	        inputIndex = inputIndex + 1;
	        inputArray[inputIndex] = inputField.trim();
	        }

	    // check each element in inputArray[].  
	    // [1] will contain the primary command.  
	    // inputIndex contains the number of parameters the player entered.
		if(inputArray[1].equalsIgnoreCase("/radius")){
			if(inputArray[2]!=null){
				ei.isCancelled = true;
		List<MC_Entity> mobs = plr.getNearbyEntities(Integer.parseInt(inputArray[2]));
		MC_Entity mob = null;
		int badMobs = 0;
		int mobIndex = 0;
		int plrcount = 0;
		while (mobIndex < mobs.size()) {
		    mob = mobs.get(mobIndex);
		    if (mob.getType() == MC_EntityType.CREEPER) {
		        badMobs = badMobs + 1;
		        }
		    else if (mob.getType() == MC_EntityType.SKELETON) {
		        badMobs = badMobs + 1;
		        }
		    else if (mob.getType() == MC_EntityType.ZOMBIE) {
		        badMobs = badMobs + 1;
		        }
		    else if (mob.getType() == MC_EntityType.CAVE_SPIDER) {
		    	badMobs = badMobs + 1;
		        }
		    else if (mob.getType() == MC_EntityType.ENDERMAN){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.GHAST){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.GIANT){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.LAVA_SLIME){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.PIG_ZOMBIE){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.PLAYER){
		    	plrcount = plrcount + 1;
		    }
		    else if (mob.getType() == MC_EntityType.SILVERFISH){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.SLIME){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.SPIDER){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.WITCH){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.WITHERBOSS){
		    	badMobs = badMobs + 1;
		    }
		    //V0.2 adds the following mobs:
		    else if (mob.getType() == MC_EntityType.ENDERMITE){
		    	badMobs = badMobs +1;
		    }
		    else if (mob.getType() == MC_EntityType.GUARDIAN){
		    	badMobs = badMobs +1;
		    }
		    else if (mob.getType() == MC_EntityType.BLAZE){
		    	badMobs = badMobs +1;
		    }
		    mobIndex = mobIndex + 1; 
		    }     
		plr.sendMessage("There are " + badMobs + " monsters surrounding you! ");
		plr.sendMessage("There are " + plrcount + " players near you (including yourself)!");
			}else{
				plr.sendMessage("No value specified! /radius (checkhere)");
			}		
	}
		if(inputArray[1].equalsIgnoreCase("/radiusexact")){
			if(inputArray[2]!=null){
				ei.isCancelled = true;
			List<MC_Entity> mobs = plr.getNearbyEntities(15);
			MC_Entity mob = null;
			int creeper = 0;
			int skeleton = 0;
			int zombie = 0;
			int cavespider = 0;
			int enderman = 0;
			int ghast = 0;
			int giant = 0;
			int lavaslime = 0;
			int pigzombie = 0;
			int silverfish = 0;
			int slime = 0;
			int spider = 0;
			int witch = 0;
			int witherboss = 0;
			int endermite = 0;
			int guardian = 0;
			int blaze = 0;
			int mobIndex = 0;
			int plrcount = 0;
			while (mobIndex < mobs.size()) {
			    mob = mobs.get(mobIndex);
			    if (mob.getType() == MC_EntityType.CREEPER) {
			        plr.sendMessage("There are " + (creeper = creeper+1) + " creepers near you!");
			        }
			    else if (mob.getType() == MC_EntityType.SKELETON) {
			    	plr.sendMessage("There are " + (skeleton = skeleton+1) + " skeletons near you!");
			        }
			    else if (mob.getType() == MC_EntityType.ZOMBIE) {
			    	plr.sendMessage("There are " + (zombie = zombie+1) + " zombies near you!");
			        }
			    else if (mob.getType() == MC_EntityType.CAVE_SPIDER) {
			    	plr.sendMessage("There are " + (cavespider = cavespider +1) + " cave spiders near you!");
			        }
			    else if (mob.getType() == MC_EntityType.ENDERMAN){
			    	plr.sendMessage("There are " + (enderman = enderman+1) + " endermen near you!");
			    }
			    else if (mob.getType() == MC_EntityType.GHAST){
			    	plr.sendMessage("There are " + (ghast = ghast+1) + " ghasts near you!");
			    }
			    else if (mob.getType() == MC_EntityType.GIANT){
			    	plr.sendMessage("There are " + (giant = giant+1) + " giants near you!");
			    }
			    else if (mob.getType() == MC_EntityType.LAVA_SLIME){
			    	plr.sendMessage("There are " + (lavaslime = lavaslime+1) + " lava slimes near you!");
			    }
			    else if (mob.getType() == MC_EntityType.PIG_ZOMBIE){
			    	plr.sendMessage("There are " + (pigzombie = pigzombie+1) + " pig zombies near you!");
			    }
			    else if (mob.getType() == MC_EntityType.PLAYER){
			    	plr.sendMessage("There are " + (plrcount = plrcount + 1) + " players near you (including yourself)!");
			    }
			    else if (mob.getType() == MC_EntityType.SILVERFISH){
			    	plr.sendMessage("There are " + (silverfish = silverfish+1) + " silverfish near you!");
			    }
			    else if (mob.getType() == MC_EntityType.SLIME){
			    	plr.sendMessage("There are " + (slime = slime+1) + " slimes near you!");
			    }
			    else if (mob.getType() == MC_EntityType.SPIDER){
			    	plr.sendMessage("There are " + (spider = spider+1) + " spiders near you!");
			    }
			    else if (mob.getType() == MC_EntityType.WITCH){
			    	plr.sendMessage("There are " + (witch = witch+1) + " witches near you!");
			    }
			    else if (mob.getType() == MC_EntityType.WITHERBOSS){
			    	plr.sendMessage("There are " + (witherboss = witherboss+1) + " wither bosses near you!");
			    }
			    //V0.2 adds the following mobs:
			    else if (mob.getType() == MC_EntityType.ENDERMITE){
			    	plr.sendMessage("There are " + (endermite = endermite+1) + " endermites near you!");
			    }
			    else if (mob.getType() == MC_EntityType.GUARDIAN){
			    	plr.sendMessage("There are " + (guardian = guardian+1) + " guardians near you!");
			    }
			    else if (mob.getType() == MC_EntityType.BLAZE){
			    	plr.sendMessage("There are " + (blaze = blaze+1) + " blazes near you!");
			    }
			    mobIndex = mobIndex + 1; 
			}
			}else{
			plr.sendMessage("No value specified! /radiusexact (radius)");
			}	
		}
	}
	public void halfsecondtimer(MC_Player plr) {
			Timer timer = new Timer();
			int timeinterval = 500;
			timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  if(Togglecheck){
					List<MC_Entity> mobs = plr.getNearbyEntities(5);
					MC_Entity mob = null;
					int creeper = 0;
					int skeleton = 0;
					int zombie = 0;
					int cavespider = 0;
					int enderman = 0;
					int ghast = 0;
					int giant = 0;
					int lavaslime = 0;
					int pigzombie = 0;
					int silverfish = 0;
					int slime = 0;
					int spider = 0;
					int witch = 0;
					int witherboss = 0;
					int endermite = 0;
					int guardian = 0;
					int blaze = 0;
					int mobIndex = 0;
					while (mobIndex < mobs.size()) {
					    mob = mobs.get(mobIndex);
					    if (mob.getType() == MC_EntityType.CREEPER) {
					        plr.sendMessage("There are " + (creeper = creeper+1) + " creepers near you!");
					        }
					    else if (mob.getType() == MC_EntityType.SKELETON) {
					    	plr.sendMessage("There are " + (skeleton = skeleton+1) + " skeletons near you!");
					        }
					    else if (mob.getType() == MC_EntityType.ZOMBIE) {
					    	plr.sendMessage("There are " + (zombie = zombie+1) + " zombies near you!");
					        }
					    else if (mob.getType() == MC_EntityType.CAVE_SPIDER) {
					    	plr.sendMessage("There are " + (cavespider = cavespider +1) + " cave spiders near you!");
					        }
					    else if (mob.getType() == MC_EntityType.ENDERMAN){
					    	plr.sendMessage("There are " + (enderman = enderman+1) + " endermen near you!");
					    }
					    else if (mob.getType() == MC_EntityType.GHAST){
					    	plr.sendMessage("There are " + (ghast = ghast+1) + " ghasts near you!");
					    }
					    else if (mob.getType() == MC_EntityType.GIANT){
					    	plr.sendMessage("There are " + (giant = giant+1) + " giants near you!");
					    }
					    else if (mob.getType() == MC_EntityType.LAVA_SLIME){
					    	plr.sendMessage("There are " + (lavaslime = lavaslime+1) + " lava slimes near you!");
					    }
					    else if (mob.getType() == MC_EntityType.PIG_ZOMBIE){
					    	plr.sendMessage("There are " + (pigzombie = pigzombie+1) + " pig zombies near you!");
					    }
					    else if (mob.getType() == MC_EntityType.SILVERFISH){
					    	plr.sendMessage("There are " + (silverfish = silverfish+1) + " silverfish near you!");
					    }
					    else if (mob.getType() == MC_EntityType.SLIME){
					    	plr.sendMessage("There are " + (slime = slime+1) + " slimes near you!");
					    }
					    else if (mob.getType() == MC_EntityType.SPIDER){
					    	plr.sendMessage("There are " + (spider = spider+1) + " spiders near you!");
					    }
					    else if (mob.getType() == MC_EntityType.WITCH){
					    	plr.sendMessage("There are " + (witch = witch+1) + " witches near you!");
					    }
					    else if (mob.getType() == MC_EntityType.WITHERBOSS){
					    	plr.sendMessage("There are " + (witherboss = witherboss+1) + " wither bosses near you!");
					    }
					    //V0.2 adds the following mobs:
					    else if (mob.getType() == MC_EntityType.ENDERMITE){
					    	plr.sendMessage("There are " + (endermite = endermite+1) + " endermites near you!");
					    }
					    else if (mob.getType() == MC_EntityType.GUARDIAN){
					    	plr.sendMessage("There are " + (guardian = guardian+1) + " guardians near you!");
					    }
					    else if (mob.getType() == MC_EntityType.BLAZE){
					    	plr.sendMessage("There are " + (blaze = blaze+1) + " blazes near you!");
					    }
					    mobIndex = mobIndex + 1; 
					}
					}else{
						plr.sendMessage("Cancelling timer");
						timer.cancel();
					}
			  }
	}, timeinterval, timeinterval);
			}
	public void onPlayerJoin(MC_Player plr){
		List<MC_Entity> mobs = plr.getNearbyEntities(15);
		MC_Entity mob = null;
		int badMobs = 0;
		int mobIndex = 0;
		int plrcount = 0;
		while (mobIndex < mobs.size()) {
		    mob = mobs.get(mobIndex);
		    if (mob.getType() == MC_EntityType.CREEPER) {
		        badMobs = badMobs + 1;
		        }
		    else if (mob.getType() == MC_EntityType.SKELETON) {
		        badMobs = badMobs + 1;
		        }
		    else if (mob.getType() == MC_EntityType.ZOMBIE) {
		        badMobs = badMobs + 1;
		        }
		    else if (mob.getType() == MC_EntityType.CAVE_SPIDER) {
		    	badMobs = badMobs + 1;
		        }
		    else if (mob.getType() == MC_EntityType.ENDERMAN){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.GHAST){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.GIANT){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.LAVA_SLIME){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.PIG_ZOMBIE){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.PLAYER){
		    	plrcount = plrcount + 1;
		    }
		    else if (mob.getType() == MC_EntityType.SILVERFISH){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.SLIME){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.SPIDER){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.WITCH){
		    	badMobs = badMobs + 1;
		    }
		    else if (mob.getType() == MC_EntityType.WITHERBOSS){
		    	badMobs = badMobs + 1;
		    }
		    //V0.2 adds the following mobs:
		    else if (mob.getType() == MC_EntityType.ENDERMITE){
		    	badMobs = badMobs +1;
		    }
		    else if (mob.getType() == MC_EntityType.GUARDIAN){
		    	badMobs = badMobs +1;
		    }
		    else if (mob.getType() == MC_EntityType.BLAZE){
		    	badMobs = badMobs +1;
		    }
		    mobIndex = mobIndex + 1; 
		    }     
		plr.sendMessage("There are " + badMobs + " monsters surrounding you! ");
		plr.sendMessage("There are " + plrcount + " players near you (including yourself)!");
	}
	public void onPlayerDeath(MC_Player plrVictim, MC_Player plrKiller, MC_DamageType dmgType, String deathMsg) 
	{
		plrVictim.sendMessage("Radius checks your suroundings for mobs. /radius15");
	}
}
