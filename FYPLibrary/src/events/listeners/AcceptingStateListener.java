package events.listeners;

import events.event.AcceptingStateEvent;

/**
 * Interface for an AcceptingStateListener
 * @author Balazs Pete
 *
 */
public interface AcceptingStateListener {

	/**
	 * Method to handle the AcceptingStateEvent
	 * @param e The AcceptingStateEvent to handle
	 */
	public void handleAcceptingState(AcceptingStateEvent e);
	
}
