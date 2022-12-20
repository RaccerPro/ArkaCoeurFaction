package fr.raccer.coeurfaction.datafaction.serializer;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.upgrades.CoeurUpgrades;
import fr.raccer.coeurfaction.upgrades.HearthLevel;
import fr.raccer.coeurfaction.upgrades.TypeUpgrades;
import fr.raccer.coeurfaction.upgrades.UpgradeLevel;
import fr.raccer.mutilsplayers.utils.mlocation.MLocation;

public class SerializerCoeurFaction implements JsonSerializer<CoeurFaction>, JsonDeserializer<CoeurFaction> {

	
	
	@Override
	public JsonElement serialize(CoeurFaction coeur, Type type, JsonSerializationContext context) {
		
		JsonObject obj = new JsonObject() ;
		
		obj.addProperty("id_faction", coeur.getId_faction());
		if(coeur.isSpawned()) obj.add("mlocation", context.serialize(coeur.getMlocation()));
		
		obj.addProperty("hearth_level", coeur.getHearthLevel().name());
		obj.addProperty("current_health", coeur.getCurrent_health());
		/*
		obj.addProperty("max_health", coeur.getMax_health());
		obj.addProperty("level_time_before_respawn", coeur.getLevel_time_before_respawn());
		obj.addProperty("max_points_upgrades", coeur.getMax_points_upgrades());
		*/
		obj.addProperty("nb_bouclier", coeur.getNb_bouclier());
		obj.addProperty("time_last_destroyed", coeur.getTime_last_destroyed());
		obj.addProperty("current_points_upgrades", coeur.getCurrent_points_upgrades());
		obj.addProperty("upgrade_speed_attack_level", coeur.getUpgradeSpeedAttackLevel().name());
		//obj.addProperty("level_time_unlock", coeur.getLevel_time_unlock());
		obj.addProperty("nb_indice_attack", coeur.getNb_indice_attack());
		
		Map<String, Integer> map = new HashMap<String, Integer>() ;
		
		for(CoeurUpgrades u : coeur.getList_attacks_enabled())
			map.put(u.getTypeUpgrade().name(), u.getLevel_unlock_upgrade()) ;
		
		obj.add("list_attacks_enabled", context.serialize(map));
		
		return obj ;
	}

	
	
	@Override
	public CoeurFaction deserialize(JsonElement ele, Type type, JsonDeserializationContext context) throws JsonParseException {
		
		
		CoeurFaction coeur = new CoeurFaction() ;
		JsonObject obj = ele.getAsJsonObject() ;
		
		if(obj.has("id_faction"))
			coeur.set_faction(obj.get("id_faction").getAsString());
		
		if(obj.has("mlocation"))
			coeur.setMLocation(context.deserialize(obj.get("mlocation"), MLocation.class));
		
		if(obj.has("mlocation"))
			coeur.setCurrent_health(obj.get("current_health").getAsDouble());
		
		if(obj.has("nb_bouclier"))
			coeur.setNb_bouclier(obj.get("nb_bouclier").getAsInt());
		if(obj.has("time_last_destroyed"))
			coeur.setTime_last_destroyed(obj.get("time_last_destroyed").getAsLong());
		if(obj.has("hearth_level"))
			coeur.setHearthLevel(HearthLevel.valueOf(obj.get("hearth_level").getAsString()));
		if(obj.has("upgrade_speed_attack_level"))
			coeur.setUpgradeSpeedAttackLevel(UpgradeLevel.valueOf(obj.get("upgrade_speed_attack_level").getAsString()));
		/*
		if(obj.has("max_health"))
			coeur.setMax_health(obj.get("max_health").getAsDouble());
		if(obj.has("level_time_before_respawn"))
			coeur.setLevel_time_before_respawn(obj.get("level_time_before_respawn").getAsInt());
		if(obj.has("max_points_upgrades"))
			coeur.setMax_points_upgrades(obj.get("max_points_upgrades").getAsInt());
		 */
		if(obj.has("current_points_upgrades"))
			coeur.setCurrent_points_upgrades(obj.get("current_points_upgrades").getAsInt());
		if(obj.has("nb_indice_attack"))
			coeur.setNb_indice_attack(obj.get("nb_indice_attack").getAsInt());
		
		if(obj.has("list_attacks_enabled")) {
			JsonObject mapObj = obj.get("list_attacks_enabled").getAsJsonObject() ;
			
			for(Entry<String, JsonElement> p : mapObj.entrySet()) {
				TypeUpgrades typeUp = TypeUpgrades.convert(p.getKey()) ;
				if(typeUp == null) continue ;
				CoeurUpgrades up = typeUp.adapterToUpgrade() ;
				up.setLevel_unlock_upgrade(p.getValue().getAsInt());
				coeur.addUpgrade(up);
			}
		}
		
		return coeur ;
	}

	
}
