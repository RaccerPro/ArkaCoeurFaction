package fr.raccer.coeurfaction.upgrades.levels;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;

public class UpgradeKnockback extends CoeurUpgrades {

	private void knock(CoeurFaction coeur, double ampl) {
		
		for(Player p : coeur.getListPlayerEnnemyNear(5, 3, 5)) {
			
			int x = (int) (Math.random()%10) ;
			int z = 10 - x ;
			
			int neg_x = (int) (Math.random()%2) ;
			int neg_z = (int) (Math.random()%2) ;
			
			double add_X = ampl + x/10 ;
			double add_Z = ampl + z/10 ;
			
			if(neg_x == 0) add_X *= -1 ;
			if(neg_z == 0) add_Z *= -1 ;
			
			p.setVelocity(p.getVelocity().add(new Vector(add_X, 1.03, add_Z)));
			p.damage(0);
		}
		
	}
	
	
	@Override
	public void execute_lvl_1(CoeurFaction coeur) {
		this.knock(coeur, 0.5);
	}

	@Override
	public void execute_lvl_2(CoeurFaction coeur) {
		this.knock(coeur, 1);
	}

	@Override
	public void execute_lvl_3(CoeurFaction coeur) {
		this.knock(coeur, 1.5);
	}

	@Override
	public TypeUpgrades getTypeUpgrade() {
		return TypeUpgrades.KNOCKBACK ;
	}
	

}
