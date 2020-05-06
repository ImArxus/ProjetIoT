package main;

import java.util.List;
import java.util.Scanner;
import equipements.Lumiere;
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

	public static void main(String[] args) throws InterruptedException {
		
		Scanner s = new Scanner(System.in); // Ouverture du scanner

		// Fin de parcours
		boolean stop = false;

		// Creation des pièces
		Piece cuisine = new Piece("Cuisine");
		Piece salleaManger = new Piece("Salle à manger");
		Piece salon = new Piece("Salon");
		Piece chambre = new Piece("Chambre1");
		Piece salleDeBain = new Piece("Salle de bain");
		Piece palier = new Piece("Palier");
		Piece escalier = new Piece("Escalier");
		Piece dressing = new Piece("Dressing");
		Piece buandrie = new Piece("Buandrie");
		Piece jardin = new Piece("Jardin");
		Piece bureau = new Piece("Bureau");
		Piece mezzanine = new Piece("Mezzanine");

		// Ajout de pièces dans la maison
		maison.ajouterPiece(cuisine);
		maison.ajouterPiece(salleaManger);
		maison.ajouterPiece(salon);
		maison.ajouterPiece(chambre);
		maison.ajouterPiece(salleDeBain);
		maison.ajouterPiece(palier);
		maison.ajouterPiece(escalier);
		maison.ajouterPiece(dressing);
		maison.ajouterPiece(buandrie);
		maison.ajouterPiece(jardin);
		maison.ajouterPiece(bureau);
		maison.ajouterPiece(mezzanine);
		// Ajout de pièces adjacentes
		
		//Adjacent salon
		maison.sontAdjacents(getPosition(), cuisine);
		maison.sontAdjacents(getPosition(), salleaManger);
		maison.sontAdjacents(getPosition(), palier);
		//adjacent salle a manger
		maison.sontAdjacents(salleaManger, dressing);
		maison.sontAdjacents(salleaManger, buandrie);
		maison.sontAdjacents(salleaManger, jardin);
		maison.sontAdjacents(salleaManger, escalier);
		//adjacent salle de bain
		maison.sontAdjacents(salleDeBain, palier);
		//adjacent chambre
		maison.sontAdjacents(chambre, palier);
		maison.sontAdjacents(chambre, bureau);
		//adjacent bureau
		maison.sontAdjacents(bureau, mezzanine);
		//adjacent Palier
		maison.sontAdjacents(palier, mezzanine);

				
		// Création des équipements
		Equipement lumiere = new Lumiere("Lumière1");
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
