package coordinates;

/**
 * A class to represent a three dimensional point
 * @author Balazs Pete
 *
 */
public class Coordinate {

	private double x, y, z;
	private long time;
	
	/**
	 * Create an instance of the Coordinate class
	 * @param x x-value
	 * @param y y-value
	 * @param z z-value
	 */
	public Coordinate(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z= z;
		
		this.time = System.currentTimeMillis();
	}

	/**
	 * Get the X coordinate
	 * @return The x-coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get the Y coordinate
	 * @return The y-coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Get the Z coordinate
	 * @return The z-coordinate
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * Get all Coordinates
	 * @return An array containing all coordinates  in the form: { x, y, z }
	 */
	public double[] getAll() {
		return new double[] { x, y, z };
	}
	
	/**
	 * Get all coordinates in reverse order
	 * @return An array containing all coordinates  in the form: { z, y, x }
	 */
	public double[] getAllInReverse() {
		return new double[] { z, y, x };
	}
	
	/**
	 * Get a rounded value to 2 decimal places for the X coordinate
	 * @return rounded x value
	 */
	public double getRoundX() {
		return round(x);
	}

	/**
	 * Get a rounded value to 2 decimal places for the Y coordinate
	 * @return rounded y value
	 */
	public double getRoundY() {
		return round(y);
	}

	/**
	 * Get a rounded value to 2 decimal places for the Z coordinate
	 * @return rounded z value
	 */
	public double getRoundZ() {
		return round(z);
	}
	
	/**
	 * Get a String representation of the coordinate
	 * @return String representation in the form of "x y z"
	 */
	public String toString(){
		return "" + x + "  " + y + "  " + z + "";
	}
	
	/**
	 * Get the UNIX time at which the Coordinate was created
	 * @return The creation time of the Coordinate (UNIX time-stamp)
	 */
	public long getTime() {
		return time;
	}
	
	/**
	 * Method to round a number to two decimal places
	 * @param d input number
	 * @return rounded number
	 */
	private double round(double d) {
		return (int) Math.round(d * 100) / 100.0;
	}
}
