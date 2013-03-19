package ui.monitor.input;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;

import coordinates.CoordinateImage;
import gestures.*;

/**
 * A class to draw a CoordinateImage
 * @author Balazs Pete
 *
 */
public class InputCanvas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6076708949184456501L;
	
	private CoordinateImage image;
	private Map<Integer, Gesture> acceptingStatePoints = new HashMap<Integer, Gesture>();
	
	private String monitoredGestureName = null;

	/**
	 * Initialize an InputCanvas
	 */
	public InputCanvas() {
		image = new CoordinateImage();
	}
	
	/**
	 * Initialize an InputCanvas with a specified CoordinateImage
	 * @param image the CoordinateImage to be used
	 */
	public InputCanvas(CoordinateImage image){
		this.setSize(image.getWidth(), this.getHeight());
		this.image = image;
	}
	
	/**
	 * Replace the canvas' image
	 * @param image The new images
	 */
	public void replaceImage(CoordinateImage image) {
		setSize(image.getWidth(), image.getHeight());
		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		
		this.image = image;
	}
	
	/**
	 * Add a location at which an AcceptingStateEvent occurred
	 * @param l The AcceptingStateEvent location
	 * @param gesture The gesture
	 */
	public void addAcceptingStatePoint(int l, Gesture gesture) {
		acceptingStatePoints.put(l, gesture);
	}
	
	/**
	 * Paint the canvas and all its subcomponents
	 */
	public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        g2.drawImage(image, null, 0, 0);

    	g2.setStroke(new BasicStroke(1));
        for(Integer i : acceptingStatePoints.keySet()) {
        	g2.setColor(Color.red);
        	if(monitoredGestureName != null && acceptingStatePoints.get(i).toString().equals(monitoredGestureName)){
        		g2.setColor(Color.blue);
        		g2.draw(new Line2D.Double(i, 0, i, image.getHeight()));
        	}
        	if(!uniqueGesture) g2.draw(new Line2D.Double(i, 0, i, image.getHeight()));
        }
	}
	
	
	private boolean uniqueGesture = false;
	
	/**
	 * Set the gesture which is the focus of the monitoring
	 * @param gestureName The gesture to be marked with a blue line when encountered
	 */
	public void setMonitoredGesture(String gestureName) {
		if(gestureName.equals("__unique-gesture")){
			uniqueGesture = true;
			return;
		}
		if(gestureName.equals("__no-unique-gesture")){
			uniqueGesture = false;
			return;
		}
		monitoredGestureName = gestureName;
	}
	
	/**
	 * Remove the monitored gesture
	 */
	public void removeMonitoredGesture() {
		monitoredGestureName = null;
	}
}
