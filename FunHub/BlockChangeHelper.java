package FunHub;

import java.util.ArrayList;
import java.util.List;

import PluginReference.MC_Block;
import PluginReference.MC_Location;
import PluginReference.MC_Player;
import PluginReference.MC_Server;
import PluginReference.MC_World;

public class BlockChangeHelper{

	private Object lock = new Object();
	protected static Object valueLock = new Object();
	protected List<MC_Location> locations = new ArrayList<>();
	private List<MC_Block> blocks = new ArrayList<>();
	private List<Integer> incrementors = new ArrayList<>();
	protected MC_Player player;
	private MC_Block block;
	private boolean tracker;
	private static MC_Server server;
	private boolean active;

	public BlockChangeHelper(MC_Player player, MC_Block block, boolean tracker) {
		this.player = player;
		this.block = block;
		this.tracker = tracker;
		this.active = true;
	}

	public void setRepBlock(MC_Block block) {
		this.block = block;
	}

	public void setTracker(boolean tracker) {
		this.tracker = tracker;
	}

	public boolean isTracker() {
		return this.tracker;
	}

	public void add(MC_Location location) {
		synchronized(lock){ //Make sure every block is recorded with its location. It is by the INDEX of both lists.
			MC_World world = this.player.getWorld();
			MC_Block block = world.getBlockAt(location);
			synchronized(valueLock){
				locations.add(location);
				blocks.add(block);
				incrementors.add(0);
			}

			world.setBlockAt(location, this.block, this.block.getSubtype());
			if(locations.size() == blocks.size() && blocks.size() == incrementors.size()){
				List<Integer> overdues = MyPlugin.checkIncrementor(incrementors);
				if(overdues.size() > 0){
					int lastIndex = -1;
					for(Integer i : overdues){
						lastIndex = i;
					}
					synchronized(valueLock){
						List<MC_Location> locs = new ArrayList<>();
						List<MC_Block> blks = new ArrayList<>();
						List<Integer> incs = new ArrayList<>();
						for(int i = 0; i <= lastIndex; i++){
							MC_Location loc = locations.get(i);
							locs.add(loc);
							MC_Block blk = blocks.get(i);
							blks.add(blk);
							try{
								int inc = incrementors.get(i);
								incs.add(inc);
							}catch(IndexOutOfBoundsException e){
								//ignore
							}
							int index = wholeIndexOf(loc, locs);
							int size = locs.size();
							if(index == size -1){
								world.setBlockAt(loc, blk, blk.getSubtype());
							}
						}
						locations.removeAll(locs);
						blocks.removeAll(blks);
						incrementors.removeAll(incs);
					}
				}
			}else{
				System.out.println("Locations size: " + locations.size());
				System.out.println("Blocks size: " + blocks.size());
				System.out.println("Incrementors size: " + incrementors.size());
				System.out.println("FAILURE! List indexes do not line up! Shutting down server.");
				server.executeCommand("stop");
			}
		}
	}

	private int wholeIndexOf(MC_Location loc, List<MC_Location> locs) {
		for(int i = 0; i < locs.size(); i++){
			MC_Location location = locs.get(i);
			if(location.getBlockX() == loc.getBlockX() && location.getBlockY() == loc.getBlockY() && location.getBlockZ() == loc.getBlockZ()) return i;
		}
		return -1;
	}

	protected static void pushServer(MC_Server server){
		BlockChangeHelper.server = server;
	}


	public void stop() {
		synchronized(valueLock){
			locations.clear();
			blocks.clear();
			incrementors.clear();
			active = false;
		}
	}

	public boolean isActive(){
		return active;
	}

	public void revertAll() {
		synchronized(valueLock){
			int lastIndex = locations.size() -1;
			List<MC_Location> locs = new ArrayList<>();
			List<MC_Block> blks = new ArrayList<>();
			List<Integer> incs = new ArrayList<>();
			for(int i = 0; i <= lastIndex; i++){
				MC_Location loc = locations.get(i);
				locs.add(loc);
				MC_Block blk = blocks.get(i);
				blks.add(blk);
				try{
					int inc = incrementors.get(i);
					incs.add(inc);
				}catch(IndexOutOfBoundsException e){
					//ignore
				}
				int index = wholeIndexOf(loc, locs);
				int size = locs.size();
				if(index == size -1){
					player.getWorld().setBlockAt(loc, blk, blk.getSubtype());
				}
			}
			locations.removeAll(locs);
			blocks.removeAll(blks);
			incrementors.removeAll(incs);
		}
	}
}
