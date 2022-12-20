package fr.raccer.coeurfaction.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;

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
		
		if(sender instanceof Player) {
			fac = FPlayers.getInstance().getByPlayer(a.getPlayer()).getFaction() ;
		}
		
		if(sender.isOp() && a.length() > 0){
			if(a.length() != 1) {
				sender.sendMessage("§cUsage : /coeurfaction seePoints [Nom_Faction]");
				return ;
			}
			fac = Factions.getInstance().getByTag(a.getArgs(0)) ;
		}
		
		if(fac == null) {
			sender.sendMessage("§cFaction introuvable.");
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(fac) ;
		if(mfaction == null) {
			sender.sendMessage("§cFaction introuvable.");
			return ;
		}
		
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		sender.sendMessage("§6La faction "+fac.getTag()+" possède §e"+coeur.getCurrent_points_upgrades()+" points d'améliorations§6.");
	}

}
