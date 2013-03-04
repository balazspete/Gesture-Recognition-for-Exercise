package gestures;

/**
 * A Gesture to describe a Two drawn by the user
 * @author Balazs Pete
 *
 */
public class Gesture_2 extends Gesture{
	
	private final String GESTURE_NAME = "Two (2)";
	
	public final boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	
	public final double[][] GESTURE_MODEL = new double[][] {
			{ -2.581755593803786, -29.255286666666674, 8.261617900172112, 10.548781250000005, 5.335628227194494, 21.778129032258057, 40 },
			{ -1.549053356282272, -52.50948888888889, -3.958691910499141, -10.27359565217391, 3.4423407917383813, 32.068295000000006 }//,
			//{ 5.163511187607574, 18.003253333333333, -16.86746987951807, -4.822300000000001, -2.5817555938037877, -47.258539999999975 }
	};
	
	protected final int minimumNoReactionTime = 0;
	protected final int maximumReactionTime = 7000;
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
	
	@Override
	public String toString() {
		return GESTURE_NAME;
	}

}
