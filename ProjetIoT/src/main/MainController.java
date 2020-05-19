package main;

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
	@FXML
	private PasswordField passwordTxt;

	public void login() {
		if (listeUtilisateurs.comptes.containsKey(userTxt.getText())
				&& listeUtilisateurs.comptes.get(userTxt.getText()).equals(passwordTxt.getText())) {
			loginTxt.setText("Bienvenue " + userTxt.getText() + " !");
		} else {
			loginTxt.setText("Identifiant ou mot de passe incorrect");
		}
	}

	public void creerCompte() {
		ListeUtilisateurs ListeUser = new ListeUtilisateurs();
		if (!listeUtilisateurs.comptes.containsKey(userTxt.getText()) && !passwordTxt.getText().isEmpty()) {
			ListeUser.comptes.put(userTxt.getText(), passwordTxt.getText());
			ListeUtilisateurs.getAdmin().put(userTxt.getText(), false);
			Sauvegarde.sauvegarderCompte();
			System.out.println("Féliciations, vous avez maintenant un compte utilisateur !");
		} else {
			loginTxt.setText("Identifiant déjà existant");
		}
	}

}
