package Seasons;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import PluginReference.*;

public class MyPlugin extends PluginBase{
public static MC_Server server = null;
String pluginDir = "plugins_mod" + File.separatorChar + "Seasons";
boolean keeprunning = true;
static boolean Togglebadoff = false;
static boolean Togglegoodoff = false;
public void onStartup(MC_Server argServer)
{
	System.out.println("Seasons starting! Lets hope it works! :)");
	System.out.println("Thank you kvgeorge1 for your help with this plugin!");
	server = argServer;
	
	try{
		new File(pluginDir).mkdir();
		File basewriter = new File(pluginDir + File.separatorChar + "seasonbase.txt");
		if (basewriter.createNewFile()){
			System.out.println("The file seasonbase.txt is created!");
			BufferedWriter writer = null;
			 try {
		            writer = new BufferedWriter(new FileWriter(basewriter));
		            writer.write("1");
		            System.out.println("Season edited!");
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            try {
		                writer.close();
		            } catch (Exception e){
		            	e.printStackTrace();
			}
		        }
		}else{
			System.out.println("Cannot create seasonbase file, probably aready created.");
		}
		}
			catch (IOException prob){
				prob.printStackTrace();
			}
	try{
	File timerwriter = new File(pluginDir + File.separatorChar + "seasontimer.txt");
	if(timerwriter.createNewFile()){
		System.out.println("File seasontimer.txt has been created!");
		BufferedWriter writer = null;
		 try {
	            writer = new BufferedWriter(new FileWriter(timerwriter));
	            writer.write("2h");
	            System.out.println("Timer file set!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                writer.close();
	            } catch (Exception e){
	            	e.printStackTrace();
		}
	}
	}else{
		System.out.println("cannot create seasontimer file, probably already created.");
	}
		}catch (IOException e){
		e.printStackTrace();
		}
}
public void onShutdown()
{
	System.out.println("Seasons shutting down!");
}

public PluginInfo getPluginInfo() 
{ 
	PluginInfo info = new PluginInfo();
	info.description = "Minecraft has seasons: version 1.4b started";
	return info;
}
public void onPlayerInput(MC_Player plr, String msg, MC_EventInfo ei)
{
	if(ei.isCancelled) return;
	
	if(msg.equalsIgnoreCase("/goodon") && plr.isOp()){
		ei.isCancelled = true;
		plr.sendMessage("Toggling good mobs on");
		Togglegoodoff = false;
	}
	
	if(msg.equalsIgnoreCase("/goodoff") && plr.isOp()){
		ei.isCancelled = true;
		plr.sendMessage("Toggling good mobs of");
		Togglegoodoff = true;
	}
	
	if(msg.equalsIgnoreCase("/badon") && plr.isOp()){
		ei.isCancelled = true;
		plr.sendMessage("Toggling bad mobs on");
		Togglebadoff = false;
	}
	
	if(msg.equalsIgnoreCase("/badoff") && plr.isOp()){
		ei.isCancelled = true;
		plr.sendMessage("Toggling bad mobs off");
		Togglebadoff = true;
	}
	
	if(msg.equalsIgnoreCase("/summer"))
	{
		ei.isCancelled = true;
		plr.sendMessage("It is now summer!");
		plr.executeCommand("/weather clear");
		plr.sendMessage("Only daytime mobs will spawn!");
		 BufferedWriter writer = null;
	        try {
	            File logFile = new File(pluginDir + File.separatorChar + "seasonbase.txt");{
	            	System.out.println("Your season is being changed!");
	            }
	            writer = new BufferedWriter(new FileWriter(logFile));
	            writer.write("1");
	            plr.sendMessage("Season edited!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                writer.close();
	            } catch (Exception e){
		}
	    }
	        Togglegoodoff = false;
	        Togglebadoff = true;
		return;
	}
	if(msg.equalsIgnoreCase("/autumn"))
	{
		ei.isCancelled = true;
		plr.sendMessage("It is now autumn!");
		plr.executeCommand("/weather thunder");
		plr.sendMessage("Any mobs will spawn!");
		 BufferedWriter writer = null;
	        try {
	            File logFile = new File(pluginDir + File.separatorChar + "seasonbase.txt");{
	            	System.out.println("Your season is being changed!");
	            }
	            writer = new BufferedWriter(new FileWriter(logFile));
	            writer.write("2");
	            plr.sendMessage("Season edited!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                writer.close();
	            } catch (Exception e){
	        }
	}
	        Togglegoodoff = false;
	        Togglebadoff = false;
	       return;
	}
	if(msg.equalsIgnoreCase("/winter"))
	{
		ei.isCancelled = true;
		plr.sendMessage("It is now winter!");
		plr.executeCommand("/weather rain");
		plr.sendMessage("Only night mobs will spawn!");
		 BufferedWriter writer = null;
	        try {
	            File logFile = new File(pluginDir + File.separatorChar + "seasonbase.txt");
	            	System.out.println("Your season is being changed!");
	            writer = new BufferedWriter(new FileWriter(logFile));
	            writer.write("3");
	            plr.sendMessage("Season edited!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                writer.close();
	            } catch (Exception e){
		}
	        }
	        Togglegoodoff = true;
	        Togglebadoff = false;
	        return;
	}
	if(msg.equalsIgnoreCase("/spring"))
	{
		ei.isCancelled = true;
		plr.sendMessage("It is now spring!");
		plr.sendMessage("Any mobs can spawn!");
		 BufferedWriter writer = null;
	        try {
	            File logFile = new File(pluginDir + File.separatorChar + "seasonbase.txt");{
	            	System.out.println("Your season is being changed!");
	            }
	            writer = new BufferedWriter(new FileWriter(logFile));
	            writer.write("0");
	            plr.sendMessage("Season edited!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                writer.close();
	            } catch (Exception e){
		}
	}
	        Togglebadoff = false;
	        Togglegoodoff = false;
	        return;
}
	if(msg.equalsIgnoreCase("/season"))
	{
		int seasonltr;
		ei.isCancelled = true;
		BufferedReader reader = null;
		String season;
		try{
			reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "seasonbase.txt"));
			while ((season = reader.readLine()) != null) {
				seasonltr = Integer.parseInt(season);
					switch(seasonltr){
					case SUMMER:
						plr.sendMessage("It is summer!");
						break;
						
					case AUTUMN:
						plr.sendMessage("It is autumn!");
						break;
						
					case WINTER:
						plr.sendMessage("It is winter!");
						break;
						
					case SPRING:
						plr.sendMessage("It is spring!");
						break;
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return;
}
	if(msg.equalsIgnoreCase("/seasonsstart")){
		ei.isCancelled = true;
		keeprunning = true;
		starttimer();
	}
	if(msg.equalsIgnoreCase("/seasonsstop")){
		server.broadcastMessage("Stopping timer!");
		ei.isCancelled = true;
		keeprunning = false;
	}
}

public void switchseason() {
	if(keeprunning){
	BufferedReader reader = null;
    try{
      reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "seasonbase.txt"));
      while ((line = reader.readLine()) != null) {
        seasonno = Integer.parseInt(line);
      }
    } catch (NumberFormatException nfe) {
      System.out.println("Unable to convert value from file! " + line);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null)
          reader.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      BufferedWriter writer = null;
      switch(seasonno) {
      case SPRING:
  		server.broadcastMessage("It is now summer!");
		server.executeCommand("/weather clear");
		server.broadcastMessage("Only good mobs will spawn!");
	        try {
	            File logFile = new File(pluginDir + File.separatorChar + "seasonbase.txt");{
	            	System.out.println("Your season is being changed!");
	            }
	            writer = new BufferedWriter(new FileWriter(logFile));
	            writer.write("1");
	            System.out.println("Season edited!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                writer.close();
	            } catch (Exception e){
		}
	    }
	        Togglegoodoff = false;
	        Togglebadoff = true;
        break;
      case SUMMER:
    	  server.broadcastMessage("It is now autumn!");
  		server.executeCommand("/weather thunder");
  		server.broadcastMessage("Any mobs will spawn!");
  	        try {
  	            File logFile = new File(pluginDir + File.separatorChar + "seasonbase.txt");{
  	            	System.out.println("Your season is being changed!");
  	            }
  	            writer = new BufferedWriter(new FileWriter(logFile));
  	            writer.write("2");
  	            System.out.println("Season edited!");
  	        } catch (Exception e) {
  	            e.printStackTrace();
  	        } finally {
  	            try {
  	                writer.close();
  	            } catch (Exception e){
  	        }
  	}
  	        Togglebadoff = false;
  	        Togglegoodoff = false;
        break;
      case AUTUMN:
    	  server.broadcastMessage("It is now winter!");
  		server.executeCommand("/weather rain");
  		server.broadcastMessage("Only evil mobs will spawn!");
  	        try {
  	            File logFile = new File(pluginDir + File.separatorChar + "seasonbase.txt");{
  	            	System.out.println("Your season is being changed!");
  	            }
  	            writer = new BufferedWriter(new FileWriter(logFile));
  	            writer.write("3");
  	            System.out.println("Season edited!");
  	        } catch (Exception e) {
  	            e.printStackTrace();
  	        } finally {
  	            try {
  	                writer.close();
  	            } catch (Exception e){
  		}
  	        }
  	        Togglegoodoff = true;
  	        Togglebadoff = false;
        break;
      case WINTER:
  		server.broadcastMessage("It is now spring!");
		server.broadcastMessage("Any mobs can spawn!");
	        try {
	            File logFile = new File(pluginDir + File.separatorChar + "seasonbase.txt");
	            	System.out.println("Your season is being changed!");
	            writer = new BufferedWriter(new FileWriter(logFile));
	            writer.write("0");
	            System.out.println("Season edited!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                writer.close();
	            } catch (Exception e){
		}
	}
	        Togglegoodoff = false;
	        Togglebadoff = false;
        break;
      default:
        System.out.println("Unknown Season! " + seasonno);
    }
    }
	}					
						}
