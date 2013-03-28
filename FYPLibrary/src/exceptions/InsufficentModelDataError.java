package exceptions;

/**
 * Exception to describe an insufficient number of elements in a model
 * @author Balazs Pete
 *
 */
public class InsufficentModelDataError extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6416721351684405061L;

	/**
	 * Create a new instance of InsufficentModelDataError
	 */
	public InsufficentModelDataError() {
		super("The size of the data collection is insufficient to create a model");
	}
	
	/**
	 * Create a new instance of InsufficentModelDataError
	 * @param size the size of the collection
	 */
	public InsufficentModelDataError(int size) {
		super("Data collection of size " + size + " is insufficient to create a model");
	}
}
