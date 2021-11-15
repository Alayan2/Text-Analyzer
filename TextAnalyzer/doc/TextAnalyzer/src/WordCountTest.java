
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 */

/**
 * Unit testing for WordCount()
 * 
 * @author alayan2
 *
 */
public class WordCountTest {

	/**
	 * Unit Testing for readFileToString method.
	 */
	
	@Test
	public void testReadFileToString() throws IOException {
		
		// Create a temporary file.
		Path temp = Paths.get("/TextAnalyzer");
		
		temp = Files.createTempFile("tempFile", ".txt");

		File n = new File(temp.toString());
		
		// Write something to it.
		FileUtils.writeStringToFile(n, "hello world","ISO-8859-1");

		// Read it from temp file
		final String s = WordCount.readFileToString(n.toString());

		// Verify the content
		Assert.assertEquals("hello world", s);

	}

	/**
	 * Unit Testing for clean() method.
	 */
	@Test
	public void testClean() {
		
		String s = "<!DOCTYPE html><html><body><h1> </big>Hello World-</h1><p>hello world.***</p></body> </html>";
		String d = WordCount.clean(s);

		Assert.assertEquals("hello world hello world ", d);
	}

	/**
	 * Unit Testing for toStringArray() method.
	 */
	@Test
	public void testToStringArray() {
		String n = "hello world i have arrived";
		
		String[] s = WordCount.toStringArray(n);
		
		Assert.assertEquals(s.length, 5);
		Assert.assertEquals("hello", s[0]);
		Assert.assertEquals("world", s[1]);
		Assert.assertEquals("i", s[2]);
		Assert.assertEquals("have", s[3]);
		Assert.assertEquals("arrived", s[4]);

	}

	/**
	 * Unit Testing for buildWordFrequencyArrayList() method.
	 */
	@Test
	public void testBuildWordFrequencyArrayList() {
		
		String[] stringArray = {"two","three","one","two","three","four","three","four","four","four"};
		System.out.println(stringArray.toString());

		ArrayList<WordFrequencyPairing> stringArrayList = new ArrayList<WordFrequencyPairing>();
		
		stringArrayList = WordCount.buildWordFrequencyArrayList(stringArray);
		System.out.println(stringArrayList);

		Assert.assertEquals("1: one", stringArrayList.get(2).toString());
		Assert.assertEquals("2: two", stringArrayList.get(0).toString());
		Assert.assertEquals("3: three", stringArrayList.get(1).toString());
		Assert.assertEquals("4: four", stringArrayList.get(3).toString());

		System.out.println(stringArrayList);
	}
	
	/**
	 * Unit Testing for testSort() method.
	 */
	@Test
	public void testSort() {
		
		ArrayList<WordFrequencyPairing> stringArray = new ArrayList<WordFrequencyPairing>();
		stringArray.add(new WordFrequencyPairing(1, "a"));
		stringArray.add(new WordFrequencyPairing(2, "b"));
		stringArray.add(new WordFrequencyPairing(3, "c"));
		
		WordCount.sort(stringArray);
				
		Assert.assertEquals("[3: c, 2: b, 1: a]", stringArray.toString());

	}
}
