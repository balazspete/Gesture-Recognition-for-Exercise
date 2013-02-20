package gestures;

public class Gesture_1 implements Gesture {

	public final boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	
	public final double[][] GESTURE_MODEL = new double[][] {
		{ -6.185567010309278, -0.9700000000000001, 18.144329896907223, 0.4684659090909089, 0, 0 },
		{ 2.8865979381443303, 3.291071428571428, -30.721649484536087, -0.471979865771812, 0, 0 }
	};
	
	protected final int minimumBaseValue = 100;
	protected final int minimumNoReactionTime = 1000;
	protected final int maximumReactionTime = 3000;
	protected final boolean checkOrder = false;
	
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
	public boolean getAxisCheckOrder() {
		return checkOrder;
	}
	

	@Override
	public double getAllowedAdditionalError() {
		return 0;
	}
}
