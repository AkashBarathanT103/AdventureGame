package com.barathanakash.adventuregame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    Button yes;
    Button no;
    int x = 0;
    TextView displayText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yes=findViewById(R.id.yesButton);
        no=findViewById(R.id.noButton);
        Gson gson = new Gson();
        final Story story = gson.fromJson(loadJSONFromAsset(), Story.class);
        System.out.println("oof"+story);
        displayText=findViewById(R.id.Text);
        final Questions[] q = story.getQuestions();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int y = q[x].getAnswer().getYes();
                String s = q[y-1].getText();
                x=y-1;
                displayText.setText(s);

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = q[x].getAnswer().getNo();
                String s = q[a-1].getText();
                x=a-1;
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
