package pieces;

import java.io.Serializable;
import java.util.LinkedList;

import equipements.Alarme;
import equipements.Alexa;
import equipements.Enceinte;
import equipements.Lumiere;
import equipements.PS5;
import equipements.Radiateur;
import equipements.TV;
import equipements.Thermostat;
import equipements.Ventilateur;
import equipements.Volet;
import main.Equipement;
import main.Piece;

public class Chambre extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	public Chambre(String nom) {
		super(nom, new LinkedList<Piece>(), new LinkedList<Equipement>());
	}

	public Chambre(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
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
			return new PS5(name);
		case 6:
			return new Radiateur(name);
		case 7:
			return new Thermostat(name);
		case 8:
			return new TV(name);
		case 9:
			return new Ventilateur(name);
		default:
			return new Volet(name);
		}
	}
}
