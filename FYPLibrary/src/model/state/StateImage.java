package model.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * An image to display a set of PseudoStates
 * @author Balazs Pete
 *
 */
public class StateImage extends BufferedImage {
	
	/**
	 * A helper class to color the displayed states
	 * @author Balazs Pete
	 *
	 */
	private class ColorChooser {
		private int count = 0;
		private Color[] colors = new Color[] {
			new Color(0, 212, 255), new Color(0, 212, 255, 70),
			new Color(145, 255, 0), new Color(145, 255, 0, 70),
			new Color(255, 162, 0), new Color(255, 162, 0, 70)
		};
		
		/**
		 * Create an instance of ColorChooser
		 */
		public ColorChooser() {}
		
		/**
		 * Get the current Color and increment to the next
		 * @return the current Color
		 */
		public Color getColor() {
			Color c = colors[count];
			count = (++count)%colors.length;
			return c;
		}
		
		/**
		 * Reset the chooser to the base Color
		 */
		public void resetCounter() {
			count = 0;
		}
	}
	
	private Vector<FuzzyPoint> points;
	private FuzzyPoint temporary = null;
	private ColorChooser chooser = new ColorChooser();

	/**
	 * Create a blank StateImage
	 */
	public StateImage() {
		super(1, 1, BufferedImage.TYPE_INT_ARGB);
		points = new Vector<FuzzyPoint>();
	}
	
	/**
	 * Create a StateImage with specified width and height
	 * @param width width of the image
	 * @param height height of the image
	 */
	public StateImage(int width, int height) {
		super(width, height, BufferedImage.TYPE_INT_ARGB);
		points = new Vector<FuzzyPoint>();
	}
	
	/**
	 * Create an instance of StateImage with specified width, height and a set of FuzzyPoints
	 * @param width width of the image
	 * @param height height of the image
	 * @param points the set of FuzzyPoints used by the image
	 */
	public StateImage(int width, int height, Vector<FuzzyPoint> points) {
		super(width, height, BufferedImage.TYPE_INT_ARGB);
		this.points = points;
	}
	
	/**
	 * Get the set of points used by the image
	 * @return the set of FuzzyPoints
	 */
	public Vector<FuzzyPoint> getPoints() {
		return points;
	}
	
	/**
	 * Method to update (reset & redraw) the image
	 */
	public void paintImage() {
		setData(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB).getRaster());
		drawData();
	}
	
	/**
	 * Method to draw the data onto the image
	 */
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
	
	/**
	 * Method to draw a single point onto the image
	 * @param n the value to be drawn
	 * @param p the FuzzyPoint to which the value belongs to
	 * @param g2 the graphics of the image
	 */
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

	/**
	 * Method to set the set of FuzzyPoints to be used by the image
	 * @param points the new set of FuzzyPoints
	 */
	public void setPoints(Vector<FuzzyPoint> points) {
		this.points = points;
	}

	/**
	 * Get the temporary FuzzyPoint
	 * @return the temporary FuzzyPoint
	 */
	public FuzzyPoint getTemporary() {
		return temporary;
	}

	/**
	 * Set the temporary FuzzyPoint (a point which is not added to the set, but is drawn)
	 * @param temporary the FuzzyPoint to be set as temporary
	 */
	public void setTemporary(FuzzyPoint temporary) {
		this.temporary = temporary;
	}
	
	/**
	 * Add a FuzzyPoint to the set of points
	 * @param point the new FuzzyPoint
	 * @param index the index at which the point should be placed (all subsequent points will be shifted)
	 */
	public void addPoint(FuzzyPoint point, int index) {
		if(index >= points.size()) {
			points.add(point);
		} else {
			points.add(index, point);
		}
	}
	
	/**
	 * Remove a point at a specified index
	 * @param index the index of the point
	 */
	public void removePoint(int index) {
		points.remove(index);
	}
	
	
}
