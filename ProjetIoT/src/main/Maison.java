package main;

import java.io.Serializable;
import java.util.LinkedList;

public class Maison implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nom;
	private LinkedList<Piece> pieces = new LinkedList<Piece>();

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
		if (getPieces().size() > 1) {
			sontAdjacents(p, Main.getPosition());
		}
		Main.setPosition(p);
	}

	public void suppressionPiece(Piece p) {
		if (Main.getMaison().getPieces().size() > 1) {
			if (Main.getPosition().equals(p)) {
				Main.setPosition(Main.getMaison().getPieces().getFirst());
			}
			getPieces().remove(p);
			LinkedList<Piece> piecesAdj = p.getPiecesAdj();
			while (!piecesAdj.isEmpty()) {
				sontPlusAdjacents(p, piecesAdj.getFirst());
			}
			checkAccess(getPieces());
		} else {
			System.err.println("Suppression impossible");
		}
	}

	/**
	 * Si une pièce a moins de deux pièces adajcentes, alors elle devient adjacente
	 * de la première pièce de la maison pour eviter de se retrouver inaccessible en
	 * cas de suppression de la pièce qui la rattache au reste de la maison
	 */
	public void checkAccess(LinkedList<Piece> pieces) {
		for (int i = 0; i < pieces.size(); i++) {
			Piece piece = pieces.get(i);
			if (piece.getPiecesAdj().size() < 2) {
				sontAdjacents(piece, getPieces().getFirst());
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

}
