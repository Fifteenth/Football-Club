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
import com.android.base.variable.TOFieldsVariable;
import com.android.to.FinanceTO;
import android.util.Xml;





public class FinanceService {
	
	
	public static List<FinanceTO> getFinanceTOList(InputStream inputStream){
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
	        			financeTO.setNumber(Integer.valueOf(pullParser.getAttributeValue(
	        					null,TOFieldsVariable.FINANCETO_NUMBER)));
	        			financeTO.setName(pullParser.getAttributeValue(
	        					null, TOFieldsVariable.FINANCETO_NAME));
	        			financeTO.setAmount(Integer.valueOf(pullParser.getAttributeValue(
	        					null,TOFieldsVariable.FINANCETO_AMOUNT)));
	        			
	        			financeTOList.add(financeTO);
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
	public static String getOutString(List<FinanceTO> financeTOList, OutputStream out) throws Exception {
        XmlSerializer serializer = Xml.newSerializer();       
        StringWriter writer=new StringWriter();
        
//        serializer.setOutput(writer);
        
        serializer.setOutput(out,"utf-8");
        
        serializer.startDocument("utf-8", true);                
        serializer.startTag("", "finance");        
        for (FinanceTO financeTO : financeTOList) {
        	serializer.startTag("", "player");
            String name = financeTO.getName();
            if(name!=null){
            	serializer.attribute("", "name", name);
            }          
            serializer.attribute("", "amount", financeTO.getAmount()+""); 
            serializer.endTag("", "player");
        }   
        serializer.endTag("", "finance");
        serializer.endDocument();
        
        out.flush();
        out.close();
        
        return out.toString();
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
