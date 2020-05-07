package users;

import java.util.Map;

public class Administrateur extends Utilisateur {

	private Map<String, String> admins;

	public Administrateur(String pseudo, String mdp) {
		super(pseudo, mdp);
		super.setDroits(1);
	}

	public Map<String, String> getAdmins() {
		return admins;
	}

	public void setAdmins(Map<String, String> admins) {
		this.admins = admins;
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "-> Créer pièce\n-> Supprimer pièce\n-> Créer équipement\n-> Supprimer équipement";
	}

}
