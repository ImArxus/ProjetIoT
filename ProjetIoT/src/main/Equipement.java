package main;

public class Equipement {

	protected boolean etatCourant;
	protected String nom;

	protected Equipement(String nom) {
		setNom(nom);
		setEtatCourant(false);
	}

	protected Equipement(String nom, boolean etatCourant) {
		this.setNom(nom);
		this.setEtatCourant(etatCourant);
	}

	public String actionsPossibles() {
		return "➡️ 1 :Quitter\n➡️ 2 : Allumer\n➡️ 3 : Eteindre";
	}

	public String toString() {
		String etat = "éteint(e)";
		if(isEtatCourant()) {
			etat = "allumé(e)";
		}
		return getNom() + " (" + etat + ")";
	}

	public void setEtatCourant(boolean etatCourant) {
		this.etatCourant = etatCourant;
	}

	public boolean isEtatCourant() {
		return etatCourant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void allumer() {
		if (!isEtatCourant()) {
			setEtatCourant(true);
		}
	}

	public void eteindre() {
		if (isEtatCourant()) {
			setEtatCourant(false);
		}
	}
	
}
