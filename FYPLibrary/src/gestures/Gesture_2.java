package gestures;

public class Gesture_2 implements Gesture{
	
	public final boolean[] ENABLED_AXES = new boolean[]{ true, true, false };
	
	public final double[][] GESTURE_MODEL = new double[][] {
//		{ -5.322580645161291, -0.9393939393939394, 8.225806451612911, 0.9725490196078422, 9.516129032258064, 0.788135593220339 },
//		{ 4.193548387096776, 1.4307692307692303, -19.67741935483872, -0.5590163934426227, -1.7741935483870979, -5.636363636363633 }
		{ 0.2949852507374633, 16.949999999999992, 5.7522123893805315, -1.7384615384615383, 4.572271386430675, 2.18709677419355 },
		{ 4.71976401179941, 1.3771875, -20.64896755162242, -0.6053571428571428, -10.914454277286133, -0.9162162162162164 },
		{ -3.6873156342182885, -1.6272000000000002, -3.392330383480825, -2.6530434782608703, 11.061946902654869, 0.9039999999999999 }

	};
	
	protected final int minimumBaseValue = 100;
	protected final int minimumNoReactionTime = 1000;
	protected final int maximumReactionTime = 3000;
	protected final boolean checkOrder = false;
	
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
		return 0;
	}
}
