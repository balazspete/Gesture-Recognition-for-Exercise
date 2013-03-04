package gestures;

/**
 * An ExerciseGesture class defining a Bench Press 
 * @author Balazs Pete
 *
 */
public class Gesture_BenchPress extends ExerciseGesture {

	private final String GESTURE_NAME = "Bech Press";
	
	public final boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	protected final double allowedAdditionalError = 0.3;
	protected final boolean independentAxes = false;

	public final double[][] GESTURE_MODEL = new double[][] {
		{ -3.819444444444443, 	-27.1453090909091, 	27.08333333333333, 		2.339446153846154, 		0.3472222222222179, 	398.131200000005, 	20 },
		{ 1.9097222222222214,	57.30676363636366, 	-21.006944444444457, 	-3.290340495867767, 	1.2152777777777821, 	104.27245714285677 }
	};
	
	protected final boolean[] ANALYSIS_AXES = new boolean[]{ false, true, false };
	
	protected final int minimumNoReactionTime = 500;
	protected final int maximumReactionTime = 4000;
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
