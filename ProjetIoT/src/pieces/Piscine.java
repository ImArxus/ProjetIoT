package pieces;

import java.io.Serializable;
import java.util.LinkedList;

import equipements.Alarme;
import equipements.Electrolyseur;
import equipements.Lumiere;
import javafx.scene.control.Button;
import main.Equipement;
import main.Piece;

public class Piscine extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	public Piscine(String nom) {
		super(nom, new LinkedList<Piece>(), new LinkedList<Equipement>());
	}

	public Piscine(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
		super(nom, piecesAdj, equipements);
	}

	@Override
	public LinkedList<String> equipementsAutorises() {
		LinkedList<String> equip = super.equipementsAutorises();
		equip.add("Electrolyseur");
		equip.add("Lumiere");
		return equip;
	}

	@Override
	public Equipement creationEquipement(String name, int nb) {
		switch (nb) {
		case 1:
			return new Alarme(name);
		case 2:
			return new Electrolyseur(name);
		default:
			return new Lumiere(name);
		}
	}

	@Override
	public String imagePiece() {
		return ("images/piscine.png");
	}
	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(700);
		but.setTranslateY(400);
		return but;
	}

}
