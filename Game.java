
package guessthetitle;

import java.util.Scanner;

/**
 * This Class will handle the game features and functions
 * @author eduardo
 */
public class Game {
    // Declare Variables
    private String title;
    private String gameDisplayTitle;
    private String handleSpacesTitle;
    private int playerPoints;
    private int movieTitleSize;
    private int indexArray[];
    //private char titleGuesses[];
    // create an instance of the class
    PlayerGuesses playerGuesses = new PlayerGuesses();
    
    // constructor
    public Game() {
        this.playerPoints = 10;
        this.movieTitleSize = 0;
    }// end default constructor
    
    public void startGame() throws Exception{
        // lets start the game
        // display the purpose
        System.out.println("The rules are simple, the computer randomly picks a ");
        System.out.println("movie title, and shows you how many letters it's made up of. ");
        System.out.println("Your goal is to try to figure out the movie by guessing one ");
        System.out.println("letter at a time. You get (10) tries");
        System.out.println("Ready! Begin!\n");
        // call function
        getTheRandomTitle();
    }// end startGame
    
    private void getTheRandomTitle() throws Exception{
        // create instance of ReadFile
        ReadFile readfile = new ReadFile();
        // call function
        // start getting the external file information
        readfile.parseTheFile();
        // get the random movie title to play the game
        title = readfile.getRandomMovieTitle();
        // call function
        displayTheGameBoard();
    }// end getTheRandomTitle
    /**
     * Once the computer picks a random title, it will display underscores "_"
     * in place of the real letters, thereby only giving away the number of
     * letters in the movie title.
     */
    private void displayTheGameBoard(){
        // replace the " " in the title with a -
        this.handleSpacesTitle = title.replace(" ", "-");
        // use REGEX to replaceAll letters to "_"
        gameDisplayTitle = handleSpacesTitle.replaceAll("[A-Za-z]", "_");
        // start the game
        gamePlay();
    }// end displayTheGameBoard
    /**
     * If a letter is indeed in the title the computer will reveal its correct
     * position in the word, if not, you lose a point.
     * If you lose 10 points, game over!
     */
    private void gamePlay(){
        // declare variables
        movieTitleSize = title.length();
        boolean hasWon = false;
        // pass the "_____-_____" gameDisplayTitle
        playerGuesses.blankString(gameDisplayTitle);
        // Display the gamePlay movie title "_____-_____"
        System.out.println(playerGuesses.toString());
        // handle if player still has points left
        if (playerPoints <= 0) {
            System.out.println("game over");
            System.exit(0);
        } else {
            // lets take another guess
            while (playerPoints > 0){
                System.out.println("Guess a letter:");
                // get the user input
                Scanner userInput = new Scanner(System.in);
                // Gets the first character in the input 
                char letter = userInput.nextLine().charAt(0);  
                // check for a isMatch if the letter is NOT blank
                if(letter != ' '){
                    // declare variable
                    Boolean correctGuess = false;
                    // pass the random movie title and the guess letter
                    correctGuess = checkLetter(title, letter);
                   // System.out.println(correctGuess); 
                    if (correctGuess) {
                        // how many times does the letter appear in the title?
                        int numberOfFoundLetters = countTheLetter(title,letter);
                        // find the index in the tile of the letter(s)
                        findIndexLocations(title,letter,numberOfFoundLetters);
                        // store the correct guesses
                        playerGuesses.correctGuesses(letter);
                        // Finally, detect when they have guessed all the letters 
                        // and let them know they've won!
                        hasWon = playerGuesses.checkIfWin(handleSpacesTitle);
                        if (hasWon){
                            System.out.println("You have guessed: " + title + " correctly!");
                            System.exit(0);
                        } else {
                            // stay in this loop
                        }// end if (hasWon) 
                    } else {
                        // no isMatch
                        // tell the user to try again
                        System.out.println("Try again");
                        // and take another guess
                        // check if the letter has already been guessed?
                        boolean hasGuessed = playerGuesses.incorrectGuesses(letter);
                        if (hasGuessed) {
                            // do nothing
                           // System.out.println("You have " + playerPoints + " points left");
                        } else {
                            // subtact one point 
                            playerPoints--;
                            //System.out.println("You have " + playerPoints + " points left");
                        }// end if (hasGuessed)
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
    
    /**
     * Checks if the letter is in the title
     * @param title
     * @param letter
     * @return 
     */
    private boolean checkLetter(String title, char letter){
        boolean isMatch = false;
        int indexOfLetter = title.indexOf(letter);// nothng found will return -1
        // the wrong guess
        if (indexOfLetter < 0){
            isMatch = false;
        } else {
            isMatch = true; 
        }// end if (indexOfLetter < 0)
        return isMatch;
    }// end checkLetter()

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
  	    // loop and check thru the title if there is a isMatch
        for (int index=0; index<title.length(); index++){ 
            // checking character in string 
            if (title.charAt(index) == letter) 
            amount++; 
        } // end for 
        return amount; 
    } // end countTheLetter
    
    /**
     * Finds the index of the guessed letter in the movie title string
     * to start building the player guessed movie title
     * @param title - the random movie title
     * @param letter - the guessed letter
     * @param size - of the array (how many times the letter exist is string)
     */
    private void findIndexLocations(String title, char letter, int size){
        int foundIndex = title.indexOf(letter);
        while(foundIndex >= 0) {
            // send the index and the letter to the hashmap
            playerGuesses.buildTheCorrectTitle(foundIndex, letter);
            // move on to the next letter in the title
            foundIndex = title.indexOf(letter, foundIndex+1);
        }//end while(foundIndex >= 0)
        // display
        System.out.println(playerGuesses.toString());
    }// end findIndexLocations()
    
}// end class Game
