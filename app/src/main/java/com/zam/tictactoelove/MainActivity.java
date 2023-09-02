package com.zam.tictactoelove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
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
        SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_main);

        etFirstPlayer = findViewById(R.id.et_first_player);
        etSecondPlayer = findViewById(R.id.et_second_player);
        bStartGame = findViewById(R.id.b_start_game);

        setupViews();
    }

    private void setupViews() {
        bStartGame.setOnClickListener(v -> {
            sFirstPlayerName=etFirstPlayer.getText().toString();
            sSecondPlayerName=etSecondPlayer.getText().toString();

            if (sFirstPlayerName.isEmpty() || sSecondPlayerName.isEmpty()) {
                Toast.makeText(this, getString(R.string.enter_names), Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, PlayMatchActivity.class);
                intent.putExtra(AppConstants.FIRST_PLAYER, sFirstPlayerName);
                intent.putExtra(AppConstants.SECOND_PLAYER, sSecondPlayerName);
                startActivity(intent);
            }
        });
    }
}