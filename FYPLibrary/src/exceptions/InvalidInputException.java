package exceptions;

/**
 * A class to express an exception thrown when an input is invalid
 * @author Balazs Pete
 *
 */
public class InvalidInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4346322581772139027L;

	public InvalidInputException() {
		super();
	}
	
	public InvalidInputException(String message) {
		super(message);
	}
}
