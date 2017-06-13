package RainbowRanks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
*/
import PluginReference.MC_Command;
import PluginReference.MC_Player;

public class Ranks implements MC_Command {

	@Override
	public String getCommandName() {
		return "ranks";
	}

	@Override
	public List<String> getAliases() {
	List<String> ls = new ArrayList<String>();
	ls.add("rranks");
	ls.add("rakns"); //Spelling.
	ls.add("rrakns");
	return ls;
	}

	@Override
	public String getHelpLine(MC_Player plr) {
		return "/ranks help for info.";
	}

	@Override
	public void handleCommand(MC_Player plr, String[] args) {
		if(args.length < 1){
			parse(plr, getHelpLine(plr));
			return;
		}
		String doublequote = String.valueOf('"');
		if(args[0].equalsIgnoreCase("help")){
			parse(plr, "You can use the /ranks or /rranks command for the following:");
			parse(plr, "The square brackets are required. The triangle brackets are not.");
			parse(plr, doublequote + "/ranks add [rank] <order>" + doublequote + "adds a new rank at the bottom of the rank chart!");
			parse(plr, doublequote + "/ranks add [rank] [order]" + doublequote + "adds a new rank at the specified order ([order] is a number. Use /ranks list for each rank's order)");
			parse(plr, doublequote + "/ranks remove [rank]" + doublequote + " removes a rank!");
			parse(plr, doublequote + "/ranks list" + doublequote + " lists all the ranks in their order!");
			parse(plr, doublequote + "/ranks clear" + doublequote + " clears the rank data");
			parse(plr, doublequote + "/ranks player show" + doublequote + " show all the player's names, and their rank");
			parse(plr, doublequote + "/ranks player [rank] <player>" + doublequote + "adds a player to the rank. If it doesn't have the <player> argument, you yourself will be added.");
		}else if(args[0].equalsIgnoreCase("add")){
			if(args.length > 1){
				if(args.length > 2){
					if(args.length > 3){
						parse(plr, getHelpLine(plr));
					}else{
						MyPlugin.list.addAt(args[1], Integer.parseInt(args[2]));
						if(MyPlugin.list.exists(args[1])) parse(plr, "Added successfully!");
					}
				}else{
					MyPlugin.list.add(args[1]);
					if(MyPlugin.list.exists(args[1])) parse(plr, "Added successfully!");
				}
			}else{
				parse(plr, doublequote + "/ranks add [rank]" + doublequote + "adds a new rank at the bottom of the rank chart!");
				parse(plr, doublequote + "/ranks add [rank] [order]" + doublequote + "adds a new rank at the specified order ([order] is a number. Use /ranks list for each rank's order)");
			}
		}else if(args[0].equalsIgnoreCase("remove")){
			if(args.length > 1){
				MyPlugin.list.delete(args[1]);
				if(!MyPlugin.list.exists(args[1])) parse(plr, "Removed successfully!");
			}else{
				parse(plr, doublequote + "/ranks remove [rank]" + doublequote + " removes a rank!");
			}
		}else if(args[0].equalsIgnoreCase("list")){
			List<String> ranks = MyPlugin.list.order;
			for(int i = 0; i < ranks.size(); i++){
				String rank = ranks.get(i);
				parse(plr, rank);
			}
			parse(plr, "List read completed!");
		}else if(args[0].equalsIgnoreCase("clear")){
			RankList.playerfile.delete();
			RankList.rankfile.delete();
			try {
				RankList.playerfile.createNewFile();
				RankList.rankfile.createNewFile();
				MyPlugin.list.intoList();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(args[0].equalsIgnoreCase("player")){
			if(args.length > 1){
				if(args.length > 2){
					if(args.length > 3){
						parse(plr, "Incorrect syntax! " + getHelpLine(plr));
					}else{
						String player = args[2];
						MC_Player mc_player = MyPlugin.server.getOnlinePlayerByName(player);
						List<MC_Player> ls = MyPlugin.server.getPlayers();
						for(int i = 0; i < ls.size(); i++){
							MC_Player plr1 = ls.get(i);
							if(plr1.getName().equalsIgnoreCase(player)){
								MyPlugin.list.addPlayer(plr1, args[1]);
								parse(plr, "Added successfully!");
								return;
							}
						}
						parse(plr, "Not found in online player list. Parsing offline player list.");
						List<MC_Player> off = MyPlugin.server.getOfflinePlayers();
						for(int i = 0; i < off.size(); i++){
							MC_Player plr1 = off.get(i);
							if(plr1.getName().equalsIgnoreCase(player)){
								MyPlugin.list.addPlayer(plr1, args[1]);
								parse(plr, "Added successfully!");
								return;
							}
						}
						parse(plr, "Finished parsing offline player list. Parsing final test.");
						if(mc_player !=null){
							MyPlugin.list.addPlayer(mc_player, args[1]);
						}else parse(plr, "Player not recognized!");
					}
					
				}else{
					if(args[1].equalsIgnoreCase("show")){
						List<String> ls = MyPlugin.list.plrOrder;
						for(int i = 0; i < ls.size(); i++){
							String uuid = ls.get(i).substring(0, ls.get(i).indexOf(":"));
							String name = MyPlugin.server.getLastKnownPlayerNameFromUUID(uuid);
							parse(plr, name + ls.get(i).replace(uuid, ""));
						}
					}else MyPlugin.list.addPlayer(plr, args[1]);
				}
				
			}else parse(plr, "Incorrect syntax! " + getHelpLine(plr));
		}
	}

	@Override
	public boolean hasPermissionToUse(MC_Player plr) {
		return true;
	}

	@Override
	public List<String> getTabCompletionList(MC_Player plr, String[] args) {
		return null;
	}

	private void parse(MC_Player plr, String message){
		if(plr == null) System.out.println(message);
		else plr.sendMessage(message);
	}

}
