package main;

import java.io.Serializable;
import java.util.LinkedList;

import java.util.Scanner;

public class Equipement implements Serializable {

	private static final long serialVersionUID = 3L;
	protected boolean etatCourant;
	protected String nom;
	protected double positionHorizontale;
	protected double positionVerticale;

	protected Equipement(String nom) {
		setNom(nom);
		setEtatCourant(false);
		positionHorizontale = Math.random();
		positionVerticale = (Math.random() * 0.7) + 0.1;
	}

	protected Equipement(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale) {
		this.setNom(nom);
		this.setEtatCourant(etatCourant);
		this.setPositionHorizontale(positionHorizontale);
		this.setPositionVerticale(positionVerticale);
	}

	public String actionsPossibles() {
		return "➡️ 1 : Quitter\n➡️ 2 : Allumer\n➡️ 3 : Eteindre";
	}

	public static String actionsPossibles(String pseudo) {
		String reponse = "➡️ 1 : Changer de pièce\n➡️ 2 : Utiliser un équipement\n➡️ 3 : Quitter la simulation\n➡️ 4 : Sauvegarder ma maison\n";
		if (ListeUtilisateurs.getAdmin().get(pseudo)) {
			reponse += "➡️ 5 : Créer une pièce\n➡️ 6 : Supprimer la pièce actuelle\n➡️ 7 : Créer un équipement\n➡️ 8 : Supprimer un équipement\n➡️ 9 : Supprimer tous les équipements de la pièce\n➡️ 10 : Afficher toutes les pièces et équipements\n➡️ 11 : Choisir couleur des paramètres\n➡️ 12 : Changer son avatar\n";
		}
		return reponse;
	}

	public static void creerEquipement(Piece p, Scanner s) {
		System.out.println("\nTapez la commande correspondant au type d'équipement à ajouter");
		LinkedList<String> possibilites = p.equipementsAutorises();
		for (int i = 0; i < possibilites.size(); i++) {
			System.out.println("➡️ " + (i + 1) + " : " + possibilites.get(i)); // Liste des équipements
		}
		System.out.println();
		int req = Main.toInt(s.nextLine());
		if (req >= 0 && req <= possibilites.size()) {
			System.out.println("\nTapez le nom de ce nouvel équipement");
			String name = s.nextLine();
			Equipement objet = p.creationEquipement(name, req);
			p.ajouterEquipement(objet);
			System.out.println("Ajout effectué");
		} else {
			System.out.println("Mauvaise Commande");
		}
	}

	public static void supprimerEquipement(Piece p, Scanner s) {
		LinkedList<Equipement> equip = p.getEquipements();
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

	public double getPositionHorizontale() {
		return positionHorizontale;
	}

	public double getPositionVerticale() {
		return positionVerticale;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPositionHorizontale(double positionHorizontale) {
		this.positionHorizontale = positionHorizontale;
	}

	public void setPositionVerticale(double positionVerticale) {
		this.positionVerticale = positionVerticale;
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
