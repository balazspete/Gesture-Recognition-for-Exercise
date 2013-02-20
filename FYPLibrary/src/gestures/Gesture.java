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
public interface Gesture {

	/**
	 * Get the model describing the gesture
	 * @return The model
	 */
	public double[][] getGestureModel();
	
	/**
	 * Get the list of boolean values determining whether an axis is considered
	 * @return The boolean array (0: x-axis, 1: y-axis, 2: z-axis)
	 */
	public boolean[] getEnabledAxes();
	
	/**
	 * Get the minimum value of the base
	 * @return The minimum value
	 */
	public int getMinimumBaseValue();
	
	/**
	 * Get the minimum time in milliseconds that needs to be passed before allowing the recognition of the gesture
	 * @return The value
	 */
	public int getMinimumNoReactionTime();
	
	/**
	 * Get the maximum time in milliseconds allowed before the FSM needs to be reset
	 * @return The value
	 */
	public int getMaximumReactionTime();
	
	/**
	 * Get the order in which the axes should be checked
	 * @return True if check order is { x, y, z }, false if check order is { z, y, x }
	 */
	public boolean getAxisCheckOrder();
	
	/**
	 * Get the allowed additional error of the gesture
	 * @return The allowed additional error [0:1]
	 */
	public double getAllowedAdditionalError();
	
}
