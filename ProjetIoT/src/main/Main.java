package main;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import equipements.Alarme;
import equipements.Lumiere;

public class Main implements Serializable {

	private static final long serialVersionUID = -5850588170235124346L;

	public static void main(String[] args) throws InterruptedException {
		StdDraw.setCanvasSize(800, 600);
		StdDraw.picture(0.5, 0.5, "images/chargement.png");

		Scanner s = new Scanner(System.in); // Ouverture du scanner

		chargement(s); // Choix de la maison et de l'utilisateur
		boolean stop = false; // Fin de parcours

		LinkedList<Equipement> lumieres = getLumiere();
		if (intensiteLumineuseNaturelle == 0) {
			if (lumieres.isEmpty()) {
				System.out.println("Il n'y a pas de lumière dans cette pièce");
			}
			for (Equipement lum : lumieres) { // Allumer
				System.out.println("Il fait nuit, les lumières s'allument automatiquement dans cette pièce");
				lum.allumer();
			}

		}
		avatars.add("plongeur");
		while (!stop && !alarme(s)) { // Boucle d'intervention utilisateur
			calculHoraires(); // Calcul heure du jour
			affichageTemperature(); // Affichage temperature pièce
			traitementIntensiteLumineuseNaturelle(); // Traitement ILN
			traitementIntensiteLumineuse(); // Traitement & affichage IL totale
			MiseNiveauGraphique();
			Thread.sleep(2000);

			System.out.println("\nVous êtes dans : " + getPosition() + "\n");
			System.out.println("Tapez le numéro correspondant à l'action souhaitée ");
			System.out.println(Equipement.actionsPossibles(pseudo));
			LinkedList<String> liste = new LinkedList<String>();
			liste.add("1 : Changer de pièce");
			liste.add("2 : Utiliser un équipement");
			liste.add("3 : Quitter la simulation");
			liste.add("4 : Sauvegarder ma maison");
			if (droits) {
				liste.add("5 : Créer une pièce");
				liste.add("6 : Supprimer la pièce actuelle");
				liste.add("7 : Créer un équipement");
				liste.add("8 : Supprimer un équipement");
				liste.add("9 : Supprimer tous les équipements de la pièce");
				liste.add("10 : Afficher toutes les pièces et équipements");
				liste.add("11 : Choisir couleur des paramètres");
			}
			int requete = toInt(s.nextLine());

			/***************************************************************
			 ************************* Déplacement *************************
			 ***************************************************************/
			if (requete == 1) {
				LinkedList<Piece> piecesAdj = getPosition().getPiecesAdj();
				if (piecesAdj.isEmpty()) {
					System.out.println("\nIl n'y a pas de pièces dans laquelle se déplacer\n");
				} else {
					System.out.println("Tapez la commande correspondant à la destination souhaitée");
					for (int i = 0; i < piecesAdj.size(); i++) {
						System.out.println("➡️ " + (i + 1) + " : " + piecesAdj.get(i)); // Liste des pièces adjacentes
					}
					int req = toInt(s.nextLine()) - 1;
					if (req >= 0 && req < piecesAdj.size()) {
						setPosition(piecesAdj.get(req));
						if (intensiteLumineuseNaturelle == 0) {
							lumieres = getLumiere();
							if (lumieres.isEmpty()) {
								System.out.println("Il n'y a pas de lumières");
							}
							for (Equipement lum : lumieres) { // Allumer
								System.out.println(
										"Il fait nuit, les lumières s'allument automatiquement dans cette pièce");
								lum.allumer();
							}
						}
					} else {
						System.out.println("Mauvaise commande");
					}
				}
			}

			/***************************************************************
			 ************************* Utilisation *************************
			 ***************************************************************/
			else if (requete == 2) {
				LinkedList<Equipement> equip = getPosition().getEquipements();
				if (equip.isEmpty()) {
					System.out.println("\nIl n'y a pas d'équipement à utiliser ici\n");
				} else {
					System.out.println("\nTapez la commande correspondant à l'équipement souhaité");
					for (int i = 0; i < equip.size(); i++) {
						System.out.println("➡️ " + (i + 1) + " : " + equip.get(i)); // Liste des equipements
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
				LinkedList<Piece> piecesAdj = getPosition().getPiecesAdj();

				if (piecesAdj.isEmpty()) {
					System.out.println("Il n'y a pas de pièce à supprimer");
				} else {
					System.out.println("Tapez la commande correspondant à la destination souhaitée");
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
						System.out.println("\nSuppression effectuée");
						setPosition(destination);
						if (intensiteLumineuseNaturelle == 0) {
							System.out.println("Il fait nuit nous allons allumer automatiquement les lumières");
							lumieres = getLumiere();
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
				System.out.println("Suppression effectuée");
				Thread.sleep(3000); // Delai de 3 secondes
			}
			/***************************************************************
			 ******** Affichage de toutes les pièces et équipements ********
			 ***************************************************************/

			else if (requete == 10 && droits) {
				LinkedList<Piece> pieces = getMaison().getPieces();

				for (int i = 0; i < pieces.size(); i++) {
					System.out.println("➡️ " + (i + 1) + " : " + pieces.get(i)); // Affiche la liste des pièces
				}
				Thread.sleep(3000); // Delai de 3 secondes
			}
			/***************************************************************
			 *************** Choix couleur des parametres*******************
			 ***************************************************************/
			else if (requete == 11 && droits) {
				LinkedList<String> couleurs = new LinkedList<String>();
				couleurs.add("GREEN");
				couleurs.add("ORANGE");
				couleurs.add("RED");
				couleurs.add("WHITE");
				couleurs.add("GRAY");
				couleurs.add("YELLOW");
				couleurs.add("PINK");
				couleurs.add("BLUE");
				System.out.println("Tapez la commande correspondant à la couleur souhaitée");
				for (int i = 0; i < couleurs.size(); i++) {
					System.out.println((i + 1) + " : " + couleurs.get(i));
				}
				int req = Main.toInt(s.nextLine());
				if (req > 0 && req <= couleurs.size()) {
					couleur = couleurs.get(req - 1);
					System.out.println("Nouvelle couleur validée");
				} else {
					System.out.println("Mauvaise commande");
				}
				Thread.sleep(3000); // Delai de 3 secondes
			}
			/***************************************************************
			 ******** Affichage de toutes les pièces et équipements ********
			 ***************************************************************/

			else if (requete == 12 && droits) {
				System.out.println("Tapez la commande correspondant à votre avatar désiré");
				for (int i = 0; i < avatars.size(); i++) {
					System.out.println("➡️ " + (i + 1) + " : " + avatars.get(i)); // Affiche la liste des pièces
				}
				int req = Main.toInt(s.nextLine());
				if (req > 0 && req <= avatars.size()) {
					avatar = avatars.get(req - 1);
					System.out.println("Nouvelle avatar validé");
				} else {
					System.out.println("Mauvaise commande");
					Thread.sleep(3000); // Delai de 3 secondes
				}
			}

			/***************************************************************
			 ********************* Commande non valide *********************
			 ***************************************************************/
			else {
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
	private static List<String> avatars = new LinkedList<String>();
	private static String couleur = "BLUE";
	private static String avatar = "plongeur";
	static Piece salon = new Piece("Salon");

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

	public static LinkedList<Equipement> getLumiere() {
		LinkedList<Equipement> lumieres = new LinkedList<Equipement>();
		LinkedList<Equipement> equip = getPosition().getEquipements();
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
		LinkedList<Equipement> equip = getPosition().getEquipements();
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
		LinkedList<Equipement> lumieresPieces = getLumiere();
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
			System.out.print("Identifiant : ");
			pseudo = s.nextLine();
			if (listeUtilisateurs.comptes.containsKey(pseudo)) {
				System.out.print("Mot de passe : ");
				mdp = s.nextLine();
				if (listeUtilisateurs.comptes.get(pseudo).equals(mdp)) {
					System.out.println("Mot de passe correct");
					connecte = true;
				} else {
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
				System.out.println("Identifiant inconnu");
				System.out.println("➡️ 1 : Continuer en tant qu'invité\n➡️ 2 : Créer un compte\n➡️ 3 : Réessayer");
				int requete = toInt(s.nextLine());
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
					ListeUtilisateurs.estAdmin.put(pseudo, false);
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
		System.out.println("\nBienvenue " + pseudo + " ! Quelle maison voulez-vous charger ?");
		boolean maisonChoisie = false;
		while (!maisonChoisie) {
			System.out.println("➡️ 1 : Barry's House");
			System.out.println("➡️ 2 : Maison Vide");
			System.out.println("➡️ 3 : Charger Maison\n");
			int requete = toInt(s.nextLine());
			if (requete == 1) {
				maison = BarryHouse.creerMaison();
				System.out.println("\nBienvenue dans la maison de Barry !\n");
				maisonChoisie = true;
			} else if (requete == 2) {
				System.out.println("\nQuel nom voulez vous donner à votre maison?");
				String name = s.nextLine();
				maison = new Maison(name, salon);
				System.out.println("\nVotre maison de rêve n'attend que vous !\n");
				System.out.println(maison.toString());
				maisonChoisie = true;
			} else if (requete == 3) {
				maison = Sauvegarde.chargerMAISON();
				if (maison != null) {
					maisonChoisie = true;
				}
			}

		}
		setPosition(getMaison().getPieces().get(0)); // Place l'utilisateur dans la première pièce de la maison choisie
		Thread.sleep(2000);
	}

	public static void choixSauvegarde(Scanner s) throws InterruptedException {
		System.out.println("Voulez vous vraiment sauvegarder votre progression ?\n➡️ 1 : Oui\n➡️ 2 : Non\n");
		int req = toInt(s.nextLine());
		if (req == 1) {
			Sauvegarde.sauvegarder();
			System.out.println("\nSauvegarde effectuée");
		} else {
			System.out.println("\nMaison non sauvegardée");
		}
	}

	public static void MiseNiveauGraphique() {
		Piece a = getPosition();
		StdDraw.clear();
		if (a.getNom() == "Jardin") {
			StdDraw.picture(0.5, 0.5, "images/couleurs/" + couleur + ".png");
			StdDraw.picture(0.5, 0.5, "images/jardin.png");
		} else if (a.getNom() == "Piscine") {
			StdDraw.picture(0.5, 0.5, "images/couleurs/" + couleur + ".png");
			StdDraw.picture(0.5, 0.5, "images/piscine.png");
		} else if (a.getNom() == "escalier") {
			StdDraw.picture(0.5, 0.5, "images/couleurs/" + couleur + ".png");
			StdDraw.picture(0.5, 0.5, "images/escalier.png");
		} else if (a.getNom() == "Cuisine") {
			StdDraw.picture(0.5, 0.5, "images/couleurs/" + couleur + ".png");
			StdDraw.picture(0.5, 0.5, "images/cuisine.png");
		} else {
			StdDraw.picture(0.5, 0.5, "images/couleurs/" + couleur + ".png");
			StdDraw.picture(0.5, 0.5, "images/piece.png");
		}
		StdDraw.text(0.15, 0.96, pseudo);
		StdDraw.text(0.46, 0.96, maison.getNom());
		StdDraw.text(0.78, 0.96, position.getNom());
		StdDraw.text(0.29, 0.9, String.valueOf(position.getTemperature()));
		StdDraw.text(0.76, 0.9, String.valueOf(position.getIntensiteLumineuse()));
		StdDraw.text(0.9, 0.9, String.valueOf(heure) + "h");
		miseNiveauGraphiqueObjets();
		StdDraw.picture(0.5, 0.2, "images/avatar/" + avatar + ".png");
	}

	public static void choixCouleurLegende() {
		switch (couleur) {
		case "GREEN":
			StdDraw.setPenColor(StdDraw.GREEN);
			break;
		case "PINK":
			StdDraw.setPenColor(StdDraw.PINK);
			break;
		case "GRAY":
			StdDraw.setPenColor(StdDraw.GRAY);
			break;
		case "RED":
			StdDraw.setPenColor(StdDraw.RED);
			break;
		case "ORANGE":
			StdDraw.setPenColor(StdDraw.ORANGE);
			break;
		case "YELLOW":
			StdDraw.setPenColor(StdDraw.YELLOW);
			break;
		case "WHITE":
			StdDraw.setPenColor(StdDraw.WHITE);
			break;
		default:
			StdDraw.setPenColor(StdDraw.BLUE);
			break;
		}
	}

	public static void miseNiveauGraphiqueObjets() {
		choixCouleurLegende();
		String nameClass;
		LinkedList<Equipement> equip = getPosition().getEquipements();
		for (int i = 0; i < equip.size(); i++) {
			nameClass = equip.get(i).getClass().getName();
			StdDraw.picture(equip.get(i).getPositionHorizontale(), equip.get(i).getPositionVerticale(),
					"images/objets/" + nameClass + ".png");
			StdDraw.text(equip.get(i).getPositionHorizontale(), equip.get(i).getPositionVerticale(),
					equip.get(i).getNom());
		}
		StdDraw.setPenColor(StdDraw.BLACK);
	}
}
