package MobMerge;

import java.util.ArrayList;
import java.util.List;

import PluginReference.MC_DamageType;
import PluginReference.MC_Entity;
import PluginReference.MC_EntityType;
import PluginReference.MC_EventInfo;
import PluginReference.MC_Location;
import PluginReference.MC_Server;
import PluginReference.MC_World;
import PluginReference.PluginBase;
import PluginReference.PluginInfo;

public class MyPlugin extends PluginBase {

	private int increase = 0;
	private MC_EntityType pullType = null;

	public void onStartup(MC_Server svr){
		System.out.println("Plugin starting! Lets hope this works :)");
	}

	public void onShutdown(){
		System.out.println("MobMerge shutting down");
	}

	public PluginInfo getPluginInfo(){
		PluginInfo info = new PluginInfo();
		info.description = "Merges mobs to reduce server lag: V0.1 started";
		info.version = "V0.1";
		return info;
	}

	public void onAttemptEntitySpawn(MC_Entity ent, MC_EventInfo info){
		if(pullType == ent.getType()){
			pullType = null;
			return;
		}else{
			MC_World world = ent.getWorld();
			if(world == null){
				System.out.println("World is null!");
			}
			MC_EntityType type = ent.getType();
			MC_Location loc = ent.getLocation();
			increase = 0;
			List<MC_Entity> entities = ent.getNearbyEntities(16);
			List<MC_Entity> observables = new ArrayList<>();
			for(MC_Entity entity : entities){
				if(entity.getType() != type) continue;
				else if(entity.hasCustomName()) observables.add(entity);
				else{
					increase ++;
					entity.removeEntity();
				}
			}
			if(observables.size() == 1){
				MC_Entity entity = observables.get(0);
				String[] split = entity.getCustomName().split(" ");
				if(split.length != 2){
					pullType = type;
					world.spawnEntity(type, loc, String.valueOf(increase + 1) + " " + ent.getType().name() + "S");
					//Calling world.spawnEntity sends an event to this same method, meaning the code is never completed.
				}
			}else if(observables.size() > 1){
				MC_Entity entity = observable(observables);
				System.out.println(entity.getCustomName());
			}else{
				if(increase == 0){
					//No entities were of that type
					ent.setCustomName("1 " + ent.getType().name() + "S"); 
				}else{
					pullType = type;
					world.spawnEntity(type, loc, String.valueOf(increase + 1) + " " + ent.getType().name() + "S");
					//Calling world.spawnEntity sends an event to this same method, meaning the code is never completed.
				}
			}
		}
	}

	public void onNonPlayerEntityDeath(MC_Entity entVictim, MC_Entity entKiller, MC_DamageType dmgType){
		if(entVictim.hasCustomName()){
			List<MC_Entity> entity = new ArrayList<MC_Entity>();
			entity.add(entVictim);
			if(observable(entity) !=null){
				String customName = entVictim.getCustomName();
				int number = Integer.parseInt(customName.substring(0, 1));
				String type = customName.substring(2, customName.length() - 1);
				number --;
				if(number > 0){
					entVictim.setHealth(entVictim.getMaxHealth());
					entVictim.setCustomName(String.valueOf(number) + " " + type);
				}
			}
		}
	}

	/**
	 * Gets the entity that is responsible for the entity's count.
	 * When there are multiple entities responsible they become merged.
	 * @param observables the list of possible entities
	 * @return The entity that has been or is made to been responsible for the type count.
	 */
	private MC_Entity observable(List<MC_Entity> observables) {
		List<MC_Entity> holders = new ArrayList<MC_Entity>();
		for(MC_Entity ent : observables){
			String name = ent.getCustomName();
			for(MC_EntityType type : MC_EntityType.values()){
				String s = type.name() + "S";
				try{
					Integer.parseInt(name.substring(0, 1));
				}catch(NumberFormatException e){
					continue;
				}
				if(name.endsWith(s)) holders.add(ent);
			}
		}
		if(holders.size() == 1) return holders.get(0);
		else if(holders.size() == 0)return null;
		else{
			int totalCount = 0;
			MC_Entity holder = holders.get(0);
			for(int i = 1; i < holders.size(); i++){
				MC_Entity dead = holders.get(i);
				int count = Integer.parseInt(dead.getCustomName().substring(0, 1));
				totalCount = totalCount + count;
				dead.removeEntity();
			}
			int count = Integer.parseInt(holder.getCustomName().substring(0, 1));
			int newCount = count + totalCount;
			holder.setCustomName(newCount + " " + holder.getType().name() + "S");
			return holder;
		}
	}

}	