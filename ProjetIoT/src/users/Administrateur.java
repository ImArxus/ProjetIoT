package users;

public class Administrateur extends Utilisateur {

	public Administrateur(String nom) {
		super(nom);
		super.setDroits(1);
	}

}
