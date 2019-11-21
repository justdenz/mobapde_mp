package com.example.cardsagainststupidity.Model;

import com.example.cardsagainststupidity.Model.QuizRecord;

import java.util.ArrayList;
import java.util.List;


public class Statistics {

	private ArrayList<QuizRecord> history;

	public Statistics() {

	}

	public Statistics(ArrayList<QuizRecord> history) {
		this.history =  history;
	}

	public ArrayList<QuizRecord> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<QuizRecord> history) {
		this.history = history;
	}

	public int getTotalQuizzesTaken () {
		return history.size();
	}

	public float averageQuizPercentage () {

		float totalPercent = 0;

		for (QuizRecord r : history) {
			totalPercent += r.getScorePercentage();
		}

		return totalPercent / history.size();
	}

	public float averageQuizTime() {

		float totalTime = 0;

		for (QuizRecord r : history) {
			totalTime += r.getDuration();
		}

		return totalTime / history.size();
	}


}
