package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import equipements.Alarme;
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
import equipements.Volet;

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
			System.out.println("C'est actuellement la nuit, il est " + heure + "h !");
		} else if (heure == 8) {
			System.out.println("Le soleil se lève, il est " + heure + "h !");
		} else if (heure == 22) {
			System.out.println("Le soleil se couche, il est " + heure + "h !");
		} else {
			System.out.println("C'est actuellement le jour, il est " + heure + "h !");
		}
	}

	public static void affichageTemperature() {
		System.out.println(
				"La temperature ambiante de " + position.getNom() + " est de " + position.getTemperature() + "°C");
	}

	public static void traitementIntensiteLumineuseNaturelle() {
		if ((heure == 8) || (heure == 21)) {
			intensiteLumineuseNaturelle = 20;
		} else if ((heure == 9) || (heure == 20)) {
			intensiteLumineuseNaturelle = 40;
		} else if ((heure == 10) || ((heure >= 18) && (heure < 20))) {
			intensiteLumineuseNaturelle = 60;
		} else if ((heure >= 11) && (heure < 18)) {
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
		System.out.println("L'intensité lumineuse de " + position.getNom() + " est de "
				+ position.getIntensiteLumineuse() + "% (dont = " + intensiteLumineuseNaturelle + "% naturelle et  "
				+ sommeILobjets + "% artificielle)");
	}

	public static void chargement(Scanner s) throws InterruptedException {
		boolean connecte = false;
		while (!connecte) {
			System.out.print("Identifiant : ");
			pseudo = s.nextLine();
			if (listeUtilisateurs.comptes.containsKey(pseudo)) {
				System.out.println("Identifiant détecté");
				System.out.println("------------------------------------------------------------------");
				System.out.print("Mot de passe : ");
				mdp = s.nextLine();
				if (listeUtilisateurs.comptes.get(pseudo).equals(mdp)) {
					System.out.println("Mot de passe correct");
					connecte = true;
				} else {
					System.out.println("Mot de passe incorrect");
					System.out.println("➡️ 1 : Continuer en tant qu'invité\n➡️ 2 : Réessayer");
					int requete = s.nextInt();
					s.nextLine();
					if (requete == 1) {
						pseudo = "guest";
						mdp = listeUtilisateurs.comptes.get(pseudo);
						System.out.println("Connexion automatique en tant qu'invité");
						connecte = true;
					}
				}
			} else {
				System.out.println("Identifiant inconnu");
				System.out.println("➡️ 1 : Continuer en tant qu'invité\n➡️ 2 : Créer un compte\n➡️ 3 : Réessayer");
				int requete = s.nextInt();
				s.nextLine();
				if (requete == 1) {
					pseudo = "guest";
					mdp = listeUtilisateurs.comptes.get(pseudo);
					System.out.println("Connexion automatique en tant qu'invité");
					connecte = true;
				} else if (requete == 2) {
					System.out.println("Identifiant : " + pseudo);
					System.out.print("Veuillez choisir un mot de passe : ");
					mdp = s.nextLine();
					listeUtilisateurs.comptes.put(pseudo, mdp);
					listeUtilisateurs.estAdmin.put(pseudo, false);
					System.out.println("Féliciations, vous avez maintenant un compte utilisateur !");
					connecte = true;
				}
			}
			System.out.println("------------------------------------------------------------------");
		}

		System.out.println("Identifiant : " + pseudo);
		droits = listeUtilisateurs.estAdmin.get(pseudo);
		System.out.println("Activation du mode administrateur : " + droits);
		System.out.println("------------------------------------------------------------------");
		Thread.sleep(1000);
		System.out.println("\nBienvenue " + pseudo + " ! Quelle maison voulez-vous charger ?");
		System.out.println("➡️ 1 : Barry's House");
		System.out.println("➡️ 2 : Maison Vide\n");
		int requete = s.nextInt();
		s.nextLine();
		if (requete == 1) {
			maison = BarryHouse.creerMaison();
			System.out.println("\nBienvenue dans la maison de Barry !\n");
		} else {
			maison = BarryHouse.creerMaisonVide();
			System.out.println("\nVotre maison de rêve n'attend que vous !\n");
		}
		setPosition(maison.getPieces().get(0));
		Thread.sleep(2000);
	}

	public static void main(String[] args) throws InterruptedException {

		Scanner s = new Scanner(System.in); // Ouverture du scanner

		chargement(s); // Choix de la maison et de l'utilisateur

		boolean stop = false;// Fin de parcours

		while (!stop && !alarme(s)) { // Boucle d'intervention utilisateur
			calculHoraires(); // Calcul heure du jour
			affichageTemperature(); // Affichage temperature pièce
			traitementIntensiteLumineuseNaturelle(); // Traitement ILN
			traitementIntensiteLumineuse(); // Traitement & affichage IL totale
			Thread.sleep(2000);

			System.out.println("\nVous êtes dans : " + getPosition() + "\n");
			System.out.println("Tapez le numéro correspondant à l'action souhaitée ");
			System.out.println(listeUtilisateurs.actionsPossibles(pseudo));

			int requete = s.nextInt();

			/***************************************************************
			 ************************* Déplacement *************************
			 ***************************************************************/
			if (requete == 1) {
				System.out.println("Tapez la commande correspondant à la destination souhaitée");
				List<Piece> piecesAdj = getPosition().getPiecesAdj();
				for (int i = 0; i < piecesAdj.size(); i++) {
					System.out.println("➡️ " + (i + 1) + " : " + piecesAdj.get(i)); // Liste des pièces adjacentes
				}
				int req = s.nextInt() - 1;
				if (req >= 0 && req < piecesAdj.size()) {
					setPosition(piecesAdj.get(req));
					if (intensiteLumineuseNaturelle == 0) {
						System.out.println("Il fait nuit nous allons allumer automatiquement les lumières");
						List<Equipement> lumieres = getLumiere();
						if (lumieres.isEmpty()) {
							System.out.println("Il n'y a pas de lumières");
						}
						for (Equipement lum : lumieres) { // Allumer
							lum.allumer();
						}
					}
				} else {
					System.out.println("Mauvaise commande");
				}
			}

			/***************************************************************
			 ************************* Utilisation *************************
			 ***************************************************************/
			else if (requete == 2) {
				List<Equipement> equip = getPosition().getEquipements();
				System.out.println("\nTapez la commande correspondant à l'équipement souhaité");
				for (int i = 0; i < equip.size(); i++) {
					System.out.println("➡️ " + (i + 1) + " : " + equip.get(i)); // Liste des pièces adjacentes
				}
				System.out.println();
				int req = s.nextInt() - 1;
				boolean exit = false;
				if (req >= 0 && req < equip.size()) {
					Equipement objet = equip.get(req);
					while (!exit) {
						System.out.println("\n Tapez la commande correspondant à l'action souhaitée pour " + objet);
						System.out.println(objet.actionsPossibles() + "\n"); // Liste toutes les actions possibles
						exit = Action.actionEquipement(objet, s); // Commandes d'action dans la class Action
						Thread.sleep(2000); // Delai de 2 secondes
					}
				} else {
					System.out.println("Mauvaise Commande");
				}
				Thread.sleep(3000); // Delai de 3 secondes
			}

			/***************************************************************
			 **************************** Arrêt ****************************
			 ***************************************************************/
			else if (requete == 3) {
				System.out.println("\nAu revoir !");
				stop = true;
			}
			/***************************************************************
			 ************************ Création d'une pièce ******************
			 ***************************************************************/
			else if (requete == 4 && droits) {
				s.nextLine();
				System.out.println("\nTapez le nom que vous voulez donner à votre nouvelle pièce");
				String name = s.nextLine();
				Piece aCreer = new Piece(name);
				maison.ajouterPiece(aCreer);
				maison.sontAdjacents(aCreer, getPosition());
				setPosition(aCreer);
			}
			/***************************************************************
			 ********************* Suppression d'une pièce ******************
			 ***************************************************************/
			else if (requete == 5 && droits) {
				System.out.println(
						"\nTapez la commande correspondant à la pièce dans laquelle vous voulez vous déplacer");
				List<Piece> piecesAdj = getPosition().getPiecesAdj();
				for (int i = 0; i < piecesAdj.size(); i++) {
					System.out.println("➡️ " + (i + 1) + " : " + piecesAdj.get(i)); // Affiche la liste des pièces
					// adjacentes
				}
				int req = s.nextInt() - 1;
				if (req >= 0 && req < piecesAdj.size()) {
					Piece destination = piecesAdj.get(req);
					getPosition().getEquipements().clear(); // Suppression de tous les équipements de la pièce
					for (int i = 0; i < piecesAdj.size(); i++) { // Suppression de toutes les pieces adj
						maison.sontPlusAdjacents(getPosition(), piecesAdj.get(i));
					}
					maison.suppressionPiece(getPosition()); // Suppresion pièce
					System.out.println("\nSuppression effectuée");
					setPosition(destination);
					if (intensiteLumineuseNaturelle == 0) {
						System.out.println("Il fait nuit nous allons allumer automatiquement les lumières");
						List<Equipement> lumieres = getLumiere();
						if (lumieres.isEmpty()) {
							System.out.println("Il n'y a pas de lumières");
						}
						for (Equipement lum : lumieres) { // Allumer
							lum.allumer();
						}
					}
				} else {
					System.out.println("Mauvaise commande");
				}
			}
			/***************************************************************
			 ************************ Création d'un équipement***************
			 ***************************************************************/
			else if (requete == 6 && droits) {
				System.out.println("\nTapez la commande correspondant au type d'équipement à ajouter");
				List<String> possibilites = ListeEquipementConstructibles.getListe();
				for (int i = 0; i < possibilites.size(); i++) {
					System.out.println("➡️ " + (i + 1) + " : " + possibilites.get(i)); // Liste des équipements
				}
				System.out.println();
				int req = s.nextInt();
				s.nextLine();
				if (req >= 0 && req < possibilites.size()) {
					System.out.println("\nTapez le nom de ce nouvel équipement");
					String name = s.nextLine();
					Equipement objet = null;
					switch (req) {
					case 1:
						objet = new Alarme(name);
						break;
					case 2:
						objet = new Balance(name);
						break;
					case 3:
						objet = new Cheminee(name);
						break;
					case 4:
						objet = new Electrolyseur(name);
						break;
					case 5:
						objet = new Enceinte(name);
						break;
					case 6:
						objet = new Frigo(name);
						break;
					case 7:
						objet = new Lumiere(name);
						break;
					case 8:
						objet = new PS5(name);
						break;
					case 9:
						objet = new Radiateur(name);
						break;
					case 10:
						objet = new Thermostat(name);
						break;
					case 11:
						objet = new TV(name);
						break;
					default:
						objet = new Volet(name);
						break;
					}
					getPosition().ajouterEquipement(objet);
					;
					System.out.println("Ajout effectué");
				} else {
					System.out.println("Mauvaise Commande");
				}
				Thread.sleep(3000); // Délai de 3 secondes

			}
			/***************************************************************
			 ********************* Suppression d'un équipement **************
			 ***************************************************************/
			else if (requete == 7 && droits) {
				List<Equipement> equip = getPosition().getEquipements();
				System.out.println("\nTapez la commande correspondant à l'équipement à supprimer");
				for (int i = 0; i < equip.size(); i++) {
					System.out.println("➡️ " + (i + 1) + " : " + equip.get(i)); // Liste des équipements

				}
				System.out.println();
				int req = s.nextInt() - 1;
				if (req >= 0 && req < equip.size()) {
					Equipement objet = equip.get(req);
					getPosition().supprimerEquipement(objet);
					System.out.println("Suppression effectuée");
				} else {
					System.out.println("Mauvaise Commande");
				}
				Thread.sleep(3000); // Délai de 3 secondes
			}
			/***************************************************************
			 ******************** Commande non valide ***********************
			 ***********************************************/
			else {
				System.out.println("Commande non-valide");
			}

		}
		s.close(); // Fermeture du scanner
	}

}
