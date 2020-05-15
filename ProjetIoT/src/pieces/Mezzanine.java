package pieces;

import java.io.Serializable;
import java.util.LinkedList;

import main.Equipement;
import main.Piece;

public class Mezzanine extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	public Mezzanine(String nom) {
		super(nom, new LinkedList<Piece>(), new LinkedList<Equipement>());
	}

	public Mezzanine(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
		super(nom, piecesAdj, equipements);
	}

	@Override
	public LinkedList<String> equipementsAutorises() {
		LinkedList<String> equip = super.equipementsAutorises();
		equip.add("Alexa");
		equip.add("Enceinte");
		equip.add("Lumiere");
		equip.add("PS5");
		equip.add("Radiateur");
		equip.add("Thermostat");
		equip.add("TV");
		equip.add("Ventilateur");
		equip.add("Volet");
		return equip;
	}

}
