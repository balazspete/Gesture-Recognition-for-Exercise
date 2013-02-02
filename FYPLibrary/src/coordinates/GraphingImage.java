package coordinates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Class to display the data stored in a GraphData instance
 * @author Balazs Pete
 *
 */
public class GraphingImage extends BufferedImage {
	
	private GraphData data = new GraphData();
	private int scalingFactor = 1;
	
	private Color[] defaultColor = new Color[] {
			Color.blue,
			Color.green,
			Color.orange
	};
	
	/**
	 * Create a blank GraphingImage
	 */
	public GraphingImage() {
		super(1, 1, BufferedImage.TYPE_INT_RGB);
	}
	
	/**
	 * Create a Graphing image with specified inputs
	 * @param width Width of the image
	 * @param height Height of the image
	 * @param graphData the data to be used by the image
	 * @param scalingFactor the scaling factor of the image
	 */
	public GraphingImage(int width, int height, GraphData graphData, int scalingFactor) {
		super(width * scalingFactor, height * scalingFactor, BufferedImage.TYPE_INT_RGB);
		this.scalingFactor = scalingFactor;
		this.data = graphData;
		
	}
	
	/**
	 * Method to update (redraw) the image
	 */
	public void paintImage() {
		setData(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB).getRaster());
		drawData();
	}
	
	/**
	 * Method to get the scaling factor of the image
	 * @return the scaling factor (int)
	 */
	public int getScalingFactor() {
		return scalingFactor;
	}
	
	/**
	 * Method to get the data used by the image
	 * @return the GraphData instance
	 */
	public GraphData getGraphData(){
		return data;
	}
	
	/**
	 * Method to draw the data stored in the GraphData instance onto the image
	 */
	private void drawData() {
		for(CoordinateSeries s : data.getValues()) {
			drawCoordinates(s);
		}
	}
	
	/**
	 * Draw an individual CoordinateSeries onto the image
	 * @param data the instance of CoordinateSeries
	 */
	private void drawCoordinates(CoordinateSeries data) {
		Color[] color = data.getColor();
		if(color == null) color = defaultColor;
		
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.setStroke(new BasicStroke(data.isHighlighted() ? 3 : 1));
		
		Point2D[] previous = calculatePoint(data, 0);
		
		for(int i = 1; i < data.size(); i++) {
			Point2D[] p = calculatePoint(data, i);
			Line2D[] lines = calculateLine(previous, p);
			
			for(int j = 0; j< lines.length; j++) {
				g2.setColor(color[j]);
				g2.draw(lines[j]);
			}
			
			previous = p;
		}
		
		
	}
	
	/**
	 * Method to calculate a line between two coordinates
	 * @param start start point
	 * @param end end point
	 * @return the line calculated
	 */
	private Line2D.Double[] calculateLine(Point2D[] start, Point2D[] end) {
		Line2D.Double[] lines = new Line2D.Double[start.length];
		for(int i = 0; i< start.length; i++) {
			lines[i] = new Line2D.Double(start[i], end[i]);
		}
		return lines;
	}
	
	/**
	 * Method to calculate the 3-axis values of a point
	 * @param series the instance of CoordinateSeries
	 * @param index the index of the point
	 * @return the calculated point
	 */
	private Point2D.Double[] calculatePoint(CoordinateSeries series, int index) {
		Vector<Coordinate> c = series.getCoordinates();
		return new Point2D.Double[] {
				getPoint(c.elementAt(index).getX(), index, series),
				getPoint(c.elementAt(index).getY(), index, series),
				getPoint(c.elementAt(index).getZ(), index, series)
		};
	}
	
	/**
	 * Method to calculate the relative value of an axis value
	 * @param verticalValue the original value
	 * @param index the position of the value in the CoordinateSeries
	 * @param series the instance of CoordinateSeries to which the value belongs to
	 * @return the new relative value
	 */
	private Point2D.Double getPoint(double verticalValue, int index, CoordinateSeries series) {
		return new Point2D.Double(
				index * scalingFactor * series.getHorizontalScaling() * (getWidth() / series.size()) + series.getHorisontalOffset(), 
				getHeight() - (verticalValue - series.getLowest()) * scalingFactor * series.getVerticalScaling() * (getHeight() / (series.getHighest() - series.getLowest())) + series.getVerticalOffset());
	}
	
	
}
