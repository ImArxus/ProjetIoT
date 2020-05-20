package main;

import java.io.IOException;

import com.sun.javafx.scene.control.LabeledText;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController {
	private static String pseudo;


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
			loginTxt.setText("Bienvenue " + userTxt.getText() + " !");
			versChoixMaison(event);
		} else {
			loginTxt.setText("Identifiant ou mot de passe incorrect");
		}
	}

	public void versMaisonBarry(ActionEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Pane root = (Pane) getRoot("/main/Maison.fxml");
		Scene scene = new Scene(root);
		
		ImageView imageView = new ImageView();
        imageView.setImage(new Image("/images/vide.png"));
        imageView.setLayoutY(100);
        imageView.setFitWidth(800);
        imageView.setFitHeight(500);
        
        root.getChildren().add(imageView);

        
		window.setTitle("Barry's House");
		window.setScene(scene);
		window.show();
	}

	public void versMaisonVide(ActionEvent event) {
		choixTxt.setText("Votre maison de rêve n'attend que vous !");
	}

	public void versMaisonChargee(ActionEvent event) {
		choixTxt.setText("Votre maison est chargée !");
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

}
