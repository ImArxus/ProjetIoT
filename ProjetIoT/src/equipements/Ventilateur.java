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

public class Ventilateur extends Equipement implements Serializable {

	private double intensite;
	private static final long serialVersionUID = 8252594235507326423L;
	private static ProgressBar intensiteBar = new ProgressBar(0);

	public Ventilateur(String nom) {
		super(nom);
		this.setIntensite(3);
		getIntensiteBar().setLayoutX(120);
		getIntensiteBar().setLayoutY(500);
		getIntensiteBar().setPrefSize(80, 15);
	}

	public Ventilateur(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale,
			double intensite) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setIntensite(intensite);
		getIntensiteBar().setLayoutX(120);
		getIntensiteBar().setLayoutY(500);
		getIntensiteBar().setPrefSize(80, 15);
	}

	public double getIntensite() {
		return intensite;
	}

	public static ProgressBar getIntensiteBar() {
		return intensiteBar;
	}

	public void setIntensite(double intensite) {
		this.intensite = intensite;
	}

	public void augmenterIntensite(Pane root) {
		if (super.isEtatCourant()) {
			if (getIntensite() < 5) {
				setIntensite(getIntensite() + 1);
				getIntensiteBar().setProgress(getIntensite() / 5);
				try {
					root.getChildren().add(getIntensiteBar());
				} catch (Exception e) {
					System.err.println("Erreur lors de l'ajout de la barre d'intensité");
				}
			}
		}
	}

	public void diminuerIntensite(Pane root) {
		if (super.isEtatCourant()) {
			if (getIntensite() > 0) {
				setIntensite(getIntensite() - 1);
				getIntensiteBar().setProgress(getIntensite() / 5);
			}
			try {
				root.getChildren().add(getIntensiteBar());
			} catch (Exception e) {
				System.err.println("Erreur lors de l'ajout de la barre d'intensité");
			}
		}
	}

	public void choisirIntensite(int intensite) {
		if (super.isEtatCourant()) {
			if (intensite <= 5 && intensite >= 0) {
				setIntensite(intensite);
			} else {
				System.err.println("Intensité non-valide");
			}
		}
	}

	@Override
	public ImageView afficher() {
		getImageView().setImage(new Image(getImage()));
		getImageView().setTranslateY(170);
		getImageView().setTranslateX(-250);
		return getImageView();
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(600);
		but.setTranslateY(500);
		return but;
	}

	@Override
	public MenuButton getFonctionnalites(Pane root, ImageView img) {
		MenuButton fonctionnalite = super.getFonctionnalites(root, img);

		MenuItem augmenterIntensité = new MenuItem("Augmenter intensité");
		augmenterIntensité.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.getPosition()
						.setTemperature((int) (Main.getPosition().getTemperature() + (getIntensite() * 0.05)));
				augmenterIntensite(root);
				root.getChildren().remove(img);
				root.getChildren().add(afficher());
			}
		});
		MenuItem diminuerIntensité = new MenuItem("Diminuer l'intensité");
		diminuerIntensité.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.getPosition()
						.setTemperature((int) (Main.getPosition().getTemperature() + (getIntensite() * 0.05)));
				diminuerIntensite(root);
				root.getChildren().remove(img);
				root.getChildren().add(afficher());
			}
		});

		fonctionnalite.getItems().addAll(augmenterIntensité, diminuerIntensité);
		return fonctionnalite;
	}
}
