package com.example.cardsagainststupidity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CreateQuizInfoFragment extends Fragment {

    EditText titleInput, subjectInput, descriptionInput;

    public CreateQuizInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create_quiz_info_fragment, container, false);
        titleInput = view.findViewById(R.id.titleInput);
        subjectInput = view.findViewById(R.id.subjectInput);
        descriptionInput = view.findViewById(R.id.descriptionInput);
        return  view;
    }
}
