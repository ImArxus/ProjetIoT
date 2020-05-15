package main;

import java.io.Serializable;
import java.util.LinkedList;

public class ListeEquipementConstructibles implements Serializable {

	private static final long serialVersionUID = 9L;
	private static LinkedList<String> liste = new LinkedList<String>();

	public static void ajoutEquipementConstructibles() {
		liste.add("Alarme");
		liste.add("Alexa");
		liste.add("Balance");
		liste.add("Cheminee");
		liste.add("Electrolyseur");
		liste.add("Enceinte");
		liste.add("Frigo");
		liste.add("Lumiere");
		liste.add("PS5");
		liste.add("Radiateur");
		liste.add("Thermostat");
		liste.add("TV");
		liste.add("Ventilateur");
		liste.add("Volet");
	}

	public static LinkedList<String> getListe() {
		liste.clear();
		ajoutEquipementConstructibles();
		return liste;
	}

}
