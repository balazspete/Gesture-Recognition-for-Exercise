package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.ComponentOrientation;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JSlider;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JLabel;

import javax.swing.JFileChooser;

import tools.InputParser;

import coordinates.Coordinate;
import coordinates.CoordinateImage;
import filters.CorrectingBufferedFilter;
import filters.Filter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * The main class of the GestureGrapher project
 * @author Balazs Pete
 *
 */
public class MainWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2704046350100047330L;

	private String windowTitle = "Gesture Grapher";

	private Vector<Coordinate> coordinates = null;
	private int verticalFactor = 1;
	
	private JPanel contentPane;
	private CoordinateCanvas canvas;
	
	private JLabel lblStartCoordData;
	private JLabel lblStartXData;
	private JLabel lblStartYData;
	private JLabel lblStartZData;
	private JLabel lblEndCoordData;
	private JLabel lblEndXData;
	private JLabel lblEndYData;
	private JLabel lblEndZData;
	private JLabel lblSaveFolder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				createImageAndLoad();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 430);
		
		setTitle(windowTitle);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		canvas = new CoordinateCanvas();
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				canvas.updateMouseLocation(e);
				updateLabels();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				canvas.updateMouseLocation(e);
				updateLabels();
			}
		});
		scrollPane.setViewportView(canvas);
		canvas.setLayout(new BoxLayout(canvas, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JLabel lblStartCoord = new JLabel("Start Coord:");
		panel.add(lblStartCoord);
		
		lblStartCoordData = new JLabel("0");
		lblStartCoordData.setForeground(Color.GRAY);
		panel.add(lblStartCoordData);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_1.setPreferredSize(new Dimension(10, 20));
		panel.add(rigidArea_1);
		
		JLabel lblStartX = new JLabel("X:");
		panel.add(lblStartX);
		
		lblStartXData = new JLabel("0.0");
		lblStartX.setLabelFor(lblStartXData);
		lblStartXData.setForeground(Color.GRAY);
		lblStartXData.setName("dataX");
		panel.add(lblStartXData);
		
		JLabel lblStartY = new JLabel("Y:");
		panel.add(lblStartY);
		
		lblStartYData = new JLabel("0.0");
		lblStartY.setLabelFor(lblStartYData);
		lblStartYData.setForeground(Color.GRAY);
		panel.add(lblStartYData);
		
		JLabel lblStartZ = new JLabel("Z:");
		panel.add(lblStartZ);
		
		lblStartZData = new JLabel("0.0");
		lblStartZ.setLabelFor(lblStartZData);
		lblStartZData.setForeground(Color.GRAY);
		panel.add(lblStartZData);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setPreferredSize(new Dimension(40, 20));
		panel.add(rigidArea);
		
		JLabel lblEndCoord = new JLabel("End Coord:");
		panel.add(lblEndCoord);
		
		lblEndCoordData = new JLabel("0");
		lblEndCoordData.setForeground(Color.GRAY);
		panel.add(lblEndCoordData);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_2.setPreferredSize(new Dimension(10, 20));
		panel.add(rigidArea_2);
		
		JLabel lblEndX = new JLabel("X:");
		panel.add(lblEndX);
		
		lblEndXData = new JLabel("0.0");
		lblEndX.setLabelFor(lblEndXData);
		lblEndXData.setForeground(Color.GRAY);
		panel.add(lblEndXData);
		
		JLabel lblEndY = new JLabel("Y:");
		panel.add(lblEndY);
		
		lblEndYData = new JLabel("0.0");
		lblEndY.setLabelFor(lblEndYData);
		lblEndYData.setForeground(Color.GRAY);
		panel.add(lblEndYData);
		
		JLabel lblEndZ = new JLabel("Z:");
		panel.add(lblEndZ);
		
		lblEndZData = new JLabel("0.0");
		lblEndZ.setLabelFor(lblEndZData);
		lblEndZData.setForeground(Color.GRAY);
		panel.add(lblEndZData);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(40, 20));
		panel.add(rigidArea_3);
		
		JSlider slider = new JSlider();
		slider.setVisible(false);
		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
//				int value = ((JSlider) e.getComponent()).getValue();
//				if(verticalFactor != value) {
//					verticalFactor = value;
//					createImageAndLoad();
//				}
			}
		});
		slider.setSnapToTicks(true);
		slider.setMaximum(10);
		slider.setMinimum(1);
		slider.setValue(1);
		panel.add(slider);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadAndProcessFile();
			}
		});
		panel_1.add(btnLoad);
		
		JButton btnSelectSaveFolder = new JButton("Select Save Folder");
		btnSelectSaveFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chooseSaveLocation();
			}
		});
		panel_1.add(btnSelectSaveFolder);
		
		JLabel lblSaveLocation = new JLabel("Save Location:");
		panel_1.add(lblSaveLocation);
		
		lblSaveFolder = new JLabel("Not Set");
		lblSaveLocation.setLabelFor(lblSaveFolder);
		lblSaveFolder.setForeground(Color.GRAY);
		panel_1.add(lblSaveFolder);
	}
	
	/**
	 * Load a file (to be chosen by JFileChooser) and process it
	 */
	private void loadAndProcessFile() {
		JFileChooser jfc = new JFileChooser();
		int resp = jfc.showOpenDialog(null);
		
		if(resp == JFileChooser.APPROVE_OPTION) {
			String path = jfc.getSelectedFile().getPath();
			this.setTitle(windowTitle + " - Opened File: \"" + path + "\"");
//			coordinates = InputParser.parse(path);
			Vector<Coordinate> temp = InputParser.parse(path);
			coordinates = new Vector<Coordinate>();
			Filter filter = new CorrectingBufferedFilter(9);
			while(temp.size() > 0) {
				Coordinate c = filter.filter(temp.remove(0));
				if(c != null)coordinates.add(c);
			}
//			System.out.println(temp.size() +" "+coordinates.size());
			createImageAndLoad();
		}
	}
	
	/**
	 * Create a CoordinateIMage and display it
	 */
	private void createImageAndLoad() {
		if(coordinates != null) {
			CoordinateImage img = new CoordinateImage(coordinates.size() * verticalFactor, canvas.getHeight(), coordinates, verticalFactor);
			canvas.replaceImage(img);
			canvas.paintComponents(canvas.getGraphics());
		}
	}
	
	/**
	 * Update the labels 
	 */
	private void updateLabels(){
		int lb = canvas.lowerBound;
		int ml = canvas.mouseLocation;
		
		if(lb == -1){
			lblStartCoordData.setText("" + (ml / verticalFactor));
			lblEndCoordData.setText("NaN");
		} else {
			lblStartCoordData.setText("" + (lb / verticalFactor));
			lblEndCoordData.setText("" + (ml / verticalFactor));
		}
		
		if(coordinates != null){
			if(lb == -1){
				Coordinate s = getCoord((int)(ml / verticalFactor));
				setStartLabels(s.getRoundX() + "", s.getRoundY() + "", s.getRoundZ() + "");
				setEndLabels("NaN", "NaN", "NaN");
			} else {
				Coordinate s = getCoord((int)(lb / verticalFactor));
				setStartLabels(s.getRoundX() + "", s.getRoundY() + "", s.getRoundZ() + "");
				Coordinate e = getCoord((int)(ml / verticalFactor));
				setEndLabels(e.getRoundX() + "", e.getRoundY() + "", e.getRoundZ() + "");
			}
		}
	}
	
	/**
	 * Set the values of the start labels
	 * @param x x-value
	 * @param y y-value
	 * @param z z-value
	 */
	private void setStartLabels(String x, String y, String z) {
		lblStartXData.setText(x);
		lblStartYData.setText(y);
		lblStartZData.setText(z);
	}
	
	/**
	 * Set the values of the end labels
	 * @param x x-value
	 * @param y y-value
	 * @param z z-value
	 */
	private void setEndLabels(String x, String y, String z) {
		lblEndXData.setText(x);
		lblEndYData.setText(y);
		lblEndZData.setText(z);
	}
	
	/**
	 * Get the coordinates at the specified location
	 * @param position the location at which the coordinates are needed
	 * @return the Coordinate value
	 */
	private Coordinate getCoord(int position) {
		return coordinates.get((int)(position / verticalFactor));
	}

	/**
	 * Method to choose the default save location
	 */
	private void chooseSaveLocation() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int resp = jfc.showSaveDialog(null);
		
		if(resp == JFileChooser.APPROVE_OPTION) {
			String dir = jfc.getSelectedFile().getPath();
			canvas.setSaveDirectory(dir);
			lblSaveFolder.setText(dir);
		}
	}
}
