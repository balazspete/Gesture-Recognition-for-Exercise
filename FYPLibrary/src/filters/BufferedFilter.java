package filters;

import java.util.Vector;

import coordinates.Coordinate;

/**
 * A class defining the basic characteristics of a buffered filter
 * @author Balazs Pete
 *
 */
public abstract class BufferedFilter implements Filter {

	Vector<Coordinate> buffer = new Vector<Coordinate>();
	
	protected int bufferSize = 3;
	protected Filter other = null;

	/**
	 * Get the buffer of the filter
	 * @return The Vector containing the Coordinates contained in the buffer
	 */
	public Vector<Coordinate> getBuffer() {
		return buffer;
	}
	
}
