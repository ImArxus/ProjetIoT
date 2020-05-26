package equipements;

import java.io.Serializable;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Equipement;

public class Thermostat extends Equipement implements Serializable {

	private static final long serialVersionUID = 6630244045511449074L;
	private int temperature;

	public Thermostat(String nom) {
		super(nom);
		this.setTemperature(21);
	}

	public Thermostat(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale,
			int temperature) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setTemperature(temperature);
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
				setTemperature(getTemperature() + 1);
			}
		}
	}

	public void diminuerTemperature() {
		if (super.isEtatCourant()) {
			if (getTemperature() > 15) {
				setTemperature(getTemperature() - 1);
			}
		}
	}

	public void choisirTemperature(int temperature) {
		if (super.isEtatCourant()) {
			if (temperature <= 30 && temperature >= 15) {
				setTemperature(temperature);
			} else {
				System.err.println("Température non-valide");
			}
		}
	}

	@Override
	public String getImage() {
		return "/images/objets/equipements.Thermostat.png";
	}

	@Override
	public ImageView afficher() {
		getImageView().setImage(new Image(getImage()));
		getImageView().setTranslateY(10);
		getImageView().setTranslateX(200);
		return getImageView();
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(400);
		but.setTranslateY(500);
		return but;
	}

}
