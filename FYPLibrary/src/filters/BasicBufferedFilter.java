package filters;

import java.util.Vector;

import coordinates.Coordinate;
import exceptions.InvalidInputException;

/**
 * A BasicBufferedFilter using a low pas method
 * @author Balazs Pete
 *
 */
public class BasicBufferedFilter implements Filter {
	
	Vector<Coordinate> buffer = new Vector<Coordinate>();
	
	private int bufferSize = 3;
	private Filter other = null;
	
	/**
	 * Create a new instance of BasicLowPassFilter
	 * @param filterSize The number of neighbors to consider -1
	 * @throws InvalidInputException An exception thrown when the input is an even number
	 */
	public BasicBufferedFilter(int sampleSize) throws InvalidInputException { 
		if(sampleSize%2==0) throw new InvalidInputException("Input must be an odd number");
		this.bufferSize = sampleSize;
	}
	
	public BasicBufferedFilter(int sampleSize, Filter other) { 
		this.bufferSize = sampleSize;
	}

	@Override
	public Coordinate filter(Coordinate coordinate) {
		buffer.add(coordinate);
		
		if(buffer.size() < bufferSize) return null;
		
		int middleIndex = bufferSize/2 + 1;
		double x=0, y=0, z=0;
		for(int i = 0; i < bufferSize; i++) {
			if(i == middleIndex) continue;
			Coordinate c = buffer.get(i);
			x += c.getX();
			y += c.getY();
			z += c.getZ();
		}
		
		buffer.remove(middleIndex);
		buffer.add(middleIndex, new Coordinate(
				x / (bufferSize-1),
				y / (bufferSize-1),
				z / (bufferSize-1)));
		
		Coordinate c = buffer.remove(0);
		if(other != null) c = other.filter(c);
		return c;
	}
	
}
