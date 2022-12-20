package fr.raccer.coeurfaction.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.mutilsplayers.mfactions.events.MFactionDisbandEvent;

public class ListenerMFactionDisband implements Listener {
	
	@EventHandler
	public void onDisband(MFactionDisbandEvent e) {
		DataCoeurFaction data = e.getMfaction().getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = data.getCoeurFaction() ;
		coeur.killCoeurFaction();
	}
	
}
