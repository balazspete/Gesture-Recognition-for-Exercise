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

	/**
	 * Create a new instance of the AcceptingStateEvent object
	 * @param source The source of the event
	 */
	public AcceptingStateEvent(FiniteStateMachine source) {
		super(source);
		gesture = source.getGesture();
		stateCoordinates = new CoordinateSeries(source.getStateCoordinates());
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
}
