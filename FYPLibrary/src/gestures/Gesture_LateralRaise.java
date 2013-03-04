package gestures;

/**
 * An ExerciseGesture class describing a Lateral Raise
 * @author Balazs Pete
 *
 */
public class Gesture_LateralRaise extends ExerciseGesture {
	
	private final String GESTURE_NAME = "Lateral Raise";
	
	public final boolean[] ENABLED_AXES = new boolean[]{ true, true, false };
	protected final double allowedAdditionalError = 0.3;
	protected final boolean independentAxes = false;

	public final double[][] GESTURE_MODEL = new double[][] {
		{ -10.03717472118959, -8.040111111111113, 38.66171003717472, 1.2524019230769232, -3.9033457249070658, -24.8094857142857, 20 },
		{ 9.851301115241638, 9.830173584905658, -38.47583643122676, -1.5381082125603867, 3.9033457249070658, 27.566095238095222 }
	};
	
	protected final boolean[] ANALYSIS_AXES = new boolean[]{ false, true, false };
	
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
		return ANALYSIS_AXES;
	}
	
	@Override
	public String toString() {
		return GESTURE_NAME;
	}
}
