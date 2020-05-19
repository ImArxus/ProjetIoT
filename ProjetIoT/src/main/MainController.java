package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {

	ListeUtilisateurs listeUtilisateur = Main.getListeUtilisateur();

	@FXML
	private Label loginTxt;
	@FXML
	private TextField userTxt;
	@FXML
	private PasswordField passwordTxt;

	@FXML
	private Label creationTxt;
	@FXML
	private TextField newUserTxt;
	@FXML
	private PasswordField newPasswordTxt;

	public Scene choixScene(String url) {
		try {
			return new Scene(FXMLLoader.load(getClass().getResource(url)));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void login() {
		if (listeUtilisateur.comptes.containsKey(userTxt.getText())
				&& listeUtilisateur.comptes.get(userTxt.getText()).equals(passwordTxt.getText())) {
			loginTxt.setText("Bienvenue " + userTxt.getText() + " !");
		} else {
			loginTxt.setText("Identifiant ou mot de passe incorrect");
		}
	}

	public void versCreation(ActionEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = choixScene("/main/CreationCompte.fxml");
		window.setTitle("Créer un compte");
		window.setScene(scene);
		window.show();
	}

	public void creerCompte() {
		if (!listeUtilisateur.comptes.containsKey(newUserTxt.getText()) && !newPasswordTxt.getText().isEmpty()) {
			listeUtilisateur.comptes.put(newUserTxt.getText(), newPasswordTxt.getText());
			ListeUtilisateurs.getAdmin().put(newUserTxt.getText(), false);
			Sauvegarde.sauvegarderCompte();
			creationTxt.setText("Féliciations, vous avez maintenant un compte utilisateur !");
		} else {
			creationTxt.setText("Identifiant déjà existant");
		}
	}

}
