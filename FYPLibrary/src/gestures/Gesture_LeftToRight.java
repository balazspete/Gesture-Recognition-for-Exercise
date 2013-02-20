package gestures;

public class Gesture_LeftToRight implements Gesture {

	public final boolean[] ENABLED_AXES = new boolean[]{ true, true, false };
	
	public final double[][] GESTURE_MODEL = new double[][] {
		{ -5.592105263157897, -1.5199999999999994, 0.3289473684210549, 25.839999999999822, /*0.3289473684210513*/0, 30.40000000000012 },
		{ -5.921052631578945, -1.6888888888888896, -0.8223684210526443, -13.375999999999793, 4.605263157894736, 2.605714285714286 },
		{ -5.592105263157894, -1.9670588235294122, -1.973684210526315, -6.8400000000000025, 6.743421052631579, 2.8175609756097564 },
		{ -3.6184210526315805, -2.7636363636363623, -2.796052631578931, -4.64941176470591, 12.171052631578952, 1.8075675675675669 }
	};

	protected final int minimumBaseValue = 0;
	protected final int minimumNoReactionTime = 0;
	protected final int maximumReactionTime = 50000;
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
	public int getMinimumBaseValue() {
		return minimumBaseValue;
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
		return 0;
	}
}
