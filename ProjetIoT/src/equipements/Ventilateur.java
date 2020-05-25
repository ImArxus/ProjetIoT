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
	private static ProgressBar volumeBar = new ProgressBar(0);

	public Ventilateur(String nom) {
		super(nom);
		this.setIntensite(3);
		this.setPositionHorizontale(0.2);
		this.setPositionVerticale(0.21);
		volumeBar.setLayoutX(120);
		volumeBar.setLayoutY(500);
		volumeBar.setPrefSize(80, 15);
	}

	public Ventilateur(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale,
			double intensite) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setIntensite(intensite);
		volumeBar.setLayoutX(120);
		volumeBar.setLayoutY(500);
		volumeBar.setPrefSize(80, 15);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Augmenter intensité\n➡️ 5 : Diminuer intensité\n➡️ 6 : Choisir intensité ";
	}

	public double getIntensite() {
		return intensite;
	}

	public void setIntensite(double intensite2) {
		this.intensite = intensite2;
	}

	public void augmenterIntensiteFX(Pane root) {
		if (super.isEtatCourant()) {
			if (getIntensite() < 5) {
				intensite += 1;
				volumeBar.setProgress(getIntensite() / 5);
				try {
					root.getChildren().add(volumeBar);
				} catch (Exception e) {
				}
			}
		}
	}

	public void diminuerIntensiteFX(Pane root) {
		if (super.isEtatCourant()) {
			if (getIntensite() > 0) {
				intensite -= 1;
				volumeBar.setProgress(getIntensite() / 5);
			}
			try {
				root.getChildren().add(volumeBar);
			} catch (Exception e) {
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public void augmenterIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() < 5) {
				intensite++;
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas augmenter l'intensité");
		}
	}

	public void diminuerIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() > 0) {
				intensite--;
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas diminuer l'intensite");
		}
	}

	public void choisirIntensite(int intensite) {
		if (super.isEtatCourant()) {
			if (intensite <= 5 && intensite >= 0) {
				setIntensite(intensite);
			} else {
				System.out.println("Intensité non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas changer l'intensité");
		}
	}

	public ImageView afficher() {
		ImageView imageView = new ImageView();
		imageView.setImage(new Image(getImage()));
		imageView.setTranslateY(170);
		imageView.setTranslateX(-250);
		return imageView;
	}

	public ImageView getImageView() {
		ImageView i0 = new ImageView();
		i0.setImage(new Image("/images/objets/equipements.Ventilateur.png"));
		return i0;
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(600);
		but.setTranslateY(500);
		return but;
	}
	@Override
	public String getImage() {
		if (etatCourant) {
			return ("/images/objets/equipements.Ventilateur.png");
		} else {
			return ("/images/objets/equipements.Ventilateur.desactive.png");
		}}

	
	@Override
	public MenuButton getFonctionnalitées(Pane root, ImageView img) {
		MenuButton fonctionnalite = super.getFonctionnalitées(root, img);

		MenuItem augmenterIntensité = new MenuItem("Augmenter intensité");
		augmenterIntensité.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				intensite = (int) getIntensite();
				Main.getPosition().setTemperature((int) (Main.getPosition().getTemperature() + (intensite * 0.05)));
				augmenterIntensiteFX(root);
				System.out.println("L'intensité de " + getNom() + " est réglé sur " + getIntensite());
				root.getChildren().remove(img);
				root.getChildren().add(afficher());			}
		});
		MenuItem diminuerIntensité = new MenuItem("Diminuer l'intensité");
		diminuerIntensité.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				intensite = (int) getIntensite();
				Main.getPosition().setTemperature((int) (Main.getPosition().getTemperature() + (intensite * 0.05)));
				diminuerIntensiteFX(root);
				System.out.println("L'intensité de " + getNom() + " est réglé sur " + getIntensite());
				root.getChildren().remove(img);
				root.getChildren().add(afficher());
			}
		});
	
		fonctionnalite.getItems().addAll(augmenterIntensité, diminuerIntensité);
		return fonctionnalite;
	}
}
