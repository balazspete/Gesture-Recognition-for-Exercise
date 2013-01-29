package model.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class StateImage extends BufferedImage {
	
	private Vector<FuzzyPoint> points = new Vector<FuzzyPoint>();
	private FuzzyPoint temporary = null;

	public StateImage() {
		super(1, 1, BufferedImage.TYPE_INT_ARGB);
	}
	
	public StateImage(int width, int height) {
		super(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public StateImage(int width, int height, Vector<FuzzyPoint> points) {
		super(width, height, BufferedImage.TYPE_INT_ARGB);
		this.points = points;
		this.points.add(new FuzzyPoint(
				new FuzzyNumber(30.10),
				new FuzzyNumber(60.10),
				new FuzzyNumber(90.10)));
	}
	
	public void paintImage() {
		setData(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB).getRaster());
		drawData();
	}
	
	private void drawData() {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		
		for(FuzzyPoint point : points) {
			for(FuzzyNumber n : point.getValues()) {
				drawPoint(n, point, g2);
			}
		}
		
		if(temporary != null) {
			for(FuzzyNumber n : temporary.getValues()) {
				drawPoint(n, temporary, g2);
			}
		}
	}
	
	private void drawPoint(FuzzyNumber n, FuzzyPoint p, Graphics2D g2) {
		double e = ((n.getError() / 100) * getWidth()) / 2;
		Ellipse2D e1 = new Ellipse2D.Double(
				(p.getHorizontalAlignment() / 100) * getWidth(), 
				getHeight() - (n.getValue() / 100) * getHeight(), 
				2, 2);
		g2.setColor(new Color(0, 212, 255));
		g2.fill(e1);
		double error = (n.getError() / 100) * getHeight();
		Ellipse2D e2 = new Ellipse2D.Double(
				(p.getHorizontalAlignment() / 100) * getWidth() - e, 
				getHeight() - (n.getValue() / 100) * getHeight() - e, 
				error, error);
		g2.setColor(new Color(0, 212, 255, 70));
		g2.fill(e2);
	}

	public void setPoints(Vector<FuzzyPoint> points) {
		this.points = points;
	}

	public FuzzyPoint getTemporary() {
		return temporary;
	}

	public void setTemporary(FuzzyPoint temporary) {
		this.temporary = temporary;
	}
	
	public void addPoint(FuzzyPoint point, int index) {
		if(index >= points.size()) {
			points.add(point);
		} else {
			points.add(index, point);
		}
	}
	
	public void removePoint(int index) {
		points.remove(index);
	}
	
	
}
