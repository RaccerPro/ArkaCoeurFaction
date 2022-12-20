package fr.raccer.coeurfaction.datafaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import fr.raccer.coeurfaction.Main;
import fr.raccer.coeurfaction.events.CoeurFactionExecuteUpgradeEvent;
import fr.raccer.coeurfaction.events.CoeurFactionKilledEvent;
import fr.raccer.coeurfaction.events.CoeurFactionTakeDamageEvent;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.HearthLevel;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;
import fr.raccer.coeurfaction.upgrades.UpgradeLevel;
import fr.raccer.mutils.utils.MUtilsMethods;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import fr.raccer.mutilsplayers.mplayers.MPlayer;
import fr.raccer.mutilsplayers.utils.mlocation.MLocation;
import lombok.Getter;
import lombok.Setter;

public class CoeurFaction {
	
	public final static String meta_data_entity = "arka-coeur-faction" ; 
	
	@Getter private String id_faction ;
	@Getter @Setter private MFaction mfaction ;
	@Getter private MLocation mlocation ;
	@Getter private Entity enderCrystal ;
	
	@Getter @Setter private MPlayer last_damager_player ;
	
	@Getter @Setter private HearthLevel hearthLevel ;
	@Getter @Setter private double current_health ;
	//@Getter @Setter private double max_health ;
	
	@Getter @Setter private int nb_bouclier ;
	
	@Getter @Setter private long time_last_destroyed ;
	//@Getter @Setter private int level_time_before_respawn ;
	
	//@Getter @Setter private int max_points_upgrades ;
	@Getter @Setter private int current_points_upgrades ;
	
	@Getter @Setter private UpgradeLevel upgradeSpeedAttackLevel ;
	@Getter @Setter private long time_last_upgrade_attack ; // Time de la dernière attack
	
	@Getter @Setter private int nb_indice_attack ; // Sert à savoir quelle attack doit être execute
	@Getter @Setter private List<CoeurUpgrades> list_attacks_enabled ; // Toutes les attacks pouvant être activées
	
	public CoeurFaction() {
		id_faction = "-1" ;
		hearthLevel = HearthLevel.Hearth_Lvl_0 ;
		//max_health = current_health = 500.0 ;
		
		nb_bouclier = 0 ;
		
		time_last_destroyed = 0 ;
		//level_time_before_respawn = 0 ;
		
		//max_points_upgrades = 0 ;
		current_points_upgrades = 0 ;
		
		upgradeSpeedAttackLevel = UpgradeLevel.Upgrade_Speed_Lvl_0 ;
		time_last_upgrade_attack = 0 ;
		
		nb_indice_attack = 0 ;
		list_attacks_enabled = new LinkedList<CoeurUpgrades>() ;
	}
	
	public void set_faction(String id_faction) {
		this.id_faction = id_faction ;
		this.mfaction = MUtilsPlayers.getMFaction(id_faction) ;
	}
	
	public void addUpgrade(CoeurUpgrades upgrade) {
		list_attacks_enabled.add(upgrade) ;
	}
	
	public CoeurUpgrades getUpgrade(TypeUpgrades type) {
		for(CoeurUpgrades u : list_attacks_enabled)
			if(u.getTypeUpgrade() == type) return u ;
		return null ;
	}
	
	public boolean containsUpgrade(TypeUpgrades type) {
		return this.getUpgrade(type) != null ;
	}
	
	public void removeUpgrade(TypeUpgrades type) {
		for(CoeurUpgrades u : list_attacks_enabled)
			if(u.getTypeUpgrade() == type) {
				list_attacks_enabled.remove(u) ;
				return ;
			}
	}
	
	public boolean isSpawned() {
		return mlocation != null ;
	}
	
	public boolean canSpawn() {
		return System.currentTimeMillis() - time_last_destroyed > 
			hearthLevel.getTime_respawn_after_killed_minutes()*1000*60 ;
	}
	
	public void spawnWithLocation(Location l) {
		this.mlocation = new MLocation(l.getWorld(), l.getBlockX()+0.5, l.getBlockY()+1, l.getBlockZ()+0.5) ;
		this.spawn();
	}
	
	public String getTimeStringBeforeCanSpawn() {
		long i = (System.currentTimeMillis() - time_last_destroyed) ;
		long d = hearthLevel.getTime_respawn_after_killed_minutes()*60*1000 - i ;
		if(d < 0) return "Aucun" ;
		return MUtilsMethods.getTimeFormatted_mm_ss(d) ;
	}
	
	public void setMLocation(MLocation mloc) {
		this.mlocation = mloc ;
		Collection<Entity> coll = mloc.getLocation().getWorld().getNearbyEntities(mloc.getLocation(), 2, 2, 2) ;
		for(Entity e : coll)
			if(e instanceof EnderCrystal) {
				this.setEnderCrystal(e);
				return ;
			}
	}
	
	public void setEnderCrystal(Entity e) {
		this.enderCrystal = e ;
		enderCrystal.setMetadata(meta_data_entity, new FixedMetadataValue(Main.instance, this));
	}
	
