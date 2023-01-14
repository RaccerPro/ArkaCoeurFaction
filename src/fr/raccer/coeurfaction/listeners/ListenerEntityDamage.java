package fr.raccer.coeurfaction.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.projectiles.ProjectileSource;

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
	public void onExplodea(ExplosionPrimeEvent e) {
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
		
		Player damager = null ;
		
		if(e.getDamager() instanceof Player) damager = (Player) e.getDamager() ;
		
		else if(e.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) e.getDamager() ;
			ProjectileSource src = arrow.getShooter() ;
			if(src instanceof Player) {
				damager = (Player) src ;
				arrow.remove();
			}
		}
		
		if(damager == null) return ;
		
		MPlayer mplayer = MUtilsPlayers.getMPlayer(damager) ;
		Object obj = entity.getMetadata(CoeurFaction.meta_data_entity).get(0).value() ;
				
		if(!(obj instanceof CoeurFaction)) {
			return ;
		}
		
		CoeurFaction coeur = (CoeurFaction) obj ;
		
		if(coeur.isSameFaction(damager)) {
			//coeur.addHealth(5);
			//damager.getWorld().playEffect(coeur.getMlocation().getLocation().clone().add(0, 1.2, 0), Effect.HEART, 2);
			//return ;
		}
		
		coeur.setLast_damager_player(mplayer);
		coeur.takeDamage(e.getDamage());
		
	}

}
