package equipements;

import java.io.Serializable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Box;
import main.Equipement;
import main.Main;

public class Radiateur extends Equipement implements Serializable {

	private static final long serialVersionUID = -4663697483416985328L;
	private int thermostat;

	public Radiateur(String nom) {
		super(nom);
		setThermostat(1);
		super.setEtatCourant(false);
	}

	public Radiateur(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale,
			int thermostat) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setThermostat(thermostat);
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
				setThermostat(getThermostat() + 1);
			}
		}
	}

	public void diminuerTemperature() {
		if (super.isEtatCourant()) {
			if (getThermostat() > 0) {
				setThermostat(getThermostat() - 1);
			}
		}
	}

	public void choisirThermostat(int thermostat) {
		if (super.isEtatCourant()) {
			if (thermostat <= 5 && thermostat >= 0) {
				setThermostat(thermostat);
			} else {
				System.err.println("Thermostat non-valide");
			}
		}
	}

	@Override
	public String getImage() {
		return "/images/objets/equipements.Radiateur.png";
	}

	@Override
	public ImageView afficher() {
		getImageView().setImage(new Image(getImage()));
		getImageView().setTranslateY(110);
		getImageView().setTranslateX(240);
		return getImageView();
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(300);
		but.setTranslateY(500);
		return but;
	}

	public static void boxTemperature(Pane root) {
		Box box = new Box(100, 22, 0);
		box.setLayoutX(190);
		box.setLayoutY(80);
		Label lblTemp = new Label();
		lblTemp.setText("" + Main.getPosition().getTemperature() + "°C");
		lblTemp.setStyle("-fx-font: 20 arial; -fx-font-weight: bold;");
		lblTemp.setLayoutX(148);
		lblTemp.setLayoutY(68);

		root.getChildren().add(box);
		root.getChildren().add(lblTemp);
	}

	@Override
	public MenuButton getFonctionnalites(Pane root, ImageView img) {
		MenuButton fonctionnalite = super.getFonctionnalites(root, img);

		if (isEtatCourant()) {
			MenuItem augmenterTemperature = new MenuItem("Augmenter temperature");
			augmenterTemperature.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					augmenterTemperature();
					boxTemperature(root);
				}
			});
			MenuItem diminuerTemperature = new MenuItem("Diminuer temperature");
			diminuerTemperature.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					diminuerTemperature();
					boxTemperature(root);
				}
			});

			fonctionnalite.getItems().addAll(augmenterTemperature, diminuerTemperature);
		}
		return fonctionnalite;
	}

}
