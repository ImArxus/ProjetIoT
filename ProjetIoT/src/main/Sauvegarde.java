package main;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Sauvegarde {

	// https://forum.hardware.fr/hfr/Programmation/Java/sauvegarde-sujet_129577_1.htm

	public static void sauvegarder() {
		// Map d'une maison associée à un pseudo
		Map<String, Maison> maMaison = new HashMap<String, Maison>();
		maMaison.put(Main.getPseudo(), Main.getMaison());

		// Crée un fichier texte
		try {
			PrintWriter ecrit = new PrintWriter(new FileWriter("sauvegarde" + Main.getPseudo() + ".txt"));
			ecrit.print(maMaison);
			ecrit.flush();
			ecrit.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public static void charger(String save) {
		try {
			FileInputStream fichier = new FileInputStream(save);
			ObjectInputStream ois = new ObjectInputStream(fichier);
			Maison load = (Maison) ois.readObject();

			System.out.println(load);
			System.out.println(Main.getMaison());
			/**
			 * gui.print(play.getcurrentRoom().toString()); gui.print(load.toString());
			 * play.enterRoom(load); gui.print(play.getcurrentRoom().toString());
			 */

		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		charger("sauvegardealex.txt");
	}

}
