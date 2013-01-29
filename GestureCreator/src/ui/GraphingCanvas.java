package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JPanel;

import model.state.FuzzyPoint;
import model.state.StateImage;

import coordinates.GraphingImage;

@SuppressWarnings("serial")
public class GraphingCanvas extends JPanel {

	private GraphingImage image = null;
	private StateImage simage = null;
	private boolean cursor = false;
	private int mouseXLocation = -1;
	private int mouseYLocation;
	
	public GraphingCanvas() {
		image = new GraphingImage();
		simage = new StateImage();
		addListeners();
	}
	
	public GraphingCanvas(GraphingImage image) {
		this.setSize(image.getWidth(), image.getHeight());
		this.image = image;
		this.simage = new StateImage(getWidth(), getHeight());
		addListeners();
	}
	
	public GraphingCanvas(GraphingImage image, Vector<FuzzyPoint> points) {
		this.setSize(image.getWidth(), image.getHeight());
		this.image = image;
		this.simage = new StateImage(getWidth(), getHeight(), points);
		addListeners();
	}
	
	public void replaceImage(GraphingImage image) {
		this.image = image;
		this.setSize(image.getWidth(), image.getHeight());
		this.setPreferredSize(this.getSize());
	}
	
	public void reloadImage() {
		image.paintImage();
		simage.paintImage();
	}
	
	public void resizeImage() {
		image = new GraphingImage(getWidth(), getHeight(), image.getGraphData(), image.getScalingFactor());
		image.paintImage();
		simage = new StateImage(getWidth(), getHeight());
		simage.paintImage();
	}
	
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
	}

	public void updateMouseLocation(MouseEvent e){
		mouseXLocation = e.getX();
		mouseYLocation = e.getY(); 
		this.paintAll(this.getGraphics());
	}
	
	public StateImage getSimage() {
		return simage;
	}
	
	private void hideLine(MouseEvent e){
		mouseXLocation = -1;
		this.paintAll(this.getGraphics());
	}
	
	private void addListeners() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				hideLine(e);
			}
		});
	}
	
	public void setCursorVisibility(boolean visible) {
		cursor = visible;
	}
	
}
