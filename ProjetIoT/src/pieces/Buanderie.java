package pieces;

import java.io.Serializable;
import java.util.LinkedList;

import equipements.Alarme;
import equipements.Frigo;
import equipements.Lumiere;
import javafx.scene.control.Button;
import main.Equipement;
import main.Piece;

public class Buanderie extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	public Buanderie(String nom) {
		super(nom, new LinkedList<Piece>(), new LinkedList<Equipement>());
	}

	public Buanderie(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
		super(nom, piecesAdj, equipements);
	}

	@Override
	public LinkedList<String> equipementsAutorises() {
		LinkedList<String> equip = super.equipementsAutorises();
		equip.add("Lumiere");
		equip.add("Frigo");
		return equip;
	}

	@Override
	public Equipement creationEquipement(String name, int nb) {
		switch (nb) {
		case 1:
			return new Alarme(name);
		case 5:
			return new Lumiere(name);
		default:
			return new Frigo(name);
		}
	}
	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(400);
		but.setTranslateY(450);
		return but;
	}
}
