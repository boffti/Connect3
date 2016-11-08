package com.aneeshmelkot.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {





    // 0 = yellow, 1 = red;
    int activePlayer = 0;

    // 2 = Unplayed Slot
    int gameState[] = {2,2,2,2,2,2,2,2,2};

    int winningPositions[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameIsActive = true;


    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(600);

            for (int winningPosition[] : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    //Win Situation
                    gameIsActive = false;
                    String winner = "Red";

                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow";
                    }

                    TextView text = (TextView) findViewById(R.id.winText);
                    text.setText(winner +" "+getString(R.string.winText));
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    if (layout.getVisibility() == View.INVISIBLE) {
                        //layout.startAnimation(slideUp);
                        layout.setVisibility(View.VISIBLE);
                    }

                } else {

                    boolean gameIsOver = true;

                    for(int counterState : gameState){
                        if(counterState==2) gameIsOver=false;
                    }

                    if(gameIsOver){
                        TextView text = (TextView) findViewById(R.id.winText);
                        text.setText(R.string.drawText);
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        if (layout.getVisibility() == View.INVISIBLE) {
                            //layout.startAnimation();
                            layout.setVisibility(View.VISIBLE);
                        }
                    }

                }
            }
        }
    }

    public void playAgain(View view){
        gameIsActive=true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }

        GridLayout gl = (GridLayout)findViewById(R.id.board);

        for(int i=0;i<gl.getChildCount();i++){
            ((ImageView)gl.getChildAt(i)).setImageResource(0);
        }

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
