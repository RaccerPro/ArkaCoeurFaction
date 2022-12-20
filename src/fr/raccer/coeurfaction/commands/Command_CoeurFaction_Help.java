package fr.raccer.coeurfaction.commands;

import fr.raccer.mutils.mcustom.mcommand.Command;
import fr.raccer.mutils.mcustom.mcommand.CommandArgs;

public class Command_CoeurFaction_Help {

	@Command(name = "coeurfaction.help", permission = "arka.coeurfaction.help")
	public void Command_CoeurFaction(CommandArgs a) {
		
		String s = "§8§m+---------------- §b§lArka§3§lCoeurFaction§8§m----------------+" + "\n" +
				"§e§l» §b/coeurfaction help§6 : Affiche l'aide du plugin" + "\n" +
				"§e§l» §b/coeurfaction buy [id]§6 : Achète ou améliore une amélioration" + "\n" +
				"§e§l» §b/coeurfaction remove [id]§6 : Supprime ou rétrograde une amélioration" + "\n" +
				"§e§l» §b/coeurfaction ids§6 : Liste tous les IDs des améliorations" + "\n" +
				"§e§l» §b/coeurfaction buyNextLevelHearth§6 : Améliore son coeur de faction" + "\n" +
				"§e§l» §b/coeurfaction buySpeedAttack§6 : Améliore sa vitesse d'attaque" + "\n" +
				"§e§l» §b/coeurfaction removeSpeedAttack§6 : Enlève de la vitesse d'attaque" + "\n" +
				"§e§l» §b/coeurfaction giveItemStack [player]§6 : Give l'item pour positionner le coeur de faction" + "\n" +
				"§e§l» §b/coeurfaction givePoints [faction] [nombre]§6 : Give [nombre] points d'améliorations à la [faction]" + "\n" +
				"§e§l» §b/coeurfaction takePoints [faction] [nombre]§6 : Take [nombre] points d'améliorations à la [faction]" + "\n" +
				"§e§l» §b/coeurfaction seePoints [faction]§6 : Montre le nombre de points d'améliorations de [faction]" + "\n" +
				"§e§l» §b/coeurfaction seeUpgrades§6 : Liste toutes les améliorations actives de la faction" + "\n" +
				"§e§l» §b/coeurfaction spawn§6 : Permet de faire spawn le coeur à l'emplacement du joueur" + "\n" +
				"§e§l» §b/coeurfaction resetSpawn§6 : Permet de réinitialiser le cooldown pour poser à nouveau son coeur" + "\n" +
				"§e§l» §b/coeurfaction reload§6 : Reload la configuration en entier" + "\n" +
				"§e§l» §b/coeurfaction testPlaceholders§6 : Testes toutes les variables placeholders sur le joueur (résultat console)" + "\n" +
				"§e§l» §b/coeurfaction updateClassements§6 : Actualise tous les classements MFaction (Damage + Kills)" + "\n" +
				"§8§m+------------------------------------------------+" ; 
		
		a.getSender().sendMessage(s);
		
	}
	
}


/*

» /coeurfaction help : Affiche l'aide du plugin
» /coeurfaction buy [id] : Achète ou améliore une amélioration
» /coeurfaction remove [id] : Supprime ou rétrograde une amélioration
» /coeurfaction ids : Liste tous les IDs des améliorations
» /coeurfaction buyNextLevelHearth : Améliore son coeur de faction
» /coeurfaction buySpeedAttack : Améliore sa vitesse d'attaque
» /coeurfaction removeSpeedAttack : Enlève de la vitesse d'attaque
» /coeurfaction giveItemStack [player] : Give l'item pour positionner le coeur de faction
» /coeurfaction givePoints [faction] [nombre] : Give [nombre] points d'améliorations à la [faction]
» /coeurfaction takePoints [faction] [nombre] : Take [nombre] points d'améliorations à la [faction]
» /coeurfaction seePoints [faction] : Montre le nombre de points d'améliorations de [faction]
» /coeurfaction seeUpgrades : Liste toutes les améliorations actives de la faction
» /coeurfaction spawn : Permet de faire spawn le coeur à l'emplacement du joueur
» /coeurfaction resetSpawn : Permet de réinitialiser le cooldown pour poser à nouveau son coeur
» /coeurfaction reload : Reload la configuration en entier
» /coeurfaction testPlaceholders : Testes toutes les variables placeholders sur le joueur (résultat console)
» /coeurfaction updateClassements : Actualise tous les classements MFaction (Damage + Kills)

*/