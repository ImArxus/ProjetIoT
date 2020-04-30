package main;

import java.util.List;

public class Maison {

	private String nom;
	private List<Piece> pieces;

	public Maison(String nom) {
		this.setNom(nom);
	}

	public Maison(String nom, Piece piece) {
		this.setNom(nom);
		ajouterPiece(piece);
	}

	public Maison(String nom, List<Piece> pieces) {
		this.setNom(nom);
		this.setPieces(pieces);
	}

	public void ajouterPiece(Piece p) {
		pieces.add(p);
	}

	public String toString() {
		return getNom() + " : " + getPieces();
	}

	public static void main(String[] args) {
		Maison house = new Maison("MyHouse");
		System.out.println(house.toString());
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

}
