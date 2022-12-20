package fr.raccer.coeurfaction.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.events.CoeurFactionTakeDamageEvent;
import fr.raccer.mutilsplayers.mfactions.MFaction;

public class ListenerCoeurTakeDamage implements Listener {
	
	@EventHandler
	public void onDamage(CoeurFactionTakeDamageEvent e) {
		MFaction mfac = e.getDamager().getMFaction() ;
		if(mfac == null) return ;
		
		DataCoeurFaction dataCoeur = mfac.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		dataCoeur.addDamageTotal(e.getDamage()) ;
	}

}
