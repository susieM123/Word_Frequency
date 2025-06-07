/*
file name:      WordCounter.java
Authors:        Ike Lage
last modified:  10/21/2023

How to run:     java WordCounter
*/

import java.util.ArrayList;
import java.io.* ;

// The purpose of this class is to manage a MapSet of word-count pairs.

public class WordCounter {

	private MapSet<String, Integer> wordCounts ;
	private int wordCount ;

	// Constructor builds the data structure and stores it in the map field
	public WordCounter( String data_structure ) {
		
		// Binary Search Tree
		if ( data_structure.equals( "BST" ) ) {
			wordCounts = new BSTMap<String, Integer>() ;
		}
		else { // HashMap
			assert data_structure.equals( "HashMap" ) : "Invalid data structure" ;
			wordCounts = new HashMap<String, Integer>() ;
		}
	}

	//given the filename of a text file, read the text file and return an ArrayList list of all the words in the file.
	public ArrayList<String> readWords( String filename ) {

		ArrayList <String> words = new ArrayList<String>() ;

		try {
		  // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
		  FileReader fr = new FileReader(filename);
		  // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
		  BufferedReader br = new BufferedReader(fr);
		  
		  // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
		  String line = br.readLine();
		  // start a while loop that loops while line isn't null
		  while(line != null){
		      // assign to line the result of calling the readLine method of your BufferedReader object.
		  		String [] lineWords = line.split("[ ]+") ;
		  		for ( String word : lineWords ) {
		  			words.add( word );
		  		}
		  		line = br.readLine();
		  }
		  // call the close method of the BufferedReader
		  br.close();

		  this.wordCount = words.size() ;

		  return words ;
		}
		catch(FileNotFoundException ex) {
		  System.out.println("WordCounter.readWords():: unable to open file " + filename );
		}
		catch(IOException ex) {
		  System.out.println("WordCounter.readWords():: error reading file " + filename);
		}

		return null ;
	}

	//given an ArrayList of words, put the words into the map data structure. Return the time taken in ms.
	public double buildMap( ArrayList<String> words ) {

		long startTime = System.currentTimeMillis() ;

		for ( String word : words ) {

			if ( this.wordCounts.containsKey( word ) ) {
				this.wordCounts.put( word , this.wordCounts.get( word ) + 1 );
			} else {
				this.wordCounts.put( word , 1 );
			}
		}

		long totalTime = System.currentTimeMillis() - startTime ;
		return totalTime ;
	}

	//return the total word count from the last time readWords was called.
	public int totalWordCount() {
		return this.wordCount ;
	}

	//return the unique word count
	public int uniqueWordCount() {
		return this.wordCounts.size() ;
	}

	//return the number of times the word occurred in the list of words.
	public int getCount( String word ) {
		Integer count = this.wordCounts.get( word );
		if ( count != null ) {
			return count ;
		}
		return 0 ;
	} 

	// return the frequency of the word in the list of words.
	public int getFrequency( String word ) {
		return this.getCount( word ) / this.totalWordCount() ;
	}

	//clear the map data structure.
	public void clearMap() {
		this.wordCounts.clear() ;
		this.wordCount = 0 ;
	}

	//write a word count file given the current set of words in the data structure.
	public boolean writeWordCount( String filename ) {

		try {
		 	// assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
		 	FileWriter fw = new FileWriter(filename);
			fw.write( Integer.toString( this.totalWordCount() ) + "\n" ) ;
			ArrayList <String> keys = this.wordCounts.keySet() ;
			ArrayList <Integer> values = this.wordCounts.values() ;
			for ( int i = 0 ; i < keys.size() ; i ++  ) {
				fw.write( keys.get( i ) + " - " + Integer.toString( values.get( i ) ) + "\n" );
			}
			fw.close() ;
			return true ;
		}
		catch(FileNotFoundException ex) {
		  System.out.println("WordCounter.readWords():: unable to open file " + filename );
		}
		catch(IOException ex) {
		  System.out.println("WordCounter.readWords():: error reading file " + filename);
		}
		return false ;
	}


	// Returns most frequent word. 
	public String mostFrequentWord() {

		if (wordCounts.size() == 0) {
			return null; 
		}

		String mostFrequentWord = null; 
		int maxFrequency = 0; 

		for (String word : wordCounts.keySet()) {
			int frequency = wordCounts.get(word);
			if (frequency > maxFrequency) {
				maxFrequency = frequency; 
				mostFrequentWord = word; 
			}
		}

		return mostFrequentWord;
	}


	public static void main( String[] args ) {

		// Usage Statement
		if (args.length == 0) {
			System.out.println("Usage: java WordCounter <filename>");
			return;
    	}

		// Command Line Argument - choose file from a specific year
		String filename = args[0].toLowerCase(); 

		// Number of runs to average
		int numRuns = 6; 

		// Creates word counter object to read the words from the file
		WordCounter wcBST = new WordCounter( "BST" ) ;
		WordCounter wcHashMap = new WordCounter("HashMap");
		
		// Reads words from command line argument
		ArrayList <String> words = wcBST.readWords(filename);

		// Analyzing maxDepth for each data structure
		wcBST.buildMap(words);
		wcHashMap.buildMap(words);
		System.out.println("BST MaxDepth: " + wcBST.wordCounts.maxDepth());
		System.out.println("HashMap MaxDepth: " + wcHashMap.wordCounts.maxDepth());

		// Finding the most frequent word (Extension)
		String mostFrequentWord = wcBST.mostFrequentWord(); 
		System.out.println("Most Frequent Word: " + mostFrequentWord);
		System.out.println("Word Count: " + wcBST.getCount(mostFrequentWord));

		// Initialize variables to save results from running multiple trials on each data structure
		double totalBstTime = 0; 
		double totalHashMapTime = 0; 

		// Analyzing time taken to build map using buildMap() method
		for (int run = 0; run < numRuns; run++) {

			totalBstTime += wcBST.buildMap(words); // Uses buildMap method to add total time taken for BST
			totalHashMapTime += wcHashMap.buildMap(words); // Adds total time taken for HashMap
			wcBST.clearMap(); // Clears map for the next run
			wcHashMap.clearMap();
		}

		// Calculates and prints average time
		double avgBstTime = totalBstTime / numRuns;
		double avgHashMapTime = totalHashMapTime / numRuns;
		System.out.println("Average BST Time: " + avgBstTime);
		System.out.println("Average HashMap Time: " + avgHashMapTime);

		// Initializes variables to keep track of word counts.
		int pollutionCount = 0; 
		int trumpCount = 0; 
		int racismCount = 0;
		pollutionCount = wcBST.getCount("pollution");
		trumpCount = wcBST.getCount("Trump");
		racismCount = wcBST.getCount("racism");

		// Prints results
		System.out.println("Total Occurances of 'pollution': " + pollutionCount);
		System.out.println("Total Occurances of 'Trump': " + trumpCount);
		System.out.println("Total Occurances of 'racism': " + racismCount);

		//Writes word counts to an output file
		wcBST.writeWordCount( "output.txt" ) ;
	}
}

// How to Run: java -Xmx4g WordCounter <filename>
// -Xmx4g command increases the amount of memory Java can utilize

/*
 * Reddit filenames: 
 * 		reddit_comments_2015.txt
 * 		reddit_comments_2014.txt 
 * 		reddit_comments_2013.txt 
 * 		reddit_comments_2012.txt 
 * 		reddit_comments_2011.txt 
 * 		reddit_comments_2010.txt 
 * 		reddit_comments_2009.txt
 * 		reddit_comments_2008.txt
 */