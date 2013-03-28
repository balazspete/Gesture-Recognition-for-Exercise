package input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import tools.InputParser;
import coordinates.Coordinate;

public class GestureSimulator extends InputInterface {

	private static final int SLEEP_TIME = 20;
	
	private String filePath;
	private GestureSimulatorWindow window;
	
	/**
	 * Create an instance of FileInput
	 * @param filePath File from which coordinate data will be retrieved
	 */
	public GestureSimulator() {
		window = new GestureSimulatorWindow();
		window.setVisible(true);
	}
	
	/**
	 * Run the FileInput in a separate thread
	 */
	@Override
	public void run() {
		while (true) {
        	Coordinate c = window.getCoordinate();
        	fireEvent(c);
        	
        	try {
				sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				System.err.println("FileInput l.39: Could not 'sleep'");
			}
        }
	}
	
}
