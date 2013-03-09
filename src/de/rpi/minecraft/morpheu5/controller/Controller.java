package de.rpi.minecraft.morpheu5.controller;

import de.rpi.minecraft.morpheu5.interfaces.IWindowControl;
import pi.Block;
import pi.Minecraft;
import pi.Vec;

public class Controller implements IWindowControl {

	private Minecraft mc;

	public Controller(String host) {
		mc = Minecraft.connect(host);
		mc.postToChat("Minecraft API-GUI Conneted Successfully");
	}

	@Override
	public void postToChat(String message) {
		mc.postToChat(message);
	}

	@Override
	public void getBlockWithData(int x, int y, int z) {
		Block block = mc.getBlockWithData(Vec.xyz(x, y, z));
		System.out.println(block);
		
		
		//TODO
	}
	
	
	
	

}
