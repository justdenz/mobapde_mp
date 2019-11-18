package com.example.cardsagainststupidity.Model;

public class QuizRecord {

	private int recordID;
	private Quiz quiz;
	private float scorePercentage;
	private int duration;

	public QuizRecord () {
		quiz = new Quiz();
	}

	public QuizRecord (Quiz quiz, float s, int d) {
		this.quiz = quiz;
		this.scorePercentage = s;
		this.duration = d;
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
