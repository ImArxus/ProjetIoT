package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import equipements.Alarme;

public class Main {

	private static Maison maison = BarryHouse.creerMaison(); // Maison définie dans la classe BarryHouse
	private static Piece position = maison.getPieces().get(0); // Position initiale dans la premère pièce ajoutée

	public static Piece getPosition() {
		return position;
	}

	public static void setPosition(Piece position) {
		Main.position = position;
	}

	public static List<Equipement> getLumiere() {
		List<Equipement> Lumieres = new LinkedList<Equipement>();
		List<Equipement> l = getPosition().getEquipements();
		Iterator<Equipement> it = l.iterator();
		while (it.hasNext()) {
			Equipement e = it.next();
			String tmp = e.getClass().getSimpleName();
			if (tmp.equals("Lumiere")) {
				Lumieres.add(e);
			}
		}
		return Lumieres;
	}

	public static boolean alarme(Scanner s) {
		// Demande action pour l'alarme si il y en a une dans la pièce
		List<Equipement> equip = getPosition().getEquipements();
		for (int i = 0; i < equip.size(); i++) {
			Equipement objet = equip.get(i);
			if (objet instanceof Alarme) {
				if (((Alarme) objet).isEtatCourant()) {
					System.out.println("Désactiver l'alarme (oui/non) ?\n");
					String requete = s.nextLine();
					if (requete.equals("oui")) {
						objet.eteindre();
					} else {
						((Alarme) objet).sonner();
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws InterruptedException {

		Scanner s = new Scanner(System.in); // Ouverture du scanner

		// Fin de parcours
		boolean stop = false;

		while (!stop && !alarme(s)) { // Boucle d'intervention utilisateur

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

						List<Equipement> lumieres = getLumiere();
						for (Equipement lum : lumieres) { //// Eteindre les lumieres
							lum.eteindre();
						}
						setPosition(piecesAdj.get(i)); // Déplacement dans la pièce choisie
						lumieres = getLumiere();
						if (lumieres.isEmpty()) {
							System.out.println("Il n'y a pas de lumière");
						}
						for (Equipement lum : lumieres) { //// Allumer
							lum.allumer();
						}

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
				List<Equipement> equip = getPosition().getEquipements();
				System.out.println("\nQuel équipement souhaitez-vous utiliser ?");
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
							exit = Action.actionEquipement(objet, s); // Commandes d'action dans la class Action
							Thread.sleep(2000); // Delai de 2 secondes
						}
					} else {
						i++;
					}
				}
				if (!trouve) {
					System.out.println("L'équipement que vous souhaitez utiliser n'existe pas ou est inaccessible");
					Thread.sleep(3000); // Delai de 3 secondes
				}
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
