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

public class Command_CoeurFaction_Buy {
	

	@Command(name = "coeurfaction.buy", permission = "arka.coeurfaction.buy", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
		
		Player player = a.getPlayer() ;
		MPlayer mplayer = MUtilsPlayers.getMPlayer(player) ;
		FPlayer fplayer = MUtilsFactions.getInstance().getFPlayer(player) ;
		
		if(fplayer.getFaction().isWilderness()) {
			mplayer.sendMessage(M.NEED_HAVE_A_FACTION.get());
			return ;
		}
		
		if(!MUtilsFactions.getInstance().isLeader(fplayer)) {
			mplayer.sendMessage(M.NOT_LEADER_FACTION.get());
			return ;
		}
		
		if(a.length() != 1) {
			mplayer.sendMessage(M.USAGE_CMD_BUY.get());
			return ;
		}
		
		TypeUpgrades type_upgrade = TypeUpgrades.convert(a.getArgs(0)) ;
		
		if(type_upgrade == null ) {
			mplayer.sendMessage(M.ID_NOT_FIND.get());
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(player) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		CoeurUpgrades upgrade = coeur.containsUpgrade(type_upgrade) ? 
				coeur.getUpgrade(type_upgrade) : type_upgrade.adapterToUpgrade() ;
		
		if(upgrade == null) {
			mplayer.sendMessage(M.ERROR.get());
			return ;
		}
		
		int price_next_upgrade = upgrade.getPriceNextTypeUpgrade() ;
		
		if(price_next_upgrade == -1) {
			mplayer.sendMessage(M.UPGRADE_MAXIMUM_LEVEL.get());
			return ;
		}
		
		if(!coeur.containsPoints(price_next_upgrade)) {
			mplayer.sendMessage(M.DONT_HAVE_POINTS_AVAILABLE.get());
			return ;
		}
		
		coeur.takePoints(price_next_upgrade);
		upgrade.add_level_unlock();
		if(!coeur.containsUpgrade(type_upgrade)) coeur.addUpgrade(upgrade);
		mplayer.sendMessage(M.UPGRADE_LEVEL_CHANGE, "\\{upgrade_name\\}", type_upgrade.getName(), "\\{upgrade_level\\}", ""+upgrade.getLevel_unlock_upgrade());
	}

}