	public void spawn() {
		this.current_health = hearthLevel.getMax_health() ;
		this.setEnderCrystal(mlocation.getLocation().getWorld().spawnEntity(mlocation.getLocation(), EntityType.ENDER_CRYSTAL));
		this.updateDisplayHealth();
		
		Location loc = new Location(mlocation.getWorld(), mlocation.getX(), mlocation.getY(), mlocation.getZ()) ;
		
		for(int x=-1 ; x < 2 ; x++)
			for(int y=-1 ; y < 2 ; y++)
				for(int z=-1 ; z < 2 ; z++) {
					Location l = new Location(mlocation.getWorld(), loc.getBlockX()+x, loc.getBlockY()+y, loc.getBlockZ()+z) ;
					l.getBlock().setType(Material.AIR);
				}
	
		loc.add(0, -2, 0) ;
		
		for(int x=-1 ; x < 2 ; x++)
			for(int z=-1 ; z < 2 ; z++) {
				Location l = new Location(mlocation.getWorld(), loc.getBlockX()+x, loc.getBlockY(), loc.getBlockZ()+z) ;
				l.getBlock().setType(Material.BEDROCK);
			}
	}
	
	
	public void updateDisplayHealth() {
		enderCrystal.setCustomName("§l§6"+current_health+" §l§c♥ §8║ §3 "+nb_bouclier+" §b§l◍");
		enderCrystal.setCustomNameVisible(true);
	}
	
	
	public void killCoeurFaction() {
		
		Location loc = new Location(mlocation.getWorld(), mlocation.getX(), mlocation.getY(), mlocation.getZ()) ;
		
		loc.add(0, -2, 0) ;
		
		for(int x=-1 ; x < 2 ; x++)
			for(int z=-1 ; z < 2 ; z++) {
				Location l = new Location(mlocation.getWorld(), loc.getBlockX()+x, loc.getBlockY(), loc.getBlockZ()+z) ;
				l.getBlock().setType(Material.STONE);
			}
		
		if(enderCrystal != null || !enderCrystal.isDead()) enderCrystal.remove();
		mlocation = null ;

	}
	
	public void addHealth(double health) {
		double newHealth = this.current_health + health ;
		if(newHealth > hearthLevel.getMax_health()) newHealth = hearthLevel.getMax_health() ;
		this.setCurrent_health(newHealth);
		this.updateDisplayHealth();
	}
	
	public void addBouclier(int nombre) {
		nb_bouclier += nombre ;
	}
	
	public void takeDamage(double damage) {
		
		if(nb_bouclier > 0) damage = 0 ;
		
		CoeurFactionTakeDamageEvent e = new CoeurFactionTakeDamageEvent(mfaction, last_damager_player, damage) ;
		Bukkit.getPluginManager().callEvent(e);
		if(e.isCancelled()) return ;
		
		if(nb_bouclier > 0) nb_bouclier-- ;
		else current_health -= damage ;
		
		
		
		if(current_health < 0) {
			CoeurFactionKilledEvent ekilled = new CoeurFactionKilledEvent(mfaction, last_damager_player) ;
			Bukkit.getPluginManager().callEvent(ekilled);
			if(ekilled.isCancelled()) return ;
			
			time_last_destroyed = System.currentTimeMillis() ;
			this.killCoeurFaction() ;
			return ;
		}
		
		this.updateDisplayHealth(); 
		
		if(list_attacks_enabled.isEmpty()) return ;
		if(list_attacks_enabled.size() >= nb_indice_attack) nb_indice_attack = 0 ;
		
		this.activeAttackUpgrade() ;
		
	}



	private void activeAttackUpgrade() {

		long now = System.currentTimeMillis() ;
		
		if(now - time_last_upgrade_attack < 
				upgradeSpeedAttackLevel.getCooldown_between_2_attacks_seconds()*1000) return ;
		
		CoeurUpgrades upgrade = list_attacks_enabled.get(nb_indice_attack) ;
		
		CoeurFactionExecuteUpgradeEvent e = new CoeurFactionExecuteUpgradeEvent(mfaction, upgrade) ;
		Bukkit.getPluginManager().callEvent(e);
		if(e.isCancelled()) return ;
		
		upgrade.execute(this);
		
		time_last_upgrade_attack = now ;
	}	
	
	
	public boolean isSameFaction(Player player) {
		FPlayer fp = FPlayers.getInstance().getByPlayer(player) ;
		return fp.getFactionId().equalsIgnoreCase(id_faction) ;
	}
	
	
	public List<Player> getListPlayerEnnemyNear(double x, double y, double z){
		List<Player> list = new ArrayList<Player>() ;
		
		Collection<Entity> coll = mlocation.getWorld().getNearbyEntities(mlocation.getLocation(), x, y, z) ;
		for(Entity e : coll)
			if(e instanceof Player 
					//&& !this.isSameFaction((Player) e)
				) list.add((Player) e) ;
		
		
		return list ;
	}
	
	public List<Player> getListPlayerFactionNear(double x, double y, double z){
		List<Player> list = new ArrayList<Player>() ;
		
		Collection<Entity> coll = mlocation.getWorld().getNearbyEntities(mlocation.getLocation(), x, y, z) ;
		for(Entity e : coll)
			if(e instanceof Player && this.isSameFaction((Player) e)) 
				list.add((Player) e) ;
		
		
		return list ;
	}
	
	
	
	public void givePoints(int nombre) {
		current_points_upgrades += nombre ;
	}
	
	public boolean containsPoints(int nombre) {
		return current_points_upgrades >= nombre ;
	}
	
	public void takePoints(int nombre) {
		current_points_upgrades -= nombre ;
		if(current_points_upgrades < 0) current_points_upgrades = 0 ;
	}

	public String getCoordsFormatted() {
		if(mlocation == null) return "§cAucun" ;
		return mlocation.getBlockX()+" "+mlocation.getBlockY()+" "+mlocation.getBlockZ() ;
	}
	
	
	
	
	
	
	
}
