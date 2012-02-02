package mcgill.fiveCardStud;

import mcgill.poker.Deck;
import mcgill.poker.Player;

public class FiveCardStud {
	
	private int pot;
	private Deck deck;
	private Player[] players;
	
	public FiveCardStud(Player[] players) {
		this.pot = 0;
		this.players = players;
		this.deck = new Deck();
	}

	public void initialize(int ante) {
		for(int i = 0; i < this.players.length; i++) {
			// TODO Remove cash for Ante
		}
	}
	
}
