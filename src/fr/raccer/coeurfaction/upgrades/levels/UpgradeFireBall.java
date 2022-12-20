package fr.raccer.coeurfaction.upgrades.levels;

import org.bukkit.entity.Player;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;

public class UpgradeFireBall extends CoeurUpgrades {

	private void fireball(CoeurFaction coeur, int nb_fireball) {
		
		Player p = coeur.getLast_damager_player().getPlayer() ; 
		if(p == null) return ;
		
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
