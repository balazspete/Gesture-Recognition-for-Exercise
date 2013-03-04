package gestures;

/**
 * An ExerciseGesture to describe an Overhead Press
 * @author Balazs Pete
 *
 */
public class Gesture_OverheadPress extends ExerciseGesture {
	
	private final String GESTURE_NAME = "Overhead Press";
	
	public final boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	protected final double allowedAdditionalError = 0.3;
	protected final boolean independentAxes = false;

	public final double[][] GESTURE_MODEL = new double[][] {
		{ -3.7234042553191493, 	-13.632685714285712, 	14.184397163120565, 	3.5785800000000005, 	4.078014184397164, 		13.830260869565217 },
		{ 1.4184397163120561, 	43.73820000000002, 		8.86524822695037, 		5.7257279999999895, 	-3.368794326241133, 	-16.741894736842116 },
		{ 1.950354609929077, 	40.48494545454548, 		-20.744680851063833, 	-2.4468923076923073, 	-1.7730496453900706, 	-38.17152 }
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
