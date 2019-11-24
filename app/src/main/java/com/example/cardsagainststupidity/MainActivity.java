package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.adapter.QuizCardAdapter;
import com.example.cardsagainststupidity.database.DatabaseHandler;
import com.example.cardsagainststupidity.util.Util;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Quiz> quizzes;

    RecyclerView recyclerView;
    QuizCardAdapter quizCardAdapter;
    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to reset db
        //this.deleteDatabase(Util.DATABASE_NAME);
        databaseHandler = new DatabaseHandler(this);

        quizzes = databaseHandler.getAllQuizzes();


        initRecyclerView();

    }

    

    private void initRecyclerView(){

        recyclerView = findViewById(R.id.recycler_view_pinned);
        quizCardAdapter = new QuizCardAdapter(this, quizzes);
        recyclerView.setAdapter(quizCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void createQuiz(View view) {
        Intent intent = new Intent(this, CreateQuizActivity.class);
        startActivity(intent);
    }
}
