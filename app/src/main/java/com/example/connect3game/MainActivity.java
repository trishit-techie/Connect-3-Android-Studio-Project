package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    int activePlayer=0;//0-cross,1-circle,2-empty;
    int gameState[]={2,2,2,2,2,2,2,2,2};
    int winningPosition[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};
    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1500);
        Log.i("info", counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive==true) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            }
            else {
                counter.setImageResource(R.drawable.blue);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int win[] : winningPosition) {
                if (gameState[win[0]] == gameState[win[1]] && gameState[win[1]] == gameState[win[2]] && gameState[win[2]] == gameState[win[0]] && gameState[win[0]] != 2) {
                    String winner;
                    gameActive=false;
                    if (activePlayer == 0) {
                        winner = "Player 1";
                    } else {
                        winner = "Player 2";
                    }
                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
                    TextView t = (TextView) findViewById(R.id.resultTextView);
                    t.setText(winner + " has won!");
                    Button b = (Button)findViewById(R.id.playAgainButton);
                    b.setVisibility(view.VISIBLE);
                    t.setVisibility(view.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view) {
        Button b = (Button) findViewById(R.id.playAgainButton);
        TextView t  = (TextView)findViewById(R.id.resultTextView);
        b.setVisibility(view.INVISIBLE);
        t.setVisibility(view.INVISIBLE);
        GridLayout myGridView  = (GridLayout)findViewById(R.id.gridLayout);
        for (int i = 0; i < myGridView.getChildCount(); i++) {
            ImageView counter = (ImageView) myGridView.getChildAt(i);
            counter.setImageDrawable(null);

        }
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        gameActive = true;
        activePlayer=0;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
