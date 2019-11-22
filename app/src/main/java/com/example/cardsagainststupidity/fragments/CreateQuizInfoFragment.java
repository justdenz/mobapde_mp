package com.example.cardsagainststupidity.fragments;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cardsagainststupidity.CreateQuizActivity;
import com.example.cardsagainststupidity.R;
import com.google.android.material.textfield.TextInputEditText;

public class CreateQuizInfoFragment extends Fragment {

    private static final String TAG = "CreateQuizInfoFragment";
    private TextInputEditText titleInput, subjectInput, descriptionInput;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Button nextBtn;

    public CreateQuizInfoFragment() {
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
        View view = inflater.inflate(R.layout.create_quiz_info_fragment, container, false);
        titleInput = view.findViewById(R.id.titleInput);
        subjectInput = view.findViewById(R.id.subjectInput);
        descriptionInput = view.findViewById(R.id.descriptionInput);
        nextBtn = view.findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CreateQuizActivity) getActivity()).setQuizInfo(titleInput.getText().toString(), subjectInput.getText().toString(), descriptionInput.getText().toString());
                ((CreateQuizActivity) getActivity()).goNext();
            }
        });

        return  view;
    }









}
