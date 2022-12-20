package fr.raccer.coeurfaction.placeholdersapi;

import org.bukkit.Bukkit;

import fr.raccer.coeurfaction.placeholdersapi.expansions.Placeholder_CoeurFaction;

public class PlaceholderManager {

	public PlaceholderManager() {
		if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            new Placeholder_CoeurFaction().register() ;
	}
	
}
