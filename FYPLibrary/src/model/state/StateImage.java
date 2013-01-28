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
			System.out.println("Drawing temporary");
			for(FuzzyNumber n : temporary.getValues()) {
				drawPoint(n, temporary, g2);
			}
		}
	}
	
	private void drawPoint(FuzzyNumber n, FuzzyPoint p, Graphics2D g2) {
		double e = n.getError() / 2;
		Ellipse2D e1 = new Ellipse2D.Double(p.getHorizontalAlignment() * getWidth(), getHeight() * (1 - n.getValue()), 3, 2);
		g2.setColor(new Color(0, 212, 255));
		g2.fill(e1);
		Ellipse2D e2 = new Ellipse2D.Double(p.getHorizontalAlignment() * getWidth() - e, getHeight() * (1 - n.getValue() + e), n.getError(), n.getError());
		g2.setColor(new Color(0, 212, 255, 150));
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
	
	
}
