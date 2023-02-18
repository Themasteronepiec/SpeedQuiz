package com.rossxav.speedquiz.Models;

import android.database.Cursor;

public class Question {

    private final String question;
    private final int reponse;

    public Question(String question, int reponse){
        this.question = question;
        this.reponse = reponse;
    }

    public Question(Cursor cursor){
        this.question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        this.reponse = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));
    }

    public String getQuestion() { return question; }

    public boolean isTrue() {
        return reponse == 1;
    }

}
