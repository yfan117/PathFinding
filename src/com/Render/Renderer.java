package com.Render;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.Controller.MouseController;
import com.Driver.Driver;
import com.Utilities.ActionHandler;
import com.Utilities.MidService;



public class Renderer extends JFrame{
	
	Canvas canvas;
	
	public JSlider grideSizeSlider;
	public JSlider timeSlider;
	public JLabel grideSizeLabel;
	public JLabel timeLabel;

	public JButton begine;
	
	public JButton Start;
	public JButton End;
	
	public JButton refresh;
	public JButton obstacle;
	public JButton eraser;
	

	public Renderer(int width, int height) {
		
		this.setTitle("Scout Path Finding - A Greedy Algorithm");
	
		canvas = new Canvas(MidService.numGrideX, MidService.numGrideY);

//		MouseController mouseController = new MouseController();
		canvas.addMouseListener(Driver.mouseController);
		canvas.addMouseMotionListener(Driver.mouseController);
		
//		this.add(canvas);
//		
//	
		this.add(canvas, BorderLayout.CENTER);
		
		JPanel controlPanel = new JPanel();
		this.add(controlPanel, BorderLayout.WEST);
//		BoxLayout boxlayout = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		controlPanel.setBackground(Color.DARK_GRAY);
		ActionHandler action = new ActionHandler();
		
	
		grideSizeSlider = new JSlider(15,50);
		grideSizeSlider.setBackground(Color.DARK_GRAY);
		grideSizeSlider.setForeground(Color.white);
		grideSizeSlider.addChangeListener(action);
		controlPanel.add(grideSizeSlider);
		grideSizeSlider.setPaintTrack(true);
		grideSizeSlider.setPaintTicks(true);
		grideSizeSlider.setPaintLabels(true);
		grideSizeSlider.setMajorTickSpacing(10);
		grideSizeSlider.setMinorTickSpacing(1);
		grideSizeLabel = new JLabel();
		grideSizeLabel.setText("Gride Size: " +grideSizeSlider.getValue());
		grideSizeLabel.setForeground(Color.white);
		controlPanel.add(grideSizeLabel);	
		controlPanel.add(new JLabel("---------------------------------------------"));
		
		timeSlider = new JSlider(0,500);
		timeSlider.setBackground(Color.DARK_GRAY);
		timeSlider.setForeground(Color.white);
		timeSlider.addChangeListener(action);
		controlPanel.add(timeSlider);
		timeSlider.setPaintTrack(true);
		timeSlider.setPaintTicks(true);
		timeSlider.setPaintLabels(true);
		timeSlider.setMajorTickSpacing(100);
		timeSlider.setMinorTickSpacing(10);
		timeLabel = new JLabel();
		timeLabel.setText("Time Delay: " +timeSlider.getValue() +" ms");
		timeLabel.setForeground(Color.white);
		controlPanel.add(timeLabel);	
		controlPanel.add(new JLabel("---------------------------------------------"));
		
		Start = new JButton("Start Marker");
		Start.setBackground(Color.DARK_GRAY);
		Start.setForeground(Color.white);
		Start.addActionListener(action);
		controlPanel.add(Start);
		
		End = new JButton("End Marker");
		End.setBackground(Color.DARK_GRAY);
		End.setForeground(Color.white);
		End.addActionListener(action);
		controlPanel.add(End);
		controlPanel.add(new JLabel("---------------------------------------------"));
		
		
		obstacle = new JButton("Obstacle");
		obstacle.setBackground(Color.DARK_GRAY);
		obstacle.setForeground(Color.white);
		controlPanel.add(obstacle);
		obstacle.addActionListener(action);
		
		eraser = new JButton("Eraser");
		eraser.setBackground(Color.DARK_GRAY);
		eraser.setForeground(Color.white);
		controlPanel.add(eraser);
		eraser.addActionListener(action);
		controlPanel.add(new JLabel("---------------------------------------------"));
		
		begine = new JButton("Run");
		begine.setBackground(Color.DARK_GRAY);
		begine.setForeground(Color.white);
		controlPanel.add(begine);
		begine.addActionListener(action);
		
		refresh = new JButton("Refresh");
		refresh.setBackground(Color.DARK_GRAY);
		refresh.setForeground(Color.white);
		controlPanel.add(refresh);
		refresh.addActionListener(action);
		controlPanel.add(new JLabel("---------------------------------------------"));
		
		JLabel userManual = new JLabel("Thank you for using the app!");
		userManual.setForeground(Color.white);
		controlPanel.add(userManual);
		
		JLabel userManual2 = new JLabel("- Yulong Fan");
		userManual2.setForeground(Color.white);
		controlPanel.add(userManual2);

		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.invalidate();
			
	}
	
	public void update() {
		
		
		canvas.update();
	}

}

