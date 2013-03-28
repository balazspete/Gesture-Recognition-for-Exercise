package coordinates;

/**
 * A class to represent a displacement in space
 * @author Balazs Pete
 *
 */
public class DisplacementVector extends Coordinate {

	/**
	 * Create an instance of the class with specified values
	 * @param x x-displacement
	 * @param y y-displacement
	 * @param z z-displacement
	 */
	public DisplacementVector(double x, double y, double z) {
		super(x, y, z);
	}

	/**
	 * Get the displacement vector between the two Coordinates (to - from)
	 * @param from Point from which vector starts
	 * @param to Point to which vector points
	 * @return the DisplacementVector
	 */
	public static DisplacementVector getVector(Coordinate from, Coordinate to) {
		return new DisplacementVector(
				to.getX() - from.getX(),
				to.getY() - from.getY(),
				to.getZ() - from.getZ());
	}
}
