package gestures;

/**
 * An ExcerciseGesture describing a Squat
 * @author Balazs Pete
 *
 */
public class Gesture_Squat extends ExerciseGesture {
	
	private final String GESTURE_NAME = "Squat";
	
	public final boolean[] ENABLED_AXES = new boolean[]{ false, true, false };
	protected final double allowedAdditionalError = 0.3;
	protected final boolean independentAxes = false;
	
	public final double[][] GESTURE_MODEL = new double[][] {
		{ 0.0, 100, -38.1679389312977, -1.5101680000000004, 2.2900763358778615, 61.77960000000004, 20 },
		{ 1.1450381679389317, 100.67786666666663, 38.54961832061069, 2.446716831683168, 1.3816793893129784, 70.3215999999985 }
	};
	
	protected boolean[] ANALYSIS_AXES = new boolean[]{ false, true, false };
	
	protected final int minimumNoReactionTime = 100;
	protected final int maximumReactionTime = 1000;
	protected final boolean checkOrder = true;

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
		return ANALYSIS_AXES;
	}
	
	@Override
	public String toString() {
		return GESTURE_NAME;
	}
}
