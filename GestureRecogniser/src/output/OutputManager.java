package output;

import events.event.AcceptingStateEvent;

/**
 * A class to manage the output of the GestureRecogniser
 * @author Balazs Pete
 *
 */
public class OutputManager {

	private OutputInterface output;
	
	/**
	 * Create a new instance of OutputManager
	 * @param output The output to be used
	 */
	public OutputManager(OutputInterface output) {
		this.output = output;
	}
	
	public void handleAcceptingStateEvent(AcceptingStateEvent e) {
		output.handleGesture(e.getGesture(), e.getTime());
	}
	
	
}
