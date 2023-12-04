package com.sandipbhattacharya.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TikTakTowPlay extends AppCompatActivity {
    int gameScoreX = 0;
    int gameScoreY = 0;

    int currentPlayer = 0;
    boolean gameActive = true;
    int[] gameState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };
    String winner = "X";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tik_tak_tow_play);
    }
    public void displayScoreX(int scoreCount){
        TextView scoreXCount = (TextView) findViewById(R.id.scoreCounterX);
        scoreXCount.setText(""+scoreCount);
    }
    public void displayScoreY(int scoreCount){
        TextView scoreYCount = (TextView) findViewById(R.id.scoreCounterY);
        scoreYCount.setText(""+scoreCount);
    }
    public void scoreCounterX(){
        if(winner == "0"){
            gameScoreX++;
        }
        displayScoreX(gameScoreX);
    }
    public void scoreCounterY(){
        if(winner == "X"){
            gameScoreY++;
        }
        displayScoreY(gameScoreY);
    }

    /**
     * This method contains the entire functionality of my game
     * @param view
     */
    public void play(View view) {
        int scoreX = 0;
        TextView scoreXCount = (TextView) findViewById(R.id.scoreCounterX);
        ImageView ivClicked = (ImageView) view;
        ivClicked.setAlpha(1.0f);
        ivClicked.setVisibility(View.VISIBLE);
        int clickedImageView = Integer.parseInt(ivClicked.getTag().toString());
        if(gameState[clickedImageView] == -1 && gameActive){
            gameState[clickedImageView] = currentPlayer;
            if(currentPlayer == 0){
                ivClicked.setImageResource(R.drawable.o);
                currentPlayer = 1;
            } else {
                ivClicked.setImageResource(R.drawable.x);
                currentPlayer = 0;
            }
            ivClicked.setVisibility(View.VISIBLE);
            for (int[] winningPosition: winningPositions
            ) {
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != -1){
                    gameActive = false;
                    if(gameState[winningPosition[0]] == 0){
                        scoreX++;
                        winner = "0";
                        scoreXCount.setText(""+scoreX);
                    }
                    TextView tvMessage = findViewById(R.id.tvMessage);
                    tvMessage.setText(winner + " has won!");
                    LinearLayout layout = findViewById(R.id.restartLayout);
                    layout.setVisibility(View.VISIBLE);
                }else{
                    boolean gameOver = true;
                    for (int currentPlayer: gameState
                    ) {
                        if(currentPlayer == -1){
                            gameOver = false;
                        }
                    }
                    if(gameOver){
                        TextView tvMessage = findViewById(R.id.tvMessage);
                        tvMessage.setText("It's a draw");
                        LinearLayout layout = findViewById(R.id.restartLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        scoreCounterX();
        scoreCounterY();
    }

    public void playAgain(View view) {
        int scoreY = 0;
        TextView scoreYCount = (TextView) findViewById(R.id.scoreCounterY);
        winner = "X";
        scoreY++;
        scoreYCount.setText("scoreY");
        gameActive = true;
        currentPlayer = 0;
        LinearLayout layout = findViewById(R.id.restartLayout);
        layout.setVisibility(View.INVISIBLE);
        for (int i=0; i< gameState.length; i++){
            gameState[i] = -1;
        }
        LinearLayout gameLayout = findViewById(R.id.gameLayout);
        for (int i=0; i < gameLayout.getChildCount(); i++){
            View subView = gameLayout.getChildAt(i);
            if(subView instanceof LinearLayout){
                LinearLayout linearLayout = (LinearLayout) subView;
                for(int j=0; j < linearLayout.getChildCount(); j++){
                    View linearSubView = linearLayout.getChildAt(j);
                    if(linearSubView instanceof ImageView){
                        linearSubView.setAlpha(0.0f);
                    }
                }
            }
        }
    }
}