package gestures;

/**
 * A Gesture to describe a One drawn by the user
 * @author Balazs Pete
 *
 */
public class Gesture_1 extends Gesture {
	
	private final String GESTURE_NAME = "One";

	public final boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	
	public final double[][] GESTURE_MODEL = new double[][] {
//		{ -6.185567010309278, -0.9700000000000001, 18.144329896907223, 0.4684659090909089, 0, 0 },
//		{ 2.8865979381443303, 3.291071428571428, -30.721649484536087, -0.471979865771812, 0, 0 }
		{ -6.470588235294117, -7.881818181818183, 22.15686274509804, 2.7621238938053096, 13.529411764705882, 4.146521739130435 },
		{ 3.9215686274509807, 23.409, -20.15686274509803, -3.013353658536586, 0.5882352941176467, 147.39000000000007, 150 }
	};
	
	protected final int minimumNoReactionTime = 1000;
	protected final int maximumReactionTime = 3000;
	protected final boolean checkOrder = true;
	
	protected final double allowedAdditionalError = 0.2;
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
