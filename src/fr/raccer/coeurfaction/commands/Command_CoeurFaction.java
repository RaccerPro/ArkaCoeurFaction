package fr.raccer.coeurfaction.commands;

import org.bukkit.entity.Player;

import cc.javajobs.factionsbridge.bridge.infrastructure.struct.FPlayer;
import fr.raccer.coeurfaction.Main;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.utils.methods.MUtilsFactions;

public class Command_CoeurFaction {
	
	@Command(name = "coeurfaction", permission = "arka.coeurfaction", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
		
		Player player = a.getPlayer() ;
		FPlayer fplayer = MUtilsFactions.getInstance().getFPlayer(player) ;
		
		
		
		if(!fplayer.getFaction().getLeader().getName().equalsIgnoreCase(fplayer.getName())) {
			player.sendMessage(Main.PREFIX+"§cVous n'êtes pas chef de votre faction.");
			return ;
		}
		
		//MFaction fac = MUtilsPlayers.getMFaction(player) ;
		
	}

}
