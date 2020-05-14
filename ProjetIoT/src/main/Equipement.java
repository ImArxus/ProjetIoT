package main;

import java.util.List;
import java.util.Scanner;

import equipements.Alarme;
import equipements.Alexa;
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
import equipements.Ventilateur;
import equipements.Volet;

public class Equipement {

	protected boolean etatCourant;
	protected String nom;

	protected Equipement(String nom) {
		setNom(nom);
		setEtatCourant(false);
	}

	protected Equipement(String nom, boolean etatCourant) {
		this.setNom(nom);
		this.setEtatCourant(etatCourant);
	}

	public String actionsPossibles() {
		return "➡️ 1 : Quitter\n➡️ 2 : Allumer\n➡️ 3 : Eteindre";
	}

	public static String actionsPossibles(String pseudo) {
		String reponse = "➡️ 1 : Changer de pièce\n➡️ 2 : Utiliser d'un équipement\n➡️ 3 : Quitter la simulation\n➡️ 4 : Sauvegarder ma maison\n";
		if (ListeUtilisateurs.getAdmin().get(pseudo)) {
			reponse += "➡️ 5 : Créer d'une pièce\n➡️ 6 : Supprimer de la pièce actuelle\n➡️ 7 : Créer d'un équipement\n➡️ 8 : Supprimer d'un équipement\n➡️ 9 : Supprimer de tous les équipements de la pièce\n➡️ 10 : Afficher de toutes les pièces et équipements\n";
		}
		return reponse;
	}

	public static void creerEquipement(Piece p, Scanner s) {
		System.out.println("\nTapez la commande correspondant au type d'équipement à ajouter");
		List<String> possibilites = ListeEquipementConstructibles.getListe();
		for (int i = 0; i < possibilites.size(); i++) {
			System.out.println("➡️ " + (i + 1) + " : " + possibilites.get(i)); // Liste des équipements
		}
		System.out.println();
		int req = Main.toInt(s.nextLine());
		if (req >= 0 && req <= possibilites.size()) {
			System.out.println("\nTapez le nom de ce nouvel équipement");
			String name = s.nextLine();
			Equipement objet = null;
			switch (req) {
			case 1:
				objet = new Alarme(name);
				break;
			case 2:
				objet = new Alexa(name);
				break;
			case 3:
				objet = new Balance(name);
				break;
			case 4:
				objet = new Cheminee(name);
				break;
			case 5:
				objet = new Electrolyseur(name);
				break;
			case 6:
				objet = new Enceinte(name);
				break;
			case 7:
				objet = new Frigo(name);
				break;
			case 8:
				objet = new Lumiere(name);
				break;
			case 9:
				objet = new PS5(name);
				break;
			case 10:
				objet = new Radiateur(name);
				break;
			case 11:
				objet = new Thermostat(name);
				break;
			case 12:
				objet = new TV(name);
				break;
			case 13:
				objet = new Ventilateur(name);
				break;
			case 14:
				objet = new Volet(name);
				break;
			default:
				break;
			}
			p.ajouterEquipement(objet);
			System.out.println("Ajout effectué");
		} else {
			System.out.println("Mauvaise Commande");
		}
	}

	public static void supprimerEquipement(Piece p, Scanner s) {
		List<Equipement> equip = p.getEquipements();
		if (equip.isEmpty()) {
			System.out.println("Il n'y a pas d'équipement à supprimer");
		} else {
			System.out.println("\nTapez la commande correspondant à l'équipement à supprimer");
			for (int i = 0; i < equip.size(); i++) {
				System.out.println("➡️ " + (i + 1) + " : " + equip.get(i)); // Liste des équipements
			}
			System.out.println();
			int req = Main.toInt(s.nextLine()) - 1;
			if (req >= 0 && req < equip.size()) {
				Equipement objet = equip.get(req);
				p.supprimerEquipement(objet);
				System.out.println("Suppression effectuée");
			} else {
				System.out.println("Mauvaise Commande");
			}
		}
	}

	public String toString() {
		String etat = "éteint(e)";
		if (isEtatCourant()) {
			etat = "allumé(e)";
		}
		return (getClass().getSimpleName() + " (" + getNom() + ", " + etat + ")");
	}

	public void setEtatCourant(boolean etatCourant) {
		this.etatCourant = etatCourant;
	}

	public boolean isEtatCourant() {
		return etatCourant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void allumer() {
		if (!isEtatCourant()) {
			setEtatCourant(true);
		}
	}

	public void eteindre() {
		if (isEtatCourant()) {
			setEtatCourant(false);
		}
	}

}
