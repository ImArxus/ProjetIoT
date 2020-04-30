package main;

import java.util.Scanner;

public class Main {

	private static Piece position = Maison.getPieces().get(0); // Position initiale dans la premère pièce ajoutée

	public void action() {

	}

	public static Piece getPosition() {
		return position;
	}

	public void setPosition(Piece position) {
		Main.position = position;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		boolean stop = false;

		while (!stop) {
			System.out.println("Vous êtes dans : " + getPosition() + "\n");
			System.out.println("Que souhaitez-vous faire ?");
			System.out.println("     Pour changer de pièce, tapez 'move'");
			System.out.println("     Pour utiliser un équipement, tapez 'use'");

			String requete = s.nextLine();
			if (requete == "move") {
				System.out.println("\nDans quelle pièce souhaitez-vous aller ?");
				System.out.println("     " + Maison.getPieces());
			}
		}
		s.close();
	}

}
