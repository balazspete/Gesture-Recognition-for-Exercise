package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JEditorPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import javax.swing.JCheckBox;
import java.awt.Color;

public class ResultsWindow extends JFrame {

	private JPanel contentPane;
	
	private String saveDirectory = null;

	private JEditorPane editorPane;

	private JCheckBox chckbxCloseAfterSave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultsWindow frame = new ResultsWindow();
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
	public ResultsWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 577, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				saveResults();
			}
		});
		panel.add(btnSave);
		
		chckbxCloseAfterSave = new JCheckBox("Close after save");
		chckbxCloseAfterSave.setForeground(Color.BLACK);
		chckbxCloseAfterSave.setSelected(true);
		panel.add(chckbxCloseAfterSave);
	}
	
	public void setSaveDirectory(String directory) {
		this.saveDirectory = directory;
	}
	
	public void setText(String text) {
		editorPane.setText(text);
	}
	
	private void saveResults(){
		JFileChooser jfc = new JFileChooser();
		if(saveDirectory != null) { 
			jfc.setCurrentDirectory(new File(saveDirectory));
		}
		jfc.showSaveDialog(null);
		int resp = jfc.showSaveDialog(null);
		
		if(resp == JFileChooser.APPROVE_OPTION) {
			try{
				FileWriter fstream = new FileWriter(jfc.getSelectedFile());
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(editorPane.getText());
				out.close();
				if(chckbxCloseAfterSave.isSelected()) {
					this.dispose();
				}
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "An error has occurred while saving the file...", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
	}

}
