package com.example.fastcamp_secretdiary

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private val numberPicker1: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1).apply {
            minValue = 0
            maxValue = 9
        }
    }
    private val numberPicker2: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker2).apply {
            minValue = 0
            maxValue = 9
        }
    }
    private val numberPicker3: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3).apply {
            minValue = 0
            maxValue = 9
        }
    }
    private val btn_btn : AppCompatButton by lazy{
        findViewById<AppCompatButton>(R.id.btn_button)
    }
    private val btn_change : AppCompatButton by lazy{
        findViewById<AppCompatButton>(R.id.btn_changePW)
    }

    private var changePWmode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numberPicker1
        numberPicker2
        numberPicker3

        btn_btn.setOnClickListener{
            if(changePWmode){
                Toast.makeText(this, "비밀번호를 변경중입니다", LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val shared_pw = getSharedPreferences("password", Context.MODE_PRIVATE)

            val user_pw = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (shared_pw.getString("password", "000").equals(user_pw)){
                // 패스워드 성공
                val intent  = Intent(this, DiaryActivity::class.java)
                startActivity(intent)
            }
            else{
                dialog_noteuqlPW()
            }

        }
        btn_change.setOnClickListener{
            val shared_pw = getSharedPreferences("password", Context.MODE_PRIVATE)

            val user_pw = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
            if(changePWmode) {
                // 비밀번호 저장하는 기능
                shared_pw.edit {
                    putString("password", user_pw)
                    commit()
                }
                changePWmode = false
                btn_change.setBackgroundColor(Color.BLACK)
                Toast.makeText(this, "변경되었습니다 !", LENGTH_SHORT).show()


            }
            else{
                // 비밀번호가 맞는지 체크해야함
                if (shared_pw.getString("password", "000").equals(user_pw)){
                    // 패스워드 성공
                    changePWmode = true
                    Toast.makeText(this, "변경할 비밀번호를 설정해주세요", LENGTH_SHORT).show()
                    btn_change.setBackgroundColor(Color.RED)
                }
                else{
                    dialog_noteuqlPW()
                }

            }
        }

    }
    private fun dialog_noteuqlPW() {
        AlertDialog.Builder(this)
            .setTitle("실패")
            .setMessage("비밀번호가 잘못되었습니다")
            .setPositiveButton("확인"){_ , _->}
            .create()
            .show()
        // 패스워드 실패
    }
}
