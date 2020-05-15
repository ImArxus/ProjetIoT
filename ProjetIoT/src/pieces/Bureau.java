package pieces;

import java.io.Serializable;
import java.util.LinkedList;

import equipements.Alarme;
import equipements.Alexa;
import equipements.Enceinte;
import equipements.Lumiere;
import equipements.Radiateur;
import equipements.Thermostat;
import equipements.Ventilateur;
import equipements.Volet;
import main.Equipement;
import main.Piece;

public class Bureau extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	public Bureau(String nom) {
		super(nom, new LinkedList<Piece>(), new LinkedList<Equipement>());
	}

	public Bureau(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
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
		return equip;
	}

	@Override
	public Equipement creationEquipement(String name, int nb) {
		switch (nb) {
		case 1:
			return new Alarme(name);
		case 2:
			return new Alexa(name);
		case 3:
			return new Enceinte(name);
		case 4:
			return new Lumiere(name);
		case 5:
			return new Radiateur(name);
		case 6:
			return new Thermostat(name);
		case 7:
			return new Ventilateur(name);
		default:
			return new Volet(name);
		}
	}

}
