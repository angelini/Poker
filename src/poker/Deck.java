package poker;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	public static final int SIZE = 52;
	
	private int[] suits = {
			Card.HEART, 
			Card.DIAMOND,
			Card.CLUB,
			Card.SPADE
	};
	
	private ArrayList<Card> deck;
	
	public Deck() {
		this.deck = new ArrayList<Card>();
		
		for(int suit : this.suits) {
			for(int value = Card.LOW; value <= Card.HIGH; value++) {
				this.deck.add(new Card(value, suit));
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(this.deck);
	}
	
	public Card getTop() {
		return this.deck.remove(0);
	}
	
	public int getSize() {
		return this.deck.size();
	}
}
