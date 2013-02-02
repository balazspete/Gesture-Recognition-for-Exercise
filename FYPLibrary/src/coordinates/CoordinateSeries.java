package coordinates;

import java.awt.Color;
import java.util.Collections;
import java.util.Vector;

/**
 * Class to represent the 
 * @author balazs Pete
 *
 */
public class CoordinateSeries {

	private Vector<Coordinate> series;
	
	private Color[] color = null;

	private double lowest;
	private double highest;
	
	private double verticalScaling = 1;

	private double horizontalScaling = 1;
	
	private double verticalOffset = 0;
	private double horisontalOffset = 0;
	
	private boolean highlighted = false;
	
	/**
	 * Create an instance of CoordinateSeries
	 * @param series list of Coordinates
	 */
	public CoordinateSeries(Vector<Coordinate> series) {
		this.series = series;
		
		Vector<Double> x = new Vector<Double>(), 
				y = new Vector<Double>(), 
				z = new Vector<Double>();
		separateCoordinates(x, y, z);
		
		double[] xStats = getStats(x), 
				yStats = getStats(y), 
				zStats = getStats(z);
		
		highest = Math.max(xStats[1], Math.max(yStats[1], zStats[1]));
		lowest = Math.min(xStats[0], Math.min(yStats[0], zStats[0]));
	}
	
	/**
	 * Get the list of Coordinates
	 * @return the Coordinates in order
	 */
	public Vector<Coordinate> getCoordinates() {
		return series;
	}
	
	/**
	 * Get the size of the series
	 * @return number of Coordinates in series
	 */
	public int size() {
		return series.size();
	}
	
	/**
	 * Get the highest value in the series
	 * @return the highest value
	 */
	public double getHighest() {
		return highest;
	}
	
	/**
	 * Get the lowest value in the series
	 * @return the lowest value
	 */
	public double getLowest() {
		return lowest;
	}
	
	/**
	 * Get the vertical scaling factor of the series
	 * @return vertical scaling
	 */
	public double getVerticalScaling() {
		return verticalScaling;
	}

	/**
	 * Set the vertical scaling of the series
	 * @param verticalScaling new vertical scaling
	 */
	public void setVerticalScaling(double verticalScaling) {
		this.verticalScaling = verticalScaling;
	}

	/**
	 * Get the horizontal scaling of the series
	 * @returnthe horizontal scaling
	 */
	public double getHorizontalScaling() {
		return horizontalScaling;
	}

	/**
	 * Set the horizontal scaling of the series
	 * @param horisontalScaling new horizontal scaling
	 */
	public void setHorizontalScaling(double horisontalScaling) {
		this.horizontalScaling = horisontalScaling;
	}

	/**
	 * Get the vertical offset
	 * @return vertical offset (int) in px
	 */
	public double getVerticalOffset() {
		return verticalOffset;
	}

	/**
	 * Set the vertical offset 
	 * @param verticalOffset vertical offset in px
	 */
	public void setVerticalOffset(double verticalOffset) {
		this.verticalOffset = verticalOffset;
	}

	/**
	 * Get the horizontal offset of the series
	 * @return horizontal offset in px
	 */
	public double getHorisontalOffset() {
		return horisontalOffset;
	}

	/**
	 * Set the horizontal offset of the series
	 * @param horisontalOffset new horizontal offset in px
	 */
	public void setHorizontalOffset(double horisontalOffset) {
		this.horisontalOffset = horisontalOffset;
	}
	
	/**
	 * Get the list of Colors
	 * @return list of Colors
	 */
	public Color[] getColor() {
		return color;
	}

	/**
	 * Set the list of Colors to be used by the instance
	 * @param color
	 */
	public void setColor(Color[] color) {
		this.color = color;
	}
	
	/**
	 * Set whether the series is highlighted (3px stroke)
	 * @param highlight true if highlighted, false otherwise
	 */
	public void setHighlight(boolean highlight) {
		this.highlighted = highlight;
	}
	
	/**
	 * Determine whether the series if highlighted
	 * @return true if highlighted, false otherwise
	 */
	public boolean isHighlighted() {
		return highlighted;
	}
	
	/**
	 * Function to separate the coordinates into three axis-wise lists
	 * @param x x-list
	 * @param y y-list
	 * @param z z-list
	 */
	private void separateCoordinates(Vector<Double> x, Vector<Double> y, Vector<Double> z) {
		for(int i = 0; i < series.size(); i++) {
			Coordinate c = series.elementAt(i);
			x.add(c.getX());
			y.add(c.getY());
			z.add(c.getZ());
		}
	}
	
	/**
	 * Method to get the stats of a set of numbers (min, max, difference)
	 * @param points set of points
	 * @return the statistics of the set (0:min, 1:max, 2:max-difference)
	 */
	private double[] getStats(Vector<Double> points) {
		double[] stats = new double[3];
		
		stats[0] = Collections.min(points);
		stats[1] = Collections.max(points);
		stats[2] = Math.abs(stats[1] - stats[0]);
		
		return stats;
	}
}
