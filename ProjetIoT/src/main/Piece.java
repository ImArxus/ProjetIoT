package main;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import equipements.Alarme;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Piece implements Serializable {

	private static final long serialVersionUID = 2L;
	private String nom;
	private double temperature;
	private int intensiteLumineuse; // Intensité totale
	private LinkedList<Piece> piecesAdj = new LinkedList<Piece>();
	private LinkedList<Equipement> equipements = new LinkedList<Equipement>();

	public Piece(String nom) {
		this.setNom(nom);
		this.temperature = 21;
		this.intensiteLumineuse = 50;
	}

	public Piece(String nom, LinkedList<Equipement> equipements) {
		this.setNom(nom);
		this.setEquipements(equipements);
		this.temperature = 21;
		this.intensiteLumineuse = 50;
	}

	public Piece(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
		this.setNom(nom);
		this.setPiecesAdj(piecesAdj);
		this.setEquipements(equipements);
		this.temperature = 21;
		this.intensiteLumineuse = 0;
	}

	public void ajouterEquipement(Equipement equip) {
		getEquipements().add(equip);
	}

	public void supprimerEquipement(Equipement equip) {
		getEquipements().remove(equip);
	}

	public void ajouterPieceAdj(Piece piece) {
		getPiecesAdj().add(piece);
	}

	public void supprimerPieceAdj(Piece piece) {
		getPiecesAdj().remove(piece);
	}

	public String toString() {
		if (getEquipements().isEmpty()) {
			return getNom() + " qui est sans équipement...";
		}
		return getClass().getSimpleName() + " (" + getNom() + ") qui est équipé(e) de " + afficher(equipements);
	}

	public String afficher(LinkedList<Equipement> a) {
		Iterator<Equipement> it = a.iterator();
		String fin = "\n";
		while (it.hasNext()) {
			Equipement b = it.next();
			fin = fin + "       ->️ " + b.toString() + "\n";
		}
		return fin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getTemperature() {
		Main.traitementTemperatureNaturelle();
		Main.traitementTemperature();
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public int getIntensiteLumineuse() {
		Main.traitementIntensiteLumineuseNaturelle();
		Main.traitementIntensiteLumineuse();
		return intensiteLumineuse;
	}

	public void setIntensiteLumineuse(int intensiteLumineuse) {
		if ((intensiteLumineuse >= 0) && (intensiteLumineuse <= 100)) {
			this.intensiteLumineuse = intensiteLumineuse;
		} else if (intensiteLumineuse > 100) {
			this.intensiteLumineuse = 100;
		} else {
			this.intensiteLumineuse = 0;
		}
	}

	public LinkedList<Piece> getPiecesAdj() {
		return piecesAdj;
	}

	public void setPiecesAdj(LinkedList<Piece> piecesAdj) {
		this.piecesAdj = piecesAdj;
	}

	public LinkedList<Equipement> getEquipements() {
		return equipements;
	}

	public void setEquipements(LinkedList<Equipement> equipements) {
		this.equipements = equipements;
	}

	public String imagePiece() {
		return ("images/piece.png");
	}

	public Equipement creationEquipement(String name, int nb) {
		return new Alarme(name);
	}

	public LinkedList<String> equipementsAutorises() {
		LinkedList<String> equip = new LinkedList<String>();
		equip.add("Alarme");
		return equip;
	}

	public static LinkedList<String> piecesConstructibles() {
		LinkedList<String> pieces = new LinkedList<String>();
		pieces.add("Buanderie");
		pieces.add("Bureau");
		pieces.add("Chambre");
		pieces.add("Cuisine");
		pieces.add("Dressing");
		pieces.add("Escalier");
		pieces.add("Jardin");
		pieces.add("Mezzanine");
		pieces.add("Palier");
		pieces.add("Piscine");
		pieces.add("Salle à manger");
		pieces.add("Salle de bain");
		pieces.add("Salon");
		return pieces;
	}

	public Button getButton() {
		Button but = new Button();
		but.setText(getNom());
		return but;
	}

	public static ImageView imageViewPiece() {
		ImageView imageView = new ImageView();
		imageView.setFitWidth(800);
		imageView.setFitHeight(600);
		if (Main.getPosition().getClass().getName() == "pieces.Cuisine") {
			imageView.setImage(new Image("/images/cuisine.png"));
		} else if (Main.getPosition().getClass().getName() == "pieces.Escalier") {
			imageView.setImage(new Image("/images/escalier.png"));
		} else if (Main.getPosition().getClass().getName() == "pieces.Jardin") {
			imageView.setImage(new Image("/images/jardin.png"));
		} else if (Main.getPosition().getClass().getName() == "pieces.Piscine") {
			imageView.setImage(new Image("/images/piscine.png"));
		} else {
			imageView.setImage(new Image("/images/piece.png"));
		}
		return imageView;
	}

}
