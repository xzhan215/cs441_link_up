package com.example.lineup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button_start;
    Button button_ins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_start = (Button) findViewById(R.id.start);
        button_ins = (Button) findViewById(R.id.instruction);
    }

    public void switch_to_game(View view){
        Intent act_action = new Intent( this, game.class);
        startActivity(act_action);
    }

    public void switch_to_instruction(View view){
        Intent act_action = new Intent( this, instruction.class);
        startActivity(act_action);
    }
}

