package com.example.lineup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class board extends AppCompatActivity {

    Button button_menu;
    TextView first;
    TextView second;
    TextView third;
    int score, b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        button_menu = (Button) findViewById(R.id.menu1);
        first = (TextView) findViewById(R.id.best1);
        second = (TextView) findViewById(R.id.best2);
        third = (TextView) findViewById(R.id.best3);

        SharedPreferences prefs = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        score = prefs.getInt("key", 0); //0 is the default value
        b1 = prefs.getInt("best1", 0);
        b2 = prefs.getInt("best2", 0);
        b3 = prefs.getInt("best3", 0);

        if(score > b1){
            b3 = b2;
            b2 = b1;
            b1 = score;
        }else if(score < b1 && score > b2){
            b3 = b2;
            b2 = score;
        }else if(score < b2 && score > b3){
            b3 = score;
        }
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("best1", b1);
        editor.putInt("best2", b2);
        editor.putInt("best3", b3);
        editor.apply();

        first.setText("1st: " + Integer.toString(b1));
        second.setText("2nd: " + Integer.toString(b2));
        third.setText("3rd: " + Integer.toString(b3));
    }

    public void switch_to_game(View view){
        Intent act_action = new Intent( this, game.class);
        startActivity(act_action);
    }

    public void switch_to_menu(View view){
        Intent act_action = new Intent( this, MainActivity.class);
        startActivity(act_action);
    }
}