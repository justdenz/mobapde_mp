package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.database.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.Objects;

import static android.app.AlertDialog.THEME_DEVICE_DEFAULT_LIGHT;

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

	public void deleteQuiz(View view) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
		builder.setTitle("Delete Quiz");
		builder.setMessage("Are you sure you want to delete this quiz? This cannot be reverted.");
		builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				databaseHandler.deleteQuiz(quiz.getQuizID());
				Intent returnIntent = new Intent();
				setResult(MainActivity.RESULT_OK, returnIntent);
				finish();
			}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

				dialog.cancel();
			}
		});

		AlertDialog alert = builder.create();
		alert.show();

		alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorDelete));
		alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorCancel));


	}
}
