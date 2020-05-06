package equipements;

import main.Equipement;

public class Enceinte extends Equipement {

	private int volume;
	private String enEcoute;

	public Enceinte(String nom) {
		super(nom, false);
		this.setVolume(100);
		this.setEnEcoute("rien");
	}

	public Enceinte(String nom, boolean etatCourant, int volume, String enEcoute) {
		super(nom, etatCourant);
		this.setVolume(volume);
		this.setEnEcoute(enEcoute);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n-> Augmenter Volume\n-> Diminuer Volume\n->Changer Musique ";
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public void setEnEcoute(String enEcoute) {
		if ((enEcoute.equals("rien")) || (enEcoute.equals("Bim Bam Toi - Carla")) || (enEcoute.equals("Dance Monkey - Tones and I"))
				|| (enEcoute.equals("Allez les gros - Marwa Loud ft Naza")) || (enEcoute.equals("Allumer le feu - Johnny Hallyday"))) {
			this.enEcoute = enEcoute;
		} else {
			System.out.println("Votre collection musicale ne possède pas ce titre");
		}
	}

	public String getEnEcoute() {
		return enEcoute;
	}

	public void augmenterVolume() {
		if (super.isEtatCourant()) {
			if (getVolume() < 91) {
				volume += 10;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas augmenter le volume");
		}
	}

	public void descendreVolume() {
		if (super.isEtatCourant()) {
			if (getVolume() > 9) {
				volume -= 10;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas diminuer le volume");
		}
	}

	public void JouerMusique(String musique) {
		if (super.isEtatCourant()) {
			setEnEcoute(musique);
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas changer de musique");
		}
	}

}
