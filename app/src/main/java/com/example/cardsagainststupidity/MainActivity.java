package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.adapter.QuizCardAdapter;
import com.example.cardsagainststupidity.database.DatabaseHandler;
import com.example.cardsagainststupidity.util.Util;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    static final int MODIFY_QUIZ = 1;
    static final int TAKE_QUIZ = 2;

    ArrayList<Quiz> quizzes;
    RecyclerView recyclerView;
    QuizCardAdapter quizCardAdapter;
    DatabaseHandler databaseHandler;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        databaseHandler = new DatabaseHandler(this);

        quizzes = databaseHandler.getAllQuizzes();

        initRecyclerView();

        sharedPref = PreferenceManager
                .getDefaultSharedPreferences(this);
//        String marketPref = sharedPref
//                .getString("timer_count", "-1");


    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode,data);

        if (requestCode == MODIFY_QUIZ){
            if (resultCode == RESULT_OK){
                quizCardAdapter.refresh(databaseHandler.getAllQuizzes());
            }
        }
        else if (requestCode == TAKE_QUIZ) {
            if (resultCode == RESULT_OK) {

            }
        }
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
        startActivityForResult(intent, MODIFY_QUIZ);
    }

    public void deleteQuiz(final int quizID) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Delete Quiz");
        builder.setMessage("Are you sure you want to delete this quiz? This cannot be reverted.");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                databaseHandler.deleteQuiz(quizID);
                quizCardAdapter.refresh(databaseHandler.getAllQuizzes());
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

    public void editQuiz(int quizID) {

       Intent intent = new Intent(this, EditQuizActivity.class);
       intent.putExtra("QUIZ_ID", quizID);
       startActivityForResult(intent, MODIFY_QUIZ);

    }

    public void viewSettings(MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

	public void takeQuiz(int quizID) {
        Intent intent = new Intent(this, TakeQuizActivity.class);
        intent.putExtra("QUIZ_ID", quizID);
        intent.putExtra("TIMER_COUNT", Integer.parseInt(sharedPref.getString("timer_count", "0")));
        startActivityForResult(intent, TAKE_QUIZ);
	}
}
