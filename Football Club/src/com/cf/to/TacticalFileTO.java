package com.cf.to;

public class TacticalFileTO {

	public static String classPath = TacticalFileTO.class.getName();
	
	private String number;
	private String role;//a,b,c
	private String saveNum;//saveNum
	private String formation;
	private String describtion;
	private String datetime;
	
	private String pathAllStepAllJSon;// JSon String
	private String pathAllTotal;
	private String pathXStepTotalJSon;
	private String type;

	public TacticalFileTO(){
		
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
	
	public String getPathAllStepAllJSon() {
		return pathAllStepAllJSon;
	}

	public void setPathAllStepAllJSon(String pathAllStepAllJSon) {
		this.pathAllStepAllJSon = pathAllStepAllJSon;
	}
	
	public String getPathAllTotal() {
		return pathAllTotal;
	}

	public void setPathAllTotal(String pathAllTotal) {
		this.pathAllTotal = pathAllTotal;
	}

	public String getPathXStepTotalJSon() {
		return pathXStepTotalJSon;
	}

	public void setPathXStepTotalJSon(String pathXStepTotalJSon) {
		this.pathXStepTotalJSon = pathXStepTotalJSon;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
