package model.state;

import exceptions.InvalidDimensionException;
import exceptions.InvalidStringRepresentationException;

/**
 * A class to imitate a FuzzyState
 * @author Balazs Pete
 *
 */
public class PseudoState extends FuzzyPoint {
	
	/**
	 * Create an instance of PseudoState
	 * @param x x-value
	 * @param y y-value
	 * @param z z-value
	 */
	public PseudoState(FuzzyNumber x, FuzzyNumber y, FuzzyNumber z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Create an instance of PseudoState from its string representation
	 * @param stringRepresentation the string representation
	 * @throws InvalidStringRepresentationException Thrown if string representation is not correct
	 */
	public PseudoState(String stringRepresentation) throws InvalidStringRepresentationException {
		fromString(stringRepresentation);
	}
	
	/**
	 * Get a String representation of the object
	 */
	public String toString() {
		return (x == null ? ", " : x.toString()) + ", " + (y == null ? "," : y.toString()) + ", " + (z==null ? "," : z.toString());
	}
	
	/**
	 * Calculate a FuzzyState representing a Vector |point2 - point1|
	 * @param point1 
	 * @param point2
	 * @return the resulting PseudoState
	 */
	public static PseudoState getDifference(FuzzyPoint point1, FuzzyPoint point2) {
		FuzzyNumber x, y, z;
		try {
			x = point2.getX().subtract(point1.getX());
		} catch (InvalidDimensionException e) {
			x = null;
		}
		
		try {
			y = point2.getY().subtract(point1.getY());
		} catch (InvalidDimensionException e) {
			y = null;
		}
		
		try {
			z = point2.getZ().subtract(point1.getZ());
		} catch (InvalidDimensionException e) {
			z = null;
		}
		
		return new PseudoState(x, y, z);
	}

	/**
	 * Parse the string and populate instance variables 
	 * @param strRep The String representation
	 * @throws InvalidStringRepresentationException Thrown if string representation is not correct
	 */
	private void fromString(String strRep) throws InvalidStringRepresentationException {
		String[] rep = strRep.split(",");
		
		if(rep.length != 6) throw new InvalidStringRepresentationException(strRep);
		
		this.x = getFuzzyNumber(rep, 0);
		this.y = getFuzzyNumber(rep, 2);
		this.z = getFuzzyNumber(rep, 4);
	}
	
	/**
	 * Method to get a FuzzyNumber
	 * @param rep The split String representation
	 * @param i The index of the value
	 * @return The creates FuzzyNumber
	 */
	private FuzzyNumber getFuzzyNumber(String[] rep, int i) {
		if(i > rep.length - 1 || i < 0) return null;
		return this.x = rep[i].equals("") ? null : new FuzzyNumber(Double.parseDouble(rep[i]), Double.parseDouble(rep[i + 1]));
	}
}
