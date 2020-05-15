package equipements;

import java.io.Serializable;

import main.Equipement;

public class Radiateur extends Equipement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4663697483416985328L;
	private int thermostat;

	public Radiateur(String nom) {
		super(nom, false);
		setThermostat(1);
	}

	public Radiateur(String nom, boolean etatCourant, int thermostat) {
		super(nom, etatCourant);
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

	public static void main(String[] args) {
		Radiateur a = new Radiateur("Radiateur1", false, 2);
		a.allumer();
		System.out.println(a.thermostat);
		a.augmenterTemperature();
		System.out.println(a.thermostat);
	}
}
