package gestures;

/**
 * An ExerciseGesture class describing a Deadlift
 * @author Balazs Pete
 *
 */
public class Gesture_Deadlift extends ExerciseGesture {

	public final boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	protected final double allowedAdditionalError = 0.3;
	protected final boolean independentAxes = false;

	public final double[][] GESTURE_MODEL = new double[][] {
		{ -2.1739130434782616, 	-59.24799999999998, 	40.06211180124224, 	1.7682542635658915, 	-2.7950310559006226, -50.68995555555553, 30 },
		{ 2.1739130434782616, 	53.32319999999998, 		-35.40372670807453, -2.7285263157894737, 	0.31055900621118226, 497.6831999999966 }
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
	
}
