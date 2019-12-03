package com.example.cardsagainststupidity.fragments;


import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardsagainststupidity.Model.Flashcard;
import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.Model.Stopwatch;
import com.example.cardsagainststupidity.R;
import com.example.cardsagainststupidity.TakeQuizActivity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeQuizCardsFragment extends Fragment {

    public static final int WARM_UP_SECONDS = 5;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    TextView txtFlashcardNum, txtQuestion, txtAnswer;
    ImageButton backBtn;
    MaterialButton btnGuessed, btnCorrect, btnSkip;

    private ArrayList<Flashcard> deck;
    private ArrayList<Flashcard> skipped;
    private int timerCount;

    private ProgressBar progressBar;
    private boolean game_proper;
    private MyCountDownTimer timer;
    private int current;
    private int nCorrect;
    private Stopwatch stopwatch;
    private Flashcard currentFlashcard;



    public TakeQuizCardsFragment(Quiz quiz, int timerCount) {
        this.deck = quiz.getDeck();
        this.timerCount = timerCount;
        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_take_quiz_cards, container, false);
        txtFlashcardNum = view.findViewById(R.id.txtFlashcardNum);
        backBtn = view.findViewById(R.id.backBtn);
        btnGuessed = view.findViewById(R.id.btnGuessed);
        btnCorrect = view.findViewById(R.id.btnCorrect);
        btnSkip = view.findViewById(R.id.btnSkip);
        progressBar = view.findViewById(R.id.progressBar);
        txtQuestion = view.findViewById(R.id.txtQuestion);
        txtAnswer = view.findViewById(R.id.txtAnswer);

        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (game_proper) {
                    nCorrect++;
                    setNextQuestion();


                }

            }
        });

        btnGuessed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game_proper) {

                    setNextQuestion();
                }
            }

        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game_proper) {

                    if (current < deck.size()) {
                        skipped.add(currentFlashcard);
                        setNextQuestion();
                    }
                    else {
                        txtFlashcardNum.setText("You cannot skip twice!");
                    }

                    // if user skips, it will be added to the skipped array

                }
            }
        });


        initQuiz();

        return view;
    }

    private void initQuiz() {
        game_proper = false;
        nCorrect = 0;
        current = -1;
        this.skipped = new ArrayList<>();
        stopwatch = new Stopwatch();
        startTimer(WARM_UP_SECONDS);
    }

    private void startQuiz() {
        Collections.shuffle(deck);
        setNextQuestion();
    }

    private void setNextQuestion () {

        current++;


        if (current < deck.size()) {

            if (timer != null) {
                timer.cancel();
            }

            currentFlashcard = deck.get(current);
            txtQuestion.setText(currentFlashcard.getQuestion());
            txtFlashcardNum.setText("Flashcard " + (current+1) + "/" + deck.size());
            startTimer(timerCount);
        }
        else if (skipped.size() > 0) {

            if (timer != null) {
                timer.cancel();
            }

            currentFlashcard = skipped.get(0);
            skipped.remove(currentFlashcard);

            txtFlashcardNum.setText("Last chance!");
            txtQuestion.setText(currentFlashcard.getQuestion());
            startTimer(timerCount);

        }
        else {
            Log.d("TEST", nCorrect+"");
            Log.d("TIME", ((int) stopwatch.getElapsedTimeSecs() + ""));
            ((TakeQuizActivity) getContext()).saveRecord(nCorrect, (int) stopwatch.getElapsedTimeSecs());
            ((TakeQuizActivity) getContext()).endGame();
            txtFlashcardNum.setText("Game Over!");
            stopwatch.stop();
        }



    }



    private void startTimer(int seconds) {



        timer = new MyCountDownTimer((seconds+1) * 1000, 1000);
        progressBar.setMax(seconds);
        timer.start();
    }


    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer (long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished/1000);
            if (game_proper) {
                txtAnswer.setText("Answer will be revealed in " + progress + " seconds.");
            }
            else {
                txtFlashcardNum.setText("Quiz begins in " + progress);
                stopwatch.start();
            }
            progressBar.setProgress(progress);
        }

        @Override
        public void onFinish () {
            if (game_proper) {
                txtAnswer.setText(currentFlashcard.getAnswer());
            }
            else {
                game_proper = true;
                startQuiz();
            }
            progressBar.setProgress(0);
        }

    }

}
