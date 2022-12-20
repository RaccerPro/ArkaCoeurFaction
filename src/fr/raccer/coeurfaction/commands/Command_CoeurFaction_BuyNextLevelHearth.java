package fr.raccer.coeurfaction.commands;

import org.bukkit.entity.Player;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.upgrades.HearthLevel;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;

public class Command_CoeurFaction_BuyNextLevelHearth {


	@Command(name = "coeurfaction.buyNextLevelHearth", permission = "arka.coeurfaction.buyNextLevelHearth", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
		
		Player player = a.getPlayer() ;
		FPlayer fplayer = FPlayers.getInstance().getByPlayer(player) ;
		Faction faction = fplayer.getFaction() ;
		
		if(faction.isWilderness()) {
			player.sendMessage("§cVous devez avoir une faction pour faire cette commande.");
			return ;
		}
		
		if(!faction.getFPlayerAdmin().getAccountId().equalsIgnoreCase(fplayer.getAccountId())) {
			player.sendMessage("§cVous n'êtes pas chef de votre faction.");
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(player) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		HearthLevel nextLevel = coeur.getHearthLevel().nextLevel() ;
		
		if(nextLevel == null) {
			player.sendMessage("§6Vous avez déjà atteins le niveau maximum.");
			return ;
		}
		
		/*
		
		double fac_balance = faction.getFactionBalance() ;
		
		if(fac_balance < nextLevel.getPrice_to_unlock()) {
			player.sendMessage("§cVous n'avez pas les fonds nécessaires.");
			return ;
		}
		
		faction.setFactionBalance(fac_balance - nextLevel.getPrice_to_unlock());
		*/
		coeur.setHearthLevel(nextLevel);
		mfaction.sendMessage("§6Vous venez de débloquer le §e§l"+nextLevel.getName()+" §6!");
	}
	
}
