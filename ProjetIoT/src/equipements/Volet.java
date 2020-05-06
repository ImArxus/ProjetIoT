package equipements;

import main.Equipement;

public class Volet extends Equipement {

	private int position;

	public Volet(String nom, boolean etatCourant, int position) {
		super(nom, etatCourant);
		this.setPosition(position);
	}
	
	public Volet(String nom) {
		super(nom);
		this.setPosition(position);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n-> Monter volet\n-> Descendre volet\n-> Choisir position";
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void monterVolet() {
		if (super.isEtatCourant()) {
			if (getPosition() < 91) {
				position+=10;
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas monter le volet");
		}
	}

	public void descendreVolet() {
		if (super.isEtatCourant()) {
			if (getPosition() > 9) {
				position-=10;
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas baisser le volet");
		}
	}

	public void choisirPosition(int position) {
		if (super.isEtatCourant()) {
			if (position <= 100 && position >= 0) {
				setPosition(position);
			} else {
				System.out.println("Position non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas changer de position");
		}
	}

}
