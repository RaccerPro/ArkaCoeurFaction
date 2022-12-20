package fr.raccer.coeurfaction.upgrades;

import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lombok.Getter;
import lombok.Setter;

public enum UpgradeLevel {

	Upgrade_Speed_Lvl_0("Attaque lente", 90, 0),
	Upgrade_Speed_Lvl_1("Attaque moyenne", 60, 100),
	Upgrade_Speed_Lvl_2("Attaque efficace", 45, 200),
	Upgrade_Speed_Lvl_3("Attaque rapide", 30, 300),
	Upgrade_Speed_Lvl_4("Attaque très rapide", 15, 500) ;
	
	@Getter @Setter private String name ;
	@Getter @Setter private int cooldown_between_2_attacks_seconds ;
	@Getter @Setter private int price_to_unlock ;
	
	private UpgradeLevel(String name, int cooldown_between_2_attacks_seconds, int price_to_unlock) {
		setName(name);
		setCooldown_between_2_attacks_seconds(cooldown_between_2_attacks_seconds);
		setPrice_to_unlock(price_to_unlock);
	}
	
	public UpgradeLevel nextLevel() {
		if(this == Upgrade_Speed_Lvl_0) return Upgrade_Speed_Lvl_1 ;
		if(this == Upgrade_Speed_Lvl_1) return Upgrade_Speed_Lvl_2 ;
		if(this == Upgrade_Speed_Lvl_2) return Upgrade_Speed_Lvl_3 ;
		if(this == Upgrade_Speed_Lvl_3) return Upgrade_Speed_Lvl_4 ;
		if(this == Upgrade_Speed_Lvl_4) return null ;
		return null ;
	}
	
	public UpgradeLevel beforeLevel() {
		if(this == Upgrade_Speed_Lvl_0) return null ;
		if(this == Upgrade_Speed_Lvl_1) return Upgrade_Speed_Lvl_0 ;
		if(this == Upgrade_Speed_Lvl_2) return Upgrade_Speed_Lvl_1 ;
		if(this == Upgrade_Speed_Lvl_3) return Upgrade_Speed_Lvl_2 ;
		if(this == Upgrade_Speed_Lvl_4) return Upgrade_Speed_Lvl_3 ;
		return null ;
	}
	
	public static JsonObject getJsonObjectEnum() {
		JsonObject obj = new JsonObject() ;
		
		for(UpgradeLevel h : UpgradeLevel.values()) {
			JsonObject hearthObj = new JsonObject() ;
			hearthObj.addProperty("name", h.name);
			hearthObj.addProperty("cooldown_between_2_attacks_seconds", h.cooldown_between_2_attacks_seconds);
			hearthObj.addProperty("price_to_unlock", h.price_to_unlock);
			obj.add(h.name(), hearthObj);
		}

		return obj ;	
	}

	public static void loadFromJsonObject(JsonObject jsonObj) {
		
		for(Entry<String, JsonElement> p : jsonObj.entrySet()) {
			UpgradeLevel h = UpgradeLevel.valueOf(p.getKey()) ;
			JsonObject obj = p.getValue().getAsJsonObject();
			
			h.setName(obj.get("name").getAsString());
			h.setCooldown_between_2_attacks_seconds(obj.get("cooldown_between_2_attacks_seconds").getAsInt());
			h.setPrice_to_unlock(obj.get("price_to_unlock").getAsInt());
		}
		
	}

	public int getLevelSpeedAttack() {
		if(this == Upgrade_Speed_Lvl_0) return 0 ;
		if(this == Upgrade_Speed_Lvl_1) return 1 ;
		if(this == Upgrade_Speed_Lvl_2) return 2 ;
		if(this == Upgrade_Speed_Lvl_3) return 3 ;
		if(this == Upgrade_Speed_Lvl_4) return 4 ;
		return -1 ;
	}
	
}
