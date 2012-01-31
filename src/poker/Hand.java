package poker;

import java.util.ArrayList;
import java.util.Iterator;

public class Hand implements Iterable<Card> {

	public static final int MAX_SIZE = 5;
	
	private ArrayList<Card> hand;
	
	public Hand() {
		this.hand = new ArrayList<Card>();
	}
	
	public Iterator<Card> iterator() {
		return this.hand.iterator();
	}

	public void addCard(Card card) throws TooManyCardsException {
		if(this.hand.size() >= MAX_SIZE) {
			throw new TooManyCardsException();
		}
		
		this.hand.add(card);
	}
	
	public HandRank getRank() throws TooFewCardsException {
		return new HandRank(this);
	}

	public int getSize() {
		return this.hand.size();
	}
	
	public Card getHighest() {
		Card max = new Card(0, 0);
		
		for(Card card : this.hand) {
			if(card.getValue() > max.getValue()) {
				max = card;
			}
		}
		
		return max;
	}
	
}
