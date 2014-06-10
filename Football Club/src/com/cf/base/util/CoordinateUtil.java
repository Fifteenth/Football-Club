package com.cf.base.util;

import com.cf.to.Point;

public class CoordinateUtil {

	public static float ComputingDistance(Point pointStart,Point pointEnd){
		float dx = pointEnd.getPointX() - pointStart.getPointX();
		float dy = pointEnd.getPointY() - pointStart.getPointY();
		
		return (float) Math.sqrt(Double.valueOf(dx * dx + dy * dy));
	}
}
