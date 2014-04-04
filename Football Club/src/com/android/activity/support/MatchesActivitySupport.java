package com.android.activity.support;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.android.base.util.FileUtil;
import com.android.base.util.ReflectUtil;
import com.android.base.util.SDCardUtil;
import com.android.base.util.XMLUtil;
import com.android.base.variable.XMLVariable;
import com.android.service.RWTOService;
import com.android.to.MatchTO;

public class MatchesActivitySupport {
	
	private static String classPath = "com.android.to.MatchTO";
	static String sdCardRootPath = SDCardUtil.getRootPath();
	
	public static List ReadMatches(){
		List list = null;
		InputStream inputStreamFinance = FileUtil.getFileInputStream(
				new File(sdCardRootPath,XMLVariable.FILE_NAME_MATCH));
		try {
			list = RWTOService.getListTOFromXML(classPath,inputStreamFinance,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void WriteMatches(List <MatchTO>list){
		File xmlFileMatch = new File(sdCardRootPath,XMLVariable.FILE_NAME_MATCH);
		FileOutputStream financePaymentXmlOutputStream = FileUtil.getFileOutputStream(xmlFileMatch);
		try {
			RWTOService.getWriteXMLFromListTO(list,classPath, financePaymentXmlOutputStream);
			XMLUtil.saveXML(financePaymentXmlOutputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
