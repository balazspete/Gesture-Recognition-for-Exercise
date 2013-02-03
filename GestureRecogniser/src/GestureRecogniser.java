import input.FileInput;
import input.InputInterface;
import input.InputManager;

/**
 * The main class of the gestureRecogniser project
 * @author Balazs Pete
 *
 */
public class GestureRecogniser {

	/**
	 * Run the application
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestureRecogniser gr = new GestureRecogniser();
		gr.start();
	}
	
	/****************************************/
	
	private InputManager inputManager;
	
	
	
	public GestureRecogniser() {
		
	}
	
	public void start() {
		// Change InputInterface depending on input requirements
		InputInterface input = new FileInput(null);
		inputManager = new InputManager(input); 
		
		
	}

}
