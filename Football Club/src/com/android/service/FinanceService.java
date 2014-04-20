package com.android.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.android.base.ConstantVariable;
import com.android.base.util.FileUtil;
import com.android.base.util.SDCardUtil;
import com.android.base.util.XMLUtil;
import com.android.base.variable.TOFieldsVariable;
import com.android.to.FinanceTO;
import android.util.Xml;





public class FinanceService {
	
	
	public static List<FinanceTO> getFinanceTOList(InputStream inputStream,String playerName){
		List<FinanceTO> financeTOList = new ArrayList<FinanceTO>(); 
		int counter = 0; 
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
	        		if(name.equals(TOFieldsVariable.FINANCETO_PLAYER)){   
	        			counter++;   
	        			
	        			FinanceTO financeTO = new FinanceTO();
	        			Object number= pullParser.getAttributeValue(
	        					null,TOFieldsVariable.FINANCETO_NUMBER);
	        			if(number==null){
	        				financeTO.setNumber(0);
	        			}else{
	        				financeTO.setNumber(Integer.valueOf((String)number));
	        			}
	        			financeTO.setName(pullParser.getAttributeValue(
	        					null, TOFieldsVariable.FINANCETO_NAME));
	        			financeTO.setAmount(Integer.valueOf(pullParser.getAttributeValue(
	        					null,TOFieldsVariable.FINANCETO_AMOUNT)));
	        			financeTO.setDescripition(pullParser.getAttributeValue(
	        					null, TOFieldsVariable.FINANCETO_DESCRIPTION));
	        			financeTO.setCurrentTime(pullParser.getAttributeValue(
	        					null,TOFieldsVariable.FINANCETO_TIME));
	        			financeTO.setType(pullParser.getAttributeValue(
	        					null,TOFieldsVariable.FINANCETO_TYPE));
	        			
	        			if(financeTO.getName().equals(playerName)){
	        				financeTOList.add(financeTO);
	        			}
	        			if(playerName == null){
	        				financeTOList.add(financeTO);
	        			}
	        			
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
		
		return financeTOList;
	}
	
	
	//
	public static String getWriteXML(List<FinanceTO> financeTOList, String fileName) 
			throws Exception {
		// 
		File xmlFileMatch = new File(SDCardUtil.sdCardRootPath,fileName);
		FileOutputStream financeOutputStream = FileUtil.getFileOutputStream(xmlFileMatch);
		
        XmlSerializer serializer = Xml.newSerializer();       
        serializer.setOutput(financeOutputStream,"utf-8");
        serializer.startDocument("utf-8", true);                
        serializer.startTag(ConstantVariable.SYSBOL_DOUBLE_QUOTES, "finance");        
        for (FinanceTO financeTO : financeTOList) {
        	// Start
        	serializer.startTag(ConstantVariable.SYSBOL_DOUBLE_QUOTES, "player");
            String name = financeTO.getName();
            // Number
            int number = financeTO.getNumber();
            serializer.attribute(ConstantVariable.SYSBOL_DOUBLE_QUOTES, "number", number+"");
            // Name
            if(name!=null){
            	serializer.attribute(ConstantVariable.SYSBOL_DOUBLE_QUOTES, "name", name);
            }    
            // Amount
            serializer.attribute(ConstantVariable.SYSBOL_DOUBLE_QUOTES, "amount", financeTO.getAmount()+""); 
            // Description
            serializer.attribute(ConstantVariable.SYSBOL_DOUBLE_QUOTES, "description", financeTO.getDescripition()+"");
            // Time
            serializer.attribute(ConstantVariable.SYSBOL_DOUBLE_QUOTES, "time", financeTO.getCurrentTime()+""); 
            // Type
            serializer.attribute(ConstantVariable.SYSBOL_DOUBLE_QUOTES, "type", financeTO.getType()+""); 
            
            // End
            serializer.endTag(ConstantVariable.SYSBOL_DOUBLE_QUOTES, "player");
        }   
        serializer.endTag(ConstantVariable.SYSBOL_DOUBLE_QUOTES, "finance");
        serializer.endDocument();
        
        return financeOutputStream.toString();
    }
	
	public static void getWriteXMLAndSave(List<FinanceTO> financeTOList, String fileName) 
			throws Exception {
		
		
		getWriteXML(financeTOList,fileName);
		XMLUtil.saveXML(fileName);
	}
	
	
/*	public static boolean writeXML(Context context,List<FinanceTO> financeTOList,
			OutputStream out)throws Exception { 
        boolean flag=false; 
        String str=getOutString(financeTOList,out); 
        flag=XMLUtil.writeToXml(context, str); 
        return flag; 
    } */
	
	/**
     * 验证是否存在该文件
     * @return
     * @throws Exception
     */ 
//    public boolean isExist(Context context)throws Exception{ 
//        boolean flag=false; 
//        FileInputStream fs=  context.openFileInput("finance_payment.xml"); 
//        if(fs!=null){ 
//            flag=true; 
//        } 
//        return flag; 
//    } 
	
}
