package main;

import java.io.Serializable;
import java.util.LinkedList;

import equipements.Cheminee;
import equipements.Enceinte;
import equipements.Frigo;
import equipements.Lumiere;
import equipements.Radiateur;
import equipements.TV;
import equipements.Ventilateur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Equipement implements Serializable {

	private static final long serialVersionUID = 3L;
	private boolean etatCourant;
	private String nom;

	public Equipement(String nom) {
		setNom(nom);
		setEtatCourant(false);
	}

	public Equipement(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale) {
		this.setNom(nom);
		this.setEtatCourant(etatCourant);
	}

	public String toString() {
		String etat = "éteint(e)";
		if (isEtatCourant()) {
			etat = "allumé(e)";
		}
		return (getClass().getSimpleName() + " (" + getNom() + ", " + etat + ")");
	}

	public void setEtatCourant(boolean etatCourant) {
		this.etatCourant = etatCourant;
	}

	public boolean isEtatCourant() {
		return etatCourant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void allumer() {
		if (!isEtatCourant()) {
			setEtatCourant(true);
		}
	}

	public void eteindre() {
		if (isEtatCourant()) {
			setEtatCourant(false);
		}
	}

	public String getImage() {
		if (isEtatCourant()) {
			return "/images/objets/" + this.getClass().getName() + ".png";
		} else {
			return "/images/objets/" + this.getClass().getName() + ".desactive.png";
		}
	}

	public ImageView getImageView() {
		return EquipementImageView.getImageView();
	}

	public ImageView afficher() {
		getImageView().setImage(new Image(getImage()));
		return getImageView();
	}

	public Button getButton() {
		Button but = new Button();
		but.setText(getNom());
		return but;
	}

	public MenuButton getFonctionnalites(Pane root, ImageView img) {
		MenuButton fonctionnalite = new MenuButton("Fonctionnalités");
		fonctionnalite.setPrefSize(220, 30);
		fonctionnalite.setLayoutX(570);
		fonctionnalite.setLayoutY(100);

		MenuItem allumer = new MenuItem("Allumer");
		allumer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				allumer();
				root.getChildren().remove(img);
				root.getChildren().add(afficher());
				Lumiere.boxIntensite(root);
				Radiateur.boxTemperature(root);
				root.getChildren().remove(fonctionnalite);
				root.getChildren().add(getFonctionnalites(root, img));
			}
		});
		MenuItem eteindre = new MenuItem("Éteindre");
		eteindre.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				eteindre();
				root.getChildren().remove(img);
				root.getChildren().removeAll(indicateurs());
				root.getChildren().add(afficher());
				Lumiere.boxIntensite(root);
				Radiateur.boxTemperature(root);
				root.getChildren().remove(fonctionnalite);
				root.getChildren().add(getFonctionnalites(root, img));
			}
		});
		MenuItem quitter = new MenuItem("Quitter");
		quitter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				root.getChildren().remove(fonctionnalite);
				root.getChildren().removeAll(indicateurs());
			}
		});

		fonctionnalite.getItems().addAll(allumer, eteindre, quitter);
		return fonctionnalite;
	}

	public LinkedList<Object> indicateurs() {
		LinkedList<Object> liste = new LinkedList<Object>();
		if (this instanceof TV) {
			liste.add(TV.getVolumeBar());
		} else if (this instanceof Enceinte) {
			liste.add(Enceinte.getVolumeBar());
		} else if (this instanceof Cheminee) {
			liste.add(Cheminee.getIntensiteBar());
		} else if (this instanceof Frigo) {
			liste.add(Frigo.getTempBar());
			liste.add(Frigo.getListeDispo());
		} else if (this instanceof Ventilateur) {
			liste.add(Ventilateur.getIntensiteBar());
		}
		return liste;
	}
}
