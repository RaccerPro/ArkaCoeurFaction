package fr.raccer.coeurfaction.commands;

import org.bukkit.entity.Player;

import cc.javajobs.factionsbridge.bridge.infrastructure.struct.FPlayer;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.messages.MessagesManager.M;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import fr.raccer.mutilsplayers.mplayers.MPlayer;
import fr.raccer.mutilsplayers.utils.methods.MUtilsFactions;

public class Command_CoeurFaction_Spawn {
	

	@Command(name = "coeurfaction.spawn", permission = "arka.coeurfaction.spawn", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
		
		Player pl = a.getPlayer() ;
		MPlayer player = MUtilsPlayers.getMPlayer(pl) ;
		FPlayer fplayer = MUtilsFactions.getInstance().getFPlayer(pl) ;
		
		if(fplayer.getFaction().isWilderness()) {
			player.sendMessage(M.NEED_HAVE_A_FACTION.get());
			return ;
		}
		
		if(!MUtilsFactions.getInstance().isLeader(fplayer)) {
			player.sendMessage(M.NOT_LEADER_FACTION);
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(pl) ;
		if(!mfaction.containsData(DataCoeurFaction.ID)) {
			DataCoeurFaction data = new DataCoeurFaction() ;
			data.getCoeurFaction().set_faction(mfaction.getId());
			mfaction.addData(data);
		}
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		if(coeur.isSpawned()) {
			coeur.killCoeurFaction();
			player.sendMessage(M.HEART_FACTION_REMOVE);
			return ;
		}
		
		if(!coeur.canSpawn()) {
			player.sendMessage(M.COOLDOWN_BEFORE_SPAWN, "\\{time_spawn\\}", coeur.getTimeStringBeforeCanSpawn());
			return ;
		}
		
		if(!MUtilsFactions.getInstance().isInOwnTerritory(fplayer)) {
			player.sendMessage(M.CANT_SPAWN_OUTSIDE_CLAIMS);
			return ;
		}
		
		if(player.getLocation().getBlockY() < 6) {
			player.sendMessage(M.NEED_TO_LOCATE_ABOVE_Y);
			return ;
		}
		
		boolean b = coeur.spawnWithLocation(player.getLocation());
		
		if(!b) {
			player.sendMessage(M.NOT_UNLOCKED_FIRST_LEVEL);
			return ;
		}
		
		player.sendMessage(M.HEART_PLACED);
		
	}

}
