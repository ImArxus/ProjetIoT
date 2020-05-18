package pieces;

import java.io.Serializable;
import java.util.LinkedList;

import equipements.Alarme;
import equipements.Lumiere;
import equipements.Radiateur;
import main.Equipement;
import main.Piece;

public class Dressing extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	public Dressing(String nom) {
		super(nom, new LinkedList<Piece>(), new LinkedList<Equipement>());
	}

	public Dressing(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
		super(nom, piecesAdj, equipements);
	}

	@Override
	public LinkedList<String> equipementsAutorises() {
		LinkedList<String> equip = new LinkedList<String>();
		equip.add("Lumiere");
		equip.add("Radiateur");
		return equip;
	}

	@Override
	public Equipement creationEquipement(String name, int nb) {
		switch (nb) {
		case 1:
			return new Alarme(name);
		case 2:
			return new Lumiere(name);
		default:
			return new Radiateur(name);
		}
	}
}
