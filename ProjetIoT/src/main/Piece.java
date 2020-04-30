package main;

import java.util.List;

public class Piece {

	private String nom;
	private List<Piece> piecesAdj;
	private List<Equipement> equipements;

	public Piece(String nom) {
		this.setNom(nom);
	}

	public Piece(String nom, List<Equipement> equipements) {
		this.setNom(nom);
		this.setEquipements(equipements);
	}

	public Piece(String nom, List<Piece> piecesAdj, List<Equipement> equipements) {
		this.setNom(nom);
		this.setPiecesAdj(piecesAdj);
		this.setEquipements(equipements);
	}

	public String toString() {
		return nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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
