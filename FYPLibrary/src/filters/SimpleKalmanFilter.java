package filters;

import coordinates.Coordinate;

/**
 * A simple implementation of the Kalman filter
 * @author Balazs Pete
 *
 */
public class SimpleKalmanFilter implements Filter {

	/**
	 * A class implementing a simple one dimensional Kalman filter
	 * @author Balazs Pete
	 *
	 */
	private class SimpleKalmanFilterHelper {
//		private double Q = 0.1;
//		private double R = 0.00001;
//		private double P = 0.9, X = 0, K;
		
		private double Q = 1;
		private double R = 0.001;
		private double P = 0.8, X = 0, K;

		/**
		 * Update the inner variables
		 */
		private void measurementUpdate(){
			K = (P + Q) / (P + Q + R);
			P = R * (P + Q) / (R + P + Q);
		}

		/**
		 * Update the filter with a new measurement
		 * @param measurement The new measurement
		 * @return The filtered measurement
		 */
		public double update(double measurement){
			measurementUpdate();
			double result = X + (measurement - X) * K;
			X = result;
	
			return result;
		}
	}
	
	private SimpleKalmanFilterHelper x, y, z;
	private Filter other = null;
	
	public SimpleKalmanFilter() {
		x = new SimpleKalmanFilterHelper();
		y = new SimpleKalmanFilterHelper();
		z = new SimpleKalmanFilterHelper();
	}
	
	public SimpleKalmanFilter(Filter other) {
		x = new SimpleKalmanFilterHelper();
		y = new SimpleKalmanFilterHelper();
		z = new SimpleKalmanFilterHelper();
		this.other = other;
	}
	
	@Override
	public Coordinate filter(Coordinate coordinate) {
		Coordinate c = coordinate; 
		if(other != null) {
			c = other.filter(c);
		} 
		
		if(c == null) return null;
		
		return new Coordinate(
				x.update(c.getX()),
				y.update(c.getY()),
				z.update(c.getZ()));
	}

}
