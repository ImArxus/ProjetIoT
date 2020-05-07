package equipements;

import main.Equipement;

public class Thermostat extends Equipement {

	private int temperature;

	public Thermostat(String nom) {
		super(nom, false);
		this.setTemperature(21);
	}

	public Thermostat(String nom, boolean etatCourant, int temperature) {
		super(nom, etatCourant);
		this.setTemperature(temperature);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Augmenter température\n➡️ 5 : Diminuer température\n➡️ 6 : Choisir température ";
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public void augmenterTemperature() {
		if (super.isEtatCourant()) {
			if (getTemperature() < 30) {
				temperature++;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas augmenter la température");
		}
	}

	public void diminuerTemperature() {
		if (super.isEtatCourant()) {
			if (getTemperature() > 15) {
				temperature--;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas diminuer la température");
		}
	}

	public void choisirTemperature(int temperature) {
		if (super.isEtatCourant()) {
			if (temperature <= 30 && temperature >= 15) {
				setTemperature(temperature);
			} else {
				System.out.println("Température non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer la température");
		}
	}

}
