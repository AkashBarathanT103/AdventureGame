package com.barathanakash.adventuregame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

import android.content.SharedPreferences;


import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    Button yes;
    Button no;
    TextView playerScore;
    TextView highScore;
    int x = 0;
    TextView displayText;
    int score;
    int hscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yes = findViewById(R.id.yesButton);
        no = findViewById(R.id.noButton);
        playerScore = findViewById(R.id.Text2);
        highScore = findViewById(R.id.Text3);
        final SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        hscore = sharedPreferences.getInt("idTV1", hscore);
        final SharedPreferences.Editor editor = sharedPreferences.edit();


        Gson gson = new Gson();
        final Story story = gson.fromJson(loadJSONFromAsset(), Story.class);
        displayText = findViewById(R.id.Text);
        final Questions[] q = story.getQuestions();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int y = q[x].getAnswer().getYes();
                score = score + q[x].getScore();
                if (score >= 45) {
                    yes.setEnabled(false);
                    no.setEnabled(false);
                    editor.remove("idTV1");
                    hscore = 0;
                    editor.apply();
                    displayText.setText("You've completed the game!");


                }
                if (score > hscore) {
                    hscore = score;
                }


                editor.putInt("idTV1", hscore);
                editor.apply();

                playerScore.setText("Player Score: " + score);
                highScore.setText("High Score: " + sharedPreferences.getInt("idTV1", hscore));
                String s = q[y - 1].getText();
                x = y - 1;
                displayText.setText(s);

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = q[x].getAnswer().getNo();
                score = score + q[x].getScore();
                if (score >= 45) {
                    yes.setEnabled(false);
                    no.setEnabled(false);
                    editor.remove("idTV1");
                    hscore = 0;
                    editor.apply();


                }

                if (score > hscore) {
                    hscore = score;
                }


                editor.putInt("idTV1", hscore);
                editor.apply();
                playerScore.setText("Player Score: " + score);
                highScore.setText("High Score: " + sharedPreferences.getInt("idTV1", hscore));
                String s = q[a - 1].getText();
                x = a - 1;
                displayText.setText(s);
            }
        });
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getBaseContext().getAssets().open("story.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
