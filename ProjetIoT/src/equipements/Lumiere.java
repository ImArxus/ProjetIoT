package equipements;

import main.Equipement;

public class Lumiere extends Equipement {

	private int intensite;

	public Lumiere(String nom) {
		super(nom, false);
		setIntensite(100);
	}

	public Lumiere(String nom, boolean etatCourant, int intensite) {
		super(nom, etatCourant);
		setIntensite(intensite);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n-> Augmenter intensité\n-> Diminuer intensité\n-> Choisir intensité";
	}

	public int getIntensite() {
		return intensite;
	}

	public void setIntensite(int intensite) {
		this.intensite = intensite;
	}
	
	public void augmenterIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() < 100) {
				intensite+=10;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas augmenter l'intensité");
		}
	}

	public void diminuerIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() > 0) {
				intensite-=10;
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
