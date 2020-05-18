package main;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import equipements.Alarme;
import pieces.Buanderie;
import pieces.Bureau;
import pieces.Chambre;
import pieces.Cuisine;
import pieces.Dressing;
import pieces.Escalier;
import pieces.Jardin;
import pieces.Mezzanine;
import pieces.Palier;
import pieces.Piscine;
import pieces.SalleAManger;
import pieces.SalleDeBain;
import pieces.Salon;

public class Piece implements Serializable {

	private static final long serialVersionUID = 2L;
	private String nom;
	private int temperature;
	private int intensiteLumineuse; // Intensité totale
	private LinkedList<Piece> piecesAdj = new LinkedList<Piece>();
	private LinkedList<Equipement> equipements = new LinkedList<Equipement>();
	private static String couleur = Main.getCouleur();

	public Piece(String nom) {
		this.setNom(nom);
		this.temperature = 21;
		this.intensiteLumineuse = 50;
	}

	public Piece(String nom, LinkedList<Equipement> equipements) {
		this.setNom(nom);
		this.setEquipements(equipements);
		this.temperature = 21;
		this.intensiteLumineuse = 50;
	}

	public Piece(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
		this.setNom(nom);
		this.setPiecesAdj(piecesAdj);
		this.setEquipements(equipements);
		this.temperature = 21;
		this.intensiteLumineuse = 0;
	}

	public void ajouterEquipement(Equipement equip) {
		getEquipements().add(equip);
	}

	public void supprimerEquipement(Equipement equip) {
		getEquipements().remove(equip);
	}

	public void ajouterPieceAdj(Piece piece) {
		getPiecesAdj().add(piece);
	}

	public void supprimerPieceAdj(Piece piece) {
		getPiecesAdj().remove(piece);
	}

	public String toString() {
		if (getEquipements().isEmpty()) {
			return getNom() + " qui est sans équipement...";
		}
		return getClass().getSimpleName() + " (" + getNom() + ") qui est équipé(e) de " + afficher(equipements);
	}

	public String afficher(LinkedList<Equipement> a) {
		Iterator<Equipement> it = a.iterator();
		String fin = "\n";
		while (it.hasNext()) {
			Equipement b = it.next();
			fin = fin + "       ->️ " + b.toString() + "\n";
		}
		return fin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		if ((temperature >= 15) && (temperature <= 30)) {
			this.temperature = temperature;
		} else if (temperature > 30) {
			this.temperature = 30;
		} else {
			this.temperature = 15;
		}

	}

	public int getIntensiteLumineuse() {
		return intensiteLumineuse;
	}

	public void setIntensiteLumineuse(int intensiteLumineuse) {
		if ((intensiteLumineuse >= 0) && (intensiteLumineuse <= 100)) {
			this.intensiteLumineuse = intensiteLumineuse;
		} else if (intensiteLumineuse > 100) {
			this.intensiteLumineuse = 100;
		} else {
			this.intensiteLumineuse = 0;
		}
	}

	public LinkedList<Piece> getPiecesAdj() {
		return piecesAdj;
	}

	public void setPiecesAdj(LinkedList<Piece> piecesAdj) {
		this.piecesAdj = piecesAdj;
	}

	public LinkedList<Equipement> getEquipements() {
		return equipements;
	}

	public void setEquipements(LinkedList<Equipement> equipements) {
		this.equipements = equipements;
	}

	public String imagePiece() {
		return ("images/piece.png");
	}

	public Equipement creationEquipement(String name, int nb) {
		return new Alarme(name);
	}

	public LinkedList<String> equipementsAutorises() {
		LinkedList<String> equip = new LinkedList<String>();
		equip.add("Alarme");
		return equip;
	}

