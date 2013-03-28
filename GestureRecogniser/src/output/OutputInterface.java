package output;

import gestures.Gesture;

/**
 * An interface describing the general functionality of an output of the GestureRecogniser
 * @author Balazs Pete
 *
 */
public interface OutputInterface {

	/**
	 * Handle a new gesture
	 * @param gesture The gesture to be output
	 * @param time The time of the gesture
	 */
	public void handleGesture(Gesture gesture, long time);
	
}
