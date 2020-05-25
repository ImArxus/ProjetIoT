package equipements;

import java.io.Serializable;
import java.util.Scanner;

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
	private static ProgressBar volumeBar = new ProgressBar(0);
	ImageView imageView = new ImageView();

	public Cheminee(String nom, boolean etatCourant, double intensite, double positionHorizontale,
			double positionVerticale) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setIntensite(intensite);
		volumeBar.setLayoutX(170);
		volumeBar.setLayoutY(350);
		volumeBar.setPrefSize(80, 15);
	}

	public Cheminee(String nom) {
		super(nom);
		this.setIntensite(intensite);
		this.setPositionHorizontale(0.26);
		this.setPositionVerticale(0.34);
		volumeBar.setLayoutX(170);
		volumeBar.setLayoutY(350);
		volumeBar.setPrefSize(80, 15);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Augmenter intensité\n➡️ 5 : Diminuer intensité\n➡️ 6 : Choisir intensité";
	}

	public static ProgressBar getVolumeBar() {
		return volumeBar;
	}

	public double getIntensite() {
		return intensite;
	}

	public void setIntensite(double intensite) {
		this.intensite = intensite;
	}

	public void augmenterIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() < 100) {
				intensite += 10;
				Main.getPosition().setTemperature((int) (Main.getPosition().getTemperature() + (intensite * 0.05)));
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas augmenter l'intensité");
		}
	}

	public void diminuerIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() > 0) {
				intensite -= 10;
				Main.getPosition().setTemperature((int) (Main.getPosition().getTemperature() + (intensite * 0.05)));
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas baisser l'intensite");
		}
	}

	public void choisirIntensite(double intensite2) {
		if (super.isEtatCourant()) {
			if (intensite2 <= 100 && intensite2 >= 0) {
				setIntensite(intensite2);
				Main.getPosition().setTemperature((int) (Main.getPosition().getTemperature() + (intensite2 * 0.05)));
			} else {
				System.out.println("Intensite non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer l'intensité");
		}
	}

	public void augmenterIntensiteFX(Pane root) {
		if (super.isEtatCourant()) {
			if (getIntensite() <= 90) {
				intensite += 10;
				volumeBar.setProgress(getIntensite() / 100);
				try {
					root.getChildren().add(volumeBar);
				} catch (Exception e) {
				}
			}
		}
	}

	public void diminuerIntensiteFX(Pane root) {
		if (super.isEtatCourant()) {
			if (getIntensite() > 10) {
				intensite -= 10;
				volumeBar.setProgress(getIntensite() / 100);
			}
			try {
				root.getChildren().add(volumeBar);
			} catch (Exception e) {
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public ImageView afficher() {
		imageView.setImage(new Image(getImage()));
		imageView.setTranslateY(105);
		imageView.setTranslateX(-180);
		return imageView;
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(400);
		but.setTranslateY(450);
		return but;
	}

	@Override
	public MenuButton getFonctionnalitées(Pane root, ImageView img) {
		MenuButton fonctionnalite = super.getFonctionnalitées(root, img);

		MenuItem augmenterIntensité = new MenuItem(" Augmenter intensité");
		augmenterIntensité.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				intensite = (int) getIntensite();
				Main.getPosition().setTemperature((int) (Main.getPosition().getTemperature() + (intensite * 0.05)));
				augmenterIntensiteFX(root);
				System.out.println("L'intensité de " + getNom() + " est réglé sur " + getIntensite());
			}
		});
		MenuItem diminuerIntensité = new MenuItem(" Diminuer l'intensité");
		diminuerIntensité.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				intensite = (int) getIntensite();
				Main.getPosition().setTemperature((int) (Main.getPosition().getTemperature() + (intensite * 0.05)));
				diminuerIntensiteFX(root);
				System.out.println("L'intensité de " + getNom() + " est réglé sur " + getIntensite());
			}
		});
		MenuItem choisirIntensité = new MenuItem(" Choisir intensité");
		choisirIntensité.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Scanner s = new Scanner(System.in);
				System.out.println("Quelle intensité (entre 0 et 100) ?");
				intensite = Main.toInt(s.nextLine());
				Main.getPosition().setTemperature((int) (Main.getPosition().getTemperature() + (intensite * 0.05)));
				choisirIntensite(intensite);
				System.out.println("L'intensité de " + getNom() + " est réglé sur " + getIntensite());
				s.close();
			}
		});
		fonctionnalite.getItems().addAll(augmenterIntensité, diminuerIntensité, choisirIntensité);
		return fonctionnalite;
	}

	@Override
	public String getImage() {
		if (etatCourant) {
			return ("/images/objets/equipements.Cheminee.png");
		} else {
			return ("/images/objets/equipements.Cheminee.desactive.png");
		}
	}
}
