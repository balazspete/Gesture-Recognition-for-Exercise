package gestures;

import java.util.LinkedList;
import java.util.List;

import model.state.FuzzyNumber;
import model.state.FuzzyState;
import model.state.PseudoState;

/**
 * An abstract class holding utility methods to create a representation of gestures using FuzzyStates
 * @author Balazs Pete
 *
 */
public abstract class GestureRepresentation {

	/**
	 * Create a Gesture representation of FuzzyStates from a Gesture model
	 * @param model The gesture model
	 * @param axes The boolean array defining which axes are enabled (0: x-axis, 1: y-axis, 2: z-axis)
	 * @return The FuzzyState representation
	 */
	public static List<FuzzyState> create(double[][] model, boolean[] axes) {
		return create(model, axes, true);
	}
	
	/**
	 * Create a Gesture representation of FuzzyStates from a Gesture model
	 * @param model The gesture model
	 * @param axes The boolean array defining which axes are enabled (0: x-axis, 1: y-axis, 2: z-axis)
	 * @param checkOrder The check order of states (true if order is { x, y, z }, false if order is { z, y, x })
	 * @return The FuzzyState representation
	 */
	public static List<FuzzyState> create(double[][] model, boolean[] axes, boolean checkOrder) {
		return create(model, axes, checkOrder, 0, false);
	}
	
	/**
	 * Create a Gesture representation of FuzzyStates from a Gesture model
	 * @param model The gesture model
	 * @param axes The boolean array defining which axes are enabled (0: x-axis, 1: y-axis, 2: z-axis)
	 * @param checkOrder The check order of states (true if order is { x, y, z }, false if order is { z, y, x })
	 * @param additionalAllowedError The percentage of value that is allowed as additional error [0.0:1.0]
	 * @param independentAxes True if axes are to be checked independently of each other, false otherwise
	 * @return The FuzzyState representation
	 */
	public static List<FuzzyState> create(double[][] model, boolean[] axes, boolean checkOrder, double additionalAllowedError, boolean independentAxes) {
		LinkedList<FuzzyState> states = new LinkedList<FuzzyState>();
		
		for(double[] row : model) {
			FuzzyState s = new FuzzyState(new PseudoState(
					axes[0] ? new FuzzyNumber(row[0], row[1]) : null,
					axes[1] ? new FuzzyNumber(row[2], row[3]) : null,
					axes[2] ? new FuzzyNumber(row[4], row[5]) : null));
			try {
				s.setMinimumBase(row[6]);
			} catch (Exception e) {
				s.setMinimumBase(0);
			}
			s.setAllowedAdditionalError(additionalAllowedError);
			s.setIndependentAxes(independentAxes);
			s.setAxisCheckDirection(checkOrder);
			states.add(s);
		}
		
		return states;
	}
}
