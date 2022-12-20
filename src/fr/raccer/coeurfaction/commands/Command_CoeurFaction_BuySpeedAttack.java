package fr.raccer.coeurfaction.commands;

import org.bukkit.entity.Player;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.upgrades.UpgradeLevel;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;

public class Command_CoeurFaction_BuySpeedAttack {
	

	@Command(name = "coeurfaction.buySpeedAttack", permission = "arka.coeurfaction.buySpeedAttack", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
		
		Player player = a.getPlayer() ;
		FPlayer fplayer = FPlayers.getInstance().getByPlayer(player) ;
		
		if(fplayer.getFaction().isWilderness()) {
			player.sendMessage("§cVous devez avoir une faction pour faire cette commande.");
			return ;
		}
		
		if(!fplayer.getFaction().getFPlayerAdmin().getAccountId().equalsIgnoreCase(fplayer.getAccountId())) {
			player.sendMessage("§cVous n'êtes pas chef de votre faction.");
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(player) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		UpgradeLevel current_level = coeur.getUpgradeSpeedAttackLevel() ;
		UpgradeLevel next_level = current_level.nextLevel() ;
		
		if(next_level == null) {
			player.sendMessage("§cCette amélioration est déjà au maximum.");
			return ;
		}
		
		int price_next_upgrade = next_level.getPrice_to_unlock() ; 
		
		if(!coeur.containsPoints(price_next_upgrade)) {
			player.sendMessage("§cVous n'avez pas assez de points d'améliorations disponibles.");
			return ;
		}
		
		coeur.takePoints(price_next_upgrade);
		coeur.setUpgradeSpeedAttackLevel(next_level);
		player.sendMessage("§6Votre attaque est désormais en §e"+next_level.getName());
	}

}
