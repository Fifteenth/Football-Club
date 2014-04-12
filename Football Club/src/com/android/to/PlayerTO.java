package com.android.to;

import com.android.dialog.TeamDialog;

public class PlayerTO {
	
	final public static String classPath = "com.android.to.PlayerTO";
	
	private String number;
	private String name;
	private String position;
	private String bitmapNumber;
	private String isCaptain;
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
	public String isCaptain() {
		return isCaptain;
	}
	public void setCaptain(String isCaptain) {
		this.isCaptain = isCaptain;
	}
	public String getBitmapCaptain() {
		return bitmapCaptain;
	}
	public void setBitmapCaptain(String bitmapCaptain) {
		this.bitmapCaptain = bitmapCaptain;
	}
	
	
	// teamDialog
	public void setNumber(TeamDialog teamDialog) {
		String number = String.valueOf(teamDialog.getEditTextNumber().getText());
		setNumber(number);
	}

	public void setPosition(TeamDialog teamDialog) {
		String position = String.valueOf(teamDialog.getEditTextPosition().getText());
		setPosition(position);
	}

	public void setName(TeamDialog teamDialog) {
		String name = String.valueOf(teamDialog.getEditTextName().getText());
		setName(name);
	}
}
