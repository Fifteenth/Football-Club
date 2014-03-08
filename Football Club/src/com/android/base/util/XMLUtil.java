package com.android.base.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.android.base.variable.TOFieldsVariable;
import com.android.to.FinanceTO;

import android.content.res.XmlResourceParser;





public class XMLUtil {
	
	
	public static List<FinanceTO> getFinanceTOList(XmlResourceParser xrp){
		List<FinanceTO> financeTOList = new ArrayList<FinanceTO>(); 
		int counter = 0; 
		try {   
            //���û�е��ļ�β����ִ��   
	        while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {    
	            //����ǿ�ʼ��ǩ   
	        	if (xrp.getEventType() == XmlResourceParser.START_TAG) {   
			      //��ȡ��ǩ����   
	        		String name = xrp.getName();   
	        		//�жϱ�ǩ�����Ƿ����friend   
	        		if(name.equals(TOFieldsVariable.FINANCETO_PLAYER)){   
	        			counter++;   
	        			
	        			FinanceTO financeTO = new FinanceTO();
	        			financeTO.setNumber(xrp.getAttributeIntValue(
	        					null,TOFieldsVariable.FINANCETO_NUMBER, 0));
	        			financeTO.setName(xrp.getAttributeValue(
	        					null, TOFieldsVariable.FINANCETO_NAME));
	        			financeTO.setAmount(xrp.getAttributeIntValue(
	        					null,TOFieldsVariable.FINANCETO_AMOUNT, 0));
	        			
	        			financeTOList.add(financeTO);
	        		}   
	        	} else if (xrp.getEventType() == XmlPullParser.END_TAG) {    
			  
	        	} else if (xrp.getEventType() == XmlPullParser.TEXT) {    
			  
	        	}    
	        	//��һ����ǩ   
	        	xrp.next();    
	        }   
		} catch (XmlPullParserException e) {   
			e.printStackTrace();   
		} catch (IOException e) {   
			e.printStackTrace();   
		}   
		
		return financeTOList;
	}
}
