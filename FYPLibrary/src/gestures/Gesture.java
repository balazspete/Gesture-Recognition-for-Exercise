package gestures;

import java.util.List;
import java.util.LinkedList;

import model.state.FuzzyNumber;
import model.state.FuzzyState;
import model.state.PseudoState;

/**
 * An interface defining the skeleton of a Gesture
 * @author Balazs Pete
 *
 */
public abstract class Gesture {

	public static final int AXES = 3;
	
	/**
	 * Get the model describing the gesture
	 * @return The model
	 */
	public abstract double[][] getGestureModel();
	
	/**
	 * Get the list of boolean values determining whether an axis is considered
	 * @return The boolean array (0: x-axis, 1: y-axis, 2: z-axis)
	 */
	public abstract boolean[] getEnabledAxes();
	
	/**
	 * Get the minimum time in milliseconds that needs to be passed before allowing the recognition of the gesture
	 * @return The value
	 */
	public abstract int getMinimumNoReactionTime();
	
	/**
	 * Get the maximum time in milliseconds allowed before the FSM needs to be reset
	 * @return The value
	 */
	public abstract int getMaximumReactionTime();
	
	/**
	 * Get the order in which the axes should be checked
	 * @return True if check order is { x, y, z }, false if check order is { z, y, x }
	 */
	public abstract boolean getAxisCheckOrder();
	
	/**
	 * Get the allowed additional error of the gesture
	 * @return The allowed additional error [0:1]
	 */
	public abstract double getAllowedAdditionalError();
	
	/**
	 * Determine whether the axes are to be matched independently of each other
	 * @return true if axes are independent, false otherwise
	 */
	public abstract boolean areAxesIndependent();
	
}
