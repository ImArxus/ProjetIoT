package main;

import java.io.IOException;
import java.util.LinkedList;

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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

	private ImageView imageViewAvatar = new ImageView();

	public void afficherAvatar(Pane root) {
		if (root.getChildren().contains(imageViewAvatar)) {
			root.getChildren().remove(imageViewAvatar);
		}
		if (Main.getAvatar() != null) {
			imageViewAvatar.setImage(new Image("/images/avatar/" + Main.getAvatar() + ".png"));
			imageViewAvatar.setTranslateX(50);
			imageViewAvatar.setTranslateY(200);
			root.getChildren().add(imageViewAvatar);
		}
	}

	public void scenePiece(ActionEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Pane root = (Pane) getRoot("/main/Maison.fxml");
		Scene scene = new Scene(root);

		// Affichage du bandeau d'infos
		root.getChildren().addAll(affichageBandeau());

		// Affichage de la pièce
		ImageView imageView = Piece.imageViewPiece();
		root.getChildren().add(imageView);

		// Affichage des équipements
		LinkedList<Equipement> equip = Main.getPosition().getEquipements();
		if (!equip.isEmpty()) {
			for (int i = 0; i < equip.size(); i++) {
				Equipement current = equip.get(i);
				ImageView img = equip.get(i).afficher();
				img.setOnMouseClicked((new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						MenuButton fonct = current.getFonctionnalites(root, img);
						root.getChildren().add(fonct);
					}
				}));
				root.getChildren().add(img);
			}
		}

		// Affichage de l'avatar
		afficherAvatar(root);

		// Affichage des fonctions admin
		if (ListeUtilisateurs.getAdmin().contains(Main.getPseudo())) {
			root.getChildren().add(actionsAdmin(event, root));
		}

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
				Sauvegarde.sauvegarderCompte();
				creationTxt.setText("Féliciations, vous avez maintenant un compte utilisateur !");
			}
		} else {
			creationTxt.setText("Identifiant déjà existant");
		}
	}

	public MenuButton actionsAdmin(ActionEvent event, Pane root) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		Maison maison = Main.getMaison();

		MenuButton choiceBox = new MenuButton("Modes admin");
		choiceBox.setPrefSize(130, 10);
		choiceBox.setLayoutX(657);
		choiceBox.setLayoutY(67);

		MenuItem choix1 = new MenuItem("Créer une pièce");
		choix1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Scene scene = new Scene(getRoot("/main/CreationPiece.fxml"));
				window.setTitle("Créer une pièce");
				window.setScene(scene);
				window.show();
			}
		});

		MenuItem choix2 = new MenuItem("Supprimer une pièce");
		choix2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Pane root = (Pane) getRoot("/main/SuppressionPiece.fxml");
				Scene scene = new Scene(root);
				LinkedList<Piece> pieces = maison.getPieces();
				if (!pieces.isEmpty()) {
					for (int i = 0; i < pieces.size(); i++) {
						Piece piece = pieces.get(i);
						Button boutonPiece = piece.getButton();
						boutonPiece.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								maison.suppressionPiece(piece);
								scenePiece(e);
							}
						});
						root.getChildren().add(boutonPiece);
					}
				}
				window.setTitle("Supprimer une pièce");
				window.setScene(scene);
				window.show();
			}
		});

		MenuItem choix3 = new MenuItem("Créer un équipement");
		choix3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Scene scene = new Scene(getRoot("/main/CreationEquipement.fxml"));
				window.setTitle("Créer un équipement");
				window.setScene(scene);
				window.show();
			}
		});

		MenuItem choix4 = new MenuItem("Supprimer un équipement");
		choix4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Pane root = (Pane) getRoot("/main/SuppressionEquipement.fxml");
				Scene scene = new Scene(root);
				LinkedList<Equipement> equip = Main.getPosition().getEquipements();
				for (int i = 0; i < equip.size(); i++) {
					Equipement equipement = equip.get(i);
					Button boutonEquip = equip.get(i).getButton();
					boutonEquip.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							Main.getPosition().supprimerEquipement(equipement);
							scenePiece(e);
						}
					});
					root.getChildren().add(boutonEquip);
				}
				window.setTitle("Supprimer un équipement");
				window.setScene(scene);
				window.show();
			}
		});

		MenuItem choix5 = new MenuItem("Supprimer tous les équipement de la pièce");
		choix5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LinkedList<Equipement> liste = Main.getPosition().getEquipements();
				for (int i = 0; i < liste.size(); i++) {
					root.getChildren().remove(liste.get(i).afficher());
				}
				Main.getPosition().getEquipements().clear();
				Main.traitementIntensiteLumineuse();
				Main.traitementTemperature();
			}
		});

		MenuItem choix6 = new MenuItem("Se déplacer dans n'importe quelle pièce");
		choix6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Pane root = (Pane) getRoot("/main/SelectionPiece.fxml");
				Scene scene = new Scene(root);

				LinkedList<Piece> pieces = Main.getMaison().getPieces();
				if (!pieces.isEmpty()) {
					for (int i = 0; i < pieces.size(); i++) {
						Piece piece = pieces.get(i);
						Button boutonPiece = piece.getButton();
						boutonPiece.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								Main.setPosition(piece);
								scenePiece(e);
							}
						});
						root.getChildren().add(boutonPiece);
					}
				}
				window.setTitle("Déplacer vers une autre pièce");
				window.setScene(scene);
				window.show();
			}
		});

		MenuItem choix7 = new MenuItem("Changer la couleur du bandeau");
		choix7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				couleurBandeau(root);
			}
		});

		MenuItem choix8 = new MenuItem("Changer l'avatar");
		choix8.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changerAvatar(root);
			}
		});

		choiceBox.getItems().addAll(choix1, choix2, choix3, choix4, choix5, choix6, choix7, choix8);
		return choiceBox;
	}

	public LinkedList<Label> affichageBandeau() {
		LinkedList<Label> liste = new LinkedList<Label>();
		double prochainLabel = 4; // Début de la zone de placement des labels

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
		lbl1.setPrefWidth(170);
		lbl1.setLayoutX(prochainLabel);
		lbl1.setLayoutY(10);
		prochainLabel = prochainLabel + 170;

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
		lbl2.setPrefWidth(170);
		lbl2.setLayoutX(prochainLabel);
		lbl2.setLayoutY(10);
		prochainLabel = prochainLabel + 180;

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
		lbl3.setPrefWidth(150);
		lbl3.setLayoutX(prochainLabel);
		lbl3.setLayoutY(10);
		prochainLabel = 4; // Reviens à la ligne

		Label temperature = new Label();
		temperature.setText("Température :");
		temperature.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		temperature.setUnderline(true);
		temperature.setLayoutX(prochainLabel);
		temperature.setLayoutY(68);
		Label lbl4 = new Label();
		lbl4.setText("" + Main.getPosition().getTemperature() + "°C");
		lbl4.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl4.setLayoutX(148);
		lbl4.setLayoutY(68);

		Label luminosite = new Label();
		luminosite.setText("Luminosité :");
		luminosite.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		luminosite.setUnderline(true);
		luminosite.setLayoutX(281);
		luminosite.setLayoutY(68);
		Label lbl5 = new Label();
		lbl5.setText("" + Main.getPosition().getIntensiteLumineuse() + "%");
		lbl5.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl5.setLayoutX(411);
		lbl5.setLayoutY(68);

		Label heure = new Label();
		heure.setText("Heure :");
		heure.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		heure.setUnderline(true);
		heure.setLayoutX(521);
		heure.setLayoutY(68);
		Label lbl6 = new Label();
		lbl6.setText("" + Main.getHeure() + "h");
		lbl6.setStyle("-fx-font: 20 arial; -fx-font-weight: bold");
		lbl6.setLayoutX(598);
		lbl6.setLayoutY(68);

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

	public void couleurBandeau(Pane root) {
		MenuButton couleurs = new MenuButton("Couleur du bandeau");
		couleurs.setPrefSize(220, 30);
		couleurs.setLayoutX(300);
		couleurs.setLayoutY(220);

		MenuItem choix1 = new MenuItem("Blanc");
		choix1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.setStyle("-fx-background-color: #FFFFFF");
			}
		});
		MenuItem choix2 = new MenuItem("Argent");
		choix2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.setStyle("-fx-background-color: #C0C0C0");
			}
		});
		MenuItem choix3 = new MenuItem("Gris");
		choix3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.setStyle("-fx-background-color: #808080");
			}
		});
		MenuItem choix4 = new MenuItem("Noir");
		choix4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.setStyle("-fx-background-color: #000000");
			}
		});
		MenuItem choix5 = new MenuItem("Rouge");
		choix5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.setStyle("-fx-background-color: #FF0000");
			}
		});
		MenuItem choix6 = new MenuItem("Bordeau");
		choix6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.setStyle("-fx-background-color: #800000");
			}
		});
		MenuItem choix7 = new MenuItem("Jaune");
		choix7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.setStyle("-fx-background-color: #FFFF00");
			}
		});
		MenuItem choix8 = new MenuItem("Vert");
		choix8.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.setStyle("-fx-background-color: #008000");
			}
		});
		MenuItem choix9 = new MenuItem("Bleu");
		choix9.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.setStyle("-fx-background-color: #0000FF");
			}
		});
		MenuItem choix10 = new MenuItem("Magenta");
		choix10.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.setStyle("-fx-background-color: #FF00FF");
			}
		});
		MenuItem choix11 = new MenuItem("Violet");
		choix11.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.setStyle("-fx-background-color: #800080");
			}
		});
		MenuItem choix12 = new MenuItem("Cette couleur est parfaite pour moi !");
		choix12.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.getChildren().remove(couleurs);
			}
		});

		couleurs.getItems().addAll(choix1, choix2, choix3, choix4, choix5, choix6, choix7, choix8, choix9, choix10,
				choix11, choix12);
		root.getChildren().add(couleurs);
	}

	public void changerAvatar(Pane root) {
		MenuButton avatars = new MenuButton("Avatars");
		avatars.setPrefSize(220, 30);
		avatars.setLayoutX(570);
		avatars.setLayoutY(100);

		MenuItem choix1 = new MenuItem("Homme 9");
		choix1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("homme9");
				afficherAvatar(root);
			}
		});
		MenuItem choix2 = new MenuItem("Homme 8");
		choix2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("homme8");
				afficherAvatar(root);
			}
		});
		MenuItem choix3 = new MenuItem("Homme 7");
		choix3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("homme7");
				afficherAvatar(root);
			}
		});
		MenuItem choix4 = new MenuItem("Homme 6");
		choix4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("homme6");
				afficherAvatar(root);
			}
		});
		MenuItem choix5 = new MenuItem("Homme 5");
		choix5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("homme5");
				afficherAvatar(root);
			}
		});
		MenuItem choix6 = new MenuItem("Homme 4");
		choix6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("homme4");
				afficherAvatar(root);
			}
		});
		MenuItem choix7 = new MenuItem("Homme 3");
		choix7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("homme3");
				afficherAvatar(root);
			}
		});
		MenuItem choix8 = new MenuItem("Homme 2");
		choix8.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("homme2");
				afficherAvatar(root);
			}
		});
		MenuItem choix9 = new MenuItem("Homme 1");
		choix9.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("homme1");
				afficherAvatar(root);
			}
		});
		MenuItem choix10 = new MenuItem("Femme 6");
		choix10.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("fille7");
				afficherAvatar(root);
			}
		});
		MenuItem choix11 = new MenuItem("Femme 5");
		choix11.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("fille6");
				afficherAvatar(root);
			}
		});
		MenuItem choix12 = new MenuItem("Femme 4");
		choix12.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("fille5");
				afficherAvatar(root);
			}
		});
		MenuItem choix13 = new MenuItem("Femme 3");
		choix13.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("fille4");
				afficherAvatar(root);
			}
		});
		MenuItem choix14 = new MenuItem("Femme 2");
		choix14.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("fille1");
				afficherAvatar(root);
			}
		});
		MenuItem choix15 = new MenuItem("Femme 1");
		choix15.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.setAvatar("fille2");
				afficherAvatar(root);
			}
		});
		MenuItem choix16 = new MenuItem("Supprimer l'avatar");
		choix16.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (root.getChildren().contains(imageViewAvatar)) {
					root.getChildren().remove(imageViewAvatar);
					Main.setAvatar(null);
				}
			}
		});
		MenuItem choix17 = new MenuItem("Cet avatar est parfait pour moi !");
		choix17.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.getChildren().remove(avatars);
			}
		});

		avatars.getItems().addAll(choix1, choix2, choix3, choix4, choix5, choix6, choix7, choix8, choix9, choix10,
				choix11, choix12, choix13, choix14, choix15, choix16, choix17);
		root.getChildren().add(avatars);
	}

}
