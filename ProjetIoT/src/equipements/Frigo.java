package equipements;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

public class Frigo extends Equipement implements Serializable {

	private static final long serialVersionUID = -4392838038317445404L;
	private double temperature;
	private Map<String, Integer> dispo = new HashMap<String, Integer>();
	private static ProgressBar tempBar = new ProgressBar(0);

	public Frigo(String nom) {
		super(nom);
		setTemperature(5);
		getDispo().put("Banane", 3);
		getDispo().put("Yaourt", 1);
		getDispo().put("Salade", 1);
		getDispo().put("tomates", 3);
		getTempBar().setLayoutX(215);
		getTempBar().setLayoutY(250);
		getTempBar().setPrefSize(80, 15);
	}

	protected Frigo(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale,
			double temperature, Map<String, Integer> dispo) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		setTemperature(temperature);
		getTempBar().setLayoutX(215);
		getTempBar().setLayoutY(250);
		getTempBar().setPrefSize(80, 15);
	}

	@Override
	public String toString() {
		String etat = "fermé";
		if (isEtatCourant()) {
			etat = "ouvert";
		}
		return getNom() + " (" + etat + ")";
	}

	public static ProgressBar getTempBar() {
		return tempBar;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public Map<String, Integer> getDispo() {
		return dispo;
	}

	public void setDispo(Map<String, Integer> dispo) {
		this.dispo = dispo;
	}

	public void augmenterTemperature(Pane root) {
		if (super.isEtatCourant()) {
			if (getTemperature() < 10) {
				setTemperature(getTemperature() + 1);
				getTempBar().setProgress(getTemperature() / 10);
				try {
					root.getChildren().add(getTempBar());
				} catch (Exception e) {
					System.err.println("Erreur lors de l'ajout de la barre de température");
				}
			}
		}
	}

	public void diminuerTemperature(Pane root) {
		if (super.isEtatCourant()) {
			if (getTemperature() > 0) {
				setTemperature(getTemperature() - 1);
				getTempBar().setProgress(getTemperature() / 10);
			}
			try {
				root.getChildren().add(getTempBar());
			} catch (Exception e) {
				System.err.println("Erreur lors de l'ajout de la barre de température");
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}

	public void commander(String produit, int quantite) {
		boolean trouve = false;
		Map<String, Integer> test = getDispo();
		Set<String> cles = test.keySet();
		Iterator<String> it = cles.iterator();
		while (it.hasNext()) {
			String cle = it.next();
			int valeur = test.get(cle);
			if (cle.equals(produit)) {
				test.put(cle, valeur + quantite);
				trouve = true;
			}
		}
		if (trouve == false) {
			test.put(produit, quantite);
		}
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(700);
		but.setTranslateY(450);
		return but;
	}

	@Override
	public String getImage() {
		return "/images/objets/" + this.getClass().getName() + ".png";
	}

	public ImageView afficher() {
		getImageView().setImage(new Image(getImage()));
		getImageView().setTranslateY(140);
		getImageView().setTranslateX(-100);
		return getImageView();
	}

	@Override
	public MenuButton getFonctionnalites(Pane root, ImageView imv) {
		MenuButton fonctionnalite = super.getFonctionnalites(root, imv);

		MenuItem baisserTemperature = new MenuItem(" Baisser temperature");
		baisserTemperature.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				diminuerTemperature(root);
			}
		});
		MenuItem augmenterTemperature = new MenuItem(" Augmenter temperature");
		augmenterTemperature.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				augmenterTemperature(root);
			}
		});
		// TODO
		MenuItem commander = new MenuItem(" Commander");
		commander.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				/**
				 * Scanner s = new Scanner(System.in); System.out.println("Que voulez vous
				 * commander ?"); String requete1 = s.nextLine(); System.out.println("En quelle
				 * quantitée ?"); int requete2 = Main.toInt(s.nextLine()); commander(requete1,
				 * requete2); System.out.println("Dans " + getNom() + ", il y a maintenant " +
				 * getDispo()); s.close();
				 */
			}
		});
		// TODO
		MenuItem listerProduits = new MenuItem(" Lister produits");
		listerProduits.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Dans " + getNom() + ", il y a " + getDispo());
			}
		});
		fonctionnalite.getItems().addAll(baisserTemperature, augmenterTemperature, listerProduits, commander);
		return fonctionnalite;
	}

}
