package equipements;

import main.Equipement;

public class TV extends Equipement {

	private int volume;
	private int numeroChaine;

	public TV(String nom, boolean etatCourant, int volume, int numeroChaine) {
		super(etatCourant, nom);
		this.setVolume(volume);
		this.numeroChaine = numeroChaine;
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
		if (getVolume() != 100 && etatCourant) {
			volume++;
		}
	}

	public void diminuerVolume() {
		if (getVolume() != 0 && etatCourant) {
			volume--;
		}
	}

	public void augmenterNuméroChaine() {
		if (etatCourant) {
			if (getNumeroChaine() != 100) {
				numeroChaine++;
			} else {
				numeroChaine = 1;
			}
		}
	}

	public void diminuerNuméroChaine() {
		if (etatCourant) {
			if (getNumeroChaine() != 100) {
				numeroChaine++;
			} else {
				numeroChaine = 1;
			}
		}
	}

	public void mettreChaine(int chaine) {
		if (etatCourant == true) {
			setNumeroChaine(chaine);
		}
	}

	public static void main(String[] args) {
		TV a = new TV("tele", false, 98, 98);
		a.allumer();
		a.augmenterNuméroChaine();
		a.augmenterNuméroChaine();
		a.augmenterNuméroChaine();
		System.out.println(a.getNumeroChaine());
		a.mettreChaine(10);
		System.out.println(a.getNumeroChaine());
		a.eteindre();
		a.augmenterVolume();
		System.out.println(a.getVolume());
	}
}
