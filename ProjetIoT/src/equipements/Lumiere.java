package equipements;

import java.io.Serializable;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Equipement;
import main.Main;

public class Lumiere extends Equipement implements Serializable {

	private static final long serialVersionUID = 3377862988501403504L;
	private int intensite;
	private String couleur;

	public Lumiere(String nom) {
		super(nom);
		setIntensite(100);
		setCouleur("blanc");
		this.setPositionVerticale(0.75);
		this.setPositionHorizontale(0.5);
	}

	public Lumiere(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale) {
		super(nom, etatCourant, positionHorizontale, positionVerticale);
		setIntensite(intensite);
		setCouleur(couleur);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Augmenter intensité\n➡️ 5 : Diminuer intensité\n➡️ 6 : Choisir intensité\n➡️ 7 : Choisir couleur";
	}

	public int getIntensite() {
		return intensite;
	}

	public void setIntensite(int intensite) {
		this.intensite = intensite;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		if ((couleur.equals("blanc")) || (couleur.equals("bleu")) || (couleur.equals("rouge"))
				|| (couleur.equals("jaune")) || (couleur.equals("vert"))) {
			this.couleur = couleur;
		} else {
			System.out.println("Couleur non-valide");
		}
	}

	public void changerCouleur(String couleur) {
		if (super.isEtatCourant()) {
			setCouleur(couleur);
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de couleur");
		}
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
			System.out.println(this.getNom() + " est éteinte, on ne peut pas diminuer l'intensité");
		}
	}

	public void choisirIntensite(int intensite) {
		if (super.isEtatCourant()) {
			if (intensite <= 100 && intensite >= 0) {
				setIntensite(intensite);
			} else {
				System.out.println("Intensité non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer d'intensité");
		}
	}

	public ImageView afficher() {
		ImageView imageView = new ImageView();
		if (Main.getHeure() > 21 || Main.getHeure() < 6) {
			imageView.setImage(new Image("/images/objets/equipements.Lumiere.png"));
		} else {
			imageView.setImage(new Image("/images/objets/equipements.Lumieres.desactive.png"));
		}
		imageView.setTranslateY(-120);
		return imageView;
	}
	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(100);
		but.setTranslateY(500);
		return but;
	}

}
