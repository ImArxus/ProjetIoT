package pieces;

import java.io.Serializable;
import java.util.LinkedList;

import main.Equipement;
import main.Main;
import main.Piece;
import main.StdDraw;

public class Cuisine extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	public Cuisine(String nom) {
		super(nom, new LinkedList<Piece>(), new LinkedList<Equipement>());
	}

	public Cuisine(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
		super(nom, piecesAdj, equipements);
	}

	@Override
	public LinkedList<String> equipementsAutorises() {
		LinkedList<String> equip = super.equipementsAutorises();
		equip.add("Alexa");
		equip.add("Enceinte");
		equip.add("Lumiere");
		equip.add("Radiateur");
		equip.add("Thermostat");
		equip.add("Ventilateur");
		equip.add("Volet");
		equip.add("Frigo");
		return equip;
	}

	@Override
	public void imagePiece() {
		StdDraw.picture(0.5, 0.5, "images/couleurs" + Main.couleur + ".png");
		StdDraw.picture(0.5, 0.5, "images/cuisine.png");
	}

}
