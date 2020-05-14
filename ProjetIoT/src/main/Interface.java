package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.*;
import javax.swing.BoxLayout;


public class Interface extends JFrame {

	public Interface() {
		StdDraw.setCanvasSize(100, 100);
		StdDraw.picture(0.92, 0.92,"images/welcomBarryHouse",0.15, 0.15);
		
		
	

	}

	public static Interface creation() {
		Interface a = new Interface();
		return a;
	}

}

