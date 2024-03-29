package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cardsagainststupidity.Model.Flashcard;
import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.database.DatabaseHandler;
import com.example.cardsagainststupidity.fragments.CreateQuizCardsFragment;
import com.example.cardsagainststupidity.fragments.CreateQuizInfoFragment;

import java.util.ArrayList;
import java.util.Objects;

public class CreateQuizActivity extends AppCompatActivity {
    private static final String TAG = "CreateQuizActivity";

    private CreateQuizInfoFragment createQuizInfoFragment;
    private CreateQuizCardsFragment createQuizCardsFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DatabaseHandler databaseHandler;
    private LinearLayout linearLayout;
    private Quiz newQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        newQuiz = new Quiz();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        fragmentTransaction.add(R.id.frame_container, new CreateQuizCardsFragment(), "CREATE_QUIZ_CARD");
        fragmentTransaction.add(R.id.frame_container, new CreateQuizInfoFragment(), "CREATE_QUIZ_INFO");
        fragmentTransaction.commit();

        linearLayout = findViewById(R.id.linear_layout);

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (getCurrentFocus() != null){
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                return true;
            }
        });
    }

    public void goNext() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if(getSupportFragmentManager().findFragmentByTag("CREATE_QUIZ_CARD") == null) {
            fragmentTransaction.detach(getSupportFragmentManager().findFragmentByTag("CREATE_QUIZ_INFO"));
            fragmentTransaction.add(R.id.frame_container, new CreateQuizCardsFragment(), "CREATE_QUIZ_CARD");
            fragmentTransaction.commit();
        } else{
            createQuizCardsFragment = (CreateQuizCardsFragment) getSupportFragmentManager().findFragmentByTag("CREATE_QUIZ_CARD");
            fragmentTransaction.detach(getSupportFragmentManager().findFragmentByTag("CREATE_QUIZ_INFO"));
            fragmentTransaction.attach(createQuizCardsFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();
            getSupportFragmentManager().executePendingTransactions();
        }
    }

    public void goBack(){
        createQuizInfoFragment = (CreateQuizInfoFragment) getSupportFragmentManager().findFragmentByTag("CREATE_QUIZ_INFO");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.detach(getSupportFragmentManager().findFragmentByTag("CREATE_QUIZ_CARD"));
        fragmentTransaction.attach(createQuizInfoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
    }

    public void setQuizInfo(String title, String subject, String description){


            //set quiz info here
            this.newQuiz.setTitle(title);
            this.newQuiz.setSubject(subject);
            this.newQuiz.setDescription(description);


    }

    public void publishQuiz(ArrayList<Flashcard> deck){
        this.newQuiz.setDeck(deck);
        databaseHandler = new DatabaseHandler(this);
        databaseHandler.addQuiz(newQuiz);
        finishAffinity();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
