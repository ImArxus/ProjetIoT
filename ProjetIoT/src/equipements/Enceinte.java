package equipements;

import java.util.LinkedList;
import java.util.List;

import main.Equipement;

public class Enceinte extends Equipement {

	private int volume;
	private List<String> musiques = new LinkedList<String>();
	private String enEcoute;

	public Enceinte(String nom) {
		super(nom, false);
		this.setVolume(100);
		musiques.add("Bim Bam Toi - Carla");
		musiques.add("Dance Monkey - Tones and I");
		musiques.add("Allez les gros - Marwa Loud ft Naza");
		musiques.add("Allumer le feu - Johnny Hallyday");
		this.setEnEcoute(getMusiques().get(0));
	}

	public Enceinte(String nom, boolean etatCourant, int volume, List<String> musiques, String enEcoute) {
		super(nom, etatCourant);
		this.setVolume(volume);
		this.setMusiques(musiques);
		this.setEnEcoute(enEcoute);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n-> Augmenter volume\n-> Diminuer volume\n-> Jouer musique ";
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public void setEnEcoute(String enEcoute) {
		if (getMusiques().contains(enEcoute)) {
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
			} else {
				setVolume(100);
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas augmenter le volume");
		}
	}

	public void diminuerVolume() {
		if (super.isEtatCourant()) {
			if (getVolume() > 9) {
				volume -= 10;
			} else {
				setVolume(0);
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas diminuer le volume");
		}
	}

	public void jouerMusique(String musique) {
		if (super.isEtatCourant()) {
			setEnEcoute(musique);
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de musique");
		}
	}

	public List<String> getMusiques() {
		return musiques;
	}

	public void setMusiques(List<String> musiques) {
		this.musiques = musiques;
	}

}
