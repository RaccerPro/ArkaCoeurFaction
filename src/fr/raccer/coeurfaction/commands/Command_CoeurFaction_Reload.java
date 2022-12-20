package fr.raccer.coeurfaction.commands;

import fr.raccer.coeurfaction.Main;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;

public class Command_CoeurFaction_Reload {
	

	@Command(name = "coeurfaction.reload", permission = "arka.coeurfaction.reload")
	public void onCoeurFaction(CommandArgs a) {
		Main.instance.loadMConfig();
		a.getSender().sendMessage("§aConfiguration reload avec succès.");
	}

}
