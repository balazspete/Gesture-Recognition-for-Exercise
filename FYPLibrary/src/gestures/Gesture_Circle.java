package gestures;

public class Gesture_Circle implements Gesture {
	public boolean[] ENABLED_AXES = new boolean[]{ true, true, true };
	
	public final double[][] GESTURE_MODEL = new double[][] {
		//{ 3.1578947368421053, 18.952499999999997, 5.112781954887211, 14.307279411764727, -6.31578947368421, -11.582083333333335 },
		{ -5.112781954887219, -14.307279411764704, 9.172932330827066, 12.324303278688527, 7.518796992481202, 14.151200000000003 },
		{ 1.9548872180451138, 27.21384615384614, -13.082706766917283, -8.132873563218398, 10.7518796992481214, 123.82299999999984 }
	};
	
	protected final int minimumNoReactionTime = 0;
	protected final int maximumReactionTime = 5000;
	protected final boolean checkOrder = true;
	
	protected final double allowedAdditionalError = 0.3;
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
}