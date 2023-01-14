package fr.raccer.coeurfaction.commands;

import org.bukkit.entity.Player;

import cc.javajobs.factionsbridge.bridge.infrastructure.struct.FPlayer;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.messages.MessagesManager.M;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import fr.raccer.mutilsplayers.mplayers.MPlayer;
import fr.raccer.mutilsplayers.utils.methods.MUtilsFactions;

public class Command_CoeurFaction_Remove {
	

	@Command(name = "coeurfaction.remove", permission = "arka.coeurfaction.remove", inGameOnly = true)
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
		
		if(a.length() != 1) {
			player.sendMessage(M.USAGE_CMD_REMOVE);
			return ;
		}
		
		TypeUpgrades type_upgrade = TypeUpgrades.convert(a.getArgs(0)) ;
		
		if(type_upgrade == null ) {
			player.sendMessage(M.ID_NOT_FIND);
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(pl) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		if(!coeur.containsUpgrade(type_upgrade)) {
			player.sendMessage(M.UPGRADE_NOT_UNLOCKED);
			return ;
		}
		
		CoeurUpgrades upgrade = coeur.getUpgrade(type_upgrade) ;
		if(upgrade == null) {
			player.sendMessage(M.ERROR);
			return ;
		}
		
		int price_upgrade = upgrade.getPriceBeforeTypeUpgrade() ;
		
		if(price_upgrade == -1) {
			player.sendMessage(M.UPGRADE_MINIMUM_LEVEL);
			return ;
		}
		
		coeur.givePoints(price_upgrade);
		player.sendMessage(M.RECEIVE_X_UPGRADE_POINTS, "\\{points\\}", ""+price_upgrade);
		
		upgrade.remove_level_unlock();
		if(upgrade.getLevel_unlock_upgrade() == 0) {
			coeur.removeUpgrade(type_upgrade);
			player.sendMessage(M.UPGRADE_REMOVE, "\\{upgrade_name\\}", type_upgrade.getName());
			return ;
		}
		player.sendMessage(M.UPGRADE_LEVEL_CHANGE, "\\{upgrade_name\\}", type_upgrade.getName(), "\\{upgrade_level\\}", ""+upgrade.getLevel_unlock_upgrade());
	}

}
