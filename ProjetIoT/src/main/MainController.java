package main;

import java.io.IOException;
import java.util.LinkedList;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pieces.Salon;

@SuppressWarnings("restriction")
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

	/**
	 * Champs page Sélection nom maison
	 */
	@FXML
	private TextField nameHouseTxt;

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
		Main.setMaison(BarryHouse.creerMaison());
		Main.setPosition(Main.getMaison().getPieces().get(0));
		scenePiece(event);
	}

	public void versMaisonVide(ActionEvent event) {
		// Donne un titre à la fenêtre avec le pseudo
		((Stage) ((Node) event.getSource()).getScene().getWindow()).setTitle("Maison de " + Main.getPseudo());

		Main.setMaison(new Maison("Maison de " + Main.getPseudo(), new Salon("Salon"))); // Crée la maison avec un salon
		Main.setPosition(Main.getMaison().getPieces().get(0));
		scenePiece(event);
	}

	public void versRetourMaison(ActionEvent event) {
		Main.getMaison().setNom(nameHouseTxt.getText());
		scenePiece(event);
	}

	public void versMaisonChargee(ActionEvent event) {
		Maison maison = Sauvegarde.chargerMaison();
		Main.setMaison(maison);
		if (maison == null) {
			choixTxt.setText("Vous n'avez pas de maison à charger...");
		} else {
			Main.setPosition(Main.getMaison().getPieces().get(0));
			scenePiece(event);
		}
	}

	public void versSelectionNomMaison(ActionEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(getRoot("/main/SelectionNomMaison.fxml"));
		window.setTitle("Modifier le nom de la maison");
		window.setScene(scene);
		window.show();
	}

	public void versSelectionPiece(ActionEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Pane root = (Pane) getRoot("/main/SelectionPiece.fxml");
		Scene scene = new Scene(root);

		LinkedList<Piece> pieceAdj = Main.getPosition().getPiecesAdj();
		if (!pieceAdj.isEmpty()) {
			for (int i = 0; i < pieceAdj.size(); i++) {
				Piece piece = pieceAdj.get(i);
				Button boutonPiece = piece.getButton();
				boutonPiece.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						Main.setPosition(piece);
						scenePiece(e);
						;
					}
				});
				root.getChildren().add(boutonPiece);
			}
		}

		window.setTitle("Déplacer vers une autre pièce");
		window.setScene(scene);
		window.show();
	}

	public void quitter(ActionEvent event) {
		Platform.exit();
		System.exit(1);
	}

	public void sauvegarder() {
		Sauvegarde.sauvegarder();
		Label lbl = new Label();
		lbl.setText(Main.getPseudo());
		lbl.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl.setLayoutX(4);
		lbl.setLayoutY(550);
	}

	public void scenePiece(ActionEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Pane root = (Pane) getRoot("/main/Maison.fxml");
		Scene scene = new Scene(root);

		ImageView imageView = Piece.imageViewPiece();
		root.getChildren().add(imageView);

		LinkedList<Label> liste = affichageBande(event);
		for (int i = 0; i < liste.size(); i++) {
			root.getChildren().add(liste.get(i));
		}

		LinkedList<Equipement> equip = Main.getPosition().getEquipements(); // Afficher equipements
		if (!equip.isEmpty()) {
			for (int i = 0; i < equip.size(); i++) {
				root.getChildren().add(equip.get(i).afficher());
			}
		}

		ImageView imageViewAvatar = new ImageView();
		imageViewAvatar.setImage(new Image("/images/avatar/" + Main.getAvatar() + ".png"));
		imageViewAvatar.setTranslateX(50);
		imageViewAvatar.setTranslateY(200);
		root.getChildren().add(imageViewAvatar);

		window.setTitle(Main.getMaison().getNom());
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
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double prochainLabel = 4; // LayoutX où insérer le prochain label

		Label pseudo = new Label();
		pseudo.setText("Pseudo :");
		pseudo.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		pseudo.setUnderline(true);
		pseudo.setLayoutX(prochainLabel);
		pseudo.setLayoutY(10);
		prochainLabel = prochainLabel + 95;
		Label lbl1 = new Label();
		lbl1.setText(Main.getPseudo());
		lbl1.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl1.setLayoutX(prochainLabel);
		lbl1.setLayoutY(10);
		prochainLabel = prochainLabel + fontLoader.computeStringWidth(lbl1.getText(), lbl1.getFont()) + 80;

		Label maison = new Label();
		maison.setText("Maison :");
		maison.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		maison.setUnderline(true);
		maison.setLayoutX(prochainLabel);
		maison.setLayoutY(10);
		prochainLabel = prochainLabel + 91;
		Label lbl2 = new Label();
		lbl2.setText(Main.getMaison().getNom());
		lbl2.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl2.setLayoutX(prochainLabel);
		lbl2.setLayoutY(10);
		prochainLabel = prochainLabel + fontLoader.computeStringWidth(lbl2.getText(), lbl2.getFont()) + 120;

		Label position = new Label();
		position.setText("Position :");
		position.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		position.setUnderline(true);
		position.setLayoutX(prochainLabel);
		position.setLayoutY(10);
		prochainLabel = prochainLabel + 102;
		Label lbl3 = new Label();
		lbl3.setText(Main.getPosition().getNom());
		lbl3.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl3.setLayoutX(prochainLabel);
		lbl3.setLayoutY(10);
		prochainLabel = 4; // Reviens à la ligne

		Label temperature = new Label();
		temperature.setText("Température :");
		temperature.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		temperature.setUnderline(true);
		temperature.setLayoutX(prochainLabel);
		temperature.setLayoutY(60);
		prochainLabel = prochainLabel + 144;
		Label lbl4 = new Label();
		lbl4.setText("" + Main.getPosition().getTemperature() + "°C");
		lbl4.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl4.setLayoutX(prochainLabel);
		lbl4.setLayoutY(60);
		prochainLabel = prochainLabel + fontLoader.computeStringWidth(lbl4.getText(), lbl4.getFont()) + 160;

		Label luminosite = new Label();
		luminosite.setText("Luminosité :");
		luminosite.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		luminosite.setUnderline(true);
		luminosite.setLayoutX(prochainLabel);
		luminosite.setLayoutY(60);
		prochainLabel = prochainLabel + 130;
		Label lbl5 = new Label();
		lbl5.setText("" + Main.getPosition().getIntensiteLumineuse() + "%");
		lbl5.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl5.setLayoutX(prochainLabel);
		lbl5.setLayoutY(60);
		prochainLabel = prochainLabel + fontLoader.computeStringWidth(lbl5.getText(), lbl5.getFont()) + 160;

		Label heure = new Label();
		heure.setText("Heure :");
		heure.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		heure.setUnderline(true);
		heure.setLayoutX(prochainLabel);
		heure.setLayoutY(60);
		prochainLabel = prochainLabel + 77;
		Label lbl6 = new Label();
		lbl6.setText("" + Main.getHeure() + "h");
		lbl6.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl6.setLayoutX(prochainLabel);
		lbl6.setLayoutY(60);

		liste.add(pseudo);
		liste.add(maison);
		liste.add(position);
		liste.add(temperature);
		liste.add(luminosite);
		liste.add(heure);

		liste.add(lbl1);
		liste.add(lbl2);
		liste.add(lbl3);
		liste.add(lbl4);
		liste.add(lbl5);
		liste.add(lbl6);

		return liste;
	}

}
