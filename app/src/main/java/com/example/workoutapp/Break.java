package com.example.workoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class Break extends AppCompatActivity {

    TextView brTime;
    MediaPlayer music;
    CountDownTimer countDownTimer;
    String timeLeft;
    int timeMin, timeSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break);
        brTime = findViewById(R.id.brTime);
        music = MediaPlayer.create(getApplicationContext(),R.raw.song_pop);
        music.start();
        music.setLooping(true);

        countDownTimer = new CountDownTimer(4 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeMin = (int) ((millisUntilFinished / 1000) / 60);
                timeSec = (int) ((millisUntilFinished / 1000) % 60);
                if (timeMin > 9) {
                    timeLeft = "" + timeMin;

                } else {
                    timeLeft = "0" + timeMin;
                }
                if (timeSec > 9) {
                    timeLeft = timeLeft + " : " + timeSec;
                } else {
                    timeLeft = timeLeft + " : " + "0" + timeSec;

                }


                brTime.setText(timeLeft);
            }

            @Override
            public void onFinish() {
                //brTime.setText("Finished");
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                music.release();
                Break.this.finish();

            }
        };
        countDownTimer.start();
    }
    public void onBackPressed() {
        super.onBackPressed();
        music.release();

    }
}