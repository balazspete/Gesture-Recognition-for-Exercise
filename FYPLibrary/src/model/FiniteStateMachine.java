package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import coordinates.Coordinate;
import coordinates.CoordinateRepository;
import coordinates.DisplacementVector;
import events.event.AcceptingStateEvent;
import events.listeners.AcceptingStateListener;
import gestures.Gesture;
import gestures.GestureRepresentation;
import gestures.Gesture_RightToLeft;

import model.state.FuzzyState;
import model.state.PseudoState;

/**
 * A class to describe a Finite State MAchine for gesture recognition
 * @author Balazs Pete
 *
 */
public class FiniteStateMachine {
	
	private static final int DEFAULT_COORDINATE_REPOSITORY_SIZE = 100;
	
	private List<AcceptingStateListener> listeners 
		= new ArrayList<AcceptingStateListener>();

	private List<FuzzyState> states;
	private int currentState = 0;
	
	private CoordinateRepository coordinates = 
			new CoordinateRepository(DEFAULT_COORDINATE_REPOSITORY_SIZE);
	
	private Gesture gesture;
	
	private long lastEventTime = 0;
	private int minimumNoReactionTime = 1000;
	private int maximumReactionTime = 5000;
	
	/**
	 * Create an instance of a FiniteStateMachine
	 * @param states The list of states to be used by this instance
	 */
	public FiniteStateMachine(Gesture gesture) {
		this.states = GestureRepresentation.create(gesture.getGestureModel(), gesture.getEnabledAxes(), gesture.getAxisCheckOrder());
		this.states.get(0).setMinimumBase(gesture.getMinimumBaseValue());
		this.minimumNoReactionTime = gesture.getMinimumNoReactionTime();
		this.maximumReactionTime = gesture.getMaximumReactionTime();
		this.gesture = gesture;
		//states.add(0, new FuzzyState(new PseudoState(null, null, null)));
	}
	
	/**
	 * Add an input to the FiniteStateMachine 
	 * @param coordinate The input as a Coordinate
	 */
	public synchronized void input(Coordinate coordinate) {
		match(coordinate);
	}
	
	/**
	 * Reset the FSM to its base state
	 */
	public void reset() {
		currentState = 0;
		coordinates.empty();
		lastEventTime = System.currentTimeMillis();
	}
	
	/**
	 * Create a list of FuzzyStates from a list of PseudoStates
	 * @param states The list of PseudoStates to convert
	 * @return The list of FuzzyStates
	 */
	public static List<FuzzyState> createFuzzyStatesFromPseudoStates(List<PseudoState> states) {
		List<FuzzyState> newStates = new LinkedList<FuzzyState>(); 
		
		for(PseudoState state : states) {
			newStates.add(new FuzzyState(state));
		}
		
		return newStates;
	}
	
	/**
	 * Add an InputListener to the interface
	 * @param listener Listener to be added
	 */
	public synchronized void addEventListener(AcceptingStateListener listener)  {
		listeners.add(listener);
	}
	
	/**
	 * Remove a listener from the interface
	 * @param listener Listener to be removed
	 */
	public synchronized void removeEventListener(AcceptingStateListener listener)   {
		listeners.remove(listener);
	}
	
	/**
	 * Get the associated gesture
	 * @return The Gesture
	 */
	public Gesture getGesture() {
		return gesture;
	}

	/**
	 * Method to be called to notify all listeners
	 */
	protected synchronized void fireEvent() {
		lastEventTime = System.currentTimeMillis();
	    AcceptingStateEvent event = new AcceptingStateEvent(this);
	    for(AcceptingStateListener l : listeners) {
	    	l.handleAcceptingState(event);
	    }
	}
	
	/**
	 * Match the DisplacementVectors between the input coordinate and the 
	 * Coordinates in the repository to the next state 
	 * @param coordinate The coordinate to match
	 */
	private synchronized void match(Coordinate coordinate) {
		if(currentState != 0 && System.currentTimeMillis()-lastEventTime <= minimumNoReactionTime) return;
		if(System.currentTimeMillis()-lastEventTime > maximumReactionTime) reset();
		System.out.println(currentState-1);
		if(currentState!=0 && currentState == states.size()) return;
		int size = coordinates.size();
		
		if(size == 0) {
			coordinates.add(coordinate);
		} else if(currentState > 0) {
			matchHelper(coordinate);
		} else {
			// Case for the initial state
			for(int i = coordinates.size() -1; i >= 0; i--) {
				if(matchHelper(coordinate)) return;
			}
			
		}
	}
	
	/**
	 * A helper method for {@link #match() match() function}
	 * @param coordinate The coordinate
	 * @return The match result
	 */
	private boolean matchHelper(Coordinate coordinate) {
		DisplacementVector vector = 
				DisplacementVector.getVector(coordinates.getLast(), coordinate);
		boolean result = states.get(currentState).match(vector);
		
		if(result) {
			goToNextState();
		} else {
			if(currentState == 0) lastEventTime = System.currentTimeMillis();
		}
		
		return result;
	}
	
	/**
	 * Go to the next state of the FSM
	 */
	private void goToNextState() {
		currentState++;
		if(currentState==1) lastEventTime = System.currentTimeMillis();
		if(currentState == states.size()) fireEvent();
		System.out.println("Increased state: " + currentState + "/" + states.size());
	}
}
