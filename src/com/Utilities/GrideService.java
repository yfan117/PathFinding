package com.Utilities;

public class GrideService {
	
	public static boolean[][] gride = new boolean[MidService.numGrideX][MidService.numGrideY];
	
	public static void changeGride(int[] click, boolean result) {
		
		int[] grideLocation = convertToGrideLocation(click);
		
		gride[grideLocation[0]][grideLocation[1]] = result;

		
	}
	
	public static boolean getGrideStatus(int x, int y) {
		
		return gride[x][y];
	}
	
	public static int[] convertToGrideLocation(int[] click) {

		return new int[]{click[0]/MidService.grideSize, click[1]/MidService.grideSize};
	}

}
