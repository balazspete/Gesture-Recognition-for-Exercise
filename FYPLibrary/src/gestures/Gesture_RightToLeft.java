package gestures;

public class Gesture_RightToLeft implements Gesture {

	protected final boolean[] ENABLED_AXES = new boolean[]{ true, true, false };
	
	protected final double[][] GESTURE_MODEL = new double[][] {
		{ 1.4319809069212397, 	4.190000000000004, 		3.8186157517899773, 	1.3093749999999995, 	0, 	0 },
		{ 3.5799522673031037, 	2.0949999999999993, 	3.1026252983293574, 	1.9338461538461527, 	0, 	0 },
		{ 3.8186157517899764, 	2.0949999999999998, 	0.4773269689737418, 	7.807500000000193, 		0, 	0 }
	};
	
	protected final int minimumBaseValue = 0;
	protected final int minimumNoReactionTime = 0;
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
