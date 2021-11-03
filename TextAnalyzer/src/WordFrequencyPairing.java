
public class WordFrequencyPairing {
	private Integer count;
	private String word;
	
	public WordFrequencyPairing(Integer count, String word) {
	
		this.count = count;
		this.word = word;

	}
	  
	// Getter
	  public Integer getCount() {
	    return count;
	  }
	  
	  public String getWord() {
		    return word;
		  }

	  // Setter
	  public void setCount(Integer newCount) {
	    this.count = newCount;
	  }

	  public void setWord(String newWord) {
		    this.word = newWord;
		  }
	  
	  @Override
	    public String toString(){
	        return  count + ": " + word ; 
	    }
}

