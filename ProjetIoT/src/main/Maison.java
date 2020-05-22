package main;

import java.io.Serializable;
import java.util.LinkedList;

public class Maison implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nom;
	protected LinkedList<Piece> pieces = new LinkedList<Piece>();

	public Maison(String nom) {
		this.setNom(nom);
		ajouterPiece(new Piece("Salon")); // Créé un salon par défaut si on ne précise aucune pièce
	}

	public Maison(String nom, Piece piece) {
		this.setNom(nom);
		ajouterPiece(piece);
	}

	public Maison(String nom, LinkedList<Piece> pieces) {
		this.setNom(nom);
		this.setPieces(pieces);
	}

	public void ajouterPiece(Piece p) {
		getPieces().add(p);
	}

	public void ajouterPieceFX(Piece p) {
		getPieces().add(p);
		sontAdjacents(p, Main.getPosition());
		Main.setPosition(p);
	}

	public void suppressionPiece(Piece p) {
		if (Main.getMaison().getPieces().size() > 1) {
			getPieces().remove(p);
		} else {
			System.err.println("Suppression impossible");
		}
	}

	public void suppressionPieceFX(Piece p) {
		if (Main.getMaison().getPieces().size() > 1) {
			if (Main.getPosition().equals(p)) {
				Main.setPosition(Main.getMaison().getPieces().getFirst());
			}
			getPieces().remove(p);
			LinkedList<Piece> piecesAdj = p.getPiecesAdj();
			int taille = piecesAdj.size();
			for (int i = 0; i < taille; i++) {
				sontPlusAdjacents(p, piecesAdj.get(i));
			}
			sansAdjacent(getPieces());
		} else {
			System.err.println("Suppression impossible");
		}
	}

	/**
	 * Si une pièce est sans pièce adjacente (donc inaccessible), alors elle devient
	 * adjacente de la première pièce de la maison
	 */
	public void sansAdjacent(LinkedList<Piece> pieces) {
		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).getPiecesAdj().isEmpty()) {
				sontAdjacents(pieces.get(i), getPieces().getFirst());
			}
		}
	}

	public void sontAdjacents(Piece piece1, Piece piece2) {
		piece1.ajouterPieceAdj(piece2);
		piece2.ajouterPieceAdj(piece1);
	}

	public void sontPlusAdjacents(Piece piece1, Piece piece2) {
		piece1.supprimerPieceAdj(piece2);
		piece2.supprimerPieceAdj(piece1);
	}

	public String toString() {
		return getNom() + " : " + getPieces().toString();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public LinkedList<Piece> getPieces() {
		return this.pieces;
	}

	public void setPieces(LinkedList<Piece> pieces) {
		this.pieces = pieces;
	}

	public static void main(String[] args) {
		Maison house = new Maison("MyHouse");
		Piece cuisine = new Piece("Cuisine");
		house.ajouterPiece(cuisine);
		System.out.println(house.toString());
	}

}
