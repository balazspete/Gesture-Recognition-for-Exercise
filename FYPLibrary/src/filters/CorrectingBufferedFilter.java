package filters;

import coordinates.Coordinate;

public class CorrectingBufferedFilter extends BasicBufferedFilter {

	/**
	 * Create a new instance of CorrectingBufferedFilter
	 * @param filterSize The number of neighbors to consider -1
	 */
	public CorrectingBufferedFilter(int sampleSize) {
		super(sampleSize);
	}
	
	/**
	 * Create a new instance of CorrectingBufferedFilter with a specified preprocessor
	 * @param filterSize The number of neighbors to consider -1
	 * @param other The preprocessor filter
	 */
	public CorrectingBufferedFilter(int sampleSize, Filter filter) {
		super(sampleSize, filter);
	}

	@Override
	public Coordinate filter(Coordinate coordinate) {
		if(other != null) coordinate = other.filter(coordinate);
		
		buffer.add(coordinate);
		if(buffer.size() <= bufferSize) return null;
		
		correctCurrentLastCoordinate();

		int middleIndex = bufferSize/2 + 1;
		Coordinate c = getAverageOfNeighbours(middleIndex);
		buffer.remove(middleIndex);
		buffer.add(middleIndex, c);
		
		return buffer.remove(0);
	}
	
	/**
	 * Correct the current last (used) coordinate in the buffer (second last element in real)
	 * 
	 * If the middle coordinate lies outside of the two bounding coordinates (start and end) 
	 * then set the middle coordinate value to be the average of the start and end values
	 */
	private void correctCurrentLastCoordinate() {
		int index = buffer.size()-1;
		double[] end = buffer.get(index--).getAll();
		double[] middle = buffer.get(index--).getAll();
		double[] start = buffer.get(index).getAll();
		
		for(int i = 0; i <= 2; i++) {
			boolean cond1 = start[i] < middle[i];
			boolean cond2 = middle[i] < end[i];
			
			if(end[i]-start[i]<0) {
				cond1 = !cond1;
				cond2 = !cond2;
			}
			
			if(!(cond1 && cond2)) middle[i]=(end[i]-start[i])/2 + start[i];
		}
		
		index = buffer.size()-2;
		buffer.remove(index);
		buffer.add(index, new Coordinate(middle[0], middle[1], middle[2]));
	}
	
}
