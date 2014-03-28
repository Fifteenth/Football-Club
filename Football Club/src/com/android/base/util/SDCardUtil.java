package com.android.base.util;

import java.io.File;

import android.os.Environment;

public class SDCardUtil {
	
	public final static String PROGRAM_DATA = "/Football-Club/data";
	public final static String PROGRAM_EXPORT = "/Football-Club/export";
	
	public static String getSDCardRootPath(){
		File dir = Environment.getExternalStorageDirectory();
		return dir.getPath();
	}
	 
	public static boolean sdCardIsExit(){
		return Environment.getExternalStorageState().equals(
	    		android.os.Environment.MEDIA_MOUNTED); //ÅÐ¶Ïsd¿¨ÊÇ·ñ´æÔÚ
	}
	
	public static void CreateRootPath(){
	    if (sdCardIsExit()) {             
	        File newFile= new File(getSDCardRootPath() + "/" + PROGRAM_DATA);  
	        newFile.mkdir();
	    } 
	}
	
	public static String getRootPath(){
		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append(getSDCardRootPath()).append("/").append(PROGRAM_DATA);
		// path
		String path = pathBuilder.toString();
		File file = new File(path);   
		if(!file.exists()){
			file.mkdir();
		}
		return path;
	}
    
	
	

}
