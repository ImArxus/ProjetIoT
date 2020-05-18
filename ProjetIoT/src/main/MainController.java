package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {

	private static ListeUtilisateurs listeUtilisateurs = new ListeUtilisateurs();

	@FXML
	private Label loginTxt;

	@FXML
	private TextField userTxt;
	private String user = userTxt.getText();

	@FXML
	private PasswordField passwordTxt;
	private String password = passwordTxt.getText();

	public void login(ActionEvent e) {
		if (listeUtilisateurs.comptes.containsKey(user) && listeUtilisateurs.comptes.get(user).equals(password)) {
			loginTxt.setText("Bienvenue " + user + " !");
		} else {
			loginTxt.setText("Identifiant ou mot de passe incorrect");
		}
	}

}
