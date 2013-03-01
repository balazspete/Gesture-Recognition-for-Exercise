package gestures;

public class Gesture_Start extends Gesture {

	public static final boolean[] ENABLED_AXES = new boolean[]{ false, true, false };
	
	public static final double[][] GESTURE_MODEL = new double[][] {
		{ 2.3529411764705888, 	21.674999999999994, 	8.235294117647058, 		11.147142857142859, 	1.9607843137254903, 	31.211999999999996, 	50 },
		{ -9.215686274509803, 	-5.534042553191489, 	23.725490196078425, 	3.4393388429752076, 	-1.764705882352942, 	-31.789999999999985 },
		{ -3.333333333333333, 	-19.89, 				-1.5686274509803866, 	-35.76375000000013, 	-2.1568627450980387, 	-28.37454545454546 }

	};
	
	protected final int minimumNoReactionTime = 500;
	protected final int maximumReactionTime = 5000;
	protected final boolean checkOrder = true;
	
	protected final double allowedAdditionalError = 0.3;
	protected final boolean independentAxes = false;

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
		return independentAxes;
	}
}
