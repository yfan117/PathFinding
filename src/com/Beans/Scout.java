package com.Beans;

import java.util.ArrayList;

import com.Utilities.GrideService;
import com.Utilities.MidService;

public class Scout {


	Node node;
	
	boolean isCenter = false;
	boolean isBlock = false;
	
	double distance;
	
	ArrayList<Node> scoutPath = new ArrayList<>();
	
	PathFinding pathFind;
	public Scout(int x, int y, PathFinding pathFind) {
		
		this.pathFind = pathFind;
		node = new Node(x, y);
		if(MidService.isOutOfBound(node.getX(),node.getY()) == true || GrideService.gride[node.getX()][node.getY()] == true) {
			isBlock = true;
		}
	}
	
	public Scout(int x, int y, boolean isCenter, PathFinding pathFind) {
		
		this.pathFind = pathFind;
		node = new Node(x, y);
		if(MidService.isOutOfBound(node.getX(),node.getY()) == true || GrideService.gride[node.getX()][node.getY()] == true) {
			isBlock = true;
		}
		this.isCenter = isCenter;
	}
	
	public void updateDistance() {
		
		if(MidService.isOutOfBound(node.getX(),node.getY()) == true || GrideService.gride[node.getX()][node.getY()] == true) {
			isBlock = true;
		}
		else {
			isBlock = false;
			double targetX = pathFind.endX;
			double targetY = pathFind.endY;
			
			distance = Math.sqrt(((double)node.getX() - targetX)*((double)node.getX() - targetX) + ((double)node.getY() - targetY)*((double)node.getY() - targetY));
		}
		

		
	}
	
	
	public void findPath() {
		
		
		int targetX = pathFind.checkPoint.get(pathFind.checkPoint.size()-1).getX();
		int targetY = pathFind.checkPoint.get(pathFind.checkPoint.size()-1).getY();
		int checkX = node.getX();
		int checkY = node.getY();
		
		
		while(targetX != checkX || targetY != checkY) {
			
			int slope1;
			if((targetY - checkY)==0) {
				slope1 = Math.abs((targetX - checkX));
			}
			else {
				slope1 = Math.abs((targetX - checkX)/(targetY - checkY));
			}
			
			int slope2 = 0;
			if((targetX - checkX)==0) {
				slope1 = Math.abs((targetY - checkY));
			}
			else {
				slope2 = Math.abs((targetY - checkY)/(targetX - checkX));
			}
			
			int slope;
			boolean isXSlope = false;
			if(Math.abs(slope1) > Math.abs(slope2)) {
				slope = slope1;
				isXSlope = true;
			}
			else {
				slope = slope2;
			}
			int preX = checkX;
			int preY = checkY;
			scoutPath.add(new Node(checkX, checkY));

			if(targetX < checkX && isXSlope == true) {
				checkX -= slope;
				
				if(targetX > checkX) {
					checkX = targetX;
				}
				
			}
			else if(targetX < checkX && isXSlope == false) {
				checkX --;
				
				if(targetX > checkX) {
					checkX = targetX;
				}
			}
			else if(targetX > checkX && isXSlope == true) {
				checkX += slope;
				
				if(targetX < checkX) {
					checkX = targetX;
				}
				
			}
			else if(targetX > checkX && isXSlope == false) {
				checkX ++;
				
				if(targetX < checkX) {
					checkX = targetX;
				}
			}
			//-----------------------------------------------------------------
			if(targetY < checkY && isXSlope == true) {
				checkY --;
				
				if(targetY > checkY) {
					checkY = targetY;
				}
				
			}
			else if(targetY < checkY && isXSlope == false) {
				checkY -= slope;
				
				if(targetY > checkY) {
					checkY = targetY;
				}
			}
			else if(targetY > checkY && isXSlope == true) {
				checkY ++;
				
				if(targetY < checkY) {
					checkY = targetY;
				}
				
			}
			else if(targetY > checkY && isXSlope == false) {
				checkY += slope;
				
				if(targetY < checkY) {
					checkY = targetY;
				}
			}
			
			fillGap(preX, preY, checkX, checkY);
			
		}
		
	}
	
	public boolean isVisible() {
		scoutPath = new ArrayList<>();
		findPath();
		
		for(Node element: scoutPath) {
			
			if(MidService.isOutOfBound(element.getX(),element.getY()) == true || GrideService.gride[element.getX()][element.getY()] == true) {
				return false;
			}
		
		}
		
		return true;
	}
	

	
	public void fillGap(int preX, int preY, int x, int y) {
		
		while(preX != x || preY != y) {
			
			if(preX > x) {
				preX--;
			}
			else if(preX < x) {
				preX++;
			}
			
			if(preY > y) {
				preY--;
			}
			else if(preY < y) {
				preY++;
			}
			scoutPath.add(new Node(preX, preY));
		}
	}
	
	public ArrayList<Node> getScoutPath(){
		
		return scoutPath;
	}

	public int getX() {
		return node.getX();
	}


	public int getY() {
		return node.getY();
	}
	
	public void setX(int x) {
		 node.setX(x);
	}

	public Node getNode() {
		return node;
	}

	public void setY(int y) {
		 node.setY(y);
	}

	public boolean isCenter() {
		return isCenter;
	}
	
	public boolean isBlock() {
		return isBlock;
	}

	public void setCenter(boolean isCenter) {
		this.isCenter = isCenter;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Scout [node=" + node + ", isCenter=" + isCenter + ", isBlock=" + isBlock + ", distance=" + distance
				+ "]";
	}
	
	
}
