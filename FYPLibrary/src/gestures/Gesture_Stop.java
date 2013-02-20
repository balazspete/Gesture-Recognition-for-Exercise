package gestures;

public class Gesture_Stop implements Gesture {

	public static final boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	
	public static final double[][] GESTURE_MODEL = new double[][] {
		{ -0.9685230024213065, 	-3.097500000000003, 	2.1791767554479406,	1.3766666666666674,		-1.6949152542372863, 	-1.7700000000000014 },
		{ -2.6634382566585946, 	-1.5018181818181822, 	9.68523002421307, 	0.6711250000000004, 	-0.4842615012106508, 	-6.195000000000036 }
		//{ -2.421307506053269, 	-2.6844999999999994, 	12.348668280871664,	0.8907843137254907, 	-1.6949152542372907, 	-2.654999999999996 }
		//{ -1.4527845036319604, 	-6.883333333333337, 	3.874092009685242,	3.3556249999999896, 	-1.9370460048426157, 	-3.6137499999999987 }
	};

	protected final int minimumBaseValue = 0;
	protected final int minimumNoReactionTime = 400;
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
