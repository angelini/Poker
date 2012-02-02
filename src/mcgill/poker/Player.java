package mcgill.poker;

public class Player {

	ChipStack chips;
	Hand hand;

	public Player(ChipStack chips) {
		this.chips = chips;
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
	
	public int addChips(Chip chip, int amount) {
		return this.chips.addChips(chip, amount);
	}
	
	public int removeChips(Chip chip, int amount) throws TooFewChipsException {
		return this.chips.removeChips(chip, amount);
	}
	
	public int totalChips() {
		return this.chips.totalChips();
	}
	
}
