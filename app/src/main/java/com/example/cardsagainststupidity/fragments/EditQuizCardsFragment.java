package com.example.cardsagainststupidity.fragments;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.cardsagainststupidity.EditQuizActivity;
import com.example.cardsagainststupidity.Model.Flashcard;
import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class EditQuizCardsFragment extends Fragment {

    TextInputEditText questionInput, answerInput;
    MaterialButton backBtn, publishBtn;
    ArrayList<Flashcard> flashcards;
    ImageButton backCardBtn, nextCardBtn, removeCardBtn;
    TextView nthFlashCard;
    private int CURRENT_CARD = 1;

    public EditQuizCardsFragment(ArrayList<Flashcard> flashcards) {
        this.flashcards = flashcards;
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
        View view = inflater.inflate(R.layout.edit_quiz_cards_fragment, container, false);


        nthFlashCard = view.findViewById(R.id.nthFlashCard);
        nthFlashCard.setText("Flashcard " + Integer.toString(CURRENT_CARD) + "/" + Integer.toString(flashcards.size()));

        questionInput = view.findViewById(R.id.questionInput);
        answerInput = view.findViewById(R.id.answerInput);

        questionInput.setText(flashcards.get(CURRENT_CARD-1).getQuestion());
        answerInput.setText(flashcards.get(CURRENT_CARD-1).getAnswer());

        //navigation for flashcards
        backCardBtn = view.findViewById(R.id.backCardBtn);
        backCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPrevCard();
                nextCardBtn.setBackgroundResource(R.drawable.ic_right);

                if(CURRENT_CARD == 1){
                    backCardBtn.setVisibility(View.INVISIBLE);
                }
            }
        });


        nextCardBtn = view.findViewById(R.id.nextCardBtn);
        if(flashcards.size() == 1) {
            nextCardBtn.setBackgroundResource(R.drawable.ic_add_circular_outlined_button);
        }else {
            nextCardBtn.setBackgroundResource(R.drawable.ic_right);
        }
        nextCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CURRENT_CARD == flashcards.size()) {
                    nextCardBtn.setBackgroundResource(R.drawable.ic_add_circular_outlined_button);
                    addCard();
                    backCardBtn.setVisibility(View.VISIBLE);
                }else{
                    goNextCard();
                    if (CURRENT_CARD == flashcards.size()){
                        nextCardBtn.setBackgroundResource(R.drawable.ic_add_circular_outlined_button);
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
                ((EditQuizActivity) getActivity()).goBack();
            }
        });

        removeCardBtn = view.findViewById(R.id.removeCardBtn);
        removeCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flashcards.size() == 1){
                    Toast.makeText(((EditQuizActivity) getActivity()), "You should have at least one flashcard!", Toast.LENGTH_SHORT).show();
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

                if (isComplete()) {
                    ((EditQuizActivity) getActivity()).finishEditQuiz(flashcards);
                }
                else {
                    Toast.makeText(((EditQuizActivity) getActivity()), "Please fill up all the flashcards.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        backCardBtn.setVisibility(View.INVISIBLE);
        return  view;
    }

    public void removeCard(){


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Remove Card");
        builder.setMessage("Are you sure you want to remove this card? This cannot be reverted.");
        builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
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
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorDelete));
        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorCancel));

    }

    private boolean isComplete () {


        for (Flashcard f : flashcards) {

            if (f.getQuestion().equals("") || f.getAnswer().equals("")) {
                return false;
            }
        }

        return true;
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
