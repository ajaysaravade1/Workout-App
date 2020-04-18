package com.example.workoutapp;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DirectAction;
import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {
    Button btnStart;
    TextView noOfSet;
    int sets;


    int EX_ID = 1;
    int BR_ID = 2;
    ArrayList<Integer> finList;
    Random rand = new Random();
    int r_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        noOfSet = findViewById(R.id.noOfSet);

        finList = new ArrayList<Integer>();


        while (finList.size() != 5) {
            r_index = rand.nextInt(9);
            if (finList.indexOf(r_index) == -1) {
                finList.add(r_index);
            }
        }


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    sets = Integer.parseInt(noOfSet.getText().toString());

                    for (int i = 0; i < sets; i++) {
                        for (int j = 0; j < finList.size(); j++) {
                            Intent intent1 = new Intent(MainActivity.this, Excercise.class);
                            intent1.putExtra("random", finList.get(j));
                            startActivityForResult(intent1, EX_ID);


                        }
                    }


                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Enter number of sets", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==EX_ID)
        {
            if(resultCode==RESULT_OK)
            {

            }
            if(resultCode==RESULT_CANCELED)
            {
                setContentView(R.layout.activity_main);
            }
        }
    }
}

