package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	
	public GraphingCanvas() {
		image = new GraphingImage();
		simage = new StateImage();
	}
	
	public GraphingCanvas(GraphingImage image) {
		this.setSize(image.getWidth(), image.getHeight());
		this.image = image;
		this.simage = new StateImage(getWidth(), getHeight());
	}
	
	public GraphingCanvas(GraphingImage image, Vector<FuzzyPoint> points) {
		this.setSize(image.getWidth(), image.getHeight());
		this.image = image;
		this.simage = new StateImage(getWidth(), getHeight(), points);
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
	}

	public StateImage getSimage() {
		return simage;
	}
	
}
