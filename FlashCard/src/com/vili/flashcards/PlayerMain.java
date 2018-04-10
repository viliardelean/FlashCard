package com.vili.flashcards;

import javax.swing.SwingUtilities;

public class PlayerMain {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					
					UserInterface window = new UserInterface();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
