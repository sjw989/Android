package com.example.doitmission03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView1;
    ScrollView scrollView2;
    ImageView imageView1;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView1 = findViewById(R.id.scrollView);
        scrollView2 = findViewById(R.id.scrollView1);
        imageView1 = findViewById(R.id.imageView10);
        imageView2 = findViewById(R.id.imageView11);
        scrollView1.setHorizontalScrollBarEnabled(true);
        scrollView2.setHorizontalScrollBarEnabled(true);

    }
    public void upButton(View v){
        imageView1.setVisibility(View.VISIBLE);
        imageView2.setVisibility(View.INVISIBLE);
    }
    public void downButton(View v){
        imageView2.setVisibility(View.VISIBLE);
        imageView1.setVisibility(View.INVISIBLE);
    }

}