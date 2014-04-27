package com.cf.base.util;

import java.io.File;

import android.os.Environment;

public class SDCardUtil {
	
	public final static String sdCardRootPath = SDCardUtil.getRootPath();
	
	public final static String PROGRAM = "Football-Club";
	public final static String PROGRAM_DATA = "Football-Club/DATA";
	public final static String PROGRAM_EXPORT = "Football-Club/EXPORT";
	
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
		if(sdCardRootPath !=null ){
			return sdCardRootPath;
		}
		StringBuilder pathBuilderProgram = new StringBuilder();
		pathBuilderProgram.append(getSDCardRootPath()).append("/").append(PROGRAM);
		//
		String pathProgram = pathBuilderProgram.toString();
		File fileProgram = new File(pathProgram);   
		if(!fileProgram.exists()){
			fileProgram.mkdir();
		}
		
		StringBuilder pathBuilderFile = new StringBuilder();
		pathBuilderFile.append(getSDCardRootPath()).append("/").append(PROGRAM_DATA);
		// path
		String pathFile = pathBuilderFile.toString();
		File file = new File(pathFile);   
		if(!file.exists()){
			file.mkdir();
		}
		return pathFile;
	}
    
	
	

}
