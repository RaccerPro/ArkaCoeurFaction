package fr.raccer.coeurfaction.upgrades.levels;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.raccer.coeurfaction.Main;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;

public class UpgradePNJHealer extends CoeurUpgrades {

	private void pnj(CoeurFaction coeur, double pourc) {
		
		
		Location loc = coeur.getMlocation().getLocation() ;
		Entity e = loc.getWorld().spawnEntity(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()), EntityType.VILLAGER) ;
		e.setCustomName("§c§lPNJ Healer");
		e.setCustomNameVisible(true);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(e.isDead()) return ;
				coeur.addHealth(coeur.getHearthLevel().getMax_health()*pourc);
				loc.getWorld().playEffect(e.getLocation(), Effect.HEART, 1);
				loc.getWorld().playSound(e.getLocation(), Sound.VILLAGER_YES, 1, 1);
				e.remove();
			}
		}.runTaskLater(Main.instance, 10*20) ;
		
	}
	
	@Override
	public void execute_lvl_1(CoeurFaction coeur) {
		this.pnj(coeur, 0.15);
	}

	@Override
	public void execute_lvl_2(CoeurFaction coeur) {
		this.pnj(coeur, 0.25);
	}

	@Override
	public void execute_lvl_3(CoeurFaction coeur) {
		this.pnj(coeur, 0.35);
	}

	@Override
	public TypeUpgrades getTypeUpgrade() {
		return TypeUpgrades.PNJHEALER ;
	}
	

}
