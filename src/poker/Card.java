package poker;

public class Card {

	public static final int HEART = 0;
	public static final int DIAMOND = 1;
	public static final int SPADE = 2;
	public static final int CLUB = 3;
	
	public static final int LOW = 2;
	public static final int HIGH = 14;
	
	private int value;
	private int suit;
	
	public Card(int value, int suit) {
		this.value = value;
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public int getSuit() {
		return suit;
	}
	
}
