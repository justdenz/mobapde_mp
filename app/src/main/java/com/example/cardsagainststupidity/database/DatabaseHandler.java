package com.example.cardsagainststupidity.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cardsagainststupidity.Model.Flashcard;
import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.Model.QuizRecord;
import com.example.cardsagainststupidity.R;
import com.example.cardsagainststupidity.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

	public DatabaseHandler(Context context) {
		super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
	}

	// We create our table
	@Override
	public void onCreate(SQLiteDatabase db) {


		String CREATE_QUIZ_TABLE = "CREATE TABLE " + Util.QUIZ_TABLE_NAME + "("
				+ Util.QUIZ_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Util.QUIZ_KEY_TITLE + "TEXT,"
				+ Util.QUIZ_KEY_SUBJECT + " TEXT," + Util.QUIZ_KEY_DESCRIPTION + "TEXT"  +")";

		String CREATE_FLASHCARD_TABLE = "CREATE TABLE " + Util.FLASHCARD_TABLE_NAME + "("
				+ Util.FLASHCARD_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Util.QUIZ_KEY_ID + "INTEGER,"
				+ Util.FLASHCARD_KEY_QUESTION + " TEXT," + Util.FLASHCARD_KEY_ANSWER + "TEXT"  + ")";

		String CREATE_RECORD_TABLE = "CREATE TABLE " + Util.RECORD_TABLE_NAME + "("
				+ Util.RECORD_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Util.QUIZ_KEY_ID + "INTEGER,"
				+ Util.RECORD_KEY_SCOREPERCENTAGE + "REAL," + Util.RECORD_KEY_DURATION + "TEXT"  + ")";

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


	public List<Quiz> getAllQuizzes () {

		List<Quiz> quizList = new ArrayList<>();

		SQLiteDatabase db = this.getReadableDatabase();

		String selectAll = "SELECT * FROM " + Util.QUIZ_TABLE_NAME;
		Cursor cursor = db.rawQuery(selectAll, null);

		//Loop through our data
		if (cursor.moveToFirst()) {
			do {
				Quiz quiz = new Quiz();
				quiz.setQuizID(Integer.parseInt(cursor.getString(0)));
				quiz.setTitle(cursor.getString(1));
				quiz.setSubject(cursor.getString(2));
				quiz.setDescription(cursor.getString(3));

				//add contact objects to our list
				quizList.add(quiz);
			}while (cursor.moveToNext());
		}

		return quizList;
	}

	public List<QuizRecord> getAllRecords () {
		List<QuizRecord> recordList = new ArrayList<>();

		SQLiteDatabase db = this.getReadableDatabase();

		String recordID = Util.RECORD_TABLE_NAME + "." + Util.RECORD_KEY_ID;
		String title = Util.QUIZ_TABLE_NAME + "." + Util.QUIZ_KEY_TITLE;
		String score = Util.RECORD_TABLE_NAME + "." + Util.RECORD_KEY_SCOREPERCENTAGE;
		String duration = Util.RECORD_TABLE_NAME + "." + Util.RECORD_KEY_DURATION;
		String quizzesQuizID = Util.QUIZ_TABLE_NAME + "." + Util.QUIZ_KEY_ID;
		String recordsQuizID = Util.RECORD_TABLE_NAME + "." + Util.QUIZ_KEY_ID;


		//SELECT records.record_id, quizzes.quiz_id, quizzes.title,  records.score_percentage, records.duration
		// FROM cas_db.records, cas_db.quizzes WHERE quizzes.quiz_id  = records.quiz_id
		String selectAll = "SELECT " + recordID + ", "  + quizzesQuizID + ", " + title + ", " + score + ", " + duration +
				" FROM " + Util.RECORD_TABLE_NAME + ", "+ Util.QUIZ_TABLE_NAME +
				" WHERE " + quizzesQuizID + " = " + recordsQuizID;

		Cursor cursor = db.rawQuery(selectAll, null);

		//Loop through our data
		if (cursor.moveToFirst()) {
			do {
				QuizRecord record = new QuizRecord();

				record.setRecordID(Integer.parseInt(cursor.getString(0)));
				record.setQuizID(Integer.parseInt(cursor.getString(1)));
				record.setQuizTitle(cursor.getString(2));
				record.setScorePercentage(Float.parseFloat(cursor.getString(3)));
				record.setDuration(Integer.parseInt(cursor.getString(4)));

				//add record objects to our list
				recordList.add(record);
			}while (cursor.moveToNext());
		}

		return recordList;
	}



	public Quiz getQuiz (int quizID) {
		Quiz quiz = new Quiz();

		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuiz = "SELECT * FROM " + Util.QUIZ_TABLE_NAME + " WHERE " + Util.QUIZ_KEY_ID + " = ?";

		Cursor cursor = db.rawQuery(selectQuiz, new String[] {String.valueOf(quizID)});

		if (cursor.moveToFirst()) {
			quiz.setQuizID(Integer.parseInt(cursor.getString(0)));
			quiz.setTitle(cursor.getString(1));
			quiz.setSubject(cursor.getString(2));
			quiz.setDescription(cursor.getString(3));
			quiz.setDeck((ArrayList<Flashcard>) getFlashcardsByQuizID(quizID));
		}

		return quiz;

	}

	public int updateQuiz(Quiz q) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(Util.QUIZ_KEY_TITLE, q.getTitle());
		values.put(Util.QUIZ_KEY_SUBJECT, q.getSubject());
		values.put(Util.QUIZ_KEY_DESCRIPTION, q.getDescription());

		updateFlashcards(q.getDeck(), q.getQuizID());

		//update the row
		//update(tablename, values, where id = 43)
		return db.update(Util.QUIZ_TABLE_NAME, values, Util.QUIZ_KEY_ID + "= ?",
				new String[]{String.valueOf(q.getQuizID())});
	}

	public void addQuiz(Quiz q){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(Util.QUIZ_KEY_TITLE, q.getTitle());
		values.put(Util.QUIZ_KEY_SUBJECT, q.getSubject());
		values.put(Util.QUIZ_KEY_DESCRIPTION, q.getDescription());

		// Insert to row
		db.insert(Util.QUIZ_TABLE_NAME,  null, values);

		addFlashcards(q.getDeck(), q.getQuizID());

		db.close();
	}


	//Delete single quiz
	public void deleteQuiz(int quizID) {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(Util.QUIZ_TABLE_NAME, Util.QUIZ_KEY_ID + "=?",
				new String[]{String.valueOf(quizID)});

		db.close();

		deleteFlashcardsInQuiz(quizID);
	}

	private void addFlashcards (ArrayList<Flashcard> deck, int quizID) {
		SQLiteDatabase db = this.getWritableDatabase();

		for (Flashcard d : deck) {
			ContentValues values = new ContentValues();
			values.put(Util.QUIZ_KEY_ID, quizID);
			values.put(Util.FLASHCARD_KEY_QUESTION, d.getQuestion());
			values.put(Util.FLASHCARD_KEY_ANSWER, d.getAnswer());
			// Insert to row
			db.insert(Util.FLASHCARD_TABLE_NAME,  null, values);
		}

		db.close();
	}



	private void updateFlashcards(ArrayList<Flashcard> deck, int quizID) {

		SQLiteDatabase db = this.getWritableDatabase();

		for (Flashcard f : deck) {
			ContentValues values = new ContentValues();

			values.put(Util.QUIZ_KEY_ID, quizID);
			values.put(Util.FLASHCARD_KEY_QUESTION, f.getQuestion());
			values.put(Util.FLASHCARD_KEY_ANSWER, f.getAnswer());


			db.update(Util.FLASHCARD_TABLE_NAME, values, Util.FLASHCARD_KEY_ID + "= ?",
					new String[]{String.valueOf(f.getFlashcardID())});
		}
	}

	private void deleteFlashcardsInQuiz (int quizID) {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(Util.RECORD_TABLE_NAME, Util.QUIZ_KEY_ID + "=?",
				new String[]{String.valueOf(quizID)});

		db.close();
	}

	private List<Flashcard> getFlashcardsByQuizID (int quizID) {

		List<Flashcard> flashcardList = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();

		String selectAll = "SELECT * FROM " + Util.FLASHCARD_TABLE_NAME +
				" WHERE " + Util.QUIZ_KEY_ID + " = ?";
		Cursor cursor = db.rawQuery(selectAll, new String[] {String.valueOf(quizID)});

		//Loop through our data
		if (cursor.moveToFirst()) {
			do {
				Flashcard flashcard = new Flashcard();
				flashcard.setFlashcardID(Integer.parseInt(cursor.getString(0)));
				flashcard.setQuestion(cursor.getString(2));
				flashcard.setAnswer(cursor.getString(3));

				//add flashcard objects to our list
				flashcardList.add(flashcard);
			}while (cursor.moveToNext());
		}

		return flashcardList;
	}























}
