package com.Beans;

import java.util.Scanner;

import com.Render.Renderer;
import com.Utilities.MidService;

public class DisplayThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Renderer renderer = new Renderer(MidService.windowX, MidService.windowY);
		while(true) {
			try {
				Thread.sleep(MidService.timer);
				renderer.update();
				
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		
		
	}
}
