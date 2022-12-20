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

public class Command_CoeurFaction_Remove {
	

	@Command(name = "coeurfaction.remove", permission = "arka.coeurfaction.remove", inGameOnly = true)
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
			player.sendMessage("§6/coeurfaction remove [ID]");
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
		
		if(!coeur.containsUpgrade(type_upgrade)) {
			player.sendMessage("§cVous n'avez pas débloquer cette amélioration.");
			return ;
		}
		
		CoeurUpgrades upgrade = coeur.getUpgrade(type_upgrade) ;
		if(upgrade == null) {
			player.sendMessage("§cUne erreur est survenue.");
			return ;
		}
		
		int price_upgrade = upgrade.getPriceBeforeTypeUpgrade() ;
		
		if(price_upgrade == -1) {
			player.sendMessage("§cCette amélioration est au minimum.");
			return ;
		}
		
		coeur.givePoints(price_upgrade);
		player.sendMessage("§cVous avez reçu "+price_upgrade+" points d'améliorations.");
		
		upgrade.remove_level_unlock();
		if(upgrade.getLevel_unlock_upgrade() == 0) {
			coeur.removeUpgrade(type_upgrade);
			player.sendMessage("§cL'amélioration "+type_upgrade.getName()+" a été supprimé.");
			return ;
		}
		player.sendMessage("§cL'amélioration "+type_upgrade.getName()+" a été mis au niveau "+upgrade.getLevel_unlock_upgrade()+".");
	}

}
