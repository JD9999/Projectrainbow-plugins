package bedClaim;

import java.util.List;

import PluginReference.MC_Command;
import PluginReference.MC_Player;

public class CmdRepBed implements MC_Command{

	@Override
	public List<String> getAliases() {
		return null;
	}

	@Override
	public String getCommandName() {
		return "repbed";
	}

	@Override
	public String getHelpLine(MC_Player arg0) {
		return "empties the bed file";
	}

	@Override
	public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
		return null;
	}

	@Override
	public void handleCommand(MC_Player plr, String[] arg1) {
		MyPlugin.bedfile.delete();
		CmdGuestBed.bedFile.delete();
		MyPlugin.doStartup();
	}

	@Override
	public boolean hasPermissionToUse(MC_Player plr) {
		if(plr !=null) return plr.isOp();
		else return true;
	}

}
