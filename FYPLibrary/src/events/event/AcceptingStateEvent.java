package events.event;

import java.util.EventObject;

/**
 * A class to describe an event generated when a FiniteStateMachine has reached an accepting state
 * @author Balazs Pete
 *
 */
public class AcceptingStateEvent extends EventObject {

	/**
	 * Automatically generated serialVersionUID
	 */
	private static final long serialVersionUID = -3796844397739483225L;

	/**
	 * Create a new instance of the AcceptingStateEvent object
	 * @param source The source of the event
	 */
	public AcceptingStateEvent(Object source) {
		super(source);
	}

}
