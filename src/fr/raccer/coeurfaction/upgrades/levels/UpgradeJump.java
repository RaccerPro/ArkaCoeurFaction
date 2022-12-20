package fr.raccer.coeurfaction.upgrades.levels;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;

public class UpgradeJump extends CoeurUpgrades {
	
	private void jump(CoeurFaction coeur, double ampl) {
		
		for(Player p : coeur.getListPlayerEnnemyNear(5, 3, 5)) {
			p.setVelocity(p.getVelocity().add(new Vector(0, ampl, 0)));
			p.damage(0);
		}
		
	}

	@Override
	public void execute_lvl_1(CoeurFaction coeur) {
		this.jump(coeur, 1.1);
	}

	@Override
	public void execute_lvl_2(CoeurFaction coeur) {
		this.jump(coeur, 1.2);
	}

	@Override
	public void execute_lvl_3(CoeurFaction coeur) {
		this.jump(coeur, 1.3);
	}

	@Override
	public TypeUpgrades getTypeUpgrade() {
		return TypeUpgrades.JUMP ;
	}
	

}
