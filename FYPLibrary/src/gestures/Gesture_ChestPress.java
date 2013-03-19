package gestures;

/**
 * An ExerciseGesture to describe a Chest Press motion by the user
 * @author Balazs Pete
 *
 */
public class Gesture_ChestPress extends ExerciseGesture {
	
	private final String GESTURE_NAME = "ChestPress";
	
	public final boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	protected final double allowedAdditionalError = 0.3;
	protected final boolean independentAxes = false;

	public final double[][] GESTURE_MODEL = new double[][] {
		{ -0.5535055350553506, 	-195.84266666666664, 	0.0, 					100, 				34.87084870848709, 		2.7977523809523808, 	40 },
		{ 2.952029520295203, 	42.228575000000006, 	0.36900369003690514, 	381.8931999999951, 	-31.549815498154985, 	-2.92046081871345, 		20 }
	};
	
	protected final boolean[] ANALYSIS_AXES = new boolean[]{ false, false, true };
	
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
