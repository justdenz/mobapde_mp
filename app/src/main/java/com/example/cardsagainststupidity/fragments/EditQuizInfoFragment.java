package com.example.cardsagainststupidity.fragments;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cardsagainststupidity.CreateQuizActivity;
import com.example.cardsagainststupidity.EditQuizActivity;
import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.R;
import com.example.cardsagainststupidity.database.DatabaseHandler;
import com.google.android.material.textfield.TextInputEditText;

public class EditQuizInfoFragment extends Fragment {

    private static final String TAG = "CreateQuizInfoFragment";
    private TextInputEditText titleInput, subjectInput, descriptionInput;
    private Quiz quiz;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Button goToFlashcardsBtn;

    public EditQuizInfoFragment(Quiz quiz) {
        this.quiz = quiz;
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
        View view = inflater.inflate(R.layout.edit_quiz_info_fragment, container, false);
        titleInput = view.findViewById(R.id.titleInput);
        subjectInput = view.findViewById(R.id.subjectInput);
        descriptionInput = view.findViewById(R.id.descriptionInput);

        titleInput.setText(quiz.getTitle());
        subjectInput.setText(quiz.getSubject());
        descriptionInput.setText(quiz.getDescription());

        goToFlashcardsBtn = view.findViewById(R.id.goToFlashcardsBtn);
        goToFlashcardsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isComplete(titleInput.getText().toString(), subjectInput.getText().toString(), descriptionInput.getText().toString())) {
                    ((EditQuizActivity) getActivity()).updateQuizInfo(titleInput.getText().toString(), subjectInput.getText().toString(), descriptionInput.getText().toString());
                    ((EditQuizActivity) getActivity()).goNext();
                }
                else {
                    Toast.makeText((EditQuizActivity) getActivity(), "Please fill up all the fields", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return  view;
    }

    private boolean isComplete(String title, String subject, String description) {

        if (title.trim().isEmpty()) {
            return false;
        }
        else if (subject.trim().isEmpty()) {
            return false;
        }
        else if (description.trim().isEmpty()) {
            return false;
        }
        else {
            return true;
        }

    }
}
