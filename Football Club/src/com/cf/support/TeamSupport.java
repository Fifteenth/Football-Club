package com.cf.support;

import java.util.ArrayList;
import java.util.List;

import com.cf.base.variable.FileVariable;
import com.cf.service.RWTOService;
import com.cf.to.PlayerTO;
import com.cf.to.TeamTO;

public class TeamSupport {
	
	// Team Setting
	public static TeamTO ReadTeamSetting(){
		List list = null;
		try {
			list = RWTOService.getListTOFromXML(TeamTO.classPath,FileVariable.FILE_NAME_TEAM_SETTING);
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
			list = RWTOService.getListTOFromXML(PlayerTO.classPath,FileVariable.FILE_NAME_TEAM_PLAYERS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void WriteTeamPlayer(List <PlayerTO>list){
		try {
			RWTOService.getWriteXMLFromListTOAndSave(list,PlayerTO.classPath, FileVariable.FILE_NAME_TEAM_PLAYERS);
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
			RWTOService.getWriteXMLFromListTOAndSave(list,TeamTO.classPath, FileVariable.FILE_NAME_TEAM_SETTING);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
