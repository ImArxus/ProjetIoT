package equipements;

import java.util.List;
import java.util.Scanner;

import main.Equipement;
import main.Main;
import main.Maison;
import main.Piece;

public class Alexa extends Equipement {

	private static String pseudo;

	public Alexa(String nom) {
		super(nom, false);
		pseudo = Main.getPseudo();
	}

	public Alexa(String nom, boolean etatCourant) {
		super(nom, etatCourant);
		pseudo = Main.getPseudo();
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Alexa je souhaite connaitre l'heure\n➡️ 5 : Alexa je souhaite connaitre la temperature d'une pièce\n➡️ 6 : Alexa je souhaite connaitre les équipements d'une pièce\n";
	}

	public void reponseHeure() {
		if (super.isEtatCourant()) {
			System.out.println(" Bonjour " + pseudo + ", il est actuellement " + Main.getHeure() + "h!");
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas l'utiliser");
		}
	}

	public void reponseTemperature() {
		Scanner s = new Scanner(System.in); // Ouverture du scanner
		if (super.isEtatCourant()) {
			System.out.println(" Bonjour " + pseudo + ", de quelle pièce s'agit-il?");
			List<Piece> pieces = Maison.getPieces();
			for (int i = 0; i < pieces.size(); i++) {
				System.out.println("➡️ " + (i + 1) + " : " + pieces.get(i).getNom()); // Affiche la liste des pièces
			}
			int req = s.nextInt() - 1;
			if (req >= 0 && req < pieces.size()) {
				System.out.println(" Bonjour " + pseudo + ", la temperature de la pièce " + pieces.get(req).getNom()
						+ " est de " + pieces.get(req).getTemperature() + "°C");
			} else {
				System.out.println("Mauvaise commande");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas l'utiliser");
		}
		s.close();
	}

	public void reponseEquipement() {
		Scanner s = new Scanner(System.in); // Ouverture du scanner
		if (super.isEtatCourant()) {
			System.out.println(" Bonjour " + pseudo + ", de quelle pièce s'agit-il?");
			List<Piece> pieces = Maison.getPieces();
			for (int i = 0; i < pieces.size(); i++) {
				System.out.println("➡️ " + (i + 1) + " : " + pieces.get(i).getNom()); // Affiche la liste des pièces
			}
			int req = s.nextInt() - 1;
			if (req >= 0 && req < pieces.size()) {
				System.out.println(" Bonjour " + pseudo + ", les équipements présent dans " + pieces.get(req).getNom()
						+ " sont " + pieces.get(req).getEquipements());
			} else {
				System.out.println("Mauvaise commande");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas l'utiliser");
		}
		s.close();
	}
}