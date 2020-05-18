package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Sauvegarde implements Serializable {

	private static final long serialVersionUID = -1850993100796950057L;

	public static void sauvegarder() {
		try {
			/* Create a file to write the serialized tree to. */
			FileOutputStream ostream = new FileOutputStream("Maison de " + Main.getPseudo());
			/* Create the output stream */
			ObjectOutputStream p = new ObjectOutputStream(ostream);

			/* Create a tree with three levels. */
			Maison m = Main.getMaison();

			p.writeObject(m); // Write the tree to the stream.
			p.flush();
			ostream.close(); // close the file.

			System.out.println("\nVotre maison a bien été enregistré !");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void sauvegarderCompte() {
		try {
			/* Create a file to write the serialized tree to. */
			FileOutputStream ostream = new FileOutputStream("Comptes");
			/* Create the output stream */
			ObjectOutputStream p = new ObjectOutputStream(ostream);

			/* Create a tree with three levels. */
			ListeUtilisateurs ListeAdmin = Main.getListeAdmin();

			p.writeObject(ListeAdmin); // Write the tree to the stream.
			p.flush();
			ostream.close(); // close the file.

			System.out.println("Compte enregistré");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static ListeUtilisateurs chargerComptes() {

		try {

			FileInputStream istream = new FileInputStream("Comptes");
			ObjectInputStream q = new ObjectInputStream(istream);

			/* Read a tree object, and all the subtrees */
			ListeUtilisateurs ListeAdmin = (ListeUtilisateurs) q.readObject();
			q.close();
			return ListeAdmin;
		} catch (Exception ex) {
			return null;
		}
	}

	public static Maison chargerMAISON() {

		try {
			FileInputStream istream = new FileInputStream("Maison de " + Main.getPseudo());
			ObjectInputStream q = new ObjectInputStream(istream);

			/* Read a tree object, and all the subtrees */
			Maison m = (Maison) q.readObject();
			q.close();
			System.out.println(m.toString());
			return m;
		} catch (Exception ex) {
			System.out.println("Vous n'avez pas de maison sauvegardée...");
			return null;
		}
	}

}
