# FlashCard

This is a simple flash cards app made with SWING library.
It can be used to practice vocabulary in a foreign language for exemple.
I included a capitals.csv file in the resources folder. 
It contains the europian countries and their respective capitals.
--------------------------------------------------------
How to Play:
  Press File/Load Card in order to load a file of cards. 
  It can be a text or a csv file for exemple.
  Each question and answer must be on a line, separated with a coma
  I included a capitals.csv file in the resources folder to serve as an exemple.
  
--------------------------------------------------------
About the code:

  The Card class is a simple POJO with 2 properties.
  The PlayerMain contains the Main method and creates a FlashCards object.
  The FlashCars class contains several methods like:
      initialise: it creates the User Interface
      loadFile: loads the opened file using the BufferedReader
      makeCard: takes lines from the previous method and ads them 
  to a list of type Card
      showNext: it is the listener method of the button Next Card
  The inner class SubmitListener compares the answer given by the user 
  with the answer property of the Card and if the answer is correct, 
  the "correct icon"(green) apears.
  If the answer is not right, the "incorrect icon"apears and the right answer
  apears in the text area. 
