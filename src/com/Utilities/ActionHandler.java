package com.Utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.Beans.Node;
import com.Driver.Driver;


public class ActionHandler implements ActionListener, ChangeListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MidService.isStartPoint = false;
		MidService.isEndPoint = false;
		MidService.isObstacle = false;
		MidService.isEraser = false;
		
		if(e.getSource().equals(Driver.renderer.begine))
		{
			if(MidService.isOutOfBound(MidService.beginLocation[0], MidService.beginLocation[1]) == false &&
				MidService.isOutOfBound(MidService.endLocation[0], MidService.endLocation[1]) == false)
			{
				if(Driver.path != null) {
					Driver.path.killThread = true;
					Driver.path = null;
				}
				
				if(Driver.path2 != null) {
					Driver.path2.killThread = true;
					Driver.path2 = null;
				}
			
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				MidService.checkPath = new ArrayList<>();
				MidService.backupNode = new ArrayList<>();
				MidService.usedNode = new ArrayList<>();

				Driver.findPath();
			}
		}
		else if(e.getSource().equals(Driver.renderer.Start))
		{
			MidService.isStartPoint = true;
			
		}
		else if(e.getSource().equals(Driver.renderer.End))
		{

			MidService.isEndPoint = true;
		}
		else if(e.getSource().equals(Driver.renderer.refresh))
		{
			if(Driver.path != null) {
				Driver.path.killThread = true;
				Driver.path = null;
			}
			
			if(Driver.path2 != null) {
				Driver.path2.killThread = true;
				Driver.path2 = null;
			}
		
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			GrideService.gride = new boolean[MidService.numGrideX][MidService.numGrideY];
			MidService.checkPath = new ArrayList<>();
			MidService.backupNode = new ArrayList<>();
			MidService.usedNode = new ArrayList<>();
			
			MidService.beginLocation = new int[] {MidService.windowX*2, MidService.windowY*2};
			MidService.endLocation = new int[] {MidService.windowX*2, MidService.windowY*2};
		}
		else if(e.getSource().equals(Driver.renderer.obstacle))
		{
			MidService.isObstacle = true;
		}
		else if(e.getSource().equals(Driver.renderer.eraser))
		{
			MidService.isEraser = true;
		}
	
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource().equals(Driver.renderer.grideSizeSlider))
		{
			Driver.renderer.grideSizeLabel.setText("Contrast Value: " +Driver.renderer.grideSizeSlider.getValue());
			MidService.grideSize = Driver.renderer.grideSizeSlider.getValue();
		}
		else if(e.getSource().equals(Driver.renderer.timeSlider))
		{
			Driver.renderer.timeLabel.setText("Time Delay: " +Driver.renderer.timeSlider.getValue() +" ms");
			MidService.timeDelay = Driver.renderer.timeSlider.getValue();
		}

		
		
	}

}
