package com.Render;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.Beans.Node;
import com.Beans.PathFinding;
import com.Beans.Scout;
import com.Driver.Driver;
import com.Utilities.GrideService;
import com.Utilities.MidService;

public class Canvas extends JPanel{
	
	int width;
	int height;
	
	public Canvas(int width, int height) {
		
		this.width = width;
		this.height = height;
		this.setBackground(Color.DARK_GRAY);
	}
	
	public void update() {

		repaint();
	}
	
	protected void paintComponent(Graphics g) {
//		System.out.println("painting");
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.LIGHT_GRAY);
		for(int x = 0; x < width; x++) {
			g.drawLine(x*MidService.grideSize, 0, x*MidService.grideSize, height*MidService.grideSize);
		}
		
		for(int y = 0; y < height; y++) {
			
			g.drawLine(0, y*MidService.grideSize, width*MidService.grideSize, y*MidService.grideSize);
		}
		
		g.setColor(Color.BLUE);
		g.fillRect(MidService.beginLocation[0]*MidService.grideSize,
					MidService.beginLocation[1]*MidService.grideSize,
					MidService.grideSize, 
					MidService.grideSize);
		
		g.setColor(Color.red);
		g.fillRect(MidService.endLocation[0]*MidService.grideSize,
					MidService.endLocation[1]*MidService.grideSize,
					MidService.grideSize, 
					MidService.grideSize);
		
//			System.out.println(PathFinding.usedNode.size());
			for(int i = 0; i < MidService.usedNode.size(); i++) {

				g.setColor(Color.green);
				g.fillRect(MidService.usedNode.get(i).getX()*MidService.grideSize,
						MidService.usedNode.get(i).getY()*MidService.grideSize,
						MidService.grideSize, 
						MidService.grideSize);
				
//				g.setColor(Color.red);
//				g.drawString(String.valueOf(i), 
//							PathFinding.usedNode.get(i).getX()*MidService.grideSize + MidService.grideSize/2,
//							PathFinding.usedNode.get(i).getY()*MidService.grideSize + MidService.grideSize/2);
				
			}
			if(MidService.backupNode!=null) {
				for(int i = 0; i < MidService.backupNode.size(); i++) {

					g.setColor(Color.green);
					g.fillRect(MidService.backupNode.get(i).getX()*MidService.grideSize,
							MidService.backupNode.get(i).getY()*MidService.grideSize,
							MidService.grideSize, 
							MidService.grideSize);
				}
			}
			
//			for(int i = 0; i < MidService.usedNode.size(); i++) {
//
//				g.setColor(Color.cyan);
//				g.fillRect(MidService.usedNode.get(i).getX()*MidService.grideSize,
//						MidService.usedNode.get(i).getY()*MidService.grideSize,
//						MidService.grideSize, 
//						MidService.grideSize);
//				
////				g.setColor(Color.red);
////				g.drawString(String.valueOf(i), 
////							PathFinding.usedNode.get(i).getX()*MidService.grideSize + MidService.grideSize/2,
////							PathFinding.usedNode.get(i).getY()*MidService.grideSize + MidService.grideSize/2);
//				
//			}
//			
//			for(int i = 0; i < PathFinding.removedNode.size(); i++) {
//				
//				g.setColor(Color.red);
//				g.fillRect(PathFinding.removedNode.get(i).getX()*MidService.grideSize,
//						PathFinding.removedNode.get(i).getY()*MidService.grideSize,
//						MidService.grideSize, 
//						MidService.grideSize);
//				
//				g.setColor(Color.red);
//				g.drawString(String.valueOf(i), 
//							PathFinding.removedNode.get(i).getX()*MidService.grideSize + MidService.grideSize/2,
//							PathFinding.removedNode.get(i).getY()*MidService.grideSize + MidService.grideSize/2);
//			}
			
//			for(int i = 0; i < MidService.checkPath.size(); i++) {
//
//				g.setColor(Color.orange);
//				g.fillRect(MidService.checkPath.get(i).getX()*MidService.grideSize,
//						MidService.checkPath.get(i).getY()*MidService.grideSize,
//						MidService.grideSize, 
//						MidService.grideSize);
//
//
//				g.setColor(Color.red);
//				g.drawString(String.valueOf(i), 
//						MidService.checkPath.get(i).getX()*MidService.grideSize + MidService.grideSize/2,
//						MidService.checkPath.get(i).getY()*MidService.grideSize + MidService.grideSize/2);
//			}
//			if(Driver.path != null) {
//			for(int i = 0; i < Driver.path.checkPoint.size(); i++) {
//
//				g.setColor(Color.green);
//				g.fillRect(Driver.path.checkPoint.get(i).getX()*MidService.grideSize,
//						Driver.path.checkPoint.get(i).getY()*MidService.grideSize,
//						MidService.grideSize, 
//						MidService.grideSize);
//				if(i+1 != Driver.path.checkPoint.size()) {
//					
//					 g2.setStroke(new BasicStroke(5));
//					  
//					g2.drawLine(Driver.path.checkPoint.get(i).getX()*MidService.grideSize + MidService.grideSize/2,
//							Driver.path.checkPoint.get(i).getY()*MidService.grideSize + MidService.grideSize/2,
//							Driver.path.checkPoint.get(i+1).getX()*MidService.grideSize + MidService.grideSize/2,
//							Driver.path.checkPoint.get(i+1).getY()*MidService.grideSize + MidService.grideSize/2);
//				}
//
//				g.setColor(Color.red);
//				g.drawString(String.valueOf(i), 
//							Driver.path.checkPoint.get(i).getX()*MidService.grideSize + MidService.grideSize/2,
//							Driver.path.checkPoint.get(i).getY()*MidService.grideSize + MidService.grideSize/2);
//			}
//			
//			if(Driver.path.scouts[1][1] != null)
//			{
//				g.fillRect(Driver.path.scouts[1][1].getX()*MidService.grideSize,
//						Driver.path.scouts[1][1].getY()*MidService.grideSize,
//						MidService.grideSize, 
//						MidService.grideSize);
//				
//			}
//			
//			
//			if(Driver.path.minDistanceScout != null) {
//				g.setColor(Color.blue);
//				g.fillRect(Driver.path.minDistanceScout.getX()*MidService.grideSize,
//						Driver.path.scouts[1][1].getY()*MidService.grideSize,
//						MidService.grideSize, 
//						MidService.grideSize);
//			}
//			
//
//			}
			
//			if(Driver.path2 != null) {
//				
//		
//				for(int i = 0; i < Driver.path2.checkPoint.size(); i++) {
//
//					g.setColor(Color.green);
//					g.fillRect(Driver.path2.checkPoint.get(i).getX()*MidService.grideSize,
//							Driver.path2.checkPoint.get(i).getY()*MidService.grideSize,
//							MidService.grideSize, 
//							MidService.grideSize);
//					if(i+1 != Driver.path2.checkPoint.size()) {
//						
//						 g2.setStroke(new BasicStroke(5));
//						  
//						g2.drawLine(Driver.path2.checkPoint.get(i).getX()*MidService.grideSize + MidService.grideSize/2,
//								Driver.path2.checkPoint.get(i).getY()*MidService.grideSize + MidService.grideSize/2,
//								Driver.path2.checkPoint.get(i+1).getX()*MidService.grideSize + MidService.grideSize/2,
//								Driver.path2.checkPoint.get(i+1).getY()*MidService.grideSize + MidService.grideSize/2);
//					}
//				}
//			}
			
			g.setColor(Color.white);
			for(int x = 0; x < MidService.numGrideX; x++) {
				for(int y = 0; y < MidService.numGrideY; y++) {
					
					if(GrideService.getGrideStatus(x,y) == true) {
						g.fillRect(x*MidService.grideSize, y*MidService.grideSize, MidService.grideSize, MidService.grideSize);
					};
				}
			}
	}



}
