package equipements;

import main.Equipement;
import main.Main;

public class Alexa extends Equipement {

	private static String pseudo;
	
	public Alexa(String nom) {
		super(nom, false);
		pseudo=Main.getPseudo();
	}

	public Alexa(String nom, boolean etatCourant) {
		super(nom, etatCourant);
		pseudo=Main.getPseudo();
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Alexa quelle heure est-il?\n";
	}
	public void reponseHeure() {
		System.out.println(" Bonjour "+pseudo+", il est actuellement "+Main.getHeure()+"h!");
	}
	
}
