package com.Utilities;

import java.util.ArrayList;

import com.Beans.Node;
import com.Beans.PathFinding;
import com.Beans.Scout;

public class MidService {

	public static int timer = 1000/30;
	
	public static int grideSize =15;
	
	public static int windowX = 1920;
	public static int windowY = 1080;
	
	public static int numGrideX = windowX/grideSize;
	public static int numGrideY = windowY/grideSize;
	
	public static boolean isBegin = true;
	public static int[] beginLocation = new int[] {windowX*2,windowY*2};
	public static int[] endLocation = new int[] {windowX*2,windowY*2};
	
	public static boolean isEraser = false;
	public static boolean isStartPoint = false;
	public static boolean isEndPoint = false;
	public static boolean isObstacle = false;
	public static boolean isRun = false;
	public static boolean isRefresh = false;
	
	public static int timeDelay = 250;
	
	public static Scout scout;
	
	public static ArrayList<Node> checkPath = new ArrayList<>();
	
	public static ArrayList<Node> usedNode = new ArrayList<>();
	public static ArrayList<Node> backupNode;
	public static int threadFlag = 0;
	

	public static boolean isLineOfSight(int startX, int startY, int endX, int endY) {
//		checkPath = new ArrayList<>();
		int targetX = endX;
		int targetY = endY;
		int checkX = startX;
		int checkY = startY;
		
		
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
			checkPath.add(new Node(checkX, checkY));

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
			
			int tempX = 0;
			int tempY = 0;
			while(preX != checkX || preY != checkY) {
				
				if(preX > checkX) {
					tempX = preX-1;
				}
				else if(preX < checkX) {
					tempX = preX+1;
				}
				
				if(preY > checkY) {
					tempY = preY-1;
				}
				else if(preY < checkY) {
					tempY = preY+1;
				}
				
				if(isReachable(preX, preY, tempX, tempY) == false) {
					return false;
				}
				checkPath.add(new Node(tempX, tempY));
				preX = tempX;
				preY = tempY;
			}
			
		}
		
		for(Node element: checkPath) {
			
			if(GrideService.gride[element.getX()][element.getY()] == true) {
				
				return false;
			}

		}
		
		return true;
	}
	
	public static boolean isReachable(int startX, int startY, int endX, int endY) {
		
		if(MidService.isOutOfBound(endX, endY) == true) {
			return false;
		}
		int deltaX = endX - startX;
		int deltaY = endY - startY;

		if(GrideService.gride[startX + deltaX][startY] == true && GrideService.gride[startX + deltaX][startY] == true) {
			return false;
		}


		return true;
	}
	
	public static boolean isOutOfBound(int x, int y) {
		
		if(x<0 || y<0 || x>=numGrideX || y>=numGrideY) {
			return true;
		}
		
		return false;

	}
	

}
