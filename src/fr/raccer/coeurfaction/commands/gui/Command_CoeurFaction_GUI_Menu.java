package fr.raccer.coeurfaction.commands.gui;

import org.bukkit.entity.Player;

import fr.raccer.coeurfaction.menus.MenuType;
import fr.raccer.coeurfaction.menus.MenusAdapter;
import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mplayers.MPlayer;

public class Command_CoeurFaction_GUI_Menu {
	
	@Command(name = "coeurfaction.gui.menu", permission = "arka.coeurfaction..gui.menu", inGameOnly = true)
	public void onCoeurFaction(CommandArgs a) {
		Player p = a.getPlayer() ;
		MPlayer mp = MUtilsPlayers.getMPlayer(p) ;
		p.openInventory(new MenusAdapter().get(mp, MenuType.MenuDefault)) ;
	}

}
