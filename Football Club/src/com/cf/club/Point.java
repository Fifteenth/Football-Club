package com.cf.club;

public class Point extends Member{
	
	
	public static String classPath = Point.class.getName();
	public int pointX;
	public int pointY;

	public String steps[] = new String[2];
	
	
	public String[] getSteps() {
		return steps;
	}

	public void setSteps(String[] steps) {
		this.steps = steps;
	}

	// 
	public Point(){
		
	}

	public int getPointX() {
		return pointX;
	}

	public void setPointX(int pointX) {
		this.pointX = pointX;
	}

	public int getPointY() {
		return pointY;
	}

	public void setPointY(int pointY) {
		this.pointY = pointY;
	}
}
