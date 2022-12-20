package fr.raccer.coeurfaction.commands.tests;

import fr.raccer.coeurfaction.placeholdersapi.expansions.Placeholder_CoeurFaction;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;

public class Command_CoeurFaction_TestPlaceholders {

	@Command(name = "coeurfaction.testPlaceholders", permission = "arka.coeurfaction.testPlaceholders", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
		
		Placeholder_CoeurFaction.test(a.getPlayer());
		
	}
	
}
