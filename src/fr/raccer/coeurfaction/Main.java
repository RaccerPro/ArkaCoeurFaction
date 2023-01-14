package fr.raccer.coeurfaction;

import java.util.Collection;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import fr.raccer.coeurfaction.classements.ClassementManager;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_Buy;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_BuyNextLevelHearth;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_BuySpeedAttack;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_GiveItemStack;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_GivePoints;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_Help;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_Ids;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_Reload;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_Remove;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_RemoveSpeedAttack;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_ResetSpawn;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_SeePoints;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_SeeUpgrades;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_Spawn;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_TakePoints;
import fr.raccer.coeurfaction.commands.Command_CoeurFaction_UpdateClassements;
import fr.raccer.coeurfaction.commands.tests.Command_CoeurFaction_TestPlaceholders;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.CoeurFaction_ItemStack;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.coeurfaction.datafaction.serializer.SerializerCoeurFaction;
import fr.raccer.coeurfaction.listeners.ListenerCoeurExecuteUpgrade;
import fr.raccer.coeurfaction.listeners.ListenerCoeurKilled;
import fr.raccer.coeurfaction.listeners.ListenerCoeurTakeDamage;
import fr.raccer.coeurfaction.listeners.ListenerEntityDamage;
import fr.raccer.coeurfaction.listeners.ListenerExplosiveEvent;
import fr.raccer.coeurfaction.listeners.ListenerFactionUnclaim;
import fr.raccer.coeurfaction.listeners.ListenerMFactionCreate;
import fr.raccer.coeurfaction.listeners.ListenerMFactionDisband;
import fr.raccer.coeurfaction.messages.MessagesManager;
import fr.raccer.coeurfaction.placeholdersapi.PlaceholderManager;
import fr.raccer.coeurfaction.upgrades.HearthLevel;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;
import fr.raccer.coeurfaction.upgrades.UpgradeLevel;
import fr.raccer.mutils.MPlugin;
import fr.raccer.mutils.MUtils;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import fr.raccer.mutilsplayers.utils.mconfig.MConfig;
import lombok.Getter;
import lombok.Setter;

public class Main extends MPlugin {

	public static String PREFIX = "§b[§3§lCoeurFaction§b]§r " ;
	
	public static Main instance ;
	@Getter @Setter private ClassementManager classementManager ;
	@Getter @Setter private MConfig mconfig ;
	@Getter @Setter private PlaceholderManager placeholderManager ;
	
	@Override
	public void onLoad() {
		
		instance = this ;
		MUtilsPlayers.getInstance().registerTypeAdapterGson(CoeurFaction.class, new SerializerCoeurFaction());
		
	}

