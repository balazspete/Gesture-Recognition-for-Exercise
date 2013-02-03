package input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import tools.InputParser;

import coordinates.Coordinate;

/**
 * A class extending the InputInterface to allow inputting the data from a file
 * @author Balazs Pete
 *
 */
public class FileInput extends InputInterface {

	private String filePath;
	
	/**
	 * Create an instance of FileInput
	 * @param filePath File from which coordinate data will be retrieved
	 */
	public FileInput(String filePath) {
		this.filePath = filePath;
	}
	

	/**
	 * Run the FileInput in a separate thread
	 */
	@Override
	public void run() {
		File file = getFile();
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
	        	Coordinate c = InputParser.getCoord(scanner.nextLine());
	        	fireEvent(c);
	        	
	        	try {
					sleep(400);
				} catch (InterruptedException e) {
					System.err.println("FileInput l.39: Could not 'sleep'");
				}
	        }
	        scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("FileInput l.44: Input file is invalid");
		}
	}
	
	/**
	 * Get the file defined by the input file path, or if null select file using a JFileChooser
	 * @return The selected file
	 */
	private File getFile() {
		if(filePath == null) {
			JFileChooser fc = new JFileChooser();
			int resp = fc.showSaveDialog(null);
			
			if(resp == JFileChooser.APPROVE_OPTION) {
				return fc.getSelectedFile();
			} else {
				return null;
			}
		}
		
		return new File(filePath);
	}
}
