package model.state;

import exceptions.InvalidDimensionException;

public class PseudoState extends FuzzyPoint {
	
	public PseudoState(FuzzyNumber x, FuzzyNumber y, FuzzyNumber z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String toString() {
		return (x == null ? ", " : x.toString()) + ", " + (y == null ? "," : y.toString()) + ", " + (z==null ? "," : z.toString());
	}
	
	/**
	 * Calculate a FuzzyState representing a Vector |point2 - point1|
	 * @param point1
	 * @param point2
	 * @return
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
	
}
