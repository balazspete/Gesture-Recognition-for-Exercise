package gestures;

/**
 * An ExerciseGesture describing a Punching gesture
 * @author Balazs Pete
 *
 */
public class Gesture_Punching extends ExerciseGesture {
	
	public final boolean[] ENABLED_AXES = new boolean[]{ true, false, true };
	protected final double allowedAdditionalError = 0.3;
	protected final boolean independentAxes = false;

	public final double[][] GESTURE_MODEL = new double[][] {
		{ -5.0434782608695645, 	-13.681034482758623, 	6.956521739130423, 		10.745312500000018, 	-5.739130434782606, 	-19.03598484848486, 	40 },
		{ -2.086956521739131, 	-46.83854166666666, 	-0.6956521739130466, 	-148.78124999999935, 	-6.086956521739131, 	-22.67142857142857 },
		{ 1.3913043478260878, 	78.52343749999996, 		-0.5217391304347814, 	-198.37500000000048, 	5.913043478260869, 		19.448529411764707 },
		{ 6.2608695652173925, 	16.531249999999996, 	-5.043478260869563, 	-20.52155172413794, 	4.869565217391308, 		21.25446428571427 }
	};
	
	protected final boolean[] ANALYSIS_AXES = new boolean[]{ true, false, true };
	
	protected final int minimumNoReactionTime = 500;
	protected final int maximumReactionTime = 4000;
	protected final boolean checkOrder = false;

	@Override
	public boolean getAxisCheckOrder() {
		return checkOrder;
	}
	
	@Override
	public double[][] getGestureModel() {
		return GESTURE_MODEL;
	}

	@Override
	public boolean[] getEnabledAxes() {
		return ENABLED_AXES;
	}

	@Override
	public int getMinimumNoReactionTime() {
		return minimumNoReactionTime;
	}

	@Override
	public int getMaximumReactionTime() {
		return maximumReactionTime;
	}

	@Override
	public double getAllowedAdditionalError() {
		return allowedAdditionalError;
	}

	@Override
	public boolean areAxesIndependent() {
		return independentAxes;
	}

	@Override
	public boolean[] getAnalysisAxes() {
		// TODO Auto-generated method stub
		return null;
	}

}
