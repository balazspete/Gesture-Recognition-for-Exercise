package gestures;

/**
 * A gesture to describe a Left-to-Right motion by the user
 * @author Balazs Pete
 *
 */
public class Gesture_LeftToRight extends Gesture {

	private final String GESTURE_NAME = "Left-To_right";
	
	public final boolean[] ENABLED_AXES = new boolean[]{ true, true, true };
	
	public final double[][] GESTURE_MODEL = new double[][] {
		{ -10.7421875, 	-1.2101818181818182, 	-1.3671875, 	-17.554285714285715, 	0.9765625, 	16.384 ,				20 },
		{ -9.9609375, 	-1.8070588235294118, 	-6.640625, 		-5.270588235294118, 	13.046875, 	0.8677966101694915, 	40 }
	};

	protected final int minimumNoReactionTime = 2500;
	protected final int maximumReactionTime = 5000;
	protected final boolean checkOrder = true;
	
	protected double allowedAdditionalError = 0.4;
	protected boolean areAxesIndependent = false;

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
		return areAxesIndependent;
	}
	
	@Override
	public String toString() {
		return GESTURE_NAME;
	}
}
