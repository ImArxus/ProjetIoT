package pieces;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import equipements.Alarme;
import equipements.Electrolyseur;
import equipements.Lumiere;
import main.Equipement;
import main.Main;
import main.Piece;
import main.StdDraw;

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

}
