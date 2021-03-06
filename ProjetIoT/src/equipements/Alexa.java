package equipements;

import java.io.Serializable;
import java.util.List;
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
import main.Piece;

public class Alexa extends Equipement implements Serializable {

	private static final long serialVersionUID = 8308711409297886419L;
	private static String pseudo;

	public Alexa(String nom) {
		super(nom);
		pseudo = Main.getPseudo();
	}

	public Alexa(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale) {
		super(nom, etatCourant, positionHorizontale, positionVerticale);
		pseudo = Main.getPseudo();
	}

	public void reponseHeure() {
		if (super.isEtatCourant()) {
			System.out.println(" Bonjour " + pseudo + ", il est actuellement " + Main.getHeure() + "h!");
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas l'utiliser");
		}
	}

	public void reponseTemperature() {
		Scanner s = new Scanner(System.in); // Ouverture du scanner
		if (super.isEtatCourant()) {
			System.out.println(" Bonjour " + pseudo + ", de quelle pièce s'agit-il?");
			List<Piece> pieces = Main.getMaison().getPieces();
			for (int i = 0; i < pieces.size(); i++) {
				System.out.println("➡️ " + (i + 1) + " : " + pieces.get(i).getNom()); // Affiche la liste des pièces
			}
			int req = s.nextInt() - 1;
			if (req >= 0 && req < pieces.size()) {
				System.out.println(" Bonjour " + pseudo + ", la temperature de la pièce " + pieces.get(req).getNom()
						+ " est de " + pieces.get(req).getTemperature() + "°C");
			} else {
				System.out.println("Mauvaise commande");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas l'utiliser");
		}
		s.close();
	}

	public void reponseEquipement() {
		Scanner s = new Scanner(System.in); // Ouverture du scanner
		if (super.isEtatCourant()) {
			System.out.println(" Bonjour " + pseudo + ", de quelle pièce s'agit-il?");
			List<Piece> pieces = Main.getMaison().getPieces();
			for (int i = 0; i < pieces.size(); i++) {
				System.out.println("➡️ " + (i + 1) + " : " + pieces.get(i).getNom()); // Affiche la liste des pièces
			}
			int req = s.nextInt() - 1;
			if (req >= 0 && req < pieces.size()) {
				System.out.println(" Bonjour " + pseudo + ", les équipements présent dans " + pieces.get(req).getNom()
						+ " sont " + pieces.get(req).getEquipements());
			} else {
				System.out.println("Mauvaise commande");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas l'utiliser");
		}
		s.close();
	}

	@Override
	public ImageView afficher() {
		getImageView().setImage(new Image(getImage()));
		getImageView().setTranslateY(25);
		getImageView().setTranslateX(-200);
		return getImageView();
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(200);
		but.setTranslateY(450);
		return but;
	}

	@Override
	public MenuButton getFonctionnalites(Pane root, ImageView img) {
		MenuButton fonctionnalite = super.getFonctionnalites(root, img);

		MenuItem reponseHeure = new MenuItem(" Connaitre l'heure");
		reponseHeure.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				reponseHeure();
			}
		});
		MenuItem reponseTemperature = new MenuItem(" Connaitre la température");
		reponseTemperature.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				reponseTemperature();
			}
		});
		MenuItem reponseEquipement = new MenuItem(" Connaitre les équipements");
		reponseEquipement.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				reponseEquipement();
			}
		});

		fonctionnalite.getItems().addAll(reponseHeure, reponseTemperature, reponseEquipement);
		return fonctionnalite;
	}

}