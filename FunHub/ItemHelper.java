package FunHub;

import java.util.LinkedHashMap;
import java.util.Map;

import PluginReference.MC_ID;
import PluginReference.MC_ItemStack;
import PluginReference.MC_Server;

public class ItemHelper {

	private static MC_Server server;

	public static MC_ItemStack getKey() {
		int id = MC_ID.BLOCK_REPEATING_COMMAND_BLOCK;
		MC_ItemStack stack = server.createItemStack(id, 1, 0);
		stack.setCustomName(KeyHelper.KEY_NAME);
		return stack;
	}

	public static MC_ItemStack getWalkTrackerMenuItem() {
		int id = MC_ID.ITEM_MAP;
		MC_ItemStack stack = server.createItemStack(id, 1, 0);
		stack.setCustomName(KeyHelper.WALK_TRACKER_MENU_ITEM_NAME);
		return stack;
	}

	public static MC_ItemStack getAnimalPiggybackMenuItem() {
		int id = MC_ID.ITEM_SKELETON_SKULL;
		MC_ItemStack stack = server.createItemStack(id, 1, 3); //Subtype 3 is a Steve head.
		stack.setCustomName(KeyHelper.ANIMAL_PIGGYBACK_MENU_ITEM_NAME);
		return stack;
	}

	public static MC_ItemStack getIceW() {
		int id = MC_ID.BLOCK_ICE;
		MC_ItemStack stack = server.createItemStack(id, 1, 0);
		return stack;
	}

	public static MC_ItemStack getClose() {
		int id = MC_ID.BLOCK_BARRIER;
		MC_ItemStack stack = server.createItemStack(id, 1, 0);
		stack.setCustomName("Close the menu");
		return stack;
	}

	public static Map<MC_ItemStack, Integer> getAllWoolItems() {
		Map<MC_ItemStack, Integer> stacks = new LinkedHashMap<>();
		for(int i = 0; i < 16; i++){
			MC_ItemStack stack = server.createItemStack(35, 1, i);
			int type = 99999;
			switch(i){
			case 0:
				type = KeyHelper.WALK_TRACKER_TYPE_WHITE_WOOL;
				break;
			case 1:
				type = KeyHelper.WALK_TRACKER_TYPE_ORANGE_WOOL;
				break;
			case 2:
				type = KeyHelper.WALK_TRACKER_TYPE_MAGENTA_WOOL;
				break;
			case 3:
				type = KeyHelper.WALK_TRACKER_TYPE_LIGHT_BLUE_WOOL;
				break;
			case 4:
				type = KeyHelper.WALK_TRACKER_TYPE_YELLOW_WOOL;
				break;
			case 5:
				type = KeyHelper.WALK_TRACKER_TYPE_LIME_WOOL;
				break;
			case 6:
				type = KeyHelper.WALK_TRACKER_TYPE_PINK_WOOL;
				break;
			case 7:
				type = KeyHelper.WALK_TRACKER_TYPE_GREY_WOOL;
				break;
			case 8:
				type = KeyHelper.WALK_TRACKER_TYPE_LIGHT_GREY_WOOL;
				break;
			case 9:
				type = KeyHelper.WALK_TRACKER_TYPE_CYAN_WOOL;
				break;
			case 10:
				type = KeyHelper.WALK_TRACKER_TYPE_PURPLE_WOOL;
				break;
			case 11:
				type = KeyHelper.WALK_TRACKER_TYPE_BLUE_WOOL;
				break;
			case 12:
				type = KeyHelper.WALK_TRACKER_TYPE_BROWN_WOOL;
				break;
			case 13:
				type = KeyHelper.WALK_TRACKER_TYPE_GREEN_WOOL;
				break;
			case 14:
				type = KeyHelper.WALK_TRACKER_TYPE_RED_WOOL;
				break;
			case 15:
				type = KeyHelper.WALK_TRACKER_TYPE_BLACK_WOOL;
				break;
			default:
				System.out.println("Why has java iteration of 15 exceeded 15?");
			}
			stacks.put(stack, type);
		}
		return stacks;
	}
	
	public static void pushServer(MC_Server server){
		ItemHelper.server = server;
	}

	public static MC_ItemStack getCarpetMenuItem() {
		int id = MC_ID.BLOCK_CARPET;
		MC_ItemStack stack = server.createItemStack(id, 1, 0);
		stack.setCustomName(KeyHelper.MAGIC_CARPET_NAME);
		return stack;
	}
	
	public static MC_ItemStack getWhiteWool(){
		MC_ItemStack stack = server.createItemStack(35, 1, 0);
		return stack;
	}
	
	public static MC_ItemStack getBrownWool(){
		MC_ItemStack stack = server.createItemStack(35, 1, 12);
		return stack;
	}
	
	public static MC_ItemStack getPinkWool(){
		MC_ItemStack stack = server.createItemStack(35, 1, 6);
		return stack;
	}

	public static MC_ItemStack getStop() {
		MC_ItemStack stack = getClose();
		stack.setCustomName("Disables your tracker or carpet, then closes the menu!");
		return stack;
	}

}
