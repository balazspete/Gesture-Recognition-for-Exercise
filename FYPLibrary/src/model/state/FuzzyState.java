package model.state;

import java.util.Arrays;
import java.util.Collections;

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
	
	private double[] lowFactors = new double[] { 1, 1, 1 };
	private double[] highFactors = new double[] { 1, 1, 1 };
	
	// The direction factor indicates the direction of the motion, 
	// true for positive, false for negative
	private boolean direction = true;
	
	private double minimumBase = 0; 
	
	private double[] factors = new double[] { 0, 0, 0 };
	
	private boolean checkDirection = true;
	
	private double allowedAdditionalError = 1;
	private boolean independentAxes = false;
	
	/**
	 * Create an instance of FuzzyState from the specified PseudoState
	 * @param state The PseudoState from which the FuzzyState will be created form
	 */
	public FuzzyState(PseudoState state) {
		create(state);
	}
	
	/**
	 * Create an instance of FuzzyState from the specified PseudoState with a minimum base value
	 * @param state The PseudoState from which the FuzzyState will be created form
	 * @param minimumBase The minimum value of the base axis in matching
	 */
	public FuzzyState(PseudoState state, int minimumBase) {
		this.minimumBase = minimumBase;
		create(state);
	}
	
	/**
	 * Create an instance of FuzzyState from the specified PseudoState with a minimum base value
	 * @param state The PseudoState from which the FuzzyState will be created form
	 * @param minimumBase The minimum value of the base axis in matching
	 * @param allowedAdditionalError The allowed additional error
	 */
	public FuzzyState(PseudoState state, int minimumBase, double allowedAdditionalError) {
		this.minimumBase = minimumBase;
		this.allowedAdditionalError = allowedAdditionalError;
		create(state);
	}
	
	/**
	 * Set the minimum base value of the state
	 * @param value The value of the minimum base
	 */
	public void setMinimumBase(double value) {
		this.minimumBase = value;
	}
	
	/**
	 * Set the amount of allowed additional error
	 * @param error The value of the additional allowed error
	 */
	public void setAllowedAdditionalError(double error) {
		this.allowedAdditionalError = error;
	}
	
	/**
	 * Determine whether the input vector is pointing towards the same 
	 * general direction as the vector representation of the state
	 * @param vector The vector to test
	 * @return True if the vector and the state are pointing towards the same direction, false otherwise
	 */
	public boolean isInDirection(DisplacementVector vector) {
		double[] values = vector.getAll();
		int index = 0;
		
		while(index < DIMENSIONS) {
			// Axis directions do not correspond, return false
			if((factors[index] * (direction ? 1 : -1) >= 0) != (values[index] >= 0)) return false;
			
			index++;
		}
		
		return true;
	}
	
	/**
	 * Set the direction of check of the axes
	 * @param direction True if axes are to be checked in the order {x, y, z}, false if to be checked in the order {z, y, x}
	 */
	public void setAxisCheckDirection(boolean direction) {
		this.checkDirection = direction;
	}
	
	/**
	 * Get a String representation of the Object
	 */
	public String toString() {
		return direction + " | " +Arrays.toString(factors);
	}
	
	/**
	 * Match the DisplacementVector to the FuzzyState, to determine if it satisfies the state's condition 
	 * @param vector the DisplacementVector to match
	 * @return True if conditions satisfied, false otherwise
	 */
	public synchronized boolean match(DisplacementVector vector) {
		if(independentAxes) {
			return matchHelper_IndependentAxes(vector);
		} else {
			return matchHelper_DependentAxes(vector);
		}
	}
	
	/**
	 * Helper method containing the matching process for independent axes
	 * @param vector the DisplacementVector to match
	 * @return True if conditions satisfied, false otherwise
	 */
	private boolean matchHelper_IndependentAxes(DisplacementVector vector) {
		double[] values = checkDirection ? vector.getAll() : vector.getAllInReverse();
		System.out.println("--");
		
		int index = firstIndex;
		while(index < values.length) {
			// If the corresponding factor is 0, the axis is unused hence skip it
			if(factors[index] == 0) {
				index++;
				continue;
			}
			
			// Return no match, if the direction is opposite
			double axis = factors[index] * (direction ? 1 : -1);
			System.out.println((axis > 0) +" - "+ (values[index] > 0));
			if(axis > 0 != values[index] > 0) return false;

			// Return no match, if the base does not meet the minimum value
			if(Math.abs(minimumBase) > Math.abs(axis)) return false;
			
			System.out.println(minimumBase +" "+axis );
			index++;
		}
		
		return true;
	}
	
	/**
	 * Helper method containing the matching process for dependent axes
	 * @param vector the DisplacementVector to match
	 * @return True if conditions satisfied, false otherwise
	 */
	private boolean matchHelper_DependentAxes(DisplacementVector vector) {
		double[] values = checkDirection ? vector.getAll() : vector.getAllInReverse();
		double base = values[firstIndex];
		
		System.out.println("-\n"+direction + " " + base + " " + minimumBase);
		
		// Return no match, if the direction is opposite
		if(base == 0 || (base > 0) != direction) return false;
		System.out.println(Arrays.toString(values) + " " + Arrays.toString(factors));

		// Return no match, if the base does not meet the minimum value
		if(Math.abs(minimumBase) > Math.abs(base)) return false;

		System.out.println("..-..");
		double baseLow = lowFactors[firstIndex];
		double baseHigh = highFactors[firstIndex];
		
		// For each subsequent values, determine if they are in the acceptable range
		// If value is not in range (between low and high) then return no match
		int index = firstIndex + 1;
		while(index < DIMENSIONS) {
			// If the corresponding factor is 0, the axis is unused hence skip it
			if(factors[index] == 0) {
				index++;
				continue;
			}
			
			double _temp1  = factors[index] * base * baseLow  *  lowFactors[index] * (1-allowedAdditionalError);
			double _temp2 = factors[index] * base * baseHigh * highFactors[index] * (1+allowedAdditionalError);			

			double high = Math.max(_temp1, _temp2);
			double low = Math.min(_temp1, _temp2);
			
			double value = values[index];// * (index%2==1 ? -1 : 1);
			
			// If the value is not within the required bounds, return no match
			System.out.println(index + " | " + low + " " + value + " " + high);
			if(value < low || high < value) return false;
			
			
			index++;
		}
		System.out.println(Arrays.toString(values));
		// If all conditions passed (reached this point), the coordinate is a match
		return true;
	}
	
	/**
	 * Set whether the axes are matched independently
	 * @param isIndependent True if axes are to be matched independently, false otherwise
	 */
	public void setIndependentAxes(boolean isIndependent) {
		this.independentAxes = isIndependent;
	}
	
	/**
	 * Method used to create the state
	 * @param state the PseudoState to use to set the state parameters
	 */
	private void create(PseudoState state) {
		double factor = 1;
		
		try {
			factor = setupBase(checkDirection ? state.getX() : state.getZ(), 0);
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
			FuzzyNumber z = checkDirection ? state.getZ() : state.getX();
			
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
		double newFactor;
		if(factor == 0) {
			newFactor = 0;
		} else {
			newFactor = n.getValue() / factor;
		}
		factors[index] = newFactor;

		double error = n.getError()/100;
		lowFactors[index] = 1.0 - error;
		highFactors[index] = 1.0 + error;
	}
	
	/**
	 * Method to set up the base number (axis)
	 * @param n The corresponding FuzzyNuber
	 * @param index The corresponding index
	 * @return The created base factor
	 */
	private double setupBase(FuzzyNumber n, int index) {
		double factor = 1;
		firstIndex = index;
		
		factor = n.getValue();
		factors[index] = 1;
		direction = (factor >= 0);
		
		double error = n.getError()/100;
		lowFactors[index] = 1.0 - error;
		highFactors[index] = 1.0 + error;
		
		return factor;
	}
	
}
