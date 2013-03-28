package gestures;

/**
 * A Gesture describing a motion indicating "Stop"
 * @author Balazs Pete
 *
 */
public class Gesture_Stop extends Gesture {

	private final String GESTURE_NAME = "Stop";
	
	public static final boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	
	public static final double[][] GESTURE_MODEL = new double[][] {
		{ -3.2755298651252405, -22.1826705882353, 16.184971098265905, 4.489349999999998, -1.5414258188824679, -60.60622499999994 },
		{ -0.5780346820809257, -161.61659999999978, 8.863198458574189, 11.711347826086948, -0.7707129094412313, -107.74440000000027 } 
	};

	protected final int minimumNoReactionTime = 400;
	protected final int maximumReactionTime = 5000;
	protected final boolean checkOrder = true;
	
	protected final double allowedAdditionalError = 0.4;
	protected final boolean independentAxes = false;

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
	public String toString() {
		return GESTURE_NAME;
	}
}
