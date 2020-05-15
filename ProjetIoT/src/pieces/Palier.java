package pieces;

import java.io.Serializable;
import java.util.LinkedList;

import equipements.Alarme;
import equipements.Alexa;
import equipements.Cheminee;
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

public class Palier extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	public Palier(String nom) {
		super(nom, new LinkedList<Piece>(), new LinkedList<Equipement>());
	}

	public Palier(String nom, LinkedList<Piece> piecesAdj, LinkedList<Equipement> equipements) {
		super(nom, piecesAdj, equipements);
	}

	@Override
	public LinkedList<String> equipementsAutorises() {
		LinkedList<String> equip = super.equipementsAutorises();
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
