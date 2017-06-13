package SoulbindEnchantment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import PluginReference.*;

public class MyPlugin extends PluginBase{

	private Map<MC_Player, List<MC_ItemStack>> deadSoulbinds = new LinkedHashMap<MC_Player, List<MC_ItemStack>>();
	public static int EMERALD_COST = 1;
	public static int XP_COST = 5;
	public static String EMERALD_CONFIG_ID = "emeralds_per_cast_cost:";
	public static String XP_CONFIG_ID = "xp_per_cast_cost:";

	public void onStartup(MC_Server server){
		System.out.println("Plugin starting! Lets hope this works :)");
		loadConfig();
		server.registerCommand(new SoulbindCommand());
	}

	public void onAttemptItemDrop(MC_Player plr, MC_ItemStack stack, MC_EventInfo info){
		if(isBinded(stack)){
			info.isCancelled = true;
			giveBack(stack.getFriendlyName(), plr);
		}
	}

	public void onPlayerDeath(MC_Player entVictim, MC_Player entKiller, MC_DamageType dmgType, String string){
		List<MC_ItemStack> soulbinds = new ArrayList<MC_ItemStack>();
		for(MC_ItemStack stack : entVictim.getInventory()){
			if(isBinded(stack)) soulbinds.add(stack);
		}
		deadSoulbinds.put(entVictim, soulbinds);
	}

	public void onPlayerRespawn(MC_Player plr){
		List<MC_ItemStack> items = deadSoulbinds.get(plr);
		plr.setInventory(items);
	}
	public static boolean isBinded(MC_ItemStack stack){
		List<String> lores = stack.getLore();
		if(lores !=null){
			if(lores.isEmpty()) return false;
			for(String lore : lores){
				if(lore.startsWith("sb-binded")){
					return true;
				}
			}
		}else System.out.println("Lore is null!");	
		return false;
	}

	private void loadConfig() {
		File config = new File("plugins_mod" + File.separator + "SoulbindEnchantment" + File.separator + "config.txt");
		try {
			List<String> lines = Files.readAllLines(config.toPath());
			for(String line : lines){
				if(line.startsWith(EMERALD_CONFIG_ID)){
					EMERALD_COST = Integer.parseInt(line.substring(23));
				}else if(line.startsWith(XP_CONFIG_ID)){
					XP_COST = Integer.parseInt(line.substring(17));
				}
			}
		} catch (IOException e) {
			if(e instanceof NoSuchFileException){
				System.out.println("File doesn't exist! Using default values, and creating config now.");
				File directories = new File("plugins_mod" + File.separator + "SoulbindEnchantment" + File.separator);
				directories.mkdirs();
				try{
					config.createNewFile();
					BufferedWriter writer = new BufferedWriter(new FileWriter(config));
					writer.write(EMERALD_CONFIG_ID + EMERALD_COST);
					writer.newLine();
					writer.write(XP_CONFIG_ID + XP_COST);
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else e.printStackTrace();
		}
	
	}

	private void giveBack(String friendlyName, MC_Player plr) {
		boolean found = false;
		List<MC_ItemStack> stacks = plr.getInventory();
		for(int i = 0; i < 9; i++){
			if(found) continue;
			MC_ItemStack stack = stacks.get(i);
			if(stack.getFriendlyName().equals(friendlyName)){
				found = true;
				stack.setCount(stack.getCount() + 1);
				plr.updateInventory();
			}
		}
		if(found == false) System.err.println("Cannot give back dropped soulbinded item " + friendlyName + " to " + plr.getName());
	}


}
