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

}
