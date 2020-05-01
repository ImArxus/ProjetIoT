package main;

public abstract class Equipement {

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
		return "-> Allumer\n-> Eteindre";
	}

	public String toString() {
		return getNom() + " : " + isEtatCourant();
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
