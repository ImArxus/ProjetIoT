package equipements;

import main.Equipement;

public class PS5 extends Equipement {

	private String jeu;
	
	public PS5(String nom) {
		super(nom, false);
		this.setJeu("aucun");
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

}
