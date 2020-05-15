package pieces;

import java.io.Serializable;
import java.util.LinkedList;

import equipements.Alarme;
import equipements.Alexa;
import equipements.Enceinte;
import equipements.Lumiere;
import equipements.Radiateur;
import equipements.Thermostat;
import equipements.Volet;
import main.Equipement;
import main.Piece;

public class SalleDeBain extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	public SalleDeBain(String nom) {
		super(nom, new LinkedList<Piece>(), new LinkedList<Equipement>());
	}

	public SalleDeBain(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
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
		default:
			return new Volet(name);
		}
	}

}
