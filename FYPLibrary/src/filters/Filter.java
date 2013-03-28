package filters;

import coordinates.Coordinate;

/**
 * An interface to define the basic structure of a Filter
 * @author Balazs Pete
 *
 */
public interface Filter {

	/**
	 * Filter a coordinate and return 
	 * @param coordinate The coordinate to handle
	 * @return The final coordinate
	 */
	public Coordinate filter(Coordinate coordinate);
	
	
}
