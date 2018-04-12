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

public class FlashCards {
	private JFrame frame;
	private JPanel panel;
	private ArrayList<Card> cardList;
	private Iterator<Card> cardIterator;
	private Card currentCard;
	private JButton btnNextCard;
	private JButton btnSubmit;
	private JTextArea qArea;
	private JTextArea aArea;

	private JLabel correctIcon;
	private JLabel incorrectIcon;
	

	public FlashCards() {
		initialize(); 
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Frame and Panel
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
		fileMenu.setBackground(Color.BLUE);
		fileMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		JMenuItem opeMenuItem = new JMenuItem("Load Card Set");
		fileMenu.add(opeMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		opeMenuItem.addActionListener(new OpenMenuListener()); 

		// Labels
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

		// The 2 buttons
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubmit.setBounds(10, 245, 105, 38);
		panel.add(btnSubmit);
		btnSubmit.addActionListener(new SubmitListener());

		btnNextCard = new JButton("Next Card");
		btnNextCard.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNextCard.setBounds(236, 245, 105, 38);
		panel.add(btnNextCard);
		btnNextCard.addActionListener(new NextCardListener());

		// Icons for right and wrong answers feedback
		ImageIcon icon1 = new ImageIcon(getUrl("/com/vili/resources/Correct_Icon.png"), "Correct");
		ImageIcon icon2 = new ImageIcon(getUrl("/com/vili/resources/Incorrect_Icon.png"), "Incorrect");
		

		correctIcon = new JLabel(icon1);
		correctIcon.setBounds(140, 240, 70, 50);
		panel.add(correctIcon);
		correctIcon.setVisible(false);
		
		incorrectIcon = new JLabel(icon2);
		incorrectIcon.setBounds(140, 240, 70, 50);
		panel.add(incorrectIcon);
		incorrectIcon.setVisible(false);
		

	}
	//	Returns the URL (complete path) of a resource or Null if the file is not found
	protected URL getUrl(String path) {
		java.net.URL url = getClass().getResource(path);
		if (url != null) {
			
			return url;
			
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
	
		StringTokenizer result = new StringTokenizer(lineToParse, ",");
		if (result.hasMoreTokens()) {
			
			Card card = new Card(result.nextToken(), result.nextToken());
			cardList.add(card);
					
		}
		
	}
		// Shows the next card when the button Next Card/Skip Card is pushed
	private void showNextCard() {
		
		currentCard = (Card) cardIterator.next();
		
		
		qArea.setText(currentCard.getQuestion());
		aArea.setText("");
		aArea.requestFocus();
		btnNextCard.setText("Skip Card");
		
	}
	
	public class NextCardListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			correctIcon.setVisible(false);
			incorrectIcon.setVisible(false);
				if (cardIterator.hasNext()) {
					
					showNextCard();
				
				}else {
					
					// no more cards to show
					qArea.setText("That was the last card.");
					aArea.setText("");
					btnNextCard.setEnabled(false);
					btnSubmit.setEnabled(false);
				}
			}
			
		}
	public class SubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String typedAnswer = aArea.getText();		//get the answer typed by the user
			
			// if the answer is correct
			if (typedAnswer.equalsIgnoreCase(currentCard.getAnswer())) {
				incorrectIcon.setVisible(false);
				correctIcon.setVisible(true);
				btnNextCard.setText("Next Card");
			} 
			// if the answer is NOT correct
			else  {
			correctIcon.setVisible(false);
			incorrectIcon.setVisible(true);			
			aArea.setText("Correct answer: "+currentCard.getAnswer());
			btnNextCard.setText("Next Card");
			}
		}
		
	}
}
