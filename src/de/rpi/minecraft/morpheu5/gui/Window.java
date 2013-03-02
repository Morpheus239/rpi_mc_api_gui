package de.rpi.minecraft.morpheu5.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import de.rpi.minecraft.morpheu5.interfaces.IWindowControl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Window extends JFrame{
	private JTextField textField_chat_data_input;
	private IWindowControl control;
	
	public Window() {
		super("RPi MC API GUI");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_chat = new JPanel();
		tabbedPane.addTab("Chat", null, panel_chat, null);
		panel_chat.setLayout(new BorderLayout(0, 0));
		
		JList list_commands = new JList();
		list_commands.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		list_commands.setPreferredSize(new Dimension(150, 0));
		list_commands.setValueIsAdjusting(true);
		list_commands.setModel(new AbstractListModel() {
			String[] values = new String[] {"Test1", "Test2", "Test3"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panel_chat.add(list_commands, BorderLayout.WEST);
		
		JPanel panel_chat_info = new JPanel();
		panel_chat.add(panel_chat_info, BorderLayout.CENTER);
		panel_chat_info.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_chat_info_description = new JPanel();
		panel_chat_info.add(panel_chat_info_description);
		panel_chat_info_description.setLayout(new BorderLayout(0, 0));
		
		JLabel label_chat_command = new JLabel("COMMAND");
		label_chat_command.setFont(new Font("Dialog", Font.BOLD, 30));
		panel_chat_info_description.add(label_chat_command, BorderLayout.NORTH);
		
		JTextPane txtpnLoremIpsumDolor = new JTextPane();
		txtpnLoremIpsumDolor.setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
		panel_chat_info_description.add(txtpnLoremIpsumDolor, BorderLayout.CENTER);
		
		JPanel panel_chat_info_input = new JPanel();
		panel_chat_info.add(panel_chat_info_input);
		panel_chat_info_input.setLayout(new BorderLayout(0, 0));
		
		JLabel label_chat_data = new JLabel("DATA");
		label_chat_data.setVerticalAlignment(SwingConstants.TOP);
		label_chat_data.setPreferredSize(new Dimension(80, 80));
		label_chat_data.setFont(new Font("Dialog", Font.BOLD, 30));
		panel_chat_info_input.add(label_chat_data, BorderLayout.NORTH);
		
		JPanel panel_chat_data_input = new JPanel();
		panel_chat_info_input.add(panel_chat_data_input, BorderLayout.CENTER);
		panel_chat_data_input.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel label_chat_data_message = new JLabel("Message:");
		panel_chat_data_input.add(label_chat_data_message);
		
		textField_chat_data_input = new JTextField();
		textField_chat_data_input.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_chat_data_input.add(textField_chat_data_input);
		textField_chat_data_input.setColumns(10);
		
		JPanel panel_chat_data_aktion = new JPanel();
		panel_chat_info_input.add(panel_chat_data_aktion, BorderLayout.SOUTH);
		panel_chat_data_aktion.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_chat_data_input.setText("");
			}
		});
		panel_chat_data_aktion.add(btnClear);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.postToChat(textField_chat_data_input.getText());
				textField_chat_data_input.setText("");
			}
		});
		panel_chat_data_aktion.add(btnSend);
		
		JPanel panel_1 = new JPanel();
		panel_chat_info_input.add(panel_1, BorderLayout.WEST);
	}
	
	
	public void addController(IWindowControl control) {
		this.control = control;
	}
	
	
}
