package com.cf.to;

import com.cf.club.Player;

public class TacticalFileTO {

	public static String classPath = TacticalFileTO.class.getName();
	
	private String number;
	private String stepX;
	private String stepY;
	
	private String role;//a,b,c
	private String saveNum;//saveNum
	private String formation;
	private String describtion;
	private String datetime;
	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TacticalFileTO(){
		
	}
	
	public TacticalFileTO(Player player){
		number = player.getNumber();
		
		if(player.steps != null 
				&& player.steps[0] != null
				&& !player.steps[0].equals("") ){
			stepX = player.steps[0];
			stepY = player.steps[1];
		}else{
			stepX = player.getStepX();
			stepY = player.getStepY();
		}
	}
	
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStepX() {
		return stepX;
	}

	public void setStepX(String stepX) {
		this.stepX = stepX;
	}

	public String getStepY() {
		return stepY;
	}

	public void setStepY(String stepY) {
		this.stepY = stepY;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSaveNum() {
		return saveNum;
	}

	public void setSaveNum(String saveNum) {
		this.saveNum = saveNum;
	}
	
	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public String getDescribtion() {
		return describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}
	
	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
}
