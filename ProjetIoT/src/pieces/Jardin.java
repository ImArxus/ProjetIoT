package pieces;

import java.io.Serializable;
import java.util.LinkedList;

import main.Equipement;
import main.Main;
import main.Piece;
import main.StdDraw;

public class Jardin extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	public Jardin(String nom) {
		super(nom, new LinkedList<Piece>(), new LinkedList<Equipement>());
	}

	public Jardin(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
		super(nom, piecesAdj, equipements);
	}

	@Override
	public LinkedList<String> equipementsAutorises() {
		LinkedList<String> equip = super.equipementsAutorises();
		equip.add("Enceinte");
		equip.add("Lumiere");
		equip.add("Volet");
		return equip;
	}

	@Override
	public void imagePiece() {
		StdDraw.picture(0.5, 0.5, "images/couleurs/" + Main.couleur + ".png");
		StdDraw.picture(0.5, 0.5, "images/jardin.png");
	}

}
