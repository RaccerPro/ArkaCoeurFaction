package fr.raccer.coeurfaction.commands;

import org.bukkit.entity.Player;

import cc.javajobs.factionsbridge.bridge.infrastructure.struct.FPlayer;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.messages.MessagesManager.M;
import fr.raccer.coeurfaction.upgrades.UpgradeLevel;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import fr.raccer.mutilsplayers.mplayers.MPlayer;
import fr.raccer.mutilsplayers.utils.methods.MUtilsFactions;

public class Command_CoeurFaction_BuySpeedAttack {
	

	@Command(name = "coeurfaction.buySpeedAttack", permission = "arka.coeurfaction.buySpeedAttack", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
		
		Player pl = a.getPlayer() ;
		MPlayer player = MUtilsPlayers.getMPlayer(pl) ;
		FPlayer fplayer = MUtilsFactions.getInstance().getFPlayer(pl) ;
		
		if(fplayer.getFaction().isWilderness()) {
			player.sendMessage(M.NEED_HAVE_A_FACTION.get());
			return ;
		}
		
		if(!MUtilsFactions.getInstance().isLeader(fplayer)) {
			player.sendMessage(M.NOT_LEADER_FACTION.get());
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(pl) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		UpgradeLevel current_level = coeur.getUpgradeSpeedAttackLevel() ;
		UpgradeLevel next_level = current_level.nextLevel() ;
		
		if(next_level == null) {
			player.sendMessage(M.UPGRADE_MAXIMUM_LEVEL.get());
			return ;
		}
		
		int price_next_upgrade = next_level.getPrice_to_unlock() ; 
		
		if(!coeur.containsPoints(price_next_upgrade)) {
			player.sendMessage(M.DONT_HAVE_POINTS_AVAILABLE.get());
			return ;
		}
		
		coeur.takePoints(price_next_upgrade);
		coeur.setUpgradeSpeedAttackLevel(next_level);
		player.sendMessage(M.SET_LEVEL_SPEED_ATTACK.get(), "\\{level_speed_attack_name\\}", next_level.getName());
	}

}
