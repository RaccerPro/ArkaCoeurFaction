package fr.raccer.coeurfaction.datafaction;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import fr.raccer.coeurfaction.Main;
import lombok.Getter;
import lombok.Setter;

public class ScheduleAutoRegeneration extends BukkitRunnable {
	
	@Getter @Setter private CoeurFaction coeurFaction ;

	public ScheduleAutoRegeneration(CoeurFaction coeurFaction) {
		setCoeurFaction(coeurFaction);
	}

	@Override
	public void run() {
		if(coeurFaction.isFullLife()) return ;
		int add = Main.instance.getMconfig().getAsInt("health_added_auto_regeneration") ;
		coeurFaction.addHealth(add);
		Location l = coeurFaction.getMlocation().getLocation().clone() ;
		l.getWorld().playEffect(l.add(0, 1.2, 0), Effect.HEART, 2);
	}

}
