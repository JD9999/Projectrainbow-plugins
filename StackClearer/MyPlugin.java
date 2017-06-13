package StackClearer;

import java.util.ArrayList;
import java.util.List;

import PluginReference.*;

public class MyPlugin extends PluginBase{

public void onPlayerInput(MC_Player plr, String message, MC_EventInfo ei){
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
    if(inputArray[1].equalsIgnoreCase("clearhanditem")){
    	int n = 1;
    	MC_ItemStack inv = plr.getInventory().get(n);
    	if(inv.getId() == Integer.parseInt(inputArray[2])){
    		removeid(n,plr);
    	}
    	else{
    		n = n+1;
    		if(inv.getId() == Integer.parseInt(inputArray[2])){
        		removeid(n,plr);
        	}else{
        		n = n+1;
        		if(inv.getId() == Integer.parseInt(inputArray[2])){
            		removeid(n,plr);
        		}
        	}
    	}
    }   
}	
	
public void onStartup(MC_Server svr){
	System.out.println("Plugin starting! Lets hope it works! :)");
}	

public void onShutdown(){
	System.out.println("StackClearer not removing anything from inventories soon!");
}
	
public PluginInfo getPluginInfo(){
	PluginInfo info = new PluginInfo();
	info.description = "Removes a specified ID from a player's inventory";
	return info;
}

private void removeid(int n, MC_Player plr){
	List<MC_ItemStack> list = new ArrayList<MC_ItemStack>();
	list.remove(n);
	plr.setInventory(list);
}

}
