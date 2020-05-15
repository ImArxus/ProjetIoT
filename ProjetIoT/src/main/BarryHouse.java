package main;

import equipements.Balance;
import equipements.Cheminee;
import equipements.Enceinte;
import equipements.Frigo;
import equipements.Lumiere;
import equipements.PS5;
import equipements.Radiateur;
import equipements.TV;
import equipements.Thermostat;
import equipements.Ventilateur;
import equipements.Volet;
import pieces.Cuisine;
import pieces.Salon;
import equipements.Electrolyseur;

import java.io.Serializable;

import equipements.Alarme;
import equipements.Alexa;

public class BarryHouse implements Serializable {

	private static final long serialVersionUID = 5L;
	static Piece cuisine = new Cuisine("Cuisine");
	static Piece salleaManger = new Piece("Salle à manger");
	static Piece salon = new Salon("Salon");
	static Piece chambre = new Piece("Chambre1");
	static Piece salleDeBain = new Piece("Salle de bain");
	static Piece palier = new Piece("Palier");
	static Piece escalier = new Piece("Escalier");
	static Piece dressing = new Piece("Dressing");
	static Piece buanderie = new Piece("Buanderie");
	static Piece jardin = new Piece("Jardin");
	static Piece bureau = new Piece("Bureau");
	static Piece mezzanine = new Piece("Mezzanine");
	static Piece piscine = new Piece("Piscine");
	static Piece salonVide = new Piece("Salon");

	static Maison maison = new Maison("Barry House", piscine); // Créé une maison avec un salon

	// Création des équipements
	static Equipement lumiere1 = new Lumiere("Lumière1");
	static Equipement lumiere2 = new Lumiere("Lumière2");
	static Equipement lumiere3 = new Lumiere("Lumière3");
	static Equipement lumiere4 = new Lumiere("Lumière4");
	static Equipement lumiere5 = new Lumiere("Lumière5");
	static Equipement lumiere6 = new Lumiere("Lumière6");
	static Equipement lumiere7 = new Lumiere("Lumière7");
	static Equipement lumiere8 = new Lumiere("Lumière8");
	static Equipement lumiere9 = new Lumiere("Lumière9");
	static Equipement lumiere10 = new Lumiere("Lumière10");
	static Equipement lumiere11 = new Lumiere("Lumière11");
	static Equipement TV1 = new TV("TV1");
	static Equipement TV2 = new TV("TV2");
	static Equipement TV3 = new TV("TV3");
	static Equipement volet1 = new Volet("Volet1");
	static Equipement volet2 = new Volet("Volet2");
	static Equipement volet3 = new Volet("Volet3");
	static Equipement volet4 = new Volet("Volet4");
	static Equipement volet5 = new Volet("Volet5");
	static Equipement volet6 = new Volet("Volet6");
	static Equipement volet7 = new Volet("Volet7");
	static Equipement volet8 = new Volet("Volet8");
	static Equipement volet9 = new Volet("Volet9");
	static Equipement volet10 = new Volet("Volet10");
	static Equipement Radiateur1 = new Radiateur("Radiateur1");
	static Equipement Radiateur2 = new Radiateur("Radiateur2");
	static Equipement Radiateur3 = new Radiateur("Radiateur3");
	static Equipement Radiateur4 = new Radiateur("Radiateur4");
	static Equipement Radiateur5 = new Radiateur("Radiateur5");
	static Equipement Radiateur6 = new Radiateur("Radiateur6");
	static Equipement Radiateur7 = new Radiateur("Radiateur7");
	static Equipement Radiateur8 = new Radiateur("Radiateur8");
	static Equipement Radiateur9 = new Radiateur("Radiateur9");
	static Equipement Radiateur10 = new Radiateur("Radiateur10");
	static Equipement cheminee1 = new Cheminee("Cheminee");
	static Equipement PS51 = new PS5("PS5");
	static Equipement enceinte1 = new Enceinte("Enceinte");
	static Equipement Electrolyseur = new Electrolyseur("Electrolyseur");
	static Equipement Alarme = new Alarme("Alarme");
	static Equipement balance1 = new Balance("Balance1");
	static Equipement frigo1 = new Frigo("Frigo1");
	static Equipement thermostat1 = new Thermostat("Thermostat1");
	static Equipement ventilateur1 = new Ventilateur("Ventilateur1");
	static Equipement alexa1 = new Alexa("Alexa1");

