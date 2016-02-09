package ColorDictionary;

import PluginReference.*;


public class MyPlugin extends PluginBase
	{
		public static MC_Server server = null;
		public static boolean check = true;

		public void onStartup(MC_Server argServer)
		{
			System.out.println("Plugin starting! Lets hope it works! :)");
			server = argServer;
		}
		public void onShutdown()
		{
			System.out.println("ColorDictionary shutting down!");
		}

		public PluginInfo getPluginInfo() 
		{ 
			PluginInfo info = new PluginInfo();
			info.description = "A ChatColor dictionary: version 1.0 started";
			return info;
		}
		public void onPlayerJoin(MC_Player plr, String msg) 
		{
			plr.sendMessage(ChatColor.RED + "Chatcolor Dictionary can be viewed with /color");
		}
		public void onPlayerInput(MC_Player plr, String msg, MC_EventInfo ei)
		{
			if(ei.isCancelled)return;
			if(msg.equalsIgnoreCase("colorcheckon")){
				ei.isCancelled = true;
				plr.sendMessage("Toggling ColorDictionary checking on!");
				check = true;
			}
			if(msg.equalsIgnoreCase("colorcheckoff")){
				ei.isCancelled = true;
				plr.sendMessage("Toggleing ColorDictionary checking off!");
				check = false;
			}
			if(msg.equalsIgnoreCase("colourcheckon")){
				ei.isCancelled = true;
				plr.sendMessage("Toggling ColorDictionary checking on!");
				check = true;
			}
			if(msg.equalsIgnoreCase("colourcheckoff")){
				ei.isCancelled = true;
				plr.sendMessage("Toggleing ColorDictionary checking off!");
				check = false;
			}
			if(check){
			if(msg.contains("&b")){
				plr.sendMessage(ChatColor.AQUA + "Your message will contain aqua!");
			}
			if(msg.contains("&0")){
				plr.sendMessage(ChatColor.BLACK + "Your message will contain black!");
			}
			if(msg.contains("&9")){
				plr.sendMessage(ChatColor.BLUE + "Your message will contain blue!");
			}
			if(msg.contains("&l")){
				plr.sendMessage(ChatColor.BOLD + "Your message will contain bold!");
			}
			if(msg.contains("&3")){
				plr.sendMessage(ChatColor.DARK_AQUA + "Your message will contain dark aqua!");
			}
			if(msg.contains("&1")){
				plr.sendMessage(ChatColor.DARK_BLUE + "Your message will contain dark blue!");
			}
			if(msg.contains("&8")){
				plr.sendMessage(ChatColor.DARK_GRAY + "Your message will contains dark gray!");
			}
			if(msg.contains("&2")){
				plr.sendMessage(ChatColor.DARK_GREEN + "Your message will contain dark green!");
			}
			if(msg.contains("&5")){
				plr.sendMessage(ChatColor.DARK_PURPLE + "Your message will contain dark purple!");
			}
			if(msg.contains("&4")){
				plr.sendMessage(ChatColor.DARK_RED + "Your message will contain dark red!");
			}
			if(msg.contains("&6")){
				plr.sendMessage(ChatColor.GOLD + "Your message will contain gold!");
			}
			if(msg.contains("&7")){
				plr.sendMessage(ChatColor.GRAY + "Your message will contain gray!");
			}
			if(msg.contains("&a")){
				plr.sendMessage(ChatColor.GREEN + "Your message will contain green!");
			}
			if(msg.contains("&o")){
				plr.sendMessage(ChatColor.ITALIC + "Your message will contain italic!");
			}
			if(msg.contains("&d")){
				plr.sendMessage(ChatColor.LIGHT_PURPLE + "Your message will contain light purple!");
			}
			if(msg.contains("&k")){
				plr.sendMessage(ChatColor.MAGIC + "Your message will contain magic!");
			}
			if(msg.contains("&c")){
				plr.sendMessage(ChatColor.RED + "Your message will contain red!");
			}
			if(msg.contains("&m")){
				plr.sendMessage(ChatColor.STRIKETHROUGH + "Your message will contain strikethrough!");
			}
			if(msg.contains("&n")){
				plr.sendMessage(ChatColor.UNDERLINE + "Your message will contain underline!");
			}
			if(msg.contains("&f")){
				plr.sendMessage(ChatColor.WHITE + "Your message will contain white!");
			}
			if(msg.contains("&e")){
				plr.sendMessage(ChatColor.YELLOW + "Your message will contain yellow!");
			}
			}
			
			if(msg.equalsIgnoreCase("/color"))
			{
				ei.isCancelled = true;
				plr.sendMessage("Color codes can be used with the following commands:");
				plr.sendMessage("/blue shows blue-related color codes");
				plr.sendMessage("/red shows red and yellow-related color codes");
				plr.sendMessage("/green shows green-related color codes");
				plr.sendMessage("/base shows black, gray & white color codes");
				plr.sendMessage("/purple shows purple-related color codes");
				plr.sendMessage("/colortools shows text tool color codes");
				plr.sendMessage("the format will be the following:");
				plr.sendMessage("Gold - &6");			
				return;
			}
			
			if(msg.equalsIgnoreCase("/colour"))
			{
				ei.isCancelled = true;
				plr.sendMessage("Color codes can be used with the following commands:");
				plr.sendMessage("/blue shows blue-related color codes");
				plr.sendMessage("/red shows red and yellow-related color codes");
				plr.sendMessage("/green shows green-related color codes");
				plr.sendMessage("/base shows black, gray & white color codes");
				plr.sendMessage("/purple shows purple-related color codes");
				plr.sendMessage("/colortools shows text tool color codes");
				plr.sendMessage("the format will be the following:");
				plr.sendMessage("Gold - &6");			
				return;
			}
			if(msg.equalsIgnoreCase("/blue"))
			{
				ei.isCancelled = true;
				plr.sendMessage(ChatColor.DARK_BLUE + "Dark_Blue - &1");
				plr.sendMessage(ChatColor.DARK_AQUA + "Dark_Aqua - &3");
				plr.sendMessage(ChatColor.BLUE + "Blue - &9");
				plr.sendMessage(ChatColor.AQUA + "Aqua - &b");			
				return;
				}
				
			if(msg.equalsIgnoreCase("/red"))
			{
				ei.isCancelled = true;
				plr.sendMessage(ChatColor.DARK_RED + "Dark_Red - &4");
				plr.sendMessage(ChatColor.RED + "Red - &c");
				plr.sendMessage(ChatColor.YELLOW + "Yellow - &e");			
				return;
				}
			
			if(msg.equalsIgnoreCase("/green"))
		{
			ei.isCancelled = true;
			plr.sendMessage(ChatColor.DARK_GREEN + "Dark_Green - &2");
			plr.sendMessage(ChatColor.GREEN + "Green - &a");			
			return;
			}
			
			if(msg.equalsIgnoreCase("/base"))
		{
			ei.isCancelled = true;
			plr.sendMessage(ChatColor.BLACK + "Black - &0");
			plr.sendMessage(ChatColor.GRAY + "Gray - &7");
			plr.sendMessage(ChatColor.DARK_GRAY + "Dark_Gray - &8");
			plr.sendMessage(ChatColor.WHITE + "White - &f");			
			return;
			}
			
			if(msg.equalsIgnoreCase("/purple"))
			{
				ei.isCancelled = true;
				plr.sendMessage(ChatColor.DARK_PURPLE + "Dark_Purple - &5");
				plr.sendMessage(ChatColor.LIGHT_PURPLE + "Light_Purple - &d");			
				return;
				}
			
			if(msg.equalsIgnoreCase("/colortools"))
			{
				ei.isCancelled = true;
				plr.sendMessage(ChatColor.MAGIC + "Magic - &k");
				plr.sendMessage(ChatColor.RESET + ChatColor.BOLD + "Bold - &l");
				plr.sendMessage(ChatColor.RESET + ChatColor.STRIKETHROUGH + "Strikethrough, - &m");
				plr.sendMessage(ChatColor.RESET + ChatColor.UNDERLINE + "Underline - &n");
				plr.sendMessage(ChatColor.RESET + ChatColor.ITALIC + "Italic - &o");
				plr.sendMessage(ChatColor.RESET + "Reset, - &r");			
				return;
				}
			
			if(msg.equalsIgnoreCase("/colourtools"))
			{
				ei.isCancelled = true;
				plr.sendMessage(ChatColor.MAGIC + "Magic - &k");
				plr.sendMessage(ChatColor.RESET + ChatColor.BOLD + "Bold - &l");
				plr.sendMessage(ChatColor.RESET + ChatColor.STRIKETHROUGH + "Strikethrough, - &m");
				plr.sendMessage(ChatColor.RESET + ChatColor.UNDERLINE + "Underline - &n");
				plr.sendMessage(ChatColor.RESET + ChatColor.ITALIC + "Italic - &o");
				plr.sendMessage(ChatColor.RESET + "Reset, - &r");			
				return;
				}
		}
		public void onConsoleInput(String msg, MC_EventInfo ei){
			if(msg.equalsIgnoreCase("colorcheckon")){
				ei.isCancelled = true;
				System.out.println("Toggling ColorDictionary checking on!");
				check = true;
			}
			if(msg.equalsIgnoreCase("colorcheckoff")){
				ei.isCancelled = true;
				System.out.println("Toggleing ColorDictionary checking off!");
				check = false;
			}
			if(msg.equalsIgnoreCase("colourcheckon")){
				ei.isCancelled = true;
				System.out.println("Toggling ColorDictionary checking on!");
				check = true;
			}
			if(msg.equalsIgnoreCase("colourcheckoff")){
				ei.isCancelled = true;
				System.out.println("Toggleing ColorDictionary checking off!");
				check = false;
			}
		}	
	}
