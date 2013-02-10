package gestures;

import java.util.List;
import java.util.LinkedList;

import model.state.FuzzyNumber;
import model.state.FuzzyState;
import model.state.PseudoState;

/**
 * A class defining the skeleton of a Gesture
 * @author Balazs Pete
 *
 */
public abstract class Gesture {

	/**
	 * The Gesture model created by the GestureCreator
	 */
	public static double[][] GESTURE_MODEL = new double[][] {};
	
	/**
	 * Create a Gesture representation of FuzzyStates from a Gesture model
	 * @param model The gesture model
	 * @param axes The boolean array defining which axes are enabled (0: x-axis, 1: y-axis, 2: z-axis)
	 * @return The FuzzyState representation
	 */
	public static List<FuzzyState> createRepresentation(double[][] model, boolean[] axes) {
		LinkedList<FuzzyState> states = new LinkedList<FuzzyState>();
		
		for(double[] row : model) {
			states.add(new FuzzyState(new PseudoState(
					axes[0] ? new FuzzyNumber(row[0], row[1]) : null,
					axes[1] ? new FuzzyNumber(row[2], row[3]) : null,
					axes[2] ? new FuzzyNumber(row[4], row[5]) : null)));
		}
		
		return states;
	}
}
