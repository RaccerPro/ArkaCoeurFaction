package fr.raccer.coeurfaction.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.javajobs.factionsbridge.FactionsBridge;
import cc.javajobs.factionsbridge.bridge.infrastructure.struct.Faction;
import cc.javajobs.factionsbridge.bridge.infrastructure.struct.FactionsAPI;
import fr.raccer.coeurfaction.Main;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;

public class Command_CoeurFaction_GivePoints {
	

	@Command(name = "coeurfaction.givePoints", permission = "arka.coeurfaction.givePoints")
	public void onCoeurFaction(CommandArgs a) {
		
		CommandSender sender = a.getSender() ;
		
		if(a.length() != 2) {
			sender.sendMessage(Main.PREFIX+"§cUsage : /coeurfaction givePoints [nom_faction] [nombre]");
			return ;
		}
		FactionsAPI api = FactionsBridge.getFactionsAPI();
		Faction fac = api.getFactionByTag(a.getArgs(0)) ;
		MFaction mfaction = MUtilsPlayers.getMFaction(fac.getId()) ;
		
		if(fac == null || mfaction == null) {
			sender.sendMessage(Main.PREFIX+"§cFaction introuvable.");
			return ;
		}
		
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		int nombre = 0 ;
		try {
			nombre = Integer.parseInt(a.getArgs(1));
		} catch(NumberFormatException ex) {}
		
		coeur.givePoints(nombre);
		
		if(sender instanceof Player)
			sender.sendMessage(Main.PREFIX+"§aLa faction "+fac.getTag()+" a reçu "+nombre+" points d'améliorations.");
	}

}
