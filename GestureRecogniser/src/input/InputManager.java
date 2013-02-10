package input;

import java.util.ArrayList;
import java.util.List;

import coordinates.Coordinate;
import events.event.CoordinateEvent;
import events.event.InputEvent;
import events.listeners.CoordinateListener;
import events.listeners.InputListener;
import filters.Filter;

/**
 * A class to manage the input received by the application.
 * The method of the input is determined by the argument InputInterface,
 * which, depending on implementation, might receive data over the network, 
 * or read a file
 * @author Balazs Pete
 *
 */
public class InputManager {
	
	private List<CoordinateListener> listeners = new ArrayList<CoordinateListener>();

	private InputInterface inputInterface;
	private Filter<Coordinate> filter;
	
	
	/**
	 * Create a new instance of an InputManager with specified inputs
	 * @param inputInterface the instance of InputInterface to use
	 */
	public InputManager(InputInterface inputInterface, Filter<Coordinate> filter) {
		this.filter = filter;
		this.inputInterface = inputInterface;
		
		setup();
		
		inputInterface.start();
	}
	
	/**
	 * Set up the instance
	 */
	private void setup() {
		inputInterface.addEventListener(new InputListener(){
			@Override
			public void handleInput(InputEvent e) {
				Coordinate c = filter.filter(e.getCoordinate());
				if(c != null) fireEvent(c);
			}
		});
	}
	
	/**
	 * Add an CoordinateListener to the interface
	 * @param listener Listener to be added
	 */
	public synchronized void addEventListener(CoordinateListener listener)  {
		listeners.add(listener);
	}
	
	/**
	 * Remove a listener from the interface
	 * @param listener Listener to be removed
	 */
	public synchronized void removeEventListener(CoordinateListener listener)   {
		listeners.remove(listener);
	}

	/**
	 * Method to be called to notify all listeners
	 */
	protected synchronized void fireEvent(Coordinate coordinate) {
	    CoordinateEvent event = new CoordinateEvent(this, coordinate);
	    for(CoordinateListener l : listeners) {
	    	l.handleCoordinate(event);
	    }
	}
}
