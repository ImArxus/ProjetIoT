package equipements;

import main.Equipement;

public class Electrolyseur extends Equipement {

	private double sel;
	private double ph;
	private double temperature;

	public Electrolyseur(String nom) {
		super(nom, false);
		setSel(3800);
		setPh(7.2);
		setTemperature(25);
	}

	public Electrolyseur(String nom, boolean etatCourant, double sel, double ph, double temperature) {
		super(nom, etatCourant);
		setSel(sel);
		setPh(ph);
		setTemperature(temperature);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Augmenter température\n➡️ 5 : Diminuer température\n➡️ 6 : Choisir température ";
	}

	public double getPh() {
		return ph;
	}

	public void setPh(double ph) {
		this.ph = ph;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getSel() {
		return sel;
	}

	public void setSel(double sel) {
		this.sel = sel;
	}

	public void augmenterTemperature() {
		if (super.isEtatCourant()) {
			if (getTemperature() < 100) {
				temperature++;
			}
		} else {
			System.out.println(
					"L'électrolyseur de " + this.getNom() + " est éteint, on ne peut pas changer de température");
		}
	}

	public void diminuerTemperature() {
		if (super.isEtatCourant()) {
			if (getTemperature() > 0) {
				temperature--;
			}
		} else {
			System.out.println(
					"L'électrolyseur de " + this.getNom() + " est éteint, on ne peut pas changer de température");
		}
	}

	public void choisirTemperature(int temperature) {
		if (super.isEtatCourant()) {
			if (temperature <= 100 && temperature >= 0) {
				setTemperature(temperature);
			} else {
				System.out.println("Température non-valide");
			}
		} else {
			System.out.println(
					"L'électrolyseur de " + this.getNom() + " est éteint, on ne peut pas changer de température");
		}
	}

}
