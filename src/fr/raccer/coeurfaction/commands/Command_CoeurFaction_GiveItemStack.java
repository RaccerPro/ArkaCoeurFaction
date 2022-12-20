package fr.raccer.coeurfaction.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.raccer.coeurfaction.datafaction.CoeurFaction_ItemStack;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;

public class Command_CoeurFaction_GiveItemStack {
	

	@Command(name = "coeurfaction.giveItemStack", permission = "arka.coeurfaction.giveItemStack")
	public void onCoeurFaction(CommandArgs a) {
		
		
		if(a.length() != 1) {
			a.getSender().sendMessage("§cUsage : /coeurfaction giveItemStack [player]");
			return ;
		}
		
		Player p = Bukkit.getPlayer(a.getArgs(0)) ;
		if(p == null) {
			a.getSender().sendMessage("§cPlayer introuvable.");
			return ;
		}
		ItemStack i = new CoeurFaction_ItemStack().getItem() ;
		p.getInventory().addItem(i) ;
	}

}
