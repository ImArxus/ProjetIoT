package main;

import java.util.Scanner;

import equipements.Balance;
import equipements.Cheminee;
import equipements.Electrolyseur;
import equipements.Enceinte;
import equipements.Frigo;
import equipements.Lumiere;
import equipements.PS5;
import equipements.Radiateur;
import equipements.TV;
import equipements.Thermostat;
import equipements.Volet;

public class Action {

	public static boolean actionEquipement(Equipement objet, Scanner s) throws InterruptedException {
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
			if (objet instanceof Lumiere) {
				actionLumiere((Lumiere) objet, requete, s);
			} else if (objet instanceof Radiateur) {
				actionRadiateur((Radiateur) objet, requete, s);
			} else if (objet instanceof TV) {
				actionTV((TV) objet, requete, s);
			} else if (objet instanceof Volet) {
				actionVolet((Volet) objet, requete, s);
			} else if (objet instanceof Cheminee) {
				actionCheminee((Cheminee) objet, requete, s);
			} else if (objet instanceof Electrolyseur) {
				actionElectrolyseur((Electrolyseur) objet, requete, s);
			} else if (objet instanceof Enceinte) {
				actionEnceinte((Enceinte) objet, requete, s);
			} else if (objet instanceof PS5) {
				actionPS5((PS5) objet, requete, s);
			}else if (objet instanceof Balance) {
				actionBalance((Balance) objet, requete, s);
			}else if (objet instanceof Thermostat) {
				actionThermostat((Thermostat) objet, requete, s);
			}
			break;
		}
		return false;
	}

	public static void actionElectrolyseur(Electrolyseur e, String requete, Scanner s) {
		switch (requete) {
		case "Augmenter température":
			e.augmenterTemperature();
			System.out.println("La température de " + e.getNom() + " est réglé sur " + e.getTemperature() + "°C");
			break;
		case "Diminuer température":
			e.diminuerTemperature();
			System.out.println("La température de " + e.getNom() + " est réglé sur " + e.getTemperature() + "°C");
			break;
		case "Choisir thermostat":
			System.out.println("Quelle température (entre 1 et 30) en °C ?");
			int temperature = s.nextInt();
			s.nextLine(); // On vide la ligne pour ne pas avoir de problème au prochain nextLine()
			e.choisirTemperature(temperature);
			System.out.println("La température de " + e.getNom() + " est réglé sur " + e.getTemperature() + "°C");
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

	public static void actionCheminee(Cheminee c, String requete, Scanner s) {
		switch (requete) {
		case "Augmenter intensité":
			c.augmenterIntensite();
			System.out.println("L'intensité de " + c.getNom() + " est réglé sur " + c.getIntensite());
			break;
		case "Diminuer intensité":
			c.diminuerIntensite();
			System.out.println("L'intensité de " + c.getNom() + " est réglé sur " + c.getIntensite());
			break;
		case "Choisir intensité":
			System.out.println("Quelle intensité (entre 0 et 100) ?");
			int intensite = s.nextInt();
			s.nextLine(); // On vide la ligne pour ne pas avoir de problème au prochain nextLine()
			c.choisirIntensite(intensite);
			System.out.println("L'intensité de " + c.getNom() + " est réglé sur " + c.getIntensite());
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}
	public static void actionThermostat(Thermostat t, String requete, Scanner s) {
		switch (requete) {
		case "Augmenter température":
			t.augmenterTemperature();
			System.out.println("L'intensité de " + t.getNom() + " est réglé sur " + t.getTemperature());
			break;
		case "Diminuer temperature":
			t.diminuerTemperature();
			System.out.println("L'intensité de " + t.getNom() + " est réglé sur " + t.getTemperature());
			break;
		case "Choisir température":
			System.out.println("Quelle température (entre 15 et 30) ?");
			int temp = s.nextInt();
			s.nextLine(); // On vide la ligne pour ne pas avoir de problème au prochain nextLine()
			t.choisirTemperature(temp);
			System.out.println("L'intensité de " + t.getNom() + " est réglé sur " + t.getTemperature());
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}
	public static void actionEnceinte(Enceinte e, String requete, Scanner s) throws InterruptedException {
		if (e.etatCourant) {
			switch (requete) {
			case "Augmenter volume":
				e.augmenterVolume();
				System.out.println("Le volume de " + e.getNom() + " est réglé sur " + e.getVolume());
				break;
			case "Diminuer volume":
				e.diminuerVolume();
				System.out.println("Le volume de " + e.getNom() + " est réglé sur " + e.getVolume());
				break;
			case "Jouer musique":
				System.out.println("Quelle musique souhaitez-vous jouer ?");			
				System.out.println("Votre collection : " + e.getMusiques().keySet()); // Affiche la liste des musiques
				String musique = s.nextLine();
				e.jouerMusique(musique);
				if (e.getMusiques().containsKey(musique)) {
					System.out.println("Lancement de " + e.getEnEcoute() + " sur votre " + e.getNom());
					System.out.println("...");
					Thread.sleep(2000);
					System.out.println(e.getMusiques().get(musique));
				}
				break;
			default:
				System.out.println("Commande non-valide");
				break;
			}
		} else {
			System.out.println(e.getNom() + " est éteinte, on ne peut pas l'utiliser");
		}
	}

	public static void actionPS5(PS5 c, String requete, Scanner s) throws InterruptedException {
		if (c.etatCourant) {
			switch (requete) {
			case "Lancer jeu":
				System.out.println("Quel jeu voulez vous lancer ?");
				System.out.println("Votre collection : " + c.getJeux().keySet()); // Affiche la liste des jeux
				String jeu = s.nextLine();
				c.choisirJeu(jeu);
				if (c.getJeux().containsKey(jeu)) {
					System.out.println("Lancement de " + c.getJeu() + " sur votre " + c.getNom());
					System.out.println("...");
					Thread.sleep(2000);
					System.out.println(c.getJeux().get(jeu));
				}
				break;
			default:
				System.out.println("Commande non-valide");
				break;
			}
		} else {
			System.out.println(c.getNom() + " est éteinte, on ne peut pas l'utiliser");
		}

	}
	public static void actionBalance(Balance b, String requete, Scanner s) throws InterruptedException {
		if (b.etatCourant) {
			switch (requete) {
			case "Peser":
				System.out.println("Mesure ...");
				Thread.sleep(2000);
				b.peser();
				System.out.println("Votre poids est de "+b.getPoids()+" kilos.");
				break;
			default:
				System.out.println("Commande non-valide");
				break;
			}
		} else {
			System.out.println(b.getNom() + " est éteinte, on ne peut pas l'utiliser");
		}

	}
	public static void actionLumiere(Lumiere l, String requete, Scanner s) {
		switch (requete) {
		case "Augmenter intensité":
			l.augmenterIntensite();
			System.out.println("L'intensité de " + l.getNom() + " est réglé sur " + l.getIntensite());
			break;
		case "Diminuer intensité":
			l.diminuerIntensite();
			System.out.println("L'intensité de " + l.getNom() + " est réglé sur " + l.getIntensite());
			break;
		case "Choisir intensité":
			System.out.println("Quelle intensité (entre 0 et 100) ?");
			int intensite = s.nextInt();
			s.nextLine(); // On vide la ligne pour ne pas avoir de problème au prochain nextLine()
			l.choisirIntensite(intensite);
			System.out.println("L'intensité de " + l.getNom() + " est réglé sur " + l.getIntensite());
			break;
		case "Choisir couleur":
			System.out.println("Quelle couleur parmis les suivantes : blanc, bleu, rouge, jaune, vert");
			String couleur = s.nextLine();
			l.changerCouleur(couleur);
			System.out.println("La couleur de " + l.getNom() + " est réglé sur " + l.getCouleur());
			break;
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
	
	public static void actionFrigo(Frigo f, String requete, Scanner s) {
		switch(requete) {
		case "Augmenter temperature":
			f.augmenterTemperature();
			System.out.println("La temperature du frigo " + f.getNom() + " est de " + f.getTemperature());
			break;
		case "Diminuer temperature":
			f.diminuerTemperature();
			System.out.println("La temperature du frigo " + f.getNom() + " est de " + f.getTemperature());
			break;
		case "Commander":
			System.out.println("Que voulez vous commander ?");
			String requete1 = s.nextLine();
			System.out.println("En quelle quantitée ?");
			int requete2 = s.nextInt();
			f.Commander(requete1, requete2);
			System.out.println("Dans " + f.getNom() + ", il y a maintenant " + f.getDispo());
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

}
