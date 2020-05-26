package equipements;

import java.io.Serializable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.Equipement;
import main.Main;

public class Cheminee extends Equipement implements Serializable {

	private static final long serialVersionUID = 7169763608942128813L;
	private double intensite;
	private static ProgressBar intensiteBar = new ProgressBar(0);

	public Cheminee(String nom, boolean etatCourant, double intensite, double positionHorizontale,
			double positionVerticale) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setIntensite(intensite);
		getIntensiteBar().setLayoutX(170);
		getIntensiteBar().setLayoutY(350);
		getIntensiteBar().setPrefSize(80, 15);
	}

	public Cheminee(String nom) {
		super(nom);
		this.setIntensite(50);
		getIntensiteBar().setLayoutX(170);
		getIntensiteBar().setLayoutY(350);
		getIntensiteBar().setPrefSize(80, 15);
	}

	public static ProgressBar getIntensiteBar() {
		return intensiteBar;
	}

	public double getIntensite() {
		return intensite;
	}

	public void setIntensite(double intensite) {
		if (intensite <= 100 && intensite >= 0) {
			this.intensite = intensite;
		}
	}

	public void choisirIntensite(double intensite) {
		if (super.isEtatCourant()) {
			if (intensite <= 100 && intensite >= 0) {
				setIntensite(intensite);
				Main.getPosition().setTemperature((int) (Main.getPosition().getTemperature() + (intensite * 0.05)));
			} else {
				System.err.println("Intensité non-valide");
			}
		}
	}

	public void augmenterIntensite(Pane root) {
		if (super.isEtatCourant()) {
			if (getIntensite() <= 90) {
				setIntensite(getIntensite() + 10);
				getIntensiteBar().setProgress(getIntensite() / 100);
				try {
					root.getChildren().add(getIntensiteBar());
				} catch (Exception e) {
				}
			}
		}
	}

	public void diminuerIntensite(Pane root) {
		if (super.isEtatCourant()) {
			if (getIntensite() > 10) {
				setIntensite(getIntensite() - 10);
				getIntensiteBar().setProgress(getIntensite() / 100);
			}
			try {
				root.getChildren().add(getIntensiteBar());
			} catch (Exception e) {
			}
		}
	}

	@Override
	public ImageView afficher() {
		getImageView().setImage(new Image(getImage()));
		getImageView().setTranslateY(105);
		getImageView().setTranslateX(-180);
		return getImageView();
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(400);
		but.setTranslateY(450);
		return but;
	}

	@Override
	public MenuButton getFonctionnalites(Pane root, ImageView img) {
		MenuButton fonctionnalite = super.getFonctionnalites(root, img);

		if (isEtatCourant()) {
			MenuItem augmenterIntensité = new MenuItem("Augmenter intensité");
			augmenterIntensité.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					augmenterIntensite(root);
					Lumiere.boxIntensite(root);
					Radiateur.boxTemperature(root);
				}
			});
			MenuItem diminuerIntensité = new MenuItem("Diminuer l'intensité");
			diminuerIntensité.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					diminuerIntensite(root);
					Lumiere.boxIntensite(root);
					Radiateur.boxTemperature(root);
				}
			});
			fonctionnalite.getItems().addAll(augmenterIntensité, diminuerIntensité);
		}
		return fonctionnalite;
	}

}
