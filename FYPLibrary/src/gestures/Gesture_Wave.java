package gestures;

public class Gesture_Wave implements Gesture {

	public final boolean[] ENABLED_AXES = new boolean[]{ true, false, false };
	
	public final double[][] GESTURE_MODEL = new double[][] {
		{ -17.372881355932208, -0.5180487804878048, 0, 0, 36.652542372881356, 0.2864739884393064 },
		{ 21.82203389830508, 0.8019417475728156, 0, 0, -29.66101694915254, -0.5900000000000001 },
		{ -21.398305084745758, -0.9346534653465349, 0, 0, 23.093220338983052, 0.6495412844036696 },
		{ 20.127118644067796, 1.2421052631578948, 0, 0, -20.974576271186443, -0.7151515151515151 },
		{ -19.915254237288135, -1.129787234042553, 0, 0, 22.66949152542373, 0.7719626168224298 },
		{ 14.194915254237289, 1.0567164179104478, 0, 0, -26.05932203389831, -0.6715447154471543 }
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
