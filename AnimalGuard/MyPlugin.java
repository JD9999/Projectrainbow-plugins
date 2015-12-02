package AnimalGuard;

import java.util.ArrayList;
import java.util.List;

import PluginReference.*;

public class MyPlugin extends PluginBase{
	
	public static boolean ToggleGuard = true;
	public static boolean Togglesendback = false;
	public static boolean doprotect = false;
	public static MC_Server server = null;

	public void onStartup(MC_Server argServer)
	{
		System.out.println("AnimalGuard starting! Lets hope it works! :)");
		server = argServer;
	}
	public void onShutdown()
	{
		System.out.println("AnimalGuard shutting down!");
		System.out.println("Sorry animals, I'm going.");
}

	public PluginInfo getPluginInfo() 
	{ 
		PluginInfo info = new PluginInfo();
		info.description = "Animals are protected - version 1.0 started";
		return info;
	}
	public void onPlayerJoin(MC_Player plr) 
	{
		plr.sendMessage(ChatColor.RED + "Animal Guard is guarding!");
	}
	public void onPlayerInput(MC_Player plr, String msg, MC_EventInfo ei){
		if (ei.isCancelled) return;
		
		if(msg.equalsIgnoreCase("/protectedchunks")){
			ei.isCancelled = true;
			plr.sendMessage("Getting protected chunks");
			List<Integer> ls = ChunkProtect.getProtectedChunks();
			int size = ls.size();
			for(int i = 0; i < size; i++){
				Integer cp = ls.get(i);
				plr.sendMessage(String.valueOf(cp));
			}
			plr.sendMessage("Done");
		}
		
		if(msg.equalsIgnoreCase("/dissguard")){
			ei.isCancelled = true;
			ToggleGuard = false;
			plr.sendMessage("AnimalGuard is off!");
		}
		if(msg.equalsIgnoreCase("/protectguard")){
			ei.isCancelled = true;
			ToggleGuard = true;
			plr.sendMessage("AnimalGuard is on!");
}
		if(msg.equalsIgnoreCase("/chunkon")){
			ei.isCancelled = true;
			doprotect = true;
			ToggleGuard = true;
			plr.sendMessage("AnimalGuard has turned on chunk protection!");
		}
		if(msg.equalsIgnoreCase("/chunkoff")){
			ei.isCancelled = true;
			doprotect = false;
			plr.sendMessage("AnimalGuard has turned off chunk protection!");
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
		
		if(inputArray[1].equalsIgnoreCase("/protect")){
			ei.isCancelled = true;
			if(inputArray[2] == null)plr.sendMessage("The correct usage is either /protect here or /protect (chunkx) (chunkz)");
			else if(inputArray[2].equalsIgnoreCase("here")){
			MC_World world = plr.getWorld();
			List <MC_Chunk> chunks = world.getLoadedChunks();
			List <MC_Chunk> chunk = new ArrayList<MC_Chunk>();
			chunk.addAll(chunks);
			int size = chunk.size();
			for(int i = 0; i <= size; i++){
				MC_Chunk chk = chunk.get(i);
				int cx = chk.getCX();
				int cz = chk.getCZ();
				int x = (int) plr.getLocation().x;
				int z = (int) plr.getLocation().z;
				if(x >> 4 == cx && z >> 4 == cz){
					plr.sendMessage("Found chunk!");
				    ChunkProtect cp = new ChunkProtect(cx, cz);
				    cp.addchunk();
				    return;
				}
				}
		}
			else{
				int s = 0;
				int x = 0;
				try{
					s = Integer.parseInt(inputArray[2]);
				}catch(NumberFormatException n){
					plr.sendMessage("Was that second parameter an integer? I think not.");
					doprotect = false;
				}
				try{
					x = Integer.parseInt(inputArray[3]);
				}catch(NumberFormatException n){
					plr.sendMessage("Was that third parameter an integer? I think not.");
					doprotect = false;
				}
					plr.sendMessage("Adding co-ordinates!");
					ChunkProtect cp = new ChunkProtect(s, x);
					cp.addchunk();
				
			}
			}
		if(inputArray[1].equalsIgnoreCase("/diss")){
			ei.isCancelled = true;
			if(inputArray[2] == null)plr.sendMessage("The correct usage is either /diss here or /diss (chunkx) (chunkz)");
			else if(inputArray[2].equalsIgnoreCase("here")){
			MC_World world = plr.getWorld();
			List <MC_Chunk> chunks = world.getLoadedChunks();
			List <MC_Chunk> chunk = new ArrayList<MC_Chunk>();
			chunk.addAll(chunks);
			int size = chunk.size();
			for(int i = 0; i < size; i++){
				MC_Chunk chk;
				chk = chunk.get(i);
				int cx = chk.getCX();
				int cz = chk.getCZ();
				int x = (int) plr.getLocation().x;
				int z = (int) plr.getLocation().z;
				if(x >> 4 == cx && z >> 4 == cz){
					plr.sendMessage("Found chunk!");
				    ChunkProtect cp = new ChunkProtect(cx, cz);
				    cp.removechunk();
				    return;
				} else {
				}
				}
		}else{
				int s = 0;
				int x = 0;
				try{
					s = Integer.parseInt(inputArray[2]);
				}catch(NumberFormatException n){
					plr.sendMessage("Was that second parameter an integer? I think not.");
					doprotect = false;
				}
				try{
					x = Integer.parseInt(inputArray[3]);
				}catch(NumberFormatException n){
					plr.sendMessage("Was that third parameter an integer? I think not.");
					doprotect = false;
				}
					plr.sendMessage("Removing co-ordinates!");
					ChunkProtect cp = new ChunkProtect(s, x);
					cp.removechunk();
				
			}
			}
			
		}
	public void onAttemptEntityDamage(MC_Entity ent, MC_DamageType dmgType, double amt, MC_EventInfo ei)  {
		if(ToggleGuard && (!doprotect)){
		getProtectionrelatedentitydamage(ent, dmgType, amt, ei);
		return;
		}
		else{
			MC_World world = ent.getWorld();
			List <MC_Chunk> chunks = world.getLoadedChunks();
			List <MC_Chunk> chunk = new ArrayList<MC_Chunk>();
			chunk.addAll(chunks);
			int size = chunk.size();
			for(int i = 0; i < size; i++){
				MC_Chunk chk;
				if(i == 0)chk = chunk.get(i);
				else chk = chunk.get(i);
				int cx = chk.getCX();
				int cz = chk.getCZ();
				int x = (int) ent.getLocation().x;
				int z = (int) ent.getLocation().z;
				if(x >> 4 == cx && z >> 4 == cz){
				    ChunkProtect cp = new ChunkProtect(cx, cz);
				    if(doprotect && cp.checkifchunkisprotected() && ToggleGuard) getProtectionrelatedentitydamage(ent, dmgType, amt, ei);
				} else if(ToggleGuard = false){
				}else{
				}
		} 
		}
}	
	public void onAttemptAttackEntity(MC_Player plr, MC_Entity ent, MC_EventInfo ei)  {
		if(ToggleGuard && !doprotect){
			doprotectPayback(ent, plr, ei);
		} else if(ToggleGuard = false){
			plr.sendMessage("Not protecting hit, AnimalGuard protection is off");
		}else if (doprotect){
			MC_World world = ent.getWorld();
			List <MC_Chunk> chunks = world.getLoadedChunks();
			List <MC_Chunk> chunk = new ArrayList<MC_Chunk>();
			chunk.addAll(chunks);
			int size = chunk.size();
			for(int i = 0; i < size; i++){
				MC_Chunk chk;
				chk = chunk.get(i);
				int cx = chk.getCX();
				int cz = chk.getCZ();
				int x = (int) ent.getLocation().x;
				int z = (int) ent.getLocation().z;
				if(x >> 4 == cx && z >> 4 == cz){
				    ChunkProtect cp = new ChunkProtect(cx, cz);
				    if(cp.checkifchunkisprotected()) doprotectPayback(ent, plr, ei);
				}
		}
		}else{
		System.out.println("AnimalGuard has no idea what to do!");
		System.out.println("Assuming animal protection is on but chunk protection is off.");
		doprotectPayback(ent, plr, ei);
		}
			return;
	}
	
	public void onConsoleInput(String msg, MC_EventInfo ei){
		if (ei.isCancelled) return;
		
		if(msg.equalsIgnoreCase("protectedchunks")){
			ei.isCancelled = true;
			System.out.println("Getting protected chunks");
			List<Integer> ls = ChunkProtect.getProtectedChunks();
			int size = ls.size();
			for(int i = 0; i < size; i++){
				Integer cp = ls.get(i);
				System.out.println(String.valueOf(cp));
			}
			System.out.println("Done");
		}
		
		if(msg.equalsIgnoreCase("dissguard")){
			ei.isCancelled = true;
			ToggleGuard = false;
			System.out.println("AnimalGuard is off!");
		}
		if(msg.equalsIgnoreCase("protectguard")){
			ei.isCancelled = true;
			ToggleGuard = true;
			System.out.println("AnimalGuard is on!");
}
		if(msg.equalsIgnoreCase("chunkon")){
			ei.isCancelled = true;
			doprotect = true;
			ToggleGuard = true;
			System.out.println("AnimalGuard has turned on chunk protection!");
		}
		if(msg.equalsIgnoreCase("chunkoff")){
			ei.isCancelled = true;
			doprotect = false;
			System.out.println("AnimalGuard has turned off chunk protection!");
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
		
		if(inputArray[1].equalsIgnoreCase("protect")){
			ei.isCancelled = true;
			if(inputArray[2] == null)System.out.println("The correct usage is /protect (chunkx) (chunkz)");
			else{
				int s = 0;
				int x = 0;
				try{
					s = Integer.parseInt(inputArray[2]);
				}catch(NumberFormatException n){
					System.out.println("Was that second parameter an integer? I think not.");
					doprotect = false;
				}
				try{
					x = Integer.parseInt(inputArray[3]);
				}catch(NumberFormatException n){
					System.out.println("Was that third parameter an integer? I think not.");
					doprotect = false;
				}
				if(doprotect){
					ChunkProtect cp = new ChunkProtect(s, x);
					cp.addchunk();
				}
			}
			}
		if(inputArray[1].equalsIgnoreCase("diss")){
			ei.isCancelled = true;
			if(inputArray[2] == null)System.out.println("The correct usage is /diss (chunkx) (chunkz)");
			else{
				int s = 0;
				int x = 0;
				try{
					s = Integer.parseInt(inputArray[2]);
				}catch(NumberFormatException n){
					System.out.println("Was that second parameter an integer? I think not.");
					doprotect = false;
				}
				try{
					x = Integer.parseInt(inputArray[3]);
				}catch(NumberFormatException n){
					System.out.println("Was that third parameter an integer? I think not.");
					doprotect = false;
				}
				if(doprotect){
					ChunkProtect cp = new ChunkProtect(s, x);
					cp.removechunk();
				}
			}
			}
			}
	
	public void doprotectPayback(MC_Entity ent, MC_Player plr, MC_EventInfo ei) {
		float currenthealth = plr.getHealth();
	    if (ent.getType() == MC_EntityType.CHICKEN) {
	    	ei.isCancelled = true;
	    	plr.sendMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack a chicken!");
	    	Togglesendback = true;
	    	}
	    else if (ent.getType() == MC_EntityType.COW) {
	    	ei.isCancelled = true;
	        plr.sendMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack a cow!");
	        Togglesendback = true;
	        }
	    else if (ent.getType() == MC_EntityType.BAT){
	    	ei.isCancelled = true;
	    	plr.sendMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack a bat!");
	    	Togglesendback = true;
		    	}  
	    else if (ent.getType() == MC_EntityType.HORSE){
	    	ei.isCancelled = true;
	    	plr.sendMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack a horse!");
	    	Togglesendback = true;
            }
	    else if (ent.getType() == MC_EntityType.MUSHROOM_COW){
	    	ei.isCancelled = true;
	    	plr.sendMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack a mushroom cow!");
	    	Togglesendback = true;
	    }
	    else if (ent.getType() == MC_EntityType.OCELOT){
	    	ei.isCancelled = true;
	    	plr.sendMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack a ocelot!");
	    	Togglesendback = true;
	    }
	    else if (ent.getType() == MC_EntityType.PIG){
	    	ei.isCancelled = true;
	    	plr.sendMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack a pig!");
	    	Togglesendback = true;
	    }
	    else if (ent.getType() == MC_EntityType.RABBIT){
	    	ei.isCancelled = true;
	    	plr.sendMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack a rabbit!");
	    	Togglesendback = true;
	    }
	    else if (ent.getType() == MC_EntityType.SHEEP){
	    	ei.isCancelled = true;
	    	plr.sendMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack a sheep!");
	    	Togglesendback = true;
	    }
	    else if (ent.getType() == MC_EntityType.SNOWMAN){
	    	ei.isCancelled = true;
	    	plr.sendMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack a snowman!");
	    	Togglesendback = true;
	    }
	    else if (ent.getType() == MC_EntityType.SQUID){
	    	ei.isCancelled = true;
	    	plr.sendMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack a squid!");
	    	Togglesendback = true;
	    }
	    else if (ent.getType() == MC_EntityType.VILLAGER){
	    	ei.isCancelled = true;
	    	plr.sendMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack a villager!");
	    	Togglesendback = true;
	    }
	    else if (ent.getType() == MC_EntityType.WOLF){
	    	ei.isCancelled = true;
	    	plr.sendMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack a wolf!");
	    	Togglesendback = true;
	    }				
	else{
			ei.isCancelled = false;
		}
	if(Togglesendback){
		if(plr.getItemInHand().getId() == MC_ID.ITEM_WOODEN_SWORD){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a wooden sword.");
			plr.setHealth(currenthealth - 5);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_GOLDEN_SWORD){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a golden sword.");
			plr.setHealth(currenthealth - 5);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_STONE_SWORD){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a stone sword.");
			plr.setHealth(currenthealth - 6);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_IRON_SWORD){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a iron sword.");
			plr.setHealth(currenthealth - 7);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_DIAMOND_SWORD){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a diamond sword.");
			plr.setHealth(currenthealth - 8);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_WOODEN_AXE){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a wooden axe.");
			plr.setHealth(currenthealth - 4);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_GOLDEN_AXE){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a golden axe.");
			plr.setHealth(currenthealth - 4);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_STONE_AXE){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a stone axe.");
			plr.setHealth(currenthealth - 5);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_IRON_AXE){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a iron axe.");
			plr.setHealth(currenthealth - 6);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_DIAMOND_AXE){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a diamond axe.");
			plr.setHealth(currenthealth - 7);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_WOODEN_PICKAXE){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a wooden pickaxe.");
			plr.setHealth(currenthealth - 3);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_GOLDEN_PICKAXE){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a golden pickaxe.");
			plr.setHealth(currenthealth - 3);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_STONE_PICKAXE){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a stone pickaxe.");
			plr.setHealth(currenthealth - 4);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_IRON_PICKAXE){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a iron pickaxe.");
			plr.setHealth(currenthealth - 5);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_DIAMOND_PICKAXE){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a diamond pickaxe.");
			plr.setHealth(currenthealth - 6);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_WOODEN_SHOVEL){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a wooden shovel.");
			plr.setHealth(currenthealth - 2);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_GOLDEN_SHOVEL){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a golden shovel.");
			plr.setHealth(currenthealth - 2);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_STONE_SHOVEL){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a stone shovel.");
			plr.setHealth(currenthealth - 3);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_IRON_SHOVEL){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a iron shovel.");
			plr.setHealth(currenthealth - 4);
		}
		if(plr.getItemInHand().getId() == MC_ID.ITEM_DIAMOND_SHOVEL){
			plr.sendMessage("That's what you get for attacking a " + ent.getType() + " with a diamond shovel.");
			plr.setHealth(currenthealth - 5);
		}
		Togglesendback = false;
	}
	}
	public void getProtectionrelatedentitydamage(MC_Entity ent, MC_DamageType dmgType, double amt, MC_EventInfo ei){
		boolean attempt = false;	
		if(dmgType == MC_DamageType.PLAYER){
			attempt = true;
		}
	    if(dmgType == MC_DamageType.ARROW){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.ANVIL){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.CACTUS){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.DROWN){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.EXPLOSION){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.EXPLOSION_PLAYER){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.FALL){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.FALLING_BLOCK){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.FIREBALL){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.GENERIC){
	    	System.out.println("---------------------ANIMALGUARD----------------------");
	    	System.out.println("AnimalGuard has no idea what happened, blocking anyway");
	    	System.out.println("------------------------------------------------------");
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.IN_FIRE){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.IN_WALL){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.INDIRECT_MAGIC){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.LAVA){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.LIGHTING_BOLT){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.MAGIC){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.MOB){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.ON_FIRE){
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.OUT_OF_WORLD){
	    attempt = false; // that's what happens if you're a stupid animal
	    }
	    if(dmgType == MC_DamageType.STARVE){
	    attempt = true;
	    }
	    if(dmgType == MC_DamageType.THORNS){
	    attempt = true;
	    }
	    if(dmgType == MC_DamageType.THROWN){
	    attempt = true;
	    }
	    if(dmgType == MC_DamageType.UNSPECIFIED){
	    	System.out.println("---------------------ANIMALGUARD----------------------");
	    	System.out.println("AnimalGuard has no idea what happened, blocking anyway");
	    	System.out.println("------------------------------------------------------");
	    	attempt = true;
	    }
	    if(dmgType == MC_DamageType.WITHER){
	    	attempt = true;
	    }
	    if(attempt){
	    if (ent.getType() == MC_EntityType.CHICKEN) {
	    	ei.isCancelled = true;
	    	if (dmgType == MC_DamageType.ARROW){
            server.broadcastMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack one!");
	    	}
	    	}
	    else if (ent.getType() == MC_EntityType.COW) {
	    	ei.isCancelled = true;
	    	if (dmgType == MC_DamageType.ARROW){
	            server.broadcastMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack one!");
		    	}
	        }
	    else if (ent.getType() == MC_EntityType.BAT){
	    	ei.isCancelled = true;
	    	if (dmgType == MC_DamageType.ARROW){
	            server.broadcastMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack one!");
		    	}  
	    }
	    else if (ent.getType() == MC_EntityType.HORSE){
	    	ei.isCancelled = true;
	    	if (dmgType == MC_DamageType.ARROW){
	            server.broadcastMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack one!");
		    	}
            }
	    else if (ent.getType() == MC_EntityType.MUSHROOM_COW){
	    	ei.isCancelled = true;
	    	if (dmgType == MC_DamageType.ARROW){
	            server.broadcastMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack one!");
		    	}
	    }
	    else if (ent.getType() == MC_EntityType.OCELOT){
	    	ei.isCancelled = true;
	    	if (dmgType == MC_DamageType.ARROW){
	            server.broadcastMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack one!");
		    	}
	    }
	    else if (ent.getType() == MC_EntityType.PIG){
	    	ei.isCancelled = true;
	    	if (dmgType == MC_DamageType.ARROW){
	            server.broadcastMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack one!");
		    	}
	    }
	    else if (ent.getType() == MC_EntityType.RABBIT){
	    	ei.isCancelled = true;
	    	if (dmgType == MC_DamageType.ARROW){
	            server.broadcastMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack one!");
		    	}
	    }
	    else if (ent.getType() == MC_EntityType.SHEEP){
	    	ei.isCancelled = true;
	    	if (dmgType == MC_DamageType.ARROW){
	            server.broadcastMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack one!");
		    	}
	    }
	    else if (ent.getType() == MC_EntityType.SNOWMAN){
	    	ei.isCancelled = true;
	    	if (dmgType == MC_DamageType.ARROW){
	            server.broadcastMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack one!");
		    	}
	    }
	    else if (ent.getType() == MC_EntityType.SQUID){
	    	ei.isCancelled = true;
	    	if (dmgType == MC_DamageType.ARROW){
	            server.broadcastMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack one!");
		    	}
	    }
	    else if (ent.getType() == MC_EntityType.VILLAGER){
	    	ei.isCancelled = true;
	    	if (dmgType == MC_DamageType.ARROW){
	            server.broadcastMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack one!");
		    	}
	    }
	    else if (ent.getType() == MC_EntityType.WOLF){
	    	ei.isCancelled = true;
	    	if (dmgType == MC_DamageType.ARROW){
	            server.broadcastMessage("Animal Guard is on this server! Don't " + ChatColor.ITALIC + "try" + ChatColor.RESET + " to attack one!");
		    	}
	    }		
	}else{
			System.out.println("Your poor animals aren't being protected!");
			System.out.println("-----------------------------------------");
		}
	}
	}
