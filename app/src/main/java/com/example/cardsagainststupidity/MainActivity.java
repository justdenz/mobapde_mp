package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.adapter.QuizCardAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Quiz> quizzes = new ArrayList<>();

    RecyclerView recyclerView;
    QuizCardAdapter quizCardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view_pinned);
        quizCardAdapter = new QuizCardAdapter(this, quizzes);
        recyclerView.setAdapter(quizCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
