package com.vili.flashcards;

public class Card {
	private String question;
	private String answer;
	
	 Card(String q, String a) {
		 
		 question = q;
		 answer = a;
		
	 }
	 
		public String getQuestion() {
			return question;
		}

		public String getAnswer() {
			return answer;
		}

}
