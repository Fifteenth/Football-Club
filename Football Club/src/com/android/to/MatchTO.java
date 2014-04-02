package com.android.to;

import com.android.dialog.MatchDialog;

public class MatchTO {
	
	final public static String classPath = "com.android.to.MatchTO";
	
	private String season;
	private String round; 
	private String score;
	private String competitor;
	private String competitionDate;
	private String weather;
	private String description;
	private String goalPlayers;
	private String assistsPlayers;
	private String goalTimes;
	
	
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getCompetitor() {
		return competitor;
	}
	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}
	public String getCompetitionDate() {
		return competitionDate;
	}
	public void setCompetitionDate(String competitionDate) {
		this.competitionDate = competitionDate;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGoalPlayers() {
		return goalPlayers;
	}
	public void setGoalPlayers(String goalPlayers) {
		this.goalPlayers = goalPlayers;
	}
	public String getAssistsPlayers() {
		return assistsPlayers;
	}
	public void setAssistsPlayers(String assistsPlayers) {
		this.assistsPlayers = assistsPlayers;
	}
	public String getGoalTimes() {
		return goalTimes;
	}
	public void setGoalTimes(String goalTimes) {
		this.goalTimes = goalTimes;
	}
	
	
	// matchDialog
	public void setRound(MatchDialog matchDialog) {
		String round = String.valueOf(matchDialog.getEditTextRound().getText());
		setRound(round);
	}

	public void setScore(MatchDialog matchDialog) {
		String score = String.valueOf(matchDialog.getEditTextScore().getText());
		setScore(score);
	}

	public void setCompetitor(MatchDialog matchDialog) {
		String competitor = String.valueOf(matchDialog.getEditTextCompetitor().getText());
		setCompetitor(competitor);
	}

}
