package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.Box;

import coordinates.CoordinateSeries;
import coordinates.GraphData;
import coordinates.GraphingImage;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Savepoint;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JSpinner;
import javax.swing.SpringLayout;
import java.awt.Font;
import javax.swing.JCheckBox;

import exceptions.InsufficentModelDataError;
import exceptions.InvalidDimensionException;

import tools.CreateModel;
import tools.InputParser;

import model.state.FuzzyPoint;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Main class for the gestureCreator project
 * @author Balazs Pete
 *
 */
public class MainWindow {

	private JFrame frame;
	private GraphData graphData = new GraphData();
	private GraphingCanvas canvas;
	private JScrollPane scrollPane;
	private JPanel fileList;
	private JTabbedPane tabbedPane;
	private JSpinner spinnerVerticalDisplacement;
	private JSpinner spinnerHorizontalDisplacement;
	private JSpinner spinnerHorizontalScaling;
	private JLabel lblVerticalScaling;
	private JSpinner spinnerVerticalScaling;
	private JLabel lblSelectedFilePath;
	
	private StateCreator stateCreator = null;
	
	private Vector<FuzzyPoint> statePoints = new Vector<FuzzyPoint>();
	private JPanel stateList;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				canvas.resizeImage();
			}
		});
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		canvas = new GraphingCanvas(new GraphingImage(), statePoints);
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(stateCreator == null || !stateCreator.isShowing()) {
					stateCreator = new StateCreator(canvas, statePoints, (((double)e.getX()) / canvas.getWidth()) * 100);
					stateCreator.setVisible(true);
				} else {
					canvas.saveMouseYLocation();
				}
			}
		});
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				canvas.updateMouseLocation(e);
			}
		});
		scrollPane_1.setViewportView(canvas);
		
		Box horizontalBox = Box.createHorizontalBox();
		panel_1.add(horizontalBox);
		
		JLabel lblX = new JLabel("X: ");
		horizontalBox.add(lblX);
		
		JLabel lblXData = new JLabel("0.0");
		lblXData.setForeground(Color.GRAY);
		horizontalBox.add(lblXData);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox.add(rigidArea);
		
		JLabel lblY = new JLabel("Y: ");
		horizontalBox.add(lblY);
		
		JLabel lblYData = new JLabel("0.0");
		lblYData.setForeground(Color.GRAY);
		horizontalBox.add(lblYData);
		
		Component rigidArea2 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox.add(rigidArea2);
		
		JLabel lblZ = new JLabel("Z: ");
		horizontalBox.add(lblZ);
		
		JLabel lblZData = new JLabel("0.0");
		lblZData.setForeground(Color.GRAY);
		horizontalBox.add(lblZData);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane);
		
		JPanel settingsPanel = new JPanel();
		tabbedPane.addTab("Settings\n", null, settingsPanel, null);
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		settingsPanel.add(scrollPane_2);
		
		JPanel controlsList = new JPanel();
		scrollPane_2.setViewportView(controlsList);
		SpringLayout sl_controlsList = new SpringLayout();
		controlsList.setLayout(sl_controlsList);
		
		JLabel lblVerticalDisplacement = new JLabel("Vertical");
		controlsList.add(lblVerticalDisplacement);
		
		spinnerVerticalDisplacement = new JSpinner();
		sl_controlsList.putConstraint(SpringLayout.NORTH, spinnerVerticalDisplacement, -6, SpringLayout.NORTH, lblVerticalDisplacement);
		sl_controlsList.putConstraint(SpringLayout.WEST, spinnerVerticalDisplacement, 99, SpringLayout.EAST, lblVerticalDisplacement);
		controlsList.add(spinnerVerticalDisplacement);
		
		JLabel lblHorizontalDisplacement = new JLabel("Horizontal");
		sl_controlsList.putConstraint(SpringLayout.NORTH, lblVerticalDisplacement, 18, SpringLayout.SOUTH, lblHorizontalDisplacement);
		sl_controlsList.putConstraint(SpringLayout.NORTH, lblHorizontalDisplacement, 61, SpringLayout.NORTH, controlsList);
		sl_controlsList.putConstraint(SpringLayout.WEST, lblHorizontalDisplacement, 10, SpringLayout.WEST, controlsList);
		sl_controlsList.putConstraint(SpringLayout.WEST, lblVerticalDisplacement, 0, SpringLayout.WEST, lblHorizontalDisplacement);
		controlsList.add(lblHorizontalDisplacement);
		
		spinnerHorizontalDisplacement = new JSpinner();
		sl_controlsList.putConstraint(SpringLayout.NORTH, spinnerHorizontalDisplacement, -6, SpringLayout.NORTH, lblHorizontalDisplacement);
		sl_controlsList.putConstraint(SpringLayout.WEST, spinnerHorizontalDisplacement, 0, SpringLayout.WEST, spinnerVerticalDisplacement);
		sl_controlsList.putConstraint(SpringLayout.EAST, spinnerHorizontalDisplacement, -32, SpringLayout.EAST, controlsList);
		controlsList.add(spinnerHorizontalDisplacement);
		
		JLabel lblSelectedFile = new JLabel("Selected file: ");
		sl_controlsList.putConstraint(SpringLayout.NORTH, lblSelectedFile, 10, SpringLayout.NORTH, controlsList);
		sl_controlsList.putConstraint(SpringLayout.WEST, lblSelectedFile, 10, SpringLayout.WEST, controlsList);
		controlsList.add(lblSelectedFile);
		
		lblSelectedFilePath = new JLabel("No file selected");
		lblSelectedFilePath.setForeground(Color.GRAY);
		sl_controlsList.putConstraint(SpringLayout.WEST, lblSelectedFilePath, 6, SpringLayout.EAST, lblSelectedFile);
		sl_controlsList.putConstraint(SpringLayout.SOUTH, lblSelectedFilePath, 0, SpringLayout.SOUTH, lblSelectedFile);
		controlsList.add(lblSelectedFilePath);
		
		JLabel lblDisplacement = new JLabel("Displacement");
		sl_controlsList.putConstraint(SpringLayout.WEST, lblDisplacement, 0, SpringLayout.WEST, lblVerticalDisplacement);
		sl_controlsList.putConstraint(SpringLayout.SOUTH, lblDisplacement, -6, SpringLayout.NORTH, lblHorizontalDisplacement);
		lblDisplacement.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		controlsList.add(lblDisplacement);
		
		JLabel lblPx1 = new JLabel("px");
		sl_controlsList.putConstraint(SpringLayout.EAST, lblPx1, -10, SpringLayout.EAST, controlsList);
		sl_controlsList.putConstraint(SpringLayout.SOUTH, lblPx1, 0, SpringLayout.SOUTH, lblHorizontalDisplacement);
		controlsList.add(lblPx1);
		
		JLabel lblPx2 = new JLabel("px");
		sl_controlsList.putConstraint(SpringLayout.EAST, spinnerVerticalDisplacement, -6, SpringLayout.WEST, lblPx2);
		sl_controlsList.putConstraint(SpringLayout.NORTH, lblPx2, 18, SpringLayout.SOUTH, lblPx1);
		sl_controlsList.putConstraint(SpringLayout.EAST, lblPx2, 0, SpringLayout.EAST, lblPx1);
		controlsList.add(lblPx2);
		
		JLabel lblScaling = new JLabel("Scaling");
		sl_controlsList.putConstraint(SpringLayout.WEST, lblScaling, 0, SpringLayout.WEST, lblVerticalDisplacement);
		lblScaling.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		controlsList.add(lblScaling);
		
		JLabel lblHorizontalScaling = new JLabel("Horizontal");
		sl_controlsList.putConstraint(SpringLayout.NORTH, lblHorizontalScaling, 149, SpringLayout.NORTH, controlsList);
		sl_controlsList.putConstraint(SpringLayout.SOUTH, lblScaling, -6, SpringLayout.NORTH, lblHorizontalScaling);
		sl_controlsList.putConstraint(SpringLayout.WEST, lblHorizontalScaling, 0, SpringLayout.WEST, lblVerticalDisplacement);
		controlsList.add(lblHorizontalScaling);
		
		JLabel lblPx3 = new JLabel("px");
		sl_controlsList.putConstraint(SpringLayout.SOUTH, lblPx3, 0, SpringLayout.SOUTH, lblHorizontalScaling);
		sl_controlsList.putConstraint(SpringLayout.EAST, lblPx3, 0, SpringLayout.EAST, lblPx1);
		controlsList.add(lblPx3);
		
		spinnerHorizontalScaling = new JSpinner(new SpinnerNumberModel(1, 0, 5, 0.01));
		sl_controlsList.putConstraint(SpringLayout.NORTH, spinnerHorizontalScaling, -6, SpringLayout.NORTH, lblHorizontalScaling);
		sl_controlsList.putConstraint(SpringLayout.WEST, spinnerHorizontalScaling, 0, SpringLayout.WEST, spinnerVerticalDisplacement);
		sl_controlsList.putConstraint(SpringLayout.EAST, spinnerHorizontalScaling, -6, SpringLayout.WEST, lblPx3);
		JSpinner.NumberEditor editor = (JSpinner.NumberEditor)spinnerHorizontalScaling.getEditor();  
        DecimalFormat format = editor.getFormat();  
        format.setMinimumFractionDigits(2);
		controlsList.add(spinnerHorizontalScaling);
		
		spinnerVerticalScaling = new JSpinner(new SpinnerNumberModel(1, 0, 5, 0.01));
		sl_controlsList.putConstraint(SpringLayout.NORTH, spinnerVerticalScaling, 6, SpringLayout.SOUTH, spinnerHorizontalScaling);
		editor = (JSpinner.NumberEditor)spinnerVerticalScaling.getEditor();  
        format = editor.getFormat();  
        format.setMinimumFractionDigits(2);
		controlsList.add(spinnerVerticalScaling);
		
		JLabel lblPx4 = new JLabel("px");
		sl_controlsList.putConstraint(SpringLayout.EAST, spinnerVerticalScaling, -6, SpringLayout.WEST, lblPx4);
		sl_controlsList.putConstraint(SpringLayout.NORTH, lblPx4, 18, SpringLayout.SOUTH, lblPx3);
		sl_controlsList.putConstraint(SpringLayout.WEST, lblPx4, 0, SpringLayout.WEST, lblPx1);
		controlsList.add(lblPx4);
		
		lblVerticalScaling = new JLabel("Vertical");
		sl_controlsList.putConstraint(SpringLayout.WEST, spinnerVerticalScaling, 99, SpringLayout.EAST, lblVerticalScaling);
		sl_controlsList.putConstraint(SpringLayout.NORTH, lblVerticalScaling, 6, SpringLayout.NORTH, spinnerVerticalScaling);
		sl_controlsList.putConstraint(SpringLayout.WEST, lblVerticalScaling, 0, SpringLayout.WEST, lblVerticalDisplacement);
		controlsList.add(lblVerticalScaling);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateSeriesSettings(lblSelectedFilePath.getText());
			}
		});
		sl_controlsList.putConstraint(SpringLayout.WEST, btnApply, 0, SpringLayout.WEST, lblVerticalDisplacement);
		sl_controlsList.putConstraint(SpringLayout.SOUTH, btnApply, -10, SpringLayout.SOUTH, controlsList);
		controlsList.add(btnApply);
		
		JButton btnDiscardChanges = new JButton("Discard changes");
		btnDiscardChanges.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateSeriesSettings(lblSelectedFilePath.getText());
			}
		});
		sl_controlsList.putConstraint(SpringLayout.NORTH, btnDiscardChanges, 0, SpringLayout.NORTH, btnApply);
		sl_controlsList.putConstraint(SpringLayout.WEST, btnDiscardChanges, 0, SpringLayout.WEST, lblSelectedFilePath);
		controlsList.add(btnDiscardChanges);
		
		JPanel filesPanel = new JPanel();
		tabbedPane.addTab("Files", null, filesPanel, null);
		filesPanel.setLayout(new BoxLayout(filesPanel, BoxLayout.Y_AXIS));
		
		JButton btnNewButton = new JButton("Load file...");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadFiles();
			}
		});
		filesPanel.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		filesPanel.add(scrollPane);
		
		fileList = new JPanel();
		scrollPane.setViewportView(fileList);
		fileList.setLayout(new BoxLayout(fileList, BoxLayout.Y_AXIS));
		
		JPanel statesPanel = new JPanel();
		statesPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				canvas.setCursorVisibility(true);
			}
			@Override
			public void componentHidden(ComponentEvent e) {
				canvas.setCursorVisibility(false);
			}
		});
		tabbedPane.addTab("States", null, statesPanel, null);
		SpringLayout sl_statesPanel = new SpringLayout();
		statesPanel.setLayout(sl_statesPanel);
		
		JCheckBox chckbxDisplayData = new JCheckBox("Display data");
		chckbxDisplayData.setSelected(true);
		statesPanel.add(chckbxDisplayData);
		
		JLabel lblDisplay = new JLabel("Display");
		lblDisplay.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		sl_statesPanel.putConstraint(SpringLayout.NORTH, chckbxDisplayData, 6, SpringLayout.SOUTH, lblDisplay);
		sl_statesPanel.putConstraint(SpringLayout.WEST, chckbxDisplayData, 0, SpringLayout.WEST, lblDisplay);
		sl_statesPanel.putConstraint(SpringLayout.NORTH, lblDisplay, 10, SpringLayout.NORTH, statesPanel);
		sl_statesPanel.putConstraint(SpringLayout.WEST, lblDisplay, 10, SpringLayout.WEST, statesPanel);
		statesPanel.add(lblDisplay);
		
		JCheckBox chckbxDisplayEnsemble = new JCheckBox("Display ensemble");
		chckbxDisplayEnsemble.setSelected(true);
		sl_statesPanel.putConstraint(SpringLayout.NORTH, chckbxDisplayEnsemble, 6, SpringLayout.SOUTH, chckbxDisplayData);
		sl_statesPanel.putConstraint(SpringLayout.WEST, chckbxDisplayEnsemble, 0, SpringLayout.WEST, chckbxDisplayData);
		statesPanel.add(chckbxDisplayEnsemble);
		
		JLabel lblStates = new JLabel("States");
		lblStates.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		sl_statesPanel.putConstraint(SpringLayout.NORTH, lblStates, 19, SpringLayout.SOUTH, chckbxDisplayEnsemble);
		sl_statesPanel.putConstraint(SpringLayout.WEST, lblStates, 10, SpringLayout.WEST, statesPanel);
		statesPanel.add(lblStates);
		
		JButton btnAddNewState = new JButton("Add");
		btnAddNewState.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addNewState();
			}
		});
		sl_statesPanel.putConstraint(SpringLayout.NORTH, btnAddNewState, 6, SpringLayout.SOUTH, lblStates);
		sl_statesPanel.putConstraint(SpringLayout.WEST, btnAddNewState, 0, SpringLayout.WEST, chckbxDisplayData);
		statesPanel.add(btnAddNewState);
		
		JPanel panel_2 = new JPanel();
		sl_statesPanel.putConstraint(SpringLayout.NORTH, panel_2, 6, SpringLayout.SOUTH, btnAddNewState);
		sl_statesPanel.putConstraint(SpringLayout.WEST, panel_2, 10, SpringLayout.WEST, statesPanel);
		sl_statesPanel.putConstraint(SpringLayout.SOUTH, panel_2, -10, SpringLayout.SOUTH, statesPanel);
		sl_statesPanel.putConstraint(SpringLayout.EAST, panel_2, 281, SpringLayout.WEST, statesPanel);
		statesPanel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panel_2.add(scrollPane_3);
		
		stateList = new JPanel();
		scrollPane_3.setViewportView(stateList);
		stateList.setLayout(new BoxLayout(stateList, BoxLayout.Y_AXIS));
		
		JButton btnRefreshList = new JButton("Refresh");
		btnRefreshList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listStates();
			}
		});
		sl_statesPanel.putConstraint(SpringLayout.NORTH, btnRefreshList, 0, SpringLayout.NORTH, btnAddNewState);
		sl_statesPanel.putConstraint(SpringLayout.EAST, btnRefreshList, 0, SpringLayout.EAST, panel_2);
		statesPanel.add(btnRefreshList);
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createAndSaveModel();
			}
		});
		sl_statesPanel.putConstraint(SpringLayout.NORTH, btnSave, 0, SpringLayout.NORTH, btnAddNewState);
		sl_statesPanel.putConstraint(SpringLayout.WEST, btnSave, 6, SpringLayout.EAST, btnAddNewState);
		statesPanel.add(btnSave);
	}
	
	/**
	 * Load fires containing recorded gestures
	 */
	private void loadFiles() {
		if(graphData.getKeys().size() == 0) canvas.replaceImage(new GraphingImage(canvas.getWidth(), canvas.getHeight(), graphData, 1));
		
		JFileChooser jfc = new JFileChooser();
		jfc.setMultiSelectionEnabled(true);
		int resp = jfc.showOpenDialog(null);
		
		if(resp == JFileChooser.APPROVE_OPTION) {
			File[] files = jfc.getSelectedFiles();
			
			for(File file : files) {
				String path = file.getPath();
				graphData.add(path.intern(), new CoordinateSeries(InputParser.parse(path)));
				Box b = createFileBox(file);
				fileList.add(b);
				fileList.paintComponents(fileList.getGraphics());
			}

			reloadImage();
		}
	}
	
	/**
	 * Edit the display parameters of a gesture file
	 * @param filePath path to the file
	 */
	private void editFile(String filePath) {
		graphData.removeSeriesHighlight();
		
		tabbedPane.setSelectedIndex(0);
		lblSelectedFilePath.setText(filePath);
		lblSelectedFilePath.setToolTipText(filePath);
		
		CoordinateSeries series = graphData.get(filePath);
		series.setHighlight(true);
		
		spinnerHorizontalScaling.setValue(series.getHorizontalScaling());
		spinnerVerticalScaling.setValue(series.getVerticalScaling());
		
		spinnerHorizontalDisplacement.setValue(series.getHorisontalOffset());
		spinnerVerticalDisplacement.setValue(series.getVerticalOffset());
	}
	
	/**
	 * Update the settings of a CoordinateSeries based on values on the screen
	 * @param seriesFilePath path of the gesture file
	 */
	private void updateSeriesSettings(String seriesFilePath) {
		CoordinateSeries series = graphData.get(seriesFilePath);
		
		Object value = spinnerHorizontalDisplacement.getValue();
		series.setHorizontalOffset	(value instanceof Integer ? (int) value : (double) value);
		value = spinnerVerticalDisplacement.getValue();
		series.setVerticalOffset	(value instanceof Integer ? (int) value : (double) value);
		
		value = spinnerHorizontalScaling.getValue();
		series.setHorizontalScaling	(value instanceof Integer ? (int) value : (double) value);
		value = spinnerVerticalScaling.getValue();
		series.setVerticalScaling	(value instanceof Integer ? (int) value : (double) value);
		
		reloadImage();
	}
	
	/**
	 * Create an entry of the file on the screen
	 * @param file file
	 * @return the created entry
	 */
	private Box createFileBox(File file) {
		Box box = Box.createHorizontalBox();
		JLabel l0 = new JLabel("Edit  ");
		l0.setForeground(Color.blue);
		l0.setToolTipText(file.getPath());
		l0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editFile(((JLabel) e.getSource()).getToolTipText());
			}
		});
		box.add(l0);
		
		JLabel l1 = new JLabel(file.getName());
		l1.setToolTipText(file.getPath());
		box.add(l1);
		/*
		JLabel l2 = new JLabel("remove");
		l2.setForeground(Color.blue);
		l2.setLabelFor(l1);
		l2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JLabel label = (JLabel) e.getSource();
				String s = ((JLabel) label.getLabelFor()).getToolTipText();
				System.out.println(Arrays.toString(graphData.getKeys().toArray()));
				System.out.println(s);
				graphData.remove(s.intern());
				frame.remove(label.getParent());
				//fileList.paintComponents(fileList.getGraphics());
				reloadImage();
			}
		});
		box.add(l2);
		*/
		return box;
	}
	
	/**
	 * Reload the generated images
	 */
	private void reloadImage() {
		canvas.reloadImage();
		canvas.paintComponent(canvas.getGraphics());
	}
	
	/**
	 * Add a new state to the list of PsuedoStates
	 */
	private void addNewState() {
		stateCreator = new StateCreator(canvas, statePoints);
		stateCreator.setVisible(true);
	}
	
	/**
	 * List all PsuedoStates
	 */
	private void listStates() {
		stateList.removeAll();
		for(FuzzyPoint point : statePoints) {
			String s = "";
			try {
				s += point.getX().toString() + ",";
			} catch (InvalidDimensionException e) {
				s += ",,";
			}
			try {
				s += point.getY().toString() + ",";
			} catch (InvalidDimensionException e) {
				s += ",,";
			}
			try {
				s += point.getZ().toString();
			} catch (InvalidDimensionException e) {
				s += ",";
			} finally {
				if(s.length() == 0) s = "state...";
			}
			
			stateList.add(new JLabel(s));
		}
	}
	
	/**
	 * Create and save the model
	 */
	private void createAndSaveModel() {
		statePoints.capacity();
		JFileChooser jfc = new JFileChooser();
		jfc.showSaveDialog(null);
		int resp = jfc.showSaveDialog(null);
		
		if(resp == JFileChooser.APPROVE_OPTION) {
			try{
				CreateModel.save(jfc.getSelectedFile(), CreateModel.create(statePoints));
				
			} catch (InsufficentModelDataError e) {
				System.err.println("Not enough states to generate a model...");
			}
		}
	}
}
