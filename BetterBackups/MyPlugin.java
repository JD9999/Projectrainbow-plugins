package BetterBackups;

import java.io.File;
import java.io.IOException;
import java.util.List;

import PluginReference.*;

public class MyPlugin extends PluginBase {

	public static List<String> info;
	public static MC_Server server;
	public static File backupDirectory = new File("plugins_mod" + File.separator + "BetterBackups");
	public static File backupfile = new File(backupDirectory  + File.separator + "backupdata.txt");
	public static String rainbowLoc = ".";
	
	public void onStartup(MC_Server svr){
		System.out.println("Plugin starting! lets hope this works! :)");
		if(backupfile.exists()) System.out.println("Backup data info file already exists!");
		else{
			backupDirectory.mkdirs();
			try {
				backupfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("File created!");
		}
		server = svr;
		server.registerCommand(new BackupCommand());
		List<String> data = BackupManager.determineLineData(backupfile);
		info = data;
	}
	public void onShutdown(){
		System.out.println("BetterBackups shutting down!");
	}
	
	public PluginInfo getPluginInfo(){
		PluginInfo info = new PluginInfo();
		info.description = "A backup plugin: version 0.1 started!";
		info.name = "Better Backups";
		info.version = "V0.1";
		return info;
	}
	
}
