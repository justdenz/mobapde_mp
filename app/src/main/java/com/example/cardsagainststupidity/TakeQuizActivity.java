package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.Model.QuizRecord;
import com.example.cardsagainststupidity.database.DatabaseHandler;
import com.example.cardsagainststupidity.fragments.TakeQuizCardsFragment;
import com.example.cardsagainststupidity.fragments.TakeQuizScoreFragment;

import java.util.Objects;

public class TakeQuizActivity extends AppCompatActivity {

    private TakeQuizCardsFragment takeQuizCardsFragment;
    private TakeQuizScoreFragment takeQuizScoreFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DatabaseHandler databaseHandler;
    Bundle bundle;
    private int timerCount;
    private Quiz quiz;
    private QuizRecord record;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        databaseHandler = new DatabaseHandler(this);
        bundle = getIntent().getExtras();
        quiz = databaseHandler.getQuiz(bundle.getInt("QUIZ_ID"));
        timerCount = bundle.getInt("TIMER_COUNT");


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentTransaction.add(R.id.frame_container, new TakeQuizCardsFragment(quiz, timerCount), "TAKE_QUIZ_CARDS");
        fragmentTransaction.commit();
    }


    public void setRecord(int nCorrect, int duration) {
        record = new QuizRecord();

        record.setDuration(duration);
        record.setScorePercentage(nCorrect / quiz.getDeck().size());
        record.setQuiz(this.quiz);
    }

    public void endGame(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.detach(getSupportFragmentManager().findFragmentByTag("TAKE_QUIZ_CARDS"));
        fragmentTransaction.add(R.id.frame_container, new TakeQuizScoreFragment(new QuizRecord()), "TAKE_QUIZ_INFO_SCORE");
        fragmentTransaction.commit();
    }





	public void exitQuiz(View view) {
	}

    public void retakeQuiz(View view) {
    }



}
