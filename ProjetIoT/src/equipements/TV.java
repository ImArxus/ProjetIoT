package equipements;

import main.Equipement;

public class TV extends Equipement {

	private int volume;
	private int numeroChaine;

	public TV(String nom, boolean etatCourant, int volume, int numeroChaine) {
		super(etatCourant, nom);
		this.volume = volume;
		this.numeroChaine=numeroChaine;
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

}
