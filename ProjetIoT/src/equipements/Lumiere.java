package equipements;

import java.io.Serializable;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.Equipement;
import main.Main;

public class Lumiere extends Equipement implements Serializable {

	private static final long serialVersionUID = 3377862988501403504L;
	private int intensite;
	private String couleur;
	private ImageView imageView = new ImageView();

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
		imageView.setImage(new Image(getImage()));
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

	@Override
	public MenuButton getFonctionnalitées(Pane root, ImageView img) {
		MenuButton fonctionnalite = super.getFonctionnalitées(root, img);

		MenuItem augmenterIntensité = new MenuItem(" Augmenter intensité");
		augmenterIntensité.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				augmenterIntensite();
				System.out.println("L'intensité de " + getNom() + " est réglé sur " + getIntensite());
			}
		});
		MenuItem diminuerIntensité = new MenuItem(" Diminuer l'intensité");
		diminuerIntensité.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				diminuerIntensite();
				System.out.println("L'intensité de " + getNom() + " est réglé sur " + getIntensite());
			}
		});
		MenuItem choisirIntensité = new MenuItem(" Choisir intensité");
		choisirIntensité.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Scanner s = new Scanner(System.in);
				System.out.println("Quelle intensité (entre 0 et 100) ?");
				int intensite = Main.toInt(s.nextLine());
				choisirIntensite(intensite);
				System.out.println("L'intensité de " + getNom() + " est réglé sur " + getIntensite());
				s.close();
			}
		});
		MenuItem choisirCouleur = new MenuItem(" Choisir couleur");
		choisirCouleur.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Scanner s = new Scanner(System.in);
				System.out.println("Quelle couleur parmis les suivantes : blanc, bleu, rouge, jaune, vert");
				String couleur = s.nextLine();
				changerCouleur(couleur);
				System.out.println("La couleur de " + getNom() + " est réglé sur " + getCouleur());
				s.close();
			}
		});
		fonctionnalite.getItems().addAll(augmenterIntensité, diminuerIntensité, choisirIntensité, choisirCouleur);
		return fonctionnalite;
	}

	@Override
	public String getImage() {
		if (etatCourant) {
			if (getCouleur() == "bleu") {
				return ("/images/objets/equipements.Lumiere.bleu.png");
			} else if (getCouleur() == "rouge") {
				return ("/images/objets/equipements.Lumiere.rouge.png");
			} else if (getCouleur() == "jaune") {
				return ("/images/objets/equipements.Lumiere.jaune.png");
			} else if (getCouleur() == "vert") {
				return ("/images/objets/equipements.Lumiere.vert.png");
			} else {
				return ("/images/objets/equipements.Lumiere.png");
			}
		} else {
			return ("/images/objets/equipements.Lumiere.desactive.png");
		}
	}
}
