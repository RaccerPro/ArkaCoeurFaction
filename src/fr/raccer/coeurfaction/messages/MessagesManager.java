package fr.raccer.coeurfaction.messages;

import java.util.LinkedList;
import java.util.List;

import fr.raccer.coeurfaction.Main;
import fr.raccer.mutils.config.configMessages.MLang;
import fr.raccer.mutils.config.configMessages.MMessage;
import fr.raccer.mutils.config.configMessages.MMessageEnum;
import fr.raccer.mutils.config.configMessages.MMessageImpl;
import lombok.Getter;

public class MessagesManager implements MMessageImpl {

	@Override
	public List<MMessage> defaultMessages() {
		List<MMessage> list = new LinkedList<MMessage>() ;
		
		for(M m : M.values()) {
			MMessage msg = new MMessage(m.name()) ;
			msg.setMessage(MLang.FR, m.default_fr) ;
			list.add(msg) ;
		}
			
		
		return list ;
	}
	
	public enum M implements MMessageEnum {
		
		PREFIX("§b[§3§lCoeurFaction§b]§r ") ,
		
		NEED_HAVE_A_FACTION("§cVous devez avoir une faction pour faire cette commande."),
		
		NOT_LEADER_FACTION("§cVous n'êtes pas chef de votre faction."),
		ID_NOT_FIND("§cID introuvable. /coeurfaction ids"),
		
		DONT_HAVE_POINTS_AVAILABLE("§cVous n'avez pas assez de points d'améliorations disponibles."),
		DONT_HAVE_MONEY_AVAILABLE("§cVous n'avez pas les fonds nécessaires."),
		ADDED_UPGRADE("§aAmélioration ajoutée !"),
		USAGE_CMD_BUY("§6/coeurfaction buy [ID]"),
		USAGE_CMD_REMOVE("§6/coeurfaction remove [ID]"),
		USAGE_CMD_GIVEITEMSTACK("§cUsage : /coeurfaction giveItemStack [player]"),
		
		UPGRADE_MINIMUM_LEVEL("§cCette amélioration est au minimum."),
		UPGRADE_MAXIMUM_LEVEL("§cCette amélioration est déjà au maximum."),
		HEARTH_MAXIMUM_LEVEL("§6Vous avez déjà atteins le niveau maximum."),
		
		UPGRADE_NOT_UNLOCKED("§cVous n'avez pas débloquer cette amélioration."),
		
		RECEIVE_X_UPGRADE_POINTS("§cVous avez reçu {points} points d'améliorations."),
		UPGRADE_REMOVE("§cL'amélioration {upgrade_name} a été supprimé."),
		UPGRADE_LEVEL_CHANGE("§cL'amélioration {upgrade_name} a été mis au niveau {upgrade_level}."),
		
		UNLOCK_HEARTH_LEVEL("§6Vous venez de débloquer le §e§l%arka-coeurfaction_hearth_level_name% §6!"),
		
		SET_LEVEL_SPEED_ATTACK("§6Votre attaque est désormais en §e{level_speed_attack_name}"),
		HEART_FACTION_REMOVE("§eVous venez de retirer le coeur de faction."),
		
		COOLDOWN_BEFORE_SPAWN("§cIl reste {time_spawn} avant que vous puissiez mettre à nouveau votre coeur de faction."),
		CANT_SPAWN_OUTSIDE_CLAIMS("§cVous ne pouvez poser le coeur de faction uniquement dans vos claims."),
		NOT_UNLOCKED_FIRST_LEVEL("§cVous n'avez pas débloquer le premier niveau du Coeur de Faction."),
		HEART_PLACED("§aLe coeur a été placé avec succès !"),
		HEART_KILLED("§c§lVotre coeur de faction a été tué !"),
		NEED_TO_LOCATE_ABOVE_Y("§cVous ne pouvez pas positionner votre coeur de faction en dessous de la couche 5.") ,
		
		ERROR("§cUne erreur est survenue."),
		
		;
		
		@Getter private String default_fr ;
		
		private M(String s) {
			default_fr = s ;
		}
		
		public MMessage get() {
			return Main.instance.getMessagesManager().get(this.name()) ;
		}
		
	}

}
