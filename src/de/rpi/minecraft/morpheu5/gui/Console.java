package de.rpi.minecraft.morpheu5.gui;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;

import de.rpi.minecraft.morpheu5.interfaces.IGuiConsole;

public class Console extends JFrame implements IGuiConsole{
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldZ;
	
	
	
	public Console() {
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setBackground(UIManager.getColor("windowBorder"));
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelX = new JPanel();
		getContentPane().add(panelX);
		
		JLabel lblX = new JLabel("X");
		panelX.add(lblX);
		
		textFieldX = new JTextField();
		textFieldX.setEditable(false);
		panelX.add(textFieldX);
		textFieldX.setColumns(10);
		
		JPanel panelY = new JPanel();
		getContentPane().add(panelY);
		
		JLabel lblY = new JLabel("Y");
		panelY.add(lblY);
		
		textFieldY = new JTextField();
		textFieldY.setEditable(false);
		textFieldY.setColumns(10);
		panelY.add(textFieldY);
		
		JPanel panelZ = new JPanel();
		getContentPane().add(panelZ);
		
		JLabel lblZ = new JLabel("Z");
		panelZ.add(lblZ);
		
		textFieldZ = new JTextField();
		textFieldZ.setEditable(false);
		textFieldZ.setColumns(10);
		panelZ.add(textFieldZ);
		setTitle("Console");
	}



	
	
	@Override
	public void showID(int id) {
		//TODO
	}


	@Override
	public void showXYZ(int x, int y, int z) {
		textFieldX.setText(String.valueOf(x));
		textFieldY.setText(String.valueOf(y));
		textFieldZ.setText(String.valueOf(z));
	}

}

