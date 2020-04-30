package equipements;

import main.Equipement;

public class Volet extends Equipement {

	private double position;

	public Volet(String nom, boolean etatCourant, double position) {
		super(etatCourant, nom);
		this.setPosition(position);
	}

	public double getPosition() {
		return position;
	}

	public void setPosition(double position) {
		this.position = position;
	}

	public void monterVolet() {
		if (getPosition() < 100 && etatCourant) {
			position++;
		}
	}

	public void descendreVolet() {
		if (getPosition() > 0 && etatCourant) {
			position--;
		}
	}

	public void choisirPositio(double position) {
		if (etatCourant && position <= 100 && position >= 0) {
			setPosition(position);
		}
	}

	public static void main(String[] args) {
	}
}
