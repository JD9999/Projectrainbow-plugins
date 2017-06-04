package FunHub;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import PluginReference.*;

public class MyPlugin extends PluginBase {

	private Map<MC_Player, MC_Location> locMap = new LinkedHashMap<MC_Player, MC_Location>();
	private static int tickDifference = 0;

	public void onStartup(MC_Server server){
		System.out.println("Plugin starting! Let's hope it works :)");
		HubCommand.pushServer(server);
		InventoryHelper.pushServer(server);
		ItemHelper.pushServer(server);
		BlockChangeHelper.pushServer(server);
		server.registerCommand(new HubCommand());
	}

	public void onShutdown(){
		System.out.println("FunHub shutting down!");
	}

	public PluginInfo getPluginInfo(){
		PluginInfo info = new PluginInfo();
		info.description = "A hub plugin for Rainbow: V0.1 started";
		return info;
	}

	public void onPlayerJoin(MC_Player plr){		
		locMap.put(plr, plr.getLocation());	
		if(plr.hasPermission(KeyHelper.HUB_ACCESS) || plr.isOp()){
			List<MC_ItemStack> newInv = new ArrayList<MC_ItemStack>();
			boolean waiting = true;
			List<MC_ItemStack> stacks = plr.getInventory();
			if(stacks.size() > 0){
				for(MC_ItemStack stack : stacks){
					if(stack.getCustomizedName().equals(KeyHelper.KEY_NAME)){
						if(waiting){
							newInv.add(ItemHelper.getKey());
							waiting = false;
						}else{
							newInv.add(null);
						}
					}else if(stack.getId() == 0 || stack == null){
						if(waiting){
							newInv.add(ItemHelper.getKey());
							waiting = false;
						}else{
							newInv.add(null);
						}
					}else{
						newInv.add(stack);
					}
				}
				plr.setInventory(newInv);
			}else{
				List<MC_ItemStack> stack = new ArrayList<>();
				stack.add(ItemHelper.getKey());
				plr.setInventory(stack);
			}

		}
	}

	public void onAttemptPlayerMove(MC_Player plr, MC_Location locFrom, MC_Location locTo, MC_EventInfo ei){
		MC_Location lastLoc = locMap.get(plr);
		lastLoc = roundLoc(lastLoc);
		if(!(lastLoc.getBlockX() == locTo.getBlockX() && lastLoc.getBlockY() == locTo.getBlockY() && lastLoc.getBlockZ() == locTo.getBlockZ())){
			BlockChangeHelper helper = InventoryHelper.changeHelperMap.get(plr);
			locMap.put(plr, locTo);
			if(helper !=null){
				lastLoc = floorLoc(lastLoc);
				if(helper.isTracker()){
					lastLoc = longLoc(lastLoc);
					if(!plr.getMotionData().onGround) return;
				}
				helper.add(lastLoc);

			}	
		}
	}

	private MC_Location floorLoc(MC_Location location) {
		return new MC_Location(location.x, location.y - 1, location.z, location.dimension);
	}

	/**
	 * Removes the decimal off a location. 
	 * @param lastLoc
	 * @return
	 */
	private MC_Location longLoc(MC_Location lastLoc) {
		double oldX = lastLoc.x;
		double oldY = lastLoc.y;
		double oldZ = lastLoc.z;
		int dim = lastLoc.dimension;
		String sX = String.valueOf(oldX);
		String sY = String.valueOf(oldY);
		String sZ = String.valueOf(oldZ);
		String sXInt = sX.substring(0, sX.indexOf('.'));
		String sYInt = sY.substring(0, sY.indexOf('.'));
		String sZInt = sZ.substring(0, sZ.indexOf('.'));
		double dX = Double.parseDouble(sXInt);
		double dY = Double.parseDouble(sYInt);
		double dZ = Double.parseDouble(sZInt);
		return new MC_Location(dX, dY, dZ, dim);
	}

	private MC_Location roundLoc(MC_Location loc) {
		return new MC_Location(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc.dimension);
	}

	public static List<Integer> checkIncrementor(List<Integer> list) {
		List<Integer> replaceIndex = new ArrayList<>();
		for(BlockChangeHelper helper : InventoryHelper.changeHelperMap.values()){
			for(int i = 0; i < list.size(); i++){
				try{
					MC_Location plrLoc = helper.player.getLocation();
					MC_Location posLoc = helper.locations.get(i);
					if(plrLoc.getBlockX() == posLoc.getBlockX() && plrLoc.getBlockY() == posLoc.getBlockY() && plrLoc.getBlockZ() == posLoc.getBlockZ()) continue;
					else{
						int integer = list.get(i);
						if(integer + tickDifference > 15){
							replaceIndex.add(i);
						}
					}
				}catch(IndexOutOfBoundsException e){
					return list;
				}



			}
		}
		tickDifference = 0;
		return replaceIndex;
	}

	public void onTick(int tick){
		tickDifference++;
	}

}
