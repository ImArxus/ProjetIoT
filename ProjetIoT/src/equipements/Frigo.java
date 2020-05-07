package equipements;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import main.Equipement;

public class Frigo extends Equipement {

	private int temperature;
	private Map<String, Integer> dispo = new HashMap<String, Integer>();

	public Frigo(String nom) {
		super(nom, false);
		setTemperature(5);
		dispo.put("Banane", 3);
		dispo.put("Yaourt", 1);
		dispo.put("Salade", 1);
		dispo.put("tomates", 3);
		// TODO Auto-generated constructor stub
	}

	protected Frigo(String nom, boolean etatCourant, int temperature, Map<String, Integer> dispo) {
		super(nom, etatCourant);
		setTemperature(temperature);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n-> Baisser temperature\n-> Augmenter temperature\n-> Lister produits\n-> Commander";
	}

	@Override
	public String toString() {
		String etat = "fermé";
		if (isEtatCourant()) {
			etat = "ouvert";
		}
		return getNom() + " (" + etat + ")";
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public Map<String, Integer> getDispo() {
		return dispo;
	}

	public void setDispo(Map<String, Integer> dispo) {
		this.dispo = dispo;
	}

	public void augmenterTemperature() {
		if (getTemperature() < 10) {
			temperature++;
		}
		else {
			System.out.println(this.getNom() + "La temperature est au max, on ne peut pas l'augmenter");
		}
	}

	public void diminuerTemperature() {
		if (getTemperature() > -5) {
			temperature--;
		}
		else {
			System.out.println(this.getNom() + "La temperature est au min, on ne peut pas la diminuer");
		}
	}

	public void Commander(String a, int b) {
		boolean trouve = false;
		for (Entry<String, Integer> e : dispo.entrySet()) {
			if (e.getKey() == a) {
				e.setValue(b);
				trouve = true;
			}
		}	
		if(trouve==false) {
			dispo.put(a, b);
		}
	}

	public static void main(String[] args) {
		Frigo a = new Frigo("yo");
		a.allumer();
		System.out.println(a.getDispo());
		a.Commander("Bonjour", 3);
		System.out.println(a.getDispo());
	}

}
