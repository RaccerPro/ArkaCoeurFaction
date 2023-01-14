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

public class Command_CoeurFaction_RemoveSpeedAttack {
	

	@Command(name = "coeurfaction.removeSpeedAttack", permission = "arka.coeurfaction.removeSpeedAttack", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
		
		Player pl = a.getPlayer() ;
		MPlayer player = MUtilsPlayers.getMPlayer(pl) ;
		FPlayer fplayer = MUtilsFactions.getInstance().getFPlayer(pl) ;
		
		if(fplayer.getFaction().isWilderness()) {
			player.sendMessage(M.NEED_HAVE_A_FACTION);
			return ;
		}
		
		if(!MUtilsFactions.getInstance().isLeader(fplayer)) {
			player.sendMessage(M.NOT_LEADER_FACTION);
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(pl) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		UpgradeLevel current_level = coeur.getUpgradeSpeedAttackLevel() ;
		UpgradeLevel before_level = current_level.beforeLevel();
		
		if(before_level == null) {
			player.sendMessage(M.UPGRADE_MINIMUM_LEVEL);
			return ;
		}
		
		int price_current_upgrade = current_level.getPrice_to_unlock() ; 
		
		coeur.givePoints(price_current_upgrade);
		player.sendMessage(M.RECEIVE_X_UPGRADE_POINTS, "\\{points\\}", ""+price_current_upgrade);
		coeur.setUpgradeSpeedAttackLevel(before_level);
		player.sendMessage(M.SET_LEVEL_SPEED_ATTACK, "\\{level_speed_attack_name\\}", before_level.getName());
	}

}
