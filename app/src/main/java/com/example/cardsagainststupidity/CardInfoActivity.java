package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.database.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class CardInfoActivity extends AppCompatActivity {

	Quiz quiz;
	DatabaseHandler databaseHandler;
	TextView titleTxtView, subjectTxtView, descriptionTxtView, dateTxtView, nFlashcardsTxtView;
	Button editBtn, takeQuizBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_info);
		Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

		int id = getIntent().getIntExtra("ID", -1);


		databaseHandler = new DatabaseHandler(this);

		quiz = databaseHandler.getQuiz(id);

		titleTxtView = findViewById(R.id.titleTxtView);
		subjectTxtView = findViewById(R.id.subjectTxtView);
		descriptionTxtView = findViewById(R.id.descriptionTxtView);
		nFlashcardsTxtView = findViewById(R.id.nFlashcardsTxtView);
		dateTxtView = findViewById(R.id.dateTxtView);
		editBtn = findViewById(R.id.editBtn);
		takeQuizBtn = findViewById(R.id.takeQuizBtn);

		//float highscore = databaseHandler.getHighscoreByQuizID(id);

		SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");

		titleTxtView.setText(quiz.getTitle());
		subjectTxtView.setText(quiz.getSubject());
		descriptionTxtView.setText(quiz.getDescription());
		dateTxtView.setText(formatter.format(quiz.getDate_created()));
		nFlashcardsTxtView.setText(quiz.getDeck().size() + " Flashcards");

	}
}
