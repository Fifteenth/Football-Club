package com.cf.club;

import org.json.JSONObject;

import com.cf.to.Point;

import android.graphics.Bitmap;

public class Player extends Point{
	
	public static String classPath = Player.class.getName();
	// 基本属性
	private String number;
	private String position;
	private String level;
	private String description;
	
	// 技能属性
	private String speed = "60";
	private String force = "60";
	private String shoot = "60";
	
	private int pathAllTotal = 0;
	private JSONObject pathAllStepAllJSon;
	private JSONObject pathXStepTotalJSon;
	
	private Bitmap playerBitmap;
	private Boolean isSelect = false;
	
	private int stepPoint[];

	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getSpeed() {
		return speed;
	}


	public void setSpeed(String speed) {
		this.speed = speed;
	}


	public String getForce() {
		return force;
	}


	public void setForce(String force) {
		this.force = force;
	}


	public String getShoot() {
		return shoot;
	}


	public void setShoot(String shoot) {
		this.shoot = shoot;
	}
	
	
	public Bitmap getPlayerBitmap() {
		return playerBitmap;
	}

	
	public void setPlayerBitmap(Bitmap playerBitmap) {
		this.playerBitmap = playerBitmap;
	}
	
	
	public Boolean gotIsSelect() {
		return isSelect;
	}

	public void sotIsSelect(Boolean isSelect) {
		this.isSelect = isSelect;
	}
	
	public int getPathAllTotal() {
		return pathAllTotal;
	}

	public void setPathAllTotal(int pathAllTotal) {
		this.pathAllTotal = pathAllTotal;
	}
	
	public JSONObject getPathAllStepAllJSon() {
		return pathAllStepAllJSon;
	}

	public void setPathAllStepAllJSon(JSONObject pathAllStepAllJSon) {
		this.pathAllStepAllJSon = pathAllStepAllJSon;
	}

	public JSONObject getPathXStepTotalJSon() {
		return pathXStepTotalJSon;
	}

	public void setPathXStepTotalJSon(JSONObject pathXStepTotalJSon) {
		this.pathXStepTotalJSon = pathXStepTotalJSon;
	}
	

	public int[] gotStepPoint() {
		return stepPoint;
	}


	public void sotStepPoint(int[] stepPoint) {
		this.stepPoint = stepPoint;
	}
}
