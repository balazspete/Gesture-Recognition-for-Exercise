package gestures;

/**
 * A Gesture to describe a Right-to-Left motion by the user
 * @author Balazs Pete
 *
 */
public class Gesture_RightToLeft extends Gesture {

	protected final String GESTURE_NAME = "Right-To-Left";
	
	protected final boolean[] ENABLED_AXES = new boolean[]{ true, false, true };
	
	protected final double[][] GESTURE_MODEL = new double[][] {
		{ 12.896825396825397, 5.861907692307692, -15.277777777777771, -9.896727272727276, -21.825396825396822, -6.927709090909091 },
		{ 12.896825396825397, 5.861907692307692, -15.277777777777771, -9.896727272727276, 0, -6.927709090909091 }
	};
	
	protected final int minimumNoReactionTime = 2500;
	protected final int maximumReactionTime = 5000;
	protected final boolean checkOrder = true;
	
	private double allowedAdditionalError = 0.2;
	private boolean areAxesIndependent = false;

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
