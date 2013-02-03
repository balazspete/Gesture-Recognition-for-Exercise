package exceptions;

/**
 * An exception to be thrown if a String representation is invalid (does not represent Object, or corrupt)
 * @author Balazs Pete
 *
 */
public class InvalidStringRepresentationException extends Exception {

	/**
	 * Create a new instance of the exception
	 */
	public InvalidStringRepresentationException() {
		super("Invalid String representation exception...");
	}
	
	/**
	 * Create a new instance of the exception with input representation
	 * @param representation The representation which has caused the exception to be thrown
	 */
	public InvalidStringRepresentationException(String representation) {
		super("Invalid String representation exception: The String \"" + representation + "\" is invalid.");
	}
}
