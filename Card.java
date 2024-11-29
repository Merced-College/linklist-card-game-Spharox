
public class Card{
	private int value;
	private String SUIT;
	private String RANK;

	public Card(int value, String SUIT, String RANK){
		this.value = value;
		this.SUIT = SUIT;
		this.RANK = RANK;
	}

	public int getValue(){
		return value;
	}

	public String getSuit(){
		return SUIT;
	}

	public String getRank(){
		return RANK;
	}

	public String toString(){
		return RANK + " of " + SUIT + ", with value of " + value;
	}
}