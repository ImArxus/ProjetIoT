package equipements;

import java.io.Serializable;
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

public class TV extends Equipement implements Serializable {

	private static final long serialVersionUID = 5394573896230563021L;
	private static ProgressBar volumeBar = new ProgressBar(0);
	private double volume;
	private int numeroChaine;

	public TV(String nom) {
		super(nom);
		setVolume(50);
		setNumeroChaine(1);
		this.setPositionHorizontale(0.49);
		this.setPositionVerticale(0.4);
		volumeBar.setLayoutX(360);
		volumeBar.setLayoutY(275);
		volumeBar.setPrefSize(80, 15);
	}

	public TV(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale, int volume,
			int numeroChaine) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setVolume(volume);
		this.numeroChaine = numeroChaine;
		volumeBar.setLayoutX(360);
		volumeBar.setLayoutY(275);
		volumeBar.setPrefSize(80, 15);
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
		ImageView imageView = new ImageView();
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
	public String getImage() {
		if (isEtatCourant()) {
			if (getNumeroChaine() == 1) {
				return ("/images/objets/equipements.TV.chaine1.png");
			} else if (getNumeroChaine() == 2) {
				return ("/images/objets/equipements.TV.chaine2.png");
			} else if (getNumeroChaine() == 3) {
				return ("/images/objets/equipements.TV.chaine3.png");
			} else {
				return ("/images/objets/equipements.TV.chaine4.png");
			}
		} else {
			return ("/images/objets/equipements.TV.desactive.png");
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
		MenuItem augmenterChaine = new MenuItem(" Augmenter chaine");
		augmenterChaine.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				augmenterChaine();
				System.out.println(getNom() + " est réglé sur la chaine " + getNumeroChaine());
			}
		});
		MenuItem diminuerChaine = new MenuItem(" Diminuer chaine");
		diminuerChaine.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				diminuerChaine();
				System.out.println(getNom() + " est réglé sur la chaine " + getNumeroChaine());
			}
		});
		MenuItem choisirChaine = new MenuItem(" Choisir chaine");
		choisirChaine.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Scanner s = new Scanner(System.in);
				System.out.println("Quelle chaine (entre 1 et 4) ?");
				int chaine = Main.toInt(s.nextLine());
				mettreChaine(chaine);
				System.out.println(getNom() + " est réglé sur la chaine " + getNumeroChaine());
				s.close();
			}
		});
		fonctionnalite.getItems().addAll(augmenterVolume, diminuerVolume, augmenterChaine, diminuerChaine,
				choisirChaine);
		return fonctionnalite;
	}
}
