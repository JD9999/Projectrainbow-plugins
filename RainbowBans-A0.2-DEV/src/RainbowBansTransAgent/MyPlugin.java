package RainbowBansTransAgent;

import PluginReference.*;

public class MyPlugin extends PluginBase{
	
	public void onStartup(MC_Server server){
		System.out.println("Plugin starting! lets hope this works! :)");
	}
	public void onShutdown(){
		System.out.println("Not proper plugin yet - will remove bytecode on shutdown in initial release and beta builds.");
	}
	public PluginInfo getPluginInfo(){
		PluginInfo info = new PluginInfo();
		info.description = "a jar file helping to do bytecode manipulation - V0.1 started for RainbowBans A0.2!";
		return info;
	}
}
