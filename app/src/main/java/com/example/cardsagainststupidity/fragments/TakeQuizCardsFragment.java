package com.example.cardsagainststupidity.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cardsagainststupidity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeQuizCardsFragment extends Fragment {


    public TakeQuizCardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_take_quiz_cards, container, false);
    }

}
