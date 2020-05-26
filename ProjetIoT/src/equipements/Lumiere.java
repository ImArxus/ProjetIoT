package equipements;

import java.io.Serializable;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Box;
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

	public static void boxIntensiteLum(Pane root) {
		Main.traitementIntensiteLumineuseNaturelle();
		Main.traitementIntensiteLumineuse();

		Box box = new Box(100, 25, 0);
		box.setLayoutX(458);
		box.setLayoutY(80);
		Label lblLum = new Label();
		lblLum.setText("" + Main.getPosition().getIntensiteLumineuse() + "%");
		lblLum.setStyle("-fx-font: 20 arial; -fx-font-weight: bold;");
		lblLum.setLayoutX(411);
		lblLum.setLayoutY(68);

		root.getChildren().add(box);
		root.getChildren().add(lblLum);
	}

	@Override
	public MenuButton getFonctionnalites(Pane root, ImageView img) {
		MenuButton fonctionnalite = super.getFonctionnalites(root, img);

		if (isEtatCourant()) {
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
					couleurLum(root);
				}
			});

			fonctionnalite.getItems().addAll(augmenterIntensité, diminuerIntensité, choisirIntensité, choisirCouleur);
		}
		return fonctionnalite;
	}

	@Override
	public String getImage() {
		if (etatCourant) {
			if (getCouleur().equals("bleu")) {
				return ("/images/objets/equipements.Lumiere.bleu.png");
			} else if (getCouleur().equals("rouge")) {
				return ("/images/objets/equipements.Lumiere.rouge.png");
			} else if (getCouleur().equals("jaune")) {
				return ("/images/objets/equipements.Lumiere.jaune.png");
			} else if (getCouleur().equals("vert")) {
				return ("/images/objets/equipements.Lumiere.vert.png");
			} else {
				return ("/images/objets/equipements.Lumiere.png");
			}
		} else {
			return ("/images/objets/equipements.Lumiere.desactive.png");
		}
	}

	public void couleurLum(Pane root) {
		MenuButton couleurs = new MenuButton("Quelle couleur de lumière ?");
		couleurs.setPrefSize(220, 30);
		couleurs.setLayoutX(300);
		couleurs.setLayoutY(220);

		MenuItem choix1 = new MenuItem("Blanc");
		choix1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changerCouleur("blanc");
				root.getChildren().remove(imageView);
				root.getChildren().add(afficher());
			}
		});
		MenuItem choix2 = new MenuItem("Bleu");
		choix2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changerCouleur("bleu");
				root.getChildren().remove(imageView);
				root.getChildren().add(afficher());
			}
		});
		MenuItem choix3 = new MenuItem("Rouge");
		choix3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changerCouleur("rouge");
				root.getChildren().remove(imageView);
				root.getChildren().add(afficher());
			}
		});
		MenuItem choix4 = new MenuItem("Jaune");
		choix4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changerCouleur("jaune");
				root.getChildren().remove(imageView);
				root.getChildren().add(afficher());
			}
		});
		MenuItem choix5 = new MenuItem("Vert");
		choix5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changerCouleur("vert");
				root.getChildren().remove(imageView);
				root.getChildren().add(afficher());
			}
		});
		MenuItem choix6 = new MenuItem("Cette couleur est parfaite pour moi !");
		choix6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.getChildren().remove(couleurs);
			}
		});

		couleurs.getItems().addAll(choix1, choix2, choix3, choix4, choix5, choix6);
		root.getChildren().add(couleurs);
	}

}
