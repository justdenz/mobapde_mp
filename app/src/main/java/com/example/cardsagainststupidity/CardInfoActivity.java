package com.example.cardsagainststupidity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cardsagainststupidity.Model.Quiz;

import java.util.Objects;

public class CardInfoActivity extends AppCompatActivity {

	Quiz quiz;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_info);
		Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

		int id = getIntent().getIntExtra("ID", -1);

		TextView text = findViewById(R.id.idTest);
		text.setText("This is quiz id " + id);
	}
}
