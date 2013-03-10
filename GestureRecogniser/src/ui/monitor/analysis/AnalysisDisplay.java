package ui.monitor.analysis;

import graphing.GraphData;
import graphing.GraphingCanvas;
import graphing.GraphingImage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import coordinates.CoordinateSeries;


import analysis.GestureStats;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.event.MouseEvent;

public class AnalysisDisplay extends JFrame {

	private JPanel contentPane;
	private JLabel lblXDifferenceData;
	private JLabel lblXMaxData;
	private JLabel lblGestureTimeData;
	private JLabel lblXMinData;
	private JLabel lblYMaxData;
	private JLabel lblYMinData;
	private JLabel lblZDifferenceData;
	private JLabel lblYDifferenceData;
	private JLabel lblZMaxData;
	private JLabel lblZMinData;
	
	private Map<JLabel, GestureStats> gestureStats = new HashMap<JLabel, GestureStats>();
	private JPanel gestureStatsPanel;
	private JSplitPane splitPane;
	private GraphData graphData;
	private GraphingImage image;
	private GraphingCanvas panel_1;

	/**
	 * Create the frame.
	 */
	public AnalysisDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_1);
		
		graphData = new GraphData();
		image = new GraphingImage(230, 140, graphData, 1);
		panel_1 = new GraphingCanvas(image);
		splitPane_1.setLeftComponent(panel_1);
		
		JPanel panel_3 = new JPanel();
		splitPane_1.setRightComponent(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane);
		
		JPanel panel_4 = new JPanel();
		scrollPane.setViewportView(panel_4);
		
		JLabel lblGestureTime = new JLabel("Gesture time: ");
		
		lblGestureTimeData = new JLabel("0");
		lblGestureTimeData.setForeground(Color.GRAY);
		
		JLabel lblXaxis = new JLabel("X-Axis");
		lblXaxis.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JLabel lblXDifference = new JLabel("Difference:");
		
		lblXDifferenceData = new JLabel("0");
		lblXDifferenceData.setForeground(Color.GRAY);
		
		JLabel lblYaxis = new JLabel("Y-Axis");
		lblYaxis.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JLabel lblXMax = new JLabel("Max:");
		
		lblXMaxData = new JLabel("0");
		lblXMaxData.setForeground(Color.GRAY);
		
		JLabel lblXMin = new JLabel("Min:");
		
		lblXMinData = new JLabel("0");
		lblXMinData.setForeground(Color.GRAY);
		
		JLabel lblYMax = new JLabel("Max:");
		
		JLabel lblYMin = new JLabel("Min:");
		
		JLabel lblYDifference = new JLabel("Difference:");
		
		lblYMaxData = new JLabel("0");
		lblYMaxData.setForeground(Color.GRAY);
		
		lblYMinData = new JLabel("0");
		lblYMinData.setForeground(Color.GRAY);
		
		JLabel lblZaxis = new JLabel("Z-Axis");
		lblZaxis.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JLabel lblZMax = new JLabel("Max:");
		
		JLabel lblZMin = new JLabel("Min:");
		
		JLabel lblZDifference = new JLabel("Difference:");
		
		lblZDifferenceData = new JLabel("0");
		lblZDifferenceData.setForeground(Color.GRAY);
		
		lblYDifferenceData = new JLabel("0");
		lblYDifferenceData.setForeground(Color.GRAY);
		
		lblZMaxData = new JLabel("0");
		lblZMaxData.setForeground(Color.GRAY);
		
		lblZMinData = new JLabel("0");
		lblZMinData.setForeground(Color.GRAY);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(lblXMax)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblXMaxData))
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(lblYMax)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblYMaxData))
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(lblZMax)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblZMaxData)))
							.addGap(53)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(lblYMin)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblYMinData))
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(lblXMin)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblXMinData))
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(lblZMin)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblZMinData)))
							.addContainerGap())
						.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_4.createSequentialGroup()
								.addComponent(lblYDifference)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblYDifferenceData))
							.addComponent(lblZaxis))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(lblGestureTime)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblGestureTimeData))
								.addComponent(lblXaxis)
								.addComponent(lblYaxis)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(lblXDifference)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblXDifferenceData))
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(lblZDifference)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblZDifferenceData)))
							.addContainerGap(122, Short.MAX_VALUE))))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGestureTime)
						.addComponent(lblGestureTimeData))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblXaxis)
					.addGap(4)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXMax)
						.addComponent(lblXMaxData)
						.addComponent(lblXMin, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblXMinData, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblXDifferenceData)
						.addComponent(lblXDifference))
					.addGap(18)
					.addComponent(lblYaxis)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYMax)
						.addComponent(lblYMaxData)
						.addComponent(lblYMin)
						.addComponent(lblYMinData))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYDifference)
						.addComponent(lblYDifferenceData))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblZaxis)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblZMax)
						.addComponent(lblZMaxData)
						.addComponent(lblZMin)
						.addComponent(lblZMinData))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblZDifference)
						.addComponent(lblZDifferenceData))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		splitPane_1.setDividerLocation(130);
		
		updateGestureStatsPanel();
		splitPane.setDividerLocation(240);
	}
	
	/**
	 * Add a GestureStats to display in the UI
	 * @param gsts The GestureStats to add
	 */
	public void addGestureStats(GestureStats gsts) {
		JLabel lbl = new JLabel(gsts.getGesture().getClass().toString());
		lbl.setToolTipText("" + gsts.getTime());
		
		lbl.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				((JLabel) e.getSource()).setBackground(new Color(0, 255, 0, 50));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				((JLabel) e.getSource()).setBackground(new Color(255, 255, 255, 255));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				updateGestureDisplayPanel((JLabel) e.getSource());
			}
		});
		
		gestureStats.put(lbl, gsts);
		updateGestureStatsPanel();
		this.paintComponents(this.getGraphics());
	}
	
	private void updateGestureDisplayPanel(JLabel label) {
		GestureStats gsts = gestureStats.get(label);
		graphData.add(""+gsts.getTime(), new CoordinateSeries(gsts.getCoordinates()));
		panel_1.reloadImage();
		
		lblGestureTimeData.setText("" + gsts.getTime());
		
		double[] stats = gsts.getxStats();
		lblXDifferenceData.setText("" + stats[2]);
		lblXMaxData.setText("" + roundNumber(stats[1]));
		lblXMinData.setText("" + roundNumber(stats[0]));
		
		stats = gsts.getyStats();
		lblYDifferenceData.setText("" + stats[2]);
		lblYMaxData.setText("" + roundNumber(stats[1]));
		lblYMinData.setText("" + roundNumber(stats[0]));
		
		stats = gsts.getzStats();
		lblZDifferenceData.setText("" + stats[2]);
		lblZMaxData.setText("" + roundNumber(stats[1]));
		lblZMinData.setText("" + roundNumber(stats[0]));
		
		contentPane.paintComponents(getGraphics());
	}
	
	private void updateGestureStatsPanel() {
		gestureStatsPanel = new JPanel();
		splitPane.setRightComponent(gestureStatsPanel);
		gestureStatsPanel.setLayout(new BoxLayout(gestureStatsPanel, BoxLayout.Y_AXIS));
		
		for(JLabel label : gestureStats.keySet()) {
			gestureStatsPanel.add(label);
		}
		splitPane.setDividerLocation(240);
	}
	
	private double roundNumber(double number) {
		return ((int)(number * 100))/100;
	}
}
