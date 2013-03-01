package analysis;

import events.event.AcceptingStateEvent;
import gestures.ExerciseGesture;

/**
 * A manager class to handle the analysis of input gesture patterns
 * @author Balazs Pete
 *
 */
public class AnalysisManager extends Thread {

	/**
	 * Create a new instance of AnalysisManager
	 */
	public AnalysisManager() {
		
	}
	
	public void handleAcceptingStateEvent(AcceptingStateEvent e) {
		if(e.getGesture() instanceof ExerciseGesture) {
			System.err.println("Analysis: exercise");
		}
	}
	
	
}
