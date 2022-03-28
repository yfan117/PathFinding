package com.Driver;

import com.Beans.DisplayThread;
import com.Beans.PathFinding;
import com.Controller.MouseController;
import com.Render.Renderer;
import com.Utilities.MidService;

public class Driver {
	
	public static Renderer renderer;
	public static MouseController mouseController = new MouseController();
//	public static DisplayThread display = new DisplayThread();
//	public static PathFinding pathFind = new PathFinding();
	public static boolean findPath = false;
	public static void main(String[] args) {

//		Thread thread = new Thread(display);
//		thread.start();
//		


		renderer = new Renderer(MidService.windowX, MidService.windowY);
		
		while(true) {
			//if(findPath == false) 
			{
				try {
					Thread.sleep(MidService.timer);
					renderer.update();
					
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

		}

	}
	public static PathFinding path;
 	public static PathFinding path2;
	public static void findPath() {
		path = new PathFinding(MidService.beginLocation[0],MidService.beginLocation[1],
				MidService.endLocation[0],MidService.endLocation[1]);
		Thread thread = new Thread(path);
		thread.start();

	}
	
	public static void hailMarry() {
		path2 = new PathFinding(MidService.endLocation[0],MidService.endLocation[1],
				MidService.beginLocation[0],MidService.beginLocation[1]);
		Thread thread = new Thread(path2);
		thread.start();
	}
	


}
