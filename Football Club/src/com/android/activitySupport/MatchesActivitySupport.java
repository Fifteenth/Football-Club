package com.android.activitySupport;

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
	private static String FILE_NAME_MATCH = "DATA_MATCHES.XML";
	
	static String sdCardRootPath = SDCardUtil.getRootPath();
	
	public static List ReadMatches(){
		List list = null;
		InputStream inputStreamFinance = FileUtil.getFileInputStream(
				new File(sdCardRootPath,FILE_NAME_MATCH));
		try {
			list = RWTOService.getListTOFromXML(classPath,inputStreamFinance,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void WriteMatches(){
		List list = new ArrayList();
		
		// **Test Date
		MatchTO matchTO = new MatchTO();
		String assistsPlayers  = "涛哥,曹五,曹五";
		matchTO.setAssistsPlayers(assistsPlayers);
		matchTO.setCompetitionDate("2014-03-04 12:00:00");
		matchTO.setCompetitor("高次元");
		matchTO.setDescription("秒杀对手");
		matchTO.setGoalPlayers("涛哥,二哥,二哥");
		matchTO.setGoalTimes("33,44,66");
		matchTO.setRound("第一轮");
		matchTO.setScore("3:0");
		matchTO.setSeason("2014-2015");
		matchTO.setWeather("晴");
		list.add(matchTO);
		// **
		
		File xmlFileMatch = new File(sdCardRootPath,MatchesActivitySupport.FILE_NAME_MATCH);
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
