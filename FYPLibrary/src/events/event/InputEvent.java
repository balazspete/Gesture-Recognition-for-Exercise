package events.event;

import java.util.EventObject;

import coordinates.Coordinate;

/**
 * A class defining an input event
 * @author Balazs Pete
 *
 */
public class InputEvent extends EventObject {
	
	/**
	 * Automatically generated serialVersionUID
	 */
	private static final long serialVersionUID = -9072352045217730184L;
	
	private Coordinate coordinate;

	/**
	 * Create a new instance of an InputEvent
	 * @param source the source of the input event
	 */
	public InputEvent(Object source) {
		super(source);
	}
	
	/**
	 * Create a new instance of an InputEvent with a Coordinate
	 * @param source the source of the input event
	 * @param the Coordinate created with the event
	 */
	public InputEvent(Object source, Coordinate coordinate) {
		super(source);
		this.coordinate = coordinate;
	}
	
	/**
	 * Get the coordinate of the event
	 * @return the Coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}
}
