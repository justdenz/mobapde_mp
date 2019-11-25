package com.example.cardsagainststupidity.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cardsagainststupidity.R;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeQuizCardsFragment extends Fragment {

    TextView txtFlashcardNum;
    ImageButton backBtn;
    MaterialButton btnGuessed, btnCorrect;


    public TakeQuizCardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_take_quiz_cards, container, false);
        txtFlashcardNum = view.findViewById(R.id.txtFlashcardNum);
        backBtn = view.findViewById(R.id.backBtn);
        btnGuessed = view.findViewById(R.id.btnGuessed);
        btnCorrect = view.findViewById(R.id.btnCorrect);
        return view;
    }

}
