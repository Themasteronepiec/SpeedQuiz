package com.rossxav.speedquiz.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import com.rossxav.speedquiz.GameActivity;
import com.rossxav.speedquiz.Models.Question;
import com.rossxav.speedquiz.Models.SpeedQuizSqlite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuestionManager implements Iterator<Question> {

    private ArrayList<Question> questionList = new ArrayList<>();

    public QuestionManager() {
        fillDefaultQuestionList();
    }

    public QuestionManager(Context context) {
        initQuestionList(context);
    }


    private void fillDefaultQuestionList() {
        questionList.add(new Question("La capitale de la Suisse est Washington", 0));
        questionList.add(new Question("La langue de Spoke est le Klingon", 1));
        questionList.add(new Question("La théorie de la relativité du temps d'Albert Einstein est E=mc3", 0));
        questionList.add(new Question("Marie-Curie meurt d'une leucémie après avoir découvert l'uranium", 0));
        questionList.add(new Question("Mars, j'ai toujours pensé que c'était un endroit romantique, une planète pleine de mystère et d'aventures", 1));
        questionList.add(new Question("Morty (Personnage principal de Rick et Morty) vient de la dimension C-137", 0));
    }

    private void initQuestionList(Context context) {
        SpeedQuizSqlite helper = new SpeedQuizSqlite(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true, "quiz", new String[]{"idQuiz", "question", "reponse"}, null, null, null, null, "idQuiz", null);

        while (cursor.moveToNext()) {
            questionList.add(new Question(cursor));
        }


        cursor.close();
        db.close();
    }

    @Override
    public boolean hasNext() {
        return questionList.size() > 0;
    }

    @Override
    public Question next() {
        int index = (int) Math.floor(Math.random() * questionList.size());
        return questionList.remove(index);
    }
}
