package mcgill.poker;

public class Player {

	private int money;
	private Hand hand;

	public Player(int money) {
		this.money = money;
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
	
	public int addMoney(int amount) {
		this.money += amount;
		return this.money;
	}
	
	public int removeMoney(int amount) throws OutOfMoneyException {
		if(amount > this.money) {
			throw new OutOfMoneyException();
		}
		
		this.money -= amount;
		return this.money;
	}
	
	public int getTotalMoney() {
		return this.money;
	}	
}
