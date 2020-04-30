package equipements;

import main.Equipement;

public class Radiateur extends Equipement {

	private int thermostat;

	public Radiateur(String nom, boolean etatCourant, int thermostat) {
		super(etatCourant, nom);
		this.setThermostat(thermostat);
	}

	public int getThermostat() {
		return thermostat;
	}

	public void setThermostat(int thermostat) {
		this.thermostat = thermostat;
	}

	public void augmenterTemperature() {
		if (getThermostat() != 5 && etatCourant) {
			thermostat++;
		}
	}

	public void diminuerTemperature() {
		if (getThermostat() != 0 && etatCourant) {
			thermostat--;
		}
	}

	public void choisirThermostat(int thermostat) {
		if (etatCourant) {
			setThermostat(thermostat);
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
