package equipements;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Equipement;

public class Balance extends Equipement implements Serializable {

	private static final long serialVersionUID = -8591722619347662110L;
	private int poids;
	List<Integer> listePoids = new LinkedList<Integer>();

	public Balance(String nom) {
		super(nom);
		getListePoids().add(80);
		getListePoids().add(60);
		getListePoids().add(100);
		getListePoids().add(110);
		this.setPoids();
	}

	public Balance(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale,
			List<Integer> listePoids) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setListePoids(listePoids);
		setPoids();
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids() {
		int nombreAleatoire = (int) (Math.random() * (getListePoids().size()));
		int valeur = getListePoids().get(nombreAleatoire);
		if (getListePoids().contains(valeur)) {
			this.poids = valeur;
		} else {
			System.err.println("Erreur de calcul");
		}
	}

	public void peser() {
		if (super.isEtatCourant()) {
			setPoids();
		}
	}

	public List<Integer> getListePoids() {
		return listePoids;
	}

	public void setListePoids(List<Integer> listePoids) {
		this.listePoids = listePoids;
	}

	@Override
	public String getImage() {
		return "/images/objets/equipements.Balance.png";
	}

	@Override
	public ImageView afficher() {
		getImageView().setImage(new Image(getImage()));
		getImageView().setTranslateY(185);
		getImageView().setTranslateX(160);
		return getImageView();
	}
	
	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(300);
		but.setTranslateY(450);
		return but;
	}

}
