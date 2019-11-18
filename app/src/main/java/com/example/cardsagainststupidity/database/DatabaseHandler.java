package com.example.cardsagainststupidity.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cardsagainststupidity.R;
import com.example.cardsagainststupidity.util.Util;

public class DatabaseHandler extends SQLiteOpenHelper {

	public DatabaseHandler(Context context) {
		super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
	}

	// We create our table
	@Override
	public void onCreate(SQLiteDatabase db) {


		String CREATE_QUIZ_TABLE = "CREATE TABLE " + Util.QUIZ_TABLE_NAME + "("
				+ Util.QUIZ_KEY_ID + " INTEGER PRIMARY KEY," + Util.QUIZ_KEY_TITLE + "TEXT,"
				+ Util.QUIZ_KEY_SUBJECT + " TEXT" + Util.QUIZ_KEY_DESCRIPTION + "TEXT"  + ")";

		String CREATE_FLASHCARD_TABLE = "CREATE TABLE " + Util.FLASHCARD_TABLE_NAME + "("
				+ Util.FLASHCARD_KEY_ID + " INTEGER PRIMARY KEY," + Util.QUIZ_KEY_ID + "INTEGER,"
				+ Util.FLASHCARD_KEY_QUESTION + " TEXT" + Util.FLASHCARD_KEY_ANSWER + "TEXT"  + ")";

		String CREATE_RECORD_TABLE = "CREATE TABLE " + Util.RECORD_TABLE_NAME + "("
				+ Util.RECORD_KEY_ID + " INTEGER PRIMARY KEY," + Util.QUIZ_KEY_ID + "INTEGER,"
				+ Util.RECORD_KEY_SCOREPERCENTAGE + "REAL" + Util.RECORD_KEY_DURATION + "TEXT"  + ")";

		db.execSQL(CREATE_QUIZ_TABLE);
		db.execSQL(CREATE_FLASHCARD_TABLE);
		db.execSQL(CREATE_RECORD_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String DROP_TABLE = String.valueOf("DROP TABLE IF EXISTS ");

		db.execSQL(DROP_TABLE + Util.FLASHCARD_TABLE_NAME);
		db.execSQL(DROP_TABLE + Util.RECORD_TABLE_NAME);
		db.execSQL(DROP_TABLE + Util.QUIZ_TABLE_NAME);

		onCreate(db);
	}

	








}
