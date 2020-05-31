package main;

import equipements.Alarme;
import equipements.Alexa;
import equipements.Balance;
import equipements.Cheminee;
import equipements.Electrolyseur;
import equipements.Enceinte;
import equipements.Frigo;
import equipements.Lumiere;
import equipements.PS5;
import equipements.Radiateur;
import equipements.TV;
import equipements.Thermostat;
import equipements.Ventilateur;
import equipements.Volet;
import javafx.event.ActionEvent;

public class CreationEquipementController {

	Piece position = Main.getPosition();
	MainController mainController = new MainController();

	public void annuler(ActionEvent event) {
		mainController.scenePiece(event);
	}
	
	public void creerAlarme(ActionEvent event) {
		position.ajouterEquipement(new Alarme("Alarme"));
		mainController.scenePiece(event);
	}
	
	public void creerAlexa(ActionEvent event) {
		position.ajouterEquipement(new Alexa("Alexa"));
		mainController.scenePiece(event);
	}
	
	public void creerBalance(ActionEvent event) {
		position.ajouterEquipement(new Balance("Balance"));
		mainController.scenePiece(event);
	}
	
	public void creerCheminee(ActionEvent event) {
		position.ajouterEquipement(new Cheminee("Cheminée"));
		mainController.scenePiece(event);
	}
	
	public void creerElectrolyseur(ActionEvent event) {
		position.ajouterEquipement(new Electrolyseur("Électrolyseur"));
		mainController.scenePiece(event);
	}
	
	public void creerEnceinte(ActionEvent event) {
		position.ajouterEquipement(new Enceinte("Enceinte"));
		mainController.scenePiece(event);
	}
	
	public void creerFrigo(ActionEvent event) {
		position.ajouterEquipement(new Frigo("Frigo"));
		mainController.scenePiece(event);
	}
	
	public void creerLumiere(ActionEvent event) {
		position.ajouterEquipement(new Lumiere("Lumière"));
		mainController.scenePiece(event);
	}
	
	public void creerPS5(ActionEvent event) {
		position.ajouterEquipement(new PS5("PS5"));
		mainController.scenePiece(event);
	}
	
	public void creerRadiateur(ActionEvent event) {
		position.ajouterEquipement(new Radiateur("Radiateur"));
		mainController.scenePiece(event);
	}
	
	public void creerThermostat(ActionEvent event) {
		position.ajouterEquipement(new Thermostat("Thermostat"));
		mainController.scenePiece(event);
	}
	
	public void creerTV(ActionEvent event) {
		position.ajouterEquipement(new TV("TV"));
		mainController.scenePiece(event);
	}
	
	public void creerVentilateur(ActionEvent event) {
		position.ajouterEquipement(new Ventilateur("Ventilateur"));
		mainController.scenePiece(event);
	}
	
	public void creerVolet(ActionEvent event) {
		position.ajouterEquipement(new Volet("Volet"));
		mainController.scenePiece(event);
	}

}
