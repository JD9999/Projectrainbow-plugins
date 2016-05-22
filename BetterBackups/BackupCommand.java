package BetterBackups;

import java.util.ArrayList;
import java.util.List;

import PluginReference.MC_Command;
import PluginReference.MC_Player;

public class BackupCommand implements MC_Command {

	@Override
	public String getCommandName() {
		return "bbackup";
	}

	@Override
	public List<String> getAliases() {
		List<String> ls = new ArrayList<String>();
		ls.add("betterbackup");
		return ls;
	}

	@Override
	public String getHelpLine(MC_Player plr) {
		return "/bbackup help for backup command";
	}

	@Override
	public void handleCommand(MC_Player plr, String[] args) {
		if(args.length == 0){
			BackupManager.backupData(BackupManager.obtainBackupPath());
		}else if (args.length >= 1){
			if(args[0].equalsIgnoreCase("help")){
				parseMessage("/bbackup or /betterbackup can be used for the following commands.", plr);
				parseMessage("/bbackup will backup the server", plr);
				parseMessage("/bbackup silent will backup the server silently", plr);
				//parseMessage("/bbackup restore will schedule a restore", plr);
				//parseMessage("/bbackup restore instant will restart the server with the restore", plr);
				//plr.sendMessage("/bbackup time will show you the last time a backup was conducted");
				//plr.sendMessage("/bbackup backup will backup with specific preferences. Type /bbackup backup to find more");
			//}else if(args[0].equalsIgnoreCase("time")){
				
			}else if(args[0].equalsIgnoreCase("silent")){
				BackupManager.silent = true;
				BackupManager.backupData(BackupManager.obtainBackupPath());
			}
		}
	}

	@Override
	public boolean hasPermissionToUse(MC_Player plr) {
		if(plr == null) return true;
		String backupKey = "bb.b";
		String lengthyBackupKey = "betterbackups.backup";
		return (plr.hasPermission(backupKey) || plr.isOp() || plr.hasPermission(lengthyBackupKey));
	}

	@Override
	public List<String> getTabCompletionList(MC_Player plr, String[] args) {
		return null;
	}
	
	public void parseMessage(String mes, MC_Player plr){
		if(plr != null) plr.sendMessage(mes);
		else System.out.println(mes);
	}

}
