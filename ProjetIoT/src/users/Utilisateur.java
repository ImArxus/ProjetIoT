package users;

public class Utilisateur {

	protected String nom;
	protected int droits;

	public Utilisateur(String nom) {
		setNom(nom);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDroits() {
		return droits;
	}

	public void setDroits(int droits) {
		this.droits = droits;
	}

	public String actionsPossibles() {
		return "-> Pour changer de pièce, tapez 'move'\n-> Pour utiliser un équipement, tapez 'use'\n-> Pour quitter, tapez 'exit'";
	}

}
