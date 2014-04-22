package com.cf.to;

import android.app.AlertDialog;

import com.cf.base.ConstantVariable;
import com.cf.dialog.MatchDialog;

public class MatchTO {
	
	final public static String classPath = MatchTO.class.getName();
	
	private String season;
	private String round; 
	private String score;
	private String competitor;
	private String competitionDate;
	private String competitionTime;
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
	public String getCompetitionTime() {
		return competitionTime;
	}
	public void setCompetitionTime(String competitionTime) {
		this.competitionTime = competitionTime;
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
	
	
	
	
	/* 
	 * MatchDialog--Injection Property 
	 * round
	 * score
	 * competitor
	 * competitionDate
	 */
	public void setRound(MatchDialog matchDialog) {
		String round = String.valueOf(matchDialog.getEditTextRound().getText());
		// Handle Different length 
		if(round.length() == 1){
			round = ConstantVariable.SYSBOL_SPAN_TWO + round;
		}
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
	
	public void setCompetitionDate(MatchDialog matchDialog){
		String competitionDate = String.valueOf(matchDialog.getEditTextDate().getText());
		setCompetitionDate(competitionDate);
	}
	
	public void setCompetitionTime(MatchDialog matchDialog){
		String competitionTime = String.valueOf(matchDialog.getEditTextTime().getText());
		setCompetitionTime(competitionTime);
	}

}
