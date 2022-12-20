package fr.raccer.coeurfaction.upgrades.levels;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;

public class UpgradeNausea extends CoeurUpgrades {
	
	private void nausea(CoeurFaction coeur, int amplifier, int seconds) {
		
		List<Player> list = coeur.getListPlayerEnnemyNear(5, 5, 5) ;
		
		for(Player p : list) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, seconds*20, amplifier)) ;
		}
		
	}

	@Override
	public void execute_lvl_1(CoeurFaction coeur) {
		this.nausea(coeur, 1, 10);
	}

	@Override
	public void execute_lvl_2(CoeurFaction coeur) {
		this.nausea(coeur, 1, 15);
	}

	@Override
	public void execute_lvl_3(CoeurFaction coeur) {
		this.nausea(coeur, 1, 20);
	}

	@Override
	public TypeUpgrades getTypeUpgrade() {
		return TypeUpgrades.NAUSEA ;
	}
	

}
