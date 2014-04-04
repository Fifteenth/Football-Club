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
import com.android.to.TeamTO;

public class TeamActivitySupport {
	
	private static String classPath = "com.android.to.TeamTO";
	static String sdCardRootPath = SDCardUtil.getRootPath();
	
	public static List ReadTeam(){
		List list = null;
		InputStream inputStreamFinance = FileUtil.getFileInputStream(
				new File(sdCardRootPath,XMLVariable.FILE_NAME_TEAM));
		try {
			list = RWTOService.getListTOFromXML(classPath,inputStreamFinance,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void WriteTeam(List <TeamTO>list){
		File xmlFileTeam = new File(sdCardRootPath,XMLVariable.FILE_NAME_TEAM);
		FileOutputStream financePaymentXmlOutputStream = FileUtil.getFileOutputStream(xmlFileTeam);
		try {
			RWTOService.getWriteXMLFromListTO(list,classPath, financePaymentXmlOutputStream);
			XMLUtil.saveXML(financePaymentXmlOutputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
