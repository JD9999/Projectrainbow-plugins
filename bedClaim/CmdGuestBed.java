package bedClaim;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

//import PluginReference.BlockHelper;
import PluginReference.MC_Block;
import PluginReference.MC_Command;
import PluginReference.MC_ID;
import PluginReference.MC_Location;
import PluginReference.MC_Player;
import PluginReference.MC_World;

public class CmdGuestBed implements MC_Command{

	public static File bedFile = new File(MyPlugin.bedsave.getAbsolutePath() + File.separator + "GuestBeds.txt");
	
	@Override
	public List<String> getAliases() {
		return null;
	}

	@Override
	public String getCommandName() {
		return "guestbed";
	}

	@Override
	public String getHelpLine(MC_Player arg0) {
		return "creates a guest bed";
	}

	@Override
	public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
		return null;
	}

	@Override
	public void handleCommand(MC_Player plr, String[] args) {
		if(plr !=null){
			MC_Location loc = plr.getLocation();
			MC_World world = plr.getWorld();
			MC_Block blk = world.getBlockAt(loc);
			if(blk.getId() == MC_ID.BLOCK_BED){
				addGuestBed(loc);
				plr.sendMessage("Guest bed added!");
			}else plr.sendMessage("You are not on a bed!");
		}
	}

	@Override
	public boolean hasPermissionToUse(MC_Player plr) {
		if(plr !=null) return plr.isOp();
		else return true;
	}

	private void addGuestBed(MC_Location bed) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(bedFile, true));
			writer.newLine();
			MC_Location loc = roundOffLocation(bed);
			writer.write(loc.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private MC_Location roundOffLocation(MC_Location bed) {
		int x = bed.getBlockX();
		int y = bed.getBlockY();
		int z = bed.getBlockZ();
		MC_Location loc = new MC_Location((double)x, (double)y, (double)z, bed.dimension);
		return loc;
	}

	public static String readGuestBeds(){
		try {
			return new String(Files.readAllBytes(bedFile.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
