package pieces;

import java.io.Serializable;
import java.util.LinkedList;

import equipements.Alarme;
import equipements.Enceinte;
import equipements.Lumiere;
import equipements.Volet;
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
	public Equipement creationEquipement(String name, int nb) {
		switch (nb) {
		case 1:
			return new Alarme(name);
		case 2:
			return new Enceinte(name);
		case 3:
			return new Lumiere(name);
		default:
			return new Volet(name);
		}
	}

	@Override
	public String imagePiece() {
		return ("images/jardin.png");
	}

}
