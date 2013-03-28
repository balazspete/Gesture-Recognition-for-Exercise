package analysis;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import coordinates.Coordinate;
import coordinates.CoordinateSeries;
import events.event.AcceptingStateEvent;
import gestures.Gesture;

/**
 * A class describing gesture statistics
 * @author Balazs Pete
 *
 */
public class GestureStats extends CoordinateSeries {

	protected Gesture gesture;
	protected long time = -1;
	protected long startTime = -1;
	
	public GestureStats(Gesture gesture) {
		super();
		this.gesture = gesture;
	}
	
	/**
	 * Create a new instance of GestureStats 
	 * @param series The List of Coordinates
	 * @param gesture the corresponding gesture
	 */
	public GestureStats(List<Coordinate> series, Gesture gesture) {
		super(series);
		this.gesture = gesture;
	}
	
	/**
	 * Set the corresponding Gesture
	 * @param gesture The gesture
	 */
	public void setGesture(Gesture gesture) {
		this.gesture = gesture;
	}
	
	/**
	 * Get the corresponding Gesture
	 * @return The Gesture
	 */
	public Gesture getGesture() {
		return gesture;
	}
	
	/**
	 * Set the length of the gesture in time in milliseconds
	 * @param time The length of the Gesture
	 */
	public void setTime(long time) {
		this.time = time;
	}
	
	/**
	 * Get the gesture time
	 * @return The length of the gesture in time in milliseconds
	 */
	public long getTime() {
		return time;
	}
	
	
	/**
	 * Get the start time of the Gesture (UNIX time)
	 * @return the start time
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * Set the start time of the Gesture (UNIX time)
	 * @param startTime the start time to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * Create a GestureStats object from data contained in an AcceptingStateEvent  
	 * @param e The acceptingStateEvent containing the data
	 * @return The Gesture statistics
	 */
	public static GestureStats create(AcceptingStateEvent e) {
		GestureStats gsts = e.getStateCoordinates().convert(e.getGesture());

		Vector<Coordinate> coordinates = gsts.getCoordinates();
		
		long startTime = coordinates.get(0).getTime();
		gsts.setStartTime(startTime);
		
		long time = coordinates.get(coordinates.size()-1).getTime() - startTime;
		gsts.setTime(time);
		
		return gsts;
	}
	
	
}
