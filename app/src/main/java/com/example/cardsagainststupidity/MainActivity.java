package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_pinned);
        QuizCardAdapter quizCardAdapter = new QuizCardAdapter(this);
        recyclerView.setAdapter(quizCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
