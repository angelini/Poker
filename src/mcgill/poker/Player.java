package mcgill.poker;

import java.util.HashMap;
import java.util.Map;

public class Player {

	Map<Chip, Integer> chips;
	Hand hand;

	public Player() {
		this.chips = new HashMap<Chip, Integer>();
	}
	
	public void addCard(Card card) throws TooManyCardsException {
		this.hand.addCard(card);
	}
	
	public int getHandSize() {
		return this.hand.getSize();
	}
	
	public HandRank getHandRank() throws TooFewCardsException {
		return new HandRank(this.hand);
	}
	
	public int addChips(Chip chip, Integer amount) {
		Integer current = this.chips.get(chip);
		this.chips.put(chip, current + amount);
		
		return this.totalChips();
	}
	
	public int removeChips(Chip chip, Integer amount) throws TooFewChipsException {
		Integer current = this.chips.get(chip);
		
		if(current > amount) {
			throw new TooFewChipsException();
		}
		
		this.chips.put(chip, current - amount);
		
		return this.totalChips();
	}

	private int totalChips() {
		int total = 0;
		
		for(Map.Entry<Chip, Integer> entry : this.chips.entrySet()) {
			total += entry.getKey().getValue() * entry.getValue();
		}
		
		return total;
	}
	
}
