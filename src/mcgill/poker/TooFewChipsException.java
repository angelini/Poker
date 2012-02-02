package mcgill.poker;

public class TooFewChipsException extends Exception {

	private static final long serialVersionUID = 8422294394651129577L;

	public String getMessage() {
		return "Player has too few of these chips";
	}
	
}
