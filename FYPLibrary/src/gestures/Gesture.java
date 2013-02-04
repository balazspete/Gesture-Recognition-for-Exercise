package gestures;

import java.util.List;
import java.util.LinkedList;

import model.state.FuzzyNumber;
import model.state.FuzzyState;
import model.state.PseudoState;

public abstract class Gesture {

	public static double[][] GESTURE_MODEL = new double[][] {};
	
	public static List<FuzzyState> createRepresentation(double[][] model) {
		LinkedList<FuzzyState> states = new LinkedList<FuzzyState>();
		
		for(double[] row : model) {
			states.add(new FuzzyState(new PseudoState(
					new FuzzyNumber(row[0], row[1]),
					new FuzzyNumber(row[2], row[3]),
					new FuzzyNumber(row[4], row[5]))));
		}
		
		return states;
	}
}