	@Override
	public void onEnableCore() {
		
		this.loadMConfig();
		
		MUtils.getInstance().addUsableItemStack(new CoeurFaction_ItemStack());
		placeholderManager = new PlaceholderManager() ;
		classementManager = new ClassementManager() ;
		classementManager.start() ;
		
		super.registerListener(new ListenerMFactionCreate());
		super.registerListener(new ListenerMFactionDisband());
		super.registerListener(new ListenerEntityDamage());
		super.registerListener(new ListenerFactionUnclaim());
		super.registerListener(new ListenerExplosiveEvent());
		super.registerListener(new ListenerCoeurExecuteUpgrade());
		super.registerListener(new ListenerCoeurKilled());
		super.registerListener(new ListenerCoeurTakeDamage());
		//super.registerListener(new ListenerEggHitGround());
		
		super.registerClassCommand(new Command_CoeurFaction());
		super.registerClassCommand(new Command_CoeurFaction_Help());
		super.registerClassCommand(new Command_CoeurFaction_Reload());
		super.registerClassCommand(new Command_CoeurFaction_BuyNextLevelHearth());
		super.registerClassCommand(new Command_CoeurFaction_ResetSpawn());
		super.registerClassCommand(new Command_CoeurFaction_Spawn());
		super.registerClassCommand(new Command_CoeurFaction_Buy());
		super.registerClassCommand(new Command_CoeurFaction_Remove());
		super.registerClassCommand(new Command_CoeurFaction_BuySpeedAttack());
		super.registerClassCommand(new Command_CoeurFaction_RemoveSpeedAttack());
		super.registerClassCommand(new Command_CoeurFaction_Ids());
		super.registerClassCommand(new Command_CoeurFaction_GivePoints());
		super.registerClassCommand(new Command_CoeurFaction_GiveItemStack());
		super.registerClassCommand(new Command_CoeurFaction_TakePoints());
		super.registerClassCommand(new Command_CoeurFaction_SeePoints());
		super.registerClassCommand(new Command_CoeurFaction_SeeUpgrades());
		super.registerClassCommand(new Command_CoeurFaction_TestPlaceholders());
		super.registerClassCommand(new Command_CoeurFaction_UpdateClassements());
		
		super.getMessagesManager().register(new MessagesManager());
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				initEntitiesFactions() ;
			}
		}.runTaskLater(this, 5) ;
		
		
	}

	@Override
	public void onDisableCore() {
		
		mconfig.save();
		
	}


	public void loadMConfig() {
		mconfig = new MConfig(this) ;
		
		this.initConfiguration() ;
		mconfig.load();
		HearthLevel.loadFromJsonObject(mconfig.getAsJsonObject("HearthProperties"));
		TypeUpgrades.loadFromJsonObject(mconfig.getAsJsonObject("UpgradesProperties"));
		UpgradeLevel.loadFromJsonObject(mconfig.getAsJsonObject("UpgradeLevelSpeed"));
		
		JsonArray array_lores = mconfig.get("itemstack_coeur_faction_lores").getAsJsonArray() ;
		CoeurFaction_ItemStack.listLores.clear();
		for(int i=0 ; i<array_lores.size() ; i++)
			CoeurFaction_ItemStack.listLores.add(array_lores.get(i).getAsString()) ;
		
	}
	
	private void initConfiguration() {
		
		mconfig.add("timer_cooldown_update_classements_minutes", 15);
		
		mconfig.add("timer_cooldown_auto_regeneration_seconds", 5);
		mconfig.add("health_added_auto_regeneration", 50);
		/*
		mconfig.add("cost_upgrade_faction_lvl_1_pts", 50);
		mconfig.add("cost_upgrade_faction_lvl_2_pts", 100);
		mconfig.add("cost_upgrade_faction_lvl_3_pts", 150);
		
		
		mconfig.add("cost_upgrade_time_lvl_1_pts", 100);
		mconfig.add("cost_upgrade_time_lvl_2_pts", 200);
		mconfig.add("cost_upgrade_time_lvl_3_pts", 300);
		mconfig.add("cost_upgrade_time_lvl_4_pts", 500);
		
		mconfig.add("time_cooldown_attack_upgrade_lvl_0_seconds", 90);
		mconfig.add("time_cooldown_attack_upgrade_lvl_1_seconds", 60);
		mconfig.add("time_cooldown_attack_upgrade_lvl_2_seconds", 45);
		mconfig.add("time_cooldown_attack_upgrade_lvl_3_seconds", 30);
		mconfig.add("time_cooldown_attack_upgrade_lvl_4_seconds", 15);
		*/
		
		JsonArray jsonArray = new JsonArray() ;
		jsonArray.add(new JsonPrimitive("BEACON"));
		jsonArray.add(new JsonPrimitive("HOPPER"));
		jsonArray.add(new JsonPrimitive("CHEST"));
		mconfig.add("list_material_not_explode_when_heart_actived", jsonArray);
		
		mconfig.add("HearthProperties", HearthLevel.getJsonObjectEnum());
		mconfig.add("UpgradesProperties", TypeUpgrades.getJsonObjectEnum());
		mconfig.add("UpgradeLevelSpeed", UpgradeLevel.getJsonObjectEnum());
		
		
		JsonArray jsonArray_loresItem = new JsonArray() ;
		jsonArray_loresItem.add(new JsonPrimitive(""));
		jsonArray_loresItem.add(new JsonPrimitive("§7Ce coeur de faction permet"));
		jsonArray_loresItem.add(new JsonPrimitive("§7de se protéger des explosions."));
		jsonArray_loresItem.add(new JsonPrimitive(""));
		jsonArray_loresItem.add(new JsonPrimitive(""));
		jsonArray_loresItem.add(new JsonPrimitive(""));
		jsonArray_loresItem.add(new JsonPrimitive(""));
		jsonArray_loresItem.add(new JsonPrimitive(""));
		mconfig.add("itemstack_coeur_faction_lores", jsonArray_loresItem);
		
		
		
	}
	
	
	

	private void initEntitiesFactions() {
		
		//Collection<EnderCrystal> crystals = Bukkit.getWorld("Flat").getEntitiesByClass(EnderCrystal.class) ;
		//System.out.println("Size crystals : "+crystals.size());
		
		for(Entry<String, MFaction> p : MUtilsPlayers.getInstance().getMfactionsManager().getMapToMFaction().entrySet()) {
			
			MFaction mfac = p.getValue() ;
			DataCoeurFaction dataCoeur = mfac.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
			CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
			/*
			System.out.println("--------");
			System.out.println("Mfac : "+mfac.getId());
			*/
			if(coeur.isSpawned()) {
				
				//System.out.println("Is spawned");
				Location loc = coeur.getMlocation().getLocation() ;
				Collection<EnderCrystal> crystals = loc.getWorld().getEntitiesByClass(EnderCrystal.class) ;
				//System.out.println(">> Test size : "+crystals.size());
				//Collection<Entity> coll = loc.getWorld().getNearbyEntities(loc, 5, 5, 5) ;
				
				for(EnderCrystal crys : crystals) {
					
					//System.out.println("Distance : "+crys.getLocation().distance(loc)+" : "+crys.getLocation());
					if(crys.getLocation().distance(loc) < 2) {
						//System.out.println("Set endercrystal");
						coeur.setEnderCrystal(crys);
						break ;
					}
					
				}
				/*
				for(Entity e : coll) {
					System.out.println("> "+e.getType()+" : "+e.getLocation());
					if(!(e instanceof EnderCrystal)) continue ;
					System.out.println("Set endercrystal");
					coeur.setEnderCrystal(e);
					break ;
				}
				*/
			}
			
			
		}
		
	}
	
	
}
