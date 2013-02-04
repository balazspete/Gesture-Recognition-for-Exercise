package events.event;

import coordinates.Coordinate;

public class CoordinateEvent extends InputEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8731760814551005000L;

	public CoordinateEvent(Object source, Coordinate coordinate) {
		super(source, coordinate);
	}

}
