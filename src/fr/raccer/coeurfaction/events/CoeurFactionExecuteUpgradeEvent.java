package fr.raccer.coeurfaction.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import lombok.Getter;
import lombok.Setter;

public class CoeurFactionExecuteUpgradeEvent extends Event implements Cancellable {
	
    private static final HandlerList handlers = new HandlerList();
    @Getter @Setter private boolean cancelled = false;
    @Getter @Setter private MFaction mfaction ;
    @Getter @Setter private CoeurUpgrades upgrade ;
    
    public CoeurFactionExecuteUpgradeEvent(MFaction mfaction, CoeurUpgrades upgrade) {
		setMfaction(mfaction);
		setUpgrade(upgrade);
	}
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }

}
