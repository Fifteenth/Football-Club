package com.android.to;

import com.android.dialog.TeamPlayerDialog;

public class PlayerTO {
	
	final public static String classPath = "com.android.to.PlayerTO";
	
	private String number;
	private String name;
	private String position;
	private String bitmapNumber;
	private String captain;
	private String bitmapCaptain;
	

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBitmapNumber() {
		return bitmapNumber;
	}
	public void setBitmapNumber(String bitmapNumber) {
		this.bitmapNumber = bitmapNumber;
	}
	public String getCaptain() {
		return captain;
	}
	public void setCaptain(String captain) {
		this.captain = captain;
	}
	public String getBitmapCaptain() {
		return bitmapCaptain;
	}
	public void setBitmapCaptain(String bitmapCaptain) {
		this.bitmapCaptain = bitmapCaptain;
	}
	
	
	// teamDialog
	public void setNumber(TeamPlayerDialog teamDialog) {
		String number = String.valueOf(teamDialog.getEditTextNumber().getText());
		setNumber(number);
	}

	public void setPosition(TeamPlayerDialog teamDialog) {
		String position = String.valueOf(teamDialog.getEditTextPosition().getText());
		setPosition(position);
	}

	public void setName(TeamPlayerDialog teamDialog) {
		String name = String.valueOf(teamDialog.getEditTextName().getText());
		setName(name);
	}
}
