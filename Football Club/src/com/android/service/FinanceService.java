package com.android.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

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
	public static String getWriteXML(List<FinanceTO> financeTOList, OutputStream xmlOutputStream) 
			throws Exception {
        XmlSerializer serializer = Xml.newSerializer();       
        serializer.setOutput(xmlOutputStream,"utf-8");
        serializer.startDocument("utf-8", true);                
        serializer.startTag("", "finance");        
        for (FinanceTO financeTO : financeTOList) {
        	// Start
        	serializer.startTag("", "player");
            String name = financeTO.getName();
            // Number
            int number = financeTO.getNumber();
            serializer.attribute("", "number", number+"");
            // Name
            if(name!=null){
            	serializer.attribute("", "name", name);
            }    
            // Amount
            serializer.attribute("", "amount", financeTO.getAmount()+""); 
            // Time
            serializer.attribute("", "time", financeTO.getCurrentTime()+""); 
            // Type
            serializer.attribute("", "type", financeTO.getType()+""); 
            
            // End
            serializer.endTag("", "player");
        }   
        serializer.endTag("", "finance");
        serializer.endDocument();
        
        return xmlOutputStream.toString();
    }
	
	public static void getWriteXMLAndSave(List<FinanceTO> financeTOList, OutputStream xmlOutputStream) 
			throws Exception {
		getWriteXML(financeTOList,xmlOutputStream);
		XMLUtil.saveXML(xmlOutputStream);
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
