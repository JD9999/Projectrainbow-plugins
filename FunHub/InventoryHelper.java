package FunHub;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import PluginReference.BlockHelper;
import PluginReference.MC_Block;
import PluginReference.MC_Entity;
import PluginReference.MC_EntityType;
import PluginReference.MC_InventoryGUI;
import PluginReference.MC_InventoryGUI.ClickHandler;
import PluginReference.MC_ItemStack;
import PluginReference.MC_Location;
import PluginReference.MC_Player;
import PluginReference.MC_Server;
import PluginReference.MC_World;

public class InventoryHelper {

	public static Map<MC_Player, BlockChangeHelper> changeHelperMap = new LinkedHashMap<MC_Player, BlockChangeHelper>();
	private static Map<MC_Player, MC_Location> lastLocMap = new LinkedHashMap<MC_Player, MC_Location>();
	private static MC_Server server;
	private static boolean tracker;

	public static MC_InventoryGUI getWalkTracker(boolean b) {
		String name = "";
		tracker = b;
		name = b? "FunHubWalkTracker menu" : "FunHubMagicCarpet menu";
		MC_InventoryGUI gui2 = server.createInventoryGUI(18, name);
		gui2.setItemStackAt(0, ItemHelper.getIceW());
		gui2.setClickHandler(0, getClickHandler(KeyHelper.WALK_TRACKER_TYPE_ICE));
		Map<MC_ItemStack, Integer> wool = ItemHelper.getAllWoolItems();
		Iterator<MC_ItemStack> it = wool.keySet().iterator();
		Iterator<Integer> in = wool.values().iterator();
		for(int i = 0; i < wool.size(); i++){
			int index = i + 1;
			gui2.setItemStackAt(index, it.next());
			gui2.setClickHandler(index, getClickHandler(in.next()));
		}
		gui2.setItemStackAt(wool.size() + 1, ItemHelper.getStop());
		gui2.setClickHandler(wool.size() + 1, getCloseHandler());
		return gui2;
	}

	private static ClickHandler getCloseHandler() {
		return player ->{
			BlockChangeHelper helper = changeHelperMap.get(player);
			if(helper !=null) helper.revertAll();
			changeHelperMap.put(player, null);
			player.closeInventory();
		};
	}

	private static ClickHandler getClickHandler(Integer type) {
		return player ->{
			lastLocMap.put(player, player.getLocation());
			player.closeInventory();
			player.sendMessage("You have activated " + KeyHelper.forKey(type) + " walk!");
			MC_Block block = server.getBlock(KeyHelper.getType(type), KeyHelper.getSubtype(type));
				BlockChangeHelper helper = changeHelperMap.get(player);
				if(helper !=null){
				helper.setRepBlock(block);
				helper.setTracker(tracker);
			}else{
				System.out.println("Setting up tracker: ");
				System.out.println("Player: " + player.getName());
				System.out.println("Block: " + BlockHelper.getBlockFriendlyName(block.getId(), block.getSubtype()));
				System.out.println("Tracker: " + tracker);
				helper = new BlockChangeHelper(player, block, tracker);
				changeHelperMap.put(player, helper);
			}
		};
	}

	public static MC_InventoryGUI getBehindTheAnimals(MC_Player plr) {
		MC_InventoryGUI gui = server.createInventoryGUI(9, "FunHubAnimalPiggyback menu");
		gui.setItemStackAt(0, ItemHelper.getWhiteWool());
		gui.setItemStackAt(1, ItemHelper.getPinkWool());
		gui.setItemStackAt(2, ItemHelper.getBrownWool());
		gui.setClickHandler(0, getClickHandler(MC_EntityType.SHEEP));
		gui.setClickHandler(1, getClickHandler(MC_EntityType.PIG));
		gui.setClickHandler(2, getClickHandler(MC_EntityType.COW));
		return gui;
	}

	private static ClickHandler getClickHandler(MC_EntityType type) {
		return player ->{
			MC_World world = player.getWorld();
			MC_Entity entity = world.spawnEntity(type, player.getLocation(), null);
			entity.setRider(player);
		};
	}

	public static void pushServer(MC_Server server){
		InventoryHelper.server = server;
	}

}
