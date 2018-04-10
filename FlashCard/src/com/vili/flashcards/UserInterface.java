package com.vili.flashcards;
import java.awt.Font;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;

public class UserInterface {
	private JFrame frame;
	private JPanel panel;
	
	public UserInterface() {
		initialize(); 
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Flash Cards");
		panel = new JPanel();
		
		frame.setBounds(100, 100, 357, 355);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		panel.setLayout(null);
		frame.setContentPane(panel);
		frame.setVisible(true);

		// Menu Bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setBackground(Color.GRAY);
		fileMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		JMenuItem opeMenuItem = new JMenuItem("Open");

		fileMenu.add(opeMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);

		/*
		 * newMenuItem.addActionListener(new NewMenuListener());
		 * saveMenuItem.addActionListener(new SaveMenuListener());
		 */

		JLabel qLabel = new JLabel("Question :");
		qLabel.setBounds(10, 21, 70, 14);
		panel.add(qLabel);

		JLabel aLabel = new JLabel("Answer : ");
		aLabel.setBounds(10, 134, 70, 14);
		panel.add(aLabel);

		JTextArea qArea = new JTextArea();
		qArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		qArea.setLineWrap(true);
		qArea.setWrapStyleWord(true);
		qArea.setBounds(10, 38, 331, 77);
		panel.add(qArea);

		JTextArea aArea = new JTextArea();
		aArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		aArea.setLineWrap(true);
		aArea.setWrapStyleWord(true);
		aArea.setBounds(10, 154, 331, 77);
		panel.add(aArea);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubmit.setBounds(10, 245, 105, 38);
		panel.add(btnSubmit);

		JButton btnNextCard = new JButton("Next Card");
		btnNextCard.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNextCard.setBounds(236, 245, 105, 38);
		panel.add(btnNextCard);

		ImageIcon icon1 = createImageIcon("/com/vili/resources/Correct_Icon.png", "Correct");
		ImageIcon icon2 = createImageIcon("/com/vili/resources/Incorrect_Icon.png", "Incorrect");
		

		JLabel rightIconLabel = new JLabel(icon2);
		rightIconLabel.setBounds(140, 240, 70, 50);
		panel.add(rightIconLabel);

	}
	//	Return the URL of the Image Icon or Null if the file is not found
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		System.out.println(imgURL);
		if (imgURL != null) {
			
			return new ImageIcon(imgURL, description);
			
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
