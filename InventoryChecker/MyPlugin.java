package InventoryChecker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import PluginReference.*;

public class MyPlugin extends PluginBase{
	
	public static MC_Server server = null;
	public static File invlog = new File("invlog.txt");

	public void onStartup(MC_Server argServer)
	{
		System.out.println("Plugin starting! Let's hope it works :)");
		server = argServer;
		try{
			
			if (invlog.createNewFile()){
				System.out.println("The file invlog.txt is created!");
			}else{
				System.out.println("invlog.txt aready created.");
			}
			}
				catch (IOException prob){
					prob.printStackTrace();
				}
	}
	public void onShutdown()
	{
		System.out.println("Inventory checker is shutting down");
	}

	public PluginInfo getPluginInfo() 
	{ 
		PluginInfo info = new PluginInfo();
		info.description = "Logs the player's inventory: version 0.1 started";
		return info;
	}
	public void onItemCrafted(MC_Player plr, MC_ItemStack isCraftedItem) 
	{
		List<MC_ItemStack> inv = plr.getInventory();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(invlog, true));
			writer.newLine();
			for(int i = 0; i < inv.size(); i++){
				MC_ItemStack stack = inv.get(i);
				writer.write(stack.getFriendlyName() + ", ");	
			}
			writer.write(isCraftedItem.getFriendlyName());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
