package equipements;

import java.util.Scanner;

import main.Equipement;

public class Lumiere extends Equipement {

	private int intensite;
	private String couleur;

	public Lumiere(String nom) {
		super(nom, false);
		setIntensite(100);
		couleur = "blanc";
	}

	public Lumiere(String nom, boolean etatCourant, int intensite, String couleur) {
		super(nom, etatCourant);
		setIntensite(intensite);
		setCouleur(couleur);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n-> Augmenter intensité\n-> Diminuer intensité\n-> Choisir intensité\n-> Choisir couleur";
	}

	public int getIntensite() {
		return intensite;
	}

	public void setIntensite(int intensite) {
		this.intensite = intensite;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		if ((couleur.equals("blanc")) || (couleur.equals("bleu")) || (couleur.equals("rouge"))
				|| (couleur.equals("jaune")) || (couleur.equals("vert"))) {
			this.couleur = couleur;
		} else {
			this.couleur = "blanc";
		}
	}

	public void changerCouleur() {
		if (super.isEtatCourant()) {
			System.out.println("Veuillez entrer la couleur choisie parmis les suivantes : blanc bleu rouge jaune vert");
			Scanner s = new Scanner(System.in);
			String requete = s.nextLine();
			setCouleur(requete);
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de couleur");
		}
	}

	public void augmenterIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() < 100) {
				intensite += 10;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas augmenter l'intensité");
		}
	}

	public void diminuerIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() > 0) {
				intensite -= 10;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas diminuer l'intensité");
		}
	}

	public void choisirIntensite(int intensite) {
		if (super.isEtatCourant()) {
			if (intensite <= 100 && intensite >= 0) {
				setIntensite(intensite);
			} else {
				System.out.println("Intensité non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer d'intensité");
		}
	}

}
