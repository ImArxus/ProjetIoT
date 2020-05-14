package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import equipements.Alarme;
import equipements.Lumiere;

public class Main {

	@SuppressWarnings("null")
	public static void main(String[] args) throws InterruptedException {
		StdDraw.setCanvasSize(800,600);
		StdDraw.picture(0.5, 0.5,"images/accueilBH.png");
/*
		StdDraw.rectangle(0.21, 0.91, 0.2, 0.08);
		StdDraw.text(0.085, 0.95, "Identifiant :");
		StdDraw.text(0.075, 0.90, "Mot de passe :");
		StdDraw.rectangle(0.25, 0.95, 0.1, 0.02);
		StdDraw.rectangle(0.25, 0.90, 0.1, 0.02);	
		StdDraw.text(0.24, 0.95, "Dans la console");
		StdDraw.text(0.24, 0.90, "Dans la console");*/

		
		Scanner s = new Scanner(System.in); // Ouverture du scanner

		chargement(s); // Choix de la maison et de l'utilisateur
		boolean stop = false; // Fin de parcours

		List<Equipement> lumieres = getLumiere();
		if (intensiteLumineuseNaturelle == 0) {
			if (lumieres.isEmpty()) {
				System.out.println("Il n'y a pas de lumière dans cette pièce");
			}
			for (Equipement lum : lumieres) { // Allumer
				System.out.println("Il fait nuit, les lumières s'allument automatiquement dans cette pièce");
				lum.allumer();
			}
		}

		while (!stop && !alarme(s)) { // Boucle d'intervention utilisateur
			MiseNiveauGraphique();
			calculHoraires(); // Calcul heure du jour
			affichageTemperature(); // Affichage temperature pièce
			traitementIntensiteLumineuseNaturelle(); // Traitement ILN
			traitementIntensiteLumineuse(); // Traitement & affichage IL totale
			Thread.sleep(2000);

			System.out.println("\nVous êtes dans : " + getPosition() + "\n");
			System.out.println("Tapez le numéro correspondant à l'action souhaitée ");
			System.out.println(Equipement.actionsPossibles(pseudo));
			List<String> liste = new LinkedList<String>();
			liste.add("1 : Changer de pièce");
			liste.add("2 : Utiliser un équipement");
			liste.add("3 : Quitter la simulation");
			liste.add("4 : Sauvegarder ma maison");
			if(droits) {
				liste.add("5 : Créer une pièce");
				liste.add("6 : Supprimer la pièce actuelle");
				liste.add("7 : Créer un équipement");
				liste.add("8 : Supprimer un équipement");
				liste.add("9 : Supprimer tous les équipements de la pièce");
				liste.add("10 : Afficher toutes les pièces et équipements");
			}
			afficherMessageChoix(liste);
			int requete = toInt(s.nextLine());

			/***************************************************************
			 ************************* Déplacement *************************
			 ***************************************************************/
			if (requete == 1) {
				List<Piece> piecesAdj = getPosition().getPiecesAdj();
				if (piecesAdj.isEmpty()) {
					afficherMessageBoucle("Il n'y a pas de pièces dans laquelle se déplacer");
					System.out.println("\nIl n'y a pas de pièces dans laquelle se déplacer\n");
				} else {
					List<String> listeChoix = new LinkedList<String>();
					System.out.println("Tapez la commande correspondant à la destination souhaitée");
					for (int i = 0; i < piecesAdj.size(); i++) {
						System.out.println("➡️ " + (i + 1) + " : " + piecesAdj.get(i)); // Liste des pièces adjacentes
						listeChoix.add((i+1)+" : "+piecesAdj.get(i).getNom());
					}
					afficherMessageChoix(listeChoix);
					int req = toInt(s.nextLine()) - 1;
					if (req >= 0 && req < piecesAdj.size()) {
						setPosition(piecesAdj.get(req));
						if (intensiteLumineuseNaturelle == 0) {
							lumieres = getLumiere();
							if (lumieres.isEmpty()) {
								afficherMessageBoucle("Il n'y a pas de lumières");
								System.out.println("Il n'y a pas de lumières");
							}
							for (Equipement lum : lumieres) { // Allumer
								afficherMessageBoucle("Allumage automatique des lumières");
								System.out.println(
										"Il fait nuit, les lumières s'allument automatiquement dans cette pièce");
								lum.allumer();
							}
						}
					} else {
						afficherMessageBoucle("Mauvaise commande");
						System.out.println("Mauvaise commande");
					}
				}
			}

			/***************************************************************
			 ************************* Utilisation *************************
			 ***************************************************************/
			else if (requete == 2) {
				List<Equipement> equip = getPosition().getEquipements();
				if (equip.isEmpty()) {
					afficherMessageBoucle("Il n'y a pas d'équipement à utiliser ici");
					System.out.println("\nIl n'y a pas d'équipement à utiliser ici\n");
				} else {
					System.out.println("\nTapez la commande correspondant à l'équipement souhaité");
					for (int i = 0; i < equip.size(); i++) {
						System.out.println("➡️ " + (i + 1) + " : " + equip.get(i)); // Liste des pièces adjacentes
					}
					System.out.println();
					int req = toInt(s.nextLine()) - 1;
					boolean exit = false;
					if (req >= 0 && req < equip.size()) {
						Equipement objet = equip.get(req);
						while (!exit) {
							System.out.println("\nTapez la commande correspondant à l'action souhaitée pour " + objet);
							System.out.println(objet.actionsPossibles() + "\n"); // Liste toutes les actions possibles
							exit = Action.actionEquipement(objet, s); // Commandes d'action dans la class Action
							Thread.sleep(2000); // Delai de 2 secondes
						}
					} else {
						afficherMessageBoucle("Mauvaise Commande");
						System.out.println("Mauvaise Commande");
					}
					Thread.sleep(3000); // Delai de 3 secondes
				}
			}

			/***************************************************************
			 **************************** Arrêt ****************************
			 ***************************************************************/
			else if (requete == 3) {
				choixSauvegarde(s);
				afficherMessageBoucle("Au revoir !");
				System.out.println("\nAu revoir !");
				stop = true;
			}
			/***************************************************************
			 ************************** Sauvegarde *************************
			 ***************************************************************/
			else if (requete == 4) {
				choixSauvegarde(s);
			}
			/***************************************************************
			 ********************* Création d'une pièce ********************
			 ***************************************************************/
			else if (requete == 5 && droits) {
				afficherMessageBoucle("Tapez le nom que vous voulez donner à votre nouvelle pièce");
				System.out.println("\nTapez le nom que vous voulez donner à votre nouvelle pièce");
				String name = s.nextLine();
				Piece aCreer = new Piece(name);
				maison.ajouterPiece(aCreer);
				maison.sontAdjacents(aCreer, getPosition());
				setPosition(aCreer);
			}

			/***************************************************************
			 ******************* Suppression d'une pièce *******************
			 ***************************************************************/
			else if (requete == 6 && droits) {
				List<Piece> piecesAdj = getPosition().getPiecesAdj();
				if (piecesAdj.isEmpty()) {
					afficherMessageBoucle("Il n'y a pas de pièce à supprimer");
					System.out.println("Il n'y a pas de pièce à supprimer");
				} else {
					System.out.println(
							"\nTapez la commande correspondant à la pièce dans laquelle vous voulez vous déplacer");
					for (int i = 0; i < piecesAdj.size(); i++) {
						System.out.println("➡️ " + (i + 1) + " : " + piecesAdj.get(i)); // Liste des pièces adjacentes
					}
					int req = toInt(s.nextLine()) - 1;
					if (req >= 0 && req < piecesAdj.size()) {
						Piece destination = piecesAdj.get(req);
						getPosition().getEquipements().clear(); // Suppression de tous les équipements de la pièce
						for (int i = 0; i < piecesAdj.size(); i++) { // Suppression de toutes les pieces adj
							maison.sontPlusAdjacents(getPosition(), piecesAdj.get(i));
						}
						maison.suppressionPiece(getPosition()); // Suppresion pièce
						afficherMessageBoucle("Suppression effectuée");
						System.out.println("\nSuppression effectuée");
						setPosition(destination);
						if (intensiteLumineuseNaturelle == 0) {
							System.out.println("Il fait nuit nous allons allumer automatiquement les lumières");
							afficherMessageBoucle("Allumage automatique");
							lumieres = getLumiere();
							if (lumieres.isEmpty()) {
								afficherMessageBoucle("Il n'y a pas de lumières");
								System.out.println("Il n'y a pas de lumières");
							}
							for (Equipement lum : lumieres) { // Allumer
								lum.allumer();
							}
						}
					} else {
						afficherMessageBoucle("Mauvaise commande");
						System.out.println("Mauvaise commande");
					}
				}
			}

			/***************************************************************
			 ******************** Création d'un équipement *****************
			 ***************************************************************/
			else if (requete == 7 && droits) {
				Equipement.creerEquipement(getPosition(), s);
				Thread.sleep(3000); // Délai de 3 secondes
			}

			/***************************************************************
			 ***************** Suppression d'un équipement *****************
			 ***************************************************************/
			else if (requete == 8 && droits) {
				Equipement.supprimerEquipement(getPosition(), s);
				Thread.sleep(3000); // Délai de 3 secondes
			}
			/***************************************************************
			 ******* Suppression de tous les equipements de la pièce *******
			 ***************************************************************/
			else if (requete == 9 && droits) {
				getPosition().getEquipements().clear();// suppression de tous les équipements de la pièce
				afficherMessageBoucle("Suppression effectuée");
				System.out.println("Suppression effectuée");
				Thread.sleep(3000); // Delai de 3 secondes
			}
			/***************************************************************
			 ******** Affichage de toutes les pièces et équipements ********
			 ***************************************************************/
			else if (requete == 10 && droits) {
				List<Piece> pieces = Maison.getPieces();
				for (int i = 0; i < pieces.size(); i++) {
					System.out.println("➡️ " + (i + 1) + " : " + pieces.get(i)); // Affiche la liste des pièces
				}
				Thread.sleep(3000); // Delai de 3 secondes
			}
			/***************************************************************
			 ********************* Commande non valide *********************
			 ***************************************************************/
			else {
				afficherMessageBoucle("Mauvaise commande");
				System.out.println("Cette commande n'est pas disponible\n");
			}

		}
		s.close(); // Fermeture du scanner
	}

