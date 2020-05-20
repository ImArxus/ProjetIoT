package equipements;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Equipement;

public class Cheminee extends Equipement implements Serializable {

	private static final long serialVersionUID = 7169763608942128813L;
	private int intensite;

	public Cheminee(String nom, boolean etatCourant, int intensite, double positionHorizontale,
			double positionVerticale) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setIntensite(intensite);
	}

	public Cheminee(String nom) {
		super(nom);
		this.setIntensite(intensite);
		this.setPositionHorizontale(0.26);
		this.setPositionVerticale(0.34);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Augmenter intensité\n➡️ 5 : Diminuer intensité\n➡️ 6 : Choisir intensité";
	}

	public double getIntensite() {
		return intensite;
	}

	public void setIntensite(int intensite) {
		this.intensite = intensite;
	}

	public void augmenterIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() < 100) {
				intensite += 10;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas augmenter l'intensité");
		}
	}

	public void diminuerIntensite() {
		if (super.isEtatCourant()) {
			if (getIntensite() > 0) {
				intensite -= 10;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas baisser l'intensite");
		}
	}

	public void choisirIntensite(int intensite) {
		if (super.isEtatCourant()) {
			if (intensite <= 100 && intensite >= 0) {
				setIntensite(intensite);
			} else {
				System.out.println("Intensite non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer l'intensité");
		}
	}
	
	public ImageView afficher() {
		ImageView imageView = new ImageView();
        imageView.setImage(new Image("/images/objets/equipements.Cheminee.png"));
        imageView.setTranslateY(105);
        imageView.setTranslateX(-180);
        return imageView;
	}

}
