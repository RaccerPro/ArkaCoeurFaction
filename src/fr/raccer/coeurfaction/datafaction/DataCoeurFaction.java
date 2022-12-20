package fr.raccer.coeurfaction.datafaction;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fr.raccer.mutilsplayers.data.MData;
import lombok.Getter;
import lombok.Setter;

public class DataCoeurFaction extends MData {
	
	public static final String ID = "arka-coeur-faction" ;

	@Getter @Setter private CoeurFaction coeurFaction ;
	@Getter @Setter private int damageTotal ; // Damage sur les autres coeurs 
	@Getter @Setter private int coeurTues ; // Nb de coeur tues
	
	@Getter @Setter private int rank_damageTotal ;
	@Getter @Setter private int rank_coeurTues ;
	
	public DataCoeurFaction() {
		setCoeurFaction(new CoeurFaction());
		setDamageTotal(0);
		setCoeurTues(0);
		setRank_damageTotal(0);
		setRank_coeurTues(0);
	}
	
	@Override
	public String getID() {
		return DataCoeurFaction.ID ;
	}

	@Override
	public boolean isSavable() {
		return true;
	}

	@Override
	public JsonElement serialize(Gson gson) {
		
		JsonObject obj = new JsonObject() ;
		
		obj.add("coeurFaction", gson.toJsonTree(coeurFaction));
		obj.addProperty("damageTotal", damageTotal);
		obj.addProperty("coeurTues", coeurTues);
		obj.addProperty("rank_damageTotal", rank_damageTotal);
		obj.addProperty("rank_coeurTues", rank_coeurTues);
		
		return obj;
	}


	@Override
	public MData deserialize(JsonObject obj, JsonDeserializationContext context) {
				
		this.coeurFaction = context.deserialize(obj.get("coeurFaction"), CoeurFaction.class) ;
		this.damageTotal = obj.get("damageTotal").getAsInt() ;
		this.coeurTues = obj.get("coeurTues").getAsInt() ;
		this.rank_damageTotal = obj.get("rank_damageTotal").getAsInt() ;
		this.rank_coeurTues = obj.get("rank_coeurTues").getAsInt() ;
		
		return this ;
	}

	public void addDamageTotal(double damage) {
		this.damageTotal += damage ;
	}
	public void addCoeurTues(int nombre) {
		this.coeurTues += nombre ;
	}




	
	
}
