package com.example.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv : TextView = findViewById(R.id.tv)
        val edit_tv : EditText = findViewById(R.id.edit_tv)
        val btn : Button = findViewById(R.id.btn_change)

        val shared = getSharedPreferences("text", MODE_PRIVATE)
        tv.text = shared.getString("str","디폴트 스트링입니다.")

        btn.setOnClickListener{
            tv.text = edit_tv.text.toString()
            shared.edit {
                putString("str",edit_tv.text.toString())
            }
        }
    }
}