	private static Maison maison; // Maison définie dans la classe BarryHouse
	private static Piece position; // Position initiale dans la premère pièce ajoutée
	private static String pseudo;
	private static String mdp;
	private static Boolean droits;
	private static int intensiteLumineuseNaturelle = 0;
	private static int heure = (int) (Math.random() * 24);
	private static ListeUtilisateurs listeUtilisateurs = new ListeUtilisateurs();

	public static Maison getMaison() {
		return maison;
	}

	public static String getPseudo() {
		return pseudo;
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

	public static List<Equipement> getLumiere() {
		List<Equipement> lumieres = new LinkedList<Equipement>();
		List<Equipement> equip = getPosition().getEquipements();
		Iterator<Equipement> it = equip.iterator();
		while (it.hasNext()) {
			Equipement e = it.next();
			String tmp = e.getClass().getSimpleName();
			if (tmp.equals("Lumiere")) {
				lumieres.add(e);
			}
		}
		return lumieres;
	}

	public static int toInt(String s) {
		int res;
		try {
			res = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			System.out.println("La commande entrée n'est pas un entier\n");
			res = -1;
		}
		return res;
	}

	public static boolean alarme(Scanner s) {
		List<Equipement> equip = getPosition().getEquipements();
		for (int i = 0; i < equip.size(); i++) {
			Equipement objet = equip.get(i);
			if (objet instanceof Alarme) {
				if (((Alarme) objet).isEtatCourant()) {
					System.out.println("Désactiver l'alarme (oui/non) ?\n➡️ 1 : Oui\n➡️ 2 : Non\n");
					int requete = toInt(s.nextLine());
					if (requete == 1) {
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
				+ position.getIntensiteLumineuse() + "% (dont = " + intensiteLumineuseNaturelle + "% naturelle et "
				+ sommeILobjets + "% artificielle)");
	}

	public static void chargement(Scanner s) throws InterruptedException {

		/***************************************************************
		 ************************** Connexion **************************
		 ***************************************************************/
		boolean connecte = false;
		while (!connecte) {
			afficherMessageChargement("Veuillez saisir votre identifiant dans la console");
			System.out.print("Identifiant : ");
			pseudo = s.nextLine();
			if (listeUtilisateurs.comptes.containsKey(pseudo)) {
				afficherMessageChargement("Veuillez saisir votre mot de passe dans la console");
				System.out.print("Mot de passe : ");
				mdp = s.nextLine();
				if (listeUtilisateurs.comptes.get(pseudo).equals(mdp)) {
					System.out.println("Mot de passe correct");
					connecte = true;
				} else {
					afficherMessageChargement("Mot de passe incorrect, choisissez une option");
					System.out.println("Mot de passe incorrect");
					System.out.println("➡️ 1 : Continuer en tant qu'invité\n➡️ 2 : Réessayer");
					int requete = toInt(s.nextLine());
					if (requete == 1) {
						pseudo = "guest";
						mdp = listeUtilisateurs.comptes.get(pseudo);
						System.out.println("Connexion automatique en tant qu'invité");
						connecte = true;
					}
				}
			} else {
				afficherMessageChargement("Identifiant inconnu, choisissez une option");
				System.out.println("Identifiant inconnu");
				System.out.println("➡️ 1 : Continuer en tant qu'invité\n➡️ 2 : Créer un compte\n➡️ 3 : Réessayer");
				int requete = toInt(s.nextLine());
				if (requete == 1) {
					pseudo = "guest";
					mdp = listeUtilisateurs.comptes.get(pseudo);
					afficherMessageChargement("Connexion automatique en tant qu'invité");
					System.out.println("Connexion automatique en tant qu'invité");
					connecte = true;
				} else if (requete == 2) {
					System.out.println("Identifiant : " + pseudo);
					afficherMessageChargement("Veuillez choisir un mot de passe");
					System.out.print("Veuillez choisir un mot de passe : ");
					mdp = s.nextLine();
					listeUtilisateurs.comptes.put(pseudo, mdp);
					ListeUtilisateurs.estAdmin.put(pseudo, false);
					afficherMessageChargement("Féliciations, vous avez maintenant un compte utilisateur !");
					System.out.println("Féliciations, vous avez maintenant un compte utilisateur !");
					connecte = true;
				}
			}
			System.out.println("------------------------------------------------------------------");
		}

		/***************************************************************
		 ********************* Choix de la maison **********************
		 ***************************************************************/
		System.out.println("Identifiant : " + pseudo);
		droits = ListeUtilisateurs.getAdmin().get(pseudo);
		System.out.println("Activation du mode administrateur : " + droits);
		System.out.println("------------------------------------------------------------------");
		Thread.sleep(1000);
		afficherMessageChargement("Bienvenue " + pseudo + " ! Quelle maison voulez-vous charger ?");
		System.out.println("\nBienvenue " + pseudo + " ! Quelle maison voulez-vous charger ?");
		boolean maisonChoisie = false;
		while (!maisonChoisie) {
			System.out.println("➡️ 1 : Barry's House");
			System.out.println("➡️ 2 : Maison Vide\n");
			int requete = toInt(s.nextLine());
			if (requete == 1) {
				maison = BarryHouse.creerMaison();
				afficherMessageChargement("Bienvenue dans la maison de Barry !");
				System.out.println("\nBienvenue dans la maison de Barry !\n");
				maisonChoisie = true;
			} else if (requete == 2) {
				maison = BarryHouse.creerMaisonVide();
				afficherMessageChargement("Votre maison de rêve n'attend que vous !");
				System.out.println("\nVotre maison de rêve n'attend que vous !\n");
				maisonChoisie = true;
			}
		}
		setPosition(Maison.getPieces().get(0)); // Place l'utilisateur dans la première pièce de la maison choisie
		Thread.sleep(2000);
	}

	public static void choixSauvegarde(Scanner s) throws InterruptedException {
		System.out.println("Voulez vous vraiment sauvegarder votre progression ?\n➡️ 1 : Oui\n➡️ 2 : Non\n");
		int req = toInt(s.nextLine());
		if (req == 1) {
			Sauvegarde.sauvegarder(maison.getNom());
			System.out.println("\nSauvegarde effectuée");
		} else {
			System.out.println("\nMaison non sauvegardée");
		}
	}
	public static void MiseNiveauGraphique() {
		StdDraw.clear();
		StdDraw.picture(0.5, 0.5,"images/accueilBH.png");
		StdDraw.text(0.13, 0.97, pseudo);
		StdDraw.text(0.58, 0.97, position.getNom());
		StdDraw.text(0.95, 0.97, String.valueOf(heure+1)+"h");
	}
	public static void afficherMessageChargement(String message) {
		StdDraw.clear();
		StdDraw.picture(0.5, 0.5,"images/accueilBH.png");
		StdDraw.text(0.5, 0.5, message);
	}
	public static void afficherMessageBoucle(String message) {
		StdDraw.clear();
		StdDraw.picture(0.5, 0.5,"images/accueilBH.png");
		StdDraw.text(0.5, 0.5, message);
		StdDraw.text(0.13, 0.97, pseudo);
		StdDraw.text(0.58, 0.97, position.getNom());
		StdDraw.text(0.95, 0.97, String.valueOf(heure+1)+"h");
	}
	public static void afficherMessageChoix(List<String> liste) {
		StdDraw.clear();
		StdDraw.picture(0.5, 0.5,"images/accueilBH.png");
		StdDraw.text(0.13, 0.97, pseudo);
		StdDraw.text(0.58, 0.97, position.getNom());
		StdDraw.text(0.95, 0.97, String.valueOf(heure+1)+"h");
		int i=0; 
		Iterator<String> iter = liste.iterator(); 
		while (iter.hasNext()) { 
		    String element = iter.next(); 
		    double v=0.8-(i*0.05);
		    StdDraw.text(0.5,v, element);
		    i++; 
		} 
	}
}
