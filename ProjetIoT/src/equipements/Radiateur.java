package equipements;

import main.Equipement;

public class Radiateur extends Equipement {

	private int thermostat;

	public Radiateur(String nom) {
		super(nom);
		setThermostat(1);
		this.setPositionHorizontale(0.8);
		this.setPositionVerticale(0.32);
	}

	public Radiateur(String nom, boolean etatCourant,double positionHorizontale,double positionVerticale, int thermostat) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setThermostat(thermostat);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n➡️ 4 : Augmenter température\n➡️ 5 : Diminuer température\n➡️ 6 : Choisir thermostat";
	}

	public int getThermostat() {
		return thermostat;
	}

	public void setThermostat(int thermostat) {
		this.thermostat = thermostat;
	}

	public void augmenterTemperature() {
		if (super.isEtatCourant()) {
			if (getThermostat() < 5) {
				thermostat++;
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas changer le thermostat");
		}
	}

	public void diminuerTemperature() {
		if (super.isEtatCourant()) {
			if (getThermostat() > 0) {
				thermostat--;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public void choisirThermostat(int thermostat) {
		if (super.isEtatCourant()) {
			if (thermostat <= 5 && thermostat >= 0) {
				setThermostat(thermostat);
			}else {
				System.out.println("ThermostatF non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas changer le thermostat");
		}
	}

}
