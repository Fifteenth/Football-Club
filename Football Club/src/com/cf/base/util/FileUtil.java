package com.cf.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {
	
	public static FileOutputStream getFileOutputStream(File file){
		FileOutputStream fileOutputStream = null;
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			fileOutputStream = new FileOutputStream(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileOutputStream;
	}
	
	
	public static FileInputStream getFileInputStream(File file){
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			return new FileInputStream(file.getPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
