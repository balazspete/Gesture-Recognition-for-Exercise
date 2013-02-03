package input;

import events.event.InputEvent;
import events.listeners.InputListener;

/**
 * A class to manage the input received by the application.
 * The method of the input is determined by the argument InputInterface,
 * which, depending on implementation, might receive data over the network, 
 * or read a file
 * @author Balazs Pete
 *
 */
public class InputManager {

	private InputInterface inputInterface;
	
	
	/**
	 * Create a new instance of an InputManager with specified inputs
	 * @param inputInterface the instance of InputInterface to use
	 */
	public InputManager(InputInterface inputInterface) {
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
				System.out.println(e.getCoordinate().toString());
			}
		});
	}
	
}
