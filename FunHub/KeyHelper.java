package FunHub;

import PluginReference.MC_ID;

public class KeyHelper {

	protected static final String KEY_NAME = "FunHubAccessKey";
	protected static final String HUB_ACCESS = "FunHub.access";
	protected static final String MAGIC_CARPET_NAME = "Magic Carpet";
	protected static final String WALK_TRACKER_MENU_ITEM_NAME = "FunHubWalkTracker";
	protected static final String ANIMAL_PIGGYBACK_MENU_ITEM_NAME = "FunHubPiggyback";
	protected static final Integer WALK_TRACKER_TYPE_ICE = 1;
	protected static final Integer WALK_TRACKER_TYPE_WHITE_WOOL = 2;
	protected static final Integer WALK_TRACKER_TYPE_ORANGE_WOOL = 12;
	protected static final Integer WALK_TRACKER_TYPE_MAGENTA_WOOL = 22;
	protected static final Integer WALK_TRACKER_TYPE_LIGHT_BLUE_WOOL = 32;
	protected static final Integer WALK_TRACKER_TYPE_YELLOW_WOOL = 42;
	protected static final Integer WALK_TRACKER_TYPE_LIME_WOOL = 52;
	protected static final Integer WALK_TRACKER_TYPE_PINK_WOOL = 62;
	protected static final Integer WALK_TRACKER_TYPE_GREY_WOOL = 72;
	protected static final Integer WALK_TRACKER_TYPE_LIGHT_GREY_WOOL = 82;
	protected static final Integer WALK_TRACKER_TYPE_CYAN_WOOL = 92;
	protected static final Integer WALK_TRACKER_TYPE_PURPLE_WOOL = 102;
	protected static final Integer WALK_TRACKER_TYPE_BLUE_WOOL = 112;
	protected static final Integer WALK_TRACKER_TYPE_BROWN_WOOL = 122;
	protected static final Integer WALK_TRACKER_TYPE_GREEN_WOOL = 132;
	protected static final Integer WALK_TRACKER_TYPE_RED_WOOL = 142;
	protected static final Integer WALK_TRACKER_TYPE_BLACK_WOOL = 152;
	protected static String forKey(Integer key){
		if(key.intValue() == WALK_TRACKER_TYPE_ICE.intValue()) return "ice";
		else if(key.intValue() == WALK_TRACKER_TYPE_WHITE_WOOL.intValue()) return "white wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_ORANGE_WOOL.intValue()) return "orange wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_MAGENTA_WOOL.intValue()) return "magenta wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_LIGHT_BLUE_WOOL.intValue()) return "light blue wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_YELLOW_WOOL.intValue()) return "yellow wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_LIME_WOOL.intValue()) return "lime wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_PINK_WOOL.intValue()) return "pink wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_GREY_WOOL.intValue()) return "grey wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_LIGHT_GREY_WOOL.intValue()) return "light grey wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_CYAN_WOOL.intValue()) return "cyan wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_PURPLE_WOOL.intValue()) return "purple wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_BLUE_WOOL.intValue()) return "blue wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_BROWN_WOOL.intValue()) return "brown wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_GREEN_WOOL.intValue()) return "green wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_RED_WOOL.intValue()) return "red wool";
		else if(key.intValue() == WALK_TRACKER_TYPE_BLACK_WOOL.intValue()) return "black wool";
		else throw new IllegalArgumentException("key: " + key.intValue() + " is not recognized by system!");
	}
	
	protected static Integer forKey(String key){
		if(key.equals("ice")) return WALK_TRACKER_TYPE_ICE;
		else if(key.equals("white wool")) return WALK_TRACKER_TYPE_WHITE_WOOL;
		else if(key.equals("orange wool")) return WALK_TRACKER_TYPE_ORANGE_WOOL;
		else if(key.equals("magenta wool")) return WALK_TRACKER_TYPE_MAGENTA_WOOL;
		else if(key.equals("light blue wool")) return WALK_TRACKER_TYPE_LIGHT_BLUE_WOOL;
		else if(key.equals("yellow wool")) return WALK_TRACKER_TYPE_YELLOW_WOOL;
		else if(key.equals("lime wool")) return WALK_TRACKER_TYPE_LIME_WOOL;
		else if(key.equals("pink wool")) return WALK_TRACKER_TYPE_PINK_WOOL;
		else if(key.equals("grey wool")) return WALK_TRACKER_TYPE_GREY_WOOL;
		else if(key.equals("light grey wool")) return WALK_TRACKER_TYPE_LIGHT_GREY_WOOL;
		else if(key.equals("cyan wool")) return WALK_TRACKER_TYPE_CYAN_WOOL;
		else if(key.equals("purple wool")) return WALK_TRACKER_TYPE_PURPLE_WOOL;
		else if(key.equals("blue wool")) return WALK_TRACKER_TYPE_BLUE_WOOL;
		else if(key.equals("brown wool")) return WALK_TRACKER_TYPE_BROWN_WOOL;
		else if(key.equals("green wool")) return WALK_TRACKER_TYPE_GREEN_WOOL;
		else if(key.equals("red wool")) return WALK_TRACKER_TYPE_RED_WOOL;
		else if(key.equals("black wool")) return WALK_TRACKER_TYPE_BLACK_WOOL;
		else throw new IllegalArgumentException("key: " + key + " is not recognized by system!");
	}

	protected static int getType(Integer type) {
		return type.intValue() == WALK_TRACKER_TYPE_ICE.intValue() ? MC_ID.BLOCK_ICE : MC_ID.BLOCK_WOOL;
	}

	protected static int getSubtype(Integer type) {
		int value = type.intValue();
		if(value <= 2) return 0;
		else return (type.intValue() - 2) / 10;
	}

}
