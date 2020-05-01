package main;

import java.util.List;
import java.util.Scanner;

import equipements.Lumiere;

public class Main {

	private static Maison maison = new Maison("MyHouse");
	private static Piece position = maison.getPieces().get(0); // Position initiale dans la premère pièce ajoutée

	public static Piece getPosition() {
		return position;
	}

	public static void setPosition(Piece position) {
		Main.position = position;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		boolean stop = false;

		// Créé une cuisine adjacente à notre position avec une lumière
		Piece cuisine = new Piece("Cuisine");
		maison.ajouterPiece(cuisine);
		getPosition().ajouterPieceAdj(cuisine);
		Equipement lumiere = new Lumiere("Lumière1", false);
		cuisine.ajouterEquipement(lumiere);
		
		while (!stop) { // Boucle d'intervention utilisateur
			System.out.println("\nVous êtes dans : " + getPosition() + "\n");
			System.out.println("Que souhaitez-vous faire ?");
			System.out.println("-> Pour changer de pièce, tapez 'move'");
			System.out.println("-> Pour utiliser un équipement, tapez 'use'");
			System.out.println("-> Pour quitter, tapez 'exit'\n");

			String requete = s.nextLine();

			if (requete.equals("move")) { // Condition de déplacement dans la maison
				System.out.println("\nDans quelle pièce souhaitez-vous aller ?");
				List<Piece> piecesAdj = getPosition().getPiecesAdj();
				System.out.println("     " + piecesAdj + "\n");
				String newPiece = s.nextLine();
				for (int i = 0; i < piecesAdj.size(); i++) { // On se déplace dans la pièce si elle existe
					if (newPiece.equals(piecesAdj.get(i).getNom())) {
						setPosition(piecesAdj.get(i));
					} else {
						System.out.println("La pièce que vous souhaitez rejoindre n'existe pas ou est inaccessible");
					}
				}
			}

			if (requete.equals("use")) { // Condition d'utilisation d'un équipement (objet)
				System.out.println("\nQuel équipement souhaitez-vous utiliser ?");
				List<Equipement> equip = getPosition().getEquipements();
				System.out.println("     " + equip);
				String newObjet = s.nextLine();
				for (int i = 0; i < equip.size(); i++) { // On se déplace dans la pièce si elle existe
					if (newObjet.equals(equip.get(i).getNom())) {
						Equipement objet = equip.get(i);
						System.out.println("Que souhaitez-vous faire avec " + objet.toString());
					} else {
						System.out.println("L'équipement que vous souhaitez utiliser n'existe pas ou est inaccessible");
					}
				}
			}

			if (requete.equals("exit")) { // Condition d'arrêt
				System.out.println("\nAu revoir !");
				stop = true;
			}

		}
		s.close();
	}

}
