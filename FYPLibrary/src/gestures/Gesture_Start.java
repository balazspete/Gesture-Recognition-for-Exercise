package gestures;

public class Gesture_Start implements Gesture {

	public static final boolean[] ENABLED_AXES = new boolean[]{ false, true, false };
	
	public static final double[][] GESTURE_MODEL = new double[][] {
		{ -1.616161616161616, -6.187500000000001, 21.616161616161605, 0.11565420560747669, 0.1, 100 },
		{ -8.080808080808083, -2.1656249999999995, -2.8282828282828234, -2.2982142857142898, -1.0101010101010122, -9.899999999999979 },
		{ -1.0101010101010104, -14.849999999999996, -0.6060606060606091, -14.849999999999927, -2.222222222222218, -4.500000000000009 }
	};
	

	protected final int minimumBaseValue = 0;
	protected final int minimumNoReactionTime = 500;
	protected final int maximumReactionTime = 10000;
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
