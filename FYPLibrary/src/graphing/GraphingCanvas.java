package graphing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.Vector;

import javax.swing.JPanel;

import model.state.FuzzyPoint;
import model.state.StateImage;


@SuppressWarnings("serial")
/**
 * A class to display a StateImage on top of a GraphingImage
 * @author Balazs Pete
 *
 */
public class GraphingCanvas extends JPanel {

	private GraphingImage image = null;
	private StateImage simage = null;
	private boolean cursor = false;
	private int mouseXLocation = -1;
	private int mouseYLocation = -1;
	private int savedMouseYLocation = -1;
	
	/**
	 * create an instance of GraphingCanvas
	 */
	public GraphingCanvas() {
		image = new GraphingImage();
		simage = new StateImage();
		addListeners();
	}
	
	/**
	 * Create an instance of GraphingCanvas with a specified GraohingImage
	 * @param image instance of GraphingImage to be used 
	 */
	public GraphingCanvas(GraphingImage image) {
		this.setSize(image.getWidth(), image.getHeight());
		this.image = image;
		this.simage = new StateImage(getWidth(), getHeight());
		addListeners();
	}
	
	/**
	 * Create an instance of GraphingCanvas with a specified GraohingImage and a set of FuzzyPoints
	 * @param image instance of GraphingImage to be used 
	 * @param points set of FuzzyPoints to be used by the StatesImage
	 */
	public GraphingCanvas(GraphingImage image, Vector<FuzzyPoint> points) {
		this.setSize(image.getWidth(), image.getHeight());
		this.image = image;
		this.simage = new StateImage(getWidth(), getHeight(), points);
		addListeners();
	}
	
	/**
	 * Replace the GraphingImage used by the canvas
	 * @param image the new instance of GraphingImage to be used
	 */
	public void replaceImage(GraphingImage image) {
		this.image = image;
		this.setSize(image.getWidth(), image.getHeight());
		this.setPreferredSize(this.getSize());
	}
	
	/**
	 * Update the images of the canvas
	 */
	public void reloadImage() {
		image.paintImage();
		simage.paintImage();
	}
	
	/**
	 * Resize the images contained in the canvas to fir the canvas
	 */
	public void resizeImage() {
		image = new GraphingImage(getWidth(), getHeight(), image.getGraphData(), image.getScalingFactor());
		image.paintImage();
		simage = new StateImage(getWidth(), getHeight(), simage.getPoints());
		simage.paintImage();
	}
	
	/**
	 * Paint the canvas and all subcomponents
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        g2.drawImage(image, null, 0, 0);
        g2.drawImage(simage, null, 0,0);
        
        if (cursor) {
        	Line2D line = new Line2D.Double(mouseXLocation, 0, mouseXLocation, this.getHeight());
            g2.setColor(Color.red);
            g2.setStroke(new BasicStroke(1));
            g2.draw(line);
        }
        
        FuzzyPoint p = simage.getTemporary();
        if (p != null) {
        	double x = (p.getHorizontalAlignment() / 100) * getWidth();
        	Line2D line = new Line2D.Double(x, 0, x, this.getHeight());
            g2.setColor(Color.pink);
            g2.setStroke(new BasicStroke(1));
            g2.draw(line);
            Line2D line2 = new Line2D.Double(0, mouseYLocation, this.getHeight(), mouseYLocation);
            g2.setColor(Color.red);
            g2.draw(line2);
        }
        
        if(savedMouseYLocation >= 0) {
        	Line2D line = new Line2D.Double(0, savedMouseYLocation, this.getHeight(), savedMouseYLocation);
            g2.setColor(Color.pink);
            g2.setStroke(new BasicStroke(1));
            g2.draw(line);
        }
	}

	/**
	 * Update the mouse location
	 * @param e the mouse event
	 */
	public void updateMouseLocation(MouseEvent e){
		mouseXLocation = e.getX();
		mouseYLocation = e.getY(); 
		this.paintAll(this.getGraphics());
	}
	
	/**
	 * Get the StateImage contained by the canvas
	 * @return the instance of StateImage
	 */
	public StateImage getSimage() {
		return simage;
	}
	
	/**
	 * Hide the line showing the mouse location
	 * @param e mouse event
	 */
	private void hideLine(MouseEvent e){
		mouseXLocation = -1;
		this.paintAll(this.getGraphics());
	}
	
	/**
	 * Add the listeners
	 */
	private void addListeners() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				hideLine(e);
			}
		});
	}
	
	/**
	 * Set whether the cursor is drawn or not
	 * @param visible true if to be drawn, false otherwise
	 */
	public void setCursorVisibility(boolean visible) {
		cursor = visible;
		if(!visible) savedMouseYLocation = -1;
	}
	
	/**
	 * Get the last saved location of the mouse
	 * @return the last saved mouse location
	 */
	public int getSavedMouseYLocation() {
		return savedMouseYLocation;
	}
	
	/**
	 * Save the mouse location
	 */
	public void saveMouseYLocation() {
		savedMouseYLocation = mouseYLocation;
	}
}
