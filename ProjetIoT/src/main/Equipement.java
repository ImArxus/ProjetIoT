package main;

public abstract class Equipement {

	protected boolean etatCourant;
	protected String nom;

	protected Equipement(boolean etatCourant, String nom) {
		this.setNom(nom);
		this.setEtatCourant(etatCourant);
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
