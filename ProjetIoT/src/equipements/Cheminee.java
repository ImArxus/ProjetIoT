package equipements;

import java.io.Serializable;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.Equipement;
import main.Main;

public class Cheminee extends Equipement implements Serializable {

	private static final long serialVersionUID = 7169763608942128813L;
	private int intensite;

	public Cheminee(String nom, boolean etatCourant, int intensite, double positionHorizontale,
			double positionVerticale) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setIntensite(intensite);
	}

	public Cheminee(String nom) {
		super(nom);
		this.setIntensite(intensite);
		this.setPositionHorizontale(0.26);
		this.setPositionVerticale(0.34);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Augmenter intensité\n➡️ 5 : Diminuer intensité\n➡️ 6 : Choisir intensité";
	}

	public double getIntensite() {
		return intensite;
	}

	public void setIntensite(int intensite) {
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

	public void choisirIntensite(int intensite) {
		if (super.isEtatCourant()) {
			if (intensite <= 100 && intensite >= 0) {
				setIntensite(intensite);
				Main.getPosition().setTemperature((int) (Main.getPosition().getTemperature() + (intensite * 0.05)));
			} else {
				System.out.println("Intensite non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer l'intensité");
		}
	}

	public ImageView afficher() {
		ImageView imageView = new ImageView();
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
				augmenterIntensite();
				System.out.println("L'intensité de " + getNom() + " est réglé sur " + getIntensite());
			}
		});
		MenuItem diminuerIntensité = new MenuItem(" Diminuer l'intensité");
		diminuerIntensité.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				intensite = (int) getIntensite();
				Main.getPosition().setTemperature((int) (Main.getPosition().getTemperature() + (intensite * 0.05)));
				diminuerIntensite();
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
