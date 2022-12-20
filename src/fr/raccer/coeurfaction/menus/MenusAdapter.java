package fr.raccer.coeurfaction.menus;

import org.bukkit.inventory.Inventory;

import fr.raccer.mutilsplayers.mplayers.MPlayer;

public class MenusAdapter {
	
	public MenusAdapter() {}
	
	public Inventory get(MPlayer mp, MenuType menu) {
		return menu.getMInventory(mp).getInv() ;
	}

}
