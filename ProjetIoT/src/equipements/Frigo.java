package equipements;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import main.Equipement;

public class Frigo extends Equipement {

	private int temperature;
	private Map<String, Integer> dispo = new HashMap<String, Integer>();

	protected Frigo(String nom) {
		super(nom,false);
		setTemperature(5);
		dispo.put("Banane", 3);
		dispo.put("Yaourt", 1);
		dispo.put("Salade", 1);
		dispo.put("tomates", 3);
		// TODO Auto-generated constructor stub
	}

	protected Frigo(String nom, boolean etatCourant, int temperature, Map<String, Integer> dispo ) {
		super(nom, etatCourant);
		setTemperature(temperature);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n-> Baisser temperature\n-> Augmenter temperature\n-> Lister produits\n->Commander";
	}
	
	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
	public  Map<String, Integer> getDispo() {
		return dispo;
	}

	public void setDispo(Map<String, Integer> dispo) {
		this.dispo = dispo;
	}
	
	public void augmenterTemperature() {
		if(super.etatCourant) {
			if(getTemperature()<10) {
				temperature++;
			}
		}
		else {
			System.out.println(this.getNom() + " est pas ouvert, on ne peut pas augmenter la temperature");
		}
	}
	
	
	public void diminuerTemperature() {
		if(super.etatCourant) {
			if(getTemperature()>-5) {
				temperature--;
			}
		}
		else {
			System.out.println(this.getNom() + " est pas ouvert, on ne peut pas diminuer la temperature");
		}
	}
	
	public void Commander(String a,int b) {
		for (Entry<String, Integer> e : dispo.entrySet()){
			if(e.getKey()==a) {
				e.setValue(b);
			}
		}
	}
	
	
	public static void main(String[] args) {
		Frigo a = new Frigo("yo");
		a.allumer();
		System.out.println(a.getDispo());
		a.Commander("Yaourt", 3);
		System.out.println(a.getDispo());
	}


}
