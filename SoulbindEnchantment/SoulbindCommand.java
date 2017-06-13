package SoulbindEnchantment;

import java.util.ArrayList;
import java.util.List;

import PluginReference.MC_Command;
import PluginReference.MC_ItemStack;
import PluginReference.MC_Player;

public class SoulbindCommand implements MC_Command {

	@Override
	public List<String> getAliases() {
		return null;
	}

	@Override
	public String getCommandName() {
		return "soulbind";
	}

	@Override
	public String getHelpLine(MC_Player plr) {
		return "Type /soulbind for the soulbind help page";
	}

	@Override
	public List<String> getTabCompletionList(MC_Player plr, String[] args) {
		return null;
	}

	@Override
	public void handleCommand(MC_Player plr, String[] args) {
		if(plr == null){
			System.out.println("Only players can use the soulbind commands!");
		}else if(args.length == 0){
			plr.sendMessage("Commands are as follows:");
			plr.sendMessage("/soulbind bind - binds the item in your main hand");
			plr.sendMessage("/soulbind bindoff - binds the item in your off hand");
			plr.sendMessage("/soulbind list - lists all your binded objects");
		}else{
			String mainArg = obtainArg(args, 0);
			switch(mainArg){
			case "bind":
				bind(plr, plr.getItemInHand(), "on-hand");
				break;
			case "bindoff":
				bind(plr, plr.getItemInOffHand(), "off-hand");
				break;
			case "list":
				for(MC_ItemStack stack : plr.getInventory()){
					if(stack.getId() == 0)continue;
					if(MyPlugin.isBinded(stack)) plr.sendMessage(stack.getFriendlyName());
				}
				break;
			}
		}
	}

	@Override
	public boolean hasPermissionToUse(MC_Player plr) {
		return plr!=null ? plr.hasPermission("soulbind.use") : true;
	}

	private String obtainArg(String[] args, int i) {
		return args.length >= i + 1 ? args[i] : null;
	}

	private void bind(MC_Player plr, MC_ItemStack targetStack, String hand) {
		//Make the player pay for it
		int emeraldCount = MyPlugin.EMERALD_COST;
		List<MC_ItemStack> duplicateInventory = new ArrayList<>();
		duplicateInventory.addAll(plr.getInventory());
		for(MC_ItemStack stack : duplicateInventory){
			//Remove emeralds
			if(stack.getId() == 388){
				int stackSize = stack.getCount();
				if(emeraldCount == 0) continue;
				else if(stackSize < emeraldCount){
					stack.setCount(0);
					emeraldCount = emeraldCount - stackSize;
				}else if(stackSize == emeraldCount){
					stack.setCount(0);
					emeraldCount = 0;
				}else{
					//Stack size > emeraldCount
					stack.setCount(stackSize - emeraldCount);
					emeraldCount = 0;
				}
			}	
		}
		if(emeraldCount > 0){
			plr.sendMessage("Unable to soulbind! You need to have " + MyPlugin.EMERALD_COST + " emeralds to soulbind!");
		}else{
			plr.setInventory(duplicateInventory);
		}
		int xp = plr.getLevel();
		if(xp < MyPlugin.XP_COST){
			plr.sendMessage("Unable to soulbind! You need to be level " + MyPlugin.XP_COST + " to soulbind!");
			return;
		}else{
			plr.setLevel(xp - MyPlugin.XP_COST);
		}
		//Add the lores
		List<String> addedLores = new ArrayList<String>();
		addedLores.add("sb-binded:true");
		addedLores.add("sb-uuid:" + plr.getUUID().toString());
		addedLores.add("sb-hand:" + hand);
		addedLores.add("sb-xp_cost:" + MyPlugin.XP_COST);
		addedLores.add("sb-emeralds_cost:" + MyPlugin.EMERALD_COST);
		targetStack.setLore(addedLores);
		//Command feedback
		plr.sendMessage("Bound " + targetStack.getFriendlyName());
	}

}
