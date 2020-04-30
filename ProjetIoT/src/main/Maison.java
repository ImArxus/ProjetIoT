package main;

import java.util.List;

public class Maison {

	private String nom;
	protected static List<Piece> pieces;

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

	public String toString() {
		return getNom() + " : " + getPieces();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public static List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		Maison.pieces = pieces;
	}
	
	public static void main(String[] args) {
		Maison house = new Maison("MyHouse");
		Piece salon = new Piece("Salon");
		Piece cuisine = new Piece("Cuisine");
		house.ajouterPiece(salon);
		house.ajouterPiece(cuisine);
		System.out.println(house.toString());
	}

}
