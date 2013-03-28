package filters;

import coordinates.Coordinate;

/**
 * A class to artificially introduce noise into a coordinate
 * @author Balazs Pete
 *
 */
public class NoiseSimulatorFilter implements Filter {
	
	protected double noiseRatio;
	protected double maxNoiseValue;
	protected Filter other;
	
	/**
	 * Create a new instance of NoiseSimulatorFilter with specified parameters
	 * @param noiseRatio The probability of a distorting noise value to be introduced (range: [0; 1])
	 * @param maxNoiseValue The maximum amount of noise that can be introduced into the coordinate
	 */
	public NoiseSimulatorFilter(double noiseRatio, double maxNoiseValue) {
		setup(noiseRatio, maxNoiseValue);
	}
	
	/**
	 * Create a new instance of NoiseSimulatorFilter with specified parameters
	 * @param noiseRatio The probability of a distorting noise value to be introduced (range: [0; 1])
	 * @param maxNoiseValue The maximum amount of noise that can be introduced into the coordinate
	 */
	public NoiseSimulatorFilter(double noiseRatio, double maxNoiseValue, Filter other) {
		this.other = other;
		setup(noiseRatio, maxNoiseValue);
	}
	
	/**
	 * Method to help the initialization of the Object
	 * @param noiseRatio The noise ratio
	 * @param maxNoiseValue The maximum noise value
	 */
	private void setup(double noiseRatio, double maxNoiseValue) {
		if(noiseRatio < 0 || 1 < noiseRatio) {
			this.noiseRatio = 0;
		} else {
			this.noiseRatio = noiseRatio;
		}
		
		this.maxNoiseValue = Math.abs(maxNoiseValue);
	}

	@Override
	public Coordinate filter(Coordinate coordinate) {
		System.out.println("...");
		return new Coordinate(
			addNoise(coordinate.getX()),
			addNoise(coordinate.getY()),
			addNoise(coordinate.getZ()));
	}
	
	/**
	 * Add some noise to the input number based on the instance attributes
	 * @param d The number to add noise to
	 * @return the input number with some noise (probability of noise being added depends on instance parameters)
	 */
	private double addNoise(double d) {
		return d + (Math.random() < noiseRatio ? (Math.random() * 2 * maxNoiseValue) - maxNoiseValue : 0);
	}

}
