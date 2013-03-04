
import analysis.AnalysisManager;

import events.event.AcceptingStateEvent;
import events.event.CoordinateEvent;
import events.listeners.AcceptingStateListener;
import events.listeners.CoordinateListener;
import filters.CorrectingBufferedFilter;
import filters.Filter;
import filters.NoiseSimulatorFilter;
import filters.SimpleKalmanFilter;
import model.FiniteStateMachine;
import recogniser.FiniteStateMachineManager;
import ui.monitor.analysis.AnalysisDisplay;
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
		Filter noiseFilter = new NoiseSimulatorFilter(1, 500000);
		
		Filter correctingFilter = new CorrectingBufferedFilter(7, noiseFilter);
		Filter simpleKalmanFilter = new SimpleKalmanFilter(correctingFilter);
		
		// filter is passed into system components...
		Filter filter = simpleKalmanFilter;
		
		// Set up the input manager
		inputManager = new InputManager(input, filter);
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
		
		AnalysisDisplay adp = new AnalysisDisplay();
		adp.setVisible(true);
		analysisManager = new AnalysisManager(adp);
		fsmm.addAcceptingStateListener(new AcceptingStateListener() {
			@Override
			public void handleAcceptingState(AcceptingStateEvent e) {
				analysisManager.handleAcceptingStateEvent(e);
			}
		});
	}
	
	private void addGestures() {
		// LeftToRight
		fsmm.add(new FiniteStateMachine(new Gesture_LeftToRight()));
		
//		// RightToLeft
//		fsmm.add(new FiniteStateMachine(new Gesture_RightToLeft()));
		
		// Upwards
		fsmm.add(new FiniteStateMachine(new Gesture_Upwards()));
		
		// Stop
		fsmm.add(new FiniteStateMachine(new Gesture_Stop()));

		// Start
		fsmm.add(new FiniteStateMachine(new Gesture_Start()));
				
//		// FAIL
//		// Wave
//		fsmm.add(new FiniteStateMachine(new Gesture_Wave()));
		
		// 1
		fsmm.add(new FiniteStateMachine(new Gesture_1()));
		
		// 2
		fsmm.add(new FiniteStateMachine(new Gesture_2()));
		
		// 3
		fsmm.add(new FiniteStateMachine(new Gesture_3()));
		
		// Circle
		fsmm.add(new FiniteStateMachine(new Gesture_Circle()));
		
		// Downwards
		fsmm.add(new FiniteStateMachine(new Gesture_Downwards()));
		
//		// Vertical Jump
//		fsmm.add(new FiniteStateMachine(new Gesture_VerticalJump()));
//		
//		// Squat
//		fsmm.add(new FiniteStateMachine(new Gesture_Squat()));
//		
//		// Lateral Raise
//		fsmm.add(new FiniteStateMachine(new Gesture_LateralRaise()));
//		
//		// Bicep Curl
//		fsmm.add(new FiniteStateMachine(new Gesture_BicepCurl()));
//		
//		// Overhead Press
//		fsmm.add(new FiniteStateMachine(new Gesture_OverheadPress()));
//		
//		// Punching
//		fsmm.add(new FiniteStateMachine(new Gesture_Punching()));
//
//		// Isometric Hold
//		fsmm.add(new FiniteStateMachine(new Gesture_IsometricHold()));
//		
//		// Bench Press
//		fsmm.add(new FiniteStateMachine(new Gesture_BenchPress()));
//		
//		// Deadlift
//		fsmm.add(new FiniteStateMachine(new Gesture_Deadlift()));
//		
//		// Chest Press
//		fsmm.add(new FiniteStateMachine(new Gesture_ChestPress()));
	}

}
