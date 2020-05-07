package main;

import java.util.HashMap;
import java.util.Map;

//test mez

public class ListeUtilisateurs {
	protected Map<String, String> comptes=new HashMap<String, String>();//(pseudo,mdp)
	protected Map<String, Boolean> estAdmin=new HashMap<String, Boolean>();//(pseudo ,estAdmin)
	

	public ListeUtilisateurs() {
		comptes.put("prof", "prof");
		comptes.put("mez", "khodja");
		comptes.put("alex", "le balanger");
		comptes.put("eloy", "martinez");
		comptes.put("vince", "dollo");
		comptes.put("newbie1", "newbie");
		comptes.put("newbie2", "newbie");
		comptes.put("newbie3", "newbie");
		comptes.put("guest", "guest");
		estAdmin.put("prof", true);
		estAdmin.put("mez", true);
		estAdmin.put("alex", true);
		estAdmin.put("eloy", true);
		estAdmin.put("vince", true);
		estAdmin.put("newbie1", false);
		estAdmin.put("newbie2", false);
		estAdmin.put("newbie3", false);
		estAdmin.put("guest", false);
	}
	public String actionsPossibles(String pseudo) {
		String reponse ="-> Pour changer de pièce, tapez 'move'\n-> Pour utiliser un équipement, tapez 'use'\n-> Pour quitter, tapez 'exit'";;		
		if(estAdmin.get(pseudo)) {
			reponse+= "-> Créer pièce\n-> Supprimer pièce\n-> Créer équipement\n-> Supprimer équipement";
		}
		return reponse;
	}
}