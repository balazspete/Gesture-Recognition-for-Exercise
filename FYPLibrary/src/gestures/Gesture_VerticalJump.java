package gestures;

/**
 * An ExerciseGesture describing a Vertical Jump
 * @author Balazs Pete
 *
 */
public class Gesture_VerticalJump extends ExerciseGesture {
	
	public boolean[] ENABLED_AXES = new boolean[]{ false, true, true };
	protected final double allowedAdditionalError = 0.5;
	protected final boolean independentAxes = false;
	
	public final double[][] GESTURE_MODEL = new double[][] {
		{ 0.3724394785847309, 288.3689999999993, -12.10428305400373, -4.436446153846152, 1.6759776536312856, 102.53119999999996 },
		{ -3.7243947858473003, -25.953209999999995, 30.167597765363126, 2.136066666666667, 5.959031657355677, 29.738053125000015, 100 },
		{ 5.027932960893855, 19.2246, -24.58100558659217, -4.150765909090911, -8.007448789571693, -22.130644186046517 }
	};
	
	protected boolean[] ANALYSIS_AXES = new boolean[]{ false, true, false };
	
	protected final int minimumNoReactionTime = 0;
	protected final int maximumReactionTime = 2000;
	protected final boolean checkOrder = true;
	
	
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
	public boolean[] getAnalysisAxes() {
		return ANALYSIS_AXES;
	}
}
