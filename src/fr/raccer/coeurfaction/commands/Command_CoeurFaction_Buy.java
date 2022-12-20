package fr.raccer.coeurfaction.commands;

import org.bukkit.entity.Player;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;

public class Command_CoeurFaction_Buy {
	

	@Command(name = "coeurfaction.buy", permission = "arka.coeurfaction.buy", inGameOnly = true)
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
		
		if(a.length() != 1) {
			player.sendMessage("§6/coeurfaction buy [ID]");
			return ;
		}
		
		TypeUpgrades type_upgrade = TypeUpgrades.convert(a.getArgs(0)) ;
		
		if(type_upgrade == null ) {
			player.sendMessage("§cID introuvable. /coeurfaction ids");
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(player) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		CoeurUpgrades upgrade = coeur.containsUpgrade(type_upgrade) ? 
				coeur.getUpgrade(type_upgrade) : type_upgrade.adapterToUpgrade() ;
		
		if(upgrade == null) {
			player.sendMessage("§cUne erreur est survenue.");
			return ;
		}
		
		int price_next_upgrade = upgrade.getPriceNextTypeUpgrade() ;
		
		if(price_next_upgrade == -1) {
			player.sendMessage("§cCette amélioration est déjà au maximum.");
			return ;
		}
		
		if(!coeur.containsPoints(price_next_upgrade)) {
			player.sendMessage("§cVous n'avez pas assez de points d'améliorations disponibles.");
			return ;
		}
		
		coeur.takePoints(price_next_upgrade);
		upgrade.add_level_unlock();
		if(!coeur.containsUpgrade(type_upgrade)) coeur.addUpgrade(upgrade);
		player.sendMessage("§aAmélioration ajoutée !");
	}

}
