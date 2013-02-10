package recogniser;

import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import coordinates.Coordinate;
import events.event.AcceptingStateEvent;
import events.listeners.AcceptingStateListener;

import gestures.Gesture;
import model.FiniteStateMachine;

/**
 * An object to manage all FiniteStateMachines of the gesture recognizer
 * @author Balazs Pete
 *
 */
public class FiniteStateMachineManager extends Thread {

	private HashMap<FiniteStateMachine, Gesture> managees = new HashMap<FiniteStateMachine, Gesture>();
	private Queue<Coordinate> coordinateQueue = new ConcurrentLinkedQueue<Coordinate>(); 
	
	/**
	 * Create an instance of a FiniteStateMachineManager
	 */
	public FiniteStateMachineManager() {
		
	}
	
	/**
	 * Add a FiniteStateMachine to the manager, with its corresponding Gesture
	 * @param fsm The FiniteStateMachine
	 * @param gesture The corresponding Gesture
	 */
	public void add(FiniteStateMachine fsm, Gesture gesture) {
		fsm.addEventListener(new AcceptingStateListener() {
			@Override
			public void handleAcceptingState(AcceptingStateEvent e) {
				processAcceptingStateEvent(e);
			}
		});
		managees.put(fsm, gesture);
	}
	
	/**
	 * Add a coordinate to the manager to be processed by the FiniteStateMachines
	 * @param coordinate The coordinate to be added
	 */
	public synchronized void input(Coordinate coordinate) {
		coordinateQueue.add(coordinate);
		run();
	}
	
	/**
	 * Method to clear the Coordinate queue of the manager
	 */
	public void clear() {
		coordinateQueue.clear();
	}
	
	/**
	 * Method to reset all managed FiniteStateMachinesto their base states, 
	 * and clear the coordinates queue of the manager
	 */
	public void reset() {
		interrupt();
		clear();
		for(FiniteStateMachine fsm : managees.keySet()) {
			fsm.reset();
		}
	}
	
	/**
	 * Add an AcceptinStateListener to all FiniteStateMachines
	 * @param listener The AcceptinStateListener to add to the FSMs.
	 */
	public void addAcceptingStateListener(AcceptingStateListener listener) {
		for(FiniteStateMachine fsm : managees.keySet()) {
			fsm.addEventListener(listener);
		}
	}
	
	@Override
	public void run() {
		Coordinate coordinate = coordinateQueue.poll();
		for(FiniteStateMachine fsm : managees.keySet()) {
			fsm.input(coordinate);
		}
	}
	
	/**
	 * Method to handle an AcceptingStateEvent
	 * @param e The AcceptingStateEvent to be handled
	 */
	private void processAcceptingStateEvent(AcceptingStateEvent e) {
		Gesture g = managees.get((FiniteStateMachine) e.getSource());
		reset();
		
		System.err.println(g.getClass().getName());
	}
}
