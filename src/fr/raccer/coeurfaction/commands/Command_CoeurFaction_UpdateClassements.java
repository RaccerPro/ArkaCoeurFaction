package fr.raccer.coeurfaction.commands;

import fr.raccer.coeurfaction.Main;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;

public class Command_CoeurFaction_UpdateClassements {
	

	@Command(name = "coeurfaction.updateClassements", permission = "arka.coeurfaction.updateClassements")
	public void onCoeurFaction(CommandArgs a) {
		Main.instance.getClassementManager().update();
		a.getSender().sendMessage("§aActualisation du classement faction avec succès.");
	}

}
