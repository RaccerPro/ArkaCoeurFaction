package fr.raccer.coeurfaction.commands;

import org.bukkit.entity.Player;

import cc.javajobs.factionsbridge.bridge.infrastructure.struct.FPlayer;
import cc.javajobs.factionsbridge.bridge.infrastructure.struct.Faction;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.messages.MessagesManager.M;
import fr.raccer.coeurfaction.upgrades.HearthLevel;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import fr.raccer.mutilsplayers.mplayers.MPlayer;
import fr.raccer.mutilsplayers.utils.methods.MUtilsFactions;

public class Command_CoeurFaction_BuyNextLevelHearth {


	@Command(name = "coeurfaction.buyNextLevelHearth", permission = "arka.coeurfaction.buyNextLevelHearth", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
		
		Player player = a.getPlayer() ;
		MPlayer mplayer = MUtilsPlayers.getMPlayer(player);
		FPlayer fplayer = MUtilsFactions.getInstance().getFPlayer(player) ;
		Faction faction = fplayer.getFaction() ;
		
		if(faction.isWilderness()) {
			mplayer.sendMessage(M.NEED_HAVE_A_FACTION.get());
			return ;
		}
		
		
		if(!MUtilsFactions.getInstance().isLeader(fplayer)) {
			mplayer.sendMessage(M.NOT_LEADER_FACTION.get());
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(player) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		HearthLevel nextLevel = coeur.getHearthLevel().nextLevel() ;
		
		if(nextLevel == null) {
			mplayer.sendMessage(M.HEARTH_MAXIMUM_LEVEL.get());
			return ;
		}
		/*
		double fac_balance = faction.getBank() ;
		
		if(fac_balance < nextLevel.getPrice_to_unlock()) {
			mplayer.sendMessage(M.DONT_HAVE_MONEY_AVAILABLE.get());
			return ;
		}
		faction.setBank(fac_balance - nextLevel.getPrice_to_unlock());
		*/
		coeur.setHearthLevel(nextLevel);
		
		mfaction.sendMessage(M.UNLOCK_HEARTH_LEVEL.get());
	}
	
}
