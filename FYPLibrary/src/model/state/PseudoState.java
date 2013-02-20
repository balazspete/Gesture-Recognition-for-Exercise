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
	 * Calculate a PseudoState representing a Vector |point2 - point1|
	 * @param point1 
	 * @param point2
	 * @return the resulting PseudoState
	 */
	public static PseudoState getDifference(FuzzyPoint point1, FuzzyPoint point2) {
		return getDifference(point1, point2, 100);
	}
	
	/**
	 * Calculate a PseudoState representing a Vector |point2 - point1| within a given scale
	 * @param point1 
	 * @param point2
	 * @param The scale of the calculation
	 * @return the resulting PseudoState
	 */
	public static PseudoState getDifference(FuzzyPoint point1, FuzzyPoint point2, double scale) {
		FuzzyNumber x, y, z;
		try {
			x = getDifferenceHelper(point1.getX(), point2.getX(), scale);
		} catch (InvalidDimensionException e) {
			x = null;
		}
		
		try {
			y = getDifferenceHelper(point1.getY(), point2.getY(), scale);
		} catch (InvalidDimensionException e) {
			y = null;
		}
		
		try {
			z = getDifferenceHelper(point1.getZ(), point2.getZ(), scale);
		} catch (InvalidDimensionException e) {
			z = null;
		}
		
//		try {
//			FuzzyNumber p1 = point1.getX(), p2 = point2.getX();
//			double xMin = (p2.getValue() - p2.getError()/2) - (p1.getValue() + p1.getError()/2);
//			double xMax = (p2.getValue() + p2.getError()/2) - (p1.getValue() - p1.getError()/2);
//			double diff = (xMax - xMin)/2;
//			double value = xMin + diff;
//			
//			x = new FuzzyNumber(value, diff/value);
//		} catch (InvalidDimensionException e) {
//			x = null;
//		}
//		
//		try {
//			FuzzyNumber p1 = point1.getY(), p2 = point2.getY();
//			double yMin = (p2.getValue() - p2.getError()/2) - (p1.getValue() + p1.getError()/2);
//			double yMax = (p2.getValue() + p2.getError()/2) - (p1.getValue() - p1.getError()/2);
//			double diff = (yMax - yMin)/2;
//			double value = yMin + diff;
//			
//			y = new FuzzyNumber(value, diff/value);
//		} catch (InvalidDimensionException e) {
//			y = null;
//		}
//		
//		try {
//			FuzzyNumber p1 = point1.getZ(), p2 = point2.getZ();
//			double zMin = (p2.getValue() - p2.getError()/2) - (p1.getValue() + p1.getError()/2);
//			double zMax = (p2.getValue() + p2.getError()/2) - (p1.getValue() - p1.getError()/2);
//			double diff = (zMax - zMin)/2;
//			double value = zMin + diff;
//			
//			z = new FuzzyNumber(value, diff/value);
//		} catch (InvalidDimensionException e) {
//			z = null;
//		}
		
		return new PseudoState(x, y, z);
	}
	
	/**
	 * A Method to help calculate the difference between two FuzzyNumbers
	 * @param a Fuzzy number 1
	 * @param b Fuzzy number 2
	 * @param scale
	 * @return
	 */
	private static FuzzyNumber getDifferenceHelper(FuzzyNumber a, FuzzyNumber b, double scale) {
		double value = b.getValue() - a.getValue();
		double errorValue = ((a.getError() + b.getError()) / 100) * scale;
		return new FuzzyNumber(value, errorValue / value);
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
