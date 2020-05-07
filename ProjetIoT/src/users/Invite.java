package users;

import java.util.Map;

public class Invite extends Utilisateur {

	private Map<String, String> guests;

	public Invite(String pseudo, String mdp) {
		super(pseudo, mdp);
		super.setDroits(2);
	}

	public Map<String, String> getGuests() {
		return guests;
	}

	public void setGuests(Map<String, String> guests) {
		this.guests = guests;
	}

}
