package com.zam.tictactoelove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlayMatchActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout llRed, llYellow;
    private TextView tvRedHeart, tvYellowHeart;
    private ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9;
    private List<int[]> combinationList=new ArrayList<>();
    private int[] cellPositions={-1,0,0,0,0,0,0,0,0,0};
    private int playerTurn=1;
    private int totalSelectedCells=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_play_match);

        llRed=findViewById(R.id.ll_red);
        llYellow=findViewById(R.id.ll_yellow);

        tvRedHeart=findViewById(R.id.tv_red_heart);
        tvYellowHeart=findViewById(R.id.tv_yellow_heart);

        tvRedHeart.setText(getIntent().getStringExtra("FIRST_PLAYER"));
        tvYellowHeart.setText(getIntent().getStringExtra("SECOND_PLAYER"));

        iv1=findViewById(R.id.iv_1);
        iv2=findViewById(R.id.iv_2);
        iv3=findViewById(R.id.iv_3);
        iv4=findViewById(R.id.iv_4);
        iv5=findViewById(R.id.iv_5);
        iv6=findViewById(R.id.iv_6);
        iv7=findViewById(R.id.iv_7);
        iv8=findViewById(R.id.iv_8);
        iv9=findViewById(R.id.iv_9);

        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        iv6.setOnClickListener(this);
        iv7.setOnClickListener(this);
        iv8.setOnClickListener(this);
        iv9.setOnClickListener(this);

        combinationList.add(new int[]{-1,-1,-1});

        combinationList.add(new int[]{1,2,3});
        combinationList.add(new int[]{4,5,6});
        combinationList.add(new int[]{7,8,9});

        combinationList.add(new int[]{1,4,7});
        combinationList.add(new int[]{2,5,8});
        combinationList.add(new int[]{3,6,9});

        combinationList.add(new int[]{1,5,9});
        combinationList.add(new int[]{3,5,7});

        llRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        llYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_1: if (isSelectable(1)) selectCell(iv1,1); break;
            case R.id.iv_2: if (isSelectable(2)) selectCell(iv2,2); break;
            case R.id.iv_3: if (isSelectable(3)) selectCell(iv3,3); break;
            case R.id.iv_4: if (isSelectable(4)) selectCell(iv4,4); break;
            case R.id.iv_5: if (isSelectable(5)) selectCell(iv5,5); break;
            case R.id.iv_6: if (isSelectable(6)) selectCell(iv6,6); break;
            case R.id.iv_7: if (isSelectable(7)) selectCell(iv7,7); break;
            case R.id.iv_8: if (isSelectable(8)) selectCell(iv8,8); break;
            case R.id.iv_9: if (isSelectable(9)) selectCell(iv9,9); break;
        }
    }

    private boolean isSelectable(int position) {
        return cellPositions[position] == 0;
    }

    private void selectCell(ImageView iv, int position) {
        cellPositions[position]=playerTurn;

        if (playerTurn==1) {
            iv.setImageResource(R.drawable.heart_red);
            if (isMatchOver()) new MatchOverDialog().showDialog(this,tvRedHeart.getText()+" "+getString(R.string.game_over_won));
            else if (totalSelectedCells==9) new MatchOverDialog().showDialog(this,getString(R.string.game_over_draw));
            else {
                changePlayerTurn();
                totalSelectedCells++;
            }
        }
        else {
            iv.setImageResource(R.drawable.heart_yellow);
            if (isMatchOver()) new MatchOverDialog().showDialog(this,tvYellowHeart.getText()+" "+getString(R.string.game_over_won));
            else if (totalSelectedCells==9) new MatchOverDialog().showDialog(this,getString(R.string.game_over_draw));
            else {
                changePlayerTurn();
                totalSelectedCells++;
            }
        }
    }

    private boolean isMatchOver() {
        int [] combination;
        for (int i=1; i<combinationList.size(); i++) {
            combination=combinationList.get(i);
            if (cellPositions[combination[0]]==playerTurn &&
                cellPositions[combination[1]]==playerTurn &&
                cellPositions[combination[2]]==playerTurn) {
                return true;
            }
        }
        return false;
    }

    private void changePlayerTurn() {
        if (playerTurn==1) {
            playerTurn=2;
            llYellow.setBackgroundResource(R.drawable.heart_border_highlight);
            llRed.setBackgroundResource(R.drawable.heart_border);
        }
        else {
            playerTurn=1;
            llYellow.setBackgroundResource(R.drawable.heart_border);
            llRed.setBackgroundResource(R.drawable.heart_border_highlight);
        }
    }

    private class MatchOverDialog {

        public void showDialog(Context activity, String message){
            Dialog dialog = new Dialog(activity);
            dialog.setContentView(R.layout.game_over_dialog);
            dialog.setCancelable(false);

            TextView tvMatchOver=(TextView) dialog.findViewById(R.id.tv_game_over);
            tvMatchOver.setText(message);

            Button bRestart=(Button) dialog.findViewById(R.id.b_restart);
            Button bExit=(Button) dialog.findViewById(R.id.b_exit);

            bRestart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    restartMatch();
                    dialog.dismiss();
                }
            });
            bExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            dialog.show();
        }
    }

    private void restartMatch() {
        this.recreate();
    }

    boolean exit = false;

    @Override
    public void onBackPressed() {
        if (exit) {
            super.onBackPressed();
            return;
        }

        this.exit = true;
        Toast.makeText(this, getString(R.string.exit_game), Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                exit=false;
            }
        }, 2000);
    }
}