package equipements;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

public class PS5 extends Equipement implements Serializable {

	private static final long serialVersionUID = -8852582871510136171L;
	private String jeu;
	private List<String> jeux = new LinkedList<String>();
	private int indice;

	public PS5(String nom) {
		super(nom);
		getJeux().add("/images/objets/equipements.PS5.jeu4.png");
		getJeux().add("/images/objets/equipements.PS5.jeu3.png");
		getJeux().add("/images/objets/equipements.PS5.jeu2.png");
		getJeux().add("/images/objets/equipements.PS5.jeu1.png");
		this.setJeu(getJeux().get(0));
		indice = 0;
	}

	public PS5(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale, String jeu) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setJeu(jeu);
	}

	public String getJeu() {
		return jeu;
	}

	public void setJeu(String jeu) {
		this.jeu = jeu;
	}

	public List<String> getJeux() {
		return jeux;
	}

	public void setJeux(List<String> jeux) {
		this.jeux = jeux;
	}

	@Override
	public ImageView afficher() {
		getImageView().setImage(new Image(getImage()));
		getImageView().setTranslateY(90);
		getImageView().setTranslateX(40);
		return getImageView();
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(200);
		but.setTranslateY(500);
		return but;
	}

	@Override
	public String getImage() {
		return "/images/objets/equipements.PS5.png";
	}

	@Override
	public MenuButton getFonctionnalites(Pane root, ImageView img) {
		MenuButton fonctionnalite = super.getFonctionnalites(root, img);
		LinkedList<Equipement> equip = Main.getPosition().getEquipements();
		LinkedList<TV> listeTV = new LinkedList<TV>();

		if (isEtatCourant()) {
			MenuItem seConnecter = new MenuItem("Connecter à la télé");
			seConnecter.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Iterator<Equipement> it = equip.iterator();
					while (it.hasNext()) {
						Equipement e = it.next();
						String tmp = e.getClass().getSimpleName();
						if (tmp.equals("TV")) {
							listeTV.add((TV) e);
						}
					}
					if (!listeTV.isEmpty()) {
						Iterator<TV> it2 = listeTV.iterator();
						while (it2.hasNext()) {
							TV e = it2.next();
							if (e.isEtatCourant()) {
								e.setImage("/images/objets/equipements.TV.psn.png");
								root.getChildren().remove(e.afficher());
								root.getChildren().add(e.afficher());
							}
						}
					}
				}
			});
			MenuItem changerDisque = new MenuItem("Changer disque");
			changerDisque.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					indice++;
					if (indice == getJeux().size()) {
						indice = 0;
					}
					setJeu(getJeux().get(indice));
				}
			});
			MenuItem lancerJeu = new MenuItem("Lancer Jeu");
			lancerJeu.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Iterator<Equipement> it = equip.iterator();
					while (it.hasNext()) {
						Equipement e = it.next();
						String tmp = e.getClass().getSimpleName();
						if (tmp.equals("TV")) {
							listeTV.add((TV) e);
						}
					}
					if (!listeTV.isEmpty()) {
						Iterator<TV> it2 = listeTV.iterator();
						while (it2.hasNext()) {
							TV e = it2.next();
							if (e.isEtatCourant()) {
								e.setImage(getJeux().get(indice));
								root.getChildren().remove(e.afficher());
								root.getChildren().add(e.afficher());
							}
						}
					}
				}
			});
			fonctionnalite.getItems().addAll(seConnecter, changerDisque, lancerJeu);
		}
		return fonctionnalite;
	}
}
