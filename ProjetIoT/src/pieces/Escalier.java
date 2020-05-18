package pieces;

import java.io.Serializable;
import java.util.LinkedList;

import equipements.Alarme;
import equipements.Lumiere;
import main.Equipement;
import main.Main;
import main.Piece;
import main.StdDraw;

public class Escalier extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	public Escalier(String nom) {
		super(nom, new LinkedList<Piece>(), new LinkedList<Equipement>());
	}

	public Escalier(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
		super(nom, piecesAdj, equipements);
	}

	@Override
	public LinkedList<String> equipementsAutorises() {
		LinkedList<String> equip = new LinkedList<String>();
		equip.add("Lumiere");
		return equip;
	}

	@Override
	public Equipement creationEquipement(String name, int nb) {
		switch (nb) {
		case 1:
			return new Alarme(name);
		default:
			return new Lumiere(name);
		}
	}

}
