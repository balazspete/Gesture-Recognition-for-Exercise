
package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.state.FuzzyPoint;
import model.state.InsufficentModelDataError;
import model.state.PseudoState;

public class CreateModel {
	
	public static Vector<PseudoState> create(Vector<FuzzyPoint> points) throws InsufficentModelDataError {
		if(points.size() < 2) throw new InsufficentModelDataError(points.size());
		
		return calculate(points);
	}
	
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
	
	private static Vector<PseudoState> calculate(Vector<FuzzyPoint> points) {
		Vector<PseudoState> states = new Vector<PseudoState>();
		
		for(int i = 1; i < points.size(); i++) {
			states.add(PseudoState.getDifference(points.get(i - 1), points.get(i)));
		}
		
		return states;
	}
	
	
	
	
	
}
