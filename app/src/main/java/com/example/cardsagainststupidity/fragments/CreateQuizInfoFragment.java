package com.example.cardsagainststupidity.fragments;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cardsagainststupidity.R;

public class CreateQuizInfoFragment extends Fragment {

    private QuizInfoFragmentListener listener;
    private EditText titleInput, subjectInput, descriptionInput;
    private Button nextBtn;

    public interface QuizInfoFragmentListener{
        void onInputQuizInfoSent(CharSequence input);
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
                CharSequence input = titleInput.getText();
                listener.onInputQuizInfoSent(input);

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();

                ft.replace(R.id.frame_container, new CreateQuizCardsFragment());
                ft.commit();
            }
        });
        return  view;
    }

    public void updateTitleInput(CharSequence newText){
        titleInput.setText(newText);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof QuizInfoFragmentListener){
            listener = (QuizInfoFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement QuizInfoFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
