package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.database.DatabaseHandler;
import com.example.cardsagainststupidity.fragments.TakeQuizCardsFragment;
import com.example.cardsagainststupidity.fragments.TakeQuizInfoFragment;
import com.example.cardsagainststupidity.fragments.TakeQuizScoreFragment;

public class TakeQuizActivity extends AppCompatActivity {

    private TakeQuizInfoFragment takeQuizInfoFragment;
    private TakeQuizCardsFragment takeQuizCardsFragment;
    private TakeQuizScoreFragment takeQuizScoreFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DatabaseHandler databaseHandler;
    private Quiz currentQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
        databaseHandler = new DatabaseHandler(this);
        Intent intent = getIntent();
        int quizID = intent.getIntExtra("QUIZ",-1);
        takeQuizInfoFragment = new TakeQuizInfoFragment();
        takeQuizCardsFragment = new TakeQuizCardsFragment();
        takeQuizScoreFragment = new TakeQuizScoreFragment();
        currentQuiz = databaseHandler.getQuiz(quizID);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, takeQuizInfoFragment, "TAKE_QUIZ_INFO");
        fragmentTransaction.commit();
    }

    public void goNext(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if(getSupportFragmentManager().findFragmentByTag("TAKE_QUIZ_CARD") == null) {
            fragmentTransaction.detach(getSupportFragmentManager().findFragmentByTag("TAKE_QUIZ_INFO"));
            fragmentTransaction.add(R.id.frame_container, takeQuizCardsFragment, "TAKE_QUIZ_CARD");
            fragmentTransaction.commit();
        } else{
            takeQuizCardsFragment = (TakeQuizCardsFragment) getSupportFragmentManager().findFragmentByTag("TAKE_QUIZ_CARD");
            fragmentTransaction.detach(getSupportFragmentManager().findFragmentByTag("TAKE_QUIZ_INFO"));
            fragmentTransaction.attach(takeQuizCardsFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();
            getSupportFragmentManager().executePendingTransactions();
        }
    }
}
