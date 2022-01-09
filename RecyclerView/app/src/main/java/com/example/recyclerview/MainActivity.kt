package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val info_list = listOf<Pair<String,String>>(Pair("Shin", "010-1234-5678"),
                                                    Pair("Jiwoo", "010-2324-2343"),
                                                    Pair("John", "010-2434-3545"),
                                                    Pair("happy", "010-8787-2469"))

        val recyclerview : RecyclerView = findViewById(R.id.rcv_info)

        val layoutManager = LinearLayoutManager(this) // 레이아웃 매니저 생성
        recyclerview.layoutManager = layoutManager // 레이아웃 메니저 설정

        val adapter = RecyclerViewAdapter(info_list) // 리사이클러뷰 어댑터 객체 생성
        recyclerview.adapter = adapter // 어댑터 설정
    }
}