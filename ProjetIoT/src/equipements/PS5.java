package equipements;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Equipement;

public class PS5 extends Equipement implements Serializable {

	private static final long serialVersionUID = -8852582871510136171L;
	private String jeu;
	private Map<String, String> jeux = new HashMap<String, String>();
	private ImageView imageView = new ImageView();

	public PS5(String nom) {
		super(nom);
		jeux.put("FIFA 2021", "It's in the game");
		jeux.put("Call of Duty Modern Warfare V", "bam bam bam");
		jeux.put("Mario Kart X", "Mariooooo");
		jeux.put("Just Dance 8", "La la la la");
		this.setJeu((String) getJeux().keySet().toArray()[0]);
		this.setPositionHorizontale(0.58);
		this.setPositionVerticale(0.34);
	}

	public PS5(String nom, boolean etatCourant, double positionHorizontale, double positionVerticale, String jeu) {
		super(nom, etatCourant, positionVerticale, positionVerticale);
		this.setJeu(jeu);
	}

	@Override
	public String actionsPossibles() {
		return super.actionsPossibles() + "\n➡️ 4 : Lancer jeu";
	}

	public String getJeu() {
		return jeu;
	}

	public void setJeu(String jeu) {
		if (jeux.containsKey(jeu)) {
			this.jeu = jeu;
		} else {
			System.out.println("Votre collection de jeux vidéos ne possède pas ce titre");
		}
	}

	public void choisirJeu(String jeu) {
		if (super.isEtatCourant()) {
			setJeu(jeu);
		} else {
			System.out.println(this.getNom() + " est éteinte, on ne peut pas lancer un jeu");
		}
	}

	public Map<String, String> getJeux() {
		return jeux;
	}

	public void setJeux(Map<String, String> jeux) {
		this.jeux = jeux;
	}

	public ImageView afficher() {
		imageView.setImage(new Image(getImage()));
		imageView.setTranslateY(90);
		imageView.setTranslateX(40);
		return imageView;
	}

	@Override
	public Button getButton() {
		Button but = super.getButton();
		but.setTranslateX(200);
		but.setTranslateY(500);
		return but;
	}
}
