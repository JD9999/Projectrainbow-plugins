package ZombieDoctor;

import PluginReference.*;

public class MyPlugin extends PluginBase
	{
		public static MC_Server server = null;
		public void onStartup(MC_Server argServer)
		{
			System.out.println("Zombie Doctor starting! Lets hope it works! :)");
			server = argServer;
		}
		public void onShutdown()
		{
			System.out.println("Zombie Doctor shutting down!");
		}

		public PluginInfo getPluginInfo() 
		{ 
			PluginInfo info = new PluginInfo();
			info.description = "Zombies heal you - version 0.1 started";
			return info;
		}
		public void onServerFullyLoaded()
		{
			server.setServerMOTD("Zombie Doctor ready!");
			}

		public void onAttemptEntityDamage(MC_Entity ent, MC_DamageType dmgType, double amt, MC_EventInfo ei)  {
			if(dmgType == MC_DamageType.MOB){
				if(ent.getAttacker().getType() == MC_EntityType.ZOMBIE){
					ei.isCancelled = true;
					ent.setHealth(20.0F);
				}	
			}
		}
		public void onAttemptEntityInteract(MC_Player plr, MC_Entity ent, MC_EventInfo ei)
		  {
		    if (ent.getType() == MC_EntityType.ZOMBIE) {
		      plr.setHealth(20.0F);
		    }
		  }
		}