	public static LinkedList<String> piecesConstructibles() {
		LinkedList<String> pieces = new LinkedList<String>();
		pieces.add("Buanderie");
		pieces.add("Bureau");
		pieces.add("Chambre");
		pieces.add("Cuisine");
		pieces.add("Dressing");
		pieces.add("Escalier");
		pieces.add("Jardin");
		pieces.add("Mezzanine");
		pieces.add("Palier");
		pieces.add("Piscine");
		pieces.add("Salle à manger");
		pieces.add("Salle de bain");
		pieces.add("Salon");
		return pieces;
	}

	public static void creerPiece(Maison m, Scanner s) {
		System.out.println("\nQuel type de pièce souhaitez-vous créer ?");
		LinkedList<String> pieces = piecesConstructibles();
		for (int i = 0; i < pieces.size(); i++) {
			System.out.println("➡️ " + (i + 1) + " : " + pieces.get(i)); // Liste des equipements
		}
		System.out.println();
		int req = Main.toInt(s.nextLine());
		System.out.println("\nTapez le nom que vous voulez donner à votre nouvelle pièce");
		String name = s.nextLine();
		Piece aCreer = null;
		if (req >= 0 && req <= pieces.size()) {
			switch (req) {
			case 1:
				aCreer = new Buanderie(name);
				break;
			case 2:
				aCreer = new Bureau(name);
				break;
			case 3:
				aCreer = new Chambre(name);
				break;
			case 4:
				aCreer = new Cuisine(name);
				break;
			case 5:
				aCreer = new Dressing(name);
				break;
			case 6:
				aCreer = new Escalier(name);
				break;
			case 7:
				aCreer = new Jardin(name);
				break;
			case 8:
				aCreer = new Mezzanine(name);
				break;
			case 9:
				aCreer = new Palier(name);
				break;
			case 10:
				aCreer = new Piscine(name);
				break;
			case 11:
				aCreer = new SalleAManger(name);
				break;
			case 12:
				aCreer = new SalleDeBain(name);
				break;
			case 13:
				aCreer = new Salon(name);
				break;
			default:
				break;
			}
			m.ajouterPiece(aCreer);
			m.sontAdjacents(aCreer, Main.getPosition());
			Main.setPosition(aCreer);
			System.out.println(aCreer.getNom() + " a bien été créé\n");
		} else {
			System.out.println("Mauvaise Commande");
		}
	}

	public static void supprimerPiece(Maison m, Scanner s) {
		LinkedList<Piece> piecesAdj = Main.getPosition().getPiecesAdj();
		if (piecesAdj.isEmpty()) {
			System.out.println("Il n'y a pas de pièce à supprimer\n");
		} else {
			System.out.println("Tapez la commande correspondant à la destination souhaitée");
			for (int i = 0; i < piecesAdj.size(); i++) {
				System.out.println("➡️ " + (i + 1) + " : " + piecesAdj.get(i)); // Liste des pièces adjacentes
			}
			int req = Main.toInt(s.nextLine()) - 1;
			if (req >= 0 && req < piecesAdj.size()) {
				Piece destination = piecesAdj.get(req);
				Main.getPosition().getEquipements().clear(); // Suppression de tous les équipements de la pièce
				for (int i = 0; i < piecesAdj.size(); i++) { // Suppression de toutes les pieces adj
					m.sontPlusAdjacents(Main.getPosition(), piecesAdj.get(i));
				}
				m.suppressionPiece(Main.getPosition()); // Suppresion pièce
				System.out.println("\nSuppression effectuée");
				Main.setPosition(destination);
				if (Main.getIntensiteLumineuseNaturelle() == 0) {
					LinkedList<Equipement> lumieres = Main.getLumiere();
					if (lumieres.isEmpty()) {
						System.out.println("Il n'y a pas de lumière dans cette pièce\n");
					}
					for (Equipement lum : lumieres) { // Allumer
						lum.allumer();
						System.out.println("Il fait nuit, les lumières s'allument automatiquement dans cette pièce\n");
					}
				}
			} else {
				System.out.println("Mauvaise commande");
			}
		}
	}

}
