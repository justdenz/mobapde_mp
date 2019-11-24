package com.example.cardsagainststupidity.fragments;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardsagainststupidity.CreateQuizActivity;
import com.example.cardsagainststupidity.Model.Flashcard;
import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class CreateQuizCardsFragment extends Fragment {

    TextInputEditText questionInput, answerInput;
    MaterialButton backBtn, publishBtn;
    ArrayList<Flashcard> flashcards;
    ImageButton backCardBtn, nextCardBtn, removeCardBtn;
    TextView nthFlashCard;
    private int CURRENT_CARD = 1;

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

        flashcards = new ArrayList<>();

        Flashcard firstFlashCard = new Flashcard();
        flashcards.add(firstFlashCard);

        nthFlashCard = view.findViewById(R.id.nthFlashCard);
        nthFlashCard.setText("Flashcard " + Integer.toString(CURRENT_CARD) + "/" + Integer.toString(flashcards.size()));

        questionInput = view.findViewById(R.id.questionInput);
        answerInput = view.findViewById(R.id.answerInput);

        //navigation for flashcards
        backCardBtn = view.findViewById(R.id.backCardBtn);
        backCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPrevCard();
                nextCardBtn.setBackgroundResource(R.drawable.next_button);

                if(CURRENT_CARD == 1){
                    backCardBtn.setVisibility(View.INVISIBLE);
                }
            }
        });


        nextCardBtn = view.findViewById(R.id.nextCardBtn);
        nextCardBtn.setBackgroundResource(R.drawable.add_button);
        nextCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CURRENT_CARD == flashcards.size()) {
                    nextCardBtn.setBackgroundResource(R.drawable.add_button);
                    addCard();
                    backCardBtn.setVisibility(View.VISIBLE);
                }else{
                    goNextCard();
                    if (CURRENT_CARD == flashcards.size()){
                        nextCardBtn.setBackgroundResource(R.drawable.add_button);
                    }
                    backCardBtn.setVisibility(View.VISIBLE);
                }
            }
        });


        //Navigations for fragments
        backBtn = view.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CreateQuizActivity) getActivity()).goBack();
            }
        });

        removeCardBtn = view.findViewById(R.id.removeCardBtn);
        removeCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flashcards.size() == 1){
                    Toast.makeText(((CreateQuizActivity) getActivity()), "You should have at least one flashcard!", Toast.LENGTH_SHORT).show();
                }else {
                    removeCard();
                }
            }
        });

        publishBtn = view.findViewById(R.id.publishBtn);
        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrentCard();
                ((CreateQuizActivity) getActivity()).publishQuiz(flashcards);
            }
        });

        backCardBtn.setVisibility(View.INVISIBLE);
        return  view;
    }

    public void removeCard(){
        flashcards.remove(CURRENT_CARD-1); // removes the current card
        if(CURRENT_CARD == 1) {
            CURRENT_CARD += 1;
            backCardBtn.setVisibility(View.INVISIBLE);
        }
        else if (CURRENT_CARD == 2){
            CURRENT_CARD -= 1;
            backCardBtn.setVisibility(View.INVISIBLE);
        } else{
            CURRENT_CARD -=1;
            backCardBtn.setVisibility(View.VISIBLE);
        }
        nthFlashCard.setText("Flashcard " + Integer.toString(CURRENT_CARD) + "/" + Integer.toString(flashcards.size()));
        questionInput.setText(flashcards.get(CURRENT_CARD-1).getQuestion());
        answerInput.setText(flashcards.get(CURRENT_CARD-1).getAnswer());
    }

    public void addCard(){
        updateCurrentCard();

        CURRENT_CARD += 1;


        Flashcard newFlashCard = new Flashcard();
        flashcards.add(newFlashCard);

        nthFlashCard.setText("Flashcard " + Integer.toString(CURRENT_CARD) + "/" + Integer.toString(flashcards.size()));
        questionInput.getText().clear();
        answerInput.getText().clear();
    }

    public void goNextCard(){
        updateCurrentCard();
        CURRENT_CARD += 1;
        nthFlashCard.setText("Flashcard " + Integer.toString(CURRENT_CARD) + "/" + Integer.toString(flashcards.size()));
        questionInput.setText(flashcards.get(CURRENT_CARD-1).getQuestion());
        answerInput.setText(flashcards.get(CURRENT_CARD-1).getAnswer());
    }

    public void goPrevCard(){
        updateCurrentCard();
        CURRENT_CARD -= 1;
        nthFlashCard.setText("Flashcard " + Integer.toString(CURRENT_CARD) + "/" + Integer.toString(flashcards.size()));
        questionInput.setText(flashcards.get(CURRENT_CARD-1).getQuestion());
        answerInput.setText(flashcards.get(CURRENT_CARD-1).getAnswer());
    }

    //updates the current card before doing something
    public void updateCurrentCard(){
        flashcards.get(CURRENT_CARD-1).setQuestion(questionInput.getText().toString());
        flashcards.get(CURRENT_CARD-1).setAnswer(answerInput.getText().toString());
    }




}
