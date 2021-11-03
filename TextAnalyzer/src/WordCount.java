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
	
	static ArrayList<WordFrequencyPairing> textAnalyzer = new ArrayList<WordFrequencyPairing>();

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	
	public static void main(String[] args) throws FileNotFoundException {

		// read whole file as String 
		String data = readFileToString(wordOcurrencesGUI.fileName);

		// remove extraneous symbols and text
		String cleanText = clean(data);
		
		// split string by white space and store as array
		String[] textArray = toStringArray(cleanText);
		
		// build ArrayList of word frequency count
		textAnalyzer = buildWordFrequencyArrayList(textArray);
		
		// sort ArrayList in descending order
		sort(textAnalyzer);

	}

	/**Return a string of the words in a text file 
	 *
	 * @param filename, name of the file
	 * @return string of words in textFile
	 */
	public static String readFileToString(String fileName){
		
		String data = "";
		try {
			data = new String(Files.readAllBytes(Paths.get(fileName)));
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**Return a string of the words in a text file,
	 * removes all html tags, punctuation
	 * @param textFile, file of text separated by whitespace
	 * @return string of words in textFile
	 */
	public static String clean(String textFile){
		if(textFile == null || textFile.isEmpty()){
			return null;
		}

		int a = textFile.indexOf("</big>");
		
		//removes text above poem title
		String textFile2 = textFile.substring(a);
		
		//removes text after poem
		String textFile3 = textFile2.substring(6, textFile2.indexOf("***"));

		//removes all html tags between '< >'
		textFile3 = textFile3.replaceAll("\\<.*?\\>", "" );

		//removes dashes
		String textFile4 = textFile3.replaceAll("&mdash", " ");

		//removes all remaining punctuation
		String textFile5 = textFile4.replaceAll("\\p{Punct}", " ");

		//removes all remaining punctuation, converts to all lower case letters, and splits string for array
		String words = textFile4.replaceAll("[^a-zA-Z ]", " ").toLowerCase();

		return words;
	}
	
	/**Return an array of the words in a text file,
	 * 
	 * @param stringName, string of text separated by whitespace
	 * @return array of strings in stringName
	 */
	public static String[] toStringArray(String stringName){
		if(stringName == null || stringName.isEmpty()){
			return null;
		}
		String[] words = stringName.split("\\s+");
		return words;
	}
	
	
	/**Return an ArrayList of the word frequency 
	 * counts in a string.
	 *
	 * @param filename, name of the file
	 * @return string of words in textFile
	 */
	public static ArrayList buildWordFrequencyArrayList(String[] stringArray) {
		
		Integer count = 1;
		for (int i = 0; i < stringArray.length; i++) {
			if(!(textAnalyzer.toString().contains(stringArray[i] + " "))) {
				for (int j = 1; j < stringArray.length; j++) {
					if(stringArray[i].equals(stringArray[j])) {
						count++;
					}
				}
			}
			String newText = stringArray[i].toString();
//			if(!(textAnalyzer.toString().contains(count + ": " + stringArray[i]))) {
			if(!(textAnalyzer.toString().contains(stringArray[i]))) {

				textAnalyzer.add(new WordFrequencyPairing(count, newText));
			}
			count = 0;
		}
		return textAnalyzer;
	}
	
	public static void sort(ArrayList textAnalyzerSort) {
		
		Comparator<WordFrequencyPairing> countComparator = (c1, c2) -> (int) (c1.getCount() - c2.getCount());
		textAnalyzerSort.sort(Collections.reverseOrder(countComparator));	
	}
	

	
}