package RainbowRanks;

import java.io.File;

import PluginReference.*;

public class MyPlugin extends PluginBase{

public static MC_Server server = null;
public static String pluginDir = "plugins_mod" + File.separator + "RainbowRanks" + File.separator;
public static RankList list = new RankList();
	
public void onStartup(MC_Server svr){
	new File(pluginDir).mkdirs();
	System.out.println("Plugin starting! Lets hope it works! :)");
	server = svr;
	server.registerCommand(new Ranks());
	list.intoList();
}	
	
public PluginInfo getPluginInfo(){
	PluginInfo info = new PluginInfo();
	info.description = "A custom rank plugin: v0.1 started";
	info.name = "RainbowRanks";
	info.ref = this;
	info.version = "v0.1";
	return info;
}

public void onShutdown(){
	System.out.println("RainbowRanks shutting down!");
}

}
