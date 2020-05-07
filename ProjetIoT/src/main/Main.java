package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import equipements.Alarme;
import equipements.Lumiere;

public class Main {

	private static Maison maison; // Maison définie dans la classe BarryHouse
	private static Piece position; // Position initiale dans la premère pièce ajoutée
	private static String pseudo;
	private static String mdp;
	private static Boolean droits;
	private static int intensiteLumineuseNaturelle = 0;
	private static int heure = (int) (Math.random() * 24);
	private static ListeUtilisateurs listeUtilisateurs = new ListeUtilisateurs();

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

	public static void calculHoraires() {
		heure++;
		if ((heure > 22) || (heure < 8)) {
			if (heure == 24) {
				heure = 0;
			}
			System.out.println("C'est actuellement la nuit !, il est " + heure + "h!");
		} else if (heure == 8) {
			System.out.println("Le soleil se lève !, il est " + heure + "h!");
		} else if (heure == 22) {
			System.out.println("Le soleil se couche, il est " + heure + "h!");
		} else {
			System.out.println("C'est actuellement le jour !, il est " + heure + "h!");
		}
	}

	public static void affichageTemperature() {
		System.out.println("La temperature ambiante de la piece " + position.getNom() + " est de "
				+ position.getTemperature() + "°C.");
	}

	public static void traitementIntensiteLumineuseNaturelle() {
		if ((heure == 8) || (heure == 21)) {
			intensiteLumineuseNaturelle = 20;
		} 
		else if ((heure == 9) || (heure == 20)) {
			intensiteLumineuseNaturelle = 40;
		}
		else if ((heure==10) || ((heure >= 18) && (heure < 20))) {
			intensiteLumineuseNaturelle = 60;
		}
		else if ((heure >= 11) && (heure < 18)) {
			intensiteLumineuseNaturelle = 100;
		} else {
			intensiteLumineuseNaturelle = 0;
		}
	}

	public static void traitementIntensiteLumineuse() {
		List<Equipement> lumieresPieces = getLumiere();
		int sommeILobjets = 0;
		for (int i = 0; i < lumieresPieces.size(); i++) {
			Equipement objet = lumieresPieces.get(i);
			if (objet instanceof Lumiere) {
				if (((Lumiere) objet).isEtatCourant()) {
					sommeILobjets += ((Lumiere) objet).getIntensite();
				}
			}
		}
		position.setIntensiteLumineuse(intensiteLumineuseNaturelle + sommeILobjets);
		System.out.println("L'intensité lumineuse de la piece " + position.getNom() + " est de "
				+ position.getIntensiteLumineuse() + "%. (dont = " + intensiteLumineuseNaturelle + "% naturelle et  "
				+ sommeILobjets + "% artificielle).");
	}

	public static void chargement(Scanner s) throws InterruptedException {
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		for (int i = 0; i < 11; i++) {
			System.out.println("Chargement de Barry's House™️");
			for (int j = 0; j <= i; j++) {
				System.out.print("✅️️️️️️");
			}
			for (int k = 10; k > i; k--) {
				System.out.print("⬜️");
			}

			System.out.print("\n " + i * 10 + "%");
			Thread.sleep(500);
			System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		}
		System.out.println("Veuillez saisir votre identifiant");
		pseudo = s.nextLine();
		if (listeUtilisateurs.comptes.containsKey(pseudo)) {
			System.out.println("identifiant détecté");
			System.out.println("Veuillez saisir votre mdp");
			mdp = s.nextLine();
			if (listeUtilisateurs.comptes.get(pseudo).equals(mdp)) {
				System.out.println("mot de passe correct");
			} else {
				System.out.println("mot de passe incorrect");
				pseudo = "guest";
				mdp = listeUtilisateurs.comptes.get(pseudo);
				System.out.println("Connexion automatique sur le compte guest");
			}
		} else {
			System.out.println("identifiant inconnu");
			System.out.println("\nNouveau compte crée, Veuillez chosir votre mdp");
			mdp = s.nextLine();
			listeUtilisateurs.comptes.put(pseudo, mdp);
			listeUtilisateurs.estAdmin.put(pseudo, false);
		}
		System.out.println("----------------------------------------------");
		System.out.println("identifiant : " + pseudo);
		System.out.print("mdp : ");
		for (int v = 0; v < mdp.length(); v++) {
			System.out.print("*️");// masquage mot de passe
		}
		droits = listeUtilisateurs.estAdmin.get(pseudo);
		System.out.println("\nactivation du mode administrateur : " + droits);
		System.out.println("----------------------------------------------");
		Thread.sleep(1000);
		System.out.println("\n\nBienvenue " + pseudo + " ! Quelle maison voulez-vous charger ?");
		System.out.println("-> 1 : Barry's House");
		System.out.println("-> 2 : Maison Vide\n");
		int requete = s.nextInt();
		s.nextLine();
		if (requete == 1) {
			maison = BarryHouse.creerMaison();
			System.out.println("\nBienvenue dans la maison de Barry !\n");
		} else {
			maison = BarryHouse.creerMaisonVide();
			System.out.println("\nVotre maison de rêve n'attend que vous !");
		}
		setPosition(maison.getPieces().get(0));
		Thread.sleep(2000);
	}

	public static void main(String[] args) throws InterruptedException {

		Scanner s = new Scanner(System.in); // Ouverture du scanner

		chargement(s); // Choix de la maison et de l'utilisateur

		boolean stop = false;// Fin de parcours

		while (!stop && !alarme(s)) { // Boucle d'intervention utilisateur
			calculHoraires();// calcul heure du jour
			affichageTemperature();// affichage temperature pièce
			traitementIntensiteLumineuseNaturelle();// traitement ILN
			traitementIntensiteLumineuse();// traitement&affichage IL totale
			Thread.sleep(2000);

			System.out.println("\nVous êtes dans : " + getPosition() + "\n");
			System.out.println("Que souhaitez-vous faire ?");
			System.out.println(listeUtilisateurs.actionsPossibles(pseudo));

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
