package main;

import java.util.Scanner;
import equipements.Lumiere;
import equipements.Radiateur;
import equipements.TV;
import equipements.Volet;

public class Action {

	public static boolean actionEquipement(Equipement objet, Scanner s) {
		String requete = s.nextLine();
		switch (requete) {
		case "Quitter":
			System.out.println("Vous n'utilisez plus " + objet.getNom()); // Ne fait aucune action
			return true;
		case "Allumer":
			objet.allumer();
			System.out.println(objet.getNom() + " est allumé(e)");
			break;
		case "Eteindre":
			objet.eteindre();
			System.out.println(objet.getNom() + " est éteint(e)");
			break;
		default:
			if (objet instanceof Radiateur) { // Si l'objet est un radiateur
				actionRadiateur((Radiateur) objet, requete, s);
			} else if (objet instanceof TV) { // Si l'objet est une TV
				actionTV((TV) objet, requete, s);
			} else if (objet instanceof Volet) { // Si l'objet est un volet
				actionVolet((Volet) objet, requete, s);
			}
			break;
		}
		return false;
	}

	public void actionLumiere(Lumiere l, String requete, Scanner s) {
		switch (requete) {
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

	public static void actionRadiateur(Radiateur r, String requete, Scanner s) {
		switch (requete) {
		case "Augmenter température":
			r.augmenterTemperature();
			System.out.println("Le thermostat de " + r.getNom() + " est réglé sur " + r.getThermostat());
			break;
		case "Diminuer température":
			r.diminuerTemperature();
			System.out.println("Le thermostat de " + r.getNom() + " est réglé sur " + r.getThermostat());
			break;
		case "Choisir thermostat":
			System.out.println("Quelle thermostat (entre 0 et 5) ?");
			int thermostat = s.nextInt();
			s.nextLine(); // On vide la ligne pour ne pas avoir de problème au prochain nextLine()
			r.choisirThermostat(thermostat);
			System.out.println("Le thermostat de " + r.getNom() + " est réglé sur " + r.getThermostat());
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

	public static void actionTV(TV tv, String requete, Scanner s) {
		switch (requete) {
		case "Augmenter volume":
			tv.augmenterVolume();
			System.out.println("Le volume de " + tv.getNom() + " est de " + tv.getVolume());
			break;
		case "Diminuer volume":
			tv.diminuerVolume();
			System.out.println("Le volume de " + tv.getNom() + " est de " + tv.getVolume());
			break;
		case "Augmenter chaine":
			tv.augmenterChaine();
			System.out.println(tv.getNom() + " est réglé sur la chaine " + tv.getNumeroChaine());
			break;
		case "Diminuer chaine":
			tv.diminuerChaine();
			System.out.println(tv.getNom() + " est réglé sur la chaine " + tv.getNumeroChaine());
			break;
		case "Mettre chaine":
			System.out.println("Quelle chaine (entre 0 et 100) ?");
			int chaine = s.nextInt();
			s.nextLine(); // On vide la ligne pour ne pas avoir de problème au prochain nextLine()
			tv.mettreChaine(chaine);
			System.out.println(tv.getNom() + " est réglé sur la chaine " + tv.getNumeroChaine());
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

	public static void actionVolet(Volet v, String requete, Scanner s) {
		switch (requete) {
		case "Monter volet":
			v.monterVolet();
			System.out.println("La position du store " + v.getNom() + " est de " + v.getPosition());
			break;
		case "Descendre volet":
			v.descendreVolet();
			System.out.println("La position du store " + v.getNom() + " est de " + v.getPosition());
			break;
		case "Choisir position":
			System.out.println("Quelle position (entre 0 et 100) ?");
			int position = s.nextInt();
			s.nextLine(); // On vide la ligne pour ne pas avoir de problème au prochain nextLine()
			v.choisirPosition(position);
			System.out.println("La position du store " + v.getNom() + " est de " + v.getPosition());
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

}
