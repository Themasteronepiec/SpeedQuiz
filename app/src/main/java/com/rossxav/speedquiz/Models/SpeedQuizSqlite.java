package com.rossxav.speedquiz.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpeedQuizSqlite extends SQLiteOpenHelper {

    static String DB_NAME = "SpeedQuiz.db";

    static int DB_VERSION = 1;

    public SpeedQuizSqlite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateDataTable = "CREATE TABLE quiz (" +
                "idQuiz INTEGER PRIMARY KEY," +
                "question TEXT," +
                "reponse BOOLEAN" +
                ")";
        sqLiteDatabase.execSQL(sqlCreateDataTable);
        sqLiteDatabase.execSQL("INSERT INTO quiz VALUES (null,\"La langue de Spoke est le Klingon\",1)");
        sqLiteDatabase.execSQL("INSERT INTO quiz VALUES (null,\"La capitale de la Suisse est Washington\",0)");
        sqLiteDatabase.execSQL("INSERT INTO quiz VALUES (null,\"La théorie de la relativité du temps d'Albert Einstein est E=mc3\",0)");
        sqLiteDatabase.execSQL("INSERT INTO quiz VALUES (null,\"Marie-Curie meurt d'une leucémie après avoir découvert l'uranium\",0)");
        sqLiteDatabase.execSQL("INSERT INTO quiz VALUES (null,\"Mars, j'ai toujours pensé que c'était un endroit romantique, une planète pleine de mystère et d'aventures\",1 )");
        sqLiteDatabase.execSQL("INSERT INTO quiz VALUES (null,\"Morty (Personnage principal de Rick et Morty) vient de la dimension C-137\",0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
