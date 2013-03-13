package de.rpi.minecraft.morpheu5.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.rpi.minecraft.morpheu5.controller.Controller;
import de.rpi.minecraft.morpheu5.gui.Console;
import de.rpi.minecraft.morpheu5.gui.Window;
import de.rpi.minecraft.morpheu5.log.rpi_logger;

public class Main {

	private static Controller controller;
	private static Window main_window;
	private static Console console_window;

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

		createWindows();
		createController(input);
	}

	private static void createController(String hostadress) {
		controller = new Controller(hostadress);
		main_window.addController(controller);
		controller.connectConsole(console_window);
		
	}

	private static void createWindows() {
		main_window = new Window();
		console_window = new Console();

		// Zentrierung / Breite & Hoehe des Fensters
		Dimension main_window_size = new Dimension(600, 480);
		Dimension console_window_size = new Dimension(450, 200);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int top = (screenSize.height - main_window_size.height) / 2;
		int left = (screenSize.width - main_window_size.width) / 2;
		left -=200;
		
		main_window.setSize(main_window_size);
		main_window.setLocation(left, top);
		main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		console_window.setSize(console_window_size);
		console_window.setLocation(left+600, top);
		console_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		main_window.setVisible(true);
		console_window.setVisible(true);
		
		
		
	}

}
