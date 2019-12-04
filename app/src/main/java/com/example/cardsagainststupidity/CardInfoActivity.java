package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.database.DatabaseHandler;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.SimpleDateFormat;
import java.util.Objects;

import static android.app.AlertDialog.THEME_DEVICE_DEFAULT_LIGHT;
import static com.example.cardsagainststupidity.MainActivity.MODIFY_QUIZ;
import static com.example.cardsagainststupidity.MainActivity.TAKE_QUIZ;


public class CardInfoActivity extends AppCompatActivity {

	Quiz quiz;
	DatabaseHandler databaseHandler;
	TextView titleTxtView, subjectTxtView, scorePercentageTxtView,descriptionTxtView, dateTxtView, nFlashcardsTxtView;
	Button editBtn, takeQuizBtn;
	SimpleDateFormat formatter;
	EditText secondsInput;
	SharedPreferences sharedPref;
	CircularProgressBar circularProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_info);
		Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

		int id = getIntent().getIntExtra("ID", -1);
		sharedPref = PreferenceManager
				.getDefaultSharedPreferences(this);


		databaseHandler = new DatabaseHandler(this);

		quiz = databaseHandler.getQuiz(id);

		titleTxtView = findViewById(R.id.titleTxtView);
		subjectTxtView = findViewById(R.id.subjectTxtView);
		descriptionTxtView = findViewById(R.id.descriptionTxtView);
		nFlashcardsTxtView = findViewById(R.id.nFlashcardsTxtView);
		dateTxtView = findViewById(R.id.dateTxtView);
		editBtn = findViewById(R.id.editBtn);
		takeQuizBtn = findViewById(R.id.takeQuizBtn);
		circularProgressBar = findViewById(R.id.circularProgressBar);
		scorePercentageTxtView = findViewById(R.id.scorePercentageTxtView);


		secondsInput = findViewById(R.id.secondsInput);

		float highscore = databaseHandler.getHighscoreByQuizID(id);

		formatter = new SimpleDateFormat("MMMM dd, yyyy");

		titleTxtView.setText(quiz.getTitle());
		subjectTxtView.setText(quiz.getSubject());
		descriptionTxtView.setText(quiz.getDescription());
		dateTxtView.setText(formatter.format(quiz.getDate_created()));
		nFlashcardsTxtView.setText(quiz.getDeck().size() + " Flashcards");
		secondsInput.setText(sharedPref.getString("timer_count", "0"));
		scorePercentageTxtView.setText(highscore + "%");

		circularProgressBar.setProgressWithAnimation(highscore, (long)1000);
		circularProgressBar.setProgressMax(100);


	}

	@Override
	public  void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode,data);

		if (requestCode == MODIFY_QUIZ){
			if (resultCode == RESULT_OK){
				quiz = databaseHandler.getQuiz(quiz.getQuizID());


				//float highscore = databaseHandler.getHighscoreByQuizID(id);

				formatter = new SimpleDateFormat("MMMM dd, yyyy");

				titleTxtView.setText(quiz.getTitle());
				subjectTxtView.setText(quiz.getSubject());
				descriptionTxtView.setText(quiz.getDescription());
				dateTxtView.setText(formatter.format(quiz.getDate_created()));
				nFlashcardsTxtView.setText(quiz.getDeck().size() + " Flashcards");

			}
		}
		else if (requestCode == TAKE_QUIZ) {
			if (resultCode == RESULT_OK) {
				quiz = databaseHandler.getQuiz(quiz.getQuizID());


			}
		}
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

	public void editQuiz(View view) {
		Intent intent = new Intent(this, EditQuizActivity.class);
		intent.putExtra("QUIZ_ID", quiz.getQuizID());
		startActivityForResult(intent, MODIFY_QUIZ);
	}

	public void takeQuiz(View view) {

		float oldRecord = databaseHandler.getHighscoreByQuizID(quiz.getQuizID());

		int timer;

		if (secondsInput.getText().toString().equals("")) {
			timer = Integer.parseInt(sharedPref.getString("timer_count", "0"));
		}
		else {
			timer = Integer.parseInt(secondsInput.getText().toString());
		}

		Intent intent = new Intent(this, TakeQuizActivity.class);
		intent.putExtra("QUIZ_ID", quiz.getQuizID());
		intent.putExtra("TIMER_COUNT", timer);
		intent.putExtra("OLD_RECORD", oldRecord);
		startActivityForResult(intent, TAKE_QUIZ);
	}
}
