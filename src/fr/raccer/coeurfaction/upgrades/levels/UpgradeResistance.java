package fr.raccer.coeurfaction.upgrades.levels;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;

public class UpgradeResistance extends CoeurUpgrades {
	
	private void resistance(CoeurFaction coeur, int amplifier, int seconds) {
		
		List<Player> list = coeur.getListPlayerFactionNear(10, 10, 10) ;
		
		for(Player p : list) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, seconds*20, amplifier)) ;
		}
		
	}

	@Override
	public void execute_lvl_1(CoeurFaction coeur) {
		this.resistance(coeur, 0, 5);
	}

	@Override
	public void execute_lvl_2(CoeurFaction coeur) {
		this.resistance(coeur, 0, 10);
	}

	@Override
	public void execute_lvl_3(CoeurFaction coeur) {
		this.resistance(coeur, 1, 10);
	}

	@Override
	public TypeUpgrades getTypeUpgrade() {
		return TypeUpgrades.RESISTANCE ;
	}
	

}
