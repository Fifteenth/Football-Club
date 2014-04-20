package com.android.support;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.android.base.util.FileUtil;
import com.android.base.util.ReflectUtil;
import com.android.base.util.SDCardUtil;
import com.android.base.util.XMLUtil;
import com.android.base.variable.FileVariable;
import com.android.service.RWTOService;
import com.android.to.PlayerTO;
import com.android.to.TeamTO;

public class TeamSupport {
	
	private static String classPathPlayerTO = "com.android.to.PlayerTO";
	private static String classPathTeamTO = "com.android.to.TeamTO";
	
	// Team Setting
	public static TeamTO ReadTeamSetting(){
		List list = null;
		try {
			list = RWTOService.getListTOFromXML(classPathTeamTO,FileVariable.FILE_NAME_TEAM_SETTING);
			if(list == null	|| list.size() == 0){
				return new TeamTO();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (TeamTO) list.get(0);
	}
	
	// Team Player 
	public static List ReadTeamPlayer(){
		List list = null;
		try {
			list = RWTOService.getListTOFromXML(classPathPlayerTO,FileVariable.FILE_NAME_TEAM_PLAYERS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void WriteTeamPlayer(List <PlayerTO>list){
		try {
			RWTOService.getWriteXMLFromListTOAndSave(list,classPathPlayerTO, FileVariable.FILE_NAME_TEAM_PLAYERS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	
	// Dialog
	public static void WriteTeamSetting(TeamTO teamTO){
		List list = new ArrayList();
		list.add(teamTO);
		try {
			RWTOService.getWriteXMLFromListTOAndSave(list,classPathTeamTO, FileVariable.FILE_NAME_TEAM_SETTING);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
