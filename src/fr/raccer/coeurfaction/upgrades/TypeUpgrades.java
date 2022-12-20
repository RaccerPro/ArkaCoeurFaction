package fr.raccer.coeurfaction.upgrades;

import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fr.raccer.coeurfaction.upgrades.levels.UpgradeBouclier;
import fr.raccer.coeurfaction.upgrades.levels.UpgradeCactus;
import fr.raccer.coeurfaction.upgrades.levels.UpgradeEggs;
import fr.raccer.coeurfaction.upgrades.levels.UpgradeFireBall;
import fr.raccer.coeurfaction.upgrades.levels.UpgradeHunger;
import fr.raccer.coeurfaction.upgrades.levels.UpgradeIronGolem;
import fr.raccer.coeurfaction.upgrades.levels.UpgradeJump;
import fr.raccer.coeurfaction.upgrades.levels.UpgradeKnockback;
import fr.raccer.coeurfaction.upgrades.levels.UpgradeNausea;
import fr.raccer.coeurfaction.upgrades.levels.UpgradePNJHealer;
import fr.raccer.coeurfaction.upgrades.levels.UpgradePoison;
import fr.raccer.coeurfaction.upgrades.levels.UpgradeResistance;
import fr.raccer.coeurfaction.upgrades.levels.UpgradeSlime;
import fr.raccer.coeurfaction.upgrades.levels.UpgradeWither;
import lombok.Getter;
import lombok.Setter;

public enum TypeUpgrades {
	
	CACTUS("Cactus", 50, 100, 150),
	EGGS("Jet d'oeufs", 50, 100, 150),
	FIREBALL("Fireball", 50, 100, 150),
	HUNGER("Hunger", 50, 100, 150),
	IRONGOLEM("Iron Golem", 50, 100, 150),
	JUMP("Saut au 7ème ciel", 50, 100, 150),
	KNOCKBACK("Ejection", 50, 100, 150),
	NAUSEA("Nausée", 50, 100, 150),
	PNJHEALER("PNJ Soutien", 50, 100, 150),
	POISON("Poison", 50, 100, 150),
	Bouclier("Bouclier", 50, 100, 150),
	RESISTANCE("Resistance", 50, 100, 150),
	SLIME("Slime", 50, 100, 150),
	WITHER("Wither", 50, 100, 150)
	
	;
	
	
	@Getter @Setter private String name ; 
	@Getter @Setter private int price_lvl_1, price_lvl_2, price_lvl_3 ;
	
	private TypeUpgrades(String name, int price_lvl_1, int price_lvl_2, int price_lvl_3) {
		setName(name);
		setPrice_lvl_1(price_lvl_1);
		setPrice_lvl_2(price_lvl_2);
		setPrice_lvl_3(price_lvl_3);
	}
	
	
	public static TypeUpgrades convert(String str) {
		for(TypeUpgrades t : values())
			if(t.name().equalsIgnoreCase(str)) return t; 
		
		return null;
	}
	
	public CoeurUpgrades adapterToUpgrade() {
		
		if(this == CACTUS) return new UpgradeCactus() ;
		if(this == EGGS) return new UpgradeEggs() ;
		if(this == FIREBALL) return new UpgradeFireBall() ;
		if(this == HUNGER) return new UpgradeHunger() ;
		if(this == IRONGOLEM) return new UpgradeIronGolem() ;
		if(this == JUMP) return new UpgradeJump() ;
		if(this == KNOCKBACK) return new UpgradeKnockback() ;
		if(this == NAUSEA) return new UpgradeNausea() ;
		if(this == PNJHEALER) return new UpgradePNJHealer() ;
		if(this == POISON) return new UpgradePoison() ;
		if(this == Bouclier) return new UpgradeBouclier() ;
		if(this == RESISTANCE) return new UpgradeResistance() ;
		if(this == SLIME) return new UpgradeSlime() ;
		if(this == WITHER) return new UpgradeWither() ;
		
		return null; 
	}
	
	
	
	
	public static JsonObject getJsonObjectEnum() {
		JsonObject obj = new JsonObject() ;
		
		for(TypeUpgrades u : TypeUpgrades.values()) {
			
			JsonObject upObj = new JsonObject() ;
			
			upObj.addProperty("name", u.name);
			upObj.addProperty("price_lvl_1", u.price_lvl_1);
			upObj.addProperty("price_lvl_2", u.price_lvl_2);
			upObj.addProperty("price_lvl_3", u.price_lvl_3);
			
			obj.add(u.name(), upObj);
		}

		return obj ;
		
	}
	
	public static void loadFromJsonObject(JsonObject jsonObj) {
		
		for(Entry<String, JsonElement> p : jsonObj.entrySet()) {
			TypeUpgrades u = TypeUpgrades.valueOf(p.getKey()) ;
			JsonObject obj = p.getValue().getAsJsonObject();
			
			u.setName(obj.get("name").getAsString());
			u.setPrice_lvl_1(obj.get("price_lvl_1").getAsInt());
			u.setPrice_lvl_2(obj.get("price_lvl_2").getAsInt());
			u.setPrice_lvl_3(obj.get("price_lvl_3").getAsInt());
		}
		
	}


	public String getSmallDescription(int level_unlock_upgrade) {
		return "§e"+this.name+" §6(niveau §e"+level_unlock_upgrade+"§6)" ;
	}
	
	

}
