package com.example.lineup;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class instruction extends AppCompatActivity {

    Button button_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrction);

        button_menu = (Button) findViewById(R.id.menu1);
    }

    public void switch_to_menu(View view){
        Intent act_action = new Intent( this, MainActivity.class);
        startActivity(act_action);
    }
}