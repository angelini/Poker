package mcgill.poker;

import java.util.Arrays;

public class HandRank {

	public static final int HIGH_CARD = 0;
	public static final int ONE_PAIR = 1;
	public static final int TWO_PAIR = 2;
	public static final int THREE_OF_A_KIND = 3;
	public static final int STRAIGHT = 4;
	public static final int FLUSH = 5;
	public static final int FULL_HOUSE = 6;
	public static final int FOUR_OF_A_KIND = 7;
	public static final int STRAIGHT_FLUSH = 8;
	
	private int level;
	private int[] value;
	private Hand hand;
	
	private boolean flush;
	private boolean straight;
	private int pairs;
	private int triples;
	private int quads;
	
	public static int compareHands(Hand first, Hand second, int numCards) throws TooFewCardsException, TooManyCardsException {
		Hand firstNew;	
		Hand secondNew;
		
		if (numCards == 1) {
			return compareCards(first.cardAt(1), second.cardAt(1));
		} else if (numCards < 5) {
			firstNew = new Hand();
			secondNew = new Hand();
			
			for (int i = 1; i < first.getSize(); i++) {
				firstNew.addCard(first.cardAt(i));
				secondNew.addCard(second.cardAt(i));
			}
		} else {
			firstNew = first;
			secondNew = second;
		}
		
		HandRank first_rank = new HandRank(firstNew);
		HandRank second_rank = new HandRank(secondNew);
		
		return compareRanks(first_rank, second_rank);
	}
	
	//returns 0 if first card has higher rank than second card and 1 if reversed or equal.
	public static int compareCards(Card first, Card second) {
		if (first.getValue() > second.getValue()) {
			return 0;
		} else if (first.getValue() < second.getValue()) {
			return 1;
		} else if (first.getSuit() > second.getSuit()) {
			return 0;
		} else if (first.getSuit() < second.getSuit()){
			return 1;
		} else {
			return 1;
		}
		
	}
	
	public static int compareRanks(HandRank first, HandRank second) throws TooFewCardsException {
		if(first.getLevel() > second.getLevel()) {
			return 0;
		} else if(first.getLevel() < second.getLevel()) {
			return 1;
		}
		
		int[] first_values = first.getValue();
		int[] second_values = second.getValue();
		
		for(int i = 0; i < first_values.length; i++) {
			if(first_values[i] > second_values[i]) {
				return 0;
			} else if(first_values[i] < second_values[i]) {
				return 1;
			}
		}
		
		return -1;
	}
	
	public HandRank(Hand hand) throws TooFewCardsException {
		if(hand.getSize() < 2) {
			throw new TooFewCardsException();
		}
		
		this.hand = hand;
		
		int i = 0;
		int[] values = new int[hand.getSize()];
		int[] suits = new int[hand.getSize()];
		
		for(Card card : hand) {
			values[i] = card.getValue();
			suits[i] = card.getSuit();
			i++;
		}
		
		//used to evaluate multiple hand sizes 
		switch (hand.getSize()) {
			case 2:
				this.pairs = this.detectSimilar(values, 2);
				
				this.level = this.determineLevel();
				this.value = this.determineValue(values);
				break;
				
			case 3:
				this.pairs = this.detectSimilar(values, 2);
				this.triples = this.detectSimilar(values, 3);
				
				this.level = this.determineLevel();
				this.value = this.determineValue(values);
				break;
				
			case 4:
				this.pairs = this.detectSimilar(values, 2);
				this.triples = this.detectSimilar(values, 3);
				this.quads = this.detectSimilar(values, 4);
				
				this.level = this.determineLevel();
				this.value = this.determineValue(values);
				break;
				
			case 5:
				this.flush = this.detectFlush(suits);
				this.straight = this.detectStraight(values);
				this.pairs = this.detectSimilar(values, 2);
				this.triples = this.detectSimilar(values, 3);
				this.quads = this.detectSimilar(values, 4);
				
				this.level = this.determineLevel();
				this.value = this.determineValue(values);
				break;
		}
	}
	
	private int[] reverseIntArray(int[] values) {
		for(int i = 0; i < values.length / 2; i++) {
			int temp = values[i];
		    values[i] = values[values.length - i - 1];
		    values[values.length - i - 1] = temp;
		}
		
		return values;
	}
	
	private int countInArray(int[] array, int key) {
		int count = 0;
		
		for(int i = 0; i < array.length; i++) {
			if(array[i] == key) {
				count++;
			}
		}
		
		return count;
	}
	
