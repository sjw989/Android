package com.example.hw02_apptive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    lateinit var navController:NavController // 네비게이션 컨트롤러

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val nav_host_fragment : View? = findViewById(R.id.nav_host_fragment)

        // login 프래그먼트 실행
        if (nav_host_fragment != null) {
            navController = nav_host_fragment.findNavController()
        }



    }
}