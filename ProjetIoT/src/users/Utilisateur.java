package users;

import java.util.Map;

public class Utilisateur {

	protected Map<String, String> user;
	protected int droits;

	public Utilisateur(String pseudo, String mdp) {
		user.put(pseudo, mdp);
		setDroits(3); // Aucun droit
	}

	public int getDroits() {
		return droits;
	}

	public void setDroits(int droits) {
		this.droits = droits;
	}

	public String actionsPossibles() {
		return "-> Pour changer de pièce, tapez 'move'\n-> Pour utiliser un équipement, tapez 'use'\n-> Pour quitter, tapez 'exit'";
	}

}
