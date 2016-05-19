package bedClaim;

import PluginReference.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MyPlugin extends PluginBase{
public static MC_Server server = null;
public static boolean Togglebed;
public static File bedsave = new File("plugins_mod" + File.separator + "BedClaim" + File.separator);
public static File bedfile = new File(bedsave.getAbsolutePath() + File.separator + "BedClaim.txt");
public static int increment = 0;

public void onStartup(MC_Server argServer)
{
	System.out.println("Plugin starting! Lets hope it works! :)");
	server = argServer;	
	doStartup();
	server.registerCommand(new CmdReadBed());
	server.registerCommand(new CmdRepBed());
	server.registerCommand(new CmdGuestBed());
}
public static void doStartup() {
	try {
		bedsave.mkdirs();
		bedfile.createNewFile();
		List<String> lines = Files.readAllLines(bedfile.toPath());
		if(lines.isEmpty()){
			increment = 0;
			BufferedWriter writer = new BufferedWriter(new FileWriter(bedfile));
			writer.write("0");
			writer.close();
		}else{
			String line = lines.get(lines.size() -1);
			System.out.println("Parsing line: " + line);
			increment = Integer.parseInt(line);
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	try {
		CmdGuestBed.bedFile.createNewFile();
	} catch (IOException e) {
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
	info.description = "Allows you to claim beds: version 1.2 started";
	return info;
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
	}
	}
}
public void onPlayerBedEnter(MC_Player plr, MC_Block bedBlk, MC_Location loc)
{
	if(CmdGuestBed.readGuestBeds().contains(loc.toString())){
		plr.sendMessage("You are sleeping in a guest bed!");
		return;
	}
  boolean processed = false;
  try {
	List<String> lines = Files.readAllLines(Paths.get(bedfile.toURI()));
	for(int i = 0; i < lines.size(); i++){
		String line = lines.get(i);
		if(line.contains(plr.getName() + loc.toString())){
			plr.sendMessage("This is your bed! have a good sleep!");
			processed = true;
			return;
		}else if(line.contains(loc.toString()) && !line.contains(plr.getName())){
			plr.kick("A player has already claimed that bed!");
			processed = true;
			return;
		}else{
			if(i == (lines.size() - 1) && !processed){
				plr.sendMessage("No one's claimed this bed! Go ahead!");
				BufferedWriter writer = new BufferedWriter(new FileWriter(bedfile, true));
				writer.newLine();
				writer.write(plr.getName() + loc.toString());
				writer.newLine();
				increment++;
				writer.write(String.valueOf(increment));
				writer.close();
			}
		}
	}
} catch (IOException e) {
	e.printStackTrace();
}
  
}
public void onPlayerBedLeave(MC_Player plr, MC_Block blk, MC_Location loc)
{
	System.out.println("Player " +plr.getName() + " has exited a bed at: " + loc.toString());
}

public void onAttemptBlockBreak(MC_Player plr, MC_Location loc, MC_EventInfo ei){
	MC_Block block = getBlockAtLocation(loc);
	if(block.getId() == MC_ID.BLOCK_BED){
		//Why is this reversed? Yep, the plugin has a glitch that it does the opposite of what its supposed to do
		if(!CmdGuestBed.readGuestBeds().contains(loc.toString()) || !CmdReadBed.readBed().contains(loc.toString())){
			if(plr.isOp()){
				plr.sendMessage("You are about to destroy a claimed bed or a guest bed!");
			}else plr.kick("Cannot destroy another player's claimed bed or a guest bed unless you are an OP");	
		}
	}
	}
private MC_Block getBlockAtLocation(MC_Location loc) {
	MC_World world = server.getWorld(loc.dimension);
	MC_Block blk = world.getBlockAt(loc);
	return blk;
}

}
