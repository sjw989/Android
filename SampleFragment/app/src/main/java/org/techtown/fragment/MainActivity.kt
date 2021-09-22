package org.techtown.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFrag(0)
        val btn1 = findViewById<Button>(R.id.button1) as Button
        val btn2 = findViewById<Button>(R.id.button2) as Button
        val btn3 = findViewById<Button>(R.id.button3) as Button

        btn1.setOnClickListener(View.OnClickListener {
            setFrag(0)
        })
        btn2.setOnClickListener(View.OnClickListener {
            setFrag(1)
        })
        btn3.setOnClickListener(View.OnClickListener {
            setFrag(2)
        })
    }

    private fun setFrag(fragNum : Int) {
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum){
            0 -> {
                ft.replace(R.id.main_frag, MainFragment()).commit()
            }
            1 -> {
                ft.replace(R.id.main_frag, MainFragment2()).commit()
            }
            2 -> {
                ft.replace(R.id.main_frag, MainFragment3()).commit()
            }
        }
    }
}