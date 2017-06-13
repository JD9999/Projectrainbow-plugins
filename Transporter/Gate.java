package Transporter;

import java.util.ArrayList;
import java.util.List;

import PluginReference.MC_Block;
import PluginReference.MC_DirectionNESWUD;
import PluginReference.MC_Location;
import PluginReference.MC_Player;
import PluginReference.MC_Sign;
import PluginReference.MC_World;
import PluginReference.RainbowUtils;

public class Gate {

	private String name;
	private List<MC_Location> locs = new ArrayList<MC_Location>();
	private List<Gate> links = new ArrayList<Gate>();
	private boolean eastOrWest = true;
	private MC_DirectionNESWUD direction;
	
	public Gate(String name, List<MC_Location> locs){
		this.name = name;
		this.locs.addAll(locs);
	}
	
	public boolean isEastOrWest(){
		return eastOrWest;
	}
	
	public void setEastOrWest(boolean eastOrWest){
		this.eastOrWest = eastOrWest;
	}
	
	public void setDirection(MC_DirectionNESWUD dir){
		this.direction = dir;
		setEastOrWest(dir == MC_DirectionNESWUD.EAST || dir == MC_DirectionNESWUD.WEST);
	}
	
	public String getName(){
		return name;
	}
	
	public MC_Location getLocation(){
		MC_Location baseLoc = getTransportLocations().get(1);
		return baseLoc;
	}
	
	public List<Gate> getAllLinks(){
		return links;
	}
	
	public void addLink(Gate link){
		//Should make sure it doesn't go in an endless loop.
		if(!links.contains(link)){
			links.add(link);
			link.addLink(this);
		}
	}
	
	public void removeLink(Gate link){
		if(links.contains(link)){
			links.remove(link);
			link.removeLink(this);
		}
		
	}
	
	public void sendPlayer(MC_Player plr, GateRequest request){
		Gate returnGate = request.returnGate();
		MC_Location loc = returnGate.getLocation();
		MC_Location teleportLocation = isEastOrWest()? new MC_Location(loc.x -1, loc.y, loc.z, loc.dimension): new MC_Location(loc.x, loc.y, loc.z + 1, loc.dimension);
		plr.teleport(teleportLocation);
	}

	public List<MC_Location> getTransportLocations() {
		List<MC_Location> locations = new ArrayList<MC_Location>();
		if(eastOrWest){
			MC_Location locOne = locs.get(0);
			MC_Location locSeven = locs.get(6);
			MC_Location locLast = locs.get(locs.size() - 1);
			int dim = locOne.dimension;
			int x = (int)locOne.x + 1;
			int y = (int)locSeven.y;
			for(int i = -3; i < 0; i++){
				int z = (int)locLast.z - i - 4;
				locations.add(new MC_Location(x,y,z,dim));
			}
		}else{
			MC_Location baseLoc = locs.get(8);
			for(int i = -2; i < 1; i++){
				MC_Location loc = new MC_Location(baseLoc.x - i, baseLoc.y + 1, baseLoc.z, baseLoc.dimension);
				locations.add(loc);
			}
		}
		
		return locations;
	}

	public List<MC_Location> getLocations() {
		return locs;
	}
	
	public MC_Sign getSign(){
		return RainbowUtils.getServer().getWorld(getLocation().dimension).getSignAt(getSignLocation());
	}

	public MC_Location getSignLocation() {
		MC_Location baseLoc = getLocation();
		MC_Location signLoc;
		if(direction == MC_DirectionNESWUD.EAST){
			signLoc = new MC_Location(baseLoc.x - 2, baseLoc.y + 3, baseLoc.z, baseLoc.dimension);
		}else if(direction == MC_DirectionNESWUD.WEST){
			signLoc = new MC_Location(baseLoc.x, baseLoc.y + 3, baseLoc.z, baseLoc.dimension);
		}else if(direction == MC_DirectionNESWUD.NORTH){
			signLoc = new MC_Location(baseLoc.x - 1, baseLoc.y + 3, baseLoc.z + 1, baseLoc.dimension);
		}else{
			//Direction is south or unspecified, in that case assume south
			signLoc = new MC_Location(baseLoc.x - 1, baseLoc.y + 3, baseLoc.z - 1, baseLoc.dimension);
		}
		return signLoc;
	}
	
	public void buildSign(){
		MC_Block wallSign = RainbowUtils.getServer().getBlock(68);
		MC_Location signLoc = getSignLocation();
		MC_World world = RainbowUtils.getServer().getWorld(signLoc.dimension);
		int subtype = getSignDataValues();
		world.setBlockAt(signLoc, wallSign, subtype);
	}

	public int getSignDataValues() {
		if(this.direction == MC_DirectionNESWUD.NORTH) return 3;
		else if(this.direction == MC_DirectionNESWUD.EAST) return 4;
		else if(this.direction == MC_DirectionNESWUD.WEST) return 5;
		else return 2;
	}

	public MC_DirectionNESWUD getDirection() {
		return direction;
	}
	
}
