
package guessthetitle;

import java.util.*;


public class PlayerGuesses {
    // list to track the incorrect guessed letters
    private final List incorrectLetters;
    // list to track to correct guessed letter
    private final List correctLetters;
    // track the correct guessed letters AND the index location of the letter
    // in the title
    private final Map<Integer, Character> correctGuessedTitle;//identifier for hashMap
    // build a string out of the correct guessed letters
    private StringBuilder correctGuessesString;
    // constructor
    public PlayerGuesses() {
        incorrectLetters = new ArrayList();
        correctLetters = new ArrayList();
        correctGuessedTitle = new HashMap<>();
    }// end default constructor
    /**
     * logic to keep track of the player's wrong letters
     * @param guess
     * @return 
     */
    public boolean incorrectGuessList(char guess){
        boolean hasGuessed = false;
        // check if the letter is already in the list?
        if (incorrectLetters.contains(guess)) {
            System.out.println("letter already guessed");
            // need logic here to return 
            hasGuessed = true;
        } else {
            // Build the list
            incorrectLetters.add(guess);
        }
        return hasGuessed;
    }// end incorrectGuessList()
    
    public void correctGuessList(char guess){
 
        // check if the letter is already in the list?
        if (correctLetters.contains(guess)) {
            System.out.println("letter already guessed correctly");
            // need logic here to return 
           
        } else {
            correctLetters.add(guess);
            // debug
            //System.out.println("correct letters = " + correctLetters);
        }// end if 
    }// end correctGuessList()
    
    public void blankString(String title){
        correctGuessesString = new StringBuilder(title);
        System.out.println("blankString = " + correctGuessesString);
       
        
//        for (int i = 0; i < size; i++) {
//            correctGuessesString.insert(i, "_");
//            System.out.println("blankString = " + correctGuessesString);
//            
//        }
        
    }//end
    
    
    
    public void buildTheCorrectTitle(int location, char value){
        // this is coming from game.java when the user guesses a correct letter 
        // that is in the playing movie title.
        // if the movie is "samurai" and the player guesses "a" then "a" appears
        // twice in the movie title
        // 0123456
        //  1   5
        // _a___a_
        correctGuessedTitle.put(location, value);
        // System.out.println("--> "+correctGuessedTitle);
        // delete the "_" char at the index location
        correctGuessesString.deleteCharAt(location);
        // now that the location is blank
        // insert the new correct guess
        correctGuessesString.insert(location, value);
        //
        System.out.println("builder-> " + correctGuessesString);
        //displayTheCorrectTitle();
        // works perfect
        // i need some logic to check for a match
        
    }// end buildTheCorrectTitle
    
//    public void displayTheCorrectTitle(){
//        
//        for (char i : correctGuessedTitle.values()) {
//           // correctGuessesString.append(i);
//           //System.out.println(correctGuessesString);
//            
//        }// end for
//        
//        
//        // Print keys and values
//        for (int i : correctGuessedTitle.keySet()) {
//            //System.out.println("key: " + i + " value: " + correctGuessedTitle.get(i));
//        }
//        
//    }// end displayTheCorrectTitle
    
    
    
    
    
    
    
    
    
}// end class
