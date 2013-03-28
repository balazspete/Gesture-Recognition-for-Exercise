package gestures;

/**
 * A Gesture describing an upwards motion
 * @author Balazs Pete
 *
 */
public class Gesture_Upwards extends Gesture {

	private final String GESTURE_NAME = "Upwards";
	
	public static final boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	
	public static final double[][] GESTURE_MODEL = new double[][] {
//		{ -2.0179372197309418, 	-3.2211111111111115, 	17.883408071748875, 	1.7250943396226421, 1.5695067264573996, 2.5485714285714276 },
//		{ 0.44843049327354123, 	25.645000000000078, 	8.295964125560545, 		1.386216216216215, 	2.2421524663677115, 1.7840000000000011 }
		{ -1.3207547169811313, -60.19285714285718, 16.603773584905674, 5.745681818181813, 1.6981132075471699, 31.21111111111111, 20 },
		{ 0.37735849056603765, 308.99000000000007, 18.30188679245282, 3.185463917525775, 2.830188679245282, 20.59933333333334 }

	};
	
	protected final int minimumNoReactionTime = 2000;
	protected final int maximumReactionTime = 5000;	
	protected final boolean checkOrder = true;
	
	private final double allowedAdditionalerror = 0.4;
	private final boolean independentAxes = false;

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
		return allowedAdditionalerror;
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
