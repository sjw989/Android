package com.example.samplenotification

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val tv_result : TextView by lazy{
        findViewById(R.id.tv_result)
    }
    private val tv_token : TextView by lazy{
        findViewById(R.id.tv_token)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getToken()
        updateResult()

    }
    
    private fun getToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener{
            if(it.isSuccessful){
                tv_token.text = it.result
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        updateResult(true)
    }

    @SuppressLint("SetTextI18n")
    private fun updateResult(isNewIntent : Boolean = false){
        tv_result.text = (intent.getStringExtra("notificationType") ?: "앱 런처") +
        if (isNewIntent) {
            "(으)로 갱신했습니다."
        }else {
            "(으)로 실행했습니다."
        }
    }






}