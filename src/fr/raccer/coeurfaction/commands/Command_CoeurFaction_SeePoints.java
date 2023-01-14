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

public class Command_CoeurFaction_SeePoints {
	

	@Command(name = "coeurfaction.seePoints", permission = "arka.coeurfaction.seePoints")
	public void onCoeurFaction(CommandArgs a) {
		
		CommandSender sender = a.getSender() ;
		Faction fac = null ; 
		FactionsAPI api = FactionsBridge.getFactionsAPI() ;
		
		
		if(sender instanceof Player) {
			fac = api.getFaction(a.getPlayer()) ;
		}
		
		if(sender.isOp() && a.length() > 0){
			if(a.length() != 1) {
				sender.sendMessage(Main.PREFIX+"§cUsage : /coeurfaction seePoints [Nom_Faction]");
				return ;
			}
			fac = api.getFactionByTag(a.getArgs(0)) ;
		}
		
		if(fac == null) {
			sender.sendMessage(Main.PREFIX+"§cFaction introuvable.");
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(fac) ;
		if(mfaction == null) {
			sender.sendMessage(Main.PREFIX+"§cFaction introuvable.");
			return ;
		}
		
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		sender.sendMessage(Main.PREFIX+"§6La faction "+fac.getTag()+" possède §e"+coeur.getCurrent_points_upgrades()+" points d'améliorations§6.");
	}

}
