package mcgill.fiveCardStud;

import java.util.ArrayList;

import mcgill.poker.Deck;
import mcgill.poker.OutOfMoneyException;
import mcgill.poker.Player;
import mcgill.poker.TooManyCardsException;

public class FiveCardStud {
	
	private int pot;
	private Deck deck;
	private ArrayList<Player> players;
	
	public FiveCardStud(ArrayList<Player> players) {
		this.pot = 0;
		this.players = players;
		this.deck = new Deck();
	}

	public void initialize(int ante) {
		for(Player player : this.players) {
			try {
				player.removeMoney(ante);
				this.pot += ante;
				
				player.addCard(this.deck.getTop());
				player.addCard(this.deck.getTop());
				
			} catch (OutOfMoneyException e) {
				this.players.remove(player);
			} catch (TooManyCardsException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public int getPot() {
		return pot;
	}
	
}
