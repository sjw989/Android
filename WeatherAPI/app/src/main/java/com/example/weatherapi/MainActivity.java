package com.example.weatherapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String lon ;
    String lat ;
    EditText editText;
    TextView textView;
    static RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.textView);
        AndPermisson.with(this)
                .runtime()
                .permission(
                        Permission.ACCESS_FINE_LOCATION,
                        Permission.ACCESS_COARSE_LOCATION)
                .onGranted(new Action)
        startLocationService();
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }
    public void startLocationService(){
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        try{
            Location location = manager.getCurrentLocation(LocationManager.GPS_PROVIDER);
            lat = (String)location.getLatitude();
            lon = (String)location.getLongtitue();

        }
        catch(SecurityException e){
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lat = (String)location.getLatitude();
            lon = (String)location.getLongtitue();
        }
    }


    public void makeRequest(){
        String url = "https://api.openweathermap.org/data/2.5/weather?lat="+lat + "&lon=" + lon + "&appid=70d0dd949829ada26da501a0cfbe0fad";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답-> " + response);
                        processResponse(response);
                    }
                    public void processResponse(String response){
                        Gson gson = new Gson();
                        Weather weather = gson.fromJson(response, Weather.class);

                        println("위도 : " + weather.coord.lat);
                        println("경도 : " + weather.coord.lon);
                        println("기압 : " + weather.main.pressure);
                        println("습도 : " + weather.main .humidity);
                        println("시정 : " + weather.visibility);
                        println("온도 : " + weather.main.temp);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        ){
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청 보냄");

    }
    public void println(String data){
        textView.append(data + "\n");
    }

}