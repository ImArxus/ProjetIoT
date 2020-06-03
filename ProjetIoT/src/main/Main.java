package main;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import equipements.Cheminee;
import equipements.Lumiere;
import equipements.Radiateur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application implements Serializable {

	private static final long serialVersionUID = -5850588170235124346L;

	public static void main(String[] args) throws InterruptedException {

		Main.chargerCompte();

		launch(args); // Lancement interface graphique sous JavaFX
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			primaryStage.setTitle("Login");
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/Login.fxml")));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			System.err.println("Erreur lors de la création de l'interface");
		}
	}

	private static Maison maison; // Maison définie dans la classe BarryHouse
	private static Piece position; // Position initiale dans la premère pièce ajoutée
	private static String pseudo;
	private static int intensiteLumineuseNaturelle = 0;
	private static double temperatureNaturelle = 0;
	private static int heure = 4;
	//private static int heure = (int) (Math.random() * 24);
	private static String avatar = "homme1";
	private static ListeUtilisateurs listeUtilisateur = new ListeUtilisateurs();

	public static ListeUtilisateurs getListeUtilisateur() {
		return listeUtilisateur;
	}
	
	public static Maison getMaison() {
		return maison;
	}

	public static void setMaison(Maison maison) {
		Main.maison = maison;
	}

	public static String getPseudo() {
		return pseudo;
	}

	public static void setPseudo(String pseudo) {
		Main.pseudo = pseudo;
	}

	public static String getAvatar() {
		return avatar;
	}

	public static void setAvatar(String avatar) {
		Main.avatar = avatar;
	}

	public static Piece getPosition() {
		return position;
	}

	public static void setPosition(Piece position) {
		Main.position = position;
	}

	public static int getHeure() {
		return heure;
	}
	
	public static int getIntensiteLumineuseNaturelle() {
		return intensiteLumineuseNaturelle;
	}

	public static void setIntensiteLumineuseNaturelle(int newIntensite) {
		intensiteLumineuseNaturelle = newIntensite;
	}
	
	public static double getTemperatureNaturelle() {
		return temperatureNaturelle;
	}

	public static void setTemperatureNaturelle(double temperatureNaturelle) {
		Main.temperatureNaturelle = temperatureNaturelle;
	}

	public static void chargerCompte() {
		ListeUtilisateurs listeUserTmp = Sauvegarde.chargerComptes();
		if (listeUserTmp != null) {
			listeUtilisateur = listeUserTmp;
		}
	}
	
	public static LinkedList<Equipement> getLumiere() {
		LinkedList<Equipement> lumieres = new LinkedList<Equipement>();
		LinkedList<Equipement> equip = getPosition().getEquipements();
		Iterator<Equipement> it = equip.iterator();
		while (it.hasNext()) {
			Equipement e = it.next();
			String tmp = e.getClass().getSimpleName();
			if (tmp.equals("Lumiere") || tmp.equals("Cheminee")) {
				lumieres.add(e);
			}
		}
		return lumieres;
	}

	public static LinkedList<Equipement> getChauffants() {
		LinkedList<Equipement> chauffants = new LinkedList<Equipement>();
		LinkedList<Equipement> equip = getPosition().getEquipements();
		Iterator<Equipement> it = equip.iterator();
		while (it.hasNext()) {
			Equipement e = it.next();
			String tmp = e.getClass().getSimpleName();
			if (tmp.equals("Radiateur") || tmp.equals("Cheminee")) {
				chauffants.add(e);
			}
		}
		return chauffants;
	}

	public static void calculHoraires() {
		heure++;
		if ((heure > 22) || (heure < 8)) {
			if (heure == 24) {
				heure = 0;
			}
			System.out.println("C'est actuellement la nuit, il est " + heure + "h !");
		} else if (heure == 8) {
			System.out.println("Le soleil se lève, il est " + heure + "h !");
		} else if (heure == 22) {
			System.out.println("Le soleil se couche, il est " + heure + "h !");
		} else {
			System.out.println("C'est actuellement le jour, il est " + heure + "h !");
		}
	}

	public static void traitementIntensiteLumineuseNaturelle() {
		if ((heure == 8) || (heure == 21)) {
			setIntensiteLumineuseNaturelle(20);
		} else if ((heure == 9) || (heure == 20)) {
			setIntensiteLumineuseNaturelle(40);
		} else if ((heure == 10) || ((heure >= 18) && (heure < 20))) {
			setIntensiteLumineuseNaturelle(60);
		} else if ((heure >= 11) && (heure < 18)) {
			setIntensiteLumineuseNaturelle(100);
		} else {
			setIntensiteLumineuseNaturelle(0);
		}
	}

	public static void traitementIntensiteLumineuse() {
		LinkedList<Equipement> lumieresPieces = getLumiere();
		int sommeILobjets = 0;
		for (int i = 0; i < lumieresPieces.size(); i++) {
			Equipement objet = lumieresPieces.get(i);
			if (objet instanceof Lumiere) {
				if (((Lumiere) objet).isEtatCourant()) {
					sommeILobjets += ((Lumiere) objet).getIntensite();
				}
			}
			if (objet instanceof Cheminee) {
				if (((Cheminee) objet).isEtatCourant()) {
					sommeILobjets += ((Cheminee) objet).getIntensite() / 3;
				}
			}
		}
		position.setIntensiteLumineuse(getIntensiteLumineuseNaturelle() + sommeILobjets);
	}

	public static void traitementTemperatureNaturelle() {
		if ((heure == 8) || (heure == 21)) {
			setTemperatureNaturelle(10);
		} else if ((heure == 9) || (heure == 20)) {
			setTemperatureNaturelle(12);
		} else if ((heure == 10) || ((heure >= 18) && (heure < 20))) {
			setTemperatureNaturelle(14);
		} else if ((heure >= 11) && (heure < 18)) {
			setTemperatureNaturelle(16);
		} else {
			setTemperatureNaturelle(6);
		}
	}

	public static void traitementTemperature() {
		LinkedList<Equipement> objetChaud = getChauffants();
		int sommeTempObjets = 0;
		for (int i = 0; i < objetChaud.size(); i++) {
			Equipement objet = objetChaud.get(i);
			if (objet instanceof Radiateur) {
				if (((Radiateur) objet).isEtatCourant()) {
					sommeTempObjets += ((Radiateur) objet).getThermostat() * 3;
				}
			} else if (objet instanceof Cheminee) {
				if (((Cheminee) objet).isEtatCourant()) {
					sommeTempObjets += ((Cheminee) objet).getIntensite() / 20;
				}
			}
		}
		position.setTemperature(getTemperatureNaturelle() + sommeTempObjets);
	}

}
