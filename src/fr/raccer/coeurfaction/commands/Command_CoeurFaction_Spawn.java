package fr.raccer.coeurfaction.commands;

import org.bukkit.entity.Player;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;

public class Command_CoeurFaction_Spawn {
	

	@Command(name = "coeurfaction.spawn", permission = "arka.coeurfaction.spawn", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
		
		Player player = a.getPlayer() ;
		FPlayer fplayer = FPlayers.getInstance().getByPlayer(player) ;
		
		if(!fplayer.getFaction().getFPlayerAdmin().getAccountId().equalsIgnoreCase(fplayer.getAccountId())) {
			player.sendMessage("§cVous n'êtes pas chef de votre faction.");
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(player) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		if(coeur.isSpawned()) {
			coeur.killCoeurFaction();
			player.sendMessage("§eVous venez de retirer le coeur de faction.");
			return ;
		}
		
		if(!coeur.canSpawn()) {
			player.sendMessage("§cIl reste "+coeur.getTimeStringBeforeCanSpawn()+" avant que vous puissiez mettre à nouveau votre coeur de faction.");
			return ;
		}
		
		if(!fplayer.isInOwnTerritory()) {
			player.sendMessage("§cVous ne pouvez poser le coeur de faction uniquement dans vos claims.");
			return ;
		}
		
		coeur.spawnWithLocation(player.getLocation());
		player.sendMessage("§aLe coeur a été placé avec succès !");
		
	}

}
