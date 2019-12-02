package com.example.cardsagainststupidity.util;

public class Util {
	/*
		Just a Utility class to hold all values
	 */
	// Database Related Items
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "cas_db";

	//flashcard table columnn names
	public static final String FLASHCARD_TABLE_NAME = "flashcards";
	public static final String FLASHCARD_KEY_ID = "flashcard_id";
	public static final String FLASHCARD_KEY_QUESTION = "question";
	public static final String FLASHCARD_KEY_ANSWER = "answer";

	public static final String QUIZ_TABLE_NAME = "quizzes";
	public static final String QUIZ_KEY_ID = "quiz_id";
	public static final String QUIZ_KEY_TITLE = "title";
	public static final String QUIZ_KEY_SUBJECT = "subject";
	public static final String QUIZ_KEY_DESCRIPTION = "description";
	public static final String QUIZ_KEY_DATECREATED = "date_created";

	public static final String RECORD_TABLE_NAME = "records";
	public static final String RECORD_KEY_ID = "record_id";
	public static final String RECORD_KEY_SCOREPERCENTAGE = "score_percentage";
	public static final String RECORD_KEY_DURATION = "duration";
	public static final String RECORD_DATE_TAKEN = "date_taken";
}
