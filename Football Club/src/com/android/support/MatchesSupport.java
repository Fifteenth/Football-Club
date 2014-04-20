package com.android.support;

import java.util.List;

import com.android.base.variable.FileVariable;
import com.android.service.RWTOService;
import com.android.to.MatchTO;

public class MatchesSupport {
	
	private static String classPath = "com.android.to.MatchTO";
	
	public static List ReadMatches(){
		List list = null;
		try {
			list = RWTOService.getListTOFromXML(classPath,FileVariable.FILE_NAME_MATCH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void WriteMatches(List <MatchTO>list){
		try {
			RWTOService.getWriteXMLFromListTOAndSave(list,classPath, FileVariable.FILE_NAME_MATCH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
