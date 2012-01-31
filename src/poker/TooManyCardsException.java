package poker;

public class TooManyCardsException extends Exception {

	private static final long serialVersionUID = 8113597435517058176L;
	
	public String getMessage() {
		return "Too many cards in the Hand";
	}
}
