package fr.raccer.coeurfaction.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.events.CoeurFactionKilledEvent;
import fr.raccer.mutilsplayers.mfactions.MFaction;

public class ListenerCoeurKilled implements Listener {
	
	@EventHandler
	public void onDamage(CoeurFactionKilledEvent e) {
		MFaction mfac = e.getDamager().getMFaction() ;
		if(mfac == null) return ;
		
		DataCoeurFaction dataCoeur = mfac.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		dataCoeur.addCoeurTues(1);
	}

}
