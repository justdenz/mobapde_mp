package com.example.cardsagainststupidity.fragments;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.cardsagainststupidity.R;

public class CreateQuizCardsFragment extends Fragment {

    private CreateQuizCardsFragmentListener listener;

    EditText questionInput;

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
        questionInput = view.findViewById(R.id.questionInput);
        return  view;
    }

    public void updateQuestionInput(CharSequence newText){
        questionInput.setText(newText);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof CreateQuizCardsFragment.CreateQuizCardsFragmentListener){
            listener = (CreateQuizCardsFragment.CreateQuizCardsFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement QuizCardFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
