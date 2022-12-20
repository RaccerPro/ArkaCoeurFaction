package fr.raccer.coeurfaction.upgrades.levels;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.raccer.coeurfaction.Main;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;

public class UpgradeEggs extends CoeurUpgrades {
	
	
	private void eggs(CoeurFaction coeur, int time_secs) {
		
		Location loc = coeur.getMlocation().getLocation() ;
		
		new BukkitRunnable() {
			
			private int i = 0 ; 
			
			@Override
			public void run() {
				
				Player p = coeur.getLast_damager_player().getPlayer() ; 
				if(p == null) return ;
				
				Entity egg = loc.getWorld().spawnEntity(p.getLocation().add(0, 5, 0), EntityType.EGG) ;
				egg.setVelocity(new Vector(0, -1, 0));
				//egg.setMetadata("egg_not_spawn_chicken", new FixedMetadataValue(Main.instance, true));
				
				i++ ;
				
				if(i == time_secs) this.cancel();
				
			}
		}.runTaskTimer(Main.instance, 0, 5) ;
		
	}
	
	
	

	@Override
	public void execute_lvl_1(CoeurFaction coeur) {
		this.eggs(coeur, 16);
	}

	@Override
	public void execute_lvl_2(CoeurFaction coeur) {
		this.eggs(coeur, 32);
	}

	@Override
	public void execute_lvl_3(CoeurFaction coeur) {
		this.eggs(coeur, 48);
	}

	@Override
	public TypeUpgrades getTypeUpgrade() {
		return TypeUpgrades.EGGS ;
	}
	

}
