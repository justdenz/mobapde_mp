package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cardsagainststupidity.Model.QuizRecord;
import com.example.cardsagainststupidity.Model.Statistics;
import com.example.cardsagainststupidity.adapter.QuizCardAdapter;
import com.example.cardsagainststupidity.adapter.QuizHistoryAdapter;
import com.example.cardsagainststupidity.database.DatabaseHandler;

import java.util.ArrayList;
import java.util.Objects;

public class StatsActivity extends AppCompatActivity {

	Statistics stats;
	RecyclerView recyclerView;
	QuizHistoryAdapter quizHistoryAdapter;
	DatabaseHandler databaseHandler;
	TextView averageDurationTxtView, averageScoreTxtView, totalQuizzesTxtView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

		databaseHandler = new DatabaseHandler(this);
		stats = databaseHandler.getStatistics();

		averageDurationTxtView = findViewById(R.id.averageDurationTxtView);
		averageScoreTxtView = findViewById(R.id.averageScoreTxtView);
		totalQuizzesTxtView = findViewById(R.id.totalQuizzesTxtView);

		averageDurationTxtView.setText("" + stats.getAverageQuizTime());
		averageScoreTxtView.setText("" + stats.getAverageQuizPercentage());
		totalQuizzesTxtView.setText("" + stats.getTotalQuizzesTaken());

		initRecyclerView();
	}

	private void initRecyclerView(){

		recyclerView = findViewById(R.id.recycler_view_pinned);
		quizHistoryAdapter = new QuizHistoryAdapter(this, stats.getHistory());
		recyclerView.setAdapter(quizHistoryAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}

	public void takeQuiz(View view) {


	}
}
