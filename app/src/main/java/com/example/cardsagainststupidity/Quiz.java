package com.example.cardsagainststupidity;

import java.util.ArrayList;

public class Quiz {

    private String title;
    private String subject;
    private String description;
    private ArrayList<Flashcard> deck;

    public Quiz() {

    }

    public Quiz (String t, String s, String d, ArrayList<Flashcard> deck) {
        title = t;
        subject = s;
        description = d;
        this.deck = deck;
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