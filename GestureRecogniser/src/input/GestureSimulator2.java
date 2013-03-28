package input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

import tools.InputParser;
import coordinates.Coordinate;

import events.listeners.LimitTogestureListener;

public class GestureSimulator2 extends InputInterface {

	private static final int SLEEP_TIME = 20;
	
	private String filePath;
	private GestureSimulatorWindow2 window;
	
	/**
	 * Create an instance of FileInput
	 * @param filePath File from which coordinate data will be retrieved
	 */
	public GestureSimulator2() {
		window = new GestureSimulatorWindow2();
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
	
	public void addListener(LimitTogestureListener l){
		window.addListener(l);
	}
	
}
