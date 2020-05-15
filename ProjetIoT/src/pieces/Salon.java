package pieces;

import java.io.Serializable;
import java.util.LinkedList;

import main.Equipement;
import main.Piece;

public class Salon extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	public Salon(String nom) {
		super(nom, new LinkedList<Piece>(), new LinkedList<Equipement>());
	}

	public Salon(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
		super(nom, piecesAdj, equipements);
	}

	@Override
	public LinkedList<String> equipementsAutorises() {
		LinkedList<String> equip = super.equipementsAutorises();
		equip.add("Alexa");
		equip.add("Cheminee");
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
