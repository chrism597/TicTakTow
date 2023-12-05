package com.gamecenter.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button goToAnotherClass =  findViewById(R.id.buttonPlay);
    }
    public void switchScreen(View v){
        Intent intent = new Intent(MainActivity.this, TikTakTowPlay.class);
        startActivity(intent);
    }
}