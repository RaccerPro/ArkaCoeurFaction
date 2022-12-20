package fr.raccer.coeurfaction.menus.minventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.menus.MenuMInventory;
import fr.raccer.mutils.mcustom.mitem.MInventory;
import fr.raccer.mutils.mcustom.mitem.MItem;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import fr.raccer.mutilsplayers.mplayers.MPlayer;

public class MInventoryMenu implements MenuMInventory {

	@Override
	public MInventory get(MPlayer mp) {
		
		MFaction mfaction = MUtilsPlayers.getMFaction(mp.getPlayer()) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		MInventory minv = new MInventory(54, "§7Coeur de Faction") ;
		this.setDefaultPanes(minv);
		
		List<String> lores_egg = new ArrayList<String>();
		lores_egg.add("") ;
		lores_egg.add("§6Coordonnées : ") ;
		lores_egg.add("§e§l"+coeur.getCoordsFormatted()) ;
		
		
		if(!coeur.isSpawned()) {
			lores_egg.add("") ;
			lores_egg.add("§6Temps avant de pouvoir l'activer :") ;
			lores_egg.add("§e§l"+coeur.getTimeStringBeforeCanSpawn()) ;
			lores_egg.add("") ;
		}
		
		MItem egg = new MItem(Material.DRAGON_EGG)
				.name("§6Le Coeur")
				.lores(lores_egg) ;
		
		MItem book = new MItem(Material.BOOK_AND_QUILL)
				.name("§6Configuration des compétences")
				.lores("") ;
		
		minv.setMItem(22, egg);
		minv.setMItem(31, book);
		
		return minv;
	}
	
	
	
}
