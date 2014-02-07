package com.android.club;

public class Player extends Point{
	
	
	// 基本属性
	private String number;
	private String position;
	private String level;
	private String description;
	
	// 技能属性
	private int speed = 60;
	private int force = 60;
	private int shoot = 60;
	
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


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public int getForce() {
		return force;
	}


	public void setForce(int force) {
		this.force = force;
	}


	public int getShoot() {
		return shoot;
	}


	public void setShoot(int shoot) {
		this.shoot = shoot;
	}

}
