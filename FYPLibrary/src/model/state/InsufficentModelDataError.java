package model.state;

public class InsufficentModelDataError extends Exception {
	public InsufficentModelDataError(int size) {
		super("Data collection of size " + size + " is insufficient to create a model");
	}
}
