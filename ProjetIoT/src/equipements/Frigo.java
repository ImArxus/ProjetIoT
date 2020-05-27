package equipements;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
	private static ListView<String> listeDispo;

	public Frigo(String nom) {
		super(nom);
		setEtatCourant(true);
		setTemperature(5);
		getDispo().put("Banane", 3);
		getDispo().put("Yaourt", 1);
		getDispo().put("Salade", 1);
		getDispo().put("Tomate", 3);
		getTempBar().setLayoutX(215);
		getTempBar().setLayoutY(250);
		getTempBar().setPrefSize(80, 15);
		setListeDispo(new ListView<String>());
	}

	protected Frigo(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale,
			double temperature, Map<String, Integer> dispo) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		setTemperature(temperature);
		getTempBar().setLayoutX(215);
		getTempBar().setLayoutY(250);
		getTempBar().setPrefSize(80, 15);
		getListeDispo().setLayoutX(207);
		getListeDispo().setLayoutY(310);
		getListeDispo().setPrefSize(100, 200);
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

	public static ListView<String> getListeDispo() {
		return listeDispo;
	}

	public static void setListeDispo(ListView<String> listeDispo) {
		Frigo.listeDispo = listeDispo;
		getListeDispo().setLayoutX(207);
		getListeDispo().setLayoutY(310);
		getListeDispo().setPrefSize(100, 200);
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

	@Override
	public ImageView afficher() {
		getImageView().setImage(new Image(getImage()));
		getImageView().setTranslateY(140);
		getImageView().setTranslateX(-100);
		return getImageView();
	}

	@Override
	public MenuButton getFonctionnalites(Pane root, ImageView imv) {
		MenuButton fonctionnalite = super.getFonctionnalites(root, imv);

		if (isEtatCourant()) {
			MenuItem baisserTemperature = new MenuItem("Baisser temperature");
			baisserTemperature.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					diminuerTemperature(root);
				}
			});
			MenuItem augmenterTemperature = new MenuItem("Augmenter temperature");
			augmenterTemperature.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					augmenterTemperature(root);
				}
			});
			MenuItem commander = new MenuItem("Commander");
			commander.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if (root.getChildren().contains(getListeDispo())) {
						root.getChildren().remove(getListeDispo());
					}
					MenuButton produits = new MenuButton("Commander");
					produits.setLayoutX(207);
					produits.setLayoutY(310);
					produits.setPrefSize(100, 25);
					MenuItem banane = new MenuItem("Banane");
					banane.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent evt) {
							commander("Banane", 1);
							root.getChildren().remove(produits);
						}
					});
					MenuItem yaourt = new MenuItem("Yaourt");
					yaourt.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent evt) {
							commander("Yaourt", 1);
							root.getChildren().remove(produits);
						}
					});
					MenuItem salade = new MenuItem("Salade");
					salade.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent evt) {
							commander("Salade", 1);
							root.getChildren().remove(produits);
						}
					});
					MenuItem tomate = new MenuItem("Tomate");
					tomate.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent evt) {
							commander("Tomate", 1);
							root.getChildren().remove(produits);
						}
					});
					MenuItem dinde = new MenuItem("Dinde");
					dinde.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent evt) {
							commander("Dinde", 1);
							root.getChildren().remove(produits);
						}
					});
					produits.getItems().addAll(banane, yaourt, salade, tomate, dinde);
					root.getChildren().add(produits);
				}
			});
			MenuItem listerProduits = new MenuItem("Lister produits");
			listerProduits.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					setListeDispo(new ListView<String>());
					Set<String> produits = getDispo().keySet();
					Iterator<String> it = produits.iterator();
					while (it.hasNext()) {
						String produit = it.next();
						getListeDispo().getItems().add(produit + " => " + getDispo().get(produit));
					}
					root.getChildren().add(getListeDispo());
				}
			});
			fonctionnalite.getItems().addAll(baisserTemperature, augmenterTemperature, listerProduits, commander);
		}
		return fonctionnalite;
	}

}
