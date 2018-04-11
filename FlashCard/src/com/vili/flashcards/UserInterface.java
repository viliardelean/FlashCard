package com.vili.flashcards;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.Color;

public class UserInterface {
	private JFrame frame;
	private JPanel panel;
	private ArrayList<Card> cardList;
	private Iterator cardIterator;
	private Card currentCard;
	private JButton btnNextCard;
	private JTextArea qArea;
	private JTextArea aArea;
	private boolean isSkipCard;
	private JLabel rightIcon;
	
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
		
		Font mFont = new Font("Helvetica Neue", Font.BOLD, 23);

		// Menu Bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setBackground(Color.GRAY);
		fileMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		JMenuItem opeMenuItem = new JMenuItem("Load Card Set");
		fileMenu.add(opeMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		opeMenuItem.addActionListener(new OpenMenuListener()); 

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
		
		// Text Areas for questions (q) and answers (a)
		qArea = new JTextArea();
		qArea.setFont(mFont);
		qArea.setLineWrap(true);
		qArea.setWrapStyleWord(true);
		qArea.setBounds(10, 38, 331, 77);
		panel.add(qArea);

		aArea = new JTextArea();
		aArea.setFont(mFont);
		aArea.setLineWrap(true);
		aArea.setWrapStyleWord(true);
		aArea.setBounds(10, 154, 331, 77);
		panel.add(aArea);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubmit.setBounds(10, 245, 105, 38);
		panel.add(btnSubmit);
		btnSubmit.addActionListener(new SubmitListener());

		btnNextCard = new JButton("Next Card");
		btnNextCard.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNextCard.setBounds(236, 245, 105, 38);
		panel.add(btnNextCard);
		btnNextCard.addActionListener(new NextCardListener());

		ImageIcon icon1 = createImageIcon("/com/vili/resources/Correct_Icon.png", "Correct");
		ImageIcon icon2 = createImageIcon("/com/vili/resources/Incorrect_Icon.png", "Incorrect");
		

		rightIcon = new JLabel(icon2);
		rightIcon.setBounds(140, 240, 70, 50);
		panel.add(rightIcon);
		rightIcon.setVisible(false);
		

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
	
	public class OpenMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(fileOpen);
			loadFile(fileOpen.getSelectedFile());
			
		}
	}
		
	public void loadFile(File file) {
			
			cardList = new ArrayList<Card>();
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = null;
				
				while (( line = reader.readLine()) != null) {
					makeCard(line);
				}
				reader.close();
				
			} catch (Exception e) {
				System.err.println("Couldn't read the card file");
				e.printStackTrace();
			}
			
			// show the first card
			cardIterator = cardList.iterator();
			showNextCard();
		
	}
	
	public void makeCard(String lineToParse) {
		//String[] result = lineToParse.split("/");
		StringTokenizer result = new StringTokenizer(lineToParse, "/");
		if (result.hasMoreTokens()) {
			
			Card card = new Card(result.nextToken(), result.nextToken());
			cardList.add(card);
					
		}
		
	}
	
	private void showNextCard() {
		
		currentCard = (Card) cardIterator.next();
		
		
		qArea.setText(currentCard.getQuestion());
		btnNextCard.setText("Skip Card");
		isSkipCard = true;
		
	}
	
	public class NextCardListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

				if (cardIterator.hasNext()) {
					
					showNextCard();
				}else {
					
					// no more cards to show
					qArea.setText("That was the last card.");
					btnNextCard.setEnabled(false);
					
				}
			}
			
		}
	public class SubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			rightIcon.setVisible(true);
			btnNextCard.setText("Next Card");
			
		}
		
	}
}
