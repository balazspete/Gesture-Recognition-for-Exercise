package gestures;

public class Gesture_Upwards implements Gesture {

	public static final boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	
	public static final double[][] GESTURE_MODEL = new double[][] {
		{ -2.0179372197309418, 	-3.2211111111111115, 	17.883408071748875, 	1.7250943396226421, 1.5695067264573996, 2.5485714285714276 },
		{ 0.44843049327354123, 	25.645000000000078, 	8.295964125560545, 		1.386216216216215, 	2.2421524663677115, 1.7840000000000011 }
	};
	
	protected final int minimumBaseValue = 50;
	protected final int minimumNoReactionTime = 500;
	protected final int maximumReactionTime = 5000;	
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