	public static void ajoutPiece() {
		// Ajout de pièces dans la maison
		maison.ajouterPiece(cuisine);
		maison.ajouterPiece(salleaManger);
		maison.ajouterPiece(salon);
		maison.ajouterPiece(chambre);
		maison.ajouterPiece(salleDeBain);
		maison.ajouterPiece(palier);
		maison.ajouterPiece(escalier);
		maison.ajouterPiece(dressing);
		maison.ajouterPiece(buanderie);
		maison.ajouterPiece(jardin);
		maison.ajouterPiece(bureau);
		maison.ajouterPiece(mezzanine);
		maison.ajouterPiece(piscine);
	}

	public static void ajoutEquipement() {
		// Ajout des équipements dans les pièces
		// Ajout cuisine
		cuisine.ajouterEquipement(lumiere1);
		cuisine.ajouterEquipement(volet1);
		cuisine.ajouterEquipement(TV1);
		cuisine.ajouterEquipement(Radiateur1);
		cuisine.ajouterEquipement(frigo1);
		cuisine.ajouterEquipement(Alarme);
		// Ajout salle a manger
		salleaManger.ajouterEquipement(lumiere2);
		salleaManger.ajouterEquipement(Radiateur2);
		salleaManger.ajouterEquipement(volet2);
		salleaManger.ajouterEquipement(thermostat1);
		// Ajout salon
		salon.ajouterEquipement(TV2);
		salon.ajouterEquipement(cheminee1);
		salon.ajouterEquipement(lumiere3);
		salon.ajouterEquipement(Radiateur3);
		salon.ajouterEquipement(volet3);
		salon.ajouterEquipement(PS51);
		salon.ajouterEquipement(enceinte1);
		salon.ajouterEquipement(Alarme);
		salon.ajouterEquipement(ventilateur1);
		salon.ajouterEquipement(alexa1);
		// Ajout Chambre
		chambre.ajouterEquipement(lumiere4);
		chambre.ajouterEquipement(Radiateur4);
		chambre.ajouterEquipement(TV3);
		chambre.ajouterEquipement(volet4);
		// ajout salle de bain
		salleDeBain.ajouterEquipement(lumiere5);
		salleDeBain.ajouterEquipement(volet5);
		salleDeBain.ajouterEquipement(Radiateur5);
		salleDeBain.ajouterEquipement(balance1);
		// ajout palier
		palier.ajouterEquipement(volet6);
		palier.ajouterEquipement(lumiere6);
		palier.ajouterEquipement(Radiateur6);
		// ajout escalier
		escalier.ajouterEquipement(lumiere7);
		// Ajout dressing
		dressing.ajouterEquipement(lumiere8);
		dressing.ajouterEquipement(Radiateur7);
		dressing.ajouterEquipement(volet7);
		// ajout buanderie
		buanderie.ajouterEquipement(volet8);
		buanderie.ajouterEquipement(lumiere9);
		buanderie.ajouterEquipement(Radiateur8);
		// ajout jardin
		jardin.ajouterEquipement(Alarme);
		// ajout bureau
		bureau.ajouterEquipement(lumiere10);
		bureau.ajouterEquipement(volet9);
		bureau.ajouterEquipement(Radiateur9);
		// ajout mezzanine
		mezzanine.ajouterEquipement(lumiere11);
		mezzanine.ajouterEquipement(volet10);
		mezzanine.ajouterEquipement(Radiateur10);
		// ajout piscine
		piscine.ajouterEquipement(Electrolyseur);
		piscine.ajouterEquipement(Alarme);
	}

	public static void ajoutPiecesAdjacentes() {
		// Ajout de pièces adjacentes
		// adjacent salon
		maison.sontAdjacents(salon, cuisine);
		maison.sontAdjacents(salon, salleaManger);
		maison.sontAdjacents(salon, palier);
		// adjacent salle a manger
		maison.sontAdjacents(salleaManger, dressing);
		maison.sontAdjacents(salleaManger, buanderie);
		maison.sontAdjacents(salleaManger, jardin);
		maison.sontAdjacents(salleaManger, escalier);
		// adjacent salle de bain
		maison.sontAdjacents(salleDeBain, palier);
		// adjacent chambre
		maison.sontAdjacents(chambre, palier);
		maison.sontAdjacents(chambre, bureau);
		// adjacent Palier
		maison.sontAdjacents(palier, mezzanine);
		// adjacent bureau
		maison.sontAdjacents(bureau, mezzanine);
		// adjacent piscine
		maison.sontAdjacents(jardin, piscine);
	}

	public static Maison creerMaison() {
		ajoutPiece();
		ajoutEquipement();
		ajoutPiecesAdjacentes();
		return maison;
	}

}
