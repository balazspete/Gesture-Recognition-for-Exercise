package filters;

import java.util.Vector;

import coordinates.Coordinate;
import exceptions.InvalidInputException;

/**
 * A BasicBufferedFilter using a low pas method
 * @author Balazs Pete
 *
 */
public class BasicBufferedFilter extends BufferedFilter {
	
	/**
	 * Create a new instance of BasicBufferedFilter
	 * @param filterSize The number of neighbors to consider -1
	 */
	public BasicBufferedFilter(int sampleSize) { 
		this.bufferSize = sampleSize;
	}
	
	/**
	 * Create a new instance of BasicBufferedFilter with a specified preprocessor
	 * @param filterSize The number of neighbors to consider -1
	 * @param other The preprocessor filter
	 */
	public BasicBufferedFilter(int sampleSize, Filter other) { 
		this.bufferSize = sampleSize;
	}

	@Override
	public Coordinate filter(Coordinate coordinate) {
		if(other != null) coordinate = other.filter(coordinate);
		
		buffer.add(coordinate);
		if(buffer.size() < bufferSize) return null;

		int middleIndex = bufferSize/2 + 1;
		Coordinate c = getAverageOfNeighbours(middleIndex);
		buffer.remove(middleIndex);
		buffer.add(middleIndex, c);
		
		return buffer.remove(0);
	}
	
	/**
	 * A method to alter the coordinate to be the average of its neighbors
	 * @param toFilter The index of the Coordinate to be filtered
	 * @return The new Coordinate
	 */
	protected Coordinate getAverageOfNeighbours(int toFilter) {
		double x=0, y=0, z=0;
		
		for(int i = 0; i < bufferSize; i++) {
			if(i == toFilter) continue;
			Coordinate c = buffer.get(i);
			x += c.getX();
			y += c.getY();
			z += c.getZ();
		}
		
		return new Coordinate(
				x / (bufferSize-1),
				y / (bufferSize-1),
				z / (bufferSize-1));
	}
}
