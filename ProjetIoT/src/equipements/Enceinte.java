package equipements;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.Equipement;
import main.Main;

public class Enceinte extends Equipement implements Serializable {

	private static final long serialVersionUID = 6075055980198184766L;
	private double volume;
	private Map<String, String> musiques = new HashMap<String, String>();
	private String enEcoute;
	private ProgressBar volumeBar = new ProgressBar(0);


	public Enceinte(String nom) {
		super(nom);
		this.setVolume(50);
		musiques.put("Bim Bam Toi - Carla", "Et ça fait bim-bam-boum, ça fait -pschhht!- et ça fait vroum");
		musiques.put("Dance Monkey - Tones and I", "Dance for me, dance for me, dance for me, oh, oh, oh");
		musiques.put("Allez les gros - Marwa Loud ft Naza",
				"Mes poignées d'amour, ouais, c'est pour toi, mon amour, ouais");
		musiques.put("Allumer le feu - Johnny Hallyday", "Il suffira d'une étincelle");
		this.setEnEcoute((String) getMusiques().keySet().toArray()[0]);
		this.setPositionHorizontale(0.68);
		this.setPositionVerticale(0.28);
		volumeBar.setLayoutX(485);
		volumeBar.setLayoutY(370);
		volumeBar.setPrefSize(80, 15);
	}

	public Enceinte(String nom, boolean etatCourant, double volume, double positionHorizontale, double positionVerticale,
			Map<String, String> musiques, String enEcoute) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setVolume(volume);
		this.setMusiques(musiques);
		this.setEnEcoute(enEcoute);
		volumeBar.setLayoutX(485);
		volumeBar.setLayoutY(370);
		volumeBar.setPrefSize(80, 15);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n➡️ 4 : Augmenter volume\n➡️ 5 : Diminuer volume\n➡️ 6 : Jouer musique ";
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public void setEnEcoute(String enEcoute) {
		if (getMusiques().containsKey(enEcoute)) {
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

	public Map<String, String> getMusiques() {
		return musiques;
	}

	public void setMusiques(Map<String, String> musiques) {
		this.musiques = musiques;
	}

	@Override
	public ImageView afficher() {
		ImageView imageView = new ImageView();
		imageView.setImage(new Image(getImage()));
		imageView.setTranslateY(133);
		imageView.setTranslateX(140);
		return imageView;
	}
	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(600);
		but.setTranslateY(450);
		return but;
	}
	
	public void augmenterVolumeFX(Pane root) {
		if (super.isEtatCourant()) {
			if (getVolume() < 90) {
				volume += 10;
				volumeBar.setProgress(getVolume() / 100);
			}
			root.getChildren().add(volumeBar);
		} 
	}
	
	public void diminuerVolumeFX(Pane root) {
		if (super.isEtatCourant()) {
			if (getVolume() > 10) {
				volume -= 10;
				volumeBar.setProgress(getVolume() / 100);
			}
			root.getChildren().add(volumeBar);
		}
	}
	
	@Override
	public MenuButton getFonctionnalitées(Pane root, ImageView img) {
		MenuButton fonctionnalite = super.getFonctionnalitées(root, img);

		MenuItem augmenterVolume = new MenuItem(" Augmenter volume");
		augmenterVolume.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				augmenterVolumeFX(root);
				System.out.println("Le volume de " + getNom() + " est de " + getVolume());
			}
		});
		MenuItem diminuerVolume = new MenuItem(" Diminuer Volume");
		diminuerVolume.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				diminuerVolumeFX(root);
				System.out.println("Le volume de " + getNom() + " est de " + getVolume());
			}
		});
		fonctionnalite.getItems().addAll(augmenterVolume, diminuerVolume);
		return fonctionnalite;
	}
	
	
}
