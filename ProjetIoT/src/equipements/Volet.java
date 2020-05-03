package equipements;

import main.Equipement;

public class Volet extends Equipement {

	private int position;

	public Volet(String nom, boolean etatCourant, int position) {
		super(nom, etatCourant);
		this.setPosition(position);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n-> Monter volet\n-> Descendre volet\n-> Choisir position";
	}

	public double getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void monterVolet() {
		if (super.isEtatCourant()) {
			if (getPosition() < 100) {
				position++;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public void descendreVolet() {
		if (super.isEtatCourant()) {
			if (getPosition() > 0) {
				position--;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
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
