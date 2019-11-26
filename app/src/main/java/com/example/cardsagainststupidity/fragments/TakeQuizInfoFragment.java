package com.example.cardsagainststupidity.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cardsagainststupidity.R;
import com.example.cardsagainststupidity.TakeQuizActivity;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeQuizInfoFragment extends Fragment {

    private static final String TAG = "TakeQuizInfoFragment";

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    TextView txtQuizTitle, txtSubject, txtQuizDescription, txtCardNumber;
    EditText secondsInput;
    MaterialButton btnCancel, btnNext;

    int timerSeconds;

    public TakeQuizInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_take_quiz_info, container, false);
        txtQuizTitle = view.findViewById(R.id.txtQuizTitle);
        txtSubject = view.findViewById(R.id.txtSubject);
        txtQuizDescription = view.findViewById(R.id.txtQuizDescription);
        txtCardNumber = view.findViewById(R.id.txtCardNumber);
        secondsInput = view.findViewById(R.id.secondsInput);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnNext = view.findViewById(R.id.btnNext);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void next(View v){
        TakeQuizActivity quizActivity = (TakeQuizActivity) getActivity();
        fragmentManager = quizActivity.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(quizActivity.getTakeQuizCardsFragment(),"TAKE_QUIZ_CARDS");
        fragmentTransaction.commit();
    }

}
