package de.rpi.minecraft.morpheu5.controller;

import de.rpi.minecraft.morpheu5.interfaces.IWindowControl;
import pi.Minecraft;

public class Controller implements IWindowControl{

	private Minecraft mine;
	
	public Controller(String host) {

		mine = Minecraft.connect(host);
		mine.postToChat("Minecraft API-GUI Conneted Successfully");
	
	}


	
	
	@Override
	public void postToChat(String message) {
		mine.postToChat(message);
	}
	
}
