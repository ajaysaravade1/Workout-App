package com.example.workoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DirectAction;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class Excercise extends AppCompatActivity {
    VideoView exVideo;
    MediaPlayer music;
    TextView exName,exTime;
    TextView exCounter;

    String timeLeft;
    int timeMin, timeSec;

    CountDownTimer countDownTimer;
    ArrayList<ExerciseModel> ex;

    int r_index ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise);

        exName = findViewById(R.id.exName);
        exTime = findViewById(R.id.exTime);
        exVideo = findViewById(R.id.exVideo);
        exCounter = findViewById(R.id.cronoMeter);
        music = MediaPlayer.create(getApplicationContext(),R.raw.ashes);
        music.start();



        ex= new ArrayList<ExerciseModel>();
        r_index = getIntent().getIntExtra("random",-1);


        exVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);

            }
        });
        music.setLooping(true);


        ex.add( new ExerciseModel(R.raw.burpees,R.string.burpees,3));//add video, name ,time in Min
        ex.add( new ExerciseModel(R.raw.dumbbell_rows,R.string.dumbbell_rows,4));
        ex.add( new ExerciseModel(R.raw.glute_bridge,R.string.glute_bridge,3));
        ex.add( new ExerciseModel(R.raw.lunges,R.string.lunges,2));
        ex.add( new ExerciseModel(R.raw.overhead,R.string.overhead,2));

        ex.add( new ExerciseModel(R.raw.pushups,R.string.pushups,2));
        ex.add( new ExerciseModel(R.raw.sideplanks,R.string.sideplanks,2));
        ex.add( new ExerciseModel(R.raw.situps,R.string.situps,2));
        ex.add( new ExerciseModel(R.raw.squats,R.string.squats,3));
        /*ex.add( new ExerciseModel(R.raw.single_leg_deadlifts,R.string.single_leg_deadlifts_left,2));

        ex.add( new ExerciseModel(R.raw.single_leg_deadlifts,R.string.single_leg_deadlifts_right,2))*/;

        //Testing purpose <start>
        ex.get(r_index).setCounter(5);
        //<end>

        startExcercise(ex.get(r_index));





    }

    private void startExcercise(ExerciseModel ex)
    {

        playVideo(ex.getVideo());

        exName.setText(getString(ex.getName()));
        exTime.setText(ex.getTime());
        exCounter.setText(""+ex.getCounter());

        countDownTimer = new CountDownTimer(ex.getCounter()*1000,1000) {
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


                exCounter.setText(timeLeft);
            }

            @Override
            public void onFinish() {
                //exCounter.setText("Finished");
                music.release();
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);

                Intent intent2 = new Intent(Excercise.this, Break.class);

                startActivity(intent2);

                Excercise.this.finish();

            }
        };
        countDownTimer.start();



    }

    public void playVideo(int Rid) {

        Uri uri = Uri.parse("android.resource://com.example.workoutapp/" + Rid);

        exVideo.setVideoURI(uri);
        exVideo.requestFocus();
        exVideo.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playVideo(ex.get(r_index).getVideo());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        music.release();

    }
}
