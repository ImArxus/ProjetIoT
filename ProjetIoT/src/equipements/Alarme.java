package equipements;

import java.io.Serializable;

import main.Equipement;

public class Alarme extends Equipement implements Serializable {

	private static final long serialVersionUID = -1828693427527124467L;

	public Alarme(String nom) {
		super(nom);
		this.setEtatCourant(true);
	}

	public Alarme(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale) {
		super(nom, etatCourant, positionHorizontale, positionVerticale);
	}

	public void sonner() {
		System.out.println("\nBip !!! Bip !!! Bip !!!");
		System.out.println("Vous devez sortir de la maison...");
	}

}
