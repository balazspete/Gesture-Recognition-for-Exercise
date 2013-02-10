package ui.monitor.input;

import input.InputManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

import coordinates.Coordinate;
import coordinates.CoordinateImage;

import events.event.AcceptingStateEvent;
import events.event.CoordinateEvent;
import events.listeners.CoordinateListener;
import javax.swing.JToggleButton;

import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * A JFrame to display and monitor input Coordinate data
 * @author Balazs Pete
 *
 */
public class InputMonitor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8902258483370822829L;
	
	private JPanel contentPane;
	private InputCanvas canvas;
	
	private Vector<Coordinate> coordinates = new Vector<Coordinate>();

	private JScrollPane scrollPane;
	private JToggleButton tglbtnAutoScroll;
	
	private int coordinateCounter = 0;

	private JLabel lblNumberOfInputsCouter;
	private JPanel panel_1;

	private JPanel statesDisplay;

	private JScrollPane scrollPane_1;

	
	/**
	 * Create the frame with a specified InputManager.
	 */
	public InputMonitor(InputManager inputManager) {
		initialize();
		
		addListener(inputManager);
	}
	
	/**
	 * Initialize the components
	 */
	private void initialize() {
		setTitle("GestureRecogniser: Input Monitor");
		setBounds(100, 100, 600, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JLabel lblNumberOfInputs = new JLabel("Number of inputs: ");
		
		lblNumberOfInputsCouter = new JLabel("0000");
		lblNumberOfInputsCouter.setForeground(Color.GRAY);
		
		tglbtnAutoScroll = new JToggleButton("Auto Scroll");
		tglbtnAutoScroll.setSelected(true);
		
		panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNumberOfInputs)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNumberOfInputsCouter)
					.addPreferredGap(ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
					.addComponent(tglbtnAutoScroll))
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfInputs)
						.addComponent(lblNumberOfInputsCouter)
						.addComponent(tglbtnAutoScroll))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane_1);
		
		statesDisplay = new JPanel();
		statesDisplay.setBackground(Color.black);
		scrollPane_1.setViewportView(statesDisplay);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				syncHorizontalScrollBars();
			}
		});
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panel.add(scrollPane);
		
		canvas = new InputCanvas();
		scrollPane.setViewportView(canvas);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * Handle the input AcceptinStateEvent
	 * @param e The AcceptingStateEvent to handle
	 */
	public void handleAcceptingState(AcceptingStateEvent e) {
		canvas.addAcceptingStatePoint(coordinates.size());
		paintComponents(getGraphics());
	}
	
	/**
	 * Add a CoordinateListener
	 * @param inputManager The input manager
	 */
	private void addListener(InputManager inputManager) {
		inputManager.addEventListener(new CoordinateListener() {
			@Override
			public void handleCoordinate(CoordinateEvent e) {
				addCoordinate(e.getCoordinate());
			}
		});
	}
	
	/**
	 * Add and handle a new Coordinate
	 * @param coordinate The Coordinate to be handled
	 */
	private void addCoordinate(Coordinate coordinate) {
		coordinateCounter++;
		
		coordinates.add(coordinate);
		this.setVisible(true);
		
		CoordinateImage image = new CoordinateImage(coordinates.size(), canvas.getHeight()-1, coordinates, 1);
		canvas.replaceImage(image);
		
		Dimension d = new Dimension(coordinates.size() + 10, scrollPane_1.getHeight()-1);
		statesDisplay.setSize(d);
		statesDisplay.setPreferredSize(d);
		
		paintComponents(getGraphics());
		
		lblNumberOfInputsCouter.setText("" + coordinateCounter);
		
		if(tglbtnAutoScroll.isSelected()) scrollPane.getHorizontalScrollBar().setValue(image.getWidth());
	}
	
	private void syncHorizontalScrollBars() {
		scrollPane_1.getHorizontalScrollBar()
			.setValue(scrollPane.getHorizontalScrollBar().getValue());
	}
}
