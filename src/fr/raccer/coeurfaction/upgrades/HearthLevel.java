package fr.raccer.coeurfaction.upgrades;

import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lombok.Getter;
import lombok.Setter;

public enum HearthLevel {
	
	Hearth_Lvl_0("Coeur de Terre (niveau 0)", 0, 0, 0, 0),
	Hearth_Lvl_1("Coeur de Bois (niveau 1)",5000, 500, 60, 100000),
	Hearth_Lvl_2("Coeur de Pierre (niveau 2)",7500, 750, 45, 1000000),
	Hearth_Lvl_3("Coeur de Fer (niveau 3)",10000, 1000, 35, 2500000),
	Hearth_Lvl_4("Coeur de Diamant (niveau 4)",15000, 1500, 20, 5000000) ;

	@Getter @Setter private String name ;
	@Getter @Setter private double max_health ;
	@Getter @Setter private int max_points ;
	@Getter @Setter private int time_respawn_after_killed_minutes ;
	@Getter @Setter private int price_to_unlock ;
	
	private HearthLevel(String name, double max_health, int max_points, int time_respawn_after_killed_minutes, int price) {
		setName(name);
		setMax_health(max_health);
		setMax_points(max_points);
		setTime_respawn_after_killed_minutes(time_respawn_after_killed_minutes);
		setPrice_to_unlock(price);
	}
	
	public HearthLevel nextLevel() {
		if(this == Hearth_Lvl_0) return Hearth_Lvl_1 ;
		if(this == Hearth_Lvl_1) return Hearth_Lvl_2 ;
		if(this == Hearth_Lvl_2) return Hearth_Lvl_3 ;
		if(this == Hearth_Lvl_3) return Hearth_Lvl_4 ;
		return null ;
	}
	
	public static JsonObject getJsonObjectEnum() {
		JsonObject obj = new JsonObject() ;
		
		for(HearthLevel h : HearthLevel.values()) {
			
			JsonObject hearthObj = new JsonObject() ;
			hearthObj.addProperty("name", h.name);
			hearthObj.addProperty("max_health", h.max_health);
			hearthObj.addProperty("max_points", h.max_points);
			hearthObj.addProperty("time_respawn_after_killed_minutes", h.time_respawn_after_killed_minutes);
			hearthObj.addProperty("price_to_unlock", h.price_to_unlock);
			obj.add(h.name(), hearthObj);
		}

		return obj ;	
	}
	
	public static void loadFromJsonObject(JsonObject jsonObj) {
		
		for(Entry<String, JsonElement> p : jsonObj.entrySet()) {
			HearthLevel h = HearthLevel.valueOf(p.getKey()) ;
			JsonObject obj = p.getValue().getAsJsonObject();
			
			h.setName(obj.get("name").getAsString());
			h.setMax_health(obj.get("max_health").getAsDouble());
			h.setMax_points(obj.get("max_points").getAsInt());
			h.setTime_respawn_after_killed_minutes(obj.get("time_respawn_after_killed_minutes").getAsInt());
			h.setPrice_to_unlock(obj.get("price_to_unlock").getAsInt());
		}
		
	}
	
	
	
}
