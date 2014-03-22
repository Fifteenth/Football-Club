package com.android.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.content.Context;

public class XMLUtil {
	
	
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
	
	public static void saveXML(OutputStream xmlOutputStream)
			throws Exception {
		xmlOutputStream.flush();
        xmlOutputStream.close();
	}

}
