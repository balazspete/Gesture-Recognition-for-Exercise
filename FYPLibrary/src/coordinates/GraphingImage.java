package coordinates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class GraphingImage extends BufferedImage {
	
	private GraphData data = new GraphData();
	private int scalingFactor = 1;
	
	private Color[] defaultColor = new Color[] {
			Color.blue,
			Color.green,
			Color.orange
	};
	
	public GraphingImage() {
		super(1, 1, BufferedImage.TYPE_INT_RGB);
	}
	
	public GraphingImage(int width, int height, GraphData graphData, int scalingFactor) {
		super(width * scalingFactor, height * scalingFactor, BufferedImage.TYPE_INT_RGB);
		this.scalingFactor = scalingFactor;
		this.data = graphData;
		
	}
	
	public void paintImage() {
		setData(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB).getRaster());
		drawData();
	}
	
	public int getScalingFactor() {
		return scalingFactor;
	}
	
	public GraphData getGraphData(){
		return data;
	}
	
	private void drawData() {
		for(CoordinateSeries s : data.getValues()) {
			drawCoordinates(s);
		}
	}
	
	private void drawCoordinates(CoordinateSeries data) {
		Color[] color = data.getColor();
		if(color == null) color = defaultColor;
		
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.setStroke(new BasicStroke(1));
		
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
	
	private Line2D.Double[] calculateLine(Point2D[] start, Point2D[] end) {
		Line2D.Double[] lines = new Line2D.Double[start.length];
		for(int i = 0; i< start.length; i++) {
			lines[i] = new Line2D.Double(start[i], end[i]);
		}
		return lines;
	}
	
	private Point2D.Double[] calculatePoint(CoordinateSeries series, int index) {
		Vector<Coordinate> c = series.getCoordinates();
		return new Point2D.Double[] {
				getPoint(c.elementAt(index).getX(), index, series),
				getPoint(c.elementAt(index).getY(), index, series),
				getPoint(c.elementAt(index).getZ(), index, series)
		};
	}
	
	private Point2D.Double getPoint(double verticalValue, int index, CoordinateSeries series) {
		return new Point2D.Double(
				index * scalingFactor * series.getHorizontalScaling() * (getWidth() / series.size()) + series.getHorisontalOffset(), 
				getHeight() - (verticalValue - series.getLowest()) * scalingFactor * series.getVerticalScaling() * (getHeight() / (series.getHighest() - series.getLowest())) + series.getVerticalOffset());
	}
	
	
}
