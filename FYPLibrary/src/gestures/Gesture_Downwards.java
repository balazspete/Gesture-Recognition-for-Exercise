package gestures;

public class Gesture_Downwards extends Gesture {

	public boolean[] ENABLED_AXES = new boolean[]{ true, true, true };
	
	public final double[][] GESTURE_MODEL = new double[][] {
		{ 1.0791366906474824, 41.21813333333332, -13.129496402877692, -5.505161643835619, -3.2374100719424455, -20.60906666666667 },
		{ 2.3381294964028783, 23.779692307692304, -14.20863309352518, -11.739341772151898, -2.8776978417266186, -27.049400000000002 }
	};
	
	protected final int minimumNoReactionTime = 0;
	protected final int maximumReactionTime = 5000;
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
	
}
