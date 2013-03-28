package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Vector;

import javax.swing.JFileChooser;

import gestures.Gesture;

/**
 * An OutputInterface to buffer output and write to a specified file upon shutdown
 * @author BalazsPete
 *
 */
public class BufferedFileOutput extends Thread implements OutputInterface {
	
	/**
	 * A representation of the output created by the BufferedFileOutput
	 * @author Balazs Pete
	 *
	 */
	private class Output {
		
		private Gesture gesture;
		private long time;
		
		/**
		 * Create a new instance of Output
		 * @param gesture The gesture represented
		 * @param time The time of the gesture
		 */
		public Output(Gesture gesture, long time) {
			this.gesture = gesture;
			this.time = time;
		}
		
		/**
		 * Get the string representation of the Object
		 */
		public String toString() {
			return gesture.toString() + "      " + time;
		}
	}
	
	private Vector<Output> buffer;
	private File file;

	/**
	 * Create a new instance of BufferedFileOutput with a specified output file path
	 * @param fileName
	 */
	public BufferedFileOutput(String filePath) {
		buffer = new Vector<Output>();
		file = new File(filePath);
		
		// Add Shutdown hook
		Runtime.getRuntime().addShutdownHook(this);
	}

	@Override
	public void handleGesture(Gesture gesture, long time) {
		buffer.add(new Output(gesture, time));
	}
	
	@Override
	public void run() {
	    boolean success = false;
	    while(!success) {
		    try {
		    	success = writeToFile();
			} catch (IOException e) {
				JFileChooser fc = new JFileChooser();
				int resp = fc.showSaveDialog(null);
				
				if(resp == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
				}
		    }
	    }
	}
	
	/**
	 * Write the buffer to the output file
	 * @return True if write is successful
	 * @throws IOException IOException thrown in case of IO error
	 */
	private boolean writeToFile() throws IOException {
	    Writer output = new BufferedWriter(new FileWriter(file));
		for(Output o : buffer) {
			output.write(o.toString() + "\n");
		}
		output.close();
		
		return true;
	}

}
