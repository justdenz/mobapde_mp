package com.example.cardsagainststupidity.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cardsagainststupidity.Model.Flashcard;
import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeQuizCardsFragment extends Fragment {

    TextView txtFlashcardNum;
    ImageButton backBtn;
    MaterialButton btnGuessed, btnCorrect;
    private ArrayList<Flashcard> deck;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    public TakeQuizCardsFragment(Quiz quiz) {
        this.deck = quiz.getDeck();
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
        View view = inflater.inflate(R.layout.fragment_take_quiz_cards, container, false);
        txtFlashcardNum = view.findViewById(R.id.txtFlashcardNum);
        backBtn = view.findViewById(R.id.backBtn);
        btnGuessed = view.findViewById(R.id.btnGuessed);
        btnCorrect = view.findViewById(R.id.btnCorrect);
        return view;
    }

}
