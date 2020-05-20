package main;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import equipements.Alexa;
import equipements.Cheminee;
import equipements.Enceinte;
import equipements.Lumiere;
import equipements.PS5;
import equipements.Radiateur;
import equipements.TV;
import equipements.Ventilateur;
import equipements.Volet;

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
			Main.setPseudo(userTxt.getText());
			// loginTxt.setText("Bienvenue " + userTxt.getText() + " !");

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
		// choixTxt.setText("Bienvenue dans la maison de Barry !");
		Main.setMaison(BarryHouse.creerMaison());

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

		Label pseudoAffichage = new Label();// gestion affichage pseudo
		pseudoAffichage.setText(Main.getPseudo());
		pseudoAffichage.setTranslateX(100);
		pseudoAffichage.setTranslateY(12);
		root.getChildren().add(pseudoAffichage);

		Label positionAffichage = new Label();// gestion affichage position
		positionAffichage.setText(Main.getPosition().getNom());
		positionAffichage.setTranslateX(600);
		positionAffichage.setTranslateY(12);
		root.getChildren().add(positionAffichage);

		Label nomMaisonAffichage = new Label();// gestion affichage nom maison
		nomMaisonAffichage.setText(Main.getMaison().getNom());
		nomMaisonAffichage.setTranslateX(370);
		nomMaisonAffichage.setTranslateY(12);
		root.getChildren().add(nomMaisonAffichage);

		Label heureAffichage = new Label();// gestion affichage heure
		heureAffichage.setText(String.valueOf(Main.getHeure()));
		heureAffichage.setTranslateX(740);
		heureAffichage.setTranslateY(50);
		root.getChildren().add(heureAffichage);

		Label temperatureAffichage = new Label();// gestion affichage temperature piece
		temperatureAffichage.setText(String.valueOf(Main.getPosition().getTemperature()));
		temperatureAffichage.setTranslateX(240);
		temperatureAffichage.setTranslateY(50);
		root.getChildren().add(temperatureAffichage);

		Label ilAffichage = new Label();// gestion affichage intensitelumineuse piece
		ilAffichage.setText(String.valueOf(Main.getPosition().getIntensiteLumineuse()));
		ilAffichage.setTranslateX(600);
		ilAffichage.setTranslateY(50);
		root.getChildren().add(ilAffichage);
	
		
		/*Volet a = new Volet("Volet");
		root.getChildren().add(a.afficher());
		TV c = new TV("TV");
		root.getChildren().add(c.afficher());
		Radiateur d = new Radiateur("radia");
		root.getChildren().add(d.afficher());
		PS5 e = new PS5("PS5");
		root.getChildren().add(e.afficher());
		Enceinte d1 = new Enceinte("Enceinte");
		root.getChildren().add(d1.afficher());
		Lumiere d2 = new Lumiere("lum");
		root.getChildren().add(d2.afficher());
		Cheminee d3 = new Cheminee("lum");
		root.getChildren().add(d3.afficher());
		Ventilateur b = new Ventilateur("vent");
		root.getChildren().add(b.afficher());
		Alexa d4 = new Alexa("Alexa");
		root.getChildren().add(d4.afficher());*/


		LinkedList<Label> liste = affichageBande(event);
		for (int i = 0; i < liste.size(); i++) {
			root.getChildren().add(liste.get(i));
		}
		
		LinkedList<Equipement> equip = Main.getPosition().getEquipements();//afficher equipements
		for (int i = 0; i < liste.size(); i++) {
			root.getChildren().add(equip.get(i).afficher());
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
