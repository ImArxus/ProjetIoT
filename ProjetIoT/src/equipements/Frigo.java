package equipements;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
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
import main.Main;

public class Frigo extends Equipement implements Serializable {

	private static final long serialVersionUID = -4392838038317445404L;
	private double temperature;
	private Map<String, Integer> dispo = new HashMap<String, Integer>();
	private static ProgressBar volumeBar = new ProgressBar(0);


	public Frigo(String nom) {
		super(nom);
		setTemperature(5);
		dispo.put("Banane", 3);
		dispo.put("Yaourt", 1);
		dispo.put("Salade", 1);
		dispo.put("tomates", 3);
		this.setPositionHorizontale(0.25);
		this.setPositionVerticale(0.3);
		volumeBar.setLayoutX(215);
		volumeBar.setLayoutY(250);
		volumeBar.setPrefSize(80, 15);
	}

	protected Frigo(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale,
			double temperature, Map<String, Integer> dispo) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		setTemperature(temperature);
		volumeBar.setLayoutX(215);
		volumeBar.setLayoutY(250);
		volumeBar.setPrefSize(80, 15);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles()
				+ "\n➡️ 4 : Baisser temperature\n➡️ 5 : Augmenter temperature\n➡️ 6 : Lister produits\n➡️ 7 : Commander";
	}

	@Override
	public String toString() {
		String etat = "fermé";
		if (isEtatCourant()) {
			etat = "ouvert";
		}
		return getNom() + " (" + etat + ")";
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	public void augmenterTemperatureFX(Pane root) {
		if (super.isEtatCourant()) {
			if (getTemperature() < 10) {
				temperature += 1;
				volumeBar.setProgress(getTemperature() / 10);
				try {
					root.getChildren().add(volumeBar);
				} catch (Exception e) {
				}
			}
		}
	}
	
	public void diminuerTemperatureFX(Pane root) {
		if (super.isEtatCourant()) {
			if (getTemperature() > 0) {
				temperature -= 1;
				volumeBar.setProgress(getTemperature() / 10);
			}
			try {
				root.getChildren().add(volumeBar);
			} catch (Exception e) {
			}
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas changer de chaine");
		}
	}
	

	public Map<String, Integer> getDispo() {
		return dispo;
	}

	public void setDispo(Map<String, Integer> dispo) {
		this.dispo = dispo;
	}

	public void augmenterTemperature() {
		if (getTemperature() < 10) {
			temperature++;
		} else {
			System.out.println(this.getNom() + "La temperature est au max, on ne peut pas l'augmenter");
		}
	}

	public void diminuerTemperature() {
		if (getTemperature() > -5) {
			temperature--;
		} else {
			System.out.println(this.getNom() + "La temperature est au min, on ne peut pas la diminuer");
		}
	}

	public void commander(String a, int b) {
		boolean trouve = false;
		Map<String, Integer> test = dispo;
		Set<String> cles = test.keySet();
		Iterator<String> it = cles.iterator();
		while (it.hasNext()) {
			String cle = it.next();
			int valeur = test.get(cle);
			if (cle.equals(a)) {
				test.put(cle, valeur + b);
				trouve = true;
			}
		}
		if (trouve == false) {
			test.put(a, b);
		}
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
		but.setTranslateY(450);
		return but;
	}
	@Override
	public MenuButton getFonctionnalitées(Pane root, ImageView imv) {
		MenuButton fonctionnalite = super.getFonctionnalitées(root, imv);

		MenuItem baisserTemperature = new MenuItem(" Baisser temperature");
		baisserTemperature.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				diminuerTemperatureFX(root);
				System.out.println("La temperature du frigo " + getNom() + " est de " + getTemperature());
			}
		});
		MenuItem augmenterTemperature = new MenuItem(" Augmenter temperature");
		augmenterTemperature.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				augmenterTemperatureFX(root);
				System.out.println("La temperature du frigo " + getNom() + " est de " + getTemperature());
			}
		});
		MenuItem commander = new MenuItem(" Commander");
		commander.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent event) {
				Scanner s = new Scanner(System.in);
				System.out.println("Que voulez vous commander ?");
				String requete1 = s.nextLine();
				System.out.println("En quelle quantitée ?");
				int requete2 = Main.toInt(s.nextLine());
				commander(requete1, requete2);
				System.out.println("Dans " + getNom() + ", il y a maintenant " + getDispo());
				s.close();
			}
		});
		MenuItem listerProduits = new MenuItem(" Lister produits");
		listerProduits.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Dans " + getNom() + ", il y a " + getDispo());
			}
		});
		fonctionnalite.getItems().addAll(baisserTemperature,augmenterTemperature,listerProduits,commander);
		return fonctionnalite;
	}
	public ImageView afficher() {
		ImageView imageView = new ImageView();
		imageView.setImage(new Image(getImage()));
		imageView.setTranslateY(140);
		imageView.setTranslateX(-100);
		return imageView;
	}
}
