package fr.raccer.coeurfaction.upgrades.levels;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;

public class UpgradePoison extends CoeurUpgrades {
	
	private void apply_effect(CoeurFaction coeur, int seconds, int amplifier) {
	
		List<Player> list = coeur.getListPlayerEnnemyNear(5, 5, 5) ;
		
		for(Player p : list) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, seconds*20, amplifier)) ;
		}
	}

	@Override
	public void execute_lvl_1(CoeurFaction coeur) {
		this.apply_effect(coeur, 5, 0);
	}

	@Override
	public void execute_lvl_2(CoeurFaction coeur) {
		this.apply_effect(coeur, 10, 0);	
	}

	@Override
	public void execute_lvl_3(CoeurFaction coeur) {
		this.apply_effect(coeur, 10, 1);
	}
	
	
	@Override
	public TypeUpgrades getTypeUpgrade() {
		return TypeUpgrades.POISON ;
	}
	
}
