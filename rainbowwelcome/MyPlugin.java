package rainbowwelcome;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import PluginReference.*;

public class MyPlugin extends PluginBase
	{
	BufferedReader reader = null;
		public static MC_Server server = null;
		public static boolean Togglewelmessage = false;
		public static boolean Toggledimension = false;
		public static boolean Togglebedenter = false;
		public static boolean Togglebedexit = false;
		public static boolean Toggleplrdeath = false;
		public static boolean Toggleanidamage = false;
		public static boolean ToggleAttack = false;
		public static boolean Togglerespawn = false;
		public static boolean Togglehanging = false;
		public static boolean Toggleteleport = false;
		public static boolean Togglepotioneffect = false;
		public static boolean Toggleclock = true;
		String pluginDir = "plugins_mod" + File.separatorChar + "Rainbow Welcome";
		public static double version = 3.2;
		public static File file = null;
		
		public void onStartup(MC_Server argServer){
			System.out.println("Plugin starting! Lets hope it works! :)");
			new File(pluginDir).mkdir();
			server = argServer;
			try{
				File welcomemessage = new File(pluginDir + File.separatorChar + "welcomemessage.txt");
				if (welcomemessage.createNewFile()){
					System.out.println("The file welcomemessage.txt is created!");
					FileWriter fw = new FileWriter(welcomemessage);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("Welcome to the server!");
					bw.close();
					
					System.out.println("File Written");
				}else{
					System.out.println("welcomemessage.txt already exists!");
				}
				File dimensionmessage = new File(pluginDir + File.separatorChar + "dimensionmessage.txt");
				if (dimensionmessage.createNewFile()){
					System.out.println("The file dimensionmessage.txt is created!");
					FileWriter fw = new FileWriter(dimensionmessage);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("You switched dimensions!");
					bw.close();
					
					System.out.println("File Written");
				}else{
					System.out.println("dimensionmessage.txt aready exists!");
				}
				File bedwriter = new File(pluginDir + File.separatorChar + "bedentermessage.txt");
				if (bedwriter.createNewFile()){
					System.out.println("The file bedmessage.txt is created!");
					FileWriter fw = new FileWriter(bedwriter);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("Have a good sleep!");
					bw.close();
					
					System.out.println("File Written");
				}else{
					System.out.println("bedentermessage.txt aready exists!");
				}
				File bedexitwriter = new File(pluginDir + File.separatorChar + "bedexitmessage.txt");
				if (bedexitwriter.createNewFile()){
					System.out.println("The file bedexitmessage.txt is created!");
					FileWriter fw = new FileWriter(bedexitwriter);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("Have a good day!");
					bw.close();
					
					System.out.println("File Written");
				}else{
					System.out.println("bedexitmessage.txt aready exists!");
				}
				File deathwriter = new File(pluginDir + File.separatorChar + "deathmessage.txt");
				if (deathwriter.createNewFile()){
					System.out.println("The file deathmessage.txt is created!");
					FileWriter fw = new FileWriter(deathwriter);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("Try not to die!");
					bw.close();
					
					System.out.println("File Written");
				}else{
					System.out.println("deathmessage.txt aready exists!");
				}
				File animalwriter = new File(pluginDir + File.separatorChar + "animalmessage.txt");
				if (animalwriter.createNewFile()){
					System.out.println("The file animalmessage.txt is created!");
					FileWriter fw = new FileWriter(animalwriter);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("Don't attack the poor animal!");
					bw.close();
					
					System.out.println("File Written");
				}else{
					System.out.println("animalmessage.txt aready exists!");
				}
				File respawnwriter = new File(pluginDir + File.separatorChar + "respawnmessage.txt");
				if (respawnwriter.createNewFile()){
					System.out.println("The file respawnmessage.txt is created!");
					FileWriter fw = new FileWriter(respawnwriter);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("Respawning is the right option!");
					bw.close();
					
					System.out.println("File Written");
				}else{
					System.out.println("respawnmessage.txt aready exists!");
				}
				File hanging = new File(pluginDir + File.separatorChar + "attackhangingmessage.txt");
				if (hanging.createNewFile()){
					System.out.println("The file attackhangingmessage.txt is created!");
					FileWriter fw = new FileWriter(hanging);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("What did it do to you?");
					bw.close();
					
					System.out.println("File Written");
				}else{
					System.out.println("attackhangingmessage.txt already exists!");
				}
				File teleport = new File(pluginDir + File.separatorChar + "teleportmessage.txt");
				if (teleport.createNewFile()){
					System.out.println("The file teleportmessage.txt is created!");
					FileWriter fw = new FileWriter(teleport);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("Teleporting somewhere?");
					bw.close();
					
					System.out.println("File Written");
				}else{
					System.out.println("teleportmessage.txt already exists!");
				}
				File potion = new File(pluginDir + File.separatorChar + "potioneffectmessage.txt");
				if (potion.createNewFile()){
					System.out.println("The file potioneffectmessage.txt is created!");
					FileWriter fw = new FileWriter(potion);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("Potions are taking effect!");
					bw.close();
					
					System.out.println("File Written");
				}else{
					System.out.println("potioneffectmessage.txt already exists!");
				}
			File ver = new File(pluginDir + File.separator + "simplicitymode.txt");
			if (ver.createNewFile()){
				System.out.println("The file simplicitymode.txt is created!");
				FileWriter fw = new FileWriter(ver);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("3.2");
				bw.close();
				
				System.out.println("File Written");
			}else{
				System.out.println("simplicitymode.txt already exists!");
				BufferedReader reader = new BufferedReader(new FileReader(ver));
				String douoble = reader.readLine();	
	    			double d = Double.parseDouble(douoble);
	    			String s = String.valueOf(d);
	    				String decimal = s.substring(2, s.length());
	    			if(d > 3.2){
	    				System.out.println("You can't go into the future!");
	    				System.out.println("The biggest version is 3.2");
	    			}else if(d < 0.0){
	    				System.out.println("The smallest version is 0.1!");
	    				//Checks if there is only one decimal point
	    			}else if(decimal.length() == 1){
	    				version = d;
	    				System.out.println("New simplicity mode version is: " + version);
	    				BufferedWriter writer = new BufferedWriter(new FileWriter(ver));
	    				writer.write(String.valueOf(version));
	    				writer.close();
	    			}else{
	    				System.out.println("You can only specify a number with one decimal point! You can use .0 to specify a major version only!");
	    			}
	    			reader.close();
			}
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		public void onShutdown()
		{
			System.out.println("Rainbow Welcome shutting down!");
		}
		public PluginInfo getPluginInfo() 
		{ 
			PluginInfo info = new PluginInfo();
			info.description = "Sends customisable and toggleable messages to players: version 3.2 started";
			info.eventSortOrder = -2345.67f;
			return info;
		}
		public void onPlayerJoin(MC_Player plr){
			if(Togglewelmessage){
				System.out.println("Welcome message is disabled!");
			}else if(version <0.2){
				System.out.println("Simplicity mode is not up to the welcome message!");
			}else{
			String welmessage;
			try{
				reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "welcomemessage.txt"));
				while ((welmessage = reader.readLine()) != null) {
					String playermessage = welmessage.replaceAll("%PLAYER%", plr.getName()).replaceAll("%WORLD%", plr.getWorld().getName()).replaceAll("%ECONOMY%", String.valueOf(plr.getEconomyBalance()) ).replaceAll("%IP%", plr.getIPAddress()).replaceAll("&TIME%", getDate());
					String colouredformattedmessage = playermessage.replaceAll("&b,", ChatColor.AQUA).replaceAll("&0,", ChatColor.BLACK).replaceAll("&9,", ChatColor.BLUE).replaceAll("&l,", ChatColor.BOLD).replaceAll("&3,", ChatColor.DARK_AQUA).replaceAll("&1,", ChatColor.DARK_BLUE).replaceAll("&8,", ChatColor.DARK_GRAY).replaceAll("&2,", ChatColor.DARK_GREEN).replaceAll("&5,", ChatColor.DARK_PURPLE).replaceAll("&4,", ChatColor.DARK_RED).replaceAll("&6,", ChatColor.GOLD).replaceAll("&7,", ChatColor.GRAY).replaceAll("&a,", ChatColor.GREEN).replaceAll("&o,", ChatColor.ITALIC).replaceAll("&d,", ChatColor.LIGHT_PURPLE).replaceAll("&k,", ChatColor.MAGIC).replaceAll("&c,", ChatColor.RED).replaceAll("&r,", ChatColor.RESET).replaceAll("&m", ChatColor.STRIKETHROUGH).replaceAll("&n,", ChatColor.UNDERLINE).replaceAll("&f,", ChatColor.WHITE).replaceAll("&e,", ChatColor.YELLOW);
					plr.sendMessage(colouredformattedmessage);
					if(Toggleclock){
					plr.sendMessage("The current time is: " + getDate());
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
			}		
		
		public void onPlayerRespawn(MC_Player plr) 
		{
			if(Togglerespawn){
				System.out.println("Respawn message is toggled off!");
			}else if(version <2.1){
				System.out.println("Simplicity mode is not up to the respawn message!");
			}else{
			 String respawnmessage;
			 try{
					reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "respawnmessage.txt"));
					while ((respawnmessage = reader.readLine()) != null) {
						String playermessage = respawnmessage.replaceAll("%PLAYER%", plr.getName()).replaceAll("%WORLD%", plr.getWorld().getName()).replaceAll("%ECONOMY%", String.valueOf(plr.getEconomyBalance()) ).replaceAll("%IP%", plr.getIPAddress()).replaceAll("&TIME%", getDate());
						String colouredformattedmessage = playermessage.replaceAll("&b,", ChatColor.AQUA).replaceAll("&0,", ChatColor.BLACK).replaceAll("&9,", ChatColor.BLUE).replaceAll("&l,", ChatColor.BOLD).replaceAll("&3,", ChatColor.DARK_AQUA).replaceAll("&1,", ChatColor.DARK_BLUE).replaceAll("&8,", ChatColor.DARK_GRAY).replaceAll("&2,", ChatColor.DARK_GREEN).replaceAll("&5,", ChatColor.DARK_PURPLE).replaceAll("&4,", ChatColor.DARK_RED).replaceAll("&6,", ChatColor.GOLD).replaceAll("&7,", ChatColor.GRAY).replaceAll("&a,", ChatColor.GREEN).replaceAll("&o,", ChatColor.ITALIC).replaceAll("&d,", ChatColor.LIGHT_PURPLE).replaceAll("&k,", ChatColor.MAGIC).replaceAll("&c,", ChatColor.RED).replaceAll("&r,", ChatColor.RESET).replaceAll("&m", ChatColor.STRIKETHROUGH).replaceAll("&n,", ChatColor.UNDERLINE).replaceAll("&f,", ChatColor.WHITE).replaceAll("&e,", ChatColor.YELLOW);
						plr.sendMessage(colouredformattedmessage);
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
		}
			public void onAttemptPlayerChangeDimension(MC_Player plr, int newDimension, MC_EventInfo ei)
			{
				if(Toggledimension){
					System.out.println("Dimension message is disabled!");
				}else if(version <0.6){
					System.out.println("Simplicity mode is not up to the dimension message!");
				}else{
				 String dimmessage;
				 try{
						reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "dimensionmessage.txt"));
						while ((dimmessage = reader.readLine()) != null) {
							String playermessage = dimmessage.replaceAll("%PLAYER%", plr.getName()).replaceAll("%WORLD%", plr.getWorld().getName()).replaceAll("%ECONOMY%", String.valueOf(plr.getEconomyBalance()) ).replaceAll("%IP%", plr.getIPAddress()).replaceAll("&TIME%", getDate());
							String colouredformattedmessage = playermessage.replaceAll("&b,", ChatColor.AQUA).replaceAll("&0,", ChatColor.BLACK).replaceAll("&9,", ChatColor.BLUE).replaceAll("&l,", ChatColor.BOLD).replaceAll("&3,", ChatColor.DARK_AQUA).replaceAll("&1,", ChatColor.DARK_BLUE).replaceAll("&8,", ChatColor.DARK_GRAY).replaceAll("&2,", ChatColor.DARK_GREEN).replaceAll("&5,", ChatColor.DARK_PURPLE).replaceAll("&4,", ChatColor.DARK_RED).replaceAll("&6,", ChatColor.GOLD).replaceAll("&7,", ChatColor.GRAY).replaceAll("&a,", ChatColor.GREEN).replaceAll("&o,", ChatColor.ITALIC).replaceAll("&d,", ChatColor.LIGHT_PURPLE).replaceAll("&k,", ChatColor.MAGIC).replaceAll("&c,", ChatColor.RED).replaceAll("&r,", ChatColor.RESET).replaceAll("&m", ChatColor.STRIKETHROUGH).replaceAll("&n,", ChatColor.UNDERLINE).replaceAll("&f,", ChatColor.WHITE).replaceAll("&e,", ChatColor.YELLOW);
							plr.sendMessage(colouredformattedmessage);
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
			}
				public void onPlayerBedEnter(MC_Player plr, MC_Block bedBlk, MC_Location bedLoc) 
				{	
						 if(Togglebedenter){
							 System.out.println("Bed messages are disabled!");
						 }else if(version <0.10){
								System.out.println("Simplicity mode is not up to the bed enter message!");
							}else{
						 String bedentermessage;
						 try{
								reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "bedentermessage.txt"));
								while ((bedentermessage = reader.readLine()) != null) {
									String playermessage = bedentermessage.replaceAll("%PLAYER%", plr.getName()).replaceAll("%WORLD%", plr.getWorld().getName()).replaceAll("%ECONOMY%", String.valueOf(plr.getEconomyBalance()) ).replaceAll("%IP%", plr.getIPAddress()).replaceAll("&TIME%", getDate());
									String colouredformattedmessage = playermessage.replaceAll("&b,", ChatColor.AQUA).replaceAll("&0,", ChatColor.BLACK).replaceAll("&9,", ChatColor.BLUE).replaceAll("&l,", ChatColor.BOLD).replaceAll("&3,", ChatColor.DARK_AQUA).replaceAll("&1,", ChatColor.DARK_BLUE).replaceAll("&8,", ChatColor.DARK_GRAY).replaceAll("&2,", ChatColor.DARK_GREEN).replaceAll("&5,", ChatColor.DARK_PURPLE).replaceAll("&4,", ChatColor.DARK_RED).replaceAll("&6,", ChatColor.GOLD).replaceAll("&7,", ChatColor.GRAY).replaceAll("&a,", ChatColor.GREEN).replaceAll("&o,", ChatColor.ITALIC).replaceAll("&d,", ChatColor.LIGHT_PURPLE).replaceAll("&k,", ChatColor.MAGIC).replaceAll("&c,", ChatColor.RED).replaceAll("&r,", ChatColor.RESET).replaceAll("&m", ChatColor.STRIKETHROUGH).replaceAll("&n,", ChatColor.UNDERLINE).replaceAll("&f,", ChatColor.WHITE).replaceAll("&e,", ChatColor.YELLOW);
									plr.sendMessage(colouredformattedmessage);
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
						
				}
					public void onPlayerBedLeave(MC_Player plr, MC_Block blk, MC_Location bedLoc)
					{
							  if(Togglebedexit){
								 System.out.println("Bed messages are disabled!");
							 }else if(version <0.10){
									System.out.println("Simplicity mode is not up to the bed exit message!");
								}else{
							 String bedexitmessage;
							 try{
									reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "bedexitmessage.txt"));
									while ((bedexitmessage = reader.readLine()) != null) {
										String playermessage = bedexitmessage.replaceAll("%PLAYER%", plr.getName()).replaceAll("%WORLD%", plr.getWorld().getName()).replaceAll("%ECONOMY%", String.valueOf(plr.getEconomyBalance()) ).replaceAll("%IP%", plr.getIPAddress()).replaceAll("&TIME%", getDate());
										String colouredformattedmessage = playermessage.replaceAll("&b,", ChatColor.AQUA).replaceAll("&0,", ChatColor.BLACK).replaceAll("&9,", ChatColor.BLUE).replaceAll("&l,", ChatColor.BOLD).replaceAll("&3,", ChatColor.DARK_AQUA).replaceAll("&1,", ChatColor.DARK_BLUE).replaceAll("&8,", ChatColor.DARK_GRAY).replaceAll("&2,", ChatColor.DARK_GREEN).replaceAll("&5,", ChatColor.DARK_PURPLE).replaceAll("&4,", ChatColor.DARK_RED).replaceAll("&6,", ChatColor.GOLD).replaceAll("&7,", ChatColor.GRAY).replaceAll("&a,", ChatColor.GREEN).replaceAll("&o,", ChatColor.ITALIC).replaceAll("&d,", ChatColor.LIGHT_PURPLE).replaceAll("&k,", ChatColor.MAGIC).replaceAll("&c,", ChatColor.RED).replaceAll("&r,", ChatColor.RESET).replaceAll("&m", ChatColor.STRIKETHROUGH).replaceAll("&n,", ChatColor.UNDERLINE).replaceAll("&f,", ChatColor.WHITE).replaceAll("&e,", ChatColor.YELLOW);
										plr.sendMessage(colouredformattedmessage);
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
						}
					public void onAttemptAttackEntity(MC_Player plr, MC_Entity ent, MC_EventInfo ei) 
					{ 
						if(Toggleanidamage){
							 System.out.println("Animal messages are disabled!");
						 }else if(version <2.1){
								System.out.println("Simplicity mode is not up to the animal message!");
							}else{
							 if(ent.getType() == MC_EntityType.BAT){
								 AnimalMessage(plr, ent);
						 }
							 if(ent.getType() == MC_EntityType.CHICKEN){
								 AnimalMessage(plr, ent);
							 }
							 if(ent.getType() == MC_EntityType.COW){
								 AnimalMessage(plr, ent);
							 }
							 if(ent.getType() == MC_EntityType.HORSE){
								 AnimalMessage(plr, ent);
							 }
							 if(ent.getType() == MC_EntityType.OCELOT){
								 AnimalMessage(plr, ent);
							 }
							 if(ent.getType() == MC_EntityType.PIG){
								 AnimalMessage(plr, ent);
							 }
							 if(ent.getType() == MC_EntityType.RABBIT){
								 AnimalMessage(plr, ent);
							 }
							 if(ent.getType() == MC_EntityType.SHEEP){
								 AnimalMessage(plr, ent);
							 }
							 if(ent.getType() == MC_EntityType.SNOWMAN){
								 AnimalMessage(plr, ent);
							 }
							 if(ent.getType() == MC_EntityType.SQUID){
								 AnimalMessage(plr, ent);
							 }
							 if(ent.getType() == MC_EntityType.VILLAGER){
								 AnimalMessage(plr, ent);
							 }
							 if(ent.getType() == MC_EntityType.WOLF){
								 AnimalMessage(plr, ent);
							 }
						 }
						 }
					public void onPlayerDeath(MC_Player plr, MC_Player plrKiller, MC_DamageType dmgType, String deathMsg) 
					{
						if(Toggleplrdeath){
							System.out.println("Player death messages are disabled!");
						}else if(version <0.3){
							System.out.println("Simplicity mode is not up to the death message!");
						}else{
						System.out.println("A player has just died. Your custom message has been sent to them.");
						String deathmessage;
						try{
							reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "deathmessage.txt"));
							while ((deathmessage = reader.readLine()) != null) {
								String playermessage = deathmessage.replaceAll("%PLAYER%", plr.getName()).replaceAll("%WORLD%", plr.getWorld().getName()).replaceAll("%ECONOMY%", String.valueOf(plr.getEconomyBalance()) ).replaceAll("%IP%", plr.getIPAddress()).replaceAll("&TIME%", getDate());
								String colouredformattedmessage = playermessage.replaceAll("&b,", ChatColor.AQUA).replaceAll("&0,", ChatColor.BLACK).replaceAll("&9,", ChatColor.BLUE).replaceAll("&l,", ChatColor.BOLD).replaceAll("&3,", ChatColor.DARK_AQUA).replaceAll("&1,", ChatColor.DARK_BLUE).replaceAll("&8,", ChatColor.DARK_GRAY).replaceAll("&2,", ChatColor.DARK_GREEN).replaceAll("&5,", ChatColor.DARK_PURPLE).replaceAll("&4,", ChatColor.DARK_RED).replaceAll("&6,", ChatColor.GOLD).replaceAll("&7,", ChatColor.GRAY).replaceAll("&a,", ChatColor.GREEN).replaceAll("&o,", ChatColor.ITALIC).replaceAll("&d,", ChatColor.LIGHT_PURPLE).replaceAll("&k,", ChatColor.MAGIC).replaceAll("&c,", ChatColor.RED).replaceAll("&r,", ChatColor.RESET).replaceAll("&m", ChatColor.STRIKETHROUGH).replaceAll("&n,", ChatColor.UNDERLINE).replaceAll("&f,", ChatColor.WHITE).replaceAll("&e,", ChatColor.YELLOW);
								plr.sendMessage(colouredformattedmessage);
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
					}
					public void onAttemptDamageHangingEntity(MC_Player plr, MC_Location loc, MC_HangingEntityType entType, MC_EventInfo ei)  {
						if(Togglehanging){
							System.out.println("Attack hanging entity are disabled!");
						}else if(version <2.8){
							System.out.println("Simplicity mode is not up to the hanging entity message!");
						}else{
						String attackhangingmessage;
						try{
							reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "attackhangingmessage.txt"));
							while ((attackhangingmessage = reader.readLine()) != null) {
								String playermessage = attackhangingmessage.replaceAll("%PLAYER%", plr.getName()).replaceAll("%WORLD%", plr.getWorld().getName()).replaceAll("%ECONOMY%", String.valueOf(plr.getEconomyBalance()) ).replaceAll("%IP%", plr.getIPAddress()).replaceAll("&TIME%", getDate());
								String colouredformattedmessage = playermessage.replaceAll("&b,", ChatColor.AQUA).replaceAll("&0,", ChatColor.BLACK).replaceAll("&9,", ChatColor.BLUE).replaceAll("&l,", ChatColor.BOLD).replaceAll("&3,", ChatColor.DARK_AQUA).replaceAll("&1,", ChatColor.DARK_BLUE).replaceAll("&8,", ChatColor.DARK_GRAY).replaceAll("&2,", ChatColor.DARK_GREEN).replaceAll("&5,", ChatColor.DARK_PURPLE).replaceAll("&4,", ChatColor.DARK_RED).replaceAll("&6,", ChatColor.GOLD).replaceAll("&7,", ChatColor.GRAY).replaceAll("&a,", ChatColor.GREEN).replaceAll("&o,", ChatColor.ITALIC).replaceAll("&d,", ChatColor.LIGHT_PURPLE).replaceAll("&k,", ChatColor.MAGIC).replaceAll("&c,", ChatColor.RED).replaceAll("&r,", ChatColor.RESET).replaceAll("&m", ChatColor.STRIKETHROUGH).replaceAll("&n,", ChatColor.UNDERLINE).replaceAll("&f,", ChatColor.WHITE).replaceAll("&e,", ChatColor.YELLOW);
								plr.sendMessage(colouredformattedmessage);
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
					}
					public void onAttemptPlayerTeleport(MC_Player plr, MC_Location loc, MC_EventInfo ei)  {
						if(Toggleteleport){
							System.out.println("Teleport messages are disabled!");
						}else if(version <2.8){
							System.out.println("Simplicity mode is not up to the teleport message!");
						}else{
						String teleportmessage;
						try{
							reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "teleportmessage.txt"));
							while ((teleportmessage = reader.readLine()) != null) {
								String playermessage = teleportmessage.replaceAll("%PLAYER%", plr.getName()).replaceAll("%WORLD%", plr.getWorld().getName()).replaceAll("%ECONOMY%", String.valueOf(plr.getEconomyBalance()) ).replaceAll("%IP%", plr.getIPAddress()).replaceAll("&TIME%", getDate());
								String colouredformattedmessage = playermessage.replaceAll("&b,", ChatColor.AQUA).replaceAll("&0,", ChatColor.BLACK).replaceAll("&9,", ChatColor.BLUE).replaceAll("&l,", ChatColor.BOLD).replaceAll("&3,", ChatColor.DARK_AQUA).replaceAll("&1,", ChatColor.DARK_BLUE).replaceAll("&8,", ChatColor.DARK_GRAY).replaceAll("&2,", ChatColor.DARK_GREEN).replaceAll("&5,", ChatColor.DARK_PURPLE).replaceAll("&4,", ChatColor.DARK_RED).replaceAll("&6,", ChatColor.GOLD).replaceAll("&7,", ChatColor.GRAY).replaceAll("&a,", ChatColor.GREEN).replaceAll("&o,", ChatColor.ITALIC).replaceAll("&d,", ChatColor.LIGHT_PURPLE).replaceAll("&k,", ChatColor.MAGIC).replaceAll("&c,", ChatColor.RED).replaceAll("&r,", ChatColor.RESET).replaceAll("&m", ChatColor.STRIKETHROUGH).replaceAll("&n,", ChatColor.UNDERLINE).replaceAll("&f,", ChatColor.WHITE).replaceAll("&e,", ChatColor.YELLOW);
								plr.sendMessage(colouredformattedmessage);
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
					}
					public void onAttemptPotionEffect(MC_Player plr, MC_PotionEffectType potionType, MC_EventInfo ei){
						if(Togglepotioneffect){
							System.out.println("Potion effects messages are disabled!");
						}else if(version <2.8){
							System.out.println("Simplicity mode is not up to the potion effect message!");
						}else{
						String potioneffectmessage;
						try{
							reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "potionmessage.txt"));
							while ((potioneffectmessage = reader.readLine()) != null) {
								String playermessage = potioneffectmessage.replaceAll("%PLAYER%", plr.getName()).replaceAll("%WORLD%", plr.getWorld().getName()).replaceAll("%ECONOMY%", String.valueOf(plr.getEconomyBalance()) ).replaceAll("%IP%", plr.getIPAddress()).replaceAll("&TIME%", getDate());
								String colouredformattedmessage = playermessage.replaceAll("&b,", ChatColor.AQUA).replaceAll("&0,", ChatColor.BLACK).replaceAll("&9,", ChatColor.BLUE).replaceAll("&l,", ChatColor.BOLD).replaceAll("&3,", ChatColor.DARK_AQUA).replaceAll("&1,", ChatColor.DARK_BLUE).replaceAll("&8,", ChatColor.DARK_GRAY).replaceAll("&2,", ChatColor.DARK_GREEN).replaceAll("&5,", ChatColor.DARK_PURPLE).replaceAll("&4,", ChatColor.DARK_RED).replaceAll("&6,", ChatColor.GOLD).replaceAll("&7,", ChatColor.GRAY).replaceAll("&a,", ChatColor.GREEN).replaceAll("&o,", ChatColor.ITALIC).replaceAll("&d,", ChatColor.LIGHT_PURPLE).replaceAll("&k,", ChatColor.MAGIC).replaceAll("&c,", ChatColor.RED).replaceAll("&r,", ChatColor.RESET).replaceAll("&m", ChatColor.STRIKETHROUGH).replaceAll("&n,", ChatColor.UNDERLINE).replaceAll("&f,", ChatColor.WHITE).replaceAll("&e,", ChatColor.YELLOW);
								plr.sendMessage(colouredformattedmessage);
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
					}
					public void onConsoleInput(String cmd, MC_EventInfo ei) 
					{			
						if(ei.isCancelled) return;
						
						String inputLine = null;
					    String inputField = null;
					    String inputArray[] = new String [20];
					    int inputIndex = 0;
					    int charIndex = 0;	
					    inputIndex = 0;
					    inputLine = cmd.trim();	

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

					    if (inputArray[1].equalsIgnoreCase("rw")) {
					        ei.isCancelled = true;		
					        if (inputIndex >= 2) {
					            if (inputArray[2].equalsIgnoreCase("display")) {
									System.out.println("Welcome to the server");			
									return;
					                }
					            else if (inputArray[2].equalsIgnoreCase("displaycustom")) {
									String welmessage;
									try{
										reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "welcomemessage.txt"));
										while ((welmessage = reader.readLine()) != null) {
											System.out.println(welmessage);
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
									else if (inputArray[2].equalsIgnoreCase("broadcast")){
										server.broadcastMessage("Welcome to the server");
									}
									else if (inputArray[2].equalsIgnoreCase("broadcastcustom")){
										String welmessage;
										try{
											reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "welcomemessage.txt"));
											while ((welmessage = reader.readLine()) != null) {
												server.broadcastMessage(welmessage);
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
									else if (inputArray[2].equalsIgnoreCase("toggle")){
										 if (inputIndex >= 3){
											 if(inputArray[3].equalsIgnoreCase("messageon")){
												 System.out.println("Welcome message toggled on!");
													Togglewelmessage = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("messageoff")){
												 System.out.println("Welcome message toggled off!");
												 Togglewelmessage = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("dimensionon")){
													System.out.println("Dimension message toggled on!");
													Toggledimension = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("dimensionoff")){
													System.out.println("Dimension message toggled off!");
													Toggledimension = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("bedenteron")){
													System.out.println("Bed enter message toggled on!");
													Togglebedenter = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("bedenteroff")){
													System.out.println("Bed enter message toggled off!");
													Togglebedenter = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("bedexiton")){
													System.out.println("Bed enter message toggled on!");
													Togglebedexit = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("bedexitoff")){
													System.out.println("Bed enter message toggled off!");
													Togglebedexit = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("plrdeathon")){
													System.out.println("Player death/killer messages toggled on!");
													Toggleplrdeath = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("plrdeathoff")){
													System.out.println("Player death/killer messages toggled off!");
													Toggleplrdeath = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("anidamageon")){
													System.out.println("Animal attack message toggled on!");
													Toggleanidamage = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("anidamageoff")){
													System.out.println("Animal attack message toggled off!");
													Toggleanidamage = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("respawnon")){
													System.out.println("Respawn message toggled on!");
													Togglerespawn = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("respawnoff")){
												 System.out.println("Respawn message toggled off!");
												 Togglerespawn = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("clockon")){
												 System.out.println("Clock on login toggled on!");
												 Toggleclock = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("clockoff")){
												 System.out.println("Clock on login toggled off!");
												 Toggleclock = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("hangingoff")){
												 System.out.println("Attack hanging entity message toggled off!");
												 Togglehanging = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("hangingon")){
												 System.out.println("Attack hanging entity message toggled on!");
												 Togglehanging = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("teleportoff")){
												 System.out.println("Teleport message toggled off!");
												 Toggleteleport = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("teleporton")){
												 System.out.println("Teleport message toggled on!");
												 Toggleteleport = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("potionoff")){
												 System.out.println("Potion message toggled off!");
												 Togglepotioneffect = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("potionon")){
												 System.out.println("Potion message toggled on!");
												 Togglepotioneffect = true;
											 }
										 else{	 
										System.out.println("Message toggling help page:");
										System.out.println("Toggle messageon/off toggles welcome message");
										System.out.println("Toggle dimensionon/off toggles dimension change message");
										System.out.println("Toggle bedon/off toggles bed message(s)");
										System.out.println("Toggle plrdeathon/off toggles player death message");
										System.out.println("Toggle anidamageon/off toggles animal damage");
										System.out.println("Toggle respawnon/off toggles respawn message");
										System.out.println("Toggle hangingon/off toggles hanging entity message");
										System.out.println("Toggle teleporton/off toggles teleport message");
										System.out.println("Toggle potionon/off toggles potion effect message");
										System.out.println("Toggle respawnon/off toggles respawn message");
										 }
										 }else{
												System.out.println("Message toggling help page:");
												System.out.println("Toggle messageon/off toggles welcome message");
												System.out.println("Toggle dimensionon/off toggles dimension change message");
												System.out.println("Toggle bedon/off toggles bed message(s)");
												System.out.println("Toggle plrdeathon/off toggles player death message");
												System.out.println("Toggle anidamageon/off toggles animal damage");
												System.out.println("Toggle respawnon/off toggles respawn message");
												System.out.println("Toggle hangingon/off toggles hanging entity message");
												System.out.println("Toggle teleporton/off toggles teleport message");
												System.out.println("Toggle potionon/off toggles potion effect message");
												System.out.println("Toggle respawnon/off toggles respawn message");
										 }
									}
									else {
							System.out.println("This is the Rainbow Welcome command center:");
							System.out.println("rw display displays 'Welcome to the server'");
							System.out.println("rw displaycustom displays your custom welcome message");
							System.out.println("rw broadcast broadcasts 'Welcome to the server! to all players online");
							System.out.println("rw broadcastcustom broadcasts your custom message to all players online");
							System.out.println("rw toggle views the toggle help page");
							System.out.println("/rw mes goes to the message changing help page");
					                }						
					            }else{
										System.out.println("This is the Rainbow Welcome command center:");
										System.out.println("rw display displays 'Welcome to the server'");
										System.out.println("rw displaycustom displays your custom welcome message");
										System.out.println("rw broadcast broadcasts 'Welcome to the server! to all players online");
										System.out.println("rw broadcastcustom broadcasts your custom message to all players online");
										System.out.println("rw toggle views the toggle help page");
										System.out.println("/rw mes goes to the message changing help page");
					            }
					}
					    if(inputArray[1].equalsIgnoreCase("mes")){
							ei.isCancelled = true;
								 if (inputIndex >= 2){
									 if (inputIndex >= 3){
									 if(inputArray[2].equalsIgnoreCase("welmessage")){
										file = new File(pluginDir + File.separatorChar + "welcomemessage.txt");
										changemessage(null, cmd);
										 }
									 else if (inputArray[2].equalsIgnoreCase("dimmessage")){
										file = new File(pluginDir + File.separatorChar + "dimensionmessage.txt");
										changemessage(null, cmd);
										 }
								     else if (inputArray[2].equalsIgnoreCase("bedentermessage")){
								    	file = new File(pluginDir + File.separatorChar + "bedentermessage.txt");
										changemessage(null, cmd);
									 }
									 else if (inputArray[2].equalsIgnoreCase("animalmessage")){
										 file = new File(pluginDir + File.separatorChar + "animalmessage.txt");
										 changemessage(null, cmd);	
										 	}
									 else if (inputArray[2].equalsIgnoreCase("bedexitmessage")){
										 file = new File(pluginDir + File.separatorChar + "bedexitmessage.txt");
										 changemessage(null, cmd);
									 }
									 else if (inputArray[2].equalsIgnoreCase("deathmessage")){
										 file = new File(pluginDir + File.separatorChar + "deathmessage.txt");
										 changemessage(null, cmd);
									 }
									 else if (inputArray[2].equalsIgnoreCase("respawnmessage")){
										 file = new File(pluginDir + File.separatorChar + "respawnmessage.txt");
										 changemessage(null, cmd);
										 }
									 else if (inputArray[2].equalsIgnoreCase("hangingmessage")){
										 file = new File(pluginDir + File.separatorChar + "attackhangingmessage.txt");
										 changemessage(null, cmd);
									 }
									 else if (inputArray[2].equalsIgnoreCase("teleportmessage")){
										 file = new File(pluginDir + File.separatorChar + "teleportmessage.txt");
										 changemessage(null, cmd);
									 }
									 else if (inputArray[2].equalsIgnoreCase("potionmessage")){
										 file = new File(pluginDir + File.separatorChar + "potionmessage.txt");
										 changemessage(null, cmd);
									 }
									 else{
										 System.out.println("Message changing help page:");
										 System.out.println("mes welmessage <message> writes a new welcome message");
										 System.out.println("mes dimmessage <message> writes a new dimension message");
										 System.out.println("mes bedentermessage <message> writes a new bed enter message");
										 System.out.println("mes animalmessage <message> writes a new animal message");
										 System.out.println("mes bedexitmessage <message> writes a new bed exit message");
										 System.out.println("mes deathmessage <message> writes a new death message");
										 System.out.println("mes respawnmessage <message> writes a new respawn message");
										 System.out.println("mes hangingmessage <message> writes a new hanging entity message");
										 System.out.println("mes teleportmessage <message> writes a new teleport message");
										 System.out.println("mes potionmessage <message> writes a new potion message");
									 }
									 }else{
											 System.out.println("There is no message! The file will not be changed!");
											 System.out.println("If you don't want there to be the desired message, do /rw toggle and toggle the undesired message off!");
										 }
								 }else{
										 System.out.println("Message changing help page:");
										 System.out.println("mes welmessage <message> writes a new welcome message");
										 System.out.println("mes dimmessage <message> writes a new dimension message");
										 System.out.println("mes bedentermessage <message> writes a new bed enter message");
										 System.out.println("mes animalmessage <message> writes a new animal message");
										 System.out.println("mes bedexitmessage <message> writes a new bed exit message");
										 System.out.println("mes deathmessage <message> writes a new death message");
										 System.out.println("mes respawnmessage <message> writes a new respawn message");
										 System.out.println("mes hangingmessage <message> writes a new hanging entity message");
										 System.out.println("mes teleportmessage <message> writes a new teleport message");
										 System.out.println("mes potionmessage <message> writes a new potion message");
									 }
					    }
					    else if(inputArray[1].equalsIgnoreCase("sim")){
					    	ei.isCancelled = true;
					    	if(inputArray[2] == null){
					    		System.out.println("Your current simplicity mode version is: " + version);
					    	}else{
					    		try{	
					    			double d = Double.parseDouble(inputArray[2]);
					    			String s = String.valueOf(d);
					    				String decimal = s.substring(2, s.length());
					    			if(d > 3.2){
					    				System.out.println("You can't go into the future!");
					    				System.out.println("The biggest version is 3.2");
					    			}else if(d < 0.0){
					    				System.out.println("The smallest version is 0.1!");
					    				//Checks if there is only one decimal point
					    			}else if(decimal.length() == 1){
					    				version = d;
					    				System.out.println("New simplicity mode version is: " + version);
					    				File ver = new File(pluginDir + File.separator + "simplicitymode.txt");
					    				BufferedWriter writer = new BufferedWriter(new FileWriter(ver));
					    				writer.write(String.valueOf(version));
					    				writer.close();
					    			}else{
					    				System.out.println("You can only specify a number with one decimal point! You can use .0 to specify a major version only!");
					    			}
					    			
					    		}catch(NumberFormatException e){
					    			System.out.println("You need a proper number!");
					    			} catch (IOException e) {
									e.printStackTrace();
								}
					    		}
					    	}
					    }
					public void onPlayerInput(MC_Player plr, String message, MC_EventInfo ei) {	         
						String inputLine = null;
					    String inputField = null;
					    String inputArray[] = new String [20];
					    int inputIndex = 0;
					    int charIndex = 0;	
					    inputIndex = 0;
					    inputLine = message.trim();		
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

					    if (inputArray[1].equalsIgnoreCase("/rw")) {
					        ei.isCancelled = true;		
					        if (inputIndex >= 2) {
					            if (inputArray[2].equalsIgnoreCase("display")) {
									plr.sendMessage("Welcome to the server");			
									return;
					                }
					            else if (inputArray[2].equalsIgnoreCase("displaycustom")) {
									String welmessage;
									try{
										reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "welcomemessage.txt"));
										while ((welmessage = reader.readLine()) != null) {
											plr.sendMessage(welmessage);
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
									else if (inputArray[2].equalsIgnoreCase("broadcast")){
										if(plr.hasPermission("rwelcome.broadcast")){
										server.broadcastMessage("Welcome to the server");
									}else{
										plr.sendMessage(ChatColor.RED + "You do not have permission to use this command");
									}
									}
									else if (inputArray[2].equalsIgnoreCase("broadcastcustom")){
										if(plr.hasPermission("rwelcome.broadcast")){
										String welmessage;
										try{
											reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "welcomemessage.txt"));
											while ((welmessage = reader.readLine()) != null) {
												server.broadcastMessage(welmessage);
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
									}else{
										plr.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
									}
									}
									else if (inputArray[2].equalsIgnoreCase("toggle")){
										if(plr.hasPermission("rwelcome.toggle")){
										 if (inputIndex >= 3){
											 if(inputArray[3].equalsIgnoreCase("messageon")){
												 plr.sendMessage("Welcome message toggled on!");
													Togglewelmessage = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("messageoff")){
												 plr.sendMessage("Welcome message toggled off!");
												 Togglewelmessage = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("dimensionon")){
													plr.sendMessage("Dimension message toggled on!");
													Toggledimension = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("dimensionoff")){
													plr.sendMessage("Dimension message toggled off!");
													Toggledimension = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("bedenteron")){
													plr.sendMessage("Bed enter message toggled on!");
													Togglebedenter = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("bedenteroff")){
													plr.sendMessage("Bed enter message toggled off!");
													Togglebedenter = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("bedexiton")){
													plr.sendMessage("Bed exit message toggled on!");
													Togglebedexit = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("bedexitoff")){
													plr.sendMessage("Bed exit message toggled off!");
													Togglebedexit = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("plrdeathon")){
													plr.sendMessage("Player death/killer messages toggled on!");
													Toggleplrdeath = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("plrdeathoff")){
													plr.sendMessage("Player death/killer messages toggled off!");
													Toggleplrdeath = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("anidamgeon")){
													plr.sendMessage("Animal attack message toggled on!");
													Toggleanidamage = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("anidamageoff")){
													plr.sendMessage("Animal attack message toggled off!");
													Toggleanidamage = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("respawnon")){
													plr.sendMessage("Respawn message toggled on!");
													Togglerespawn = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("respawnoff")){
												 plr.sendMessage("Respawn message toggled off!");
												 Togglerespawn = true;
											 }else if (inputArray[3].equalsIgnoreCase("clockon")){
												 plr.sendMessage("Clock on login toggled on!");
												 Toggleclock = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("clockoff")){
												 plr.sendMessage("Clock on login toggled off!");
												 Toggleclock = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("hangingoff")){
												 plr.sendMessage("Attack hanging entity message toggled off!");
												 Togglehanging = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("hangingon")){
												 plr.sendMessage("Attack hanging entity message toggled on!");
												 Togglehanging = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("teleportoff")){
												 plr.sendMessage("Teleport message toggled off!");
												 Toggleteleport = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("teleporton")){
												 plr.sendMessage("Teleport message toggled on!");
												 Toggleteleport = true;
											 }
											 else if (inputArray[3].equalsIgnoreCase("potionoff")){
												 plr.sendMessage("Potion message toggled off!");
												 Togglepotioneffect = false;
											 }
											 else if (inputArray[3].equalsIgnoreCase("potionon")){
												 plr.sendMessage("Potion message toggled on!");
												 Togglepotioneffect = true;
											 }
										 else{	 
										plr.sendMessage("Message toggling help page:");
										plr.sendMessage("Toggle messageon/off toggles welcome message");
										plr.sendMessage("Toggle dimensionon/off toggles dimension change message");
										plr.sendMessage("Toggle bedon/off toggles bed message(s)");
										plr.sendMessage("Toggle plrdeathon/off toggles player death message");
										plr.sendMessage("Toggle anidamageon/off toggles animal damage");
										plr.sendMessage("Toggle hangingon/off toggles hanging entity message");
										plr.sendMessage("Toggle teleporton/off toggles teleport message");
										plr.sendMessage("Toggle potionon/off toggles potion effect message");
										plr.sendMessage("Toggle respawnon/off toggles respawn message");
										 }
										 }else{
												plr.sendMessage("Message toggling help page:");
												plr.sendMessage("Toggle messageon/off toggles welcome message");
												plr.sendMessage("Toggle dimensionon/off toggles dimension change message");
												plr.sendMessage("Toggle bedon/off toggles bed message(s)");
												plr.sendMessage("Toggle plrdeathon/off toggles player death message");
												plr.sendMessage("Toggle anidamageon/off toggles animal damage");
												plr.sendMessage("Toggle respawnon/off toggles respawn message"); 
												plr.sendMessage("Toggle hangingon/off toggles hanging entity message");
												plr.sendMessage("Toggle teleporton/off toggles teleport message");
												plr.sendMessage("Toggle potionon/off toggles potion effect message");
												plr.sendMessage("Toggle respawnon/off toggles respawn message");
										 }
										}else
										{
											plr.sendMessage(ChatColor.RED + "You do not have permission to use this command");
										}
									}
									else {
							plr.sendMessage("This is the Rainbow Welcome command center:");
							plr.sendMessage("/rw display displays 'Welcome to the server'");
							plr.sendMessage("/rw displaycustom displays your custom welcome message");
							plr.sendMessage("/rw broadcast broadcasts 'Welcome to the server! to all players online");
							plr.sendMessage("/rw broadcastcustom broadcasts your custom message to all players online");
							plr.sendMessage("/rw toggle views the toggle help page");
							plr.sendMessage("/rw mes goes to the message changing help page");
					                }						
					            }else{
										plr.sendMessage("This is the Rainbow Welcome command center:");
										plr.sendMessage("/rw display displays 'Welcome to the server'");
										plr.sendMessage("/rw displaycustom displays your custom welcome message");
										plr.sendMessage("/rw broadcast broadcasts 'Welcome to the server! to all players online");
										plr.sendMessage("/rw broadcastcustom broadcasts your custom message to all players online");
										plr.sendMessage("/rw toggle views the toggle help page");
										plr.sendMessage("/rw mes goes to the message changing help page");
					            }
					}
			            
							else if(inputArray[1].equalsIgnoreCase("/mes")){
								ei.isCancelled = true;
								if(plr.hasPermission("rwelcome.mes")){
									 if (inputIndex >= 2){
										 if (inputIndex >= 3){
										 if(inputArray[2].equalsIgnoreCase("welmessage")){
											 file = new File(pluginDir + File.separatorChar + "welcomemessage.txt");
											 changemessage(plr, message);
											 }
											 else if (inputArray[2].equalsIgnoreCase("dimmessage")){
												 file = new File(pluginDir + File.separatorChar + "dimensionmessage.txt");
												 changemessage(plr, message);
											 }
											 else if (inputArray[2].equalsIgnoreCase("bedentermessage")){
												 file = new File(pluginDir + File.separatorChar + "bedentermessage.txt");
												 changemessage(plr, message);
										 }
											 else if (inputArray[2].equalsIgnoreCase("animalmessage")){
												 file = new File(pluginDir + File.separatorChar + "animalmessage.txt");
												 changemessage(plr, message);
											 	}
										 else if (inputArray[2].equalsIgnoreCase("bedexitmessage")){
											 file = new File(pluginDir + File.separatorChar + "bedexitmessage.txt");
											 changemessage(plr, message);
										 }
										 else if (inputArray[2].equalsIgnoreCase("deathmessage")){
											 file = new File(pluginDir + File.separatorChar + "deathmessage.txt");
											 changemessage(plr, message);
										 }
										 else if (inputArray[2].equalsIgnoreCase("respawnmessage")){
											 file = new File(pluginDir + File.separatorChar + "respawnmessage.txt");
											 changemessage(plr, message);
											 }
										 else if (inputArray[2].equalsIgnoreCase("hangingmessage")){
											 file = new File(pluginDir + File.separatorChar + "attackhangingmessage.txt");
											 changemessage(plr, message);
										 }
										 else if (inputArray[2].equalsIgnoreCase("teleportmessage")){
											 file = new File(pluginDir + File.separatorChar + "teleportmessage.txt");
											 changemessage(plr, message);
										 }
										 else if (inputArray[2].equalsIgnoreCase("potionmessage")){
											 file = new File(pluginDir + File.separatorChar + "potionmessage.txt");
											 changemessage(plr, message);
										 }
										 else{
											 plr.sendMessage("Message changing help page:");
											 plr.sendMessage("mes welmessage <message> writes a new welcome message");
											 plr.sendMessage("mes dimmessage <message> writes a new dimension message");
											 plr.sendMessage("mes bedentermessage <message> writes a new bed enter message");
											 plr.sendMessage("mes animalmessage <message> writes a new animal message");
											 plr.sendMessage("mes bedexitmessage <message> writes a new bed exit message");
											 plr.sendMessage("mes deathmessage <message> writes a new death message");
											 plr.sendMessage("mes respawnmessage <message> writes a new respawn message");
											 plr.sendMessage("mes hangingmessage <message> writes a new hanging entity message");
											 plr.sendMessage("mes teleportmessage <message> writes a new teleport message");
											 plr.sendMessage("mes potionmessage <message> writes a new potion message");
										 }
										 }else{
											plr.sendMessage(ChatColor.RED + "There is no message! The file will not be changed!");
											plr.sendMessage(ChatColor.RED + "If you don't want there to be the desired message, do /rw toggle and toggle the undesired message off!");
											 }
								}else{
									 plr.sendMessage("Message changing help page:");
									 plr.sendMessage("mes welmessage <message> writes a new welcome message");
									 plr.sendMessage("mes dimmessage <message> writes a new dimension message");
									 plr.sendMessage("mes bedentermessage <message> writes a new bed enter message");
									 plr.sendMessage("mes animalmessage <message> writes a new animal message");
									 plr.sendMessage("mes bedexitmessage <message> writes a new bed exit message");
									 plr.sendMessage("mes deathmessage <message> writes a new death message");
									 plr.sendMessage("mes respawnmessage <message> writes a new respawn message");
									 plr.sendMessage("mes hangingmessage <message> writes a new hanging entity message");
									 plr.sendMessage("mes teleportmessage <message> writes a new teleport message");
									 plr.sendMessage("mes potionmessage <message> writes a new potion message");
								}
								}else{
									plr.sendMessage(ChatColor.RED + "You do not have permission to use this command");
								}
							}
							else if(inputArray[1].equalsIgnoreCase("/sim")){
								ei.isCancelled = true;
					    	if(inputArray[2] == null){
					    		plr.sendMessage("Your current simplicity mode version is: " + version);
					    	}else{
					    		try{	
					    			double d = Double.parseDouble(inputArray[2]);
					    			String s = String.valueOf(d);
					    			String decimal = s.substring(2, s.length());
					    			if(d > 3.2){
					    				plr.sendMessage("You can't go into the future!");
					    				plr.sendMessage("The biggest version is 3.2");
					    			}else if(d < 0.1){
					    				plr.sendMessage("The smallest version is 0.1!");
					    				//Checks if there is only one decimal point
					    			}else if(decimal.length() == 1){
					    				version = d;
					    				plr.sendMessage("New simplicity mode version is: " + version);
					    				File ver = new File(pluginDir + File.separator + "simplicitymode.txt");
					    				BufferedWriter writer = new BufferedWriter(new FileWriter(ver));
					    				writer.write(String.valueOf(version));
					    				writer.close();
					    			}else{
					    				System.out.println("You can only specify a number with one decimal point! You can use .0 to specify a major version only!");
					    			}
					    			
					    		}catch(NumberFormatException e){
					    			plr.sendMessage("You need a proper number!");
					    			} catch (IOException e) {
									plr.sendMessage("Error with writing version to file!");
								}
					    		}
					    	}
					        }
					private String getDate() {
						Date date = new Date();
						return date.toString();
					}
					private void changemessage(MC_Player plr, String message) {
						String inputLine = null;
					    String inputField = null;
					    String inputArray[] = new String [20];
					    int inputIndex = 0;
					    int charIndex = 0;	
					    inputIndex = 0;
					    inputLine = message.trim();		
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
					    
						 if(inputArray[3].equalsIgnoreCase("check")){
							 plr.sendMessage("Message is called sucessfully!");
						 }else{	 
							 try {
								FileWriter fw = new FileWriter(file);
								BufferedWriter bw = new BufferedWriter(fw);
							 if(inputIndex >=29){
						 String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16] + " " + inputArray[17] + " " + inputArray[18] + " " + inputArray[19] + " " + inputArray[20] + " " + inputArray[21] + " " + inputArray[22] + " " + inputArray[23] + " " + inputArray[24] + " " + inputArray[25] + " " + inputArray[26] + " " + inputArray[27] + " " + inputArray[28] + " " + inputArray[29];
						    bw.write(newmessage);
					             
					            }      
					        
					 
							 else if(inputIndex >=28){
								 String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16] + " " + inputArray[17] + " " + inputArray[18] + " " + inputArray[19] + " " + inputArray[20] + " " + inputArray[21] + " " + inputArray[22] + " " + inputArray[23] + " " + inputArray[24] + " " + inputArray[25] + " " + inputArray[26] + " " + inputArray[27] + " " + inputArray[28];
							     bw.write(newmessage);
							 }
						 	else if(inputIndex >=27){
								 String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16] + " " + inputArray[17] + " " + inputArray[18] + " " + inputArray[19] + " " + inputArray[20] + " " + inputArray[21] + " " + inputArray[22] + " " + inputArray[23] + " " + inputArray[24] + " " + inputArray[25] + " " + inputArray[26] + " " + inputArray[27];
								 bw.write(newmessage);
							 }
						 	else if(inputIndex >=26){
								 String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16] + " " + inputArray[17] + " " + inputArray[18] + " " + inputArray[19] + " " + inputArray[20] + " " + inputArray[21] + " " + inputArray[22] + " " + inputArray[23] + " " + inputArray[24] + " " + inputArray[25] + " " + inputArray[26];
								 bw.write(newmessage);
							 }
						 	else if(inputIndex >=25){
								 String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16] + " " + inputArray[17] + " " + inputArray[18] + " " + inputArray[19] + " " + inputArray[20] + " " + inputArray[21] + " " + inputArray[22] + " " + inputArray[23] + " " + inputArray[24] + " " + inputArray[25];
								 bw.write(newmessage);
							 }
						 	else if(inputIndex >=24){
								 String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16] + " " + inputArray[17] + " " + inputArray[18] + " " + inputArray[19] + " " + inputArray[20] + " " + inputArray[21] + " " + inputArray[22] + " " + inputArray[23] + " " + inputArray[24];
								 bw.write(newmessage);
							 }
						 	else if(inputIndex >=23){
								 String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16] + " " + inputArray[17] + " " + inputArray[18] + " " + inputArray[19] + " " + inputArray[20] + " " + inputArray[21] + " " + inputArray[22] + " " + inputArray[23];
								 bw.write(newmessage);
							 }
						 	else if(inputIndex >=22){
								 String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16] + " " + inputArray[17] + " " + inputArray[18] + " " + inputArray[19] + " " + inputArray[20] + " " + inputArray[21] + " " + inputArray[22];
								 bw.write(newmessage);
							 }				
						 	else if(inputIndex >=21){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16] + " " + inputArray[17] + " " + inputArray[18] + " " + inputArray[19] + " " + inputArray[20] + " " + inputArray[21];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=20){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16] + " " + inputArray[17] + " " + inputArray[18] + " " + inputArray[19] + " " + inputArray[20];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=19){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16] + " " + inputArray[17] + " " + inputArray[18] + " " + inputArray[19];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=18){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16] + " " + inputArray[17] + " " + inputArray[18];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=17){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16] + " " + inputArray[17];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=16){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15] + " " + inputArray[16];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=15){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14] + " " + inputArray[15];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=14){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13] + " " + inputArray[14];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=13){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12] + " " + inputArray[13];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=12){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11] + " " + inputArray[12];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=11){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10] + " " + inputArray[11];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=10){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9] + " " + inputArray[10];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=9){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8] + " " + inputArray[9];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=8){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7] + " " + inputArray[8];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=7){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6] + " " + inputArray[7];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=6){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5] + " " + inputArray[6];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=5){
						 		String newmessage = inputArray[3] + " " + inputArray[4] + " " + inputArray[5];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=4){
						 		String newmessage = inputArray[3] + " " + inputArray[4];
						 		bw.write(newmessage);
						 	}
							else if(inputIndex >=3){
						 		String newmessage = inputArray[3];
						 		bw.write(newmessage);
						 	}else{
						 		if(plr !=null){
						 		plr.sendMessage(ChatColor.RED + "Either there was no message or the message exceeded 27 words. Please edit the file manually and then tell me the amount of words you would like to have as the limit");	
						 		}else{
						 		System.out.println("Either there was no message or the message exceeded 27 words. Please edit the file manually and then tell me the amount of words you would like to have as the limit");
						 		}
						 		
						 		}
							 bw.close();
					} catch (IOException e) {
					            e.printStackTrace();
					        } finally {
					        	server.broadcastMessage("Welcome message edited!");
					        }
					}
					}
					
					public void AnimalMessage(MC_Player plr, MC_Entity ent){
					String entName = ent.getName();
					if(ent instanceof MC_Player) {
						System.out.println(entName + "got attacked by " + plr + ". Sending animal attack message.");
					}
					String animalattackmessage;
					try{
						reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "animalmessage.txt"));
						while ((animalattackmessage = reader.readLine()) != null) {
							String playermessage = animalattackmessage.replaceAll("%PLAYER%", plr.getName()).replaceAll("%WORLD%", plr.getWorld().getName()).replaceAll("%ECONOMY%", String.valueOf(plr.getEconomyBalance()) ).replaceAll("%IP%", plr.getIPAddress()).replaceAll("&TIME%", getDate());
							String colouredformattedmessage = playermessage.replaceAll("&b,", ChatColor.AQUA).replaceAll("&0,", ChatColor.BLACK).replaceAll("&9,", ChatColor.BLUE).replaceAll("&l,", ChatColor.BOLD).replaceAll("&3,", ChatColor.DARK_AQUA).replaceAll("&1,", ChatColor.DARK_BLUE).replaceAll("&8,", ChatColor.DARK_GRAY).replaceAll("&2,", ChatColor.DARK_GREEN).replaceAll("&5,", ChatColor.DARK_PURPLE).replaceAll("&4,", ChatColor.DARK_RED).replaceAll("&6,", ChatColor.GOLD).replaceAll("&7,", ChatColor.GRAY).replaceAll("&a,", ChatColor.GREEN).replaceAll("&o,", ChatColor.ITALIC).replaceAll("&d,", ChatColor.LIGHT_PURPLE).replaceAll("&k,", ChatColor.MAGIC).replaceAll("&c,", ChatColor.RED).replaceAll("&r,", ChatColor.RESET).replaceAll("&m", ChatColor.STRIKETHROUGH).replaceAll("&n,", ChatColor.UNDERLINE).replaceAll("&f,", ChatColor.WHITE).replaceAll("&e,", ChatColor.YELLOW);
							plr.sendMessage(colouredformattedmessage);
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
					}
