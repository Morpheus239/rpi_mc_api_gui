package de.rpi.minecraft.morpheu5.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pi.Minecraft;

import de.rpi.minecraft.morpheu5.controller.Controller;
import de.rpi.minecraft.morpheu5.gui.Window;
import de.rpi.minecraft.morpheu5.log.rpi_logger;

public class Main {

	private static Controller controller;
	private static Window main_window;
	
	
	
	public static void main(String[] args) {

		System.out.println("################################");
		System.out.println("##### RPi Minecraft API GUI ####");
		System.out.println("########## by Morpheu5 #########");
		System.out.println("################################");

		// Create Logger
		rpi_logger.initLogger();

		// Get Server Connection
		String input = JOptionPane.showInputDialog(null,
				"Choose the Minecraft Server", "10.42.0.75");

		// String input = JOptionPane.showInputDialog(null,
		// "Choose the Minecraft Server", "localhost");

		if (input == null)
			System.exit(0);
		else if (input.isEmpty()) {
			input = "localhost";
			rpi_logger.LOGGER.log(Level.INFO, "No Input: Using localhost");
		}

		
		createWindow();
		createController(input);
	}

	private static void createController(String input) {
		controller = new Controller(input);
		main_window.addController(controller);
	}

	
	private static void createWindow() {
		main_window = new Window();

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
