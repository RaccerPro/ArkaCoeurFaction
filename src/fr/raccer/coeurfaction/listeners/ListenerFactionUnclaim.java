package fr.raccer.coeurfaction.listeners;

import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import cc.javajobs.factionsbridge.bridge.events.FactionUnclaimAllEvent;
import cc.javajobs.factionsbridge.bridge.events.FactionUnclaimEvent;
import fr.raccer.coeurfaction.Main;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;

public class ListenerFactionUnclaim implements Listener {

	@EventHandler
	public void onUnclaim(FactionUnclaimEvent e) {
		
		MFaction mfaction = MUtilsPlayers.getMFaction(e.getFaction()) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		if(!coeur.isSpawned()) return ;
		
		Chunk cc = coeur.getMlocation().getChunk() ;
		Chunk cf = e.getClaim().getChunk() ;
		
		if(cc.getX() == cf.getX() && cc.getZ() == cf.getZ()) {
			e.setCancelled(true);
			e.getFPlayer().getPlayer()
				.sendMessage(Main.PREFIX+"§cImpossible d'unclaim ce chunk car il possède le Coeur de Faction");
			return ;
		}
	}
	
	@EventHandler
	public void onUnclaimAll(FactionUnclaimAllEvent e) {
		
		MFaction mfaction = MUtilsPlayers.getMFaction(e.getFaction()) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		if(!coeur.isSpawned()) return ;
		
		e.setCancelled(true);
		e.getFPlayer().getPlayer()
			.sendMessage(Main.PREFIX+"§cImpossible d'unclaim car vous possèdez un Coeur de Faction. Enlevez le avant d'unclaim all.");
		
	}
	
}
