package fr.raccer.coeurfaction.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import cc.javajobs.factionsbridge.bridge.infrastructure.struct.Faction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import fr.raccer.mutilsplayers.mfactions.events.MFactionCreateEvent;

public class ListenerMFactionCreate implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onCreateMFaction(MFactionCreateEvent e) {
		MFaction mfac = e.getMfaction() ;
		if(mfac.containsData(DataCoeurFaction.ID)) return ;
		DataCoeurFaction data = new DataCoeurFaction() ;
		data.getCoeurFaction().set_faction(mfac.getId());
		data.getCoeurFaction().setMfaction(mfac);
		mfac.addData(data);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		MFaction mfac = MUtilsPlayers.getMFaction(e.getPlayer()) ;
		if(mfac == null) return ;
		if(mfac.containsData(DataCoeurFaction.ID)) return ;
		
		Faction fac = mfac.getFaction() ;
		if(fac.isWilderness() || fac.isSafeZone() || fac.isWarZone()) return ;
		
		DataCoeurFaction data = new DataCoeurFaction() ;
		data.getCoeurFaction().set_faction(mfac.getId());
		data.getCoeurFaction().setMfaction(mfac);
		mfac.addData(data);
	}

}
