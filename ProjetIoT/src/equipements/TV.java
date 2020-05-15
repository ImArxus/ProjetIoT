package equipements;

import java.io.Serializable;

import main.Equipement;

public class TV extends Equipement implements Serializable {

	private static final long serialVersionUID = 5394573896230563021L;
	private int volume;
	private int numeroChaine;

	public TV(String nom) {
		super(nom);
		setVolume(50);
		setNumeroChaine(1);
		this.setPositionHorizontale(0.5);
		this.setPositionVerticale(0.385);
	}

	public TV(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale, int volume,
			int numeroChaine) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setVolume(volume);
		this.numeroChaine = numeroChaine;
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Augmenter volume\n➡️ 5 : Diminuer volume\n➡️ 6 : Augmenter chaine\n➡️ 7 : Diminuer chaine\n➡️ 8 : Mettre chaine";
	}

	public int getNumeroChaine() {
		return numeroChaine;
	}

	public void setNumeroChaine(int numeroChaine) {
		this.numeroChaine = numeroChaine;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public void augmenterVolume() {
		if (super.isEtatCourant()) {
			if (getVolume() < 100) {
				volume++;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public void diminuerVolume() {
		if (super.isEtatCourant()) {
			if (getVolume() > 0) {
				volume--;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public void augmenterChaine() {
		if (super.isEtatCourant()) {
			if (getNumeroChaine() < 100) {

				numeroChaine++;
			} else {
				numeroChaine = 1;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public void diminuerChaine() {
		if (super.isEtatCourant()) {
			if (getNumeroChaine() > 1) {
				numeroChaine--;
			} else {
				numeroChaine = 100;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public void mettreChaine(int chaine) {
		if (super.isEtatCourant()) {
			if (chaine <= 100 && chaine >= 0) {
				setNumeroChaine(chaine);
			} else {
				System.out.println("Chaine non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

}
