
import java.util.Arrays;

import analysis.AnalysisManager;

import events.event.AcceptingStateEvent;
import events.event.CoordinateEvent;
import events.listeners.AcceptingStateListener;
import events.listeners.CoordinateListener;
import filters.CorrectingBufferedFilter;
import filters.Filter;
import filters.SimpleKalmanFilter;
import model.FiniteStateMachine;
import recogniser.FiniteStateMachineManager;
import ui.monitor.input.InputMonitor;
import gestures.*;
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
	private AnalysisManager analysisManager;
	
	/**
	 * Create a new instance of GestureRecogniser
	 */
	public GestureRecogniser() { }
	
	/**
	 * Start the execution of the instance
	 */
	public void start() {
		// Add FiniteStateMachines to the FSM manager
		addGestures();
		
		// Change InputInterface depending on input requirements
		InputInterface input = new FileInput(null);
		
		// Change Filter to filter type required
		Filter filter = new CorrectingBufferedFilter(7);
		Filter filter1 = new SimpleKalmanFilter(filter);
		
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
		
		analysisManager = new AnalysisManager();
		fsmm.addAcceptingStateListener(new AcceptingStateListener() {
			@Override
			public void handleAcceptingState(AcceptingStateEvent e) {
				analysisManager.handleAcceptingStateEvent(e);
			}
		});
		
	}
	
	private void addGestures() {
//		// LeftToRight
//		fsmm.add(new FiniteStateMachine(new Gesture_LeftToRight()));
//		
//		// PASSED
//		// RightToLeft
//		fsmm.add(new FiniteStateMachine(new Gesture_RightToLeft()));
//		
//		// PASSED
//		// Upwards
//		fsmm.add(new FiniteStateMachine(new Gesture_Upwards()));
//		
//		// PASSED
//		// Stop
//		fsmm.add(new FiniteStateMachine(new Gesture_Stop()));
//
//		// PASSED
//		// Start
//		fsmm.add(new FiniteStateMachine(new Gesture_Start()));
//				
//		// --
//		// Wave
//		fsmm.add(new FiniteStateMachine(new Gesture_Wave()));
//		
//		// PASSED
//		// 1
//		fsmm.add(new FiniteStateMachine(new Gesture_1()));
//		
//		// PASSED
//		// 2
//		fsmm.add(new FiniteStateMachine(new Gesture_2()));
//		
//		// PASSED, kinda
//		// 3
//		fsmm.add(new FiniteStateMachine(new Gesture_3()));
//		
//		// PASSED, kinda
//		// Circle
//		fsmm.add(new FiniteStateMachine(new Gesture_Circle()));
//		
//		// PASSED
//		// Downwards
//		fsmm.add(new FiniteStateMachine(new Gesture_Downwards()));
//		
//		// Vertical Jump
//		fsmm.add(new FiniteStateMachine(new Gesture_VerticalJump()));
//		
//		// PASSED
//		// Squat
//		fsmm.add(new FiniteStateMachine(new Gesture_Squat()));
//		
//		// PASSED
//		// Lateral Raise
//		fsmm.add(new FiniteStateMachine(new Gesture_LateralRaise()));
//		
//		// PASSED
//		// Bicep Curl
//		fsmm.add(new FiniteStateMachine(new Gesture_BicepCurl()));
//		
//		// PASSED
//		// Overhead Press
//		fsmm.add(new FiniteStateMachine(new Gesture_OverheadPress()));
//		
		// PASSED
		// Punching
		fsmm.add(new FiniteStateMachine(new Gesture_Punching()));
//
//		// PASSED
//		// Isometric Hold
//		fsmm.add(new FiniteStateMachine(new Gesture_IsometricHold()));
	}

}
