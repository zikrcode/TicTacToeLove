package com.zam.tictactoelove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etFirstPlayer, etSecondPlayer;
    private Button bStartGame;
    private String sFirstPlayerName, sSecondPlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        etFirstPlayer=findViewById(R.id.et_first_player);
        etSecondPlayer=findViewById(R.id.et_second_player);
        bStartGame=findViewById(R.id.b_start_game);

        bStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sFirstPlayerName=etFirstPlayer.getText().toString();
                sSecondPlayerName=etSecondPlayer.getText().toString();

                if (sFirstPlayerName.isEmpty() || sSecondPlayerName.isEmpty()) {
                    Toast.makeText(MainActivity.this, getString(R.string.enter_names), Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent=new Intent(MainActivity.this, PlayMatchActivity.class);
                    intent.putExtra("FIRST_PLAYER",sFirstPlayerName);
                    intent.putExtra("SECOND_PLAYER",sSecondPlayerName);
                    startActivity(intent);
                }
            }
        });
    }
}