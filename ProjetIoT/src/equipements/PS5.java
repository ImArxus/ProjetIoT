package equipements;

import java.util.LinkedList;
import java.util.List;

import main.Equipement;

public class PS5 extends Equipement {

	private String jeu;
	private List<String> jeux = new LinkedList<String>();
	
	public PS5(String nom) {
		super(nom, false);
		this.setJeu("aucun");
		jeux.add("FIFA 2021");
		jeux.add("Call of Duty Modern Warfare V");
		jeux.add("Mario Kart X");
		jeux.add("Just Dance 8");
	}
	public PS5(String nom, boolean etatCourant, String jeu) {
		super(nom, etatCourant);
		this.setJeu(jeu);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n-> Lancer disque";
	}

	public String getJeu() {
		return jeu;
	}

	public void setJeu(String jeu) {
		if ((jeu.equals("rien")) || (jeu.equals("FIFA 2021")) || (jeu.equals("Call of Duty Modern Warfare V"))
				|| (jeu.equals("GTA VII")) || (jeu.equals("Just Dance 8")) || (jeu.equals("Mario Kart X"))) {
			this.jeu = jeu;
		} else {
			System.out.println("Votre collection de jeu vidéos ne possède pas ce titre");
		}
	}

	public void choisirJeu(String jeu) {
		if (super.isEtatCourant()) {
			setJeu(jeu);
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas lancer un jeu");
		}
	}
	public List<String> getJeux() {
		return jeux;
	}

	public void setJeux(List<String> jeux) {
		this.jeux = jeux;
	}
}
