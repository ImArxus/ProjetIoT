package equipements;

import main.Equipement;

public class Alarme extends Equipement {
	
	public Alarme(String nom) {
		super(nom);
	}

	public Alarme(String nom, boolean etatCourant) {
		super(nom, etatCourant);
	}

	public void sonner() {
		System.out.println("Bip !!! Bip !!! Bip !!!");
		System.out.println("Vous devez sortir de la maison...");
	}

}
