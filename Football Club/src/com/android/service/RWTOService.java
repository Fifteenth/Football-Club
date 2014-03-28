package com.android.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.android.base.ConstantVariable;
import com.android.base.util.ReflectUtil;
import com.android.base.variable.TOFieldsVariable;
import com.android.to.FinanceTO;

public class RWTOService {
	private static String PARENT_LEVEL_ONE="one";
	private static String PARENT_LEVEL_TWO="two";
	private static String METHOD_GET = "get";
	private static String METHOD_SET = "set";
	
	/*
	 * Read
	 * 	 playerName is filter name,it can be null
	 */
	public static List<?> getListTOFromXML(String classPath,InputStream inputStream,
			String playerName) throws Exception{
		List list = new ArrayList();
		String parentTwoMarkName = classPath.substring(classPath.lastIndexOf(
				ConstantVariable.SYSBOL_PERIOD)+1,classPath.length());
		try {   
            //如果没有到文件尾继续执行   
			XmlPullParser pullParser = Xml.newPullParser();
			pullParser.setInput(inputStream, "UTF-8"); //为Pull解释器设置要解析的XML数据
			int event = pullParser.getEventType();
	        while (event != XmlPullParser.END_DOCUMENT) {    
	            //如果是开始标签   
	        	if (event == XmlPullParser.START_TAG) {   
			      //获取标签名称   
	        		String name = pullParser.getName();   
	        		//判断标签名称是否等于friend   
	        		if(name.equals(parentLevelMarkName(PARENT_LEVEL_TWO,classPath))){   
	        			Class<?> dynamicClass = Class.forName(classPath);
	        			Object dynamicTO = dynamicClass.newInstance();
	        			// Get All SetMethod
	        			String setMethodNames[] = ReflectUtil.getAllSetOrGetMethodNames(
	        					ReflectUtil.METHOD_START_SET,classPath);
	        			for(int methodIndex=0;methodIndex<setMethodNames.length;methodIndex++){
	        				String getMethodName = setMethodNames[methodIndex];
	        				String nodeProperty = getMethodName.substring(
	        						METHOD_SET.length(), getMethodName.length());
	        				String nodeValue = pullParser.getAttributeValue(
		        					null,nodeProperty);
	        				if(nodeValue!=null){
	        					Method getMethod = dynamicClass.getMethod(setMethodNames[methodIndex],String.class);
	        					getMethod.invoke(dynamicTO,nodeValue);
	        				}
	        			}
	        			list.add(dynamicTO);
	        		}   
	        	} else if (event == XmlPullParser.END_TAG) {    
			  
	        	} else if (event == XmlPullParser.TEXT) {    
			  
	        	}    
	        	//下一个标签   
	        	event = pullParser.next();    
	        }   
		} catch (XmlPullParserException e) {   
			e.printStackTrace();   
		} catch (IOException e) {   
			e.printStackTrace();   
		}   
		return list;
	}
	
	/*
	 * Write
	 */
	public static String getWriteXMLFromListTO(List<?>listTO,String classPath, 
			OutputStream xmlOutputStream) throws Exception {
		String parentOneMarkName = parentLevelMarkName(PARENT_LEVEL_ONE,classPath);
		String parentTwoMarkName = parentLevelMarkName(PARENT_LEVEL_TWO,classPath);
		Class<?> dynamicClass = Class.forName(classPath);
		// Get All GetMethod
		String getMethodNames[] = ReflectUtil.getAllSetOrGetMethodNames(
				ReflectUtil.METHOD_START_GET,classPath);
		
        XmlSerializer serializer = Xml.newSerializer();       
        serializer.setOutput(xmlOutputStream,"utf-8");
        serializer.startDocument("utf-8", true);                
        serializer.startTag(ConstantVariable.SYSBOL_DOUBLE_QUOTES, parentOneMarkName);   
        
        for (int toIndex=0;toIndex<listTO.size();toIndex++) {
        	// Start
        	serializer.startTag(ConstantVariable.SYSBOL_DOUBLE_QUOTES, parentTwoMarkName);
        	for(int methodIndex=0;methodIndex<getMethodNames.length;methodIndex++){
        		// Get
        		Method getMethod = dynamicClass.getMethod(getMethodNames[methodIndex]); 
        		String nodeValue = (String) getMethod.invoke(listTO.get(toIndex));
        		String nodeProperty = getMethod.getName();
        		if(nodeValue!=null){
        			serializer.attribute(ConstantVariable.SYSBOL_DOUBLE_QUOTES, nodeProperty.substring(METHOD_GET.length(), nodeProperty.length()), nodeValue);	
        		}
        	}
            // End
            serializer.endTag(ConstantVariable.SYSBOL_DOUBLE_QUOTES, parentTwoMarkName);
        }   
        serializer.endTag(ConstantVariable.SYSBOL_DOUBLE_QUOTES, parentOneMarkName);
        serializer.endDocument();
        
        return xmlOutputStream.toString();
    }
	
	
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
