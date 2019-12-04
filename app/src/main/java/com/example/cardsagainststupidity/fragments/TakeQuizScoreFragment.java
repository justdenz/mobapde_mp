package com.example.cardsagainststupidity.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cardsagainststupidity.Model.QuizRecord;
import com.example.cardsagainststupidity.R;
import com.example.cardsagainststupidity.TakeQuizActivity;
import com.google.android.material.button.MaterialButton;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeQuizScoreFragment extends Fragment {


    TextView txtQuizTitle, txtScore, txtHighscore, txtDuration;
    MaterialButton btnRetake, btnFinish;
    CircularProgressBar circularProgressBar;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private QuizRecord record;
    private float oldRecord;


    public TakeQuizScoreFragment(QuizRecord record, float oldRecord) {
        this.record = record;
        this.oldRecord = oldRecord;
        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_take_quiz_score, container, false);

        txtQuizTitle = view.findViewById(R.id.txtQuizTitle);
        txtHighscore = view.findViewById(R.id.txtHighscore);
        txtScore = view.findViewById(R.id.txtScore);
        txtDuration = view.findViewById(R.id.txtDuration);
        btnFinish = view.findViewById(R.id.btnFinish);
        btnRetake = view.findViewById(R.id.btnRetake);
        circularProgressBar = view.findViewById(R.id.circularProgressBar);

        txtScore.setText(record.getScorePercentage() + "%");

        circularProgressBar.setProgressWithAnimation(record.getScorePercentage(), (long)1000);
        circularProgressBar.setProgressMax(100);

        txtQuizTitle.setText(record.getQuiz().getTitle());
        txtDuration.setText("Duration: " + record.getDuration() + " seconds");

        if (record.getScorePercentage() > oldRecord) {
            txtHighscore.setText("Good Job! You set a new highscore!");
            ((TakeQuizActivity) getContext()).setOldRecord(record.getScorePercentage());
        }
        else if (record.getScorePercentage() == oldRecord){
            txtHighscore.setText("Nice! You tied your old highscore!");
        }
        else {
            txtHighscore.setText("Your current highscore is " +oldRecord + "%");
        }


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((TakeQuizActivity) getContext()).exitQuiz();
            }
        });

        btnRetake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TakeQuizActivity) getContext()).startGame();
            }
        });

        return view;
    }

}
