package com.example.cardsagainststupidity.fragments;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cardsagainststupidity.CreateQuizActivity;
import com.example.cardsagainststupidity.R;

public class CreateQuizCardsFragment extends Fragment {

    private CreateQuizCardsFragmentListener listener;

    EditText questionInput;
    Button backBtn;

    public interface CreateQuizCardsFragmentListener{
        void onInputQuizCardSent(CharSequence input);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create_quiz_cards_fragment, container, false);
        backBtn = view.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CreateQuizActivity) getActivity()).goBack();
            }
        });
        return  view;
    }


}
