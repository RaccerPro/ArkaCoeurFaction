package fr.raccer.coeurfaction.classements;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import fr.raccer.coeurfaction.Main;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import lombok.Getter;
import lombok.Setter;

public class ClassementManager implements Updatable {

	@Getter @Setter private Classement classement ;
	
	public ClassementManager() {
		setClassement(new Classement());
	}
	
	@Override
	public void update() {
		classement.update();
		Bukkit.getConsoleSender().sendMessage("§eActualisation des classements des Coeurs de Factions.");
	}
	
	
	public MFaction getMFactionDamagerByPlace(int place) {
		return classement.getMFactionDamagerByPlace(place) ;	
	}
	
	public MFaction getMFactionKillerByPlace(int place) {
		return classement.getMFactionKillerByPlace(place) ;
	}

	public void start() {
		
		int timer = Main.instance.getMconfig().getAsInt("timer_cooldown_update_classements_minutes") ;
		ClassementManager manager = this ;
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				manager.update(); 
			}
		}.runTaskTimer(Main.instance, 5, 20*60*timer) ; // Toutes les 15 minutes par défaut
		
	}
	

}
