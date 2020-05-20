package main;

import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pieces.Salon;

public class MainController {

	ListeUtilisateurs listeUtilisateur = Main.getListeUtilisateur();

	/**
	 * Champs page Login
	 */
	@FXML
	private Label loginTxt;
	@FXML
	private TextField userTxt;
	@FXML
	private PasswordField passwordTxt;

	/**
	 * Champs page Création de compte
	 */
	@FXML
	private Label creationTxt;
	@FXML
	private TextField newUserTxt;
	@FXML
	private PasswordField newPasswordTxt;

	/**
	 * Champs page Choix maison
	 */
	@FXML
	private Label choixTxt;

	public Parent getRoot(String url) {
		try {
			return FXMLLoader.load(getClass().getResource(url));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void login(ActionEvent event) throws InterruptedException {
		if (listeUtilisateur.comptes.containsKey(userTxt.getText())
				&& listeUtilisateur.comptes.get(userTxt.getText()).equals(passwordTxt.getText())) {
			Main.setPseudo(userTxt.getText());
			versChoixMaison(event);
		} else {
			loginTxt.setText("Identifiant ou mot de passe incorrect");
		}
	}

	public void invite(ActionEvent event) {
		Main.setPseudo("guest");
		versChoixMaison(event);
	}

	public void versChoixMaison(ActionEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(getRoot("/main/ChoixMaison.fxml"));
		window.setTitle("Choix Maison");
		window.setScene(scene);
		window.show();
	}

	public void versCreation(ActionEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(getRoot("/main/CreationCompte.fxml"));
		window.setTitle("Créer un compte");
		window.setScene(scene);
		window.show();
	}

	public void versLogin(ActionEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(getRoot("/main/Login.fxml"));
		window.setTitle("Login");
		window.setScene(scene);
		window.show();
	}

	public void versMaisonBarry(ActionEvent event) {
		Main.setMaison(BarryHouse.creerMaison()); // Crée la maison avec un salon
		Main.setPosition(Main.getMaison().getPieces().get(0));
		creerMaison(event);
	}

	public void versMaisonVide(ActionEvent event) {
		// Donne un titre à la fenêtre avec le pseudo
		((Stage) ((Node) event.getSource()).getScene().getWindow()).setTitle("Maison de " + Main.getPseudo());

		Main.setMaison(new Maison("Maison de " + Main.getPseudo(), new Salon("Salon"))); // Crée la maison avec un salon
		Main.setPosition(Main.getMaison().getPieces().get(0));
		creerMaison(event);
	}


	public void versMaisonChargee(ActionEvent event) {//BUUUUUG
		Main.setMaison(Sauvegarde.chargerMAISON());
		Main.setPosition(Main.getMaison().getPieces().get(0));
		creerMaison(event);
	}

	public void creerMaison(ActionEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Pane root = (Pane) getRoot("/main/Maison.fxml");
		Scene scene = new Scene(root);

		ImageView imageView = new ImageView();
		imageView.setImage(new Image("/images/piece.png"));
		imageView.setFitWidth(800);
		imageView.setFitHeight(600);

		root.getChildren().add(imageView);
		LinkedList<Label> liste = affichageBande(event);
		for (int i = 0; i < liste.size(); i++) {
			root.getChildren().add(liste.get(i));
		}
		
		LinkedList<Equipement> equip = Main.getPosition().getEquipements();//afficher equipements
		for (int i = 0; i < liste.size(); i++) {
			root.getChildren().add(equip.get(i).getImageView());
		}
		window.setTitle("Barry House");
		window.setScene(scene);
		window.show();
	}

	public void creerCompte() {
		if (!listeUtilisateur.comptes.containsKey(newUserTxt.getText())) {
			if (newUserTxt.getText().isEmpty()) {
				creationTxt.setText("Veuillez entrer un identifiant");
			} else if (newPasswordTxt.getText().isEmpty()) {
				creationTxt.setText("Veuillez entrer un mot de passe");
			} else {
				listeUtilisateur.comptes.put(newUserTxt.getText(), newPasswordTxt.getText());
				ListeUtilisateurs.getAdmin().put(newUserTxt.getText(), false);
				Sauvegarde.sauvegarderCompte();
				creationTxt.setText("Féliciations, vous avez maintenant un compte utilisateur !");
			}
		} else {
			creationTxt.setText("Identifiant déjà existant");
		}
	}

	public LinkedList<Label> affichageBande(ActionEvent event) {
		LinkedList<Label> liste = new LinkedList<Label>();

		Label lbl1 = new Label();
		lbl1.setText(Main.getPseudo());
		lbl1.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl1.setLayoutX(85);
		lbl1.setLayoutY(10);
		Label lbl2 = new Label();
		lbl2.setText(Main.getMaison().getNom());
		lbl2.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl2.setLayoutX(310);
		lbl2.setLayoutY(10);
		Label lbl3 = new Label();
		lbl3.setText(Main.getPosition().getNom());
		lbl3.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl3.setLayoutX(570);
		lbl3.setLayoutY(10);
		Label lbl4 = new Label();
		lbl4.setText("" + Main.getPosition().getTemperature());
		lbl4.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl4.setLayoutX(205);
		lbl4.setLayoutY(47);
		Label lbl5 = new Label();
		lbl5.setText("" + Main.getPosition().getIntensiteLumineuse());
		lbl5.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl5.setLayoutX(570);
		lbl5.setLayoutY(47);
		Label lbl6 = new Label();
		lbl6.setText("" + Main.getHeure() + "h");
		lbl6.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl6.setLayoutX(700);
		lbl6.setLayoutY(47);

		liste.add(lbl1);
		liste.add(lbl2);
		liste.add(lbl3);
		liste.add(lbl4);
		liste.add(lbl5);
		liste.add(lbl6);

		return liste;
	}

}