public void onConsoleInput(String cmd, MC_EventInfo ei) 
{
if(ei.isCancelled) return;
	if(cmd.equalsIgnoreCase("season"))
	{
		ei.isCancelled = true;
		String season;
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "seasonbase.txt"));
			while ((season = reader.readLine()) != null) {
				seasonno = Integer.parseInt(season);
				switch(seasonno){
				case SUMMER:
					System.out.println("It is summer!");
					break;
					
				case AUTUMN:
					System.out.println("It is autumn");
					break;
					
				case WINTER:
					System.out.println("It is winter!");
					break;
					
				case SPRING:
					System.out.println("It is spring!");
					break;
					
					default:
						System.out.println("Unknown season! Switching to summer");
						BufferedWriter writer = null;
						try {
				            File logFile = new File(pluginDir + File.separatorChar + "seasonbase.txt");
				            writer = new BufferedWriter(new FileWriter(logFile));
				            writer.write("1");
				            System.out.println("Season edited!");
				        } catch (Exception e) {
				            e.printStackTrace();
				        } finally {
				            try {
				                writer.close();
				            } catch (Exception e){
					}
				}
				}
				}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return;
	}
	if(cmd.equalsIgnoreCase("seasonsstart")){
		ei.isCancelled = true;
		keeprunning = true;
		starttimer();
	}
	if(cmd.equalsIgnoreCase("seasonsstop")){
		server.broadcastMessage("Stopping timer!");
		ei.isCancelled = true;
		keeprunning = false;
	}
}
public void onAttemptEntitySpawn(MC_Entity ent, MC_EventInfo ei) {
	if(ent.getType() == MC_EntityType.BAT){
		ei.isCancelled = false;
	}
	else if(ent.getType() == MC_EntityType.BLAZE){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if(ent.getType() == MC_EntityType.CAVE_SPIDER){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
		}
	else if(ent.getType() == MC_EntityType.CHICKEN){
		if(Togglegoodoff){
			ei.isCancelled = true;
		}
	}
	else if(ent.getType() == MC_EntityType.COW){
		if(Togglegoodoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.CREEPER){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.ENDERDRAGON){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.ENDERMAN){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.ENDERMITE){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.GHAST){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.GIANT){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.GUARDIAN){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.HORSE){
		if(Togglegoodoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.LAVA_SLIME){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.MUSHROOM_COW){
		if(Togglegoodoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.OCELOT){
		if(Togglegoodoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.PIG){
		if(Togglegoodoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.PIG_ZOMBIE){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.RABBIT){
		if(Togglegoodoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.SHEEP){
		if(Togglegoodoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.SILVERFISH){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.SKELETON){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.SLIME){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.SNOWMAN){
		if(Togglegoodoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.SPIDER){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.SQUID){
		if(Togglegoodoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.VILLAGER){
		if(Togglegoodoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.VILLAGER_GOLEM){
		if(Togglegoodoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.WITCH){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.WITHERBOSS){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}
	else if(ent.getType() == MC_EntityType.WOLF){
		if(Togglegoodoff){
			ei.isCancelled = true;
		}
	}
	else if (ent.getType() == MC_EntityType.ZOMBIE){
		if(Togglebadoff){
			ei.isCancelled = true;
		}
	}else{
		if(isMob(ent))System.out.println("Unknown mob just spawned! Name: " + ent.getName());
	}
	}

private boolean isMob(MC_Entity ent) {
	if(ent.getType() == MC_EntityType.ARMOR_STAND)return false;
	else if(ent.getType() == MC_EntityType.ARROW)return false;
	else if(ent.getType() == MC_EntityType.BOAT)return false;
	else if(ent.getType() == MC_EntityType.ENDER_CRYSTAL)return false;
	else if(ent.getType() == MC_EntityType.EYE_OF_ENDER_SIGNAL)return false;
	else if(ent.getType() == MC_EntityType.FALLING_SAND)return false;
	else if(ent.getType() == MC_EntityType.FIREBALL)return false;
	else if(ent.getType() == MC_EntityType.FIREWORK)return false;
	else if(ent.getType() == MC_EntityType.FISHING_HOOK)return false;
	else if(ent.getType() == MC_EntityType.HANGING)return false;
	else if(ent.getType() == MC_EntityType.ITEM)return false;
	else if(ent.getType() == MC_EntityType.MINECART)return false;
	else if(ent.getType() == MC_EntityType.PRIMED_TNT)return false;
	else if(ent.getType() == MC_EntityType.SMALL_FIREBALL)return false;
	else if(ent.getType() == MC_EntityType.SNOWBALL)return false;
	else if(ent.getType() == MC_EntityType.THROWN_EGG)return false;
	else if(ent.getType() == MC_EntityType.THROWN_ENDERPEARL)return false;
	else if(ent.getType() == MC_EntityType.THROWN_EXP_BOTTLE)return false;
	else if(ent.getType() == MC_EntityType.THROWN_POTION)return false;
	else if(ent.getType() == MC_EntityType.XP_ORB)return false;
	
	else if(ent.getType() == MC_EntityType.UNSPECIFIED)return true; // Don't know, console will say that
	else return true;
}

int season;
int seasonno;
private static final int SPRING = 0;
private static final int SUMMER = 1;
private static final int AUTUMN = 2;
private static final int WINTER = 3;
String line = null;
String seasonid;
	public void onPlayerJoin(MC_Player plr){
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "seasonbase.txt"));
			while ((seasonid = reader.readLine()) != null) {
				int seasonname = Integer.parseInt(seasonid);
				
				switch(seasonname){
				case SUMMER:
					plr.sendMessage("It is summer!");
					break;
				case AUTUMN:
					plr.sendMessage("It is autumn!");
					break;
				case WINTER:
					plr.sendMessage("It is winter!");
					break;
				case SPRING:
					plr.sendMessage("It is spring!");
					break;
					
					default:
						plr.sendMessage("Unknown season! Switching to summer");
						BufferedWriter writer = null;
						try {
				            File logFile = new File(pluginDir + File.separatorChar + "seasonbase.txt");
				            writer = new BufferedWriter(new FileWriter(logFile));
				            writer.write("1");
				            plr.sendMessage("Season edited!");
				        } catch (Exception e) {
				            e.printStackTrace();
				        } finally {
				            try {
				                writer.close();
				            } catch (Exception e){
					}
				}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
}
	public void onAttemptPlayerChangeDimension(MC_Player plr, int newDimension, MC_EventInfo ei)
	{
		if(ei.isCancelled) return;
		String seasonid2;
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "seasonbase.txt"));
			while ((seasonid2 = reader.readLine()) != null) {
int seasonname = Integer.parseInt(seasonid2);
				
				switch(seasonname){
				case SUMMER:
					plr.sendMessage("It is summer!");
					break;
				case AUTUMN:
					plr.sendMessage("It is autumn!");
					break;
				case WINTER:
					plr.sendMessage("It is winter!");
					break;
				case SPRING:
					plr.sendMessage("It is spring!");
					break;
					
					default:
						plr.sendMessage("Unknown season! Switching to summer");
						BufferedWriter writer = null;
						try {
				            File logFile = new File(pluginDir + File.separatorChar + "seasonbase.txt");
				            writer = new BufferedWriter(new FileWriter(logFile));
				            writer.write("1");
				            plr.sendMessage("Season edited!");
				        } catch (Exception e) {
				            e.printStackTrace();
				        } finally {
				            try {
				                writer.close();
				            } catch (Exception e){
					}
				}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void starttimer(){

		int timeinterval;
		server.broadcastMessage("Starting seasons timer!");
		BufferedReader reader = null;
		String timera;
		try{
			reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "seasontimer.txt"));
			while((timera = reader.readLine())!=null){
				if(timera.endsWith("d")){
					if(timera.equalsIgnoreCase("1d")){
						 timeinterval = 1*24*60*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
								  switchseason();
								  }else{
									  timer.cancel();
								  }
							  }						
							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("2d")){
						 timeinterval = 2*24*60*60*1000;
				    	Timer timer = new Timer();
						timer.scheduleAtFixedRate(new TimerTask() {
						  @Override
						  public void run() {
							  if(keeprunning){
								  switchseason();
								  }else{
									  timer.cancel();
								  }
						  }		
						} , timeinterval, timeinterval);
						return;
				}else if(timera.equalsIgnoreCase("3d")){
					 timeinterval = 3*24*60*60*1000;
				    	Timer timer = new Timer();
						timer.scheduleAtFixedRate(new TimerTask() {
						  @Override
						  public void run() {
							  if(keeprunning){
								  switchseason();
								  }else{
									  timer.cancel();
								  }
						  }				
						} , timeinterval, timeinterval);
						return;
				}else if(timera.equalsIgnoreCase("4d")){
					 timeinterval = 4*24*60*60*1000;
				    	Timer timer = new Timer();
						timer.scheduleAtFixedRate(new TimerTask() {
						  @Override
						  public void run() {
							  if(keeprunning){
								  switchseason();
								  }else{
									  timer.cancel();
								  }
						  }				
						} , timeinterval, timeinterval);
						return;
				}else if(timera.equalsIgnoreCase("5d")){
					 timeinterval = 5*24*60*60*1000;
				    	Timer timer = new Timer();
						timer.scheduleAtFixedRate(new TimerTask() {
						  @Override
						  public void run() {
							  if(keeprunning){
								  switchseason();
								  }else{
									  timer.cancel();
								  }
						  }						
						} , timeinterval, timeinterval);
						return;
				}else if(timera.equalsIgnoreCase("6d")){
					 timeinterval = 6*24*60*60*1000;
				    	Timer timer = new Timer();
						timer.scheduleAtFixedRate(new TimerTask() {
						  @Override
						  public void run() {
							  if(keeprunning){
								  switchseason();
								  }else{
									  timer.cancel();
								  }
						  }					
						} , timeinterval, timeinterval);
						return;
				}else if(timera.equalsIgnoreCase("7d")){
					 timeinterval = 1*24*60*60*1000;
				    	Timer timer = new Timer();
						timer.scheduleAtFixedRate(new TimerTask() {
						  @Override
						  public void run() {
							  if(keeprunning){
								  switchseason();
								  }else{
									  timer.cancel();
								  }
						  }				
						} , timeinterval, timeinterval);
						return;
				}			
			}
				else if(timera.endsWith("h")){
					if(timera.equalsIgnoreCase("1h")){
						timeinterval = 1*60*60*1000;
						Timer timer = new Timer();
						timer.scheduleAtFixedRate(new TimerTask() {
						  @Override
						  public void run() {
							  if(keeprunning){
								  switchseason();
								  }else{
									  timer.cancel();
								  }
						  }
						} , timeinterval, timeinterval);
						return;
					}
					else if(timera.equalsIgnoreCase("2h")){
						timeinterval = 2*60*60*1000;
						Timer timer = new Timer();
						timer.scheduleAtFixedRate(new TimerTask() {
						  @Override
						  public void run() {
							  if(keeprunning){
								  switchseason();
								  }else{
									  timer.cancel();
								  }
						  }
						} , timeinterval, timeinterval);
						return;
					}
					if(timera.equalsIgnoreCase("3h")){
						 timeinterval = 3*60*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("4h")){
						 timeinterval = 4*60*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
				}
				else if(timera.equalsIgnoreCase("5h")){
					 timeinterval = 5*60*60*1000;
				    	Timer timer = new Timer();
						timer.scheduleAtFixedRate(new TimerTask() {
						  @Override
						  public void run() {
							  if(keeprunning){
								  switchseason();
								  }else{
									  timer.cancel();
								  }
						  }

						} , timeinterval, timeinterval);
						return;
				}
				else if (timera.equalsIgnoreCase("6h")){
					timeinterval = 6*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("7h")){
					timeinterval = 7*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("8h")){
					timeinterval = 8*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("9h")){
					timeinterval = 9*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("10h")){
					timeinterval = 10*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("11h")){
					timeinterval = 11*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("12h")){
					timeinterval = 12*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("13h")){
					timeinterval = 13*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("14h")){
					timeinterval = 14*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("15h")){
					timeinterval = 15*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("16h")){
					timeinterval = 16*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("17h")){
					timeinterval = 17*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("18h")){
					timeinterval = 18*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("19h")){
					timeinterval = 19*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("20h")){
					timeinterval = 20*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("21h")){
					timeinterval = 21*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}else if (timera.equalsIgnoreCase("22h")){
					timeinterval = 22*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if (timera.equalsIgnoreCase("23h")){
					timeinterval = 23*60*60*1000;
			    	Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }

					} , timeinterval, timeinterval);
					return;
				}
				else if(timera.endsWith("m")){			
					if(timera.equalsIgnoreCase("1m")){
						 timeinterval = 1*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("2m")){
						 timeinterval = 2*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("3m")){
						 timeinterval = 3*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("4m")){
						 timeinterval = 4*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("5m")){
						 timeinterval = 5*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("6m")){
						 timeinterval = 6*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("7m")){
						 timeinterval = 7*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("8m")){
						 timeinterval = 8*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("9m")){
						 timeinterval = 9*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("10m")){
						 timeinterval = 10*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("11m")){
						 timeinterval = 11*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("12m")){
						 timeinterval = 12*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("13m")){
						 timeinterval = 13*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("14m")){
						 timeinterval = 14*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("15m")){
						 timeinterval = 15*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("16m")){
						 timeinterval = 16*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("17m")){
						 timeinterval = 17*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("18m")){
						 timeinterval = 18*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("19m")){
						 timeinterval = 19*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("20m")){
						 timeinterval = 20*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("21m")){
						 timeinterval = 21*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("22m")){
						 timeinterval = 22*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("23m")){
						 timeinterval = 23*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("24m")){
						 timeinterval = 24*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("25m")){
						 timeinterval = 25*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("26m")){
						 timeinterval = 26*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("27m")){
						 timeinterval = 27*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("28m")){
						 timeinterval = 28*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("29m")){
						 timeinterval = 29*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("30m")){
						 timeinterval = 30*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("31m")){
						 timeinterval = 31*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("32m")){
						 timeinterval = 32*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("33m")){
						 timeinterval = 33*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("34m")){
						 timeinterval = 34*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("35m")){
						 timeinterval = 35*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("36m")){
						 timeinterval = 36*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("37m")){
						 timeinterval = 37*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("38m")){
						 timeinterval = 38*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("39m")){
						 timeinterval = 39*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("40m")){
						 timeinterval = 40*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("41m")){
						 timeinterval = 41*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("42m")){
						 timeinterval = 42*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("43m")){
						 timeinterval = 43*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("44m")){
						 timeinterval = 44*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("45m")){
						 timeinterval = 45*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("46m")){
						 timeinterval = 46*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("47m")){
						 timeinterval = 47*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("48m")){
						 timeinterval = 48*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("49m")){
						 timeinterval = 49*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("50m")){
						 timeinterval = 50*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("51m")){
						 timeinterval = 51*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("52m")){
						 timeinterval = 52*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("53m")){
						 timeinterval = 53*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("54m")){
						 timeinterval = 54*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("55m")){
						 timeinterval = 55*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("56m")){
						 timeinterval = 56*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("57m")){
						 timeinterval = 57*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("58m")){
						 timeinterval = 58*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("59m")){
						 timeinterval = 36*60*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
				}
				else if(timera.endsWith("s")){
					if(timera.equalsIgnoreCase("1s")){
						 timeinterval = 1*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("2s")){
						 timeinterval = 2*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("3s")){
						 timeinterval = 3*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("4s")){
						 timeinterval = 4*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("5s")){
						 timeinterval = 5*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("6s")){
						 timeinterval = 6*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("7s")){
						 timeinterval = 7*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("8s")){
						 timeinterval = 8*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("9s")){
						 timeinterval = 9*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("10s")){
						 timeinterval = 10*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("11s")){
						 timeinterval = 11*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("12s")){
						 timeinterval = 12*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("13s")){
						 timeinterval = 13*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("14s")){
						 timeinterval = 14*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("15s")){
						 timeinterval = 15*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("16s")){
						 timeinterval = 16*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("17s")){
						 timeinterval = 17*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("18s")){
						 timeinterval = 18*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("19s")){
						 timeinterval = 19*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("20s")){
						 timeinterval = 20*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("21s")){
						 timeinterval = 21*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("22s")){
						 timeinterval = 22*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("23s")){
						 timeinterval = 23*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("24s")){
						 timeinterval = 24*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("25s")){
						 timeinterval = 25*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("26s")){
						 timeinterval = 26*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("27s")){
						 timeinterval = 27*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("28s")){
						 timeinterval = 28*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("29s")){
						 timeinterval = 29*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("30s")){
						 timeinterval = 30*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("31s")){
						 timeinterval = 31*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("32s")){
						 timeinterval = 32*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("33s")){
						 timeinterval = 33*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("34s")){
						 timeinterval = 34*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("35s")){
						 timeinterval = 35*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("36s")){
						 timeinterval = 36*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("37s")){
						 timeinterval = 37*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("38s")){
						 timeinterval = 38*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("39s")){
						 timeinterval = 39*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("40s")){
						 timeinterval = 40*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("41s")){
						 timeinterval = 41*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("42s")){
						 timeinterval = 42*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("43s")){
						 timeinterval = 43*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("44s")){
						 timeinterval = 44*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("45s")){
						 timeinterval = 45*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("46s")){
						 timeinterval = 46*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("47s")){
						 timeinterval = 47*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("48s")){
						 timeinterval = 48*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("49s")){
						 timeinterval = 49*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("50s")){
						 timeinterval = 50*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("51s")){
						 timeinterval = 51*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("52s")){
						 timeinterval = 52*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("53s")){
						 timeinterval = 53*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("54s")){
						 timeinterval = 54*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("55s")){
						 timeinterval = 55*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("56s")){
						 timeinterval = 56*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("57s")){
						 timeinterval = 57*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("58s")){
						 timeinterval = 58*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
					else if(timera.equalsIgnoreCase("59s")){
						 timeinterval = 36*1000;
					    	Timer timer = new Timer();
							timer.scheduleAtFixedRate(new TimerTask() {
							  @Override
							  public void run() {
								  if(keeprunning){
									  switchseason();
									  }else{
										  timer.cancel();
									  }
							  }

							} , timeinterval, timeinterval);
							return;
					}
				}else{
				System.err.println("There is a problem with the timer file! Make sure what ever is in there can be read!");
				System.err.println("Putting on a two-hour timer like v1.1. If the config is correct please notify me of the problem in the Seasons thread.");
				{
					timeinterval = 2*60*60*1000;
					Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  if(keeprunning){
							  switchseason();
							  }else{
								  timer.cancel();
							  }
					  }
					} , timeinterval, timeinterval);
					return;
				}
				}	
				}
			
			}catch(IOException e){
			e.printStackTrace();
}finally{
	try{
		reader.close();
	}catch (IOException e){
		e.printStackTrace();
	}
}
	}
}
