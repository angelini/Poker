package mcgill.fiveCardStud;

import java.util.ArrayList;
import java.util.Scanner;

import mcgill.poker.OutOfMoneyException;
import mcgill.poker.Player;
import mcgill.poker.Hand;
import mcgill.poker.Card;
import mcgill.poker.TooFewCardsException;
import mcgill.poker.TooManyCardsException;

public class GameTest {
	
	private static final int LOW_BET = 20;
	private static final int STARTING_CASH = 400;
	private static final int NUMBER_OF_PLAYERS = 5;
	private static final int MAX_RAISES = 3;
	private static final int BRING_IN = 10;

	public static void main(String[] args) throws TooFewCardsException, TooManyCardsException, OutOfMoneyException {
		ArrayList<Player> players = new ArrayList<Player>();
		
		for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			players.add(new Player(STARTING_CASH));
		}
		
		FiveCardStud game = new FiveCardStud(players, LOW_BET, MAX_RAISES, BRING_IN);
		
		game.playRound();
	}
	
	//0 is check, -1 is fold, any positive integer is a raise or call.
	public static int getAction(int indexPlayer, int callAmount) {
		
		System.out.println("The amount to call is "+callAmount);
		System.out.println("Player "+indexPlayer+" please enter your desired action.\n"+
							"0 is check, -1 is fold, any positive integer is a raise or call:");
		
		Scanner scan = new Scanner(System.in);
		int action = scan.nextInt();
		
		System.out.println("You entered "+action);
		return action;
	}
	
	public static void printHand(Player player) {
		Hand hand = player.getHand();
		
		System.out.print("  #");
		for (Card card : hand) {
			System.out.print("|"+card.getValue()+"."+card.getSuit()+"|");
		}
		System.out.print("#  \n");
	}
	
	public static void printAmountInPots(Player player) {
		System.out.println("Amount In Pots = "+player.getAmountInPots());
	}
	
	public static void printPlayerStatus(Player player) {
		if (player.isAllIn()) {
			System.out.println("Your status: All In");
		} else if (player.isFolded()) {
			System.out.println("Your status: Folded");
		} else if (player.isBetting()) {
			System.out.println("Your status: Betting");
		}
	}
}
