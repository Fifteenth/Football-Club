package com.cf.club;

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
	
	
	private String stepX;
	private String stepY;
	
	private Bitmap playerBitmap;
	
	
	private Boolean isSelect = false;
	
	// 
	public Player(){
	}

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

	public String getStepX() {
		if(stepX != null){
			return stepX;
		}
		if(steps[0] == null){
			return String.valueOf(pointX);
		}
		return steps[0];
	}

	public void setStepX(String stepX) {
		this.stepX = stepX;
	}

	public String getStepY() {
		if(stepY != null){
			return stepY;
		}
		if(steps[1] == null){
			return String.valueOf(pointY);
		}
		return steps[1];
	}

	public void setStepY(String stepY) {
		this.stepY = stepY;
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
}
