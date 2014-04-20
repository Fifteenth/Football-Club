package com.android.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.android.base.ConstantVariable;
import com.android.base.variable.FileVariable;

import android.content.Context;

public class XMLUtil {
	
	public static String PARENT_LEVEL_ONE="one";
	public static String PARENT_LEVEL_TWO="two";
	
	/**
     * 将xml字符串写入xml文件
     * @param context
     * @param str
     * @return
     */ 
   /*public static boolean writeToXml(Context context,String xmlString){ 
          try { 
              OutputStream out=context.openFileOutput("finance_payment.xml", 
            		  Context.MODE_PRIVATE); 
        	  
              
              OutputStreamWriter outw=new OutputStreamWriter(out); 
              try { 
                  outw.write(xmlString); 
                  outw.close(); 
                  out.close(); 
                  return true; 
              } catch (IOException e) { 
                  // TODO Auto-generated catch block 
                  return false; 
              } 
          } catch (FileNotFoundException e) { 
              // TODO Auto-generated catch block 
              return false; 
          } 
      }*/
   
	
	/*
	 * 
	 */
	public static String getXMLAsString(File xmlFile,String fileName){
		try {
			File file = new File(xmlFile,fileName); 
			InputStream inputStream = new FileInputStream(file.getPath());
			StringBuffer outXml = new StringBuffer();
			byte[] b = new byte[4096];
			try {
				for (int n; (n = inputStream.read(b)) != -1;) {
					outXml.append(new String(b, 0, n));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return outXml.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void saveXML(String fileName) throws Exception {
		File xmlFileMatch = new File(SDCardUtil.sdCardRootPath,fileName);
		FileOutputStream xmlOutputStream = FileUtil.getFileOutputStream(xmlFileMatch);
		
		xmlOutputStream.flush();
		xmlOutputStream.close();
	}
	
	public static void saveXML(FileOutputStream xmlOutputStream) throws IOException{
		xmlOutputStream.flush();
		xmlOutputStream.close();
	}

	
	/*
	 * XML Node Name
	 */
	public static String parentLevelMarkName(String level,String classPath){
		String parentLevelMarkName;
		if(PARENT_LEVEL_ONE.equals(level)){
			parentLevelMarkName = classPath.substring(classPath.lastIndexOf(
					ConstantVariable.SYSBOL_PERIOD)+1,classPath.length()) + "s";
		}else{
			parentLevelMarkName = classPath.substring(classPath.lastIndexOf(
					ConstantVariable.SYSBOL_PERIOD)+1,classPath.length());
		}
		return parentLevelMarkName;
	}
}
