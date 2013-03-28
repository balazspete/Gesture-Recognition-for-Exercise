
package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Vector;

import javax.swing.JOptionPane;

import exceptions.InsufficentModelDataError;

import model.state.FuzzyPoint;
import model.state.PseudoState;

/**
 * A class to create a model of a gesture based on a list of FuzzyPoints
 * @author Balazs Pete
 *
 */
public class CreateModel {
	
	/**
	 * Create a model (a list of PseudoStates) based on a set of FuzzyPoints
	 * @param points the set of FuzzyPoints
	 * @return the set of PseudoStates
	 * @throws InsufficentModelDataError thrown if the input set contains less than two points
	 */
	public static Vector<PseudoState> create(Vector<FuzzyPoint> points) throws InsufficentModelDataError {
		return create(points, 1);
	}
	
	/**
	 * Create a model (a list of PseudoStates) based on a set of FuzzyPoints
	 * @param points the set of FuzzyPoints
	 * @param scale the scaling factor
	 * @return the set of PseudoStates
	 * @throws InsufficentModelDataError thrown if the input set contains less than two points
	 */
	public static Vector<PseudoState> create(Vector<FuzzyPoint> points, int scale) throws InsufficentModelDataError {
		if(points.size() < 2) throw new InsufficentModelDataError(points.size());
		
		return calculate(points, scale);
	}
	
	/**
	 * Save the model created by the create() function to a file
	 * @param file the file to which the model is written to
	 * @param states the model to be saved
	 */
	public static void save(File file, Vector<PseudoState> states) {
		String output = "";
		for(PseudoState state : states) {
			output += state.toString() + "\n";
		}
		
		try{
			FileWriter fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(output);
			out.close();
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "An error has occurred while saving the file...", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Calculate the model (set of PseudoStates) from a set of FuzzyPoints
	 * @param points the set of FuzzyPoints
	 * @return the set of PseudoStates, known as the model
	 */
	private static Vector<PseudoState> calculate(Vector<FuzzyPoint> points) {
		return calculate(points, 1);
	}
	
	/**
	 * Calculate the model (set of PseudoStates) from a set of FuzzyPoints
	 * @param points the set of FuzzyPoints
	 * @param scale The scaling factor
	 * @return the set of PseudoStates, known as the model
	 */
	private static Vector<PseudoState> calculate(Vector<FuzzyPoint> points, int scale) {
		Vector<PseudoState> states = new Vector<PseudoState>();
		
		for(int i = 1; i < points.size(); i++) {
			PseudoState s = PseudoState.getDifference(points.get(i - 1), points.get(i), scale);
			states.add(s);
		}
		
		return states;
	}
	
	
	
}
