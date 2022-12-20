package fr.raccer.coeurfaction.datafaction;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import fr.raccer.mutils.mcustom.mitem.MItem;
import fr.raccer.mutils.mcustom.usable.UsableItemStack;
import fr.raccer.mutilsplayers.MUtilsPlayers;
import fr.raccer.mutilsplayers.mfactions.MFaction;

public class CoeurFaction_ItemStack extends UsableItemStack {
	
	public static List<String> listLores = new LinkedList<String>() ;

	@Override
	public void useEffectItem(Event event) {

		if(!(event instanceof BlockPlaceEvent)) return ;
		

		BlockPlaceEvent e = (BlockPlaceEvent) event ;
		
		Player p = e.getPlayer() ;
		FPlayer fp = FPlayers.getInstance().getByPlayer(p) ;
		
		if(!fp.isInOwnTerritory()) {
			p.sendMessage("§cVous ne pouvez poser le coeur de faction uniquement dans vos claims.");
			e.setCancelled(true);
			return ;
		}
		
		MFaction mfaction = MUtilsPlayers.getMFaction(p) ;
		DataCoeurFaction dataCoeur = mfaction.getData(DataCoeurFaction.ID, DataCoeurFaction.class) ;
		CoeurFaction coeur = dataCoeur.getCoeurFaction() ;
		
		coeur.spawnWithLocation(e.getBlock().getLocation());
		mfaction.sendMessage("§e§l"+p.getName()+" §6a posé votre coeur de faction.");
	}

	@Override
	public Class<? extends Event> initEvent() {
		return BlockPlaceEvent.class ;
	}

	@Override
	public ItemStack initItemStack() {

		MItem item = new MItem(Material.ENDER_PORTAL_FRAME)
				.name("§6§lCoeur de Faction")
				.lores(listLores)
				.setEnchanted(true) ;
		ItemStack i = item.make() ;
		return i ;
	}

	
	
}
