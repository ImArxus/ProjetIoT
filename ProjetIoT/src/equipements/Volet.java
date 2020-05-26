package equipements;

import java.io.Serializable;

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

public class Volet extends Equipement implements Serializable {

	private static final long serialVersionUID = 4803149899705207022L;
	private int position;
	private transient ImageView imageView = new ImageView();

	public Volet(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale, int position) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setPosition(position);
	}

	public Volet(String nom) {
		super(nom);
		this.setPosition(0);
		this.setPositionHorizontale(0.95);
		this.setPositionVerticale(0.4);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n➡️ 4 : Monter volet\n➡️ 5 : Descendre volet\n➡️ 6 : Choisir position";
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void monterVolet() {
		if (getPosition() <= 4) {
			setPosition(getPosition() - 1);
		}
	}

	public void descendreVolet() {
		if (getPosition() >= 0) {
			setPosition(getPosition() + 1);
		}
	}

	public void choisirPosition(int position) {
		if (super.isEtatCourant()) {
			if (position <= 4 && position >= 0) {
				setPosition(position);
			} else {
				System.out.println("Position non-valide");
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas changer de position");
		}
	}

	@Override
	public ImageView afficher() {
		imageView.setImage(new Image(getImage()));
		imageView.setTranslateY(70);
		imageView.setTranslateX(370);
		return imageView;
	}

	public ImageView getImageView() {
		imageView.setImage(new Image(getImage()));
		return imageView;
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(700);
		but.setTranslateY(500);
		return but;
	}

	@Override
	public String getImage() {
		boolean nuit = false;
		if (isEtatCourant()) {
			if (Main.getHeure() >= 21 || Main.getHeure() <= 8) {
				nuit = true;
			}
		}
		if (isEtatCourant()) {
			if (getPosition() == 0) {
				if (nuit) {
					return "/images/objets/equipements.Volet.nuit.desactive.png";
				} else {
					return "/images/objets/equipements.Volet.desactive.png";
				}
			} else if (getPosition() == 1) {
				if (nuit) {
					return "/images/objets/equipements.Volet.nuit.position1.png";
				} else {
					return "/images/objets/equipements.Volet.position1.png";
				}
			} else if (getPosition() == 2) {
				if (nuit) {
					return "/images/objets/equipements.Volet.nuit.position2.png";
				} else {
					return "/images/objets/equipements.Volet.position2.png";
				}
			} else if (getPosition() == 3) {
				if (nuit) {
					return "/images/objets/equipements.Volet.nuit.position3.png";
				} else {
					return "/images/objets/equipements.Volet.position3.png";
				}
			}
		}
		return "/images/objets/equipements.Volet.png";
	}

	@Override
	public MenuButton getFonctionnalites(Pane root, ImageView img) {
		MenuButton fonctionnalite = super.getFonctionnalites(root, img);

		if (isEtatCourant()) {
			MenuItem monterVolet = new MenuItem(" Monter Volet");
			monterVolet.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					monterVolet();
					root.getChildren().remove(img);
					root.getChildren().add(afficher());
				}
			});
			MenuItem descendreVolet = new MenuItem(" Descendre Volet");
			descendreVolet.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					descendreVolet();
					root.getChildren().remove(img);
					root.getChildren().add(afficher());
				}
			});
			fonctionnalite.getItems().addAll(monterVolet, descendreVolet);
		}
		return fonctionnalite;
	}
}
