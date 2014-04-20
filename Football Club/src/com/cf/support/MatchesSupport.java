package com.cf.support;

import java.util.List;

import com.cf.base.variable.FileVariable;
import com.cf.service.RWTOService;
import com.cf.to.MatchTO;

public class MatchesSupport {
	
	public static List ReadMatches(){
		List list = null;
		try {
			list = RWTOService.getListTOFromXML(MatchTO.classPath,FileVariable.FILE_NAME_MATCH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void WriteMatches(List <MatchTO>list){
		try {
			RWTOService.getWriteXMLFromListTOAndSave(list,MatchTO.classPath, FileVariable.FILE_NAME_MATCH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
