package fr.raccer.coeurfaction.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.google.gson.JsonArray;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;

import fr.raccer.coeurfaction.Main;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.upgrades.levels.UpgradeFireBall;
import fr.raccer.coeurfaction.upgrades.levels.UpgradeWither;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;

public class ListenerExplosiveEvent implements Listener {
	
	public ListenerExplosiveEvent() {}

	@EventHandler
	public void onExploseWither(EntityExplodeEvent e) {
		
		if(e.getEntityType() != EntityType.WITHER_SKULL && e.getEntityType() != EntityType.FIREBALL) return ;
		
		Entity ent = e.getEntity() ;
		if(ent.hasMetadata(UpgradeWither.meta_data_wither)
			|| ent.hasMetadata(UpgradeFireBall.meta_data_fireball)) e.setCancelled(true);
	}
	
	@EventHandler
	public void onExplose(EntityExplodeEvent e) {
		
		if(e.getEntityType() == EntityType.ENDER_CRYSTAL) {
			if(!e.getEntity().hasMetadata(CoeurFaction.meta_data_entity)) return ;
			e.setCancelled(true);
			return ;
		}
		
		List<Block> block_not_remove = new ArrayList<Block>() ; 
		JsonArray array = Main.instance.getMconfig().get("list_material_not_explode_when_heart_actived").getAsJsonArray() ;
		
		for(Block b : e.blockList()) {
			
			Faction fac = Board.getInstance().getFactionAt(new FLocation(b)) ;
			if(fac.isWilderness() || fac.isSafeZone() || fac.isWarZone()) continue ;
			MFaction mfac = MUtilsPlayers.getMFaction(fac) ;
			CoeurFaction coeur = mfac.getData(DataCoeurFaction.ID, DataCoeurFaction.class).getCoeurFaction() ;
			
			if(!coeur.isSpawned()) continue ;
			
			for(int i=0 ; i<array.size() ; i++) {
				if(array.get(i).getAsString().equalsIgnoreCase(b.getType().name())) {
					block_not_remove.add(b) ;
				}
			}
		}
		
		for(Block b : block_not_remove) e.blockList().remove(b) ;
		
	}
	
}
