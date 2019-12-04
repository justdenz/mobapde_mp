package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.Model.QuizRecord;
import com.example.cardsagainststupidity.database.DatabaseHandler;
import com.example.cardsagainststupidity.fragments.CreateQuizCardsFragment;
import com.example.cardsagainststupidity.fragments.TakeQuizCardsFragment;
import com.example.cardsagainststupidity.fragments.TakeQuizScoreFragment;

import java.util.Objects;

import static com.example.cardsagainststupidity.MainActivity.TAKE_QUIZ;

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
    private float oldRecord;
    SharedPreferences sharedPref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);



        databaseHandler = new DatabaseHandler(this);
        bundle = getIntent().getExtras();
        quiz = databaseHandler.getQuiz(bundle.getInt("QUIZ_ID"));
        timerCount = bundle.getInt("TIMER_COUNT");
        oldRecord = bundle.getFloat("OLD_RECORD");

        sharedPref = PreferenceManager
                .getDefaultSharedPreferences(this);

        startGame();
    }

    public void startGame() {

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        if(getSupportFragmentManager().findFragmentByTag("TAKE_QUIZ_INFO_SCORE") != null) {
            fragmentTransaction.detach(getSupportFragmentManager().findFragmentByTag("TAKE_QUIZ_INFO_SCORE"));
        }


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentTransaction.add(R.id.frame_container, new TakeQuizCardsFragment(quiz, timerCount, sharedPref.getBoolean("swipe", false), sharedPref.getBoolean("shake", false)), "TAKE_QUIZ_CARDS");
        fragmentTransaction.commit();

    }


    public void saveRecord(int nCorrect, int duration) {
        record = new QuizRecord();

        record.setDuration(duration);
        record.setScorePercentage(nCorrect*100 / quiz.getDeck().size());
        record.setQuiz(this.quiz);
    }

    public void endGame() {

        databaseHandler.addRecord(this.record);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.detach(getSupportFragmentManager().findFragmentByTag("TAKE_QUIZ_CARDS"));
        fragmentTransaction.add(R.id.frame_container, new TakeQuizScoreFragment(this.record, oldRecord), "TAKE_QUIZ_INFO_SCORE");
        fragmentTransaction.commit();
    }


	public void exitQuiz() {
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
	}

	public void setOldRecord (float record) {
        this.oldRecord = record;
    }


}
