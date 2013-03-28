package events.event;

import gestures.Gesture;

import java.util.EventObject;
import java.util.List;

import coordinates.Coordinate;
import coordinates.CoordinateSeries;

import model.FiniteStateMachine;

/**
 * A class to describe an event generated when a FiniteStateMachine has reached an accepting state
 * @author Balazs Pete
 *
 */
public class AcceptingStateEvent extends EventObject {

	/**
	 * Automatically generated serialVersionUID
	 */
	private static final long serialVersionUID = -3796844397739483225L;
	
	protected Gesture gesture;
	protected CoordinateSeries stateCoordinates;
	
	protected long time = -1;

	/**
	 * Create a new instance of the AcceptingStateEvent object
	 * @param source The source of the event
	 */
	public AcceptingStateEvent(FiniteStateMachine source) {
		super(source);
		gesture = source.getGesture();
		
		List<Coordinate> coordinates = source.getStateCoordinates();
		stateCoordinates = new CoordinateSeries(coordinates);
		time = coordinates.get(coordinates.size()-1).getTime();
	}
	
	/**
	 * Get the gesture associated with the event
	 * @return The gesture causing the event
	 */
	public Gesture getGesture() {
		return gesture;
	}
	
	/**
	 * Get the list of state coordinates
	 * @return the List of Coordinates
	 */
	public CoordinateSeries getStateCoordinates() {
		return stateCoordinates;
	}
	
	/**
	 * Get the time at which the event was created at
	 * @return
	 */
	public long getTime() {
		return time;
	}
}
