package equipements;

import java.util.LinkedList;
import java.util.List;

import main.Equipement;

public class Balance extends Equipement {

	private int poids;
	List<Integer> listePoids = new LinkedList<Integer>();

	public Balance(String nom) {
		super(nom, false);
		listePoids.add(80);
		listePoids.add(60);
		listePoids.add(100);
		listePoids.add(110);
		this.setPoids();
	}

	public Balance(String nom, boolean etatCourant, List<Integer> listePoids) {
		super(nom, etatCourant);
		this.setListePoids(listePoids);
		setPoids();
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n-> Peser ";
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids() {
		int nombreAleatoire = (int)(Math.random() * (getListePoids().size() + 1));
		int valeur=getListePoids().get(nombreAleatoire);
		if (getListePoids().contains(valeur)) {
			this.poids = valeur;
		} else {
			System.out.println("Erreur de calcul");
		}
	}

	public void peser() {
		if (super.isEtatCourant()) {
			setPoids();
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas effectuer de mesures");
		}
	}

	public List<Integer> getListePoids() {
		return listePoids;
	}

	public void setListePoids(List<Integer> listePoids) {
		this.listePoids = listePoids;
	}

}
