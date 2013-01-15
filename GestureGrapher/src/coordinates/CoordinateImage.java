package coordinates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Vector;

public class CoordinateImage extends BufferedImage {

	private Vector<Coordinate> coordinates = null;
	
	private int height;
	private double difference;
	private int verticalFactor;
	
	private Color xColor = Color.blue;
	private Color yColor = Color.green;
	private Color zColor = Color.orange;
	
	private BasicStroke pointWidth;
	private BasicStroke lineWidth;
	
	public CoordinateImage() {
		super(1, 1, BufferedImage.TYPE_INT_RGB);
	}
	
	public CoordinateImage(int width, int height, Vector<Coordinate> coordinates, int verticalFactor) {
		super((width * verticalFactor) + 10, height, BufferedImage.TYPE_INT_RGB);
		this.verticalFactor = verticalFactor;
		this.height = height;
		this.coordinates = coordinates;
		createImage();
	}
	
	public Vector<Coordinate> getCoordinates() {
		return coordinates;
	}
	
	public int getVericalFactor() {
		return verticalFactor;
	}
	
	private void createImage() {
		Vector<Double> x = new Vector<Double>();
		Vector<Double> y = new Vector<Double>();
		Vector<Double> z = new Vector<Double>();
		separateCoordinates(x, y, z);
		
		double[] xStats = getStats(x);
		double[] yStats = getStats(y);
		double[] zStats = getStats(z);
		
		difference = Math.max(xStats[2], Math.max(yStats[2], zStats[2]));
		
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(1));
		
		double zero = calculatePoint(0, xStats, (height - 10) / difference);
		Line2D l = new Line2D.Double(0, zero, coordinates.size() * verticalFactor, zero);
		
		g2.draw(l);
		
		drawAxis(x, xStats, xColor);
		drawAxis(y, yStats, yColor);
		drawAxis(z, zStats, zColor);
		
	}
	
	private void drawAxis(Vector<Double> points, double[] stats, Color color) {
		double factor = (height - 10) / difference;
		
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.setColor(color);
		
		Point2D previous = new Point2D.Double(0, calculatePoint(points.elementAt(0), stats, factor));
		
		for(int i = 1; i< points.size(); i++) {
			Point2D p = new Point2D.Double(i* verticalFactor, calculatePoint(points.elementAt(i), stats, factor));
			
			Line2D l = new Line2D.Double(p, previous);
			g2.draw(l);
			previous = p;
		}
	}
	
	private double calculatePoint(double point, double[] stats, double factor) {
		return (point - stats[0]) * factor + 5;
	}
	
	private void separateCoordinates(Vector<Double> x, Vector<Double> y, Vector<Double> z) {
		for(int i = 0; i < coordinates.size(); i++) {
			Coordinate c = coordinates.elementAt(i);
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
