package bedClaim;

import PluginReference.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyPlugin extends PluginBase{
public static MC_Server server = null;
public static boolean Togglebed;

public void onStartup(MC_Server argServer)
{
	System.out.println("bedClaim starting! Lets hope it works! :)");
	server = argServer;	
	try {
	      File bedwriter = new File("BedClaim.txt");
	      if (bedwriter.createNewFile()){
	        System.out.println("BedClaim.txt created!");
	      }else{
	        System.out.println("BedClaim.txt already exists.");
	      }
  	} catch (IOException e) {
	      e.printStackTrace();
	}
	try{
	FileReader fr = new FileReader(new File("BedClaim.txt"));
    if (fr.read() == -1) {
    	try{
        FileWriter fw = new FileWriter("BedClaim.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("0");
		bw.close();

		System.out.println("File was empty, write complete");
	} catch (IOException e) {
		e.printStackTrace();
	}
    }else {
        System.out.println("File is not empty, assuming plugin will work correctly");
    }
    fr.close();
	}catch (IOException e){
    	e.printStackTrace();
    }
}
public void onShutdown()
{
	System.out.println("bedClaim shutting down!");
}

public PluginInfo getPluginInfo() 
{ 
	PluginInfo info = new PluginInfo();
	info.description = "Allows you to claim beds: version 1.1 started";
	return info;
}
public void onServerFullyLoaded(MC_Player plr, String msg, MC_EventInfo ei)
{
	server.setServerMOTD("Your beds can be claimed!");
	}
public void onPlayerJoin(MC_Player plr) 
{
	
	if(Togglebed){
	plr.sendMessage("Bed is toggled! You do not get a bed!");
	}else{
	plr.sendMessage(ChatColor.YELLOW + "You can claim your individual bed!");
	MC_ItemStack is = plr.getItemInHand();
	if( (is != null) && (is.getId() == 0))
	{
		// Give them a bed
		plr.setItemInHand(server.createItemStack(MC_ItemType.BED, 1, 0));
		plr.updateInventory();
		return;
	}
	}
}
public void onPlayerBedEnter(MC_Player plr, MC_Block bedBlk, MC_Location loc)
{
  String file = "BedClaim.txt";
  File tempFile = new File("BedClaimTemp.txt");
  try
  {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    String lineToRemove = plr.getName();
    String currentLine;
    while ((currentLine = reader.readLine()) != null)
    {
      String trimmedLine = currentLine.trim();
      if (!trimmedLine.contains(lineToRemove)) {
        writer.write(currentLine + System.getProperty("line.separator"));
      }
    }
    writer.close();
    reader.close();
  }
  catch (IOException e)
  {
    e.printStackTrace();
  }
  File file2 = new File(file);
  if(file2.delete())plr.sendMessage("File deleted!");
  else plr.sendMessage("Temp file not deleted sucessfully.");
  tempFile.renameTo(file2);
  readWholeFile(file, null, plr, loc);
}
public String readWholeFile(String file, Charset encoding, MC_Player plr, MC_Location loc) {
	File filename = new File("BedClaim.txt");
	byte[] encoded = null;
	try {
		encoded = Files.readAllBytes(Paths.get(file));
		String filetext = new String(encoded);
		String BedMessage1 = plr.getName() + loc.toString();
		if(filetext.contains(BedMessage1)){
			plr.sendMessage("This is your bed! have a good sleep!");
			Readlastline.tail(filename, BedMessage1);
			return filetext;
		}else if (filetext.contains(loc.toString())){
			plr.kick("A player has already claimed that bed!");
			return filetext;
		}else{
			plr.sendMessage("No one's claimed this bed! Go ahead!");
			Readlastline.tail(filename, BedMessage1);
			return filetext;
		}

	} catch (IOException e) {
		e.printStackTrace();
	}
	  return new String(encoded, encoding);
	
}
public void onPlayerBedLeave(MC_Player plr, MC_Block blk, MC_Location loc)
{
	System.out.println("Player " +plr.getName() + " has exited a bed at: " + loc.toString());
}
public void onConsoleInput(String cmd, MC_EventInfo ei) 
{
	if(ei.isCancelled) return;
	
	if(cmd.equalsIgnoreCase("readbed"))
	{
		ei.isCancelled = true;
		System.out.println("Your bed is now being read.");
		BufferedReader reader = null;
		String bedsave;
		try{
			
		reader = new BufferedReader(new FileReader("BedClaim.txt"));
		while ((bedsave = reader.readLine()) != null) {
			System.out.println(bedsave);
		}
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			System.out.println("File has been read!");
		}
	  }
	
	if(cmd.equalsIgnoreCase("delbed")){
		ei.isCancelled = true;
		
		try{
			 
    		File file = new File("BedClaim.txt");
 
    		if(file.delete()){
    			System.out.println("Your bed claims have been deleted!");
    		}else{
    			System.out.println("Delete operation has failed. Notify me in the bedClaim thread.");
    		}
 
    	}catch(Exception e){
 
    		e.printStackTrace();
 
    	}
	}
	if(cmd.equalsIgnoreCase("repbed")){
		ei.isCancelled = true;
		
		try{
			 
    		File file = new File("BedClaim.txt");
 
    		if(file.delete()){
    			System.out.println("Your bed claims have been deleted!");
    		}else{
    			System.out.println("Delete operation has failed. Notify me in the bedClaim thread.");
    		}
 
    	}catch(Exception e){
 
    		e.printStackTrace();
 
    	}
	
		try {
			 
		      File bedwriter = new File("BedClaim.txt");

		      if (bedwriter.createNewFile()){
		        System.out.println("File is created!");
		      }else{
		        System.out.println("File already exists. The file was not deleted successfully");
		        System.out.println("Notify me of it in the bedClaim thread");
		      }

	  	} catch (IOException e) {
		      e.printStackTrace();
		}	
		
	}
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
    String name;
   	if(inputArray[1].equalsIgnoreCase("/showbed"))
   	{
   		ei.isCancelled = true;
   		if(inputArray[2]!=null){
   			name = inputArray[2];	
   		BufferedReader reader = null;
   		String bedsave;
   		try{		
   		reader = new BufferedReader(new FileReader("BedClaim.txt"));
   		while ((bedsave = reader.readLine()) != null) {
   			if(bedsave.startsWith(name)){
   				System.out.println(bedsave);
   			}else{
   				System.out.println("The player specified could not be found!");
   			}	
   		}
   		}catch (IOException e) {
   			e.printStackTrace();
   		} finally {
   			try {
   				if (reader != null)reader.close();
   			} catch (IOException ex) {
   				ex.printStackTrace();
   			}
   }
   }else{
	   System.err.println("No player name! /showbed <playername>");
   }
   }
	if(cmd.equalsIgnoreCase("togglebedoff")){
		ei.isCancelled = true;
	Togglebed = false;
	}
	if(cmd.equalsIgnoreCase("togglebedon")){
		ei.isCancelled = true;
	Togglebed = true;
	}
		return;
	}
public void onPlayerInput(MC_Player plr, String msg, MC_EventInfo ei){
	
	if(msg.equalsIgnoreCase("/togglebedoff")){
		ei.isCancelled = true;
		Togglebed = false;
	}
	if(msg.equalsIgnoreCase("/togglebedon")){
		ei.isCancelled = true;
		Togglebed = true;
	}
	if(msg.equalsIgnoreCase("/readbed"))
	{
		ei.isCancelled = true;
		BufferedReader reader = null;
		String bedsave;
		try{
			
		reader = new BufferedReader(new FileReader("BedClaim.txt"));
		while ((bedsave = reader.readLine()) != null) {
			plr.sendMessage(bedsave);
		}
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
	}
	}	
	if(msg.equalsIgnoreCase("/delbed") && plr.isOp())
	{
	ei.isCancelled = true;
	try{
		 
		File file = new File("BedClaim.txt");

		if(file.delete()){
			plr.sendMessage("Your bed claims have been deleted!");
		}else{
			plr.sendMessage("Delete operation has failed. Notify me in the bedClaim thread.");
		}

	}catch(Exception e){

		e.printStackTrace();

	}
	}
	if(msg.equalsIgnoreCase("/repbed") && plr.isOp())
	{
	ei.isCancelled = true;
	File file = new File("BedClaim.txt");
	try{
		if(file.delete()){
			plr.sendMessage("Your bed claims have been deleted!");
		}else{
			plr.sendMessage("Delete operation has failed. Notify me in the bedClaim thread.");
		}

	}catch(Exception e){

		e.printStackTrace();

	}
	try {
		 
	      File bedwriter = new File("BedClaim.txt");

	      if (bedwriter.createNewFile()){
	        System.out.println("File is created!");
	      }else{
	        System.out.println("File already exists. The file was not deleted successfully");
	        System.out.println("Notify me of it in the bedClaim thread");
	      }

	} catch (IOException e) {
	      e.printStackTrace();
	}	
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
    String name;
	if(inputArray[1].equalsIgnoreCase("/showbed"))
	{
		ei.isCancelled = true;
		if(inputArray[2]!=null){
			name = inputArray[2];	
		BufferedReader reader = null;
		String bedsave;
		try{		
		reader = new BufferedReader(new FileReader("BedClaim.txt"));
		while ((bedsave = reader.readLine()) != null) {
			if(bedsave.startsWith(name)){
				plr.sendMessage(bedsave);
			}	
		}
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
}
}else{
	plr.sendMessage(ChatColor.RED + "No player name! /showbed <playername> ");
}
}
}
}
