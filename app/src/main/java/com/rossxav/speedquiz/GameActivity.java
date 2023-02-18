package com.rossxav.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rossxav.speedquiz.Controllers.QuestionManager;
import com.rossxav.speedquiz.Models.Question;


public class GameActivity extends AppCompatActivity {

    private Button BT_replay;
    private Button BT_quit;
    private TextView TV_question1;
    private TextView TV_question2;
    private String TV_question_players;
    private TextView TV_score_player_1;
    private TextView TV_score_player_2;
    private TextView TV_score_player_1_2;
    private TextView TV_score_player_2_1;
    private Button BT_player_1;
    private Button BT_player_2;
    private TextView TV_player1;
    private TextView TV_player2;
    int scorePlayer1 = 0;
    int scorePlayer2 = 0;

    private Question questionActuelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        BT_replay = findViewById(R.id.game_button_replay);
        BT_quit = findViewById(R.id.game_button_quit);
        TV_question1 = findViewById(R.id.game_question1);
        TV_question2 = findViewById(R.id.game_question2);

        TV_player1 = findViewById(R.id.game_name_player1);
        String nom1 = getIntent().getStringExtra("NOM_SAISIE1");
        TV_player1.setText(nom1);
        TV_player2 = findViewById(R.id.game_name_player2);
        String nom2 = getIntent().getStringExtra("NOM_SAISIE2");
        TV_player2.setText(nom2);

        TV_score_player_1 = findViewById(R.id.game_score_player1);
        TV_score_player_2 = findViewById(R.id.game_score_player2);
        TV_score_player_1_2 = findViewById(R.id.game_score_player1_2);
        TV_score_player_2_1 = findViewById(R.id.game_score_player2_1);

        BT_player_1 = findViewById(R.id.game_button_player1);
        BT_player_2 = findViewById(R.id.game_button_player2);

        Intent startIntent = getIntent();

        hideMenuButton();

        QuestionManager qManager = new QuestionManager(this);

        TV_score_player_1.setText(String.valueOf(scorePlayer1));
        TV_score_player_2_1.setText(String.valueOf(scorePlayer1));
        TV_score_player_2.setText(String.valueOf(scorePlayer2));
        TV_score_player_1_2.setText(String.valueOf(scorePlayer2));
        BT_player_1.setEnabled(false);
        BT_player_2.setEnabled(false);

        BT_player_1.setOnClickListener(v -> {
            BT_player_1.setEnabled(false);

            if (questionActuelle.isTrue()) {
                BT_player_2.setEnabled(false);
                scorePlayer1++;
            } else {
                scorePlayer1--;
            }
            TV_score_player_1.setText(String.valueOf(scorePlayer1));
            TV_score_player_2_1.setText(String.valueOf(scorePlayer1));
        });

        //Quand le bouton du joueur 2 est appuyé..
        BT_player_2.setOnClickListener(v -> {
            BT_player_2.setEnabled(false);

            if (questionActuelle.isTrue()) {
                BT_player_1.setEnabled(false);
                scorePlayer2++;
            } else {
                scorePlayer2--;
            }

            TV_score_player_2.setText(String.valueOf(scorePlayer2));
            TV_score_player_1_2.setText(String.valueOf(scorePlayer2));
        });
        countDownTimer.start(); // démarrez la minuterie
    }

    private void hideMenuButton() {
        BT_quit.setVisibility(View.INVISIBLE);
        BT_replay.setVisibility(View.INVISIBLE);
    }

    private void displayMenuButton() {
        BT_quit.setVisibility(View.VISIBLE);
        BT_replay.setVisibility(View.VISIBLE);
    }

    private void resetScore() {
        TV_score_player_1.setText("0");
        TV_score_player_1_2.setText("0");
        TV_score_player_2.setText("0");
        TV_score_player_2_1.setText("0");
    }

    final CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) { // créer une minuterie de compte à rebours
        @Override
        public void onTick(long millisUntilFinished) {
            // mettre à jour le contenu des deux TextView toutes les 5 secondes
            TV_question1.setText(String.valueOf(millisUntilFinished / 1000));
            TV_question2.setText(String.valueOf(millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            // déclenchez une action lorsque le temps est écoulé
            TV_question1.setText("C'est parti!");
            TV_question2.setText("C'est parti!");
            startQuestionIterative();
        }
    };

    private void startQuestionIterative() {
        QuestionManager questions = new QuestionManager(this);
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (questions.hasNext()) {
                    BT_player_1.setEnabled(true);
                    BT_player_2.setEnabled(true);
                    // afficher les questions
                    questionActuelle = questions.next();
                    TV_question1.setText(questionActuelle.getQuestion());
                    TV_question2.setText(questionActuelle.getQuestion());
                    handler.postDelayed(this, 4000);
                } else {
                    // finir le jeu
                    BT_player_1.setEnabled(false);
                    BT_player_2.setEnabled(false);
                    TV_question1.setText("FINISH!");
                    TV_question2.setText("FINISH!");
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }

}
