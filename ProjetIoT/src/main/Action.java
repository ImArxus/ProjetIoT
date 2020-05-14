package main;

import java.util.Scanner;

import equipements.Alarme;
import equipements.Alexa;
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
import equipements.Ventilateur;
import equipements.Volet;

public class Action {

	private static Piece position = Main.getPosition();

	public static boolean actionEquipement(Equipement objet, Scanner s) throws InterruptedException {
		int requete = Main.toInt(s.nextLine());
		switch (requete) {
		case 1:
			System.out.println("Vous n'utilisez plus " + objet.getNom()); // Ne fait aucune action
			return true;
		case 2:
			objet.allumer();
			if (objet instanceof Alarme) { // Si l'objet est une alarme on sort de la boucle utilisateur
				System.out.println(objet.getNom() + " est allumée");
				return true;
			}
			System.out.println(objet.getNom() + " est allumé(e)");
			break;
		case 3:
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
			} else if (objet instanceof Balance) {
				actionBalance((Balance) objet, requete, s);
			} else if (objet instanceof Thermostat) {
				actionThermostat((Thermostat) objet, requete, s);
			} else if (objet instanceof Frigo) {
				actionFrigo((Frigo) objet, requete, s);
			} else if (objet instanceof Ventilateur) {
				actionVentilateur((Ventilateur) objet, requete, s);
			} else if (objet instanceof Alexa) {
				actionAlexa((Alexa) objet, requete, s);
			}
			break;
		}
		return false;
	}

	public static void actionElectrolyseur(Electrolyseur e, int requete, Scanner s) {
		switch (requete) {
		case 4:
			e.augmenterTemperature();
			position.setTemperature((int) (position.getTemperature() + 1));
			System.out.println("La température de " + e.getNom() + " est réglé sur " + e.getTemperature() + "°C");
			break;
		case 5:
			e.diminuerTemperature();
			position.setTemperature((int) (position.getTemperature() - 1));
			System.out.println("La température de " + e.getNom() + " est réglé sur " + e.getTemperature() + "°C");
			break;
		case 6:
			System.out.println("Quelle température (entre 1 et 30) en °C ?");
			int temperature = Main.toInt(s.nextLine());
			e.choisirTemperature(temperature);
			position.setTemperature(temperature);
			System.out.println("La température de " + e.getNom() + " est réglé sur " + e.getTemperature() + "°C");
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

	public static void actionAlexa(Alexa a, int requete, Scanner s) {
		switch (requete) {
		case 4:
			a.reponseHeure();
			break;
		case 5:
			a.reponseTemperature();
			break;
		case 6:
			a.reponseEquipement();
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

	public static void actionCheminee(Cheminee c, int requete, Scanner s) {
		int intensite;
		switch (requete) {
		case 4:
			intensite = (int) c.getIntensite();
			position.setTemperature((int) (position.getTemperature() + (intensite * 0.05)));
			c.augmenterIntensite();
			System.out.println("L'intensité de " + c.getNom() + " est réglé sur " + c.getIntensite());
			break;
		case 5:
			intensite = (int) c.getIntensite();
			position.setTemperature((int) (position.getTemperature() + (intensite * 0.05)));
			c.diminuerIntensite();
			System.out.println("L'intensité de " + c.getNom() + " est réglé sur " + c.getIntensite());
			break;
		case 6:
			System.out.println("Quelle intensité (entre 0 et 100) ?");
			intensite = Main.toInt(s.nextLine());
			position.setTemperature((int) (position.getTemperature() + (intensite * 0.05)));
			c.choisirIntensite(intensite);
			System.out.println("L'intensité de " + c.getNom() + " est réglé sur " + c.getIntensite());
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

	public static void actionThermostat(Thermostat t, int requete, Scanner s) {
		switch (requete) {
		case 4:
			t.augmenterTemperature();
			position.setTemperature(t.getTemperature());
			System.out.println("L'intensité de " + t.getNom() + " est réglé sur " + t.getTemperature());
			break;
		case 5:
			t.diminuerTemperature();
			position.setTemperature(t.getTemperature());
			System.out.println("L'intensité de " + t.getNom() + " est réglé sur " + t.getTemperature());
			break;
		case 6:
			System.out.println("Quelle température (entre 15 et 30) ?");
			int temp = Main.toInt(s.nextLine());
			t.choisirTemperature(temp);
			position.setTemperature(t.getTemperature());
			System.out.println("L'intensité de " + t.getNom() + " est réglé sur " + t.getTemperature());
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

	public static void actionEnceinte(Enceinte e, int requete, Scanner s) throws InterruptedException {
		if (e.isEtatCourant()) {
			switch (requete) {
			case 4:
				e.augmenterVolume();
				System.out.println("Le volume de " + e.getNom() + " est réglé sur " + e.getVolume());
				break;
			case 5:
				e.diminuerVolume();
				System.out.println("Le volume de " + e.getNom() + " est réglé sur " + e.getVolume());
				break;
			case 6:
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

	public static void actionPS5(PS5 c, int requete, Scanner s) throws InterruptedException {
		if (c.isEtatCourant()) {
			switch (requete) {
			case 4:
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
			System.out.println(c.getNom() + " est éteinte, on ne peut pas lancer un jeu");
		}

	}

	public static void actionBalance(Balance b, int requete, Scanner s) throws InterruptedException {
		if (b.isEtatCourant()) {
			switch (requete) {
			case 4:
				System.out.println("Mesure ...");
				Thread.sleep(2000);
				b.peser();
				System.out.println("Votre poids est de " + b.getPoids() + " kilos.");
				break;
			default:
				System.out.println("Commande non-valide");
				break;
			}
		} else {
			System.out.println(b.getNom() + " est éteinte, on ne peut pas l'utiliser");
		}
	}

	public static void actionLumiere(Lumiere l, int requete, Scanner s) {
		switch (requete) {
		case 4:
			l.augmenterIntensite();
			System.out.println("L'intensité de " + l.getNom() + " est réglé sur " + l.getIntensite());
			break;
		case 5:
			l.diminuerIntensite();
			System.out.println("L'intensité de " + l.getNom() + " est réglé sur " + l.getIntensite());
			break;
		case 6:
			System.out.println("Quelle intensité (entre 0 et 100) ?");
			int intensite = Main.toInt(s.nextLine());
			l.choisirIntensite(intensite);
			System.out.println("L'intensité de " + l.getNom() + " est réglé sur " + l.getIntensite());
			break;
		case 7:
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

	public static void actionRadiateur(Radiateur r, int requete, Scanner s) {
		switch (requete) {
		case 4:
			r.augmenterTemperature();
			position.setTemperature(position.getTemperature() + r.getThermostat());
			System.out.println("Le thermostat de " + r.getNom() + " est réglé sur " + r.getThermostat());
			break;
		case 5:
			r.diminuerTemperature();
			position.setTemperature(position.getTemperature() + r.getThermostat());
			System.out.println("Le thermostat de " + r.getNom() + " est réglé sur " + r.getThermostat());
			break;
		case 6:
			System.out.println("Quelle thermostat (entre 0 et 5) ?");
			int thermostat = Main.toInt(s.nextLine());
			r.choisirThermostat(thermostat);
			position.setTemperature(position.getTemperature() + r.getThermostat());
			System.out.println("Le thermostat de " + r.getNom() + " est réglé sur " + r.getThermostat());
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

	public static void actionVentilateur(Ventilateur v, int requete, Scanner s) {
		switch (requete) {
		case 4:
			v.augmenterIntensite();
			position.setTemperature(position.getTemperature() - v.getIntensite());
			System.out.println("Le thermostat de " + v.getNom() + " est réglé sur " + v.getIntensite());
			break;
		case 5:
			v.diminuerIntensite();
			position.setTemperature(position.getTemperature() - v.getIntensite());
			System.out.println("Le thermostat de " + v.getNom() + " est réglé sur " + v.getIntensite());
			break;
		case 6:
			System.out.println("Quelle thermostat (entre 0 et 5) ?");
			int thermostat = Main.toInt(s.nextLine());
			v.choisirIntensite(thermostat);
			position.setTemperature(position.getTemperature() - v.getIntensite());
			System.out.println("Le thermostat de " + v.getNom() + " est réglé sur " + v.getIntensite());
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

	public static void actionTV(TV tv, int requete, Scanner s) {
		switch (requete) {
		case 4:
			tv.augmenterVolume();
			System.out.println("Le volume de " + tv.getNom() + " est de " + tv.getVolume());
			break;
		case 5:
			tv.diminuerVolume();
			System.out.println("Le volume de " + tv.getNom() + " est de " + tv.getVolume());
			break;
		case 6:
			tv.augmenterChaine();
			System.out.println(tv.getNom() + " est réglé sur la chaine " + tv.getNumeroChaine());
			break;
		case 7:
			tv.diminuerChaine();
			System.out.println(tv.getNom() + " est réglé sur la chaine " + tv.getNumeroChaine());
			break;
		case 8:
			System.out.println("Quelle chaine (entre 0 et 100) ?");
			int chaine = Main.toInt(s.nextLine());
			tv.mettreChaine(chaine);
			System.out.println(tv.getNom() + " est réglé sur la chaine " + tv.getNumeroChaine());
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

	public static void actionVolet(Volet v, int requete, Scanner s) {
		switch (requete) {
		case 4:
			v.monterVolet();
			System.out.println("La position du store " + v.getNom() + " est de " + v.getPosition());
			break;
		case 5:
			v.descendreVolet();
			System.out.println("La position du store " + v.getNom() + " est de " + v.getPosition());
			break;
		case 6:
			System.out.println("Quelle position (entre 0 et 100) ?");
			int position = Main.toInt(s.nextLine());
			v.choisirPosition(position);
			System.out.println("La position du store " + v.getNom() + " est de " + v.getPosition());
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

	public static void actionFrigo(Frigo f, int requete, Scanner s) {
		switch (requete) {
		case 4:
			f.augmenterTemperature();
			System.out.println("La temperature du frigo " + f.getNom() + " est de " + f.getTemperature());
			break;
		case 5:
			f.diminuerTemperature();
			System.out.println("La temperature du frigo " + f.getNom() + " est de " + f.getTemperature());
			break;
		case 6:
			System.out.println("Que voulez vous commander ?");
			String requete1 = s.nextLine();
			System.out.println("En quelle quantitée ?");
			int requete2 = Main.toInt(s.nextLine());
			f.commander(requete1, requete2);
			System.out.println("Dans " + f.getNom() + ", il y a maintenant " + f.getDispo());
			break;
		case 7:
			System.out.println("Dans " + f.getNom() + ", il y a " + f.getDispo());
			break;
		default:
			System.out.println("Commande non-valide");
			break;
		}
	}

}
