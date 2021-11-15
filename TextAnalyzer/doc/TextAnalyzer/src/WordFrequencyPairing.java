
/**
 * Getters and setters for word frequency count and text. 
 * 
 * @author alayan2
 */
 
public class WordFrequencyPairing {
	private Integer count;
	private String word;


	/**
	 * @param count
	 * @param word 
	 */ 
	
	public WordFrequencyPairing(Integer count, String word) {

		this.count = count;
		this.word = word;

	}

	// Getter
	public Integer getCount() {
		return count;
	}

	// Getter
	public String getWord() {
		return word;
	}

	// Setter
	public void setCount(Integer newCount) {
		this.count = newCount;
	}

	// Setter
	public void setWord(String newWord) {
		this.word = newWord;
	}

	@Override
	public String toString(){
		return  count + ": " + word ; 
	}
}

