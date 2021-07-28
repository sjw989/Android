package com.example.sampleorientation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String name;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToast("onCreate 호출됨");
        editText = findViewById(R.id.editTextTextPersonName);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name =editText.getText().toString();
                showToast("입력된 값을 변수에 저장했습니다 : " + name);
            }
        });

        if(savedInstanceState != null){
            name = savedInstanceState.getString("name");
            showToast("값을 복원했습니다 : " + name);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", name);
    }

    private void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            showToast("방향 : ORIENTATION_LANDSCAPE");
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            showToast("방향 : OREITNATION_PORTRAIT");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        showToast("onStart 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToast("onStop 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("onDestroy 호출됨");
    }

}