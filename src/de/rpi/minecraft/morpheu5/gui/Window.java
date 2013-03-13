package de.rpi.minecraft.morpheu5.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.KeyStroke;

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

import de.rpi.minecraft.morpheu5.controller.Command;
import de.rpi.minecraft.morpheu5.controller.Controller;
import de.rpi.minecraft.morpheu5.interfaces.IGuiConsole;
import de.rpi.minecraft.morpheu5.interfaces.IWindowControl;
import de.rpi.minecraft.morpheu5.log.rpi_logger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.ListSelectionModel;

public class Window extends JFrame {

	private IWindowControl control;
	private Command currentCommand = Command.NONE;
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldZ;

	public Window() {
		super("RPi MC API GUI");

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

				// Tabchange --> Change Current List Element to first of List
				switch (tabbedPane.getSelectedIndex()) {
				case 0:
					currentCommand = Command.CHAT_POSTTOCHAT;
					break;

				case 1:
					currentCommand = Command.WORLD_GETBLOCK;
					break;

				case 2:
					currentCommand = Command.PLAYER_GETPOS;
					break;

				default:
					break;
				}

			}
		});
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		create_chat_tab(tabbedPane);
		create_world_tab(tabbedPane);
		create_player_tab(tabbedPane);

		// Initial Value
		currentCommand = Command.CHAT_POSTTOCHAT;
	}

	private void create_chat_tab(JTabbedPane tabbedPane) {
		JPanel panel_chat = new JPanel();
		tabbedPane.addTab("chat", null, panel_chat, null);
		panel_chat.setLayout(new BorderLayout(0, 0));

		JList list_commands = new JList();
		list_commands.setValueIsAdjusting(true);
		list_commands.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_commands.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(
				0, 0, 0)));
		list_commands.setPreferredSize(new Dimension(150, 0));
		list_commands.setModel(new AbstractListModel() {
			String[] values = new String[] { "postToChat" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_commands.setSelectedIndex(0);
		panel_chat.add(list_commands, BorderLayout.WEST);

		JPanel panel_info = new JPanel();
		panel_chat.add(panel_info, BorderLayout.CENTER);
		panel_info.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel_info_description = new JPanel();
		panel_info_description.setBackground(Color.WHITE);
		panel_info.add(panel_info_description);
		panel_info_description.setLayout(new BorderLayout(0, 0));

		JLabel lblHeadline = new JLabel("PostToChat");
		lblHeadline.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0,
				0, 0)));
		lblHeadline.setBackground(Color.WHITE);
		lblHeadline.setFont(new Font("Dialog", Font.BOLD, 30));
		panel_info_description.add(lblHeadline, BorderLayout.NORTH);

		JTextPane txtDescription = new JTextPane();
		txtDescription.setEditable(false);
		txtDescription
				.setText("\nPosts a Chat Message on the Minecraft Server");
		panel_info_description.add(txtDescription, BorderLayout.CENTER);

		JPanel panel_info_input = new JPanel();
		panel_info.add(panel_info_input);
		panel_info_input.setLayout(new BorderLayout(0, 0));

		JLabel lblValue = new JLabel("Value");
		lblValue.setVerticalAlignment(SwingConstants.TOP);
		lblValue.setPreferredSize(new Dimension(80, 80));
		lblValue.setFont(new Font("Dialog", Font.BOLD, 30));
		panel_info_input.add(lblValue, BorderLayout.NORTH);

		JPanel panel_data_input = new JPanel();
		panel_info_input.add(panel_data_input, BorderLayout.CENTER);
		panel_data_input.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel label_data_message = new JLabel("Message:");
		label_data_message.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_data_input.add(label_data_message);

		final JTextField textField_data_input = new JTextField();

		textField_data_input.setHorizontalAlignment(SwingConstants.LEFT);
		panel_data_input.add(textField_data_input);
		textField_data_input.setColumns(10);

		JPanel panel_data_action = new JPanel();
		panel_info_input.add(panel_data_action, BorderLayout.SOUTH);
		panel_data_action.setLayout(new GridLayout(0, 2, 0, 0));

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_data_input.setText("");
			}
		});
		panel_data_action.add(btnClear);

		JButton btnSend = new JButton("Send");
		btnSend.setMnemonic('s');

		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (currentCommand == Command.CHAT_POSTTOCHAT) {
					control.chat_postToChat(textField_data_input.getText());
					textField_data_input.setText("");
				}

			}
		});
		panel_data_action.add(btnSend);
	}

	private void create_world_tab(JTabbedPane tabbedPane) {
		JPanel panel_world = new JPanel();
		tabbedPane.addTab("world", null, panel_world, null);
		panel_world.setLayout(new BorderLayout(0, 0));

		final JList list_commands = new JList();
		list_commands.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		list_commands.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(
				0, 0, 0)));
		list_commands.setPreferredSize(new Dimension(200, 0));
		list_commands.setValueIsAdjusting(true);
		list_commands.setModel(new AbstractListModel() {
			String[] values = new String[] { "getBlock", "getBlockWithData",
					"setBlock", "setBlocks", "getHeight", "getPlayerIds",
					"setting", "checkpoint.save", "checkpoint.restore" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_commands.setSelectedIndex(0);
		panel_world.add(list_commands, BorderLayout.WEST);

		JPanel panel_info = new JPanel();
		panel_world.add(panel_info, BorderLayout.CENTER);
		panel_info.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel_info_description = new JPanel();
		panel_info_description.setBackground(Color.WHITE);
		panel_info.add(panel_info_description);
		panel_info_description.setLayout(new BorderLayout(0, 0));

		final JLabel lblHeadline = new JLabel("setBlocks");
		lblHeadline.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0,
				0, 0)));
		lblHeadline.setBackground(Color.WHITE);
		lblHeadline.setFont(new Font("Dialog", Font.BOLD, 25));
		panel_info_description.add(lblHeadline, BorderLayout.NORTH);

		final JTextPane txtDescription = new JTextPane();
		txtDescription.setEditable(false);
		txtDescription
				.setText("Sets all blocks in a cuboid defined by two points to a speficied type. The blockData parameter is optional.\n\nx1, y1, z1: coordinates of the first selection point relative to spawn\nx2, y2, z2: coordinates of the second selection point relative to spawn\n\nblockTypeId: ID of the block\nblockData: metadata of the block ");
		panel_info_description.add(txtDescription, BorderLayout.CENTER);

		JPanel panel_info_input = new JPanel();
		panel_info.add(panel_info_input);
		panel_info_input.setLayout(new BorderLayout(0, 0));

		JLabel lblValue = new JLabel("Value");
		lblValue.setVerticalAlignment(SwingConstants.TOP);
		lblValue.setPreferredSize(new Dimension(80, 80));
		lblValue.setFont(new Font("Dialog", Font.BOLD, 30));
		panel_info_input.add(lblValue, BorderLayout.NORTH);

		JPanel panel_data_input = new JPanel();
		panel_info_input.add(panel_data_input, BorderLayout.CENTER);
		panel_data_input.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_x = new JPanel();
		panel_data_input.add(panel_x);

		JLabel labelX = new JLabel("X");
		panel_x.add(labelX);

		textFieldX = new JTextField();
		textFieldX.setColumns(10);
		panel_x.add(textFieldX);

		JPanel panel_y = new JPanel();
		panel_data_input.add(panel_y);

		JLabel labelY = new JLabel("Y");
		panel_y.add(labelY);

		textFieldY = new JTextField();
		textFieldY.setColumns(10);
		panel_y.add(textFieldY);

		JPanel panel_z = new JPanel();
		panel_data_input.add(panel_z);

		JLabel labelZ = new JLabel("Z");
		panel_z.add(labelZ);

		textFieldZ = new JTextField();
		textFieldZ.setColumns(10);
		panel_z.add(textFieldZ);

		JLabel label = new JLabel("");
		panel_data_input.add(label);

		JPanel panel_data_action = new JPanel();
		panel_info_input.add(panel_data_action, BorderLayout.SOUTH);
		panel_data_action.setLayout(new GridLayout(0, 2, 0, 0));

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_data_action.add(btnClear);

		list_commands.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {

				if (arg0.getValueIsAdjusting()) {

					// Select Command by JListselection
					switch (list_commands.getSelectedIndex()) {
					case 0:
						currentCommand = Command.WORLD_GETBLOCK;
						lblHeadline.setText("getBlock");
						txtDescription
								.setText("\nGets the ID of a block in the world.\n\nx, y, z: "
										+ "coordinates of the block relative to spawn\n\nReturns: an integer of type blockTypeId, "
										+ "corresponding to a block type in Minecraft Pi. ");

						break;

					case 1:
						currentCommand = Command.WORLD_GETBLOCKWITHDATA;
						lblHeadline.setText("getBlockWithData");
						txtDescription
								.setText("\nGets the ID and Optional Parameter for a block in the world.\n\nx, y, z: coordinates of the block relative to spawn\n\nReturns: a block object corresponding toa block type in Minecraft Pi. ");

						break;

					case 2:
						currentCommand = Command.WORLD_SETBLOCK;
						lblHeadline.setText("setBlock");
						txtDescription
								.setText("\nSets the ID of a block in the world. The data parameter is optional.\n\nx, y, z: coordinates of the block relative to spawn\n\nblockTypeId: ID of the block\n\nblockData: metadata of the block ");

						break;

					case 3:
						currentCommand = Command.WORLD_SETBLOCKS;
						lblHeadline.setText("setBlocks");
						txtDescription
						.setText("Sets all blocks in a cuboid defined by two points to a speficied type. The blockData parameter is optional.\n\nx1, y1, z1: coordinates of the first selection point relative to spawn\nx2, y2, z2: coordinates of the second selection point relative to spawn\n\nblockTypeId: ID of the block\nblockData: metadata of the block ");
						break;

					case 4:
						currentCommand = Command.WORLD_GETHEIGHT;
						break;

					case 5:
						currentCommand = Command.WORLD_GETPLAYERIDS;
						break;

					case 6:
						currentCommand = Command.WORLD_SETTING;
						break;

					case 7:
						currentCommand = Command.WORLD_CHECKPOINT_SAVE;
						break;

					case 8:
						currentCommand = Command.WORLD_CHECKPOINT_RESTORE;
						break;

					default:
						rpi_logger.LOGGER.log(Level.FINE,
								"JList not right checked");
						break;
					}

				}

			}
		});

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				switch (currentCommand) {
				case WORLD_GETBLOCK:
					break;

				case WORLD_GETBLOCKWITHDATA:
					break;

				case WORLD_SETBLOCK:
					break;

				case PLAYER_GETPOS:
					control.player_getTile();
					System.out.println("DONE");
					break;

				default:
					break;
				}

			}
		});
		panel_data_action.add(btnSend);
	}

	private void create_player_tab(JTabbedPane tabbedPane) {
		JPanel panel_player = new JPanel();
		tabbedPane.addTab("player", null, panel_player, null);
		panel_player.setLayout(new BorderLayout(0, 0));

		final JList list_commands = new JList();
		list_commands.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_commands.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (arg0.getValueIsAdjusting()) {
					switch (list_commands.getSelectedIndex()) {
					case 0:
						currentCommand = Command.PLAYER_GETPOS;

						break;

					default:
						break;
					}

				}

			}
		});
		list_commands.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(
				0, 0, 0)));
		list_commands.setPreferredSize(new Dimension(200, 0));
		list_commands.setValueIsAdjusting(true);
		list_commands.setModel(new AbstractListModel() {
			String[] values = new String[] { "getPos" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_commands.setSelectedIndex(0);
		panel_player.add(list_commands, BorderLayout.WEST);

		JPanel panel_info = new JPanel();
		panel_player.add(panel_info, BorderLayout.CENTER);
		panel_info.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel_info_description = new JPanel();
		panel_info_description.setBackground(Color.WHITE);
		panel_info.add(panel_info_description);
		panel_info_description.setLayout(new BorderLayout(0, 0));

		JLabel lblHeadline = new JLabel("COMMAND");
		lblHeadline.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0,
				0, 0)));
		lblHeadline.setBackground(Color.WHITE);
		lblHeadline.setFont(new Font("Dialog", Font.BOLD, 30));
		panel_info_description.add(lblHeadline, BorderLayout.NORTH);

		JTextPane txtDescription = new JTextPane();
		txtDescription.setEditable(false);
		txtDescription.setText("DESC");
		panel_info_description.add(txtDescription, BorderLayout.CENTER);

		JPanel panel_info_input = new JPanel();
		panel_info.add(panel_info_input);
		panel_info_input.setLayout(new BorderLayout(0, 0));

		JLabel lblValue = new JLabel("Value");
		lblValue.setVisible(false);
		lblValue.setVerticalAlignment(SwingConstants.TOP);
		lblValue.setPreferredSize(new Dimension(80, 80));
		lblValue.setFont(new Font("Dialog", Font.BOLD, 30));
		panel_info_input.add(lblValue, BorderLayout.NORTH);

		JPanel panel_data_input = new JPanel();
		panel_data_input.setVisible(false);
		panel_info_input.add(panel_data_input, BorderLayout.CENTER);
		panel_data_input.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel label_data_message = new JLabel("Message:");
		label_data_message.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_data_input.add(label_data_message);

		final JTextField textField_data_input = new JTextField();
		textField_data_input.setHorizontalAlignment(SwingConstants.LEFT);
		panel_data_input.add(textField_data_input);
		textField_data_input.setColumns(10);

		JPanel panel_data_action = new JPanel();
		panel_info_input.add(panel_data_action, BorderLayout.SOUTH);
		panel_data_action.setLayout(new GridLayout(0, 2, 0, 0));

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_data_input.setText("");
			}
		});
		panel_data_action.add(btnClear);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				switch (currentCommand) {

				case PLAYER_GETPOS:
					control.player_getTile();
					break;

				default:
					break;
				}

			}
		});
		panel_data_action.add(btnSend);
	}

	public void addController(IWindowControl control) {
		this.control = control;
	}

}
