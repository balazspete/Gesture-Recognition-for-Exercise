package model.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class StateImage extends BufferedImage {
	
	private class ColorChooser {
		@SuppressWarnings("serial")
		private int count = 0;
		private Color[] colors = new Color[] {
			new Color(0, 212, 255), new Color(0, 212, 255, 70),
			new Color(145, 255, 0), new Color(145, 255, 0, 70),
			new Color(255, 162, 0), new Color(255, 162, 0, 70)
		};
		
		public ColorChooser() {}
		
		public Color getColor() {
			Color c = colors[count];
			count = (++count)%colors.length;
			return c;
		}
		
		public void resetCounter() {
			count = 0;
		}
	}
	
	private Vector<FuzzyPoint> points;
	private FuzzyPoint temporary = null;
	private ColorChooser chooser = new ColorChooser();

	public StateImage() {
		super(1, 1, BufferedImage.TYPE_INT_ARGB);
		points = new Vector<FuzzyPoint>();
	}
	
	public StateImage(int width, int height) {
		super(width, height, BufferedImage.TYPE_INT_ARGB);
		points = new Vector<FuzzyPoint>();
	}
	
	public StateImage(int width, int height, Vector<FuzzyPoint> points) {
		super(width, height, BufferedImage.TYPE_INT_ARGB);
		this.points = points;
	}
	
	public Vector<FuzzyPoint> getPoints() {
		return points;
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
			chooser.resetCounter();
		}
		
		if(temporary != null) {
			for(FuzzyNumber n : temporary.getValues()) {
				drawPoint(n, temporary, g2);
			}
			chooser.resetCounter();
		}
	}
	
	private void drawPoint(FuzzyNumber n, FuzzyPoint p, Graphics2D g2) {
		double e = ((n.getError() / 100) * getWidth()) / 2;
		Ellipse2D e1 = new Ellipse2D.Double(
				(p.getHorizontalAlignment() / 100) * getWidth(), 
				getHeight() - (n.getValue() / 100) * getHeight(), 
				2, 2);
		g2.setColor(chooser.getColor());
		g2.fill(e1);
		double error = (n.getError() / 100) * getHeight();
		Ellipse2D e2 = new Ellipse2D.Double(
				(p.getHorizontalAlignment() / 100) * getWidth() - e, 
				getHeight() - (n.getValue() / 100) * getHeight() - e, 
				error, error);
		g2.setColor(chooser.getColor());
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
