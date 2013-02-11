
import java.util.Vector;

import coordinates.Coordinate;
import events.event.AcceptingStateEvent;
import events.event.CoordinateEvent;
import events.listeners.AcceptingStateListener;
import events.listeners.CoordinateListener;
import exceptions.InvalidInputException;
import filters.BasicBufferedFilter;
import filters.Filter;
import filters.SimpleKalmanFilter;
import model.FiniteStateMachine;
import recogniser.FiniteStateMachineManager;
import ui.monitor.input.InputMonitor;
import gestures.Gesture;
import gestures.Gesture_LeftToRight;
import input.FileInput;
import input.InputInterface;
import input.InputManager;

/**
 * The main class of the gestureRecogniser project
 * @author Balazs Pete
 *
 */
public class GestureRecogniser {

	/**
	 * Run the application
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestureRecogniser gr = new GestureRecogniser();
		gr.start();
	}
	
	private FiniteStateMachineManager fsmm = new FiniteStateMachineManager();
	private InputManager inputManager;
	private InputMonitor inputMonitor;
	
	/**
	 * Create a new instance of GestureRecogniser
	 */
	public GestureRecogniser() { }
	
	/**
	 * Start the execution of the instance
	 */
	public void start() {
		// Add FiniteStateMachines to the FSM manager
		fsmm.add(new FiniteStateMachine(
				Gesture.createRepresentation(Gesture_LeftToRight.GESTURE_MODEL, 
						Gesture_LeftToRight.ENABLED_AXES)), 
				new Gesture_LeftToRight());
		
		// Change InputInterface depending on input requirements
		InputInterface input = new FileInput(null);
		
		// Change Filter to filter type required
		Filter filter = new SimpleKalmanFilter();
		Filter filter1 = new BasicBufferedFilter(9, filter);
		
		// Set up the input manager
		inputManager = new InputManager(input, filter1);
		inputManager.addEventListener(new CoordinateListener() {
			@Override
			public void handleCoordinate(CoordinateEvent e) {
				fsmm.input(e.getCoordinate());
			}
		});
		
		// Set up the input monitor
		inputMonitor = new InputMonitor(inputManager);
		fsmm.addAcceptingStateListener(new AcceptingStateListener() {
			@Override
			public void handleAcceptingState(AcceptingStateEvent e) {
				inputMonitor.handleAcceptingState(e);
			}
		});
		
		
	}
	

}
