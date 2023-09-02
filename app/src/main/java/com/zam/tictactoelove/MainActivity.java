/*
 * Copyright (C) 2023 Zokirjon Mamadjonov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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