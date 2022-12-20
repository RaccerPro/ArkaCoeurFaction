package fr.raccer.coeurfaction.placeholdersapi.expansions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import fr.raccer.coeurfaction.Main;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class Placeholder_CoeurFaction extends PlaceholderExpansion {
	
    @Override
    public String getAuthor() {
        return "Raccer";
    }
    
    @Override
    public String getIdentifier() {
        return "arka-coeurfaction";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }
    
    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }
    
    @Override
    public String onRequest(OfflinePlayer player, String params) {
    	
    	params = params.toLowerCase() ;
    	MFaction mfaction = MUtilsPlayers.getMFaction(player.getPlayer()) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;

		if(params.equalsIgnoreCase("coeur_faction_location")) return coeur.getCoordsFormatted() ;
		if(params.equalsIgnoreCase("coeur_faction_last_player_damager")) return coeur.getLast_damager_player() == null ? "" : coeur.getLast_damager_player().getName() ;
		if(params.equalsIgnoreCase("hearth_level_name")) return coeur.getHearthLevel().getName() ;
		if(params.equalsIgnoreCase("hearth_level_max_health")) return coeur.getHearthLevel().getMax_health()+"" ;
		if(params.equalsIgnoreCase("hearth_level_max_points")) return coeur.getHearthLevel().getMax_points()+"" ;
		if(params.equalsIgnoreCase("hearth_level_price_next_level")) return coeur.getHearthLevel().getPrice_to_unlock()+"" ;
		if(params.equalsIgnoreCase("coeur_faction_current_health")) return coeur.getCurrent_health()+"" ;
		if(params.equalsIgnoreCase("coeur_faction_current_shield")) return coeur.getNb_bouclier()+"" ;
		if(params.equalsIgnoreCase("coeur_faction_current_points_upgrades")) return coeur.getCurrent_points_upgrades()+"" ;
		if(params.equalsIgnoreCase("coeur_faction_level_time_between_2_attacks")) return coeur.getUpgradeSpeedAttackLevel().getLevelSpeedAttack()+"" ;
		if(params.equalsIgnoreCase("coeur_faction_seconds_between_2_attacks")) return coeur.getUpgradeSpeedAttackLevel().getCooldown_between_2_attacks_seconds()+"" ;
		if(params.equalsIgnoreCase("coeur_faction_nb_upgrades_actived")) return coeur.getList_attacks_enabled().size()+"" ;
		if(params.equalsIgnoreCase("coeur_faction_is_spawned")) return coeur.isSpawned()+"" ;
		if(params.equalsIgnoreCase("coeur_faction_formatted_time_before_spawn")) return coeur.getTimeStringBeforeCanSpawn() ;
		
		if(params.equalsIgnoreCase("damage_total_do_others_faction")) return dataCoeur.getDamageTotal()+"" ;
		if(params.equalsIgnoreCase("coeur_tues_others_faction")) return dataCoeur.getCoeurTues()+"" ;
		
		if(params.equalsIgnoreCase("classements_get_rank_damager")) return dataCoeur.getRank_damageTotal()+"" ;
		if(params.equalsIgnoreCase("classements_get_rank_killer")) return dataCoeur.getRank_coeurTues()+"" ;
		
		String tab[] = params.split("_") ;
		
		// Renvoie le nom de faction
		if(params.startsWith("classements_damager_faction_name_")) {
			
			try {
				int place = Integer.parseInt(tab[tab.length-1]) ;
				MFaction fac = Main.instance.getClassementManager().getMFactionDamagerByPlace(place) ;
				if(fac == null) return "Aucune" ;
				return fac.getFaction().getTag()+"" ;
				
			} catch(NumberFormatException e) { return null ; } 
			
		}
		
		// Renvoie les damages du #1
		if(params.startsWith("classements_damager_faction_damages_")) {
			
			try {
				int place = Integer.parseInt(tab[tab.length-1]) ;
				MFaction fac = Main.instance.getClassementManager().getMFactionDamagerByPlace(place) ;
				if(fac == null) return "Aucune" ;
				return fac.getData(DataCoeurFaction.ID, DataCoeurFaction.class).getDamageTotal()+"" ;
				
			} catch(NumberFormatException e) { return null ; } 
			
		}

		// Renvoie le nom de faction
		if(params.startsWith("classements_killer_faction_name_")) {
			
			try {
				int place = Integer.parseInt(tab[tab.length-1]) ;
				MFaction fac = Main.instance.getClassementManager().getMFactionKillerByPlace(place) ;
				if(fac == null) return "Aucune" ;
				return fac.getFaction().getTag() ;
				
			} catch(NumberFormatException e) { return null ; } 
			
		}
		
		// Renvoie les damages du #1
		if(params.startsWith("classements_killer_faction_kills_")) {
			
			try {
				int place = Integer.parseInt(tab[tab.length-1]) ;
				MFaction fac = Main.instance.getClassementManager().getMFactionKillerByPlace(place) ;
				if(fac == null) return "Aucune" ;
				return fac.getData(DataCoeurFaction.ID, DataCoeurFaction.class).getCoeurTues()+"" ;
				
			} catch(NumberFormatException e) { return null ; } 
			
		}
		
		
		
		
		Map<String, Object> map = new HashMap<String, Object>() ;
		
		if(params.startsWith("coeur_faction_contains_upgrade_")) {
	    	for(TypeUpgrades t : TypeUpgrades.values()) 
	    		map.put("coeur_faction_contains_upgrade_"+t.name().toLowerCase(), coeur.containsUpgrade(t)) ;
    		
	    	if(map.containsKey(params)) return map.get(params)+"" ;
	    	map.clear();
		}
    	
		if(params.startsWith("coeur_faction_get_level_upgrade_")) {
	    	for(TypeUpgrades t : TypeUpgrades.values())
	    		map.put("coeur_faction_get_level_upgrade_"+t.name().toLowerCase(), coeur.containsUpgrade(t) ? coeur.getUpgrade(t).getLevel_unlock_upgrade() : 0) ;
	    		
	    	if(map.containsKey(params)) return map.get(params)+"" ;
	    	map.clear();
		}
    	
		if(params.startsWith("coeur_faction_small_description_upgrade_")) {
	    	for(TypeUpgrades t : TypeUpgrades.values())
	    		map.put("coeur_faction_small_description_upgrade_"+t.name().toLowerCase(), t.getSmallDescription(coeur.containsUpgrade(t) ? coeur.getUpgrade(t).getLevel_unlock_upgrade() : 0)) ;
	    	
	    	if(map.containsKey(params)) return map.get(params)+"" ;
	    	map.clear();
		}
    	
    	
		
		
		
    	
    	
        return null; // Placeholder is unknown by the Expansion
    }
    
    
    public static void test(Player p) {
    	
    	Map<String, Object> map = new LinkedHashMap<String, Object>() ;
    	
    	MFaction mfaction = MUtilsPlayers.getMFaction(p.getPlayer()) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
    	
    	map.put("coeur_faction_location", coeur.getCoordsFormatted()) ;
    	map.put("coeur_faction_last_player_damager", coeur.getLast_damager_player() == null ? "" : coeur.getLast_damager_player().getName()) ;
    	
    	map.put("hearth_level_name", coeur.getHearthLevel().getName()) ;
    	map.put("hearth_level_max_health", coeur.getHearthLevel().getMax_health()) ;
    	map.put("hearth_level_max_points", coeur.getHearthLevel().getMax_points()) ;
    	map.put("hearth_level_price_next_level", coeur.getHearthLevel().getPrice_to_unlock()) ;
    	
    	map.put("coeur_faction_current_health", coeur.getCurrent_health()) ;
    	map.put("coeur_faction_current_shield", coeur.getNb_bouclier()) ;
    	map.put("coeur_faction_current_points_upgrades", coeur.getCurrent_points_upgrades()) ;
    	map.put("coeur_faction_level_time_between_2_attacks", null) ;
    	map.put("coeur_faction_seconds_between_2_attacks", null) ;
    	map.put("coeur_faction_nb_upgrades_actived", coeur.getList_attacks_enabled().size()) ;
    	
    	map.put("coeur_faction_is_spawned", coeur.isSpawned()) ;
    	map.put("coeur_faction_formatted_time_before_spawn", coeur.getTimeStringBeforeCanSpawn()) ;
    	
    	map.put("damage_total_do_others_faction", null) ;
    	map.put("coeur_tues_others_faction", null) ;
    	
    	map.put("classements_get_rank_damager", null) ;
    	map.put("classements_get_rank_killer", null) ;
    	
    	map.put("classements_damager_faction_name_0", null) ;
    	map.put("classements_damager_faction_name_1", null) ;
    	map.put("classements_damager_faction_name_2", null) ;
    	map.put("classements_damager_faction_damages_0", null) ;
    	map.put("classements_damager_faction_damages_1", null) ;
    	map.put("classements_damager_faction_damages_2", null) ;
    	
    	map.put("classements_killer_faction_name_0", null) ;
    	map.put("classements_killer_faction_name_1", null) ;
    	map.put("classements_killer_faction_name_2", null) ;
    	map.put("classements_killer_faction_kills_0", null) ;
    	map.put("classements_killer_faction_kills_1", null) ;
    	map.put("classements_killer_faction_kills_2", null) ;
		
    	
    	
    	for(TypeUpgrades t : TypeUpgrades.values()) {
    		map.put("coeur_faction_contains_upgrade_"+t.name().toLowerCase(), coeur.containsUpgrade(t)) ;
    		map.put("coeur_faction_get_level_upgrade_"+t.name().toLowerCase(), coeur.containsUpgrade(t) ? coeur.getUpgrade(t).getLevel_unlock_upgrade() : 0) ;
    		map.put("coeur_faction_small_description_upgrade_"+t.name().toLowerCase(), t.getSmallDescription(coeur.containsUpgrade(t) ? coeur.getUpgrade(t).getLevel_unlock_upgrade() : 0)) ;
    	}
    	
    	for(Entry<String, Object> e : map.entrySet()) {
    		String key = "%arka-coeurfaction_"+e.getKey()+"%" ;
    		String value = PlaceholderAPI.setPlaceholders(p, key) ;
    		System.out.println(key+" : "+value);
    	}
    	
    	
    	
    }
    
    /*
%arka-coeurfaction_coeur_faction_location% : §cAucun
%arka-coeurfaction_coeur_faction_last_player_damager% : 
%arka-coeurfaction_hearth_level_name% : Coeur de Diamant (niveau 4)
%arka-coeurfaction_hearth_level_max_health% : 17500.0
%arka-coeurfaction_hearth_level_max_points% : 5000000
%arka-coeurfaction_hearth_level_price_next_level% : 5000000
%arka-coeurfaction_coeur_faction_current_health% : 0.0
%arka-coeurfaction_coeur_faction_current_shield% : 0
%arka-coeurfaction_coeur_faction_current_points_upgrades% : 0
%arka-coeurfaction_coeur_faction_level_time_between_2_attacks% : 0
%arka-coeurfaction_coeur_faction_seconds_between_2_attacks% : 90
%arka-coeurfaction_coeur_faction_nb_upgrades_actived% : 0
%arka-coeurfaction_coeur_faction_is_spawned% : false
%arka-coeurfaction_coeur_faction_formatted_time_before_spawn% : 03m56
%arka-coeurfaction_damage_total_do_others_faction% : 36213
%arka-coeurfaction_coeur_tues_others_faction% : 4
%arka-coeurfaction_classements_get_rank_damager% : 1
%arka-coeurfaction_classements_get_rank_killer% : 1
%arka-coeurfaction_classements_damager_faction_name_0% : papa
%arka-coeurfaction_classements_damager_faction_name_1% : Aucune
%arka-coeurfaction_classements_damager_faction_name_2% : Aucune
%arka-coeurfaction_classements_damager_faction_damages_0% : 36213
%arka-coeurfaction_classements_damager_faction_damages_1% : Aucune
%arka-coeurfaction_classements_damager_faction_damages_2% : Aucune
%arka-coeurfaction_classements_killer_faction_name_0% : papa
%arka-coeurfaction_classements_killer_faction_name_1% : Aucune
%arka-coeurfaction_classements_killer_faction_name_2% : Aucune
%arka-coeurfaction_classements_killer_faction_kills_0% : 3
%arka-coeurfaction_classements_killer_faction_kills_1% : Aucune
%arka-coeurfaction_classements_killer_faction_kills_2% : Aucune
%arka-coeurfaction_coeur_faction_contains_upgrade_cactus% : false
%arka-coeurfaction_coeur_faction_get_level_upgrade_cactus% : 0
%arka-coeurfaction_coeur_faction_small_description_upgrade_cactus% : §eCactus §6(niveau §e0§6)
%arka-coeurfaction_coeur_faction_contains_upgrade_eggs% : false
%arka-coeurfaction_coeur_faction_get_level_upgrade_eggs% : 0
%arka-coeurfaction_coeur_faction_small_description_upgrade_eggs% : §eJet d'oeufs §6(niveau §e0§6)
%arka-coeurfaction_coeur_faction_contains_upgrade_fireball% : false
%arka-coeurfaction_coeur_faction_get_level_upgrade_fireball% : 0
%arka-coeurfaction_coeur_faction_small_description_upgrade_fireball% : §eFireball §6(niveau §e0§6)
%arka-coeurfaction_coeur_faction_contains_upgrade_hunger% : false
%arka-coeurfaction_coeur_faction_get_level_upgrade_hunger% : 0
%arka-coeurfaction_coeur_faction_small_description_upgrade_hunger% : §eHunger §6(niveau §e0§6)
%arka-coeurfaction_coeur_faction_contains_upgrade_irongolem% : true
%arka-coeurfaction_coeur_faction_get_level_upgrade_irongolem% : 1
%arka-coeurfaction_coeur_faction_small_description_upgrade_irongolem% : §eIron Golem §6(niveau §e1§6)
%arka-coeurfaction_coeur_faction_contains_upgrade_jump% : false
%arka-coeurfaction_coeur_faction_get_level_upgrade_jump% : 0
%arka-coeurfaction_coeur_faction_small_description_upgrade_jump% : §eSaut au 7ème ciel §6(niveau §e0§6)
%arka-coeurfaction_coeur_faction_contains_upgrade_knockback% : false
%arka-coeurfaction_coeur_faction_get_level_upgrade_knockback% : 0
%arka-coeurfaction_coeur_faction_small_description_upgrade_knockback% : §eEjection §6(niveau §e0§6)
%arka-coeurfaction_coeur_faction_contains_upgrade_nausea% : false
%arka-coeurfaction_coeur_faction_get_level_upgrade_nausea% : 0
%arka-coeurfaction_coeur_faction_small_description_upgrade_nausea% : §eNausée §6(niveau §e0§6)
%arka-coeurfaction_coeur_faction_contains_upgrade_pnjhealer% : false
%arka-coeurfaction_coeur_faction_get_level_upgrade_pnjhealer% : 0
%arka-coeurfaction_coeur_faction_small_description_upgrade_pnjhealer% : §ePNJ Soutien §6(niveau §e0§6)
%arka-coeurfaction_coeur_faction_contains_upgrade_poison% : false
%arka-coeurfaction_coeur_faction_get_level_upgrade_poison% : 0
%arka-coeurfaction_coeur_faction_small_description_upgrade_poison% : §ePoison §6(niveau §e0§6)
%arka-coeurfaction_coeur_faction_contains_upgrade_bouclier% : false
%arka-coeurfaction_coeur_faction_get_level_upgrade_bouclier% : 0
%arka-coeurfaction_coeur_faction_small_description_upgrade_bouclier% : §eBouclier §6(niveau §e0§6)
%arka-coeurfaction_coeur_faction_contains_upgrade_resistance% : false
%arka-coeurfaction_coeur_faction_get_level_upgrade_resistance% : 0
%arka-coeurfaction_coeur_faction_small_description_upgrade_resistance% : §eResistance §6(niveau §e0§6)
%arka-coeurfaction_coeur_faction_contains_upgrade_slime% : false
%arka-coeurfaction_coeur_faction_get_level_upgrade_slime% : 0
%arka-coeurfaction_coeur_faction_small_description_upgrade_slime% : §eSlime §6(niveau §e0§6)
%arka-coeurfaction_coeur_faction_contains_upgrade_wither% : false
%arka-coeurfaction_coeur_faction_get_level_upgrade_wither% : 0
%arka-coeurfaction_coeur_faction_small_description_upgrade_wither% : §eWither §6(niveau §e0§6)
     */
    
	
}
