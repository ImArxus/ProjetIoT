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

public class Volet extends Equipement implements Serializable {

	private static final long serialVersionUID = 4803149899705207022L;
	private int position;

	public Volet(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale, int position) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setPosition(3);
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
		if (super.isEtatCourant()) {
			if (getPosition() < 3) {
				position ++;
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas monter le volet");
		}
	}

	public void descendreVolet() {
		if (super.isEtatCourant()) {
			if (getPosition() > 0) {
				position --;
			}
		} else {
			System.out.println(this.getNom() + " est éteint, on ne peut pas baisser le volet");
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
		ImageView imageView = new ImageView();
		imageView.setImage(new Image(getImage()));
		imageView.setTranslateY(70);
		imageView.setTranslateX(370);
		return imageView;
	}

	public ImageView getImageView() {
		ImageView i0 = new ImageView();
		i0.setImage(new Image(getImage()));
		return i0;
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
		if (getPosition() == 0) {
			return ("/images/objets/equipements.Volet.desactive.png");
		} else if (getPosition() == 1) {
			return ("/images/objets/equipements.Volet.position1.png");
		} else if (getPosition() == 2) {
			return ("/images/objets/equipements.Volet.position2.png");
		} else if (getPosition() == 3) {
			return ("/images/objets/equipements.Volet.position3.png");
		} else {
			return ("/images/objets/equipements.Volet.png");
		}
	}
	@Override
	public MenuButton getFonctionnalitées(Pane root, ImageView img) {
		MenuButton fonctionnalite = super.getFonctionnalitées(root, img);

		MenuItem monterVolet = new MenuItem(" Monter Volet");
		monterVolet.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				monterVolet();
			}
		});
		MenuItem descendreVolet = new MenuItem(" Descendre Volet");
		descendreVolet.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				descendreVolet();
			}
		});
		MenuItem choisirPosition = new MenuItem(" Choisir position");
		choisirPosition.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Scanner s = new Scanner(System.in);
				System.out.println("Quelle position (entre 0 et 4) ?");
				int position = Main.toInt(s.nextLine());
				choisirPosition(position);
				s.close();}
		});
		
		fonctionnalite.getItems().addAll(monterVolet, descendreVolet,choisirPosition);
		return fonctionnalite;
	}
}
