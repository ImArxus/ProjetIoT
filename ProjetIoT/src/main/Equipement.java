package main;

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
		return "➡️ 1 :Quitter\n➡️ 2 : Allumer\n➡️ 3 : Eteindre";
	}

	public static void creerEquipement(Piece p, Scanner s) {
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
			p.ajouterEquipement(objet);
			System.out.println("Ajout effectué");
		} else {
			System.out.println("Mauvaise Commande");
		}
	}

	public static void supprimerEquipement(Piece p, Scanner s) {
		List<Equipement> equip = p.getEquipements();
		System.out.println("\nTapez la commande correspondant à l'équipement à supprimer");
		for (int i = 0; i < equip.size(); i++) {
			System.out.println("➡️ " + (i + 1) + " : " + equip.get(i)); // Liste des équipements
		}
		System.out.println();
		int req = s.nextInt() - 1;
		if (req >= 0 && req < equip.size()) {
			Equipement objet = equip.get(req);
			p.supprimerEquipement(objet);
			System.out.println("Suppression effectuée");
		} else {
			System.out.println("Mauvaise Commande");
		}
	}

	public String toString() {
		String etat = "éteint(e)";
		if (isEtatCourant()) {
			etat = "allumé(e)";
		}
		return getNom() + " (" + etat + ")";
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
