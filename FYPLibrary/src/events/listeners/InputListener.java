package events.listeners;

import events.event.InputEvent;

/**
 * Interface for an input Listener
 * @author Balazs Pete
 *
 */
public interface InputListener {
	
	/**
	 * Method to handle the InputEvent
	 * @param e Input event to handle
	 */
	public void handleInput(InputEvent e);
	
}
