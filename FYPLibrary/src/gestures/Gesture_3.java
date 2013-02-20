package gestures;

public class Gesture_3 implements Gesture{
	
	public boolean[] ENABLED_AXES = new boolean[]{ false, false, true };
	
	public final double[][] GESTURE_MODEL = new double[][] {
		{ -1.4705882352941178, -6.119999999999999, 0.9191176470588118, 7.616000000000098, 4.779411764705884, 3.766153846153845 },
		{ -0.1838235294117645, -48.96000000000006, -9.191176470588232, -1.1968000000000005, 1.102941176470587, 18.133333333333354 },
		{ 1.102941176470587, 9.973333333333345, -4.779411764705884, -3.766153846153845, 0, 100 },
		{ 0.7352941176470598, 14.959999999999981, -11.029411764705884, -1.9946666666666664, 0.5514705882352935, 32.640000000000036 }, 
		{ 1.102941176470587, 9.973333333333345, -2.941176470588232, -7.480000000000008, -3.3088235294117645, -4.835555555555556 }
	};
	
	protected final int minimumBaseValue = 0;
	protected final int minimumNoReactionTime = 1000;
	protected final int maximumReactionTime = 5000;
	protected final boolean checkOrder = true;
	
	protected final double allowedAdditionalError = 0.5;
	
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
		return allowedAdditionalError;
	}
}
