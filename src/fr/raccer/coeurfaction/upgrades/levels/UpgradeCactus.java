package fr.raccer.coeurfaction.upgrades.levels;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.raccer.coeurfaction.Main;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;

public class UpgradeCactus extends CoeurUpgrades {
	
	private void cactus(CoeurFaction coeur, int time_secs) {
		
		Location loc = coeur.getMlocation().getLocation() ;
		
		new BukkitRunnable() {
			
			private int i = 0 ; 
			
			@Override
			public void run() {
				
				Collection<Entity> coll = loc.getWorld().getNearbyEntities(loc, 5, 5, 5) ;
				
				for(Entity e : coll) {
					if(!(e instanceof Player)) continue ;
					Player p = (Player) e; 
					if(coeur.isSameFaction(p)) continue ;
					p.damage(2);
				}
				i++ ;
				
				if(i == time_secs) this.cancel();
				
			}
		}.runTaskTimer(Main.instance, 0, 20) ;
		
	}

	@Override
	public void execute_lvl_1(CoeurFaction coeur) {
		this.cactus(coeur, 5);
	}

	@Override
	public void execute_lvl_2(CoeurFaction coeur) {
		this.cactus(coeur, 10);
	}

	@Override
	public void execute_lvl_3(CoeurFaction coeur) {
		this.cactus(coeur, 20);
	}

	@Override
	public TypeUpgrades getTypeUpgrade() {
		return TypeUpgrades.CACTUS ;
	}
	

}
