package com.example.cardsagainststupidity.Model;

public class QuizRecord {

	private int quizID;
	private float scorePercentage;
	private int duration;

	public QuizRecord () {

	}

	public QuizRecord (int id, float s, int d) {
		this.quizID = id;
		this.scorePercentage = s;
		this.duration = d;
	}

	public int getQuizID() {
		return quizID;
	}

	public void setQuizID(int quizID) {
		this.quizID = quizID;
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
