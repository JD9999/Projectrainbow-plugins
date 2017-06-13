package RainbowRanks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import PluginReference.MC_Player;

public class RankList{

	public static File rankfile = new File(MyPlugin.pluginDir + "ranklist.txt");
	public static File playerfile = new File(MyPlugin.pluginDir + "plrranks.txt");
	public List<String> order = new ArrayList<String>();
	public List<String> plrOrder = new ArrayList<String>();
	
	public RankList(){
		if(!rankfile.exists())
			try {
				rankfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if(!playerfile.exists())
			try {
				playerfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void add(String rankname){
		if(!this.order.contains(rankname)) order.add(rankname);
		writeList();
	}

	public void delete(String rankname){
		order.remove(rankname);
		writeList();
	}

	public int size() {
		try {
			List<String> lines = Files.readAllLines(rankfile.toPath());
			return lines.size();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean exists(String rankname) {
		for(int i = 0; i < order.size(); i++){
			String rank = order.get(i);
			if(rank.contains(rankname)) return true;
			else continue;
		}
		return false;
	}

	public void addAt(String rankname, int order) {
		if(!this.order.contains(rankname)) this.order.add(order, rankname);
		writeList();
	}

	public void addPlayer(MC_Player plr, String rankname) {
		plrOrder.add(plr.getUUID().toString() + ": " + rankname);
		if(!this.order.contains(rankname)) order.add(rankname);
		writeList();
	}
	public boolean checkPlayerInRank(MC_Player plr, String rankname) {
		for(int i = 0; i < plrOrder.size(); i++){
			String info = plrOrder.get(i);
			if(info.equalsIgnoreCase(plr.getUUID().toString() + ": " + rankname)) return true;
			else continue;
		}
		return false;
	}

	public void removePlayer(MC_Player plr) {
		for(int i = 0; i < plrOrder.size(); i++){
			String info = plrOrder.get(i);
			if(info.contains(plr.getUUID().toString())) plrOrder.remove(i);
		}	
	}

	public void intoList(){
		try {	
			List<String> lines = Files.readAllLines(rankfile.toPath());
			order.clear();
			order.addAll(lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {	
			List<String> lines = Files.readAllLines(playerfile.toPath());
			plrOrder.clear();
			plrOrder.addAll(lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeList() {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(rankfile));
			for(int i = 0; i < order.size(); i++){
				writer.write(order.get(i));
				writer.newLine();
			}
			writer.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(playerfile));
			for(int i = 0; i < plrOrder.size(); i++){
				writer.write(plrOrder.get(i));
				writer.newLine();
			}
			writer.close();
			}catch(IOException e){
				e.printStackTrace();
			}
	}
}
