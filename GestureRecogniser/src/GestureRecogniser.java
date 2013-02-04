import java.util.List;

import events.event.CoordinateEvent;
import events.listeners.CoordinateListener;
import model.FiniteStateMachine;
import model.state.FuzzyState;
import recogniser.FiniteStateMachineManager;
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
	
	/****************************************/
	
	private InputManager inputManager;
	FiniteStateMachineManager fsmm = new FiniteStateMachineManager();
	
	
	public GestureRecogniser() {
		
	}
	
	public void start() {
		// Add FiniteStateMachines to the FSM manager
		fsmm.add(new FiniteStateMachine(
				Gesture.createRepresentation(Gesture_LeftToRight.GESTURE_MODEL)), 
				new Gesture_LeftToRight());
		
		// Change InputInterface depending on input requirements
		InputInterface input = new FileInput(null);
		inputManager = new InputManager(input); 
		inputManager.addEventListener(new CoordinateListener() {
			@Override
			public void handleCoordinate(CoordinateEvent e) {
				fsmm.input(e.getCoordinate());
			}
		});
		
		
	}
	

}
