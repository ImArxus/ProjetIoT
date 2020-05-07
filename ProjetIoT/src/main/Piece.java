package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Piece {

	private String nom;
	private int temperature;
	private List<Piece> piecesAdj = new LinkedList<Piece>();
	private List<Equipement> equipements = new LinkedList<Equipement>();

	public Piece(String nom) {
		this.setNom(nom);
		this.temperature=21;
	}

	public Piece(String nom, List<Equipement> equipements) {
		this.setNom(nom);
		this.setEquipements(equipements);
		this.temperature=21;
	}

	public Piece(String nom, List<Piece> piecesAdj, List<Equipement> equipements) {
		this.setNom(nom);
		this.setPiecesAdj(piecesAdj);
		this.setEquipements(equipements);
		this.temperature=21;
	}

	public void ajouterEquipement(Equipement equip) {
		getEquipements().add(equip);
	}

	public void ajouterPieceAdj(Piece piece) {
		getPiecesAdj().add(piece);
	}

	public String toString() {
		return getNom() + " qui est équipé(e) de " + afficher(equipements);
	}
	
	public String afficher(List<Equipement> a) {
		Iterator<Equipement> it = a.iterator();
		String fin = "\n";
		while (it.hasNext()) {
			Equipement b = it.next();
			fin = fin +"       -> "+ b.toString() + "\n";
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
		this.temperature = temperature;
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

	public static void main(String[] args) {
		List<Equipement> equip = new LinkedList<Equipement>();
		// Equipement TV = new Equipement("TV");
		// ajouterEquipement(TV);
		Piece salon = new Piece("Salon", equip);
		System.out.println(salon.toString());
	}

}
