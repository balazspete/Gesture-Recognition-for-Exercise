package coordinates;

import java.awt.Color;
import java.util.Collections;
import java.util.Vector;

public class CoordinateSeries {

	private Vector<Coordinate> series;
	
	private Color[] color = null;

	private double lowest;
	private double highest;
	
	private double verticalScaling = 1;

	private double horizontalScaling = 1;
	
	private double verticalOffset = 0;
	private double horisontalOffset = 0;
	
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
	
	public Vector<Coordinate> getCoordinates() {
		return series;
	}
	
	public int size() {
		return series.size();
	}
	
	public double getHighest() {
		return highest;
	}
	
	public double getLowest() {
		return lowest;
	}
	
	public double getVerticalScaling() {
		return verticalScaling;
	}

	public void setVerticalScaling(double verticalScaling) {
		this.verticalScaling = verticalScaling;
	}

	public double getHorizontalScaling() {
		return horizontalScaling;
	}

	public void setHorizontalScaling(double horisontalScaling) {
		this.horizontalScaling = horisontalScaling;
	}

	public double getVerticalOffset() {
		return verticalOffset;
	}

	public void setVerticalOffset(double verticalOffset) {
		this.verticalOffset = verticalOffset;
	}

	public double getHorisontalOffset() {
		return horisontalOffset;
	}

	public void setHorizontalOffset(double horisontalOffset) {
		this.horisontalOffset = horisontalOffset;
	}
	
	public Color[] getColor() {
		return color;
	}

	public void setColor(Color[] color) {
		this.color = color;
	}
	
	private void separateCoordinates(Vector<Double> x, Vector<Double> y, Vector<Double> z) {
		for(int i = 0; i < series.size(); i++) {
			Coordinate c = series.elementAt(i);
			x.add(c.getX());
			y.add(c.getY());
			z.add(c.getZ());
		}
	}
	
	/**
	 * 
	 * @param points
	 * @return min, max, diff
	 */
	private double[] getStats(Vector<Double> points) {
		double[] stats = new double[3];
		
		stats[0] = Collections.min(points);
		stats[1] = Collections.max(points);
		stats[2] = Math.abs(stats[1] - stats[0]);
		
		return stats;
	}
}
