package gestures;

/**
 * An ExerciseGesture class describing an Isometric Hold
 * @author Balazs Pete
 *
 */
public class Gesture_IsometricHold extends ExerciseGesture {

	protected final boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	protected final double allowedAdditionalError = 0.3;
	protected final boolean independentAxes = false;

	protected final double[][] GESTURE_MODEL = new double[][] {
		{ -7.308970099667775, 	-11.531036363636362, 	35.04983388704319, 	2.576331753554502, 		-7.641196013289036, 	-14.181026086956521, 	40 },
		{ -0.16611295681063165, -507.3655999999987, 	-6.478405315614623, -11.150892307692299, 	-0.8305647840531574, 	-86.97695999999986 },
		{ 7.807308970099668, 	10.023940425531915, 	-27.90697674418604, -3.235750000000001, 	7.9734219269103, 		15.855175, 				12 }
	};
	
	protected final boolean[] ANALYSIS_AXES = new boolean[]{ false, true, false };
	
	protected final int minimumNoReactionTime = 1000;
	protected final int maximumReactionTime = 60000;
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
}