	private int[] determineValue(int[] values) {
		Arrays.sort(values);
		values = reverseIntArray(values);
		
		if(this.level == HIGH_CARD 
				|| this.level == STRAIGHT_FLUSH
				|| this.level == FLUSH
				|| this.level == STRAIGHT) {
			
			return values;
		}
		
		if(this.level == ONE_PAIR 
				|| this.level == THREE_OF_A_KIND
				|| this.level == FOUR_OF_A_KIND
				|| this.level == FULL_HOUSE) {
			
			int size_of_similar = 0;
			switch(this.level) {
			case ONE_PAIR:
				size_of_similar = 2;
				break;
			case THREE_OF_A_KIND:
				size_of_similar = 3;
				break;
			case FULL_HOUSE:
				size_of_similar = 3;
				break;
			case FOUR_OF_A_KIND:
				size_of_similar = 4;
				break;
			}
			
			int buffer_index = 0;
			int result_index = size_of_similar;
			int[] result = new int[values.length];
			int[] buffer = new int[values.length - size_of_similar];
			
			for(int i = 0; i < values.length; i++) {
				if(countInArray(values, values[i]) == size_of_similar) {
					for(int k = 0; k < size_of_similar; k++) {
						result[k] = values[i];
					}
				} else {
					buffer[buffer_index] = values[i];
					buffer_index++;
				}
			}
			
			for(int j = 0; j < buffer.length; j++) {
				result[result_index] = buffer[j];
				result_index++;
			}
			
			return result;
		}
		
		if(this.level == TWO_PAIR) {
			int j = 0;
			int buffer = -1;
			int pairs_index = 0;
			int[] results = new int[values.length];
			int[] pairs = new int[4];
			
			for(int i = 0; i < values.length; i++) {
				if(countInArray(values, values[i]) == 2) {
					pairs[pairs_index] = values[i];
					pairs_index++;
				} else {
					buffer = values[i];
				}
			}

			Arrays.sort(pairs);
			pairs = reverseIntArray(pairs);
			for(; j < pairs.length; j++) {
				results[j] = pairs[j];
			}
			
			results[j] = buffer;
			return results;
		}
		
		return values;
	}

	private int determineLevel() {
		if(this.straight && this.flush) {
			return STRAIGHT_FLUSH;
		}
		
		if(this.quads > 0) {
			return FOUR_OF_A_KIND;
		}
		
		if(this.triples > 0 && this.pairs > 0) {
			return FULL_HOUSE;
		}
		
		if(this.flush) {
			return FLUSH;
		}
		
		if(this.straight) {
			return STRAIGHT;
		}
		
		if(this.triples > 0) {
			return THREE_OF_A_KIND;
		}
		
		if(this.pairs > 1) {
			return TWO_PAIR;
		}
		
		if(this.pairs > 0) {
			return ONE_PAIR;
		}
		
		return HIGH_CARD;
	}

	private boolean detectFlush(int[] suits) {
		int first = -1;
		
		for(int suit : suits) {
			if(first == -1) {
				first = suit;
			}
			
			if(suit != first) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean detectStraight(int[] values) {
		Arrays.sort(values);
		
		for(int i = 0; i < values.length - 1; i++) {
			if(values[i] + 1 != values[i + 1]) {
				return false;
			}
		}
		
		return true;
	}
	
	private int detectSimilar(int[] values, int amount) {
		Arrays.sort(values);
		int similar = 0;
		
		for(int i = 0; i < values.length - (amount - 1); i++) {
			if(countInArray(values, values[i]) == amount) {
				similar++;
				i++;
			}
		}
		
		return similar;
	}
	
	public String getRankName() {
		switch(this.level) {
		case STRAIGHT_FLUSH: 
			return "straight flush";
			
		case FOUR_OF_A_KIND:
			return "four of a kind";
			
		case FULL_HOUSE:
			return "full house";
			
		case FLUSH:
			return "flush";
			
		case STRAIGHT:
			return "straight";
			
		case THREE_OF_A_KIND:
			return "three of a kind";
			
		case TWO_PAIR:
			return "two pair";
			
		case ONE_PAIR:
			return "one pair";

		default:
			return "high card";
		}
	}

	public int getLevel() {
		return level;
	}

	public Hand getHand() {
		return hand;
	}
	
	public int[] getValue() {
		return value;
	}

	public boolean isFlush() {
		return flush;
	}

	public boolean isStraight() {
		return straight;
	}

	public int getPairs() {
		return pairs;
	}

	public int getTriples() {
		return triples;
	}

	public int getQuads() {
		return quads;
	}
	
}
