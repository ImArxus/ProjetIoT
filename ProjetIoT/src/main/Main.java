package main;

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

	private static boolean actionEquipement(Equipement objet, Scanner s) {
		String requete = s.nextLine();
		switch (requete) {
		case "Quitter":
			System.out.println("Vous n'utilisez plus " + objet.getNom()); // Ne fait aucune action
			return true;
		case "Allumer":
			objet.allumer();
			System.out.println(objet.getNom() + " est allumé(e)");
			break;
		case "Eteindre":
			objet.eteindre();
			System.out.println(objet.getNom() + " est éteint(e)");
			break;
		default:
			if (objet instanceof Radiateur) { // Si l'objet est un radiateur
				switch (requete) {
				case "Augmenter température":
					((Radiateur) objet).augmenterTemperature();
					System.out.println("Le thermostat de " + objet.getNom() + " est réglé sur "
							+ ((Radiateur) objet).getThermostat());
					break;
				case "Diminuer température":
					((Radiateur) objet).diminuerTemperature();
					System.out.println("Le thermostat de " + objet.getNom() + " est réglé sur "
							+ ((Radiateur) objet).getThermostat());
					break;
				case "Choisir thermostat":
					System.out.println("Quelle thermostat (entre 0 et 5) ?");
					int thermostat = s.nextInt();
					((Radiateur) objet).choisirThermostat(thermostat);
					System.out.println("Le thermostat de " + objet.getNom() + " est réglé sur "
							+ ((Radiateur) objet).getThermostat());
					break;
				default:
					System.out.println("Commande non-valide");
					break;
				}
			} else if (objet instanceof TV) { // Si l'objet est une TV
				switch (requete) {
				case "Augmenter volume":
					((TV) objet).augmenterVolume();
					System.out.println("Le volume de " + objet.getNom() + " est de " + ((TV) objet).getVolume());
					break;
				case "Diminuer volume":
					((TV) objet).diminuerVolume();
					System.out.println("Le volume de " + objet.getNom() + " est de " + ((TV) objet).getVolume());
					break;
				case "Augmenter chaine":
					((TV) objet).augmenterChaine();
					System.out.println(objet.getNom() + " est réglé sur la chaine " + ((TV) objet).getNumeroChaine());
					break;
				case "Diminuer chaine":
					((TV) objet).diminuerChaine();
					System.out.println(objet.getNom() + " est réglé sur la chaine " + ((TV) objet).getNumeroChaine());
					break;
				case "Mettre chaine":
					System.out.println("Quelle chaine (entre 0 et 100) ?");
					int chaine = s.nextInt();
					s.nextLine(); // On vide la ligne pour ne pas avoir de problème au prochain nextLine()
					((TV) objet).mettreChaine(chaine);
					System.out.println(objet.getNom() + " est réglé sur la chaine " + ((TV) objet).getNumeroChaine());
					break;
				default:
					System.out.println("Commande non-valide");
					break;
				}
			} else if (objet instanceof Volet) { // Si l'objet est un volet
				switch (requete) {
				case "Monter volet":
					((Volet) objet).monterVolet();
					System.out.println(
							"La position du store " + objet.getNom() + " est de " + ((Volet) objet).getPosition());
					break;
				case "Descendre volet":
					((Volet) objet).descendreVolet();
					System.out.println(
							"La position du store " + objet.getNom() + " est de " + ((Volet) objet).getPosition());
					break;
				case "Choisir position":
					System.out.println("Quelle position (entre 0 et 100) ?");
					int position = s.nextInt();
					((Volet) objet).choisirPosition(position);
					System.out.println(
							"La position du store " + objet.getNom() + " est de " + ((Volet) objet).getPosition());
					break;
				default:
					System.out.println("Commande non-valide");
					break;
				}
			}
			break;
		}
		return false;
	}

	public static void main(String[] args) throws InterruptedException {
		Scanner s = new Scanner(System.in); // Ouverture du scanner

		// Fin de parcours
		boolean stop = false;

		// Creation des pièces
		Piece cuisine = new Piece("Cuisine");
		Piece salleaManger = new Piece("Salle à manger");

		// Ajout de pièces dans la maison
		maison.ajouterPiece(cuisine);
		maison.ajouterPiece(salleaManger);

		// Ajout de pièces adjacentes
		maison.sontAdjacents(getPosition(), cuisine);
		maison.sontAdjacents(getPosition(), salleaManger);

		// Création des équipements
		Equipement lumiere = new Lumiere("Lumière1", false);
		Equipement TV = new TV("TV1");

		// Ajout des équipements dans les pièces
		cuisine.ajouterEquipement(lumiere);
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
				for (int i = 0; i < piecesAdj.size(); i++) {
					System.out.println("-> " + piecesAdj.get(i)); // Affiche la liste des pièces adjacentes
				}
				String newPiece = s.nextLine();
				int i = 0;
				boolean trouve = false;
				while (i < piecesAdj.size() && !trouve) {
					if (newPiece.equals(piecesAdj.get(i).getNom())) { // Vérifie que la pièce existe
						trouve = true;
						setPosition(piecesAdj.get(i)); // Déplacement dans la pièce choisie
					} else {
						i++;
					}
				}
				if (!trouve) {
					System.out.println("La pièce que vous souhaitez rejoindre n'existe pas ou est inaccessible");
				}
			}

			/***************************************************************
			 ************************* Utilisation *************************
			 ***************************************************************/
			else if (requete.equals("use")) {
				System.out.println("\nQuel équipement souhaitez-vous utiliser ?");
				List<Equipement> equip = getPosition().getEquipements();
				for (int i = 0; i < equip.size(); i++) {
					System.out.println("-> " + equip.get(i)); // Affiche la liste des pièces adjacentes
				}
				System.out.println();
				String newObjet = s.nextLine();
				int i = 0;
				boolean trouve = false;
				boolean exit = false;
				while (i < equip.size() && !trouve) {
					if (newObjet.equals(equip.get(i).getNom())) { // Vérifie que l'objet existe
						trouve = true;
						Equipement objet = equip.get(i);
						while (!exit) {
							System.out.println("\nQue souhaitez-vous faire avec " + objet + " ?");
							System.out.println(objet.actionsPossibles() + "\n"); // Liste toutes les actions possibles
							exit = actionEquipement(objet, s); // Agit avec la commande entrée
							Thread.sleep(2000); // Delai de 2 secondes
						}
					} else {
						i++;
					}
				}
				if (!trouve) {
					System.out.println("L'équipement que vous souhaitez utiliser n'existe pas ou est inaccessible");
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
			
			else {
				System.out.println("Commande non-valide");
			}

		}
		s.close(); // Fermeture du scanner
	}

}
