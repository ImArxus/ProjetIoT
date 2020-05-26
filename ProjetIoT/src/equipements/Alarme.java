package equipements;

import java.io.Serializable;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Equipement;

public class Alarme extends Equipement implements Serializable {

	private static final long serialVersionUID = -1828693427527124467L;
	private transient static ImageView imageView = new ImageView();


	public Alarme(String nom) {
		super(nom);
		this.setEtatCourant(false);
	}

	public Alarme(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale) {
		super(nom, etatCourant, positionHorizontale, positionVerticale);
	}

	public void sonner() {
		System.out.println("\nBip !!! Bip !!! Bip !!!");
		System.out.println("Vous devez sortir de la maison...");
	}

	public ImageView getImageView() {
		imageView.setImage(new Image("/images/objets/equipements.Alarme.png"));
		return imageView;
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(100);
		but.setTranslateY(450);
		return but;
	}

	public ImageView afficher() {
		imageView.setImage(new Image(getImage()));
		imageView.setTranslateX(-370);
		return imageView;
	}
	@Override
	public String getImage() {
		if (!etatCourant) {
			return ("/images/objets/equipements.Alarme.png");
		} else {
			return ("/images/objets/equipements.Alarme.desactive.png");
		}
	}
}
