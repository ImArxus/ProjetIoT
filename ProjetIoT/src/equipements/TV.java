package equipements;

import java.io.Serializable;

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

public class TV extends Equipement implements Serializable {

	private static final long serialVersionUID = 5394573896230563021L;
	private static ProgressBar volumeBar = new ProgressBar(0);
	private double volume;
	private int numeroChaine;
	private String image;
	private transient ImageView imageView = new ImageView();

	public TV(String nom) {
		super(nom);
		setVolume(50);
		setNumeroChaine(1);
		this.setPositionHorizontale(0.49);
		this.setPositionVerticale(0.4);
		volumeBar.setLayoutX(360);
		volumeBar.setLayoutY(275);
		volumeBar.setPrefSize(80, 15);
		image = "/images/objets/equipements.TV.desactive.png";
	}

	public TV(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale, int volume,
			int numeroChaine) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setVolume(volume);
		this.numeroChaine = numeroChaine;
		volumeBar.setLayoutX(360);
		volumeBar.setLayoutY(275);
		volumeBar.setPrefSize(80, 15);
		image = "/images/objets/equipements.TV.desactive.png";
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Augmenter volume\n➡️ 5 : Diminuer volume\n➡️ 6 : Augmenter chaine\n➡️ 7 : Diminuer chaine\n➡️ 8 : Mettre chaine";
	}

	public static ProgressBar getVolumeBar() {
		return volumeBar;
	}

	public int getNumeroChaine() {
		return numeroChaine;
	}

	public void setNumeroChaine(int numeroChaine) {
		this.numeroChaine = numeroChaine;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setImageChaine() {
		if (isEtatCourant()) {
			if (getNumeroChaine() == 1) {
				image = "/images/objets/equipements.TV.chaine1.png";
			} else if (getNumeroChaine() == 2) {
				image = "/images/objets/equipements.TV.chaine2.png";
			} else if (getNumeroChaine() == 3) {
				image = "/images/objets/equipements.TV.chaine3.png";
			} else {
				image = "/images/objets/equipements.TV.chaine4.png";
			}
		} else {
			image = "/images/objets/equipements.TV.desactive.png";
		}
	}

	public void augmenterVolume() {
		if (super.isEtatCourant()) {
			if (getVolume() <= 100) {
				volume++;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public void augmenterVolumeFX(Pane root) {
		if (super.isEtatCourant()) {
			if (getVolume() <= 100) {
				volume += 10;
				volumeBar.setProgress(getVolume() / 100);
			}
			try {
				root.getChildren().add(volumeBar);
			} catch (Exception e) {
			}
		}
	}

	public void diminuerVolume() {
		if (super.isEtatCourant()) {
			if (getVolume() >= 0) {
				volume--;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public void diminuerVolumeFX(Pane root) {
		if (super.isEtatCourant()) {
			if (getVolume() >= 0) {
				volume -= 10;
				volumeBar.setProgress(getVolume() / 100);
			}
			try {
				root.getChildren().add(volumeBar);
			} catch (Exception e) {
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public void augmenterChaine() {
		if (super.isEtatCourant()) {
			if (getNumeroChaine() < 4) {
				numeroChaine++;
			} else {
				numeroChaine = 4;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public void diminuerChaine() {
		if (super.isEtatCourant()) {
			if (getNumeroChaine() > 1) {
				numeroChaine--;
			} else {
				numeroChaine = 1;
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public void mettreChaine(int chaine) {
		if (super.isEtatCourant()) {
			if (chaine <= 4 && chaine >= 1) {
				setNumeroChaine(chaine);
			} else {
				System.out.println("Chaine non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	@Override
	public ImageView afficher() {
		imageView.setImage(new Image(getImage()));
		imageView.setTranslateY(50);
		imageView.setTranslateX(0);
		return imageView;
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(500);
		but.setTranslateY(500);
		return but;
	}

	@Override
	public void allumer() {
		if (!isEtatCourant()) {
			setEtatCourant(true);
			image = "/images/objets/equipements.TV.demarrage.png";
		}
	}

	@Override
	public MenuButton getFonctionnalites(Pane root, ImageView img) {

		MenuButton fonctionnalite = new MenuButton("Fonctionnalités");
		fonctionnalite.setPrefSize(220, 30);
		fonctionnalite.setLayoutX(570);
		fonctionnalite.setLayoutY(100);

		MenuItem allumer = new MenuItem("Allumer");
		allumer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				allumer();
				root.getChildren().remove(img);
				root.getChildren().add(afficher());
				Lumiere.boxIntensite(root);
				Radiateur.boxTemperature(root);
				root.getChildren().remove(fonctionnalite);
				root.getChildren().add(getFonctionnalites(root, img));
			}
		});
		MenuItem eteindre = new MenuItem("Éteindre");
		eteindre.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				eteindre();
				root.getChildren().remove(img);
				root.getChildren().removeAll(indicateurs());
				setImageChaine();
				root.getChildren().add(afficher());
				Lumiere.boxIntensite(root);
				Radiateur.boxTemperature(root);
				root.getChildren().remove(fonctionnalite);
				root.getChildren().add(getFonctionnalites(root, img));
			}
		});
		MenuItem quitter = new MenuItem("Quitter");
		quitter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				root.getChildren().remove(fonctionnalite);
				root.getChildren().removeAll(indicateurs());
			}
		});

		fonctionnalite.getItems().addAll(allumer, eteindre, quitter);		
		
		if (isEtatCourant()) {
			MenuItem augmenterVolume = new MenuItem("Augmenter volume");
			augmenterVolume.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					augmenterVolumeFX(root);
				}
			});
			MenuItem diminuerVolume = new MenuItem("Diminuer Volume");
			diminuerVolume.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					diminuerVolumeFX(root);
				}
			});
			MenuItem augmenterChaine = new MenuItem("Augmenter chaine");
			augmenterChaine.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					augmenterChaine();
					setImageChaine();
					root.getChildren().remove(img);
					root.getChildren().add(afficher());
				}
			});
			MenuItem diminuerChaine = new MenuItem(" Diminuer chaine");
			diminuerChaine.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					diminuerChaine();
					setImageChaine();
					root.getChildren().remove(img);
					root.getChildren().add(afficher());
				}
			});
			MenuItem netflix = new MenuItem("Netflix & Chill");
			netflix.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					setImage("/images/objets/equipements.TV.netflix.png");
					root.getChildren().remove(img);
					root.getChildren().add(afficher());
				}
			});
			MenuItem youtube = new MenuItem("Youtube");
			youtube.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					setImage("/images/objets/equipements.TV.youtube.png");
					root.getChildren().remove(img);
					root.getChildren().add(afficher());
				}
			});
			/*
			 * MenuItem choisirChaine = new MenuItem(" Choisir chaine");
			 * choisirChaine.setOnAction(new EventHandler<ActionEvent>() {
			 * 
			 * @Override public void handle(ActionEvent event) { Scanner s = new
			 * Scanner(System.in); System.out.println("Quelle chaine (entre 1 et 4) ?"); int
			 * chaine = Main.toInt(s.nextLine()); mettreChaine(chaine);
			 * System.out.println(getNom() + " est réglé sur la chaine " +
			 * getNumeroChaine()); s.close(); } });
			 */
			fonctionnalite.getItems().addAll(augmenterVolume, diminuerVolume, augmenterChaine, diminuerChaine,netflix,youtube);
		}
		return fonctionnalite;
	}
}
