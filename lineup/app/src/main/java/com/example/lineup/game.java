package com.example.lineup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class game extends AppCompatActivity {

    //the matrix storing path obscures
    private int[][] board_search = new int[6][6];
    //the matrix storing different block information
    private String[][] board = new String[4][4];
    //the array stores black kind information
    private String[] pics = {"apple", "grape", "banana", "kiwi", "melon", "blueberry", "watermelon", "durian"};
    private ImageButton[][] board_but = new ImageButton[4][4];
    private TextView score_board;
    private int score = 0;
    private int start_r = 0, start_c = 0, des_r = 0, des_c = 0, num_clicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        score_board = (TextView) findViewById(R.id.score);
        board_but[0][0] = (ImageButton) findViewById(R.id.b1);
        board_but[0][1] = (ImageButton) findViewById(R.id.b2);
        board_but[0][2] = (ImageButton) findViewById(R.id.b3);
        board_but[0][3] = (ImageButton) findViewById(R.id.b4);
        board_but[1][0] = (ImageButton) findViewById(R.id.b5);
        board_but[1][1] = (ImageButton) findViewById(R.id.b6);
        board_but[1][2] = (ImageButton) findViewById(R.id.b7);
        board_but[1][3] = (ImageButton) findViewById(R.id.b8);
        board_but[2][0] = (ImageButton) findViewById(R.id.b9);
        board_but[2][1] = (ImageButton) findViewById(R.id.b10);
        board_but[2][2] = (ImageButton) findViewById(R.id.b11);
        board_but[2][3] = (ImageButton) findViewById(R.id.b12);
        board_but[3][0] = (ImageButton) findViewById(R.id.b13);
        board_but[3][1] = (ImageButton) findViewById(R.id.b14);
        board_but[3][2] = (ImageButton) findViewById(R.id.b15);
        board_but[3][3] = (ImageButton) findViewById(R.id.b16);
        initializeboard();
    }

    //initialize the game
    public void initializeboard(){
        //reset the selected button
        start_r = 0; start_c = 0; des_r = 0; des_c = 0; num_clicked = 0;

        score_board.setText(Integer.toString(0));
        Vector<String> pics_copy = new Vector<>();
        Map<String, Integer> pics_temp = new HashMap<String, Integer>();
        for(String tmp: pics) {
            pics_temp.put(tmp, 2);
            pics_copy.add(tmp);
        }

        //initialize the game board
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j< 4; j++) {
                Random generator = new Random();
                int randomIndex = generator.nextInt(pics_copy.size());
                String picked_pic = pics_copy.get(randomIndex);
                int tmp_num = pics_temp.get(picked_pic);
                while(tmp_num == 0){
                    pics_copy.remove(randomIndex);
                    randomIndex = generator.nextInt(pics_copy.size());
                    picked_pic = pics_copy.get(randomIndex);
                    tmp_num = pics_temp.get(picked_pic);
                }
                pics_temp.put(picked_pic, tmp_num-1);
                board[i][j] = picked_pic;
            }
        }

        //attach the image on the button with assigned image
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j< 4; j++) {
                String pic = board[i][j];
                if(pic.equals("apple")) board_but[i][j].setImageResource(R.drawable.apple);
                else if(pic.equals("grape")) board_but[i][j].setImageResource(R.drawable.grape);
                else if(pic.equals("banana")) board_but[i][j].setImageResource(R.drawable.banana);
                else if(pic.equals("kiwi")) board_but[i][j].setImageResource(R.drawable.kiwi);
                else if(pic.equals("durian")) board_but[i][j].setImageResource(R.drawable.durian);
                else if(pic.equals("melon")) board_but[i][j].setImageResource(R.drawable.melon);
                else if(pic.equals("watermelon")) board_but[i][j].setImageResource(R.drawable.watermelon);
                else if(pic.equals("blueberry")) board_but[i][j].setImageResource(R.drawable.blueberry);
            }
        }

        //initialize the search board
        for(int i = 1; i < 5; i++) {
            for(int j = 1; j< 5; j++) {
                board_search[i][j] = 1;
            }
        }
        for(int i = 0; i < 6; i++) {
            board_search[i][0] = 0;
            board_search[i][5] = 0;
            board_search[0][i] = 0;
            board_search[0][5] = 0;
        }
    }

    public void click(View view) {
        boolean isStart = true;
        if(num_clicked == 1)
            isStart = false;
        num_clicked = 1-num_clicked;

        //record the start but and destination but
        int but_id = view.getId();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(board_but[i][j].getId() == but_id) {
                    if (isStart) {
                        start_r = i;
                        start_c = j;
                    } else {
                        des_r = i;
                        des_c = j;
                    }
                    break;
                }
            }
        }

        //after selected two buttons
        if(!isStart){
            if(board[start_r][start_c] == board[des_r][des_c]) {
                score ++;
                board_but[start_r][start_c].setVisibility(View.INVISIBLE);
                board_but[des_r][des_c].setVisibility(View.INVISIBLE);
            }else{
                if(score > 0 ) score--;
            }
            score_board.setText(Integer.toString(score));
        }
    }

    public boolean find_path() {

        return true;
    }


    public void switch_to_menu(View view) {
        Intent act_action = new Intent(this, MainActivity.class);
        startActivity(act_action);
    }
}

