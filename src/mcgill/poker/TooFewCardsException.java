package mcgill.poker;

public class TooFewCardsException extends Exception {

	private static final long serialVersionUID = -3805189325624145873L;
	
	public String getMessage() {
		return "Too few cards in the Hand";
	}
	
}
