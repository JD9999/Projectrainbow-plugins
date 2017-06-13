package Transporter;

import java.util.ArrayList;
import java.util.List;

import PluginReference.MC_DirectionNESWUD;
import PluginReference.MC_Location;
import PluginReference.MC_World;
import PluginReference.RainbowUtils;

public class PortalBuilder {
	public static int incrementX = 0;
	protected static MC_DirectionNESWUD direction;

	private static MC_World world = null;

	public static List<MC_Location> build(MC_Location plrLoc){
		MC_Location buildLoc = findDesireableAreaIfExistant(plrLoc);
		List<MC_Location> locations = generateLocations(buildLoc);
		System.out.println("Building portal at dimension " + plrLoc.dimension);
		if(world == null) world = RainbowUtils.getServer().getWorld(plrLoc.dimension);
		for(MC_Location loc : locations){
			world.setBlockAt(loc, MyPlugin.PORTAL_BLOCK, MyPlugin.PORTAL_BLOCK.getSubtype());
		}
		return locations;
	}

	public static List<MC_Location> build(MC_Location plrLoc, MC_World world){
		PortalBuilder.world = world;
		return build(plrLoc);
	}

	private static MC_Location findDesireableAreaIfExistant(MC_Location plrLoc){
		int[] xzInc = getXZIncrements(getDirectionFacing(plrLoc));
		int xInc = xzInc[0];
		incrementX = xInc;
		int zInc = xzInc[1];
		return new MC_Location(plrLoc.x + xInc, plrLoc.y, plrLoc.z + zInc, plrLoc.dimension);
	}

	private static List<MC_Location> generateLocations(MC_Location loc){
		MC_Location buildLoc = roundOffLocation(loc);
		List<MC_Location> locations = new ArrayList<MC_Location>();
		for(int i = 0; i < 5; i++){ //i = 0, 1, 2, 3, 4
			if(incrementX == -2){
				MC_Location locZ = new MC_Location(buildLoc.x + i, buildLoc.y, buildLoc.z, buildLoc.dimension);
				locations.add(locZ);
				MC_Location locTop = new MC_Location(buildLoc.x + i, buildLoc.y + 4, buildLoc.z, buildLoc.dimension);
				locations.add(locTop);
				MC_Location locY = new MC_Location(buildLoc.x, buildLoc.y + i, buildLoc.z, buildLoc.dimension);
				locations.add(locY);
				MC_Location locSide = new MC_Location(buildLoc.x + 4, buildLoc.y + i, buildLoc.z, buildLoc.dimension);
				locations.add(locSide);
			}else{
				MC_Location locZ = new MC_Location(buildLoc.x, buildLoc.y, buildLoc.z + i, buildLoc.dimension);
				locations.add(locZ);
				MC_Location locTop = new MC_Location(buildLoc.x, buildLoc.y + 4, buildLoc.z + i, buildLoc.dimension);
				locations.add(locTop);
				MC_Location locY = new MC_Location(buildLoc.x, buildLoc.y + i, buildLoc.z, buildLoc.dimension);
				locations.add(locY);
				MC_Location locSide = new MC_Location(buildLoc.x, buildLoc.y + i, buildLoc.z + 4, buildLoc.dimension);
				locations.add(locSide);
			}
		}
		return locations;
	}

	public static MC_Location roundOffLocation(MC_Location location) {
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		MC_Location loc = new MC_Location((double)x, (double)y, (double)z, location.dimension);
		return loc;
	}

	private static MC_DirectionNESWUD getDirectionFacing(MC_Location loc){
		float yaw = loc.yaw;
		int indicator = (int)yaw - 45;
		if(indicator > 180) return MC_DirectionNESWUD.EAST;
		else if(indicator > 90) return MC_DirectionNESWUD.NORTH;
		else if(indicator > 0) return MC_DirectionNESWUD.WEST;
		else if(indicator > -90) return MC_DirectionNESWUD.SOUTH;
		else if(indicator > -180) return MC_DirectionNESWUD.EAST;
		else if(indicator > -270) return MC_DirectionNESWUD.NORTH;
		else if(indicator > -360) return MC_DirectionNESWUD.WEST;
		else if(indicator > -405) return MC_DirectionNESWUD.SOUTH;
		else{
			System.err.println("Yaw value is not accounted for! Setting default direction");
			System.err.println("Supplied yaw value is: " + String.valueOf(yaw));
			return MC_DirectionNESWUD.UNSPECIFIED;
		}
	}

	private static int[] getXZIncrements(MC_DirectionNESWUD direction){
		PortalBuilder.direction = direction;
		if(direction == MC_DirectionNESWUD.NORTH) return new int[]{-2, -1};
		else if(direction == MC_DirectionNESWUD.WEST) return new int[]{-1, -2};
		else if(direction == MC_DirectionNESWUD.SOUTH) return new int[]{-2, 1};
		else if(direction == MC_DirectionNESWUD.EAST) return new int[]{1, -2};
		else{
			return new int[]{-2, 1}; //South
		}
	}

}
