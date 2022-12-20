package fr.raccer.coeurfaction.commands;

import java.util.List;

import org.bukkit.entity.Player;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;

public class Command_CoeurFaction_SeeUpgrades {
	

	@Command(name = "coeurfaction.seeUpgrades", permission = "arka.coeurfaction.seeUpgrades", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
				
		Player player = a.getPlayer() ;
		FPlayer fplayer = FPlayers.getInstance().getByPlayer(player) ;
		
		if(fplayer.getFaction().isWilderness()) {
			player.sendMessage("§cVous devez avoir une faction pour faire cette commande.");
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(player) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		List<CoeurUpgrades> list = coeur.getList_attacks_enabled() ;
		
		String str = "" ;
		
		for(CoeurUpgrades u : list)
			str += "§b» "+u.getStringSmallDescription()+"\n" ;
		
		str = "§6Liste des attaques activées : \n" + str ;
		
		if(str.equalsIgnoreCase("")) str = "§cAucune amélioration activée." ;
		
		player.sendMessage(str);
		
	}

}
