package Rulevoter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import PluginReference.*;

public class MyPlugin extends PluginBase{
public static MC_Server server = null;
String pluginDir = "plugins_mod" + File.separatorChar + "RuleVoter";
public boolean TogglePVP = true;
public boolean Togglealreadyvoted = true;
public boolean Togglemobspawn = false;
public int time = 5 * 60 * 1000;
String rule = "gamemode";
public void onStartup(MC_Server argServer)
{	
	new File(pluginDir).mkdir();
	System.out.println("RuleVoter starting! Lets hope it works! :)");
	server = argServer;
	
	try{
		File dir = new File(pluginDir + File.separatorChar + "yes.txt");
		if (dir.createNewFile()){
			System.out.println("The file yes.txt is created!");
			FileWriter fw = new FileWriter(dir);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("0");
			bw.close();
		}else{
			System.out.println("yes.txt aready created.");
		}
		}
			catch (IOException prob){
				prob.printStackTrace();
			}
	try{
		File dir = new File(pluginDir + File.separatorChar + "list.txt");
		if (dir.createNewFile()){
			System.out.println("The file list.txt is created!");
		}else{
			System.out.println("list.txt aready created.");
		}
		}
			catch (IOException prob){
				prob.printStackTrace();
			}
	try{
		File dir = new File(pluginDir + File.separatorChar + "timerlength.txt");
		if (dir.createNewFile()){
			System.out.println("The file timerlength.txt is created!");
		}else{
			System.out.println("timerlength.txt aready created.");
		}
		}
			catch (IOException prob){
				prob.printStackTrace();
			}
	try{
		File dir = new File(pluginDir + File.separatorChar + "no.txt");
		if (dir.createNewFile()){
			System.out.println("The file no.txt has created!");
			FileWriter fw = new FileWriter(dir);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("0");
			bw.close();
		}else{
			System.out.println("no.txt aready created.");
		}
		}
			catch (IOException prob){
				prob.printStackTrace();
			}
	try{
		File dir = new File(pluginDir + File.separatorChar + "rule.txt");
		if (dir.createNewFile()){
			System.out.println("The file rule.txt has been created!");
			FileWriter fw = new FileWriter(dir);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("1");
			bw.close();
			
			System.out.println("File Written");
		}else{
			System.out.println("rule.txt already created");
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
}
public void onShutdown(){
	System.out.println("RuleVoter has finished casts! Resetting values");
	try{
		File dir = new File(pluginDir + File.separatorChar + "yes.txt");
			FileWriter fw = new FileWriter(dir);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("0");
			bw.close();
		}
			catch (IOException prob){
				prob.printStackTrace();
			}
	try{
		File dir = new File(pluginDir + File.separatorChar + "no.txt");
			FileWriter fw = new FileWriter(dir);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("0");
			bw.close();
		}
			catch (IOException prob){
				prob.printStackTrace();
			}
	System.out.println("Values reset!");
}
public PluginInfo getPluginInfo() 
{ 
	PluginInfo info = new PluginInfo();
	info.description = "Change gamerules by voting: version 0.5 started";
	return info;
}
public void onServerFullyLoaded()
{
	System.out.println("RuleVoter ready!");
	}
int Switch;
int switcher;
public static final int zero = 0;
public static final int one = 1;
public static final int two = 2;
public static final int three = 3;
public static final int four = 4;
public static final int five = 5;
public static final int six = 6;
public static final int seven = 7;
public static final int eight = 8;
public static final int nine = 9;
public static final int ten = 10;
public static final int eleven = 11;
public static final int twelve = 12;
public static final int thirteen = 13;
public static final int fourteen = 14;
public static final int fifteen = 15;
public static final int sixteen = 16;
public static final int seventeen = 17;
public static final int eighteen = 18;
public static final int nineteen = 19;
public static final int twenty = 20;
public void onPlayerJoin(MC_Player plr) 
{
BufferedReader reader = null;
String rules = null;
	try{	
		reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "rule.txt"));
		while ((rules = reader.readLine()) != null) {
			switch(rules){
			case "1":
				rule = "gamemode";
				plr.sendMessage("If you want it to be survival, type /yes");
				plr.sendMessage("If you want it to be creative, type /no");
				break;
				
			case "2":
				rule = "PVP";
				plr.sendMessage("If you want PVP on, type /yes");
				plr.sendMessage("If you want PVP off, type /no");
				break;
			case "3":
				rule = "MobSpawn";
				plr.sendMessage("If you want mob spawn on, type /yes");
				plr.sendMessage("If you want mob spawn off, type /no");
				break;
			case "4":
				rule = "daynight";
				plr.sendMessage("If you want it to be day, type /yes");
				plr.sendMessage("If you want it to be night, type /no");
				break;
				default:
					plr.sendMessage("RuleVoter might be having issues");
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
public void onAttemptAttackEntity(MC_Player plr, MC_Entity ent, MC_EventInfo ei) 
{
	if(ent.getType() == MC_EntityType.PLAYER){
		if(TogglePVP){
			ei.isCancelled = true;
		}else{
		plr.sendMessage("Pvp is on! But RuleVoter can fix that!");
		plr.sendMessage("If you are voting for PVP, type /no and PVP will go off!");
	}
	}			
}
public void onAttemptEntitySpawn(MC_Entity ent, MC_EventInfo ei){
	if(Togglemobspawn)ei.isCancelled = true;
}
public void onPlayerInput(MC_Player plr, String msg, MC_EventInfo ei){
	if(ei.isCancelled){
		return;
	}
	if(msg.equalsIgnoreCase("/votetimerrepeat")){
		ei.isCancelled = true;
		if(plr.hasPermission("rv.next")){
			time = calculatetime();
			if(time == 0) plr.sendMessage("Error with timer. Not putting it on.");
			else{
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			@Override
			public void run(){
				doNext();
			}
		};
		timer.scheduleAtFixedRate(task, time, time);
			}
	}
	}
	if(msg.equalsIgnoreCase("/votetimer")){
		ei.isCancelled = true;
		if(plr.hasPermission("rv.next")){
			time = calculatetime();
			if(time == 0) plr.sendMessage("Error with timer. Not putting it on.");
			else{
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			@Override
			public void run(){
				doNext();
			}
		};
		timer.schedule(task, time);
			}
	}
	}
	if (msg.equalsIgnoreCase("/yes")) {
		ei.isCancelled = true;
		if(plr.hasPermission("rv.vote")){
	    plr.sendMessage("You have voted yes!");
	    BufferedReader reader = null;
	    Set<String> rules = new HashSet<String>();
	    try {
	        reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "list.txt"));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            rules.add(line);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    if (rules.contains(plr.getName())) {
	        plr.sendMessage("You have already voted");
	    } else {
	        BufferedWriter writer = null;
	        try {
	            writer = new BufferedWriter(new FileWriter(pluginDir + File.separatorChar + "list.txt"));
	            writer.newLine();
	            writer.write(plr.getName());
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                writer.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        addyes(plr);
	    }
		}else{
			plr.sendMessage("You do not have permission to vote.");
		}
	}
	if(msg.equalsIgnoreCase("/no")) {
	    ei.isCancelled = true;
	    if(plr.hasPermission("rv.vote")){
	    	plr.sendMessage("You have voted no!");
	    BufferedReader reader = null;
	    Set<String> rules = new HashSet<String>();
	    try {
	        reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "list.txt"));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            rules.add(line);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    if (rules.contains(plr.getName())) {
	        plr.sendMessage("You have already voted");
	    } else {
	        BufferedWriter writer = null;
	        try {
	            writer = new BufferedWriter(new FileWriter(pluginDir + File.separatorChar + "list.txt"));
	            writer.newLine();
	            writer.write(plr.getName());
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                writer.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        addno(plr);
	    }
	    }else{
	    	plr.sendMessage("You do not have permission to vote.");
	    }  
	}
	if(msg.equalsIgnoreCase("/rule")){
		ei.isCancelled = true;
		BufferedReader reader = null;
		String rules = null;
			try{	
				reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "rule.txt"));
				while ((rules = reader.readLine()) != null) {
					switch(rules){
					case "1":
						plr.sendMessage("If you want it to be survival, type /yes");
						plr.sendMessage("If you want it to be creative, type /no");
						break;
						
					case "2":
						plr.sendMessage("If you want PVP on, type /yes");
						plr.sendMessage("If you want PVP off, type /no");
						break;
					case "3":
						plr.sendMessage("If you want mob spawn on, type /yes");
						plr.sendMessage("If you want mob spawn off, type /no");
						break;
					case "4":
						plr.sendMessage("If you want it to be day, type /yes");
						plr.sendMessage("If you want it to be night, type /no");
						break;
						default:
							plr.sendMessage("RuleVoter might be having issues");
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
	
	if(msg.equalsIgnoreCase("/next")){
		ei.isCancelled = true;
		if(plr.hasPermission("rv.next")){
			doNext();
		}else{
			plr.sendMessage("You do not have permission to use this command");
		}	
}
}
public void onConsoleInput(String msg, MC_EventInfo ei){
	if(msg.equalsIgnoreCase("votetimerrepeat")){
		ei.isCancelled = true;
			time = calculatetime();
			if(time == 0) System.out.println("Error with timer. Not putting it on.");
			else{
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			@Override
			public void run(){
				doNext();
			}
		};
		timer.scheduleAtFixedRate(task, time, time);
			}
	}
	if(msg.equalsIgnoreCase("votetimer")){
		ei.isCancelled = true;
			time = calculatetime();
			if(time == 0) System.out.println("Error with timer. Not putting it on.");
			else{
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			@Override
			public void run(){
				doNext();
			}
		};
		timer.schedule(task, time);
			}
	}
	if(msg.equalsIgnoreCase("rule")){
		ei.isCancelled = true;
		BufferedReader reader = null;
		String rules = null;
			try{	
				reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "rule.txt"));
				while ((rules = reader.readLine()) != null) {
					switch(rules){
					case "1":
						System.out.println("If you want it to be survival, type /yes");
						System.out.println("If you want it to be creative, type /no");
						break;
						
					case "2":
						System.out.println("If you want PVP on, type /yes");
						System.out.println("If you want PVP off, type /no");
						break;
					case "3":
						System.out.println("If you want mob spawn on, type /yes");
						System.out.println("If you want mob spawn off, type /no");
						break;
					case "4":
						System.out.println("If you want it to be day, type /yes");
						System.out.println("If you want it to be night, type /no");
						break;
						default:
							System.out.println("RuleVoter might be having issues");
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
	
	if(msg.equalsIgnoreCase("next")){
		ei.isCancelled = true;
		doNext();
}
}
/**
 * Collects the votes for yes (defaultgamemode survival and PVP on)
 * @param server the server broadcasting the message
 * @return
 */
public int checkyes(MC_Server server) {
	BufferedReader reader = null;
	String checkyes;
	try{
		reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "yes.txt"));
		while ((checkyes = reader.readLine()) != null) {
			server.broadcastMessage(checkyes + " people have voted yes");
			Switch = Integer.parseInt(checkyes);
		} 
	}catch (NumberFormatException nfe) {
			      System.out.println("Unable to convert value from file! " + Switch);
			    } catch (IOException e) {
			      e.printStackTrace();
			    } finally {
			      try {
			        if (reader != null)
			          reader.close();
			      } catch (IOException ex) {
			        ex.printStackTrace();
			      }
			switch(Switch){
			case zero:
				Switch = 0;
				break;
				
			case one:
				Switch = 1;
				break;
			
			case two:
				Switch = 2;
				break;
				
			case three:
				Switch = 3;
				break;
			
			case four:
				Switch = 4;
				break;
				
			case five:
				Switch = 5;
				break;
				
			case six:
				Switch = 6;
				break;
				
			case seven:
				Switch = 7;
				break;
				
			case eight:
				Switch = 8;
				break;
				
			case nine:
				Switch = 9;
				break;
				
			case ten:
				Switch = 10;
				break;
				
			case eleven:
				Switch = 11;
				break;
				
			case twelve:
				Switch = 12;
				break;
				
			case thirteen:
				Switch = 13;
				break;
				
			case fourteen:
				Switch = 14;
				break;
				
			case fifteen:
				Switch = 15;
				break;
				
			case sixteen:
				Switch = 16;
				break;
				
			case seventeen:
				Switch = 17;
				break;
				
			case eighteen:
				Switch = 18;
				break;
				
			case nineteen:
				Switch = 19;
				break;
				
			case twenty:
				Switch = 20;
				break;
				
			default:
				System.out.println("RuleVoter is having probelms with this 'number': " + Switch);
			}
		}return Switch;
}
/**
 * Checks the votes for no (defaultgamemode creative and PVP off)
 * @param server the server broadcasting the messages
 * @return
 */
public int checkno(MC_Server server) {
	BufferedReader reader = null;
	String checkno;
	try{
		reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "no.txt"));
		while ((checkno = reader.readLine()) != null) {
			server.broadcastMessage(checkno + " people have voted no");
			switcher = Integer.parseInt(checkno);
		} 
	}catch (NumberFormatException nfe) {
			      System.out.println("Unable to convert value from file! " + switcher);
			    } catch (IOException e) {
			      e.printStackTrace();
			    } finally {
			      try {
			        if (reader != null)
			          reader.close();
			      } catch (IOException ex) {
			        ex.printStackTrace();
			      }
			switch(switcher){
			case zero:
				switcher = 0;
				break;
				
			case one:
				switcher = 1;
				break;
			
			case two:
				switcher = 2;
				break;
				
			case three:
				switcher = 3;
				break;
			
			case four:
				switcher = 4;
				break;
				
			case five:
				switcher = 5;
				break;
				
			case six:
				switcher = 6;
				break;
				
			case seven:
				switcher = 7;
				break;
				
			case eight:
				switcher = 8;
				break;
				
			case nine:
				switcher = 9;
				break;
				
			case ten:
				switcher = 10;
				break;
				
			case eleven:
				switcher = 11;
				break;
				
			case twelve:
				switcher = 12;
				break;
				
			case thirteen:
				switcher = 13;
				break;
				
			case fourteen:
				switcher = 14;
				break;
				
			case fifteen:
				switcher = 15;
				break;
				
			case sixteen:
				switcher = 16;
				break;
				
			case seventeen:
				switcher = 17;
				break;
				
			case eighteen:
				switcher = 18;
				break;
				
			case nineteen:
				switcher = 19;
				break;
				
			case twenty:
				switcher = 20;
				break;
				
			default:
				System.out.println("RuleVoter is having probelms with this 'number': " + switcher);
			}
		}return switcher;
	}
/**
 * Adds 1 to the value in no.txt
 * @param plr The player who executed the command. This variable does nothing, may remove from the signature.
 */
public void addno(MC_Player plr){
	BufferedReader reader = null;
	try{
		reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "no.txt"));
		String line ="";
		List<String> list = new ArrayList<String>();
		          while((line = reader.readLine())!= null){
		                 list.add(line);
		         }
		String bedno =  list.get(list.size()-1); // last line
		int newbedno = Integer.parseInt(bedno);
		
