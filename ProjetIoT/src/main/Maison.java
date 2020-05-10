package main;

import java.util.LinkedList;
import java.util.List;

public class Maison {

	private String nom;
	protected static List<Piece> pieces = new LinkedList<Piece>();

	public Maison(String nom) {
		this.setNom(nom);
		ajouterPiece(new Piece("Salon")); // Créé un salon par défaut si on ne précise aucune pièce
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
		getPieces().add(p);
	}

	public void suppressionPiece(Piece p) {
		getPieces().remove(p);
	}

	public void sontAdjacents(Piece piece1, Piece piece2) {
		piece1.ajouterPieceAdj(piece2);
		piece2.ajouterPieceAdj(piece1);
	}

	public void sontPlusAdjacents(Piece piece1, Piece piece2) {
		piece1.suppressionPieceAdj(piece2);
		piece2.suppressionPieceAdj(piece1);
	}

	public String toString() {
		return getNom() + " : " + getPieces();
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
		Maison.pieces = pieces;
	}

	public static void main(String[] args) {
		Maison house = new Maison("MyHouse");
		Piece cuisine = new Piece("Cuisine");
		house.ajouterPiece(cuisine);
		System.out.println(house.toString());
	}

}
