package poker;

public class PokerTest {

	public static void main(String[] args) {
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
			int result = HandRank.compareHands(hand1, hand2);
			
			if(result == 0) {
				System.out.println("HAND 1 is the Winner!");
			} else if(result == 1) {
				System.out.println("HAND 2 is the Winner!");
			} else if(result == -1) {
				System.out.println("It is a tie!");
			}
			
		} catch (TooFewCardsException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
