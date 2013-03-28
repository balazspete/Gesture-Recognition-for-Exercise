package analysis;

import java.util.Arrays;

import ui.monitor.analysis.AnalysisDisplay;

import events.event.AcceptingStateEvent;
import gestures.ExerciseGesture;

/**
 * A manager class to handle the analysis of input gesture patterns
 * @author Balazs Pete
 *
 */
public class AnalysisManager extends Thread {
	
	private AnalysisDisplay analysisDisplay;
	
	/**
	 * Create a new instance of AnalysisManager
	 */
	public AnalysisManager(AnalysisDisplay analysisDisplay) {
		this.analysisDisplay = analysisDisplay;
	}
	
	/**
	 * Handle an AcceptingStateEvent
	 * @param e The event to handle
	 */
	public void handleAcceptingStateEvent(AcceptingStateEvent e) {
		if(e.getGesture() instanceof ExerciseGesture) {
			GestureStats gsts = GestureStats.create(e);
			gsts.setGesture(e.getGesture());
			
			analysisDisplay.addGestureStats(gsts);
		}
	}
	
}
