package fr.raccer.coeurfaction.menus;

import org.bukkit.Material;

import fr.raccer.mutils.mcustom.mitem.MInventory;
import fr.raccer.mutils.mcustom.mitem.MItem;
import fr.raccer.mutilsplayers.mplayers.MPlayer;

public interface MenuMInventory {

	public MInventory get(MPlayer mp);
	
	public default MItem getPaneOrange() {
		return new MItem(Material.STAINED_GLASS_PANE).data(1).name("") ;
	}
	
	public default void setDefaultPanes(MInventory minv) {
		for(int i=0 ; i < minv.getSize() ; i++) {
			if(i%9 == 0 || i%9 == 8) minv.setMItem(i, this.getPaneOrange()) ;
		}
		minv.setMItem(1, this.getPaneOrange()) ;
		minv.setMItem(2, this.getPaneOrange()) ;
		minv.setMItem(6, this.getPaneOrange()) ;
		minv.setMItem(7, this.getPaneOrange()) ;
		minv.setMItem(10, this.getPaneOrange()) ;
		minv.setMItem(16, this.getPaneOrange()) ;
		minv.setMItem(37, this.getPaneOrange()) ;
		minv.setMItem(43, this.getPaneOrange()) ;
		minv.setMItem(52, this.getPaneOrange()) ;
		minv.setMItem(53, this.getPaneOrange()) ;
		
	}

	
}