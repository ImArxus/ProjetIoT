package users;

public class Invite extends Utilisateur {

	public Invite(String nom) {
		super(nom);
		super.setDroits(2);
	}

}
