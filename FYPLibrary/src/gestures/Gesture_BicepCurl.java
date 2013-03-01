package gestures;

public class Gesture_BicepCurl extends ExerciseGesture {

	public final boolean[] ENABLED_AXES = new boolean[]{ false, true, false };
	protected final double allowedAdditionalError = 0.3;
	protected final boolean independentAxes = false;

	public final double[][] GESTURE_MODEL = new double[][] {
		{ -4.529616724738677, 	-16.473799999999997, 	20.55749128919861, 		2.233735593220339, 		-5.400696864111499, 	-14.879561290322583, 	20 },
		{ 1.7421602787456436, 	62.600440000000035, 	20.731707317073173, 	3.3224470588235286, 	12.195121951219514, 	7.060199999999998 },
		{ -1.0452961672473862, 	-120.80786666666674, 	-23.170731707317074, 	-2.7249894736842104, 	-12.195121951219514, 	-7.060199999999998 },
		{ 3.8327526132404195, 	25.459509090909084, 	-17.24738675958188, 	-2.995236363636364, 	4.181184668989546, 		23.33788333333334 }
	};
	
	protected final boolean[] ANALYSIS_AXES = new boolean[]{ false, true, false };
	
	protected final int minimumNoReactionTime = 500;
	protected final int maximumReactionTime = 2000;
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

	@Override
	public boolean[] getAnalysisAxes() {
		return ANALYSIS_AXES;
	}
	
	
	

}
