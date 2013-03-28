package recogniser;

import java.util.LinkedList;
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
public class FiniteStateMachineManager {

	private LinkedList<FiniteStateMachine> managees = new LinkedList<FiniteStateMachine>();
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
	public void add(FiniteStateMachine fsm) {
		fsm.addEventListener(new AcceptingStateListener() {
			@Override
			public void handleAcceptingState(AcceptingStateEvent e) {
				processAcceptingStateEvent(e);
			}
		});
		managees.add(fsm);
	}
	
	/**
	 * Add a coordinate to the manager to be processed by the FiniteStateMachines
	 * @param coordinate The coordinate to be added
	 */
	public synchronized void input(Coordinate coordinate) {
		coordinateQueue.add(coordinate);
		(new Thread(new Runnable(){
			@Override
			public void run() {
				Coordinate coordinate = getFirstCoordinate();
				for(FiniteStateMachine fsm : managees) {
					try {
						fsm.input(coordinate);
					} catch(IndexOutOfBoundsException e) {
						System.err.println("Out of bounds exception - " + fsm.getGesture().getClass().toString());
					} catch(NullPointerException e) {
						System.err.println("Null pointer exception - " + fsm.getGesture().getClass().toString());
					}
				}
			}
		})).start();
	}
	
	/**
	 * Retrieve the first element in the coordinate queue
	 * @return The first Coordinate
	 */
	private synchronized Coordinate getFirstCoordinate() {
		return coordinateQueue.poll();
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
		clear();
		for(FiniteStateMachine fsm : managees) {
			fsm.reset();
		}
	}
	
	/**
	 * Add an AcceptinStateListener to all FiniteStateMachines
	 * @param listener The AcceptinStateListener to add to the FSMs.
	 */
	public void addAcceptingStateListener(AcceptingStateListener listener) {
		for(FiniteStateMachine fsm : managees) {
			fsm.addEventListener(listener);
		}
	}
	
	/**
	 * Method to handle an AcceptingStateEvent
	 * @param e The AcceptingStateEvent to be handled
	 */
	private void processAcceptingStateEvent(AcceptingStateEvent e) {
		Gesture g = e.getGesture();
//		reset();
		
		System.err.println(g.getClass().getName());
	}
}
