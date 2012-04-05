package mcgill.poker;

public class PokerTest {

	public static void main(String[] args) {
		Hand hand1 = new Hand();
		Hand hand2 = new Hand();
		
		try {
			hand1.addCard(new Card(5, 0));
			hand1.addCard(new Card(2, 0));
			hand1.addCard(new Card(3, 0));
		} catch (TooManyCardsException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		try {
			hand2.addCard(new Card(11, 0));
			hand2.addCard(new Card(11, 2));
			hand2.addCard(new Card(11, 1));
		} catch (TooManyCardsException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		try {
			int result = HandRank.compareHands(hand1, hand2, 2);
			System.out.println("Result is: " + result);
			
		} catch (TooFewCardsException e) {
			e.printStackTrace();
		} catch (TooManyCardsException e) {
			e.printStackTrace();
		}
		
		/*
		Hand hand1 = new Hand();
		Hand hand2 = new Hand();
		Deck deck = new Deck();
		
		deck.shuffle();
		
		// Build the first hand
		for(int i = 0; i < Hand.MAX_SIZE; i++) {
			try {
				Card card = deck.getTop();
				hand1.addCard(card);
				System.out.println("HAND 1 - Card: value - " + card.getValue() + " ; suit - " + card.getSuit());
			} catch (TooManyCardsException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		System.out.println("");
		
		// Build the second hand
		for(int i = 0; i < Hand.MAX_SIZE; i++) {
			try {
				Card card = deck.getTop();
				hand2.addCard(card);
				System.out.println("HAND 2 - Card: value - " + card.getValue() + " ; suit - " + card.getSuit());
			} catch (TooManyCardsException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		
		System.out.println("");
		
		try {
			HandRank rank1 = new HandRank(hand1);
			HandRank rank2 = new HandRank(hand2);
			
			int result = HandRank.compareRanks(rank1, rank2);
			
			if(result == 0) {
				System.out.println("HAND 1 is the Winner! With a " + rank1.getRankName());
			} else if(result == 1) {
				System.out.println("HAND 2 is the Winner! With a " + rank2.getRankName());
			} else if(result == -1) {
				System.out.println("It is a tie! With " + rank1.getRankName());
			}
			
		} catch (TooFewCardsException e) {
			throw new RuntimeException(e.getMessage());
		}
		*/
	}
	
}
