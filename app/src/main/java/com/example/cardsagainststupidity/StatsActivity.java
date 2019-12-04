package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.Model.QuizRecord;
import com.example.cardsagainststupidity.Model.Statistics;
import com.example.cardsagainststupidity.adapter.QuizCardAdapter;
import com.example.cardsagainststupidity.adapter.QuizHistoryAdapter;
import com.example.cardsagainststupidity.database.DatabaseHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

import static com.example.cardsagainststupidity.MainActivity.TAKE_QUIZ;

public class StatsActivity extends AppCompatActivity {

	Statistics stats;
	RecyclerView recyclerView;
	QuizHistoryAdapter quizHistoryAdapter;
	DatabaseHandler databaseHandler;
	TextView averageDurationTxtView, averageScoreTxtView, totalQuizzesTxtView;
	SharedPreferences sharedPref;

//	Statistics s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

		databaseHandler = new DatabaseHandler(this);
		stats = databaseHandler.getStatistics();

		sharedPref = PreferenceManager
				.getDefaultSharedPreferences(this);

		averageDurationTxtView = findViewById(R.id.averageDurationTxtView);
		averageScoreTxtView = findViewById(R.id.averageScoreTxtView);
		totalQuizzesTxtView = findViewById(R.id.totalQuizzesTxtView);

		averageDurationTxtView.setText(stats.getAverageQuizTime() + " seconds");

		DecimalFormat df = new DecimalFormat("#.##");
		averageScoreTxtView.setText(df.format(stats.getAverageQuizPercentage()) + "%");
		totalQuizzesTxtView.setText(stats.getTotalQuizzesTaken() + "");

//		Quiz q = new Quiz();
//		q.setQuizID(1);
//		q.setTitle("TEST");
//
//		QuizRecord r = new QuizRecord();
//		r.setQuiz(q);
//
//		ArrayList<QuizRecord> history = new ArrayList<>();
//		history.add(r);
//
//		s = new Statistics();
//		s.setHistory(history);


		initRecyclerView();
	}

	private void initRecyclerView(){

		recyclerView = findViewById(R.id.recycler_view_pinned);
		quizHistoryAdapter = new QuizHistoryAdapter(this, stats.getHistory());
		recyclerView.setAdapter(quizHistoryAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}

	public void takeQuiz(int quizID) {

		float oldRecord = databaseHandler.getHighscoreByQuizID(quizID);

		Intent intent = new Intent(this, TakeQuizActivity.class);
		intent.putExtra("QUIZ_ID", quizID);
		intent.putExtra("TIMER_COUNT", Integer.parseInt(sharedPref.getString("timer_count", "0")));
		intent.putExtra("OLD_RECORD", oldRecord);
		startActivityForResult(intent, TAKE_QUIZ);


	}

}
