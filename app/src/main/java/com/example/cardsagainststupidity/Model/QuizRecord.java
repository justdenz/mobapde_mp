package com.example.cardsagainststupidity.Model;

public class QuizRecord {

	private Quiz quiz;
	private float scorePercentage;
	private int duration;

	public QuizRecord () {

	}

	public QuizRecord (Quiz quiz, float s, int d) {
		this.quiz = quiz;
		this.scorePercentage = s;
		this.duration = d;
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
