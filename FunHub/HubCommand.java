package FunHub;

import java.util.ArrayList;
import java.util.List;

import PluginReference.MC_Command;
import PluginReference.MC_InventoryGUI;
import PluginReference.MC_ItemStack;
import PluginReference.MC_Player;
import PluginReference.MC_Server;

public class HubCommand implements MC_Command {

	private static MC_Server server;

	@Override
	public List<String> getAliases() {
		//Ignore case
		List<String> ls = new ArrayList<String>();
		ls.add("Menu");
		ls.add("MEnu");
		ls.add("MeNu");
		ls.add("MenU");
		ls.add("MENu");
		ls.add("MEnU");
		ls.add("MeNU");
		ls.add("MENu");
		ls.add("mEnu");
		ls.add("meNu");
		ls.add("menU");
		ls.add("mENu");
		ls.add("mEnU");
		ls.add("meNU");
		ls.add("mENU");
		ls.add("MENU");
		return ls;
	}

	@Override
	public String getCommandName() {
		return "menu";
	}

	@Override
	public String getHelpLine(MC_Player plr) {
		return "/menu - launch the hub";
	}

	@Override
	public List<String> getTabCompletionList(MC_Player plr, String[] args) {
		List<String> ls = new ArrayList<String>();
		if(args.length > 1) return ls;
		else if(args.length < 1){
			ls.add("/menu");
			return ls;
		}else{
			String s = args[0];
			if(s.substring(1).equalsIgnoreCase("men") || s.substring(1).equalsIgnoreCase("me") || s.substring(1).equalsIgnoreCase("m")){
				ls.add("/menu");
				return ls;
			}else return ls;
		}
	}

	@Override
	public void handleCommand(MC_Player plr, String[] args) {
		if(plr!=null){
			if(plr.hasPermission(KeyHelper.HUB_ACCESS) && hasKey(plr)){
				MC_InventoryGUI gui = server.createInventoryGUI(9, "FunHub!");
				gui.setItemStackAt(0, ItemHelper.getWalkTrackerMenuItem());
				gui.setItemStackAt(1, ItemHelper.getAnimalPiggybackMenuItem());
				gui.setItemStackAt(2, ItemHelper.getCarpetMenuItem());
				gui.setItemStackAt(8, ItemHelper.getClose());
				gui.setClickHandler(0, player -> {
					MC_InventoryGUI walkTracker = InventoryHelper.getWalkTracker(true);
					player.closeInventory();
					player.displayInventoryGUI(walkTracker);
				});
				gui.setClickHandler(1, player ->{
					MC_InventoryGUI animalPiggyback = InventoryHelper.getBehindTheAnimals(player);
					player.closeInventory();
					player.displayInventoryGUI(animalPiggyback);
				});
				gui.setClickHandler(2, player -> {
					MC_InventoryGUI magicCarpet = InventoryHelper.getWalkTracker(false);
					player.closeInventory();
					player.displayInventoryGUI(magicCarpet);
				});
				gui.setClickHandler(8, player ->{
					player.closeInventory();
				});
				plr.displayInventoryGUI(gui);
			}
		}else System.out.println("Only players can execute this command!");
	}

	private boolean hasKey(MC_Player plr) {
		for(MC_ItemStack stack : plr.getInventory()){
			if(stack.getCustomizedName().equals(KeyHelper.KEY_NAME)) return true;
		}
		return false;
	}

	@Override
	public boolean hasPermissionToUse(MC_Player plr) {
		if(plr == null) return true;
		return plr.hasPermission(KeyHelper.HUB_ACCESS);
	}

	protected static void pushServer(MC_Server server){
		HubCommand.server = server;
	}

}
