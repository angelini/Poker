package mcgill.poker;

public class Player implements Comparable<Player> {
	
	public static final int FOLDED = -1;
	public static final int BETTING = 0;
	public static final int ALL_IN = 1;

	private int money;
	private int amountInPots;
	private Hand hand;
	private int status;

	public Player(int money) {
		this.money = money;
		this.amountInPots = 0;
		this.hand = new Hand();
		this.status = BETTING;
	}
	
	public void reset() {
		this.amountInPots = 0;
		this.hand = new Hand();	
		this.status = BETTING;
	}
	
	//gives null pointer exception 
	public void addCard(Card card) throws TooManyCardsException {
		this.hand.addCard(card);
	}
	
	public int getHandSize() {
		return this.hand.getSize();
	}
	
	public HandRank getHandRank() throws TooFewCardsException {
		return new HandRank(this.hand);
	}
	
	public Hand getHand() {
		return this.hand;
	}
	
	public int addMoney(int amount) {
		this.money += amount;
		return this.money;
	}
	
	public void bet(int amount) throws OutOfMoneyException {
		if(amount > this.money) {
			throw new OutOfMoneyException();
		}
		
		this.money -= amount;
		this.amountInPots += amount;
	}
	
	public int getAmountInPots() {
		return this.amountInPots;
	}
	
	public int getTotalMoney() {
		return this.money;
	}	

	public void setStatus(int status) {
		this.status = status;
	}
	
	public boolean isFolded() {
		if (this.status == FOLDED) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isBetting() {
		if (this.status == BETTING) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAllIn() {
		if (this.status == ALL_IN) {
			return true;
		} else {
			return false;
		}
	}
	
	public int compareTo(Player player) {
		if (this.amountInPots < player.getAmountInPots()) {
			return -1;
		} else if (this.amountInPots > player.getAmountInPots()) {
			return 1; 
		} else {
			return 0;
		}
	}
}
