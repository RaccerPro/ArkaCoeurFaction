package fr.raccer.coeurfaction.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.raccer.coeurfaction.events.CoeurFactionExecuteUpgradeEvent;

public class ListenerCoeurExecuteUpgrade implements Listener {
	
	@EventHandler
	public void onDamage(CoeurFactionExecuteUpgradeEvent e) {
		//System.out.println(e.getEventName());
	}

}
