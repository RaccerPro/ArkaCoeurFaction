package fr.raccer.coeurfaction.commands;

import org.bukkit.entity.Player;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;

public class Command_CoeurFaction {
	
	@Command(name = "coeurfaction", permission = "arka.coeurfaction", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
		
		Player player = a.getPlayer() ;
		FPlayer fplayer = FPlayers.getInstance().getByPlayer(player) ;
		
		if(!fplayer.getFaction().getFPlayerAdmin().getAccountId().equalsIgnoreCase(fplayer.getAccountId())) {
			player.sendMessage("§cVous n'êtes pas chef de votre faction.");
			return ;
		}
		
		//MFaction fac = MUtilsPlayers.getMFaction(player) ;
		
	}

}
