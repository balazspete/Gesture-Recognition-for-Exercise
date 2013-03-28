package model.state;

/**
 * A class to describe a number with an error range
 * @author Balazs pete
 *
 */
public class FuzzyNumber {

	private double value;
	private double error;
	
	/**
	 * Create an instance of a FuzzyNumber, with no error range
	 * @param value the value of the number
	 */
	public FuzzyNumber(double value) {
		this.value = value;
		this.error = 0;
	}
	
	/**
	 * Create an instance of a FuzzyNumber
	 * @param value the value of the number
	 * @param error the error of the number
	 */
	public FuzzyNumber(double value, double error) {
		this.value= value;
		this.error = error;
	}
	
	/**
	 * Get the value of the nuber
	 * @return the value (double)
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * Get the error of the number
	 * @return the error (double)
	 */
	public double getError() {
		return error;
	}
	
	/**
	 * Method to determine whether a value is within the range of the instance
	 * @param number the value to be compared
	 * @return true if within range, false otherwise
	 */
	public boolean isVithin(double number) {
		return isVithin(number, 0);
	}
	
	/**
	 * Method to determine whether another FuzzyNumber is within the range of the instance
	 * @param number the value to be compared
	 * @return true if within range, false otherwise
	 */
	public boolean isVithin(FuzzyNumber number) {
		return isVithin(number.getValue(), number.getError());
	}
	
	/**
	 * Method to determine whether a number&error combination is within the range of the instance
	 * @param number the value to be compared
	 * @param error the error range of the input value
	 * @return true if within range, false otherwise
	 */
	private boolean isVithin(double v, double e) {
		double err = error + e;
		if(value - err <= v && v <= value + err) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method to get the sum of the current instance and another FuzzyNumber
	 * @param other the other instance
	 * @return resulting FuzzyNumber
	 */
	public FuzzyNumber add(FuzzyNumber other) {
		return new FuzzyNumber(value + other.getValue(), error + other.getError());
	}
	
	/**
	 * Method to get the difference between the current instance and another FuzzyNumber
	 * @param other the other instance
	 * @return resulting FuzzyNumber
	 */
	public FuzzyNumber subtract(FuzzyNumber other) {
		return new FuzzyNumber(value - other.getValue(), error + other.getError());
	}
	
	/**
	 * Get a String representation of the object
	 * @return the string representation
	 */
	public String toString() {
		return "" + value + ", " + error;
	}
	
}
