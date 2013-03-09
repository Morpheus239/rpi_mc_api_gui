package de.rpi.minecraft.morpheu5.controller;

import de.rpi.minecraft.morpheu5.interfaces.IGuiConsole;
import de.rpi.minecraft.morpheu5.interfaces.IWindowControl;
import pi.Block;
import pi.Minecraft;
import pi.Vec;

public class Controller implements IWindowControl {

	private Minecraft mc;
	private IGuiConsole console;
	
	
	public Controller(String host) {
		mc = Minecraft.connect(host);
		mc.postToChat("Minecraft API-GUI Conneted Successfully");
	}
	
	/**
	 * Dummy for testing
	 */
	public Controller(){
			//DUMMY
	}
	
	
	/**
	 * Connects the Console Interface to the Controller
	 * @param console
	 */
	public void connectConsole(IGuiConsole console) {
		this.console = console;
	}
	
	

	@Override
	public void chat_postToChat(String message) {
		mc.postToChat(message);
	}

	@Override
	public void world_getBlockWithData(int x, int y, int z) {
		Block block = mc.getBlockWithData(Vec.xyz(x, y, z));
		System.out.println(block);
		
		//TODO
	}

	@Override
	public void player_getTile() {
		//TODO Test with RPi
		//Vec coords = mc.player.getPosition();
		Vec coords = Vec.xyz(100, 200, 300);
		console.showXYZ(coords.x, coords.y, coords.z);
	}
	
	
	
	

}
