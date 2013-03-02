package de.rpi.minecraft.morpheu5.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import de.rpi.minecraft.morpheu5.gui.Window;

public class Main {

	public static void main(String[] args) {

		System.out.println("################################");
		System.out.println("##### RPi Minecraft API GUI ####");
		System.out.println("########## by Morpheu5 #########");
		System.out.println("################################");

		Window main_window = new Window();
	
		// Zentrierung / Breite & Hoehe des Fensters
		Dimension frameSize = new Dimension(600, 480);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int top = (screenSize.height - frameSize.height) / 2;
		int left = (screenSize.width - frameSize.width) / 2;
		
		main_window.setSize(frameSize);
		main_window.setLocation(left, top);
		main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_window.setVisible(true);

	}

}
