package equipements;

import main.Equipement;

public class TV extends Equipement {

	private int volume;
	private int numeroChaine;

	public TV(String nom) {
		super(nom, false);
		setVolume(50);
		setNumeroChaine(1);
	}

	public TV(String nom, boolean etatCourant, int volume, int numeroChaine) {
		super(nom, etatCourant);
		this.setVolume(volume);
		this.numeroChaine = numeroChaine;
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n-> Augmenter volume\n-> Diminuer volume\n-> Augmenter numéro chaine\n-> Diminuer numéro chaine\n-> Mettre chaine";
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
