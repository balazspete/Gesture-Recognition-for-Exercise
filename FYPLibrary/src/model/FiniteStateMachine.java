package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import coordinates.Coordinate;
import coordinates.CoordinateRepository;
import coordinates.DisplacementVector;
import events.event.AcceptingStateEvent;
import events.listeners.AcceptingStateListener;

import model.state.FuzzyState;
import model.state.PseudoState;

/**
 * A class to describe a Finite State MAchine for gesture recognition
 * @author Balazs Pete
 *
 */
public class FiniteStateMachine {
	
	private static final int DEFAULT_COORDINATE_REPOSITORY_SIZE = 40;
	
	private List<AcceptingStateListener> listeners 
		= new ArrayList<AcceptingStateListener>();

	private List<FuzzyState> states;
	private int currentState = 0;
	
	private CoordinateRepository coordinates = 
			new CoordinateRepository(DEFAULT_COORDINATE_REPOSITORY_SIZE);
	
	/**
	 * Create an instance of a FiniteStateMachine
	 * @param states The list of states to be used by this instance
	 */
	public FiniteStateMachine(List<FuzzyState> states) {
		this.states = states;
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
	 * Method to be called to notify all listeners
	 */
	protected synchronized void fireEvent() {
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
		if(currentState!=0 && currentState == states.size()-1) return;
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
		
		if(result) goToNextState();
		
		return result;
	}
	
	/**
	 * Go to the next state of the FSM
	 */
	private void goToNextState() {
		currentState++;
		if(currentState == states.size()-1) fireEvent();
	}
}
