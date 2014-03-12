package com.android.base.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
   public static boolean writeToXml(Context context,String xmlString){ 
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
      }

}
