package main;

import java.util.LinkedList;
import java.util.List;

public class ListeEquipementConstructibles {

	private static List<String> liste= new LinkedList<String>();
	
	public ListeEquipementConstructibles() {
		liste.add("Alarme");
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
		liste.add("Volet");
	}
	
	public static List<String> getListe() {
		return liste;
	}	
}
