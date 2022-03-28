package com.Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;

import com.Beans.PathFinding;
import com.Driver.Driver;
import com.Utilities.GrideService;
import com.Utilities.MidService;

public class MouseController implements MouseListener, MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(SwingUtilities.isLeftMouseButton(e)) {
			if(MidService.isEraser == true) {
				GrideService.changeGride(new int[]{e.getX(), e.getY()}, false);
			}
			else if(MidService.isObstacle == true) {
				GrideService.changeGride(new int[]{e.getX(), e.getY()}, true);
			}
		}

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(SwingUtilities.isLeftMouseButton(e)) {
		
			
			if(MidService.isEraser == true) {
				GrideService.changeGride(new int[]{e.getX(), e.getY()}, false);
			}
			else if(MidService.isObstacle == true) {
				GrideService.changeGride(new int[]{e.getX(), e.getY()}, true);
			}
			else if(MidService.isStartPoint == true) {
				MidService.beginLocation = GrideService.convertToGrideLocation(new int[]{e.getX(), e.getY()});
			}
			else if(MidService.isEndPoint == true) {
				MidService.endLocation = GrideService.convertToGrideLocation(new int[]{e.getX(), e.getY()});
			}
			
		}

		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}






}
