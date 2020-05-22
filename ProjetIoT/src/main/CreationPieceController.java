package main;

import javafx.event.ActionEvent;
import pieces.Buanderie;
import pieces.Bureau;
import pieces.Chambre;
import pieces.Cuisine;
import pieces.Dressing;
import pieces.Escalier;
import pieces.Jardin;
import pieces.Mezzanine;
import pieces.Palier;
import pieces.Piscine;
import pieces.SalleDeBain;
import pieces.Salon;

public class CreationPieceController {

	Maison maison = Main.getMaison();
	MainController mainController = new MainController();

	public void creerBuanderie(ActionEvent event) {
		maison.ajouterPieceFX(new Buanderie("Buanderie"));
		mainController.scenePiece(event);
	}
	
	public void creerBureau(ActionEvent event) {
		maison.ajouterPieceFX(new Bureau("Bureau"));
		mainController.scenePiece(event);
	}
	
	public void creerChambre(ActionEvent event) {
		maison.ajouterPieceFX(new Chambre("Chambre"));
		mainController.scenePiece(event);
	}
	
	public void creerCuisine(ActionEvent event) {
		maison.ajouterPieceFX(new Cuisine("Cuisine"));
		mainController.scenePiece(event);
	}
	
	public void creerDressing(ActionEvent event) {
		maison.ajouterPieceFX(new Dressing("Dressing"));
		mainController.scenePiece(event);
	}
	
	public void creerEscalier(ActionEvent event) {
		maison.ajouterPieceFX(new Escalier("Escalier"));
		mainController.scenePiece(event);
	}
	
	public void creerJardin(ActionEvent event) {
		maison.ajouterPieceFX(new Jardin("Jardin"));
		mainController.scenePiece(event);
	}
	
	public void creerMezzanine(ActionEvent event) {
		maison.ajouterPieceFX(new Mezzanine("Mezzanine"));
		mainController.scenePiece(event);
	}
	
	public void creerPalier(ActionEvent event) {
		maison.ajouterPieceFX(new Palier("Palier"));
		mainController.scenePiece(event);
	}
	
	public void creerPiscine(ActionEvent event) {
		maison.ajouterPieceFX(new Piscine("Piscine"));
		mainController.scenePiece(event);
	}
	
	public void creerSalleAManger(ActionEvent event) {
		maison.ajouterPieceFX(new Buanderie("Buanderie"));
		mainController.scenePiece(event);
	}
	
	public void creerSalleDeBain(ActionEvent event) {
		maison.ajouterPieceFX(new SalleDeBain("Salle de bain"));
		mainController.scenePiece(event);
	}
	
	public void creerSalon(ActionEvent event) {
		maison.ajouterPieceFX(new Salon("Salon"));
		mainController.scenePiece(event);
	}

}
