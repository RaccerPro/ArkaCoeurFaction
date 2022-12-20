package fr.raccer.coeurfaction.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mplayers.MPlayer;

public class ListenerEntityDamage implements Listener {
	
	@EventHandler
	public void onTakeDamage(EntityDamageEvent e) {
		
		if(e.getEntityType() != EntityType.ENDER_CRYSTAL) return ;
		Entity entity = e.getEntity() ;
		if(!entity.hasMetadata(CoeurFaction.meta_data_entity)) return ;
		
		e.setCancelled(true);
		
	}
	
	@EventHandler
	public void onTakeDamagePlayer(EntityDamageByEntityEvent e) {
		
		if(e.getEntityType() != EntityType.ENDER_CRYSTAL) return ;
		Entity entity = e.getEntity() ;
		if(!entity.hasMetadata(CoeurFaction.meta_data_entity)) return ;
		
		e.setCancelled(true);
		
		if(!(e.getDamager() instanceof Player)) return ;
		
		Player damager = (Player) e.getDamager() ;
		MPlayer mplayer = MUtilsPlayers.getMPlayer(damager) ;
		CoeurFaction coeur = (CoeurFaction) entity.getMetadata(CoeurFaction.meta_data_entity).get(0).value() ;
		coeur.setLast_damager_player(mplayer);
		coeur.takeDamage(e.getDamage());
		
	}

}