		try{
			BufferedWriter writer = null;
			writer = new BufferedWriter(new FileWriter(pluginDir + File.separatorChar + "no.txt"));
			switch(newbedno){
			case zero:
				writer.write("1");
				break;
			case one:
				writer.write("2");
				break;
			case two:
				writer.write("3");
				break;
			case three:
				writer.write("4");
				break;
			case four:
				writer.write("5");
				break;
			case five:
				writer.write("6");
				break;
			case six:
				writer.write("7");
				break;
			case seven:
				writer.write("8");
				break;
			case eight:
				writer.write("9");
				break;
			case nine:
				writer.write("10");
				break;
			case ten:
				writer.write("11");
				break;
			case eleven:
				writer.write("12");
				break;
			case twelve:
				writer.write("13");
				break;
			case thirteen:
				writer.write("14");
				break;
			case fourteen:
				writer.write("15");
				break;
			case fifteen:
				writer.write("16");
				break;
			case sixteen:
				writer.write("17");
				break;
			case seventeen:
				writer.write("18");
				break;
			case eighteen:
				writer.write("19");
				break;
			case nineteen:
				writer.write("20");
				break;
			case twenty:
				writer.write("0");
				if((rule = "gamemode")!=null){
					server.executeCommand("defaultgamemode 1");	
					}else if ((rule = "PVP")!=null){
					TogglePVP = true;	
					}	
				break;
			}
			writer.close();
		}catch (IOException e){
			e.printStackTrace();
		}
		}catch (IOException e){
			e.printStackTrace();
		}
			}
