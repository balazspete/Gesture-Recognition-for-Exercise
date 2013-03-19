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
import events.event.LimitToGestureEvent;
import events.listeners.LimitTogestureListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.JCheckBox;

public class GestureSimulatorWindow2 extends JFrame {

	private JPanel contentPane;
	private String filePath;
	
	private ConcurrentLinkedQueue<Coordinate> coordinateQueue = new ConcurrentLinkedQueue<Coordinate>();
	private boolean fromQueue = false;
	private Coordinate last = new Coordinate(0,0,0);
	private JLabel lblFilepath;
	
	private final double maxOffset = 2;
	private JButton btnStartGesture;
	private JButton btnLoadGestureFile;
	
	private List<LimitTogestureListener> listeners = new LinkedList<LimitTogestureListener>();
	private List<JButton> buttons = new LinkedList<JButton>();

	/**
	 * Create the frame.
	 */
	public GestureSimulatorWindow2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 474);
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
				changeButtonStatus(false);
			}
		});
		
		JButton btnLefttoright = new JButton("Left-To-Right");
		addbutton(btnLefttoright);
		
		JButton btnRighttoleft = new JButton("Right-To-Left");
		addbutton(btnRighttoleft);
		
		JButton btnUpwards = new JButton("Upwards");
		addbutton(btnUpwards);
		
		JButton btnDownwards = new JButton("Downwards");
		addbutton(btnDownwards);
		
		JButton btnStop = new JButton("Stop");
		addbutton(btnStop);
		
		JButton btnStart = new JButton("Start");
		addbutton(btnStart);
		
		JButton btnOne = new JButton("One");
		addbutton(btnOne);
		
		JButton btnTwo = new JButton("Two");
		addbutton(btnTwo);
		
		JButton btnThree = new JButton("Three");
		addbutton(btnThree);
		
		JButton btnCircle = new JButton("Circle");
		addbutton(btnCircle);
		
		JButton btnVerticalJump = new JButton("VerticalJump");
		addbutton(btnVerticalJump);
		
		JButton btnSquat = new JButton("Squat");
		addbutton(btnSquat);
		
		JButton btnLateralRaise = new JButton("LateralRaise");
		addbutton(btnLateralRaise);
		
		JButton btnBicepCurl = new JButton("BicepCurl");
		addbutton(btnBicepCurl);
		
		JButton btnOverheadPress = new JButton("OverheadPress");
		addbutton(btnOverheadPress);
		
		JButton btnPunching = new JButton("Punching");
		addbutton(btnPunching);
		
		JButton btnIsometricHold = new JButton("IsometricHold");
		addbutton(btnIsometricHold);
		
		JButton btnDeadLift = new JButton("Deadlift");
		addbutton(btnDeadLift);
		
		JButton btnBenchPress = new JButton("BenchPress");
		addbutton(btnBenchPress);
		
		JButton btnChestPress = new JButton("ChestPress");
		addbutton(btnChestPress);
		
		JCheckBox chckbxUniqueGesture = new JCheckBox("Unique gesture");
		chckbxUniqueGesture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String buttonname = "";
				if(((JCheckBox)arg0.getSource()).isSelected()) {
					buttonname = "__unique-gesture";
				} else {
					buttonname = "__no-unique-gesture";
				}
				LimitToGestureEvent e = new LimitToGestureEvent(this, buttonname);
				for(LimitTogestureListener l : listeners) {
					l.handle(e);
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFile)
							.addGap(18)
							.addComponent(lblFilepath))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLoadGestureFile)
								.addComponent(btnLefttoright)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnCircle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnThree, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnTwo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnOne, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnStart, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnStop, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnDownwards, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnUpwards, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnRighttoleft, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnChestPress, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBenchPress, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnDeadLift, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnIsometricHold, 0, 0, Short.MAX_VALUE)
								.addComponent(btnPunching, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnOverheadPress, 0, 0, Short.MAX_VALUE)
								.addComponent(btnBicepCurl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnLateralRaise, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnVerticalJump, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnStartGesture)
								.addComponent(btnSquat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(24))
						.addComponent(chckbxUniqueGesture))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFile)
						.addComponent(lblFilepath))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoadGestureFile)
						.addComponent(btnStartGesture))
					.addGap(1)
					.addComponent(chckbxUniqueGesture)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLefttoright)
						.addComponent(btnVerticalJump))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRighttoleft)
						.addComponent(btnSquat))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpwards)
						.addComponent(btnLateralRaise))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDownwards)
						.addComponent(btnBicepCurl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStop)
						.addComponent(btnOverheadPress))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStart)
						.addComponent(btnPunching))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOne)
						.addComponent(btnIsometricHold))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTwo)
						.addComponent(btnDeadLift))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnThree)
						.addComponent(btnBenchPress))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCircle)
						.addComponent(btnChestPress))
					.addContainerGap(17, Short.MAX_VALUE))
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
				changeButtonStatus(true);
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
		processFile(file);
		fromQueue = true;
		changeButtonStatus(false);
	}
	
	private void changeButtonStatus(boolean status) {

		btnStartGesture.setEnabled(status);
		btnLoadGestureFile.setEnabled(status);
		
		for(JButton b : buttons){
			b.setEnabled(status);
		}
	}
	
	private void processFile(File file) {
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
	
	private void handlebutton(String buttonname) {
		LimitToGestureEvent e = new LimitToGestureEvent(this, buttonname);
		for(LimitTogestureListener l : listeners) {
			l.handle(e);
		}
		processFile(new File(buttonname + ".data"));
		fromQueue = true;
	}
	
	private void addbutton(JButton button){
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				handlebutton(((JButton)arg0.getSource()).getText());
			}
		});
		buttons.add(button);
	}
	
	public void addListener(LimitTogestureListener l){
		listeners.add(l);
	}
}
