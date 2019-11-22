package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cardsagainststupidity.fragments.CreateQuizCardsFragment;
import com.example.cardsagainststupidity.fragments.CreateQuizInfoFragment;

public class CreateQuizActivity extends AppCompatActivity implements CreateQuizInfoFragment.QuizInfoFragmentListener {
    private static final String TAG = "CreateQuizActivity";

    private CreateQuizInfoFragment createQuizInfoFragment;
    private CreateQuizCardsFragment createQuizCardsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        createQuizInfoFragment = new CreateQuizInfoFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, createQuizInfoFragment)
                .commit();
    }


    @Override
    public void onInputQuizInfoSent(CharSequence input) {
        createQuizCardsFragment.updateQuestionInput(input);
    }

}
