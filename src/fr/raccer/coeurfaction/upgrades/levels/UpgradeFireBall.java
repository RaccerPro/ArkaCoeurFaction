package fr.raccer.coeurfaction.upgrades.levels;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.raccer.coeurfaction.Main;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;

public class UpgradeFireBall extends CoeurUpgrades {
	
	public static final String meta_data_fireball = "fireball-not-damage" ;

	private void fireball(CoeurFaction coeur, int nb) {
		
		new BukkitRunnable() {
			
			private int i = 0 ;
			
			@Override
			public void run() {
				Player p = coeur.getLast_damager_player().getPlayer() ; 
				if(p == null) return ;
				
				Location l = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY()+2, p.getLocation().getZ()) ;
				Entity e = p.getWorld().spawnEntity(l, EntityType.FIREBALL) ;
				e.setMetadata(meta_data_fireball, new FixedMetadataValue(Main.instance, true));
				e.setVelocity(new Vector(0, -0.2, 0));
				
				i++ ;
				if(i == nb) this.cancel();
			}
		}.runTaskTimer(Main.instance, 0, 20) ;
		
		
	}
	
	@Override
	public void execute_lvl_1(CoeurFaction coeur) {
		this.fireball(coeur, 3);
	}

	@Override
	public void execute_lvl_2(CoeurFaction coeur) {
		this.fireball(coeur, 6);
	}

	@Override
	public void execute_lvl_3(CoeurFaction coeur) {
		this.fireball(coeur, 9);
	}

	@Override
	public TypeUpgrades getTypeUpgrade() {
		return TypeUpgrades.FIREBALL ;
	}

}
