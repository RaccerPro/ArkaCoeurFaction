package fr.raccer.coeurfaction.commands;

import org.bukkit.entity.Player;

import fr.raccer.coeurfaction.Main;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;

public class Command_CoeurFaction_ResetSpawn {
	

	@Command(name = "coeurfaction.resetspawn", permission = "arka.coeurfaction.resetspawn", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
		
		Player player = a.getPlayer() ;
		MFaction mfaction = MUtilsPlayers.getMFaction(player) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		coeur.setTime_last_destroyed(0);
		player.sendMessage(Main.PREFIX+"§aLe temps de cooldown pour faire respawn le coeur a été réinitialisé.");
	}

}
