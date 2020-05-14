package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Piece {

	private String nom;
	private int temperature;
	private int intensiteLumineuse; // Intensité totale
	private List<Piece> piecesAdj = new LinkedList<Piece>();
	private List<Equipement> equipements = new LinkedList<Equipement>();

	public Piece(String nom) {
		this.setNom(nom);
		this.temperature = 21;
		this.intensiteLumineuse = 50;
	}

	public Piece(String nom, List<Equipement> equipements) {
		this.setNom(nom);
		this.setEquipements(equipements);
		this.temperature = 21;
		this.intensiteLumineuse = 50;
	}

	public Piece(String nom, List<Piece> piecesAdj, List<Equipement> equipements) {
		this.setNom(nom);
		this.setPiecesAdj(piecesAdj);
		this.setEquipements(equipements);
		this.temperature = 21;
		this.intensiteLumineuse = 0;
	}

	public void ajouterEquipement(Equipement equip) {
		getEquipements().add(equip);
	}

	public void supprimerEquipement(Equipement equip) {
		getEquipements().remove(equip);
	}

	public void ajouterPieceAdj(Piece piece) {
		getPiecesAdj().add(piece);
	}

	public void supprimerPieceAdj(Piece piece) {
		getPiecesAdj().remove(piece);
	}

	public String toString() {
		if (getEquipements().isEmpty()) {
			return getNom() + " qui est sans équipement...";
		}
		return getNom() + " qui est équipé(e) de " + afficher(equipements);
	}

	public String afficher(List<Equipement> a) {
		Iterator<Equipement> it = a.iterator();
		String fin = "\n";
		while (it.hasNext()) {
			Equipement b = it.next();
			fin = fin + "       ->️ " + b.toString() + "\n";
		}
		return fin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		if ((temperature >= 15) && (temperature <= 30)) {
			this.temperature = temperature;
		} else if (temperature > 30) {
			this.temperature = 30;
		} else {
			this.temperature = 15;
		}

	}

	public int getIntensiteLumineuse() {
		return intensiteLumineuse;
	}

	public void setIntensiteLumineuse(int intensiteLumineuse) {
		if ((intensiteLumineuse >= 0) && (intensiteLumineuse <= 100)) {
			this.intensiteLumineuse = intensiteLumineuse;
		} else if (intensiteLumineuse > 100) {
			this.intensiteLumineuse = 100;
		} else {
			this.intensiteLumineuse = 0;
		}
	}

	public List<Piece> getPiecesAdj() {
		return piecesAdj;
	}

	public void setPiecesAdj(List<Piece> piecesAdj) {
		this.piecesAdj = piecesAdj;
	}

	public List<Equipement> getEquipements() {
		return equipements;
	}

	public void setEquipements(List<Equipement> equipements) {
		this.equipements = equipements;
	}

}
