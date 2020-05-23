package equipements;

import java.io.Serializable;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Equipement;

public class Ventilateur extends Equipement implements Serializable {

	private static final long serialVersionUID = 8252594235507326423L;
	private int intensite;

	public Ventilateur(String nom) {
		super(nom);
		this.setIntensite(3);
		this.setPositionHorizontale(0.2);
		this.setPositionVerticale(0.21);
	}

	public Ventilateur(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale,
			int intensite) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setIntensite(intensite);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Augmenter intensité\n➡️ 5 : Diminuer intensité\n➡️ 6 : Choisir intensité ";
	}

	public int getIntensite() {
		return intensite;
	}

	public void setIntensite(int intensite) {
		this.intensite = intensite;
	}

	public void augmenterIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() < 5) {
				intensite++;
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas augmenter l'intensité");
		}
	}

	public void diminuerIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() > 0) {
				intensite--;
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas diminuer l'intensite");
		}
	}

	public void choisirIntensite(int intensite) {
		if (super.isEtatCourant()) {
			if (intensite <= 5 && intensite >= 0) {
				setIntensite(intensite);
			} else {
				System.out.println("Intensité non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas changer l'intensité");
		}
	}
	
	public ImageView afficher() {
		ImageView imageView = new ImageView();
        imageView.setImage(new Image(getImage()));
        imageView.setTranslateY(170);
        imageView.setTranslateX(-250);
        return imageView;
	}
	public ImageView getImageView() {
		ImageView i0 = new ImageView();
		i0.setImage(new Image("/images/objets/equipements.Ventilateur.png"));
		return i0;
	}
	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(600);
		but.setTranslateY(500);
		return but;
	}
}
