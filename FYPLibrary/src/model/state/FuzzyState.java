package model.state;

import coordinates.DisplacementVector;

import exceptions.InvalidDimensionException;

/**
 * A class defining the state in a Finite State Machine for Gesture recognition
 * @author Balazs Pete
 *
 */
public class FuzzyState {
	
	private static final int DIMENSIONS = 3;
	
	private int firstIndex = -1;
	
	private double[] lowFactors = new double[] { 0, 0, 0 };
	private double[] highFactors = new double[] { 0, 0, 0 };
	
	// The direction factor indicates the direction of the motion, 
	// true for positive, false for negative
	private boolean direction = true;
	
	private double[] factors = new double[] { 0, 0, 0 };
	
	/**
	 * Create an instance of FuzzyState from the specified PseudoState
	 * @param state The PseudoState from which the FuzzyState will be created form
	 */
	public FuzzyState(PseudoState state) {
		create(state);
	}
	
	/**
	 * Match the DisplacementVector to the FuzzyState, to determine if it satisfies the state's condition 
	 * @param vector the DisplacementVector to match
	 * @return True if conditions satisfied, false otherwise
	 */
	public boolean match(DisplacementVector vector) {
		double[] values = vector.getAll();
		double base = values[firstIndex];
		
		// Return no match, if the direction is opposite
		if((base >= 0) != direction) return false;
		
		double baseLow = lowFactors[firstIndex];
		double baseHigh = highFactors[firstIndex];
		
		// For each subsequent values, determine if they are in the acceptable range
		// If value is not in range (between low and high) then return no match
		int index = firstIndex + 1;
		while(index < DIMENSIONS) {
			if(factors[index] == 0) {
				index++;
				continue;
			}
			
			double low  = factors[index] * base * baseLow  *  lowFactors[index];
			double high = factors[index] * base * baseHigh * highFactors[index];
			
			double value = values[index];
			if(value < low || high < value) return false;
			
			index++;
		}
		
		// If all conditions passed (reached this point), the coordinate is a match
		return true;
	}
	
	/**
	 * Method used to create the state
	 * @param state the PseudoState to use to set the state parameters
	 */
	private void create(PseudoState state) {
		double factor = 1;
		
		try {
			factor = setupBase(state.getX(), 0);
		} catch (InvalidDimensionException e1) {
			factors[0] = 0;
		}
		
		try {
			FuzzyNumber y = state.getY();
			
			if(firstIndex != -1) {
				setupNumber(y, 1, factor);
			} else {
				factor = setupBase(y, 1);
			}
		} catch(InvalidDimensionException e) {
			factors[1] = 0;
		}
		
		try {
			FuzzyNumber z = state.getZ();
			
			if(firstIndex != -1) {
				setupNumber(z, 2, factor);
			} else {
				factor = setupBase(z, 2);
			}
		} catch(InvalidDimensionException e) {
			factors[2] = 0;
		}
	}
	
	/**
	 * Method to set up a dependent number (axis)
	 * @param n Corresponding FuzzyNumber
	 * @param index The corresponding index of the axis
	 * @param factor The factor of the base number
	 */
	private void setupNumber(FuzzyNumber n, int index, double factor) {
		double newFactor = n.getValue() / factor;
		factors[index] = newFactor;
		
		lowFactors[index] = (100.0 - n.getError()) / 100;
		highFactors[index] = (100.0 + n.getError()) / 100;
	}
	
	/**
	 * Method to set up the base number (axis)
	 * @param n The corresponding FuzzyNuber
	 * @param index The corresponding index
	 * @return The created base factor
	 */
	private double setupBase(FuzzyNumber n, int index) {
		double factor = 1;
		firstIndex = 0;
		
		factor = n.getValue();
		factors[index] = 1;
		direction = (factor >= 0);
		
		lowFactors[index] = (100.0 - n.getError()) / 100;
		highFactors[index] = (100.0 + n.getError()) / 100;
		
		return factor;
	}
	
}
