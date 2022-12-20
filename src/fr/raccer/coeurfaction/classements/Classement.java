package fr.raccer.coeurfaction.classements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import lombok.Getter;
import lombok.Setter;

public class Classement implements Updatable {
	
	@Getter @Setter private List<MFaction> listDamager ;
	@Getter @Setter private List<MFaction> listKiller ;
	
	public Classement() {
		setListDamager(new ArrayList<MFaction>());
		setListKiller(new ArrayList<MFaction>());
	}
	
	@Override
	public void update() {
		
		listDamager.clear();
		listKiller.clear();
		
		Collection<MFaction> collection = MUtilsPlayers.getInstance().getMfactionsManager().getMapToMFaction().values() ;
		listDamager.addAll(collection) ;
		listKiller.addAll(collection) ;
		
		listDamager.sort((mf1, mf2) -> 
			mf2.getData(DataCoeurFaction.ID, DataCoeurFaction.class).getDamageTotal() 
			- mf1.getData(DataCoeurFaction.ID, DataCoeurFaction.class).getDamageTotal() 
		);
		
		listKiller.sort((mf1, mf2) -> 
			mf2.getData(DataCoeurFaction.ID, DataCoeurFaction.class).getCoeurTues() 
			- mf1.getData(DataCoeurFaction.ID, DataCoeurFaction.class).getCoeurTues() 
		);
		
		for(int i=0 ; i < collection.size() ; i++) {
			listDamager.get(i).getData(DataCoeurFaction.ID, DataCoeurFaction.class).setRank_damageTotal(i+1);
			listKiller.get(i).getData(DataCoeurFaction.ID, DataCoeurFaction.class).setRank_coeurTues(i+1);
		}
		
	}
	
	public MFaction getMFactionDamagerByPlace(int place) {
		return listDamager.size() > place ? listDamager.get(place) : null ;	
	}
	
	public MFaction getMFactionKillerByPlace(int place) {
		return listKiller.size() > place ? listKiller.get(place) : null ;
	}

	
	
}
