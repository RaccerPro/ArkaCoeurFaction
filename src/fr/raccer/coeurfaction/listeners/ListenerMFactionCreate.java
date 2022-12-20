package fr.raccer.coeurfaction.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import fr.raccer.mutilsplayers.mfactions.events.MFactionCreateEvent;

public class ListenerMFactionCreate implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onCreateMFaction(MFactionCreateEvent e) {
		MFaction mfac = e.getMfaction() ;
		DataCoeurFaction data = new DataCoeurFaction() ;
		data.getCoeurFaction().set_faction(mfac.getId());
		data.getCoeurFaction().setMfaction(mfac);
		mfac.addData(data);
	}

}
