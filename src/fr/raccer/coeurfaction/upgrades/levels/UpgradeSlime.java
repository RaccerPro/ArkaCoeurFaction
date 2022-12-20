package fr.raccer.coeurfaction.upgrades.levels;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Slime;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;

public class UpgradeSlime extends CoeurUpgrades {

	@Override
	public void execute_lvl_1(CoeurFaction coeur) {
		
		Location loc = coeur.getMlocation().getLocation() ;
		
		for(int i = 0 ; i < 4 ; i++) {
			Entity e = loc.getWorld().spawnEntity(loc, EntityType.SLIME) ;
			Slime slime = (Slime) e ; 
			slime.setSize(2);
		}
		
	}

	@Override
	public void execute_lvl_2(CoeurFaction coeur) {
		Location loc = coeur.getMlocation().getLocation() ;
		
		for(int i = 0 ; i < 2 ; i++) {
			Entity e = loc.getWorld().spawnEntity(loc, EntityType.SLIME) ;
			Slime slime = (Slime) e ; 
			slime.setSize(5);
		}
		
	}

	@Override
	public void execute_lvl_3(CoeurFaction coeur) {
		Location loc = coeur.getMlocation().getLocation() ;
		
		for(int i = 0 ; i < 1 ; i++) {
			Entity e = loc.getWorld().spawnEntity(loc, EntityType.SLIME) ;
			Slime slime = (Slime) e ; 
			slime.setSize(8);
		}
		
	}

	@Override
	public TypeUpgrades getTypeUpgrade() {
		return TypeUpgrades.SLIME ;
	}
	

}
