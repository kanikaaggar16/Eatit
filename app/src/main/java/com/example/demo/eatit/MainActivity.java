package com.example.demo.eatit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView title;
    Button sign_up , sign_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
      //  getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);
        title = (TextView)findViewById(R.id.title_main);
        sign_in = (Button)findViewById(R.id.signin);
        sign_up = (Button)findViewById(R.id.signup);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.demo.eatit.sign_in.class);
                startActivity(intent);
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, com.example.demo.eatit.sign_up.class);
            startActivity(intent);
            }
        });
       // Typeface face = Typeface.createFromAsset(getAssets()."fonts/")
    }
}
