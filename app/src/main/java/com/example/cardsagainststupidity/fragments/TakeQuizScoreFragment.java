package com.example.cardsagainststupidity.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cardsagainststupidity.R;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeQuizScoreFragment extends Fragment {


    TextView txtQuizTitle, txtSubject, txtQuizDescription, txtScore;
    MaterialButton btnRetake, btnFinish;

    public TakeQuizScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_take_quiz_score, container, false);

        txtQuizTitle = view.findViewById(R.id.txtQuizTitle);
        txtSubject = view.findViewById(R.id.txtSubject);
        txtQuizDescription = view.findViewById(R.id.txtQuizDescription);
        txtScore = view.findViewById(R.id.txtScore);
        btnFinish = view.findViewById(R.id.btnFinish);
        btnRetake = view.findViewById(R.id.btnRetake);
        return view;
    }

}
