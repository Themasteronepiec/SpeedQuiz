package com.rossxav.speedquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText ET_player1;
    private EditText ET_player2;
    private Button BT_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ET_player1 = findViewById(R.id.main_player1_et);
        ET_player2 = findViewById(R.id.main_player2_et);
        BT_play = findViewById(R.id.main_button_play);

        BT_play.setEnabled(false);

        ET_player1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!ET_player1.getText().toString().equals("") && !ET_player2.getText().toString().equals("")) {
                    BT_play.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (ET_player1.getText().toString().equals("") || ET_player2.getText().toString().equals("")) {
                    BT_play.setEnabled(false);
                }
            }
        });
        ET_player2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!ET_player1.getText().toString().equals("") && !ET_player2.getText().toString().equals("")) {
                    BT_play.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (ET_player1.getText().toString().equals("") || ET_player2.getText().toString().equals("")) {
                    BT_play.setEnabled(false);
                }
            }
        });



        BT_play.setOnClickListener(view -> {
            String nom1 = ET_player1.getText().toString();
            String nom2 = ET_player2.getText().toString();
            Intent resultActivity = new Intent(MainActivity.this, GameActivity.class);
            resultActivity.putExtra("NOM_SAISIE1", nom1);
            resultActivity.putExtra("NOM_SAISIE2", nom2);
            startActivity(resultActivity);
        });

    }
}