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
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeQuizScoreFragment extends Fragment {


    TextView txtQuizTitle, txtScore, txtHighscore, txtDuration;
    MaterialButton btnRetake, btnFinish;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private QuizRecord record;


    public TakeQuizScoreFragment(QuizRecord record) {
        this.record = record;
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

        DecimalFormat df = new DecimalFormat("#.##");
        txtScore.setText(df.format(record.getScorePercentage()) + "%");
        txtQuizTitle.setText(record.getQuiz().getTitle());
        txtDuration.setText("Duration: " + record.getDuration() + " seconds");

        return view;
    }

}
