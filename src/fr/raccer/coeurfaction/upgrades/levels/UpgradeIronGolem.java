package fr.raccer.coeurfaction.upgrades.levels;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;

public class UpgradeIronGolem extends CoeurUpgrades {
	
	private void golem(CoeurFaction coeur, int nombre) {
		
		Player p = coeur.getLast_damager_player().getPlayer() ;
		if(p == null) return ;
		
		for(int i=0 ; i<nombre ; i++) {
			Entity e = p.getWorld().spawnEntity(coeur.getMlocation().getLocation(), EntityType.IRON_GOLEM) ;
			IronGolem golem = (IronGolem) e; 
			golem.setTarget(p);
		}
		
	}

	@Override
	public void execute_lvl_1(CoeurFaction coeur) {
		this.golem(coeur, 1);
	}

	@Override
	public void execute_lvl_2(CoeurFaction coeur) {
		this.golem(coeur, 2);
	}

	@Override
	public void execute_lvl_3(CoeurFaction coeur) {
		this.golem(coeur, 3);
	}

	@Override
	public TypeUpgrades getTypeUpgrade() {
		return TypeUpgrades.IRONGOLEM;
	}
	

}
