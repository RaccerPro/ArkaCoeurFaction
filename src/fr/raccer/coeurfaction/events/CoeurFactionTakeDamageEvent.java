package fr.raccer.coeurfaction.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.raccer.mutilsplayers.mfactions.MFaction;
import fr.raccer.mutilsplayers.mplayers.MPlayer;
import lombok.Getter;
import lombok.Setter;

public class CoeurFactionTakeDamageEvent extends Event implements Cancellable {
	
    private static final HandlerList handlers = new HandlerList();
    @Getter @Setter private boolean cancelled = false;
    @Getter @Setter private MPlayer damager ;
    @Getter @Setter private MFaction mfaction ;
    @Getter @Setter private double damage;
    
    public CoeurFactionTakeDamageEvent(MFaction mfaction, MPlayer damager, double damage) {
		setMfaction(mfaction);
		setDamager(damager);
		setDamage(damage);
	}
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }

}
