package fr.raccer.coeurfaction.upgrades;

import org.bukkit.entity.Player;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import lombok.Getter;
import lombok.Setter;

public abstract class CoeurUpgrades {
	
	@Getter @Setter private int level_unlock_upgrade ;
	
	public CoeurUpgrades() {
		level_unlock_upgrade = 0 ;
	}
	
	public void execute(CoeurFaction coeur) {
		
		for(Player p : coeur.getListPlayerFactionNear(7, 5, 7))
			p.sendMessage("§6L'amélioration "+getTypeUpgrade()+" vient d'être activée !");
		
		for(Player p : coeur.getListPlayerEnnemyNear(7, 5, 7))
			p.sendMessage("§6L'amélioration "+getTypeUpgrade()+" vient d'être activée !");
		
		if(level_unlock_upgrade == 1) this.execute_lvl_1(coeur);
		else if(level_unlock_upgrade == 2) this.execute_lvl_2(coeur);
		else if(level_unlock_upgrade == 3) this.execute_lvl_3(coeur);
	}
	
	public void add_level_unlock() {
		level_unlock_upgrade++; 
	}
	public void remove_level_unlock() {
		level_unlock_upgrade--;
	}
	
	public String getStringSmallDescription() {
		return getTypeUpgrade().getSmallDescription(level_unlock_upgrade) ;
	}
	
	public abstract void execute_lvl_1(CoeurFaction coeur) ;
	public abstract void execute_lvl_2(CoeurFaction coeur) ;
	public abstract void execute_lvl_3(CoeurFaction coeur) ;
	
	public abstract TypeUpgrades getTypeUpgrade() ;
	
	public int getPriceNextTypeUpgrade() {
		if(level_unlock_upgrade == 0) return getTypeUpgrade().getPrice_lvl_1() ;
		if(level_unlock_upgrade == 1) return getTypeUpgrade().getPrice_lvl_2() ;
		if(level_unlock_upgrade == 2) return getTypeUpgrade().getPrice_lvl_3() ;
		return -1 ;
	}
	
	public int getPriceBeforeTypeUpgrade() {
		if(level_unlock_upgrade == 1) return getTypeUpgrade().getPrice_lvl_1() ;
		if(level_unlock_upgrade == 2) return getTypeUpgrade().getPrice_lvl_2() ;
		if(level_unlock_upgrade == 3) return getTypeUpgrade().getPrice_lvl_3() ;
		return -1 ;
	}
	
	/*
	public abstract int cooldown_before_exec_seconds_lvl_0() ;
	public abstract int cooldown_before_exec_seconds_lvl_1() ;
	public abstract int cooldown_before_exec_seconds_lvl_2() ;
	public abstract int cooldown_before_exec_seconds_lvl_3() ;
	public abstract int cooldown_before_exec_seconds_lvl_4() ;
	*/
}
