package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;


import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.adapter.QuizCardAdapter;
import com.example.cardsagainststupidity.database.DatabaseHandler;
import com.example.cardsagainststupidity.util.Util;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ArrayList<Quiz> quizzes;
    RecyclerView recyclerView;
    QuizCardAdapter quizCardAdapter;
    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to reset db
//        this.deleteDatabase(Util.DATABASE_NAME);
        databaseHandler = new DatabaseHandler(this);

        quizzes = databaseHandler.getAllQuizzes();

        initRecyclerView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_search, menu);

        MenuItem item = menu.findItem(R.id.main_search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Enter a keyword...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (quizCardAdapter != null){
                    quizCardAdapter.getFilter().filter(newText);
                }

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
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

    public void sortBy(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.sort_by_date: quizCardAdapter.sortByDate(); break;
            case R.id.sort_by_title: quizCardAdapter.sortByTitle(); break;
            case R.id.sort_by_subject: quizCardAdapter.sortBySubject(); break;

        }
    }


    public void viewStats(MenuItem item) {
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }


    public void viewCard(View view) {


        int id = (int) view.getTag();


        Intent intent = new Intent (this, CardInfoActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);

    }
}
