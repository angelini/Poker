package mcgill.fiveCardStud;

import mcgill.poker.Player;

public class GameTest {
	
	private static final int ANTE = 2;
	private static final int STARTING_CASH = 250;
	private static final int NUMBER_OF_PLAYERS = 5;

	public static void main(String[] args) {
		Player[] players = new Player[NUMBER_OF_PLAYERS];
		
		for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			players[i] = new Player(STARTING_CASH);
		}
		
		FiveCardStud game = new FiveCardStud(players);
		
		game.initialize(ANTE);
	}
	
}
