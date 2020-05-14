package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.*;
import javax.swing.BoxLayout;


public class Interface extends JFrame {

	public Interface() {
		
		// reglage de la taille de la fenetre de jeu, en pixels (nb: un écran fullHD = 1980x1050 pixels)

		this.setTitle("Maison de Barry");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer quand on appuie sur croix rouge
		this.setLocationRelativeTo(null);

		JPanel b1 = new JPanel();
		// On définit le layout en lui indiquant qu'il travaillera en ligne
		b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
		b1.add(new JButton("Bouton 1"));

		JPanel b2 = new JPanel();
		b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
		b2.add(new JButton("Bouton 2"));

		JPanel b3 = new JPanel();
		b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
		b3.add(new JButton("Bouton 3"));

		JPanel b5 = new JPanel();
		b5.setLayout(new BoxLayout(b5, BoxLayout.LINE_AXIS));
		b5.add(new JButton("Bouton 4"));

		JLabel a = new JLabel("Bonjour, Bienvenue dans la maison de Barry");
		a.setLayout(new BoxLayout(a, BoxLayout.LINE_AXIS));

		JPanel b4 = new JPanel();
		// Les trois lignes en colonne
		b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));
		b4.add(a);
		b4.add(b1);
		b4.add(b2);
		b4.add(b3);
		b4.add(b5);

		this.getContentPane().add(b4);
		this.setVisible(true);

	}

	public static Interface creation() {
		Interface a = new Interface();
		return a;
	}

}

