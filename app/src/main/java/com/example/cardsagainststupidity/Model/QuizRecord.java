package com.example.cardsagainststupidity.Model;

import java.util.Date;

public class QuizRecord {

	private int recordID;
	private Quiz quiz;
	private float scorePercentage;
	private int duration;
	private Date date_taken;

	public QuizRecord () {
		quiz = new Quiz();
	}

	public int getRecordID() {
		return recordID;
	}

	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

	public void setQuizID (int id) {
		quiz.setQuizID(id);
	}

	public void setQuizTitle (String title) {
		quiz.setTitle(title);
	}


	public Date getDate_taken() {
		return date_taken;
	}

	public void setDate_taken(Date date_taken) {
		this.date_taken = date_taken;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public float getScorePercentage() {
		return scorePercentage;
	}

	public void setScorePercentage(float scorePercentage) {
		this.scorePercentage = scorePercentage;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
