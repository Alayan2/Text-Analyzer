import java.io.FileNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 */

/**
 * Assignment Description
 * Write a text analyzer that reads a file and outputs statistics 
 * about that file. It should output the word frequencies of all 
 * words in the file, sorted by the most frequently used word. 
 * The output should be a set of pairs, each pair containing a 
 * word and how many times it occurred in the file.
 * 
 * @author alayan2
 *
 */
public class WordCount {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	
	static ArrayList<WordFrequencyPairing> textAnalyzer = new ArrayList<WordFrequencyPairing>();


	public static void main(String[] args) throws FileNotFoundException {

		// read whole file as String 
		String data = "";
		try {
			data = new String(Files.readAllBytes(Paths.get(wordOcurrencesGUI.fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] textArray = clean(data);


		Integer count = 0;
		for (int i = 0; i < textArray.length; i++) {
			if(!(textAnalyzer.toString().contains(textArray[i] + " "))) {
				for (int j = 1; j < textArray.length; j++) {
					if(textArray[i].equals(textArray[j])) {
						count++;
					}
				}
			}
			String newText = textArray[i].toString();
			if(!(textAnalyzer.toString().contains(count + ": " + textArray[i]))) {
				textAnalyzer.add(new WordFrequencyPairing(count, newText));
			}
			count = 0;
		}

		Comparator<WordFrequencyPairing> countComparator = (c1, c2) -> (int) (c1.getCount() - c2.getCount());

		textAnalyzer.sort(Collections.reverseOrder(countComparator));	    
		
	    //Sublist to List
	     List<WordFrequencyPairing> list = textAnalyzer.subList(0, 20);
//	     System.out.println("Top 20 Most Frequently Used Words in 'The Raven': "+list);
//	     System.out.println("\n" + "All Word Frequencies in 'The Raven': "+textAnalyzer.toString());

	}


	/**Return an array of the words in a text file 
	 * removes all html tags, punctuation and split by spaces
	 * @param textFile, file of text separated by whitespace
	 * @return array of words in textFile
	 */
	public static String[] clean(String textFile){
		if(textFile == null || textFile.isEmpty()){
			return null;
		}

		//removes text above poem title
		String textFile2 = textFile.substring(textFile.indexOf("/big>"));

		//removes text after poem
		String textFile3 = textFile2.substring(0, textFile2.indexOf("***"));

		//removes all html tags between '< >'
		textFile3 = textFile3.replaceAll("\\<.*?\\>", "" );

		//removes dashes
		String textFile4 = textFile3.replaceAll("&mdash", " ");

		//removes all remaining punctuation
		String textFile5 = textFile4.replaceAll("\\p{Punct}", " ");

		//removes all remaining punctuation, converts to all lower case letters, and splits string for array
		String[] words = textFile4.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");

		return words;
	}
}