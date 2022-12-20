package fr.raccer.coeurfaction.menus;

import fr.raccer.coeurfaction.menus.minventory.MInventoryMenu;
import fr.raccer.mutils.mcustom.mitem.MInventory;
import fr.raccer.mutilsplayers.mplayers.MPlayer;

public enum MenuType {
	
	MenuDefault ;
	
	public MInventory getMInventory(MPlayer mp) {
		
		if(this == MenuDefault) return new MInventoryMenu().get(mp) ;
		
		
		return new MInventory(54, "") ;
	}

}
