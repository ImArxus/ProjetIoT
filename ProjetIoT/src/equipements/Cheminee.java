package equipements;

import main.Equipement;

public class Cheminee extends Equipement {

	private int intensite;

	public Cheminee(String nom, boolean etatCourant, int intensite) {
		super(nom, etatCourant);
		this.setIntensite(intensite);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n-> Augmenter intensité\n-> Diminuer intensité\n-> Choisir intensité";
	}

	public double getIntensite() {
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
			System.out.println(this.getNom() + " est éteinte, on ne peut pas baisser l'intensite");
		}
	}

	public void choisirIntensite(int intensite) {
		if (super.isEtatCourant()) {
			if (intensite <= 100 && intensite >= 0) {
				setIntensite(intensite);
			} else {
				System.out.println("Intensite non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer l'intensité");
		}
	}

}
