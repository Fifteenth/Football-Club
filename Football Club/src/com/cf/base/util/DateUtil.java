package com.cf.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	
	public static String getCurrentTimeYYYYMMDDhhmmss(){
		  Date currentTime = new Date();  
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		  String dateString = formatter.format(currentTime);  
		  return dateString;
	}
	
	
	
	public static double gotTimedifferenceBySecond(long t1,long t2) {
		int hours =(int) ((t1 - t2)/3600000.0);
		int minutes =(int) (((t1 - t2)/1000.0 - hours*3600.0)/60.0);
		return (double) ((t1 - t2)/1000.0 - hours*3600.0 - minutes*60.0);
	}
	

}
