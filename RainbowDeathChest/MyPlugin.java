package RainbowDeathChest;

import java.util.ArrayList;
import java.util.List;

import PluginReference.*;

public class MyPlugin extends PluginBase{
	public static MC_Server server = null;
	
public void onPlayerDeath(MC_Player plrVictim, MC_Player plrKiller, MC_DamageType dmgType, String deathMsg){
	deathMsg = "Rainbow Death Chest collecting your stuff! But try not to die!";
	MC_Location loc1 = plrVictim.getLocation();
	MC_Block myBlock = server.getBlockFromName(BlockHelper.getBlockName(MC_BlockType.CHEST));
	MC_World world = plrVictim.getWorld();
	int x = loc1.getBlockX();
	int y = loc1.getBlockY();
	int z = loc1.getBlockZ();
	world.setBlockAt(x, y, z, myBlock, 0);
	List<MC_ItemStack> inv = plrVictim.getInventory();
	MC_Chest chest = world.getChestAt(loc1);
	if(inv.size() < 27){
	List<MC_ItemStack> chestinv = new ArrayList<MC_ItemStack>();
	for(int i = 0; i < inv.size(); i++){
		MC_ItemStack stack = inv.get(i);
		chestinv.add(stack);
	}
	chest.setInventory(chestinv);	
	}else{
		List<MC_ItemStack> chestinv = new ArrayList<MC_ItemStack>();
		for(int i = 0; i < 27; i++){
			MC_ItemStack stack = inv.get(i);
			chestinv.add(stack);
		}
		chest.setInventory(chestinv);
		x = loc1.getBlockX();
		int y1 = loc1.getBlockY() + 2;
		z = loc1.getBlockZ();
		world.setBlockAt(x, y1, z, myBlock, 0);
		MC_Chest chest2 = world.getChestAt(new MC_Location(x, y1, z, 0));
		List<MC_ItemStack> chestinv1= new ArrayList<MC_ItemStack>();
			MC_ItemStack stack = inv.get(27);
			chestinv1.add(stack);
			stack = inv.get(28);
			chestinv1.add(stack);
			stack = inv.get(29);
			chestinv1.add(stack);
			stack = inv.get(30);
			chestinv1.add(stack);
			stack = inv.get(31);
			chestinv1.add(stack);
			stack = inv.get(32);
			chestinv1.add(stack);
			stack = inv.get(33);
			chestinv1.add(stack);
			stack = inv.get(34);
			chestinv1.add(stack);
			stack = inv.get(35);
			chestinv1.add(stack);
		chest2.setInventory(chestinv1);
	}
	plrVictim.sendMessage("Your inventory has been placed at" + loc1.x + " " + loc1.y + " "  + loc1.z);
	plrVictim.setInventory(inv);
}
public void onStartup(MC_Server argServer)
{
	System.out.println("Plugin starting! Lets hope it works! :)");
	server = argServer;
}
public void onShutdown()
{
	System.out.println("Rainbow Death Chest shutting down!");
}

public PluginInfo getPluginInfo() 
{ 
	PluginInfo info = new PluginInfo();
	info.description = "Chest fills with dead players inventory where they die: version 0.1 started";
	info.name = "Rainbow Death Chest";
	info.version = "0.1";
	return info;
}
}
