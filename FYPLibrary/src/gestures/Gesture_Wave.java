package gestures;

public class Gesture_Wave implements Gesture {

	public final boolean[] ENABLED_AXES = new boolean[]{ true, false, true };
	
	public final double[][] GESTURE_MODEL = new double[][] {
			{ -7.692307692307692, -6.929000000000001, 1.3133208255159445, 32.46731428571436, 18.198874296435267, 7.029006185567012 },
			{ 10.131332082551593, 10.521814814814817, 0.9380863039399543, 73.86314000000066, -14.63414634146341, -10.926500000000004 }//,
//			{ -9.568480300187616, -11.697782352941177, -0.562851782363964, -123.10523333333629, 12.195121951219509, 11.800620000000002 },
//			{ 8.067542213883677, 10.570753488372095, 0.37523452157597603, 156.24895000000373, -14.446529080675422, -9.223668831168832 },
//			{ -8.44277673545966, -12.626177777777782, -0.18761726078800223, -312.4978999999838, 14.258911819887427, 7.4760263157894755 },
//			{ 5.065666041275794, 26.304537037037058, -0.1876172607879738, -312.49790000003117, -9.943714821763596, -13.400424528301896 }

	};
	
	protected final int minimumNoReactionTime = 100;
	protected final int maximumReactionTime = 1000;
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