/**
 * Adds 1 to the value in yes.txt
 * @param plr The player who executed the command. This variable does nothing, may remove from the signature.
 */
public void addyes(MC_Player plr){
	BufferedReader reader = null;
	try{
		reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "yes.txt"));
		String line ="";
		List<String> list = new ArrayList<String>();
		          while((line = reader.readLine())!= null){
		                 list.add(line);
		         }
		String bedno =  list.get(list.size()-1); // last line
		int newbedno = Integer.parseInt(bedno);
		
		try{
			BufferedWriter writer = null;
			writer = new BufferedWriter(new FileWriter(pluginDir + File.separatorChar + "yes.txt"));
			switch(newbedno){
			case zero:
				writer.write("1");
				break;
			case one:
				writer.write("2");
				break;
			case two:
				writer.write("3");
				break;
			case three:
				writer.write("4");
				break;
			case four:
				writer.write("5");
				break;
			case five:
				writer.write("6");
				break;
			case six:
				writer.write("7");
				break;
			case seven:
				writer.write("8");
				break;
			case eight:
				writer.write("9");
				break;
			case nine:
				writer.write("10");
				break;
			case ten:
				writer.write("11");
				break;
			case eleven:
				writer.write("12");
				break;
			case twelve:
				writer.write("13");
				break;
			case thirteen:
				writer.write("14");
				break;
			case fourteen:
				writer.write("15");
				break;
			case fifteen:
				writer.write("16");
				break;
			case sixteen:
				writer.write("17");
				break;
			case seventeen:
				writer.write("18");
				break;
			case eighteen:
				writer.write("19");
				break;
			case nineteen:
				writer.write("20");
				break;
			case twenty:
				writer.write("0");
				if((rule = "gamemode")!=null){
					server.executeCommand("defaultgamemode 0");	
					}else if ((rule = "PVP")!=null){
					TogglePVP = false;	
					}	
				break;
			}
			writer.close();
		}catch (IOException e){
			e.printStackTrace();
		}
		}catch (IOException e){
			e.printStackTrace();
		}
}
public int calculatetime(){
	File file = new File(pluginDir + File.separatorChar + "timerlength.txt");
	int number = 0;
	System.out.println("Starting timer!");
	BufferedReader reader = null;
	String timera;
	try{
		reader = new BufferedReader(new FileReader(file));
		while((timera = reader.readLine())!=null){
		if(timera.endsWith("d")){
			if(timera.equalsIgnoreCase("1d")){
					 number = 1*24*60*60*1000;
			}else if(timera.equalsIgnoreCase("2d")){
					 number = 2*24*60*60*1000;
			}else if(timera.equalsIgnoreCase("3d")){
				 number = 3*24*60*60*1000;
			}else if(timera.equalsIgnoreCase("4d")){
				 number = 4*24*60*60*1000;
			}else if(timera.equalsIgnoreCase("5d")){
				 number = 5*24*60*60*1000;
			}else if(timera.equalsIgnoreCase("6d")){
				 number = 6*24*60*60*1000;
			}else if(timera.equalsIgnoreCase("7d")){
				 number = 1*24*60*60*1000;
			    }			
		}else if(timera.endsWith("h")){
				if(timera.equalsIgnoreCase("1h")){
					number = 1*60*60*1000;
				}else if(timera.equalsIgnoreCase("2h")){
					number = 2*60*60*1000;
				}else if(timera.equalsIgnoreCase("3h")){
					number = 3*60*60*1000;
				}else if(timera.equalsIgnoreCase("4h")){
					number = 4*60*60*1000;
				}else if(timera.equalsIgnoreCase("5h")){
				 number = 5*60*60*1000;
			}else if (timera.equalsIgnoreCase("6h")){
				number = 6*60*60*1000;
			}else if (timera.equalsIgnoreCase("7h")){
				number = 7*60*60*1000;
			}else if (timera.equalsIgnoreCase("8h")){
				number = 8*60*60*1000;
			}else if (timera.equalsIgnoreCase("9h")){
				number = 9*60*60*1000;
			}else if (timera.equalsIgnoreCase("10h")){
				number = 10*60*60*1000;
			}else if (timera.equalsIgnoreCase("11h")){
				number = 11*60*60*1000;
			}else if (timera.equalsIgnoreCase("12h")){
				number = 12*60*60*1000;
			}else if (timera.equalsIgnoreCase("13h")){
				number = 13*60*60*1000;
			}else if (timera.equalsIgnoreCase("14h")){
				number = 14*60*60*1000;
			}else if (timera.equalsIgnoreCase("15h")){
				number = 15*60*60*1000;
			}else if (timera.equalsIgnoreCase("16h")){
				number = 16*60*60*1000;
			}else if (timera.equalsIgnoreCase("17h")){
				number = 17*60*60*1000;
			}else if (timera.equalsIgnoreCase("18h")){
				number = 18*60*60*1000;
			}else if (timera.equalsIgnoreCase("19h")){
				number = 19*60*60*1000;
			}else if (timera.equalsIgnoreCase("20h")){
				number = 20*60*60*1000;
			}else if (timera.equalsIgnoreCase("21h")){
				number = 21*60*60*1000;
			}else if (timera.equalsIgnoreCase("22h")){
				number = 22*60*60*1000;
			}else if (timera.equalsIgnoreCase("23h")){
				number = 23*60*60*1000;
			}
		}else if(timera.endsWith("m")){			
				if(timera.equalsIgnoreCase("1m")){
					 number = 1*60*1000;
				}
				else if(timera.equalsIgnoreCase("2m")){
					 number = 2*60*1000;
				}
				else if(timera.equalsIgnoreCase("3m")){
					 number = 3*60*1000;
				}
				else if(timera.equalsIgnoreCase("4m")){
					 number = 4*60*1000;
				}
				else if(timera.equalsIgnoreCase("5m")){
					number = 5*60*1000;
				}
				else if(timera.equalsIgnoreCase("6m")){
					 number = 6*60*1000;
				}
				else if(timera.equalsIgnoreCase("7m")){
					 number = 7*60*1000;
				}
				else if(timera.equalsIgnoreCase("8m")){
					 number = 8*60*1000;
				}
				else if(timera.equalsIgnoreCase("9m")){
					 number = 9*60*1000;
				}
				else if(timera.equalsIgnoreCase("10m")){
					 number = 10*60*1000;
				}
				else if(timera.equalsIgnoreCase("11m")){
					 number = 11*60*1000;
				}
				else if(timera.equalsIgnoreCase("12m")){
					 number = 12*60*1000;
				}
				else if(timera.equalsIgnoreCase("13m")){
					 number = 13*60*1000;
				}
				else if(timera.equalsIgnoreCase("14m")){
					number = 14*60*1000;
				}
				else if(timera.equalsIgnoreCase("15m")){
					 number = 15*60*1000;
				}
				else if(timera.equalsIgnoreCase("16m")){
					 number = 16*60*1000;
				}
				else if(timera.equalsIgnoreCase("17m")){
					 number = 17*60*1000;
				}
				else if(timera.equalsIgnoreCase("18m")){
					 number = 18*60*1000;
				}
				else if(timera.equalsIgnoreCase("19m")){
					 number = 19*60*1000;
				}
				else if(timera.equalsIgnoreCase("20m")){
					 number = 20*60*1000;
				}
				else if(timera.equalsIgnoreCase("21m")){
					 number = 21*60*1000;
				}
				else if(timera.equalsIgnoreCase("22m")){
					 number = 22*60*1000;
				}
				else if(timera.equalsIgnoreCase("23m")){
					 number = 23*60*1000;
				}
				else if(timera.equalsIgnoreCase("24m")){
					 number = 24*60*1000;
				}
				else if(timera.equalsIgnoreCase("25m")){
					 number = 25*60*1000;
				}
				else if(timera.equalsIgnoreCase("26m")){
					 number = 26*60*1000;
				}
				else if(timera.equalsIgnoreCase("27m")){
					 number = 27*60*1000;
				}
				else if(timera.equalsIgnoreCase("28m")){
					 number = 28*60*1000;
				}
				else if(timera.equalsIgnoreCase("29m")){
					 number = 29*60*1000;
				}
				else if(timera.equalsIgnoreCase("30m")){
					 number = 30*60*1000;
				}
				else if(timera.equalsIgnoreCase("31m")){
					 number = 31*60*1000;
				}
				else if(timera.equalsIgnoreCase("32m")){
					 number = 32*60*1000;
				}
				else if(timera.equalsIgnoreCase("33m")){
					 number = 33*60*1000;
				}
				else if(timera.equalsIgnoreCase("34m")){
					 number = 34*60*1000;
				}
				else if(timera.equalsIgnoreCase("35m")){
					 number = 35*60*1000;
				}
				else if(timera.equalsIgnoreCase("36m")){
					 number = 36*60*1000;
				}
				else if(timera.equalsIgnoreCase("37m")){
					 number = 37*60*1000;
				}
				else if(timera.equalsIgnoreCase("38m")){
					 number = 38*60*1000;
				}
				else if(timera.equalsIgnoreCase("39m")){
					number = 39*60*1000;
				}
				else if(timera.equalsIgnoreCase("40m")){
					 number = 40*60*1000;
				}
				else if(timera.equalsIgnoreCase("41m")){
					 number = 41*60*1000;
				}
				else if(timera.equalsIgnoreCase("42m")){
					 number = 42*60*1000;
				}
				else if(timera.equalsIgnoreCase("43m")){
					 number = 43*60*1000;
				}
				else if(timera.equalsIgnoreCase("44m")){
					 number = 44*60*1000;
				}
				else if(timera.equalsIgnoreCase("45m")){
					 number = 45*60*1000;
				}
				else if(timera.equalsIgnoreCase("46m")){
					 number = 46*60*1000;
				}
				else if(timera.equalsIgnoreCase("47m")){
					 number = 47*60*1000;
				}
				else if(timera.equalsIgnoreCase("48m")){
					 number = 48*60*1000;
				}
				else if(timera.equalsIgnoreCase("49m")){
					 number = 49*60*1000;
				}
				else if(timera.equalsIgnoreCase("50m")){
					 number = 50*60*1000;
				}
				else if(timera.equalsIgnoreCase("51m")){
					 number = 51*60*1000;
				}
				else if(timera.equalsIgnoreCase("52m")){
					 number = 52*60*1000;
				}
				else if(timera.equalsIgnoreCase("53m")){
					 number = 53*60*1000;
				}
				else if(timera.equalsIgnoreCase("54m")){
					 number = 54*60*1000;
				}
				else if(timera.equalsIgnoreCase("55m")){
					 number = 55*60*1000;
				}
				else if(timera.equalsIgnoreCase("56m")){
					 number = 56*60*1000;
				}
				else if(timera.equalsIgnoreCase("57m")){
					 number = 57*60*1000;
				}
				else if(timera.equalsIgnoreCase("58m")){
					 number = 58*60*1000;
				}
				else if(timera.equalsIgnoreCase("59m")){
					 number = 36*60*1000;
				}
			}
			else if(timera.endsWith("s")){
				if(timera.equalsIgnoreCase("1s")){
					 number = 1*1000;
				}
				else if(timera.equalsIgnoreCase("2s")){
					 number = 2*1000;
				}
				else if(timera.equalsIgnoreCase("3s")){
					 number = 3*1000;
				}
				else if(timera.equalsIgnoreCase("4s")){
					 number = 4*1000;
				}
				else if(timera.equalsIgnoreCase("5s")){
					 number = 5*1000;
				}
				else if(timera.equalsIgnoreCase("6s")){
					 number = 6*1000;
				}
				else if(timera.equalsIgnoreCase("7s")){
					 number = 7*1000;
				}
				else if(timera.equalsIgnoreCase("8s")){
					 number = 8*1000;
				    	}
				else if(timera.equalsIgnoreCase("9s")){
					 number = 9*1000;
				}
				else if(timera.equalsIgnoreCase("10s")){
					 number = 10*1000;
				}
				else if(timera.equalsIgnoreCase("11s")){
					 number = 11*1000;
					 }
				else if(timera.equalsIgnoreCase("12s")){
					 number = 12*1000;
				}
				else if(timera.equalsIgnoreCase("13s")){
					 number = 13*1000;
				}
				else if(timera.equalsIgnoreCase("14s")){
					 number = 14*1000;
				}
				else if(timera.equalsIgnoreCase("15s")){
					 number = 15*1000;
				}
				else if(timera.equalsIgnoreCase("16s")){
					 number = 16*1000;
				}
				else if(timera.equalsIgnoreCase("17s")){
					 number = 17*1000;
				}
				else if(timera.equalsIgnoreCase("18s")){
					 number = 18*1000;
				}
				else if(timera.equalsIgnoreCase("19s")){
					 number = 19*1000;
				}
				else if(timera.equalsIgnoreCase("20s")){
					 number = 20*1000;
				}
				else if(timera.equalsIgnoreCase("21s")){
					 number = 21*1000;
				}
				else if(timera.equalsIgnoreCase("22s")){
					 number = 22*1000;
				}
				else if(timera.equalsIgnoreCase("23s")){
					 number = 23*1000;
				}
				else if(timera.equalsIgnoreCase("24s")){
					 number = 24*1000;
				}
				else if(timera.equalsIgnoreCase("25s")){
					number = 25*1000;
				}
				else if(timera.equalsIgnoreCase("26s")){
					 number = 26*1000;
				}
				else if(timera.equalsIgnoreCase("27s")){
					 number = 27*1000;
				}
				else if(timera.equalsIgnoreCase("28s")){
					 number = 28*1000;
				}
				else if(timera.equalsIgnoreCase("29s")){
					 number = 29*1000;
				}
				else if(timera.equalsIgnoreCase("30s")){
					 number = 30*1000;
				}
				else if(timera.equalsIgnoreCase("31s")){
					 number = 31*1000;
				}
				else if(timera.equalsIgnoreCase("32s")){
					 number = 32*1000;
				}
				else if(timera.equalsIgnoreCase("33s")){
					 number = 33*1000;
				}
				else if(timera.equalsIgnoreCase("34s")){
					 number = 34*1000;
				}
				else if(timera.equalsIgnoreCase("35s")){
					 number = 35*1000;
				}
				else if(timera.equalsIgnoreCase("36s")){
					 number = 36*1000;
				}
				else if(timera.equalsIgnoreCase("37s")){
					 number = 37*1000;
				}
				else if(timera.equalsIgnoreCase("38s")){
					 number = 38*1000;
				}
				else if(timera.equalsIgnoreCase("39s")){
					 number = 39*1000;
				}
				else if(timera.equalsIgnoreCase("40s")){
					 number = 40*1000;
				}
				else if(timera.equalsIgnoreCase("41s")){
					 number = 41*1000;
				}
				else if(timera.equalsIgnoreCase("42s")){
					 number = 42*1000;
				}
				else if(timera.equalsIgnoreCase("43s")){
					 number = 43*1000;
				}
				else if(timera.equalsIgnoreCase("44s")){
					 number = 44*1000;
				}
				else if(timera.equalsIgnoreCase("45s")){
					 number = 45*1000;
				}
				else if(timera.equalsIgnoreCase("46s")){
					 number = 46*1000;
				}
				else if(timera.equalsIgnoreCase("47s")){
					 number = 47*1000;
				}
				else if(timera.equalsIgnoreCase("48s")){
					 number = 48*1000;
				}
				else if(timera.equalsIgnoreCase("49s")){
					 number = 49*1000;
				}
				else if(timera.equalsIgnoreCase("50s")){
					 number = 50*1000;
				}
				else if(timera.equalsIgnoreCase("51s")){
					 number = 51*1000;
				}
				else if(timera.equalsIgnoreCase("52s")){
					 number = 52*1000;
				}
				else if(timera.equalsIgnoreCase("53s")){
					 number = 53*1000;
				}
				else if(timera.equalsIgnoreCase("54s")){
					 number = 54*1000;
				}
				else if(timera.equalsIgnoreCase("55s")){
					 number = 55*1000;
				}
				else if(timera.equalsIgnoreCase("56s")){
					 number = 56*1000;
				}
				else if(timera.equalsIgnoreCase("57s")){
					 number = 57*1000;
				}
				else if(timera.equalsIgnoreCase("58s")){
					 number = 58*1000;
				}
				else if(timera.equalsIgnoreCase("59s")){
					 number = 36*1000;
				}
			}else{
			System.err.println("There is a problem with the timer file! Make sure what ever is in there can be read!");
			System.err.println("Timer disabled!");
			number = 0;
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
	return number;

}
public void doNext(){
	BufferedReader reader = null;
	String rules;
	try{			
	reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "rule.txt"));
	while ((rules = reader.readLine()) != null) {
		server.broadcastMessage("The current gamerule being voted on is " + rules);
		int ruleno = Integer.parseInt(rules);
		switch(ruleno){
		case one:
			server.broadcastMessage("It is time to finish voting for your gamemode.");
			checkno(server);
			checkyes(server);
			if (Switch > switcher){
				server.executeCommand("defaultgamemode 0");
			}
			else if (switcher > Switch){
				server.executeCommand("defaultgamemode 1");
			}
			try {		 
				File file = new File(pluginDir + File.separatorChar + "rule.txt");
	 
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("2");
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case two:
			server.broadcastMessage("It is time to finish voting for PVP");
			checkno(server);
			checkyes(server);
			if (Switch > switcher){
				TogglePVP = true;
			}
			else if (switcher > Switch){
				TogglePVP = false;
			}
			try {		 
				File file = new File(pluginDir + File.separatorChar + "rule.txt");
	 
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("3");
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case three:
			server.broadcastMessage("It is time to finish voting for mob spawning!");
			checkno(server);
			checkyes(server);
			if (Switch > switcher){
				Togglemobspawn = false;
			}
			else if (switcher > Switch){
				Togglemobspawn = true;
			}
			try {		 
				File file = new File(pluginDir + File.separatorChar + "rule.txt");
	 
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("4");
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case four:
			server.broadcastMessage("It is time to finish voting for time of day!");
			checkno(server);
			checkyes(server);
			if (Switch > switcher){
				server.executeCommand("time set day");
			}
			else if (switcher > Switch){
				server.executeCommand("time set night");
			}
			try {		 
				File file = new File(pluginDir + File.separatorChar + "rule.txt");
	 
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("1");
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;	
		}
		}
}  catch (IOException e) {
e.printStackTrace();
} finally {
try {
if (reader != null)
  reader.close();
} catch (IOException ex) {
ex.printStackTrace();
}
}
	try {		 
		File file = new File(pluginDir + File.separatorChar + "yes.txt");

		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("0");
		bw.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	try {		 
		File file = new File(pluginDir + File.separatorChar + "no.txt");

		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("0");
		bw.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	try{
		BufferedWriter writer = null;
		writer = (new BufferedWriter(new FileWriter(pluginDir + File.separatorChar + "list.txt")));
		writer.close();
	}catch(IOException e){
		e.printStackTrace();
	}
}
}
