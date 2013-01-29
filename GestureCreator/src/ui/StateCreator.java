package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import model.state.FuzzyNumber;
import model.state.FuzzyPoint;
import model.state.StateImage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class StateCreator extends JFrame {

	private JPanel contentPane;
	private GraphingCanvas canvas = null;
	private Map<JCheckBox, JSpinner[]> checkboxToSpinner = new HashMap<JCheckBox, JSpinner[]>();
	Vector<FuzzyPoint> points = null;
	private JCheckBox chckbxZaxis;
	private JCheckBox chckbxYaxis;
	private JCheckBox chckbxXaxis;
	private JSpinner spinnerZValue;
	private JSpinner spinnerZErrorValue;
	private JSpinner spinnerYErrorValue;
	private JSpinner spinnerYValue;
	private JSpinner spinnerXErrorValue;
	private JSpinner spinnerXValue;
	private JSpinner spinner;
	private JSpinner spinnerIndex;

	/**
	 * Create the frame.
	 */
	public StateCreator() {
		initialize();
	}
	
	public StateCreator(GraphingCanvas canvas, Vector<FuzzyPoint> points) {
		initialize();
		this.canvas = canvas;
		this.points = points;
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblVerticalDisplacement = new JLabel("Vertical Displacement");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblVerticalDisplacement, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblVerticalDisplacement, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblVerticalDisplacement);
		
		spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 0.0001));
		sl_contentPane.putConstraint(SpringLayout.NORTH, spinner, -6, SpringLayout.NORTH, lblVerticalDisplacement);
		sl_contentPane.putConstraint(SpringLayout.WEST, spinner, 87, SpringLayout.EAST, lblVerticalDisplacement);
		sl_contentPane.putConstraint(SpringLayout.EAST, spinner, -5, SpringLayout.EAST, contentPane);
		JSpinner.NumberEditor editor = (JSpinner.NumberEditor)spinner.getEditor();  
        DecimalFormat format = editor.getFormat();  
        format.setMinimumFractionDigits(4);
		contentPane.add(spinner);
		
		chckbxXaxis = new JCheckBox("X-axis");
		chckbxXaxis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enableAxis((JCheckBox)e.getSource());
			}
		});
		chckbxXaxis.setSelected(true);
		chckbxXaxis.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(chckbxXaxis);
		
		chckbxYaxis = new JCheckBox("Y-axis");
		sl_contentPane.putConstraint(SpringLayout.WEST, chckbxYaxis, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, chckbxXaxis, 0, SpringLayout.WEST, chckbxYaxis);
		chckbxYaxis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enableAxis((JCheckBox)e.getSource());
			}
		});
		chckbxYaxis.setSelected(true);
		chckbxYaxis.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(chckbxYaxis);
		
		JLabel lblXValue = new JLabel("X value: ");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, chckbxXaxis, -6, SpringLayout.NORTH, lblXValue);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblXValue, 0, SpringLayout.WEST, lblVerticalDisplacement);
		contentPane.add(lblXValue);
		
		spinnerXValue = new JSpinner(new SpinnerNumberModel(0, 0, 100, 0.0001));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblXValue, 6, SpringLayout.NORTH, spinnerXValue);
		sl_contentPane.putConstraint(SpringLayout.WEST, spinnerXValue, 0, SpringLayout.WEST, spinner);
		sl_contentPane.putConstraint(SpringLayout.EAST, spinnerXValue, -5, SpringLayout.EAST, contentPane);
		editor = (JSpinner.NumberEditor)spinnerXValue.getEditor();  
        format = editor.getFormat();  
        format.setMinimumFractionDigits(4);
		contentPane.add(spinnerXValue);
		
		spinnerXErrorValue = new JSpinner(new SpinnerNumberModel(0, 0, 100, 0.0001));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, spinnerXValue, -6, SpringLayout.NORTH, spinnerXErrorValue);
		sl_contentPane.putConstraint(SpringLayout.WEST, spinnerXErrorValue, 0, SpringLayout.WEST, spinner);
		sl_contentPane.putConstraint(SpringLayout.EAST, spinnerXErrorValue, -5, SpringLayout.EAST, contentPane);
		editor = (JSpinner.NumberEditor)spinnerXErrorValue.getEditor();  
        format = editor.getFormat();  
        format.setMinimumFractionDigits(4);
		contentPane.add(spinnerXErrorValue);
		
		JLabel lblXErrorRange = new JLabel("X error range: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblXErrorRange, 6, SpringLayout.NORTH, spinnerXErrorValue);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblXErrorRange, 0, SpringLayout.WEST, lblVerticalDisplacement);
		contentPane.add(lblXErrorRange);
		
		checkboxToSpinner.put(chckbxXaxis, new JSpinner[]{
			spinnerXValue, spinnerXErrorValue	
		});
		
		JLabel label = new JLabel("+/-");
		sl_contentPane.putConstraint(SpringLayout.NORTH, label, 6, SpringLayout.NORTH, spinnerXErrorValue);
		contentPane.add(label);
		
		JLabel lblYValue = new JLabel("Y value:");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, chckbxYaxis, -6, SpringLayout.NORTH, lblYValue);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblYValue, 0, SpringLayout.WEST, lblVerticalDisplacement);
		contentPane.add(lblYValue);
		
		spinnerYValue = new JSpinner(new SpinnerNumberModel(0, 0, 100, 0.0001));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, spinnerXErrorValue, -30, SpringLayout.NORTH, spinnerYValue);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblYValue, 6, SpringLayout.NORTH, spinnerYValue);
		sl_contentPane.putConstraint(SpringLayout.WEST, spinnerYValue, 234, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, spinnerYValue, 0, SpringLayout.EAST, spinner);
		editor = (JSpinner.NumberEditor)spinnerYValue.getEditor();  
        format = editor.getFormat();  
        format.setMinimumFractionDigits(4);
		contentPane.add(spinnerYValue);
		
		spinnerYErrorValue = new JSpinner(new SpinnerNumberModel(0, 0, 100, 0.0001));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, spinnerYValue, -6, SpringLayout.NORTH, spinnerYErrorValue);
		sl_contentPane.putConstraint(SpringLayout.WEST, spinnerYErrorValue, 0, SpringLayout.WEST, spinner);
		sl_contentPane.putConstraint(SpringLayout.EAST, spinnerYErrorValue, -5, SpringLayout.EAST, contentPane);
		editor = (JSpinner.NumberEditor)spinnerYErrorValue.getEditor();  
        format = editor.getFormat();  
        format.setMinimumFractionDigits(4);
		contentPane.add(spinnerYErrorValue);
		
		JLabel lblYErrorRange = new JLabel("Y error range:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblYErrorRange, 6, SpringLayout.NORTH, spinnerYErrorValue);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblYErrorRange, 0, SpringLayout.WEST, lblVerticalDisplacement);
		contentPane.add(lblYErrorRange);
		
		checkboxToSpinner.put(chckbxYaxis, new JSpinner[]{
			spinnerYValue, spinnerYErrorValue	
		});
		
		JLabel label_1 = new JLabel("+/-");
		sl_contentPane.putConstraint(SpringLayout.EAST, label_1, -6, SpringLayout.WEST, spinnerYErrorValue);
		sl_contentPane.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, label_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, label_1, 6, SpringLayout.NORTH, spinnerYErrorValue);
		contentPane.add(label_1);
		
		chckbxZaxis = new JCheckBox("Z-axis");
		sl_contentPane.putConstraint(SpringLayout.WEST, chckbxZaxis, 0, SpringLayout.WEST, contentPane);
		chckbxZaxis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enableAxis((JCheckBox)e.getSource());
			}
		});
		chckbxZaxis.setSelected(true);
		chckbxZaxis.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(chckbxZaxis);
		
		spinnerZValue = new JSpinner(new SpinnerNumberModel(0, 0, 100, 0.0001));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, spinnerYErrorValue, -23, SpringLayout.NORTH, spinnerZValue);
		sl_contentPane.putConstraint(SpringLayout.EAST, spinnerZValue, -5, SpringLayout.EAST, contentPane);
		editor = (JSpinner.NumberEditor)spinnerZValue.getEditor();  
        format = editor.getFormat();  
        format.setMinimumFractionDigits(4);
		contentPane.add(spinnerZValue);
		
		JLabel lblZValue = new JLabel("Z value:");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, chckbxZaxis, -6, SpringLayout.NORTH, lblZValue);
		sl_contentPane.putConstraint(SpringLayout.WEST, spinnerZValue, 175, SpringLayout.EAST, lblZValue);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblZValue, 6, SpringLayout.NORTH, spinnerZValue);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblZValue, 0, SpringLayout.WEST, lblVerticalDisplacement);
		contentPane.add(lblZValue);
		
		spinnerZErrorValue = new JSpinner(new SpinnerNumberModel(0, 0, 100, 0.0001));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, spinnerZValue, -6, SpringLayout.NORTH, spinnerZErrorValue);
		sl_contentPane.putConstraint(SpringLayout.EAST, spinnerZErrorValue, -5, SpringLayout.EAST, contentPane);
		editor = ((JSpinner.NumberEditor)spinnerZErrorValue.getEditor());  
        format = editor.getFormat();  
        format.setMinimumFractionDigits(4);
		contentPane.add(spinnerZErrorValue);
		
		JLabel lblZErrorRange = new JLabel("Z error range:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, spinnerZErrorValue, -6, SpringLayout.NORTH, lblZErrorRange);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblZErrorRange, 0, SpringLayout.WEST, lblVerticalDisplacement);
		contentPane.add(lblZErrorRange);
		
		checkboxToSpinner.put(chckbxZaxis, new JSpinner[]{
			spinnerZValue, spinnerZErrorValue	
		});
		
		JLabel label_2 = new JLabel("+/-");
		sl_contentPane.putConstraint(SpringLayout.EAST, label_2, -162, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, spinnerZErrorValue, 6, SpringLayout.EAST, label_2);
		sl_contentPane.putConstraint(SpringLayout.NORTH, label_2, 6, SpringLayout.NORTH, spinnerZErrorValue);
		contentPane.add(label_2);
		
		JButton btnSave = new JButton("Save");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblZErrorRange, -23, SpringLayout.NORTH, btnSave);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSave, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSave, -10, SpringLayout.SOUTH, contentPane);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateImage();
			}
		});
		contentPane.add(btnSave);
		
		JButton btnDiscard = new JButton("Discard");
		btnDiscard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removePoint();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnDiscard, 6, SpringLayout.EAST, btnSave);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnDiscard, 0, SpringLayout.SOUTH, btnSave);
		contentPane.add(btnDiscard);
		
		spinnerIndex = new JSpinner();
		sl_contentPane.putConstraint(SpringLayout.NORTH, spinnerIndex, 6, SpringLayout.SOUTH, spinner);
		sl_contentPane.putConstraint(SpringLayout.WEST, spinnerIndex, 0, SpringLayout.WEST, spinner);
		sl_contentPane.putConstraint(SpringLayout.EAST, spinnerIndex, 0, SpringLayout.EAST, spinner);
		contentPane.add(spinnerIndex);
		
		JLabel lblPosition = new JLabel("Index");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPosition, 6, SpringLayout.NORTH, spinnerIndex);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPosition, 0, SpringLayout.WEST, lblVerticalDisplacement);
		contentPane.add(lblPosition);
	}
	
	private void enableAxis(JCheckBox chckbx) {
		boolean enable = chckbx.isSelected();
		for(JSpinner spinner : checkboxToSpinner.get(chckbx)) {
			spinner.setEnabled(enable);
		}
	}
	
	private FuzzyPoint createFuzzyPoint() {
		FuzzyPoint p = new FuzzyPoint();
		
		if(chckbxXaxis.isSelected()) {
			p.setX(getFuzzyNumberFromValue(spinnerXValue.getValue(), spinnerXErrorValue.getValue()));
		}
		if(chckbxYaxis.isSelected()) {
			p.setY(getFuzzyNumberFromValue(spinnerYValue.getValue(), spinnerYErrorValue.getValue()));
		}
		if(chckbxZaxis.isSelected()) {
			p.setZ(getFuzzyNumberFromValue(spinnerZValue.getValue(), spinnerZErrorValue.getValue()));
		}
		
		Object value = spinner.getValue();
		p.setHorizontalAlignment(value instanceof Integer ? (int) value : (double) value);
		
		return p;
	}
	
	private FuzzyNumber getFuzzyNumberFromValue(Object value, Object error) {
		return new FuzzyNumber(
			value instanceof Integer ? (int) value : (double) value, 
			error instanceof Integer ? (int) error : (double) error);
	}
	
	private void updateImage() {
		StateImage img = canvas.getSimage(); 
		img.setTemporary(createFuzzyPoint());
		img.paintImage();
		canvas.paintComponent(canvas.getGraphics());
	}
	
	private void savePoint() {
		StateImage img = canvas.getSimage();
		Object value = spinnerIndex.getValue();
		img.addPoint(img.getTemporary(), 
				value instanceof Integer ? (int) value : (int)(double) value);
	}
	
	private void removePoint() {
		
	}
}
