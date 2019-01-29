
package guessthetitle;


import java.util.Arrays;
import java.util.Scanner;


public class Game {
    private String title;
    private String gameDisplayTitle;
    private int playerPoints;
    private int movieTitleSize;
    private int indexArray[];
    //private char titleGuesses[];
    PlayerGuesses playerGuesses = new PlayerGuesses();
    
    

    public Game() {
        this.playerPoints = 10;
        this.movieTitleSize = 0;
    }// end default constructor
    
    public void startGame() throws Exception{
        System.out.println("The rules are simple, the computer randomly picks a ");
        System.out.println("movie title, and shows you how many letters it's made up of. ");
        System.out.println("Your goal is to try to figure out the movie by guessing one ");
        System.out.println("letter at a time.\n");
        // lets start the game
        getTheRandomTitle();
    }// end startGame
    
    private void getTheRandomTitle() throws Exception{
        ReadFile readfile = new ReadFile();
        // call function
        // start getting the external file informations
        readfile.parseTheFile();
        // get the random movie title to play the game
        title = readfile.getRandomMovieTitle();
        displayTheGameBoard();
    }// end getTheRandomTitle
    /**
     * Once the computer picks a random title, it will display underscores "_"
     * in place of the real letters, thereby only giving away the number of
     * letters in the movie title.
     */
    private void displayTheGameBoard(){
        // replace the " " in the title with a -
        String handleSpacesTitle = title.replace(" ", "-");
        // use REGEX to replaceAll letters to _
        gameDisplayTitle = handleSpacesTitle.replaceAll("[A-Za-z]", "_");
        // Display the gamePlay movie title
        System.out.println("Movie: "+ gameDisplayTitle + "\n");
        // start the game
        gamePlay();
    }// end displayTheGameBoard
    
    private void gamePlay(){
    /*
        If a letter is indeed in the title the computer will reveal its correct 
        position in the word, if not, you lose a point. 
        If you lose 10 points, game over!

    */
        
        movieTitleSize = title.length();
        playerGuesses.blankString(gameDisplayTitle);
        
        // handle if player still has points left
        if (playerPoints <= 7) {
            System.out.println("game over");
            
        } else {
            // lets take another guess
            while (playerPoints >= 7){
                System.out.println("Guess a letter:");
                // get the user input
                Scanner userInput = new Scanner(System.in);
                // Gets the first character in the input 
                char letter = userInput.nextLine().charAt(0);  
                // check for a match if the letter is NOT blank
                if(letter != ' '){
                    // declare variable
                    Boolean correctGuess = false;
                    // pass the random movie title and the guess letter
                    correctGuess = checkLetter(title, letter);
                   // System.out.println(correctGuess); 
                    if (correctGuess) {
                        // there is a match 
                        // need to start storing the letters
                        // and take another guess
                        // how many times does the letter appear in the title?
                        // count
                        int numberOfFoundLetters = countTheLetter(title,letter);
                        // debug
                       // System.out.println("letter found " + numberOfFoundLetters + " time(s)" );
                        // what indexes of the string
                        findIndexLocations(title,letter,numberOfFoundLetters);
                        //create a array of the found letters / which will be the title
                        System.out.println("correct");
                        //System.exit(0);
                        playerGuesses.correctGuessList(letter);
                        // here we are going to have to check if the player has 
                        // guessed all the letters correctly
                        
                        // Finally, detect when they have guessed all the letters 
                        // and let them know they've won!
                    } else {
                        // no match
                        // tell the user to try again
                        
                        System.out.println("Try again");
                        // and take another guess
                        // Add the logic to keep track of wrong letters so they 
                        // don't lose points for guessing the same letter twice.
                        boolean letterAlreadyGuessed = playerGuesses.incorrectGuessList(letter);
                        if (letterAlreadyGuessed) {
                            // do nothing
                            System.out.println("You have " + playerPoints + " points left");
                            
                        } else {
                            // subtact one point 
                            playerPoints--;
                            System.out.println("You have " + playerPoints + " points left");
                        }// end if 
                    }// end if       
                } else {
                    // handle blank characters
                    System.out.println("blank characters are not allowed");
                    // exit the game for now
                    System.exit(0);
                }// end if(letter != ' ')
            }// end while (playerPoints > 0)
        }// end if player still has lives left
    }// end gamePlay
    
    
    private boolean checkLetter(String title, char letter){
        // debug
        System.out.println(title);
        // does the letter exist in the title?
        // if it does then the index of that letter will be returned
        boolean match = false;
        int indexOfLetter = title.indexOf(letter);// nothng found will return -1
        // the wrong guess
        if (indexOfLetter < 0){
            match = false;
            System.out.println("Sorry the letter (" + letter + ") is not in the title");  
        } else {
            match = true;
            // the correct letter has been guessed
            System.out.println("("+letter + ") is in the title!");  
        }// end if (indexOfLetter < 0)
        return match;
    
    }// end checkLetter
    

    /**
     * I need to know how many times the letter appears in the Movie Title
     * Method that returns a count of the given character in the string 
     * I need this method to send the amount to findIndexLocations()
     * @param title
     * @param letter
     * @return amount
     */
    private int countTheLetter (String title, char letter){ 
        int amount = 0; 
  	    // loop and check thru the title if there is a match
        for (int index=0; index<title.length(); index++){ 
            // checking character in string 
            if (title.charAt(index) == letter) 
            amount++; 
        } // end for 
        return amount; 
    } // end countTheLetter
    
    /**
     * Finds the index of the guessed letter in the movie title string
     * and stores into an array
     * @param title - the random movie title
     * @param letter - the guessed letter
     * @param size - of the array (how many times the letter exist is string)
     */
    private void findIndexLocations(String title, char letter, int size){
        // index need an array to store the indexes
        // the size is how many times the letter appears in the string
       
        indexArray = new int[size];
        int index = 0;
        int foundIndex = title.indexOf(letter);
        while(foundIndex >= 0) {
            // build the array
            indexArray[index] = foundIndex;
            // send the index and the letter to the hashmap to build 
            // the area to collect the correct letters and the letter
            // index location
            playerGuesses.buildTheCorrectTitle(foundIndex, letter);
           
    
            // move on to the next letter
            foundIndex = title.indexOf(letter, foundIndex+1);
            index++;
        }//end while
        
        // debug purposes to make sure the array has been populated
        //System.out.println("letter index location(s) "+ Arrays.toString(indexArray));
       
   
        
    }// end findIndexLocations
    

    
    
    
    
    
}// end class
