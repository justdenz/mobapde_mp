package com.example.cardsagainststupidity.Model;

import java.util.ArrayList;
import java.util.Date;

public class Quiz {




    private int quizID;
    private String title;
    private String subject;
    private String description;
    private ArrayList<Flashcard> deck;
    private Date date_created;


    public Quiz() {
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }


    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Flashcard> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Flashcard> deck) {
        this.deck = deck;
    }


}