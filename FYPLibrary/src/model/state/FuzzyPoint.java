package model.state;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidDimensionException;

/**
 * A class to describe a coordinate with an error range
 * @author Balazs Pete
 *
 */
public class FuzzyPoint {

	protected FuzzyNumber x = null, y = null, z = null;
	protected double horizontalAlignment = 0;
	
	/**
	 * Create an instance of a FuzzyPoint
	 */
	public FuzzyPoint() {
	}
	
	/**
	 * Create an instance of a Fuzzy Point with specified values
	 * @param x x-value
	 * @param y y-value
	 * @param z z-value
	 */
	public FuzzyPoint(FuzzyNumber x, FuzzyNumber y, FuzzyNumber z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Method to get the X value of the point
	 * @return the x-value
	 * @throws InvalidDimensionException if value is not set
	 */
	public FuzzyNumber getX() throws InvalidDimensionException {
		if(x == null) throw new InvalidDimensionException();
		return x;
	}

	/**
	 * Method to set the X value of the point
	 * @param x the x-axis value
	 */
	public void setX(FuzzyNumber x) {
		this.x = x;
	}
	
	/**
	 * Method to get the Y value of the point
	 * @return the y-value
	 * @throws InvalidDimensionException if value is not set
	 */
	public FuzzyNumber getY() throws InvalidDimensionException {
		if(y == null) throw new InvalidDimensionException();
		return y;
	}

	/**
	 * Method to set the Y value of the point
	 * @param x the y-axis value
	 */
	public void setY(FuzzyNumber y) {
		this.y = y;
	}

	/**
	 * Method to get the Z value of the point
	 * @return the z-value
	 * @throws InvalidDimensionException if value is not set
	 */
	public FuzzyNumber getZ() throws InvalidDimensionException {
		if(z == null) throw new InvalidDimensionException();
		return z;
	}

	/**
	 * Method to set the Z value of the point
	 * @param x the z-axis value
	 */
	public void setZ(FuzzyNumber z) {
		this.z = z;
	}
	
	/**
	 * Method to get all eligible axis values (if an axis is not defined, it will be omitted)
	 * @return the values of the point
	 */
	public List<FuzzyNumber> getValues() {
		List<FuzzyNumber> l = new ArrayList<FuzzyNumber>();
		if(x != null) l.add(x);
		if(y != null) l.add(y);
		if(z != null) l.add(z);
		
		return l;
	}

	/**
	 * Method to get the horizontal alignment of the point ()used by GraphingCanvas
	 * @return the horizontal alignment
	 */
	public double getHorizontalAlignment() {
		return horizontalAlignment;
	}

	/**
	 * Method to set the horizontal alignment of the point
	 * @param horizontalAlignment the new horizontal alignment
	 */
	public void setHorizontalAlignment(double horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}
	
}
