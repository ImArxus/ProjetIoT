package equipements;

import java.io.Serializable;

import main.Equipement;

public class Ventilateur extends Equipement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8252594235507326423L;
	private int intensite;

	public Ventilateur(String nom) {
		super(nom);
		this.setIntensite(3);
	}

	public Ventilateur(String nom, boolean etatCourant, double positionHorizontale,double positionVerticale,int intensite) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setIntensite(intensite);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Augmenter intensité\n➡️ 5 : Diminuer intensité\n➡️ 6 : Choisir intensité ";
	}

	public int getIntensite() {
		return intensite;
	}

	public void setIntensite(int intensite) {
		this.intensite = intensite;
	}

	public void augmenterIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() < 5) {
				intensite++;
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas augmenter l'intensité");
		}
	}

	public void diminuerIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() > 0) {
				intensite--;
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas diminuer l'intensite");
		}
	}

	public void choisirIntensite(int intensite) {
		if (super.isEtatCourant()) {
			if (intensite <= 5 && intensite >= 0) {
				setIntensite(intensite);
			} else {
				System.out.println("Intensité non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas changer l'intensité");
		}
	}

}
