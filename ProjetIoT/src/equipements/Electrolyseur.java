package equipements;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Equipement;

public class Electrolyseur extends Equipement implements Serializable {

	private static final long serialVersionUID = -3214694664282896820L;
	private double sel;
	private double ph;
	private double temperature;

	public Electrolyseur(String nom) {
		super(nom);
		setSel(3800);
		setPh(7.2);
		setTemperature(25);
		this.setPositionHorizontale(0.9);
		this.setPositionVerticale(0.34);
	}

	public Electrolyseur(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale,
			double sel, double ph, double temperature) {
		super(nom, etatCourant, positionHorizontale, positionVerticale);
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
	public ImageView getImageView() {
		ImageView i0 = new ImageView();
		i0.setImage(new Image("/images/objets/equipements.Electrolyseur.png"));
		return i0;
	}
}
