package fr.raccer.coeurfaction.upgrades.levels;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;

public class UpgradeBouclier extends CoeurUpgrades {

	@Override
	public void execute_lvl_1(CoeurFaction coeur) {
		coeur.addBouclier(10);
		coeur.updateDisplayHealth();
	}

	@Override
	public void execute_lvl_2(CoeurFaction coeur) {
		coeur.addBouclier(15);
		coeur.updateDisplayHealth();
	}

	@Override
	public void execute_lvl_3(CoeurFaction coeur) {
		coeur.addBouclier(20);
		coeur.updateDisplayHealth();
	}

	@Override
	public TypeUpgrades getTypeUpgrade() {
		return TypeUpgrades.Bouclier ;
	}
	

}
