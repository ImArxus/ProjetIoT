package equipements;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

public class Enceinte extends Equipement implements Serializable {

	private static final long serialVersionUID = 6075055980198184766L;
	private double volume;
	private Map<String, String> musiques = new HashMap<String, String>();
	private String enEcoute;
	private static ProgressBar volumeBar = new ProgressBar(0);

	public Enceinte(String nom) {
		super(nom);
		this.setVolume(50);
		getMusiques().put("Bim Bam Toi - Carla", "Et ça fait bim-bam-boum, ça fait -pschhht!- et ça fait vroum");
		getMusiques().put("Dance Monkey - Tones and I", "Dance for me, dance for me, dance for me, oh, oh, oh");
		getMusiques().put("Allez les gros - Marwa Loud ft Naza",
				"Mes poignées d'amour, ouais, c'est pour toi, mon amour, ouais");
		getMusiques().put("Allumer le feu - Johnny Hallyday", "Il suffira d'une étincelle");
		this.setEnEcoute((String) getMusiques().keySet().toArray()[0]);
		getVolumeBar().setLayoutX(485);
		getVolumeBar().setLayoutY(370);
		getVolumeBar().setPrefSize(80, 15);
	}

	public Enceinte(String nom, boolean etatCourant, double volume, double positionHorizontale,
			double positionVerticale, Map<String, String> musiques, String enEcoute) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setVolume(volume);
		this.setMusiques(musiques);
		this.setEnEcoute(enEcoute);
		getVolumeBar().setLayoutX(485);
		getVolumeBar().setLayoutY(370);
		getVolumeBar().setPrefSize(80, 15);
	}

	public static ProgressBar getVolumeBar() {
		return volumeBar;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public String getEnEcoute() {
		return enEcoute;
	}

	public void setEnEcoute(String enEcoute) {
		if (getMusiques().containsKey(enEcoute)) {
			this.enEcoute = enEcoute;
		} else {
			System.err.println("Votre collection musicale ne possède pas ce titre");
		}
	}

	public Map<String, String> getMusiques() {
		return musiques;
	}

	public void setMusiques(Map<String, String> musiques) {
		this.musiques = musiques;
	}

	public void augmenterVolume(Pane root) {
		if (super.isEtatCourant()) {
			if (getVolume() <= 90) {
				setVolume(getVolume() + 10);
				getVolumeBar().setProgress(getVolume() / 100);
			} else {
				setVolume(100);
			}
			try {
				root.getChildren().add(getVolumeBar());
			} catch (Exception e) {
				System.err.println("Erreur lors de l'ajout de la barre de volume");
			}
		}
	}

	public void diminuerVolume(Pane root) {
		if (super.isEtatCourant()) {
			if (getVolume() > 10) {
				setVolume(getVolume() - 10);
				getVolumeBar().setProgress(getVolume() / 100);
			} else {
				setVolume(0);
			}
			try {
				root.getChildren().add(getVolumeBar());
			} catch (Exception e) {
				System.err.println("Erreur lors de l'ajout de la barre de volume");
			}
		}
	}

	public void jouerMusique(String musique) {
		if (super.isEtatCourant()) {
			setEnEcoute(musique);
		}
	}

	@Override
	public String getImage() {
		if (isEtatCourant()) {
			return ("/images/objets/equipements.Enceinte.png");
		} else {
			return ("/images/objets/equipements.Enceinte.desactive.png");
		}
	}

	@Override
	public ImageView afficher() {
		getImageView().setImage(new Image(getImage()));
		getImageView().setTranslateY(133);
		getImageView().setTranslateX(140);
		return getImageView();
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(600);
		but.setTranslateY(450);
		return but;
	}

	@Override
	public MenuButton getFonctionnalites(Pane root, ImageView img) {
		MenuButton fonctionnalite = super.getFonctionnalites(root, img);

		MenuItem augmenterVolume = new MenuItem(" Augmenter volume");
		augmenterVolume.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				augmenterVolume(root);
				root.getChildren().remove(img);
				root.getChildren().add(afficher());
			}
		});
		MenuItem diminuerVolume = new MenuItem(" Diminuer Volume");
		diminuerVolume.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				diminuerVolume(root);
				root.getChildren().remove(img);
				root.getChildren().add(afficher());
			}
		});
		fonctionnalite.getItems().addAll(augmenterVolume, diminuerVolume);
		return fonctionnalite;
	}
}
