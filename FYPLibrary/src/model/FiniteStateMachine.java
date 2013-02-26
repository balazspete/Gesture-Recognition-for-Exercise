package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

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
	
	private static final int DEFAULT_COORDINATE_REPOSITORY_SIZE = 50;
	
	private List<AcceptingStateListener> listeners 
		= new ArrayList<AcceptingStateListener>();

	private List<FuzzyState> states;
	private final int groundState = 0;
	private int currentState = groundState;
	
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
		this.states = GestureRepresentation.create(
				gesture.getGestureModel(), 
				gesture.getEnabledAxes(), 
				gesture.getAxisCheckOrder(), 
				gesture.getAllowedAdditionalError(),
				gesture.areAxesIndependent());
		this.minimumNoReactionTime = gesture.getMinimumNoReactionTime();
		this.maximumReactionTime = gesture.getMaximumReactionTime();
		this.gesture = gesture;
		System.out.println(Arrays.toString(states.toArray()));
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
		reset(System.currentTimeMillis());
	}
	
	/**
	 * Reset the FSM to its base state
	 * @param lastEventTime Specify lastEventTime
	 */
	public void reset(long lastEventTime) {
		currentState = groundState;
		coordinates.empty();
		this.lastEventTime = lastEventTime;
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
		long time = System.currentTimeMillis();
		
		System.out.println("################ - "+coordinates.size() +" " + coordinates.maxSize());
		// Don't match if minimum waiting time has not passed yet
		if(currentState == groundState && time-lastEventTime <= minimumNoReactionTime) return;
		// Reset FSM if gesture has timed out
		if(time-lastEventTime > maximumReactionTime) reset(time-minimumNoReactionTime);
		//if(currentState!=0 && currentState == states.size()) return;

		System.out.println(currentState);
		
		if(currentState > groundState) {
			matchHelper(states.get(currentState), coordinates.getLast(), coordinate);
		} else {
			coordinates.store(coordinate);
			for(int i = coordinates.size() -1; i >= groundState; i--) {
				if(matchHelper(states.get(currentState), coordinates.get(i), coordinate)) return;
			}
		}
	}
	
	/**
	 * A helper method for {@link #match() match() function}
	 * @param inRepo Coordinate in the repository
	 * @param coordinate The coordinate
	 * @return The match result
	 */
	private boolean matchHelper(FuzzyState state, Coordinate inRepo, Coordinate coordinate) {
		DisplacementVector vector = 
				DisplacementVector.getVector(inRepo, coordinate);
		boolean result = state.match(vector);
		
		if(result) {
			goToNextState();
		}
		
		return result;
	}
	
	/**
	 * Go to the next state of the FSM
	 */
	private void goToNextState() {
		currentState++;
		if(currentState==groundState+1) lastEventTime = System.currentTimeMillis();
		if(currentState == states.size()) fireEvent();
		
		
		System.out.println("Increased state: " + currentState + "/" + states.size());
	}
}
