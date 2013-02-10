package ui.monitor.input;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Vector;

import javax.swing.JPanel;

import coordinates.CoordinateImage;

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
	private Vector<Integer> acceptingStatePoints = new Vector<Integer>();

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
	 */
	public void addAcceptingStatePoint(int l) {
		acceptingStatePoints.add(l);
	}
	
	/**
	 * Paint the canvas and all its subcomponents
	 */
	public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        g2.drawImage(image, null, 0, 0);

    	g2.setColor(Color.red);
    	g2.setStroke(new BasicStroke(1));
        for(int i : acceptingStatePoints) {
        	g2.draw(new Line2D.Double(i, 0, i, image.getHeight()));
        }
	}
}
