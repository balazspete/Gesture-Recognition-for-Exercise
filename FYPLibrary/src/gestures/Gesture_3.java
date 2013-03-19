package gestures;

/**
 * A Gesture to describe a three drawn by the user
 * @author Balazs Pete
 *
 */
public class Gesture_3 extends Gesture {
	
	private final String GESTURE_NAME = "Three";
	
	public boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	
	public final double[][] GESTURE_MODEL = new double[][] {
			{ -3.2496307237813884, -16.66650909090909, -5.022156573116689, -16.17631764705883, 6.942392909896602, 15.60268936170213 },
			{ 1.1816838995568686, 45.8329, -6.942392909896611, -15.60268936170211, -0.7385524372230421, -128.33212000000015 },
			{ 2.806499261447562, 19.298063157894745, -13.589364844903983, -7.472755434782611, -2.0679468242245207, -49.10667857142855 }

	};
	
	protected final int minimumNoReactionTime = 900;
	protected final int maximumReactionTime = 5000;
	protected final boolean checkOrder = false;
	
	protected final double allowedAdditionalError = 0.5;
	protected final boolean independentAxes = false;
	
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
	public boolean getAxisCheckOrder() {
		return checkOrder;
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
