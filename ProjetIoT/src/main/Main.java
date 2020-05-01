package main;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import equipements.Lumiere;
import equipements.Radiateur;
import equipements.TV;
import equipements.Volet;

public class Main {

	static Piece salon = new Piece("Salon");
	private static Maison maison = new Maison("MyHouse", salon); // Créé une maison avec un salon
	private static Piece position = maison.getPieces().get(0); // Position initiale dans la premère pièce ajoutée

	public static Piece getPosition() {
		return position;
	}

	public static void setPosition(Piece position) {
		Main.position = position;
	}

	private static String actionEquipement(Equipement objet, Scanner s) {
		String requete = s.nextLine();
		switch (requete) {
		case "Allumer":
			objet.allumer();
			return objet.getNom() + " est allumé";
		case "Eteindre":
			objet.eteindre();
			return objet.getNom() + " est éteint";
		default:
			break;
		}
		if (objet instanceof Radiateur) { // Si l'objet est un radiateur
			switch (requete) {
			case "Augmenter température":
				((Radiateur) objet).augmenterTemperature();
				return "Le thermostat de " + objet.getNom() + " est réglé sur " + ((Radiateur) objet).getThermostat();
			case "Diminuer température":
				((Radiateur) objet).diminuerTemperature();
				return "Le thermostat de " + objet.getNom() + " est réglé sur " + ((Radiateur) objet).getThermostat();
			case "Choisir thermostat":
				System.out.println("Quelle thermostat (entre 0 et 5) ?");
				int thermostat = s.nextInt();
				((Radiateur) objet).choisirThermostat(thermostat);
				return "Le thermostat de " + objet.getNom() + " est réglé sur " + ((Radiateur) objet).getThermostat();
			}
		}
		if (objet instanceof TV) { // Si l'objet est une TV
			switch (requete) {
			case "Augmenter volume":
				((TV) objet).augmenterVolume();
				return "Le volume de " + objet.getNom() + " est de " + ((TV) objet).getVolume();
			case "Diminuer volume":
				((TV) objet).diminuerVolume();
				return "Le volume de " + objet.getNom() + " est de " + ((TV) objet).getVolume();
			case "Augmenter numéro chaine":
				((TV) objet).augmenterNumeroChaine();
				return objet.getNom() + " est réglé sur la chaine " + ((TV) objet).getNumeroChaine();
			case "Diminuer numéro chaine":
				((TV) objet).diminuerNumeroChaine();
				return objet.getNom() + " est réglé sur la chaine " + ((TV) objet).getNumeroChaine();
			case "Mettre chaine":
				System.out.println("Quelle chaine (entre 0 et 100) ?");
				int chaine = s.nextInt();
				((TV) objet).mettreChaine(chaine);
				return objet.getNom() + " est réglé sur la chaine " + ((TV) objet).getNumeroChaine();
			}
		}
		if (objet instanceof Volet) { // Si l'objet est un volet
			switch (requete) {
			case "Monter volet":
				((Volet) objet).monterVolet();
				return "La position du store " + objet.getNom() + " est de " + ((Volet) objet).getPosition();
			case "Descendre volet":
				((Volet) objet).descendreVolet();
				return "La position du store " + objet.getNom() + " est de " + ((Volet) objet).getPosition();
			case "Choisir position":
				System.out.println("Quelle position (entre 0 et 100) ?");
				int position = s.nextInt();
				((Volet) objet).choisirPosition(position);
				return "La position du store " + objet.getNom() + " est de " + ((Volet) objet).getPosition();
			}
		}
		return "";
	}

	public static void main(String[] args) throws InterruptedException {
		Scanner s = new Scanner(System.in);
		
		//Fin de parcours
		boolean stop = false;

		//Creation des pièces
		// Créé une cuisine adjacente à notre position avec une lumière
		Piece cuisine = new Piece("Cuisine");
		Piece salleaManger = new Piece("Salle a manger");
		
		//Ajout de pièce dans la maison
		maison.ajouterPiece(cuisine);
		maison.ajouterPiece(salleaManger);
		
		
		//Ajout de pièce adjacentes
		maison.sontAdjacents(getPosition(), cuisine);
		maison.sontAdjacents(getPosition(), salleaManger);
		
		
		//Création des equipement
		Equipement lumiere = new Lumiere("Lumière1", false);
		
		//Ajout des equipements dans les pièces
		cuisine.ajouterEquipement(lumiere);

		// Créé une TV dans le salon
		Equipement TV = new TV("TV1");
		salon.ajouterEquipement(TV);

		while (!stop) { // Boucle d'intervention utilisateur
			System.out.println("\nVous êtes dans : " + getPosition() + "\n");
			System.out.println("Que souhaitez-vous faire ?");
			System.out.println("-> Pour changer de pièce, tapez 'move'");
			System.out.println("-> Pour utiliser un équipement, tapez 'use'");
			System.out.println("-> Pour quitter, tapez 'exit'\n");

			String requete = s.nextLine();

			/***************************************************************
			 ************************* Déplacement *************************
			 ***************************************************************/
			if (requete.equals("move")) {
				System.out.println("\nDans quelle pièce souhaitez-vous aller ?");
				List<Piece> piecesAdj = getPosition().getPiecesAdj();
				System.out.println("-> " + piecesAdj + "\n"); // Affiche la liste des pièces adjacentes
				String newPiece = s.nextLine();
				for (int i = 0; i < piecesAdj.size(); i++) {
					if (newPiece.equals(piecesAdj.get(i).getNom())) { // Vérifie que la pièce existe
						setPosition(piecesAdj.get(i)); // Déplacement dans la pièce choisie
					} else {
						System.out.println("La pièce que vous souhaitez rejoindre n'existe pas ou est inaccessible");
					}
				}
			}

			/***************************************************************
			 ************************* Utilisation *************************
			 ***************************************************************/
			else if (requete.equals("use")) {
				System.out.println("\nQuel équipement souhaitez-vous utiliser ?");
				List<Equipement> equip = getPosition().getEquipements();
				System.out.println("-> " + equip); // Affiche la liste des équipements disponibles
				String newObjet = s.nextLine();
				for (int i = 0; i < equip.size(); i++) {
					if (newObjet.equals(equip.get(i).getNom())) { // Vérifie que l'objet existe
						Equipement objet = equip.get(i);
						System.out.println("Que souhaitez-vous faire avec " + objet + " ?");
						System.out.println(objet.actionsPossibles() + "\n"); // Liste toutes les actions possibles
						System.out.println(actionEquipement(objet, s)); // Agit avec la commande entrée
					} else {
						System.out.println("L'équipement que vous souhaitez utiliser n'existe pas ou est inaccessible");
					}
				}
				Thread.sleep(3000); // Delai de 3 secondes
			}

			/***************************************************************
			 **************************** Arrêt ****************************
			 ***************************************************************/
			else if (requete.equals("exit")) {
				System.out.println("\nAu revoir !");
				stop = true;
			}

		}
		s.close();
	}

}
