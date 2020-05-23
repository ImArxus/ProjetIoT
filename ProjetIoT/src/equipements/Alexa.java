package equipements;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Equipement;
import main.Main;
import main.Piece;

public class Alexa extends Equipement implements Serializable {

	private static final long serialVersionUID = 8308711409297886419L;
	private static String pseudo;

	public Alexa(String nom) {
		super(nom);
		pseudo = Main.getPseudo();
		this.setPositionHorizontale(0.23);
		this.setPositionVerticale(0.46);
	}

	public Alexa(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale) {
		super(nom, etatCourant, positionHorizontale, positionVerticale);
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
			List<Piece> pieces = Main.getMaison().getPieces();
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
			List<Piece> pieces = Main.getMaison().getPieces();
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

	public ImageView afficher() {
		ImageView imageView = new ImageView();
		imageView.setImage(new Image("/images/objets/equipements.Alexa.png"));
		imageView.setTranslateY(25);
		imageView.setTranslateX(-200);
		return imageView;
	}
	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(200);
		but.setTranslateY(450);
		return but;
	}
}