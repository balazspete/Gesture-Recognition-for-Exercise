package input;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

import tools.InputParser;
import coordinates.Coordinate;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GestureSimulatorWindow extends JFrame {

	private JPanel contentPane;
	private String filePath;
	
	private ConcurrentLinkedQueue<Coordinate> coordinateQueue = new ConcurrentLinkedQueue<Coordinate>();
	private boolean fromQueue = false;
	private Coordinate last = new Coordinate(0,0,0);
	private JLabel lblFilepath;
	
	private final double maxOffset = 2;
	private JButton btnStartGesture;
	private JButton btnLoadGestureFile;

	/**
	 * Create the frame.
	 */
	public GestureSimulatorWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnLoadGestureFile = new JButton("Load gesture file");
		btnLoadGestureFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				processFile();
			}
		});
		
		JLabel lblFile = new JLabel("File:");
		
		lblFilepath = new JLabel("filepath");
		lblFilepath.setForeground(Color.GRAY);
		
		btnStartGesture = new JButton("Start Gesture");
		btnStartGesture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				fromQueue = true;
				btnStartGesture.setEnabled(false);
				btnLoadGestureFile.setEnabled(false);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFile)
							.addGap(18)
							.addComponent(lblFilepath))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnLoadGestureFile)
							.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
							.addComponent(btnStartGesture)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFilepath)
						.addComponent(lblFile))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoadGestureFile)
						.addComponent(btnStartGesture)))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public Coordinate getCoordinate() {
		if(fromQueue) {
			if(coordinateQueue.size() != 0){
				last = coordinateQueue.remove();
				return last;
			} else {
				fromQueue = false;
				btnLoadGestureFile.setEnabled(true);
				btnStartGesture.setEnabled(true);
			}
		}
		
		return offsetLast();
	}
	
	private Coordinate offsetLast() {
		Coordinate c = new Coordinate(
				last.getX() + Math.random() * maxOffset * 2 - maxOffset,
				last.getY() + Math.random() * maxOffset * 2 - maxOffset,
				last.getZ() + Math.random() * maxOffset * 2 - maxOffset
		);
		last = c;
		return c;
	}
	
	private void processFile() {
		File file = getFile();
		lblFilepath.setText(filePath);
		filePath = null;
		
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
	        	Coordinate c = InputParser.getCoord(scanner.nextLine());
	        	coordinateQueue.add(c);
	        }
	        scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("FileInput l.121: Input file is invalid");
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
