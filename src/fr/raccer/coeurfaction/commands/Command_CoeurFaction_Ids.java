package fr.raccer.coeurfaction.commands;

import fr.raccer.coeurfaction.Main;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;

public class Command_CoeurFaction_Ids {
	

	@Command(name = "coeurfaction.ids", permission = "arka.coeurfaction.ids")
	public void onCoeurFaction(CommandArgs a) {
		
		String str = Main.PREFIX+"§6Liste des IDs des améliorations : \n\n" ;
		
		
		for(TypeUpgrades t : TypeUpgrades.values())
			str+= "§3» §e"+t.name()+"\n" ;
		
		a.getSender().sendMessage(str);
	}

}
