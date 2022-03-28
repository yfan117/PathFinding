package com.Beans;

import java.util.ArrayList;

import com.Driver.Driver;
import com.Utilities.GrideService;
import com.Utilities.MidService;

public class PathFinding implements Runnable{
	
	public Scout[][] scouts = new Scout[3][3];
	
	public ArrayList<Node> checkPoint = new ArrayList<>();
//	public ArrayList<Node> usedNode = new ArrayList<>();
	public Scout minDistanceScout;
	public boolean killThread = false;
	
	int startX = 0;
	int startY = 0;
	int endX = 0;
	int endY = 0;
	
	public PathFinding(int startX, int startY, int endX, int endY) {
		super();
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}

	@Override
	public void run() {
		
		findPath();
		
	
	}
	public void findPath() {
		System.out.println("entered path find");
		
		checkPoint.add(new Node(startX, startY));
		
		scouts[0][0] = new Scout(startX -1, startY-1, this);
		scouts[1][0] = new Scout(startX, startY-1, this);
		scouts[2][0] = new Scout(startX +1, startY-1, this);
		scouts[0][1] = new Scout(startX -1, startY, this);
		scouts[1][1] = new Scout(startX, startY, true, this);
		scouts[2][1] = new Scout(startX +1, startY, this);
		scouts[0][2] = new Scout(startX -1, startY+1, this);
		scouts[1][2] = new Scout(startX, startY+1, this);
		scouts[2][2] = new Scout(startX +1, startY+1, this);
		

		Node lastCheckPoint = checkPoint.get(checkPoint.size()-1);
		 minDistanceScout = scouts[1][1];
		
		while(lastCheckPoint.getX() != endX || lastCheckPoint.getX() != endY) 
		{

			try {
				Thread.sleep(MidService.timeDelay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			Driver.renderer.update();
//			System.out.println(scouts[1][1].getX() +", " +scouts[1][1].getY());
//			System.out.println(scouts[0][1].toString());

//			Scout minDistanceScout = scouts[1][1];
			
			minDistanceScout = findMinScout();
			
			while(minDistanceScout == null) {
				System.out.println("ERROR!!! Scout trapped, reloading checkpoint");
				
				boolean isTerminal = true;
				for(int i = checkPoint.size() -1 ; i >= 0; i--) 
				{
					int deltaX = checkPoint.get(i).getX() - scouts[1][1].getX();
					int deltaY = checkPoint.get(i).getY() - scouts[1][1].getY();
					for(int a = 0; a < 3; a++) {
						for(int b = 0; b < 3; b++) {
							
							scouts[a][b].setX(scouts[a][b].getX() + deltaX);
							scouts[a][b].setY(scouts[a][b].getY() + deltaY);
							
						}
					}
					minDistanceScout = findMinScout();
					if(minDistanceScout != null) {
						System.out.println("checkpoint " +i +" reloaded");
						isTerminal = false;
						break;
					}
				}

				if(isTerminal == true) {
					System.out.println("starting rescue thread");
					hailMarry();
					break;
				}
				
				minDistanceScout = findMinScout();

			}
			
			if(killThread == true) {
				break;
			}
			for(int a = 0; a < 3; a++) {
				for(int b = 0; b < 3; b++) {
					
					scouts[a][b].updateDistance();
			
				}
			}
			for(int a = 0; a < 3; a++) {
				for(int b = 0; b < 3; b++) {

						if(
								isUsed(scouts[a][b]) == false && 
								scouts[a][b].isCenter() == false && 
								isBlock(scouts[a][b]) == false && 
								minDistanceScout.getDistance() > scouts[a][b].getDistance()) 
						{
							
							if(isReachable(scouts[1][1], scouts[a][b])==true) 
							{
								
								minDistanceScout = scouts[a][b];

							}
						
					}
	
					
				}
			}
			
//			if(hasMoved = false) {
//				minDistanceScout = checkPoint.get(index)
//			}
//			usedNode.add(new Node(scouts[1][1].getX(), scouts[1][1].getY()));
			if(minDistanceScout.isVisible() == false) {
				checkPoint.add(new Node(scouts[1][1].getX(), scouts[1][1].getY()));
			}
			if(minDistanceScout.getX() == endX && minDistanceScout.getY() == endY) {
				System.out.println("here");
				checkPoint.add(new Node(endX, endY));
				break;
			}
//			else if(MidService.isLineOfSight(minDistanceScout.getX(), minDistanceScout.getY(), endX, endY)) {
//				System.out.println("is line of sight");
//				checkPoint.add(new Node(minDistanceScout.getX(), minDistanceScout.getY()));
//				checkPoint.add(new Node(endX, endY));
//				break;
//			}
			int deltaX = minDistanceScout.getX() - scouts[1][1].getX();
			int deltaY = minDistanceScout.getY() - scouts[1][1].getY();
			for(int a = 0; a < 3; a++) {
				for(int b = 0; b < 3; b++) {
					
					scouts[a][b].setX(scouts[a][b].getX() + deltaX);
					scouts[a][b].setY(scouts[a][b].getY() + deltaY);
					
				}
			}
			if(MidService.backupNode!=null) {
				for(Node element: MidService.backupNode) {
					
					if(element.getX() == scouts[1][1].getX() && element.getY() == scouts[1][1].getY()) {
						

							killThread = true;
							System.out.println("rescue succesful");
//							checkPoint.add(new Node(Driver.path.checkPoint.get(Driver.path.checkPoint.size()-1).getX(),
//									Driver.path.checkPoint.get(Driver.path.checkPoint.size()-1).getY()));

							for(Node node: Driver.path.checkPoint) {
								if(MidService.isLineOfSight(node.getX(), node.getY(), element.getX(), element.getY())==true) {
									System.out.println("here");
									checkPoint.add(new Node(node.getX(), node.getY()));
								}
								break;
							}
							break;
						
		
					}
					
				}
			}
	
			lastCheckPoint = checkPoint.get(checkPoint.size()-1);

			MidService.usedNode.add(new Node(scouts[1][1].getX(), scouts[1][1].getY()));
		
		}
		refineCheckPoint();
		
	}
	
	public void hailMarry() {
		Driver.hailMarry();
		MidService.backupNode = MidService.usedNode;
		MidService.usedNode = new ArrayList<>();
		killThread = true;
	}
	
	public Scout findMinScout() {
		for(int a = 0; a < 3; a++) {
			for(int b = 0; b < 3; b++) {
				
				if(isUsed(scouts[a][b]) == false && scouts[a][b].isCenter() == false && isBlock(scouts[a][b]) == false && isReachable(scouts[1][1], scouts[a][b])==true) {
					return scouts[a][b];
				}
		
			}

		}
		
		return null;
	}
	
	public boolean isUsed(Scout scout) {
		
	
			for(Node element: MidService.usedNode) {
				

				if(element.getX() == scout.getX() && element.getY() == scout.getY()) {

					return true;
				}
				
			}
		
		return false;
	}
	
	public boolean isBlock(Scout scout) {
		
		if(MidService.isOutOfBound(scout.getX(),scout.getY()) == true || GrideService.gride[scout.getX()][scout.getY()] == true) {
			
			return true;
		}

		
		return false;
	}
	public boolean isReachable(Scout scout, Scout target) {
		
		if(MidService.isOutOfBound(target.getX(), target.getY()) == true) {
			return false;
		}
		int deltaX = target.getX() - scout.getX();
		int deltaY = target.getY() - scout.getY();
//		System.out.println(scout.getNode().toString() +", " +target.getNode().toString());
//		System.out.println(deltaX +", " +deltaY);
		if(GrideService.gride[scout.getX() + deltaX][scout.getY()] == true && GrideService.gride[scout.getX()][scout.getY()+deltaY] == true) {
			return false;
		}
		
//		if(MidService.isOutOfBound(scout.getX() + deltaX, scout.getY() + deltaY) == true && MidService.isOutOfBound(scout.getX(), scout.getY() + deltaY)) {
//			return false;
//		}


		return true;
	}
	public static ArrayList<Node> removedNode = new ArrayList<>();
	public void refineCheckPoint() {
		
		for(int a = 0; a < checkPoint.size(); a++) {
			for(int b = a+1; b < checkPoint.size(); b++) {
				
				if(MidService.isLineOfSight(checkPoint.get(a).getX(), 
											checkPoint.get(a).getY(), 
											checkPoint.get(b).getX(), 
											checkPoint.get(b).getY()) == true) 
				{
					for(int i = a+1; i < b-1; i++) {
						removedNode.add(checkPoint.get(i));
						checkPoint.remove(i);
						System.out.println("removed");
					}
				}
			}
		}
		
//		for(int i = 0; i < checkPoint.size(); i++) {
//
//			if(checkPoint.get(i).valid == false) {
//				checkPoint.remove(i);
//			}
//			
//		}
	
	}
	
	


}
