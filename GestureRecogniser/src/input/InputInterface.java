package input;

import java.util.ArrayList;
import java.util.List;

import coordinates.Coordinate;

import events.event.InputEvent;
import events.listeners.InputListener;

/**
 * An abstract class defining the basic characteristics of an interface for inputs
 * @author Balazs Pete
 *
 */
public abstract class InputInterface extends Thread {

	private List<InputListener> listeners = new ArrayList<InputListener>();
	
	/**
	 * Add an InputListener to the interface
	 * @param listener Listener to be added
	 */
	public synchronized void addEventListener(InputListener listener)  {
		listeners.add(listener);
	}
	
	/**
	 * Remove a listener from the interface
	 * @param listener Listener to be removed
	 */
	public synchronized void removeEventListener(InputListener listener)   {
		listeners.remove(listener);
	}

	/**
	 * Method to be called to notify all listeners
	 */
	protected synchronized void fireEvent(Coordinate coordinate) {
	    InputEvent event = new InputEvent(this, coordinate);
	    for(InputListener l : listeners) {
	    	l.handleInput(event);
	    }
	}	
}
