package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import coordinates.Coordinate;
import coordinates.CoordinateImage;

@SuppressWarnings("serial")
public class CoordinateCanvas extends JPanel {

	public int mouseLocation = -1;
	public int lowerBound = -1;
	
	private CoordinateImage image;
	private String saveDirectory = null;
	
	public CoordinateCanvas(){
		addListeners();
		image = new CoordinateImage();
	}
	
	public CoordinateCanvas(CoordinateImage image){
		addListeners();
		this.setSize(image.getWidth(), this.getHeight());
		this.image = image;
	}
	
	public void replaceImage(CoordinateImage img) {
		image = img;
		setSize(image.getWidth(), this.getHeight());
		setPreferredSize(this.getSize());
	}
	
	private void addListeners() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				hideLine(e);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				lowerBound = mouseLocation;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(image.getCoordinates() != null) snapCoords(lowerBound, mouseLocation);
				lowerBound = -1;
				updateMouseLocation(e);
			}
		});
	}
	
	private void hideLine(MouseEvent e){
		mouseLocation = -1;
		lowerBound = -1;
		this.paintAll(this.getGraphics());
	}
	
	public void updateMouseLocation(MouseEvent e){
		mouseLocation = e.getX();
		this.paintAll(this.getGraphics());
	}
	
	public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        g2.drawImage(image, null, 0, 0);

        if(lowerBound >= 0 && lowerBound < mouseLocation){
            Line2D line = new Line2D.Double(lowerBound, 0, lowerBound, this.getHeight());
            g2.setColor(Color.red);
            g2.setStroke(new BasicStroke(1));
            g2.draw(line);
        	
            Rectangle2D box = new Rectangle2D.Double(lowerBound, 0, mouseLocation-lowerBound, this.getHeight());
            g2.setColor(new Color(255, 0, 0, 70));
            g2.setStroke(new BasicStroke(0));
            g2.fill(box);
        }
        
        Line2D line = new Line2D.Double(mouseLocation, 0, mouseLocation, this.getHeight());
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(1));
        g2.draw(line);
	}
	
	private void snapCoords(int lowerBound, int upperBound){
		int factor = image.getVericalFactor();
		List<Coordinate> coords = image.getCoordinates().subList((int)(lowerBound/factor), (int)(upperBound/factor));
		
		if(coords.size() > 0) {
			String result = "";
			for(Coordinate c : coords) {
				result += c.toString() + "\n";
			}
			
			ResultsWindow rw = new ResultsWindow();
			rw.setSaveDirectory(saveDirectory);
			rw.setText(result);
			rw.setTitle("Coordinate Snap [ " + lowerBound + " -- " + upperBound + " ]");
			rw.setVisible(true);
		}
	}
	
	public void setSaveDirectory(String path) {
		this.saveDirectory = path;
	}
	
}
