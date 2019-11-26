package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.cardsagainststupidity.Model.Flashcard;
import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.database.DatabaseHandler;
import com.example.cardsagainststupidity.fragments.CreateQuizCardsFragment;
import com.example.cardsagainststupidity.fragments.CreateQuizInfoFragment;
import com.example.cardsagainststupidity.fragments.EditQuizCardsFragment;
import com.example.cardsagainststupidity.fragments.EditQuizInfoFragment;

import java.util.ArrayList;
import java.util.Objects;

public class EditQuizActivity extends AppCompatActivity {

	private EditQuizInfoFragment editQuizInfoFragment;
	private EditQuizCardsFragment editQuizCardsFragment;
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private DatabaseHandler databaseHandler;
	Bundle bundle;
	private Quiz quiz;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_quiz);

		databaseHandler = new DatabaseHandler(this);
		bundle = getIntent().getExtras();
		quiz = databaseHandler.getQuiz(bundle.getInt("QUIZ_ID"));

		fragmentManager = getSupportFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
		fragmentTransaction.add(R.id.frame_container, new EditQuizInfoFragment(quiz), "EDIT_QUIZ_INFO");
		fragmentTransaction.commit();
	}

	public void goNext() {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

		if(getSupportFragmentManager().findFragmentByTag("EDIT_QUIZ_CARD") == null) {
			fragmentTransaction.detach(getSupportFragmentManager().findFragmentByTag("EDIT_QUIZ_INFO"));
			fragmentTransaction.add(R.id.frame_container, new EditQuizCardsFragment(quiz.getDeck()), "EDIT_QUIZ_CARD");
			fragmentTransaction.commit();
		} else{
			editQuizCardsFragment = (EditQuizCardsFragment) getSupportFragmentManager().findFragmentByTag("EDIT_QUIZ_CARD");
			fragmentTransaction.detach(getSupportFragmentManager().findFragmentByTag("EDIT_QUIZ_INFO"));
			fragmentTransaction.attach(editQuizCardsFragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commitAllowingStateLoss();
			getSupportFragmentManager().executePendingTransactions();
		}
	}

	public void goBack(){
		editQuizInfoFragment = (EditQuizInfoFragment) getSupportFragmentManager().findFragmentByTag("EDIT_QUIZ_INFO");
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.detach(getSupportFragmentManager().findFragmentByTag("EDIT_QUIZ_CARD"));
		fragmentTransaction.attach(editQuizInfoFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commitAllowingStateLoss();
		getSupportFragmentManager().executePendingTransactions();
	}


	public void updateQuizInfo(String title, String subject, String description){


		//set quiz info here
		this.quiz.setTitle(title);
		this.quiz.setSubject(subject);
		this.quiz.setDescription(description);


	}

	public void finishEditQuiz(ArrayList<Flashcard> deck){
		this.quiz.setDeck(deck);
		databaseHandler = new DatabaseHandler(this);
		databaseHandler.updateQuiz(quiz);
		finishAffinity();
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}
