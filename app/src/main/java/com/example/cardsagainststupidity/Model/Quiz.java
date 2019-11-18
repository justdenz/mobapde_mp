package com.example.cardsagainststupidity.Model;

import java.util.ArrayList;

public class Quiz {




    private int quizID;
    private String title;
    private String subject;
    private String description;
    private ArrayList<Flashcard> deck;
    private int isPinned;

    public Quiz() {
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

    public int getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(int isPinned) {
        this.isPinned = isPinned;
    }

}