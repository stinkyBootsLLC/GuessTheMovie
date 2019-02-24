/*
The rules are simple, the computer randomly picks a movie title, and shows you 
how many letters it's made up of. Your goal is to try to figure out the movie 
by guessing one letter at a time.
 */
package guessthetitle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class ReadFile {
    // declare globals
    private String[] titlesArray;
    private String randMovietitle;

    public ReadFile() {
        
    }// end default constructor 
    /**
     * Count the number of lines in the external file
     * to use for the size of the array
     * @throws FileNotFoundException
     * @return integer
     */
    private int countTheLines() throws FileNotFoundException{
        // to hold the number of lines
        int linenumber = 0;
        try{
            File file =new File("movies.txt");
            if(file.exists()){
                // create instances of the class
                FileReader fileReader = new FileReader(file);
                LineNumberReader lineNumReader = new LineNumberReader(fileReader);
                // loop thru the file and count
                while (lineNumReader.readLine() != null){
                    linenumber++;
                }// end while
                // closer the readers
                lineNumReader.close();
                fileReader.close();
            }else{
                System.out.println(" Cannot count - File does not exists!");
            }// end if(file.exists())
    		
    	}catch(IOException e){
            System.out.println("- " + e.toString());
    	}// end try catch
	return linenumber;
    }// end countTheLines 
    /**
     * read the file and store into array
     * @throws java.io.FileNotFoundException
     */
    public void parseTheFile()throws FileNotFoundException {
        // count the lines in the ext file
        int size = countTheLines();
        // use the number of lines found as the size of the array
        this.titlesArray = new String[size];
        try {
            // need a file object (instance)
            File file = new File("movies.txt");
            // need a scanner to read the contents of the file
            // pass the file object
            Scanner extFile = new Scanner(file);
            // variable for the array index
            int index = 0;
            // loop thru the movies.txt file
            while (extFile.hasNextLine()) {
                // store each movieTitle into a string variable
                String movieTitle = extFile.nextLine();
                // fill the array
                titlesArray[index] = movieTitle;
                index++;
            }//end while
            // close the scanner
            extFile.close();
        } catch (FileNotFoundException error) {
            System.out.println(error);
        }// end try catch
        // call function
        pickaRandomTitle();
    }// end parseTheFile()
    
    /**
     * the computer randomly picks a movie title
     */
    private void pickaRandomTitle(){
        // firt gen a random num from 0-25 (the size of the array)
        // assign the ran num ass the index
        int randIndex = (int) (Math.random() * 25);
        // display the rand element
        randMovietitle = titlesArray[randIndex];
    }// end pickaRandomTitle()
    
    public String getRandomMovieTitle(){
        return randMovietitle;
    }// end getRandomMovieTitle()

}// end class ReadFile
