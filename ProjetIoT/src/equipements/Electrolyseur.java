package equipements;

import java.io.Serializable;

import javafx.scene.control.Button;
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
	}

	public Electrolyseur(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale,
			double sel, double ph, double temperature) {
		super(nom, etatCourant, positionHorizontale, positionVerticale);
		setSel(sel);
		setPh(ph);
		setTemperature(temperature);
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
			if (getTemperature() < 40) {
				setTemperature(getTemperature() + 1);
			}
		}
	}

	public void diminuerTemperature() {
		if (super.isEtatCourant()) {
			if (getTemperature() > 0) {
				setTemperature(getTemperature() - 1);
			}
		}
	}

	public void choisirTemperature(int temperature) {
		if (super.isEtatCourant()) {
			if (temperature <= 100 && temperature >= 0) {
				setTemperature(temperature);
			} else {
				System.err.println("Température non-valide");
			}
		}
	}

	@Override
	public String getImage() {
		return "/images/objets/equipements.Electrolyseur.png";
	}

	@Override
	public ImageView afficher() {
		getImageView().setImage(new Image(getImage()));
		getImageView().setTranslateY(105);
		getImageView().setTranslateX(300);
		return getImageView();
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(500);
		but.setTranslateY(450);
		return but;
	}

}
