package com.example.cardsagainststupidity;

import com.example.cardsagainststupidity.Model.QuizRecord;

import java.util.ArrayList;

public class UserStatsHelper {

	private ArrayList<QuizRecord> history;

	public UserStatsHelper() {

	}

	public UserStatsHelper(ArrayList<QuizRecord> history) {
		this.history = history;
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
