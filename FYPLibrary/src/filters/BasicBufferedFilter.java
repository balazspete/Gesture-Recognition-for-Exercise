package filters;

import java.util.Vector;

import coordinates.Coordinate;

public class BasicBufferedFilter implements Filter<Coordinate> {
	
	Vector<Coordinate> buffer = new Vector<Coordinate>();
	
	public BasicBufferedFilter() {
		
	}

	@Override
	public Coordinate filter(Coordinate input) {
		buffer.add(input);
		
		Coordinate coordinate = null;
		if(buffer.size() > 2) {
			coordinate = buffer.remove(0);
			Coordinate temp = getMedian(coordinate, buffer.get(1));
			
			buffer.remove(0);
			buffer.add(0, temp);
		}
		
		return coordinate;
	}
	
	private Coordinate getMedian(Coordinate c1, Coordinate c2) {
		return new Coordinate(
				(c1.getX() + c2.getX())/2,
				(c1.getY() + c2.getY())/2,
				(c1.getZ() + c2.getZ())/2);
	}
	
}
