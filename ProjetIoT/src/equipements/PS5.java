package equipements;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.Equipement;

public class PS5 extends Equipement {

	private String jeu;
	private Map<String,String> jeux = new HashMap<String,String>();
	
	public PS5(String nom) {
		super(nom, false);
		jeux.put("FIFA 2021","It's in the game");
		jeux.put("Call of Duty Modern Warfare V","bam bam bam");
		jeux.put("Mario Kart X","Mariooooo");
		jeux.put("Just Dance 8","La la la la");
		this.setJeu((String) jeux.keySet().toArray()[0]);
		
	}
	public PS5(String nom, boolean etatCourant, String jeu) {
		super(nom, etatCourant);
		this.setJeu(jeu);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n-> Lancer jeu";
	}

	public String getJeu() {
		return jeu;
	}

	public void setJeu(String jeu) {
		if (jeux.containsKey(jeu)) {
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
	public Map<String, String> getJeux() {
		return jeux;
	}

	public void setJeux(Map<String, String> jeux) {
		this.jeux = jeux;
	}
}
