package fr.raccer.coeurfaction.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.javajobs.factionsbridge.bridge.infrastructure.struct.Faction;
import fr.raccer.coeurfaction.Main;
import fr.raccer.coeurfaction.datafaction.CoeurFaction;
import fr.raccer.coeurfaction.datafaction.DataCoeurFaction;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;
import fr.raccer.mutilsplayers.utils.methods.MUtilsFactions;

public class Command_CoeurFaction_TakePoints {
	

	@Command(name = "coeurfaction.takePoints", permission = "arka.coeurfaction.takePoints")
	public void onCoeurFaction(CommandArgs a) {
		
		CommandSender sender = a.getSender() ;
		
		if(a.length() != 2) {
			sender.sendMessage(Main.PREFIX+"§cUsage : /coeurfaction takePoints [nom_faction] [nombre]");
			return ;
		}
		
		Faction fac = MUtilsFactions.getInstance().getFactionByTag(a.getArgs(0)) ;
		MFaction mfaction = MUtilsPlayers.getMFaction(fac) ;
		
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
		
		coeur.takePoints(nombre);
		
		if(sender instanceof Player)
			sender.sendMessage(Main.PREFIX+"§aLa faction "+fac.getTag()+" a perdu "+nombre+" points d'améliorations.");
	}

}
