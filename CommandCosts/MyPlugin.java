package CommandCosts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.Arrays;

import PluginReference.*;

public class MyPlugin extends PluginBase{
public static MC_Server server = null;
public String pluginDir = "plugins_mod" + File.separatorChar + "CommandCosts";
boolean no = false;
static boolean togglemoney = false;
boolean faliure = false;
public void onPlayerInput(MC_Player plr, String msg, MC_EventInfo ei){
	if (ei.isCancelled) return;
	if(msg.equalsIgnoreCase("/help")){
		no = true;
		plr.sendMessage("The /help command does not cost money to use");
	}
	if(msg.equalsIgnoreCase("/bal")){
		no = true;
		plr.sendMessage("The /bal command does not cost money to use");
	}
	if(msg.equalsIgnoreCase("/moneyon") && plr.hasPermission("cc.money")){
		ei.isCancelled = true;
		no = true;
		plr.sendMessage("Toggling money on!");
		togglemoney = true;
	} 
	if(msg.equalsIgnoreCase("/moneyoff") && plr.hasPermission("cc.money")){
		ei.isCancelled = true;
		no = true;
		plr.sendMessage("Toggling money off!");
		togglemoney = false;	
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
	if(inputArray[1].equalsIgnoreCase("/cost") && plr.hasPermission("cc.cost")){
		ei.isCancelled = true;
		no = true;
		try{
				int n = Integer.parseInt(inputArray[2]);
				writeInt(new File(pluginDir + File.separatorChar + "cost.txt"), n);
			}catch(NumberFormatException n){
				System.out.println("[CommandCosts] error occured with number parameter" + inputArray[2] + "If it's not a number, then you know why. Otherwise, report to JD9999");
				}
	}
	if(msg.startsWith("/")){
		if(no){
			no = false;
		}else{
BigDecimal bg2 = new BigDecimal("5");
BufferedReader reader = null;
String cost;
		try{			
			reader = new BufferedReader(new FileReader(pluginDir + File.separatorChar + "cost.txt"));
			while((cost = reader.readLine())!=null){
				if(cost.isEmpty()) bg2 = new BigDecimal("5");
				else bg2 = new BigDecimal(cost);		
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
BigDecimal bg1 = new BigDecimal(plr.getEconomyBalance());

BigDecimal bg3 = bg1.subtract(bg2);
plr.setEconomyBalance(bg3.doubleValue());
plr.sendMessage("Your new balance is: $" + bg3.toString());
}
}
}
public void onStartup(MC_Server argServer)
{
	System.out.println("Plugin starting! Lets hope it works! :)");
	server = argServer;
	new File(pluginDir).mkdir();
	try{
		File dir = new File(pluginDir + File.separatorChar + "cost.txt");
		if (dir.createNewFile()){
			System.out.println("The file cost.txt is created!");
		}else{
			System.out.println("cost.txt aready created.");
		}
		}
			catch (IOException prob){
				prob.printStackTrace();
			}
}
public void onShutdown()
{
	System.out.println("CommandCosts shutting down!");
}

public PluginInfo getPluginInfo() 
{ 
	PluginInfo info = new PluginInfo();
	info.description = "Commands cost money: version 0.7 started";
	info.pluginNamesINeedToGetEventsBefore = Arrays.asList("_ApiCollection", "_ColorApi", "_PI3Libs", "_RainbowXtrasLibrary", "AnimalGuard", "Anvil", "BannedItems", "bedClaim", "BetterSellCommand", "BuildAssist", "ChatCommandsblockTags", "ChestShop", "Clans", "Clock", "ColorDictionary"); 
	return info;
}
public void onPlayerJoin(MC_Player plr){
	if(togglemoney){
plr.sendMessage("CommandCosts is giving you 50 money!");
plr.setEconomyBalance(plr.getEconomyBalance() + 50);
	}
}
public void onConsoleInput(String msg, MC_EventInfo ei){
	if(msg.equalsIgnoreCase("moneyon")){
		ei.isCancelled = true;
		no = true;
		System.out.println("Toggling money on!");
		togglemoney = true;
	} 
	if(msg.equalsIgnoreCase("moneyoff")){
		ei.isCancelled = true;
		no = true;
		System.out.println("Toggling money off!");
		togglemoney = false;	
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
	if(inputArray[1].equalsIgnoreCase("cost")){
		ei.isCancelled = true;
		try{
				int n = Integer.parseInt(inputArray[2]);
				writeInt(new File(pluginDir + File.separatorChar + "cost.txt"), n);
			}catch(NumberFormatException n){
				System.out.println("[CommandCosts] error occured with number parameter" + inputArray[2] + "If it's not a number, then you know why. Otherwise, report to JD9999");
				}
	}
}
public void writeInt(File file, int i){
	String str = String.valueOf(i);
	writeString(file, str);
}
RandomAccessFile raf;
public void writeString(File file, String s){
	try {
		raf = new RandomAccessFile(file, "rw");
		raf.writeBytes(s);
	} catch (IOException e) {
		e.printStackTrace();
	}finally{
		try{
			raf.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
}
