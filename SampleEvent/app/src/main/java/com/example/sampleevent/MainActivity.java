package com.example.sampleevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    GestureDetector detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView2);
        View view = findViewById(R.id.view4);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float curX = motionEvent.getX();
                float curY = motionEvent.getY();

                if (action == MotionEvent.ACTION_DOWN){
                    print("손가락 눌림 : " + curX + "," +curY);
                }
                else if(action == MotionEvent.ACTION_MOVE){
                    print("손가락 움직임 : " + curX + "," +curY);;
                }
                else if(action == MotionEvent.ACTION_UP){
                    print("손가락 뗌 : "  + curX + "," + curY);
                }
                return true;

            }
        });
        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                print("onDonwn() 호출됨.");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                print("onShowPress() 호출됨.");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                print("onSingleTapUp 호출됨.");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                print("onScroll 호출됨." + distanceX + "," + distanceY);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                print("onLongPress 호출됨.");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                print("onFling 호출됨." + velocityX + ", " + velocityY);
                return true;
            }
        });

        View view1 = findViewById(R.id.view3);
        view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

    }
    public void print(String data){
        textView.append(data + "\n");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
            Toast.makeText(this, "시스템 [BACK] 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